/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_det_cap_DAO;
import com.sgduni.forms.orpro_det_cap;

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
public class CapDetalle_insertar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {
        System.out.println("action guardar detalle");
        HttpSession sesion = request.getSession();
        String nombre = sesion.getAttribute("xnomus").toString();
        String id =sesion.getAttribute("xid").toString();
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_det_cap_DAO daoCap = new orpro_det_cap_DAO(dataSource);
        orpro_det_cap objForm = (orpro_det_cap)form;
        PrintWriter writer;
        try
        {
           boolean estado = daoCap.guardarDetalleCAP(objForm, nombre, id);
           writer =response.getWriter();
            if(estado)
            {
               writer.print("<script>parent.fnl_resul_proc_detall_cap(1)</script>");
               response.sendRedirect("listarDetalleCap.uni?CodCap="+objForm.getIn_codigo_cap());
            }
            else
            {
                writer.print("<script>parent.fnl_resul_proc_detall_cap(0)</script>");
            }
             writer.flush();
             writer.close();
        }
        catch (IOException ex)
        {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger(CapDetalle_insertar.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("idCap", objForm.getCh_codigo_cap());

        return mapping.findForward("registrarDetCap");
    }
}
