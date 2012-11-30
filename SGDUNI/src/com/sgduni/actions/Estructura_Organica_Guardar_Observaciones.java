/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_estructura_organica_DAO;
import com.sgduni.forms.orpro_ta_observacion_estruc;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author JMarcos
 */
public class Estructura_Organica_Guardar_Observaciones extends org.apache.struts.action.Action
{
    private final static String SUCCESS = "irGuardarObservacionesEO";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession session=request.getSession(true);
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        //variable
        int idUser=Integer.parseInt(session.getAttribute("xid").toString());
        String nombreUsu = session.getAttribute("xnomus").toString();

        int in_codigo_estructura = Integer.parseInt(request.getParameter("in_codigo_estructura"));

        int idEstado=0;
        String Rol=(String)session.getAttribute("xrol");
        Rol=Rol.trim().toUpperCase();

        String vc_observacion=request.getParameter("vc_observacion");

        String ckaprobar = (request.getParameter("ckaprobar")!=null && request.getParameter("ckaprobar").equals("1"))?request.getParameter("ckaprobar"):"0";

        DataSource dataSource = getDataSource(request, "DSconnection");

        orpro_ta_estructura_organica_DAO daoEO = new orpro_ta_estructura_organica_DAO(dataSource);
        orpro_ta_observacion_estruc theForm =new orpro_ta_observacion_estruc();

        idEstado=1;//Estado Pendiente
        if(Rol.equals("ROL01"))
        {//OCDO
            if(ckaprobar.equals("1"))
                 idEstado=23;//aprobado OCO
            else
                idEstado=24;//rechazado OCO
        }
        else if(Rol.equals("ROL02"))
        {//USUARIO
            idEstado=21;//PENDIENTE
        }

        PrintWriter writer;
        try
        {
            System.out.println("estado :"+idEstado);

            theForm.setIn_codigo_estructura(in_codigo_estructura);
            theForm.setVc_observacion(vc_observacion);
            theForm.setVc_nombre_usuario(nombreUsu);

           writer =response.getWriter();
            if(daoEO.guardarObservacionEO(theForm, idUser, idEstado))
            {
               writer.print("1");
            }
            else
            {
                writer.print("0");
            }
             writer.flush();
             writer.close();
        } catch (IOException ex)
        {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger( Estructura_Organica_Guardar_Observaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;//mapping.findForward(SUCCESS);
    }
}
