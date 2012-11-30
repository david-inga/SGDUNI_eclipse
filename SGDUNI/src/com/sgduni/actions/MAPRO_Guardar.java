/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_estado_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orpro_ta_mapro_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orpro_ta_mapro;
import com.sgduni.forms.orgen_ta_estado;

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
public class MAPRO_Guardar extends org.apache.struts.action.Action {
    private final static String GUARDADO = "guardado";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      //conec
      DataSource dataSource = getDataSource(request, "DSconnection");

      //Session usuario
        HttpSession session=request.getSession();
        int idUser=Integer.parseInt(session.getAttribute("xid").toString());
        String Rol=(String)session.getAttribute("xrol");
        Rol=Rol.trim().toUpperCase();
        int idEstado=0;
        if(Rol.equals("ROL01"))
          idEstado=11;//Si el usuario que registra es OCDO el estado POR DEFECTO ES 11 (revision OCDO)
        else if(Rol.equals("ROL02"))
          idEstado=12;//Si el usuario que registra es USUARIO el estado POR DEFECTO ES 12 (revision USUARIO)
        
      //DAO
      orgen_ta_dependencia_DAO dao = new orgen_ta_dependencia_DAO(dataSource);
      orgen_ta_facultad_DAO daoFac=new orgen_ta_facultad_DAO(dataSource);
      orpro_ta_mapro_DAO daoMAPRO=new orpro_ta_mapro_DAO(dataSource);
      //orgen_ta_estado_DAO daoEst=new orgen_ta_estado_DAO(dataSource);

      //array
      ArrayList<orgen_ta_dependencia> listaDependencia = dao.getDependencias();
      ArrayList<orgen_ta_facultad> listaFacultad = daoFac.getFacultades();
      //ArrayList<orgen_ta_estado> listaEstados = daoEst.getEstados();

      request.setAttribute("dependencias", listaDependencia);
      request.setAttribute("facultad", listaFacultad);
      //request.setAttribute("estados", listaEstados);

      //form
      orpro_ta_mapro theForm = (orpro_ta_mapro) form;

      //variables
      String in_codigo_mapro=(String)request.getParameter("in_codigo_mapro");
      String NombreArchivo=null;
      int ErrorLoadFileT=0;

      if (form instanceof orpro_ta_mapro)
      {
        // mostramos los parametros del fichero

        //Variables
        FormFile myFile = theForm.getVc_ruta_archivo_v1();
        String contentType = myFile.getContentType();
        String fileName  = myFile.getFileName();
        Calendar Cal= Calendar.getInstance();
        String fecTemDoc= Cal.get(Cal.DATE)+"_"+(Cal.get(Cal.MONTH)+1)+"_"+Cal.get(Cal.YEAR)+"_"+Cal.get(Cal.HOUR_OF_DAY)+"_"+Cal.get(Cal.MINUTE)+"_"+Cal.get(Cal.SECOND);
        NombreArchivo="("+theForm.getCh_codigo_mapro()+fecTemDoc+")"+fileName;//Nombre del Archivo
        int fileSize = myFile.getFileSize();
        byte[] fileData  = myFile.getFileData();
        //String filePath = getServlet().getServletContext().getRealPath("/") +"WEB-INF/documentos/mapro";
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
                    System.out.println("ARCHIVO CARGADO");
               }else{
                    ErrorLoadFileT=1;
                    System.out.println("EL ARCHIVO YA EXISTE");
               }
          }else{
            NombreArchivo=request.getParameter("txtnom_archivo_db");
          }
      }

      if(ErrorLoadFileT==0)
      {
           if(in_codigo_mapro!=null && !in_codigo_mapro.equals("0"))
           {
                  if(daoMAPRO.modificarMapro(theForm, Integer.parseInt(in_codigo_mapro.trim()),idUser,idEstado,NombreArchivo))
                  {
                     theForm.setMensaje("Datos Modificados Correctamente");
                  }else{
                     theForm.setMensaje("Ocurrio un error al intentar modificar los datos");
                  }
           }else{
                  if(daoMAPRO.guardarMapro(theForm, idUser,idEstado,NombreArchivo))
                  {
                     theForm.setMensaje("Datos Guardados Correctamente");
                  }else{
                     theForm.setMensaje("Ocurrio un error al intentar guardar los datos");
                  }
           }
      }else
      {
            theForm.setMensaje("Ocurrio un error al intentar cargar el archivo");
      }
      orpro_ta_mapro forMapro=new orpro_ta_mapro();
      if(!in_codigo_mapro.equals("0"))
      {//Buscamos los datos del MAPRO para modificar
          orpro_ta_mapro_DAO daoMapro=new orpro_ta_mapro_DAO(dataSource);
          forMapro=daoMapro.BuscarMapro(Integer.parseInt(in_codigo_mapro));
      }
      request.setAttribute("forMapro", forMapro);//Formulario
      return mapping.findForward(GUARDADO);
    }
}
