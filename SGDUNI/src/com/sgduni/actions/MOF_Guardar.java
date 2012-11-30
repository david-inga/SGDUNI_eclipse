/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_estado_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orpro_ta_mof_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orpro_ta_mof;
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
public class MOF_Guardar extends org.apache.struts.action.Action {
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
      orpro_ta_mof_DAO daoMOF=new orpro_ta_mof_DAO(dataSource);
      //orgen_ta_estado_DAO daoEst=new orgen_ta_estado_DAO(dataSource);

      //array
      ArrayList<orgen_ta_dependencia> listaDependencia = dao.getDependencias();
      ArrayList<orgen_ta_facultad> listaFacultad = daoFac.getFacultades();
      //ArrayList<orgen_ta_estado> listaEstados = daoEst.getEstados();

      request.setAttribute("dependencias", listaDependencia);
      request.setAttribute("facultad", listaFacultad);
      //request.setAttribute("estados", listaEstados);

      //form
      orpro_ta_mof theForm = (orpro_ta_mof) form;

      //variables
      String in_codigo_mof=(String)request.getParameter("in_codigo_mof");
      String NombreArchivo=null;
      int ErrorLoadFileT=0;

      if (form instanceof orpro_ta_mof)
      {
        // mostramos los parametros del fichero

        //Variables
        FormFile myFile = theForm.getVc_ruta_archivo_v1();
        String contentType = myFile.getContentType();
        String fileName  = myFile.getFileName();
        Calendar Cal= Calendar.getInstance();
        String fecTemDoc= Cal.get(Cal.DATE)+"_"+(Cal.get(Cal.MONTH)+1)+"_"+Cal.get(Cal.YEAR)+"_"+Cal.get(Cal.HOUR_OF_DAY)+"_"+Cal.get(Cal.MINUTE)+"_"+Cal.get(Cal.SECOND);
        NombreArchivo="("+theForm.getCh_codigo_mof().trim()+fecTemDoc+")"+fileName;//Nombre del Archivo
        int fileSize = myFile.getFileSize();
        byte[] fileData  = myFile.getFileData();
        //String filePath = getServlet().getServletContext().getRealPath("/") +"WEB-INF/documentos/mof";
        String filePath = getServlet().getServletContext().getRealPath("/") +"documentos/mof";

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
            NombreArchivo = request.getParameter("txtnom_archivo_db");
          }
      }

      //System.out.println("M : "+in_codigo_mof);
      if(ErrorLoadFileT==0)
      {
       if(in_codigo_mof!=null && !in_codigo_mof.equals("0"))
       {
              if(daoMOF.modificarMof(theForm, Integer.parseInt(in_codigo_mof.trim()),idUser,idEstado,NombreArchivo))
              {
                 theForm.setMensaje("Datos Modificados Correctamente");
              }else{
                 theForm.setMensaje("Ocurrio un error al intentar modificar los datos");
              }
       }else{
              if(daoMOF.guardarMof(theForm, idUser,idEstado,NombreArchivo))
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
      orpro_ta_mof forMof=new orpro_ta_mof();
      if(!in_codigo_mof.equals("0"))
      {//Busamos los datos del MOF para modificar
          orpro_ta_mof_DAO daoMof=new orpro_ta_mof_DAO(dataSource);
          forMof=daoMof.BuscarMof(Integer.parseInt(in_codigo_mof));
      }
      request.setAttribute("forMof", forMof);//Formulario
      return mapping.findForward(GUARDADO);
    }
}
