/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_versiones_mapro_DAO;
import com.sgduni.forms.orpro_ta_versiones_mapro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
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
public class Versiones_Documentos_Gestion_Mapro_Guardar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
     public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {        
        //sesion
        HttpSession session=request.getSession(true);
        String Rol=(String)session.getAttribute("xrol");
        Rol=Rol.trim().toUpperCase();
        String idDepFacu=session.getAttribute("xiddepen_facul").toString();
        String tipDepFac=(String)session.getAttribute("xtipodepen_facul").toString();
        int idEstado=0;
        
           if(Rol.equals("ROL01"))
           {//OCDO
              idEstado=11;//
           }else if(Rol.equals("ROL02"))
           {//USUARIO
              idEstado=12;
           }else if(Rol.equals("ROL03"))
           {//ASESORIA LEGAL
              idEstado=14;
           }

        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        //variable
        //String idEstado=request.getParameter("in_codigo_estado");
        int idUser=Integer.parseInt(session.getAttribute("xid").toString());
        Calendar Cal= Calendar.getInstance();
        String fecTemDoc= Cal.get(Cal.DATE)+"_"+(Cal.get(Cal.MONTH)+1)+"_"+Cal.get(Cal.YEAR)+"_"+Cal.get(Cal.HOUR_OF_DAY)+"_"+Cal.get(Cal.MINUTE)+"_"+Cal.get(Cal.SECOND);
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_versiones_mapro_DAO daoMAPRO=new orpro_ta_versiones_mapro_DAO(dataSource);
        orpro_ta_versiones_mapro theForm = (orpro_ta_versiones_mapro) form;

        PrintWriter writer;
        try {
             writer =response.getWriter();
              String NombreArchivo=null;
              int ErrorLoadFileT=0;
              if (form instanceof orpro_ta_versiones_mapro)
              {
                //Variables
                FormFile myFile = theForm.getVc_ruta_archivo();
                String contentType = myFile.getContentType();
                String fileName  = myFile.getFileName();
                NombreArchivo="("+fecTemDoc+")"+fileName;//Nombre del Archivo
                int fileSize = myFile.getFileSize();
                byte[] fileData  = myFile.getFileData();
                //String filePath = getServlet().getServletContext().getRealPath("/") +"WEB-INF/documentos/mof";
                String filePath = getServlet().getServletContext().getRealPath("/") +"documentos/mapro";

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
                            //System.out.println("ARCHIVO CARGADO");
                       }else{
                            ErrorLoadFileT=1;
                            //System.out.println("EL ARCHIVO YA EXISTE");
                       }
                  }
                }

              if(ErrorLoadFileT==0)
              {
                  if(daoMAPRO.guardarVersionMapro(theForm, idUser,idEstado,NombreArchivo))
                  {
                     writer.print("<script language='javascript' type='text/javascript'>parent.fnl_doc_mapro_resul(1)</script>");
                  }else{
                     writer.print("<script language='javascript' type='text/javascript'>parent.fnl_doc_mapro_resul(0)</script>");
                  }
              }else
              {
                    writer.print("<script language='javascript' type='text/javascript'>parent.fnl_doc_mapro_resul(0)</script>");
              }


             writer.flush();
             writer.close();
        } catch (IOException ex) {
            writer =response.getWriter();
            writer.print("<script language='javascript' type='text/javascript'>parent.fnl_doc_mapro_resul(0)</script>");
            writer.flush();
            writer.close();
            Logger.getLogger(Versiones_Documentos_Gestion_Mapro_Guardar.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;//mapping.findForward(SUCCESS);
    }
}
