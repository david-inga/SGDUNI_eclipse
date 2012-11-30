/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions.ajax;

import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.forms.orgen_ta_usuario;
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


public class CambiarEstadoUsuario extends org.apache.struts.action.Action {
    private final static String SUCCESS = "success";
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        
        int codigo = Integer.parseInt(request.getParameter("xcod"));
        String estado = request.getParameter("estado");

        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_usuario_DAO dao = new orgen_ta_usuario_DAO(dataSource);
        orgen_ta_usuario obj = new orgen_ta_usuario();
        obj.setIn_codigo_usuario(codigo);
        obj.setVc_estado(estado);
        PrintWriter writer;
        try {
           writer =response.getWriter();
            if(dao.eliminarUsuario(obj))
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
            Logger.getLogger(CambiarEstadoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }


        return mapping.findForward(SUCCESS);
    }
}
