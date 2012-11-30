/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_detalle_proc_req_DAO;
import com.sgduni.dao.orpro_ta_tupa_DAO;
import com.sgduni.forms.orpro_detalle_proc_req;
import com.sgduni.forms.orpro_ta_tupa;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Tupa_insertarDetalle extends org.apache.struts.action.Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession sesion = request.getSession();
        String nombre = sesion.getAttribute("xnomus").toString();
        String id = sesion.getAttribute("xid").toString();

        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        //variables
        String cod_tupa=request.getParameter("codTupa");
        String cod_pro=request.getParameter("codProc");
        String cod_req=request.getParameter("codReq");

        DataSource dataSource = getDataSource( request , "DSconnection");
        orpro_detalle_proc_req_DAO dao = new orpro_detalle_proc_req_DAO(dataSource);
        orpro_detalle_proc_req objForm = new orpro_detalle_proc_req();
        objForm.setIn_codigo_tupa(Integer.parseInt(cod_tupa) );
        objForm.setIn_codigo_procedimiento(Integer.parseInt(cod_pro) );
        objForm.setIn_codigo_req(Integer.parseInt(cod_req) );

        boolean estado = dao.guardarDetalleProcReq(objForm,nombre,id);
        PrintWriter writer;
        try {
           writer =response.getWriter();
           if(estado)
               writer.print("1");
           else
               writer.print("0");
             writer.flush();
             writer.close();
        } catch (IOException ex)
        {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger(Tupa_insertarDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
