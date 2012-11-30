package com.sgduni.actions.ajax;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
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
public class CambiarEstadoDependencia extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        String codigo = request.getParameter("xcod");
        String estado = request.getParameter("estado");
        String codUsu = request.getParameter("codusu");

        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_dependencia_DAO dao = new orgen_ta_dependencia_DAO(dataSource);
        orgen_ta_dependencia obj = new orgen_ta_dependencia();
        obj.setIn_codigo_dependencia( Integer.parseInt( codigo.trim() ));
        obj.setCh_estado(estado.trim());
        PrintWriter writer;
        try {
           writer =response.getWriter();
            if(dao.cambiarEstado(obj,codUsu))
            {
               writer.print("1");
            } else
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
            Logger.getLogger(CambiarEstadoDependencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapping.findForward(SUCCESS);
    }
}
