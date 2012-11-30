/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_estructura_organica_DAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import com.sgduni.forms.orgen_ta_estructura_organica;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import javax.servlet.http.HttpSession;
import org.apache.struts.upload.FormFile;

/*
 * @author JMarcos
 */
public class Estructura_Oraganica_Modificar extends org.apache.struts.action.Action
{
    /* forward name="success" path="" */
    private final static String SUCCESS = "irFormularioModificar";
    private String rutaArchivoSubido = "";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {

      //Session usuario
        HttpSession session=request.getSession();
        int idUser=Integer.parseInt(session.getAttribute("xid").toString());
        String Rol=(String)session.getAttribute("xrol");
        String nomUsu = (String)session.getAttribute("xnomus");
        Rol=Rol.trim().toUpperCase();

      //conec
      DataSource dataSource = getDataSource(request, "DSconnection");
      orpro_ta_estructura_organica_DAO daoEO = new orpro_ta_estructura_organica_DAO(dataSource);

      orgen_ta_estructura_organica objEstructura = (orgen_ta_estructura_organica)form;


      //se almacena el nombre del archivo
      String NombreArchivo = null;

      //sirve para valdiar si el archivo cargo o no
      int ErrorLoadFileT = 0;

      if (form instanceof orgen_ta_estructura_organica)
      {
        // mostramos los parametros del fichero

        //Variables
        FormFile myFile = objEstructura.getVc_ruta_archivo();

        String fileName  = myFile.getFileName();

        Calendar Cal= Calendar.getInstance();

        String fecTemDoc = Cal.get(Cal.DATE)+"_"+(Cal.get(Cal.MONTH)+1)+"_"+Cal.get(Cal.YEAR)+"_"+Cal.get(Cal.HOUR_OF_DAY)+"_"+Cal.get(Cal.MINUTE)+"_"+Cal.get(Cal.SECOND);

        NombreArchivo = "("+objEstructura.getCh_codigo_estructura() + fecTemDoc + ")" + fileName;//Nombre del Archivo

        //String filePath = getServlet().getServletContext().getRealPath("/") +"WEB-INF/documentos/mof";
        String filePath = getServlet().getServletContext().getRealPath("/") +"documentos/EstructurasOrganicas";
        rutaArchivoSubido = filePath+"/"+NombreArchivo;
          if( !fileName.equals("") )
          {

            //System.out.println("Ruta Archivo:" +filePath+"::"+fileName);
            //Creamos el archivo
                File fileToCreate = new File(filePath, NombreArchivo);
            //Verificamos si existe el archivo para no reemplazarlo
               if(!fileToCreate.exists())
               {
                    FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                    fileOutStream.write(myFile.getFileData());
                    fileOutStream.flush();
                    fileOutStream.close();
                    ErrorLoadFileT=0;
                    System.out.println("ARCHIVO CARGADO");
               }
               else
               {
                    ErrorLoadFileT=1;
                    System.out.println("EL ARCHIVO YA EXISTE");
               }
          }
          else
          {
            NombreArchivo = request.getParameter("txtnom_archivo_db");
          }
      }

      if(ErrorLoadFileT == 0)
      {
          if( daoEO.guardarNuevaVersionEstructuraOrganica(objEstructura, nomUsu, idUser, NombreArchivo) )
          {
             objEstructura.setMensaje("La Estructura Organica fue modificada");
          }
          else
          {
             objEstructura.setMensaje("Ocurrio un error al intentar modificar los datos");
             File archivo_subido = new File(rutaArchivoSubido);
             archivo_subido.delete();
          }
      }
      else
      {
            objEstructura.setMensaje("Ocurrio un error al intentar cargar el archivo");
      }























      orgen_ta_estructura_organica objEstructuraRetorno = daoEO.getEstructuraOrganica( objEstructura.getIn_codigo_estructura().toString() );
      request.setAttribute("EstructuraOrganica", objEstructuraRetorno );
        
      return mapping.findForward(SUCCESS);
    }
}
