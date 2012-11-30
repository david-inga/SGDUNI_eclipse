/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_detalle_normas_gen_DAO;
import com.sgduni.forms.orpro_detalle_normas_gen;
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
public class Directiva_GuardarNormaGen extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
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
        String cod_dirc=request.getParameter("cod_dirc");
        String descrip=request.getParameter("descrip");

        DataSource dataSource = getDataSource( request , "DSconnection");
        orpro_detalle_normas_gen_DAO dao = new orpro_detalle_normas_gen_DAO(dataSource);

        orpro_detalle_normas_gen objForm = new orpro_detalle_normas_gen();
        objForm.setIn_codigo_directiva(Integer.parseInt(cod_dirc) );
        objForm.setVc_descripcion(descrip);

        boolean estado = dao.agregarNormaADirectiva(objForm,nombre,id);
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
            Logger.getLogger(Directivas_GuardarObjetivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
