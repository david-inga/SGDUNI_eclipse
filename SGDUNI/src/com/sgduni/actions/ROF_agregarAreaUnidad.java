/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_rof_DAO;
import com.sgduni.forms.orpro_ta_rof_unidad_area;
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
 * @author pc
 */
public class ROF_agregarAreaUnidad extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        //variables request
        String in_codigo_registro = request.getParameter("in_codigo_registro");
        String vc_nombre_area = request.getParameter("vc_nombre_area").toString();
        String vc_descripcion_area = request.getParameter("vc_descripcion_area").toString();

        DataSource dataSource = getDataSource( request , "DSconnection");
        orpro_ta_rof_DAO daoRof = new orpro_ta_rof_DAO(dataSource);

        orpro_ta_rof_unidad_area obj = new orpro_ta_rof_unidad_area();

        obj.setIn_codigo_registro(Integer.parseInt(in_codigo_registro) );
        obj.setVc_descripcion_area(vc_descripcion_area);
        obj.setVc_nombre_area(vc_nombre_area);

        boolean estado = daoRof.guardarAreadeUnidadROF(obj);
        PrintWriter writer;
        try
        {
           writer =response.getWriter();
           if(estado)
               writer.print("1");
           else
               writer.print("0");
             writer.flush();
             writer.close();
        }
        catch (IOException ex)
        {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger(ROF_agregarAreaUnidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
