/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_resolucion_rectoral_DAO;
import com.sgduni.dao.orgen_ta_estado_DAO;
import com.sgduni.forms.orpro_resolucion_rectoral;
import com.sgduni.forms.orgen_ta_estado;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Sistemas
 */
public class Resolucion_Rectoral_Guardar extends org.apache.struts.action.Action
{    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      //conec
      DataSource dataSource = getDataSource(request, "DSconnection");

      //Session usuario
        HttpSession session=request.getSession();
        int idUser=Integer.parseInt(session.getAttribute("xid").toString());
        //String idEstado=request.getParameter("in_codigo_estado");
          int idEstado=8;//Aprobado por el RECTORADO
      //DAO      
      orpro_resolucion_rectoral_DAO daoRR=new orpro_resolucion_rectoral_DAO(dataSource);
      orgen_ta_estado_DAO daoEst=new orgen_ta_estado_DAO(dataSource);

      //array      
      ArrayList<orgen_ta_estado> listaEstados = daoEst.getEstados();

      request.setAttribute("estados", listaEstados);

      //form
      orpro_resolucion_rectoral theForm = (orpro_resolucion_rectoral)form;
    
      //variables
      String NombreArchivo="";      
          if(daoRR.guardarResolucion(theForm, idUser,idEstado,NombreArchivo))
          {
             theForm.setMensaje("Datos Guardados Correctamente");
             theForm.reset(mapping, request);
          }else{
             theForm.setMensaje("Ocurrio un error al intentar guardar los datos");
          }


      /*
      int ErrorLoadFileT=0;

      if (form instanceof orpro_resolucion_rectoral)
      {
        // mostramos los parametros del fichero

        //Variables
        FormFile myFile = theForm.getVc_ruta_archivo();
        String contentType = myFile.getContentType();
        String fileName  = myFile.getFileName();
        NombreArchivo="("+theForm.getCh_codigo_resolucion()+")"+fileName;//Nombre del Archivo
        int fileSize = myFile.getFileSize();
        byte[] fileData  = myFile.getFileData();
        //String filePath = getServlet().getServletContext().getRealPath("/") +"WEB-INF/documentos/mof";
        String filePath = getServlet().getServletContext().getRealPath("/") +"documentos/resolucionrectorial";

          if(!fileName.equals(""))
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
               }else{
                    ErrorLoadFileT=1;
                    System.out.println("EL ARCHIVO YA EXISTE");
               }
          }
      }
   
      if(ErrorLoadFileT==0)
      {
          if(daoRR.guardarResolucion(theForm, idUser,Integer.parseInt(idEstado),NombreArchivo))
          {
             theForm.setMensaje("Datos Guardados Correctamente");
             theForm.reset(mapping, request);
          }else{
             theForm.setMensaje("Ocurrio un error al intentar guardar los datos");
          }
      }else
      {
            theForm.setMensaje("Ocurrio un error al intentar cargar el archivo");
      }
      */
        return mapping.findForward("guardado");
    }
}
