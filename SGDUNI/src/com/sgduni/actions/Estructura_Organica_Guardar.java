package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.dao.orpro_ta_estructura_organica_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_estructura_organica;
import com.sgduni.forms.orgen_ta_facultad;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.upload.FormFile;

/*
 * @author Jmarcos
 */
public class Estructura_Organica_Guardar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irFormularioNuevo";
    private String rutaArchivoSubido = "";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        //conec
        DataSource dataSource = getDataSource(request, "DSconnection");

        //Session usuario
        HttpSession session=request.getSession();
        
        int idUser = Integer.parseInt(session.getAttribute("xid").toString());
        String nomUsu = (String)session.getAttribute("xnomus");
        String tipoDepenFacu = session.getAttribute("xtipodepen_facul").toString();
        int idDepenFacu = Integer.parseInt( session.getAttribute("xiddepen_facul").toString() );

      //DAO
      orgen_ta_dependencia_DAO dao = new orgen_ta_dependencia_DAO(dataSource);
      orgen_ta_facultad_DAO daoFac=new orgen_ta_facultad_DAO(dataSource);
      orpro_ta_estructura_organica_DAO daoEO = new orpro_ta_estructura_organica_DAO(dataSource);
      orpro_oficio_circular_DAO daoOf = new orpro_oficio_circular_DAO(dataSource);

      //array
      ArrayList<orgen_ta_dependencia> listaDependencia = dao.getDependencias();
      ArrayList<orgen_ta_facultad> listaFacultad = daoFac.getFacultades();
      String nuevo_codigo_generado = daoEO.getCodigoGenerado();

      request.setAttribute("dependencias", listaDependencia);
      request.setAttribute("facultades", listaFacultad);
      request.setAttribute("nuevo_codigo_generado",nuevo_codigo_generado);

      //form
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
          if(daoEO.guardarEstructuraOrganica(objEstructura, idUser,NombreArchivo,nomUsu))
          {
             Integer codigoRetorno = daoEO.getIDDeLaEstructuraOrganica(objEstructura.getCh_codigo_estructura());
             // System.out.println("codigo de la estructura es "+codigoRetorno);

             boolean estaoEnvio = daoEO.guardarEstructuraOficio(codigoRetorno, objEstructura.getIn_codigo_oficio());

             if(estaoEnvio)
             {
                 //System.out.println("llego al cambio de estado");
                 daoOf.cambiarEstadoOficioEnTramite(30, objEstructura.getIn_codigo_oficio(),idDepenFacu,tipoDepenFacu );
                 objEstructura.setMensaje2("El Oficio se Anexo Correctamente");
             }
             else
             {
                objEstructura.setMensaje2("Error al Anexar el Oficio");
             }
             objEstructura.setMensaje("El Organigrama se Envió Correctamente");
          }
          else
          {
             objEstructura.setMensaje("Ocurrio un error al intentar guardar los datos");
             File archivo_subido = new File(rutaArchivoSubido);
             archivo_subido.delete();
          }
      }
      else
      {
            objEstructura.setMensaje("Ocurrio un error al intentar cargar el archivo");
      }

      return mapping.findForward(SUCCESS);
    }
}
