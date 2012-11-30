/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.forms.orgen_ta_usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author marco antonio estrella cardenas
 */
public class Usuario_insertar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private String rutaArchivoSubido = "";

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {     
        HttpSession sesion = request.getSession();
        String nombreUsuario = sesion.getAttribute("xnomus").toString();
        String idUsuario = sesion.getAttribute("xid").toString();
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_usuario_DAO dao = new orgen_ta_usuario_DAO(dataSource);
        orgen_ta_usuario objForm = (orgen_ta_usuario)form;

      String NombreArchivo = null;
      int ErrorLoadFileT = 0;

      if (form instanceof orgen_ta_usuario)
      {
        FormFile myFile = objForm.getVcfirma();
        String fileName  = myFile.getFileName();
        Calendar Cal = Calendar.getInstance();
        String fecTemDoc = Cal.get(Cal.DATE)+"_"+(Cal.get(Cal.MONTH)+1)+"_"+Cal.get(Cal.YEAR)+"_"+Cal.get(Cal.HOUR_OF_DAY)+"_"+Cal.get(Cal.MINUTE)+"_"+Cal.get(Cal.SECOND);
        NombreArchivo = "("+objForm.getVc_nombres()+"-"+objForm.getVc_apellido_paterno()+fecTemDoc + ")" + fileName;//Nombre del Archivo
         String filePath = getServlet().getServletContext().getRealPath("/") +"documentos/firmas";
         rutaArchivoSubido = filePath+"/"+NombreArchivo;
          if( !fileName.equals("") )
          {
                File fileToCreate = new File(filePath, NombreArchivo);
               //Verificamos si existe el archivo para no reemplazarlo
               if(!fileToCreate.exists())
               {
                    FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                    fileOutStream.write(myFile.getFileData());
                    fileOutStream.flush();
                    fileOutStream.close();
                    ErrorLoadFileT=0;
                    //System.out.println("FIRMA CARGADA");
               }
               else
               {
                    ErrorLoadFileT=1;
                    //System.out.println("LA FIRMA YA EXISTE");
               }
          }
          else
          {
            NombreArchivo = request.getParameter("txtnom_archivo_db");
          }
      }

      if(ErrorLoadFileT == 0)
      {
          //System.out.println("el objeto es "+objForm.getVc_nombres()+", n"+nombreUsuario+", id = "+idUsuario+" nombrearchivo = "+NombreArchivo);
          if(dao.guardarUsuario(objForm,nombreUsuario,idUsuario,NombreArchivo))
          {
             objForm.setMensaje("Usuario registrado correctarmente");
          }
          else
          {
            objForm.setMensaje("Error! ocurrio un error al registrar el usuario");
            File archivo_subido = new File(rutaArchivoSubido);
            archivo_subido.delete();
          }
      }
      else
      {
            objForm.setMensaje("Ocurrio un error al intentar cargar la firma");
      }

      return mapping.findForward("registrar");
    }
}
