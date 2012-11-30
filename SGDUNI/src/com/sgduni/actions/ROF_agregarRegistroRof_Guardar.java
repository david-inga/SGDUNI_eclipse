/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_rof_DAO;
import com.sgduni.forms.orpro_ta_registro_rof;
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
 * @author pc
 */
public class ROF_agregarRegistroRof_Guardar extends org.apache.struts.action.Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession sesion = request.getSession();
        String idVersion = sesion.getAttribute("xidVersionROF").toString();

        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        //variables request
        String in_codigo_rof = request.getParameter("in_codigo_rof");
        String in_codigo_organo = request.getParameter("in_codigo_organo").toString();
        String vc_nombre_unidad = request.getParameter("vc_nombre_unidad").toString();
        String vc_descripcion_unidad = request.getParameter("vc_descripcion_unidad");

        DataSource dataSource = getDataSource( request , "DSconnection");
        orpro_ta_rof_DAO daoRof = new orpro_ta_rof_DAO(dataSource);

        orpro_ta_registro_rof obj = new orpro_ta_registro_rof();

        obj.setIn_codigo_rof(Integer.parseInt(in_codigo_rof) );
        obj.setIn_codigo_version(Integer.parseInt(idVersion));
        obj.setIn_codigo_organo(Integer.parseInt(in_codigo_organo));
        obj.setVc_descripcion_unidad(vc_descripcion_unidad);
        obj.setVc_nombre_unidad(vc_nombre_unidad);

        boolean estado = daoRof.guardarRegistroROF(obj);
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
            Logger.getLogger(ROF_insertarFuncionesGenerales_Guardar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
