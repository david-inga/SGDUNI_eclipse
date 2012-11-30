/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions.ajax;

import com.sgduni.dao.orgen_ta_rol_DAO;
import com.sgduni.forms.orgen_ta_rol;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author marco
 */
public class CambiarEstadoRol extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        String codigo = request.getParameter("xcod");
        String estado = request.getParameter("estado");

        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_rol_DAO dao = new orgen_ta_rol_DAO(dataSource);
        orgen_ta_rol obj = new orgen_ta_rol();
        obj.setCh_codigo_rol(codigo.trim());
        obj.setEstado(estado.trim());
        PrintWriter writer;
        try {
           writer =response.getWriter();
            if(dao.eliminarRol(obj))
            {
               writer.print("1");
            } else
            {
                writer.print("0");
            }
             writer.flush();
             writer.close();
        } catch (IOException ex) {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger(CambiarEstadoRol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapping.findForward(SUCCESS);
    }
}
