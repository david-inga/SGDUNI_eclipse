/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions.ajax;

import com.sgduni.dao.orgen_ta_usuario_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Programmer : Marco A. Estrella Cardenas
 */
public class ComprobarExisteCorreo extends org.apache.struts.action.Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        String usuariotext = request.getParameter("correox");
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_usuario_DAO dao = new orgen_ta_usuario_DAO(dataSource);

        try
        {
            PrintWriter writer = response.getWriter();
            if(dao.validarSiCorreoExiste(usuariotext))
            {
                writer.print("1");
            }else{
                writer.print("0");
            }
             writer.flush();
             writer.close();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(ComprobarExisteCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
