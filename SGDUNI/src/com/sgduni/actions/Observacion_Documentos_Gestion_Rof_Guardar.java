/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_observaciones_rof_DAO;
import com.sgduni.forms.orpro_ta_observaciones_rof;

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
public class Observacion_Documentos_Gestion_Rof_Guardar extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession session=request.getSession(true);
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        //variable

        int idUser=Integer.parseInt(session.getAttribute("xid").toString());
        int in_codigo_rof=Integer.parseInt(request.getParameter("in_codigo_rof"));
        int idEstado=0;
        String Rol=(String)session.getAttribute("xrol");
        Rol=Rol.trim().toUpperCase();

        String vc_observacion=request.getParameter("vc_observacion");
        String ckaprobar=(request.getParameter("ckaprobar")!=null && request.getParameter("ckaprobar").equals("1"))?request.getParameter("ckaprobar"):"0";

        Calendar Cal= Calendar.getInstance();
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_observaciones_rof_DAO daoROF=new orpro_ta_observaciones_rof_DAO(dataSource);
        orpro_ta_observaciones_rof theForm =new orpro_ta_observaciones_rof();

        idEstado=1;//Estado Pendiente
        if(Rol.equals("ROL01"))
        {//OCDO
            if(ckaprobar.equals("1"))
                 idEstado=13;//aprobado OCO
        }
        else if(Rol.equals("ROL02"))
        {//USUARIO
            idEstado=1;
        }
        else if(Rol.equals("ROL03"))
        {
            if(ckaprobar.equals("1"))
                 idEstado=15;//APROBACION ASESORIA LEGAL
        }
        
        PrintWriter writer;
        try {
            theForm.setIn_codigo_rof(in_codigo_rof);
            theForm.setVc_observacion(vc_observacion);
           writer =response.getWriter();
//            if(daoROF.guardarObservacionRof(theForm, idUser,idEstado))
//            {
//               writer.print("1");
//            } else
//            {
//                writer.print("0");
//            }
             writer.flush();
             writer.close();
        } catch (IOException ex) {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger(Observacion_Documentos_Gestion_Mof_Guardar.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;//mapping.findForward(SUCCESS);
    }
}