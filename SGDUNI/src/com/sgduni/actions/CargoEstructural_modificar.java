/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_cargo_usuario_DAO;
import com.sgduni.forms.orgen_ta_cargo_usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author marco
 */
public class CargoEstructural_modificar extends org.apache.struts.action.Action {
    
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

        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_cargo_usuario_DAO dao = new orgen_ta_cargo_usuario_DAO(dataSource);

        orgen_ta_cargo_usuario objForm = new orgen_ta_cargo_usuario();
        objForm.setIn_codigo_cargo_estruc(Integer.parseInt( request.getParameter("in_codigo_cargo_estruc").trim() ));
        objForm.setVc_nombre(request.getParameter("vc_nombre").toString()  );
        objForm.setVc_descripcion(request.getParameter("vc_descripcion").toString() );
        PrintWriter writer;
        try
        {
           writer =response.getWriter();
            if( dao.modificarCargo(objForm,nombre,id ) )
            {
               writer.print("1");
            }
            else
            {
                writer.print("0");
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
            Logger.getLogger(CargoEstructural_modificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapping.findForward(SUCCESS);
    }
}
