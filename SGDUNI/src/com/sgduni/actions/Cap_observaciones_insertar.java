package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_observaciones_cap_DAO;
import com.sgduni.dao.orpro_ta_observaciones_mapro_DAO;
import com.sgduni.forms.orpro_ta_observaciones_cap;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author marco
 */
public class Cap_observaciones_insertar extends org.apache.struts.action.Action {
    private final static String LISTAR = "listar";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession session=request.getSession(true);
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        //variable
        //String idEstado=request.getParameter("in_codigo_estado");
        int idUser=Integer.parseInt(session.getAttribute("xid").toString());
        int in_codigo_cap=Integer.parseInt(request.getParameter("in_codigo_cap"));
        String vc_observacion=request.getParameter("vc_observacion");
        int idEstado=0;
        String idROL=(String)session.getAttribute("xrol");
        String ckaprobar=(request.getParameter("ckaprobar")!=null && request.getParameter("ckaprobar").equals("1"))?request.getParameter("ckaprobar"):"0";


        Calendar Cal= Calendar.getInstance();
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_observaciones_cap_DAO daoMAPRO=new orpro_ta_observaciones_cap_DAO(dataSource);
        orpro_ta_observaciones_cap theForm =new orpro_ta_observaciones_cap();

        if(idROL.trim().toUpperCase().equals("ROL01"))
        {
            idEstado=11;//revision OCDO
            if(ckaprobar.equals("1"))
                 idEstado=13;//aprobado OCO
        }
        else if(idROL.trim().toUpperCase().equals("ROL02"))
        {
            idEstado=12;//revision usuario
        }
        else if(idROL.trim().toUpperCase().equals("ROL03"))
        {
            idEstado=14;//se va para revisión del usuario
            if(ckaprobar.equals("1"))
                 idEstado=15;//APROBACION ASESORIA LEGAL
        }

        PrintWriter writer;
        try
        {
            theForm.setIn_codigo_cap(in_codigo_cap);
            theForm.setVc_observacion(vc_observacion);
           writer =response.getWriter();
            if(daoMAPRO.guardarObservacionCap(theForm, idUser,idEstado))
            {
               writer.print("1");
            } else
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
            Logger.getLogger(Cap_observaciones_insertar.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;//mapping.findForward(SUCCESS);
    }
}
