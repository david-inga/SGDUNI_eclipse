package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_estado_DAO;
import com.sgduni.dao.orpro_ta_directivas_DAO;
import com.sgduni.forms.orgen_ta_estado;
import com.sgduni.forms.orpro_ta_directivas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class Directivas_insertar extends org.apache.struts.action.Action {
    
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
        orpro_ta_directivas_DAO dao = new orpro_ta_directivas_DAO(dataSource);

        orpro_ta_directivas objForm = new orpro_ta_directivas();
        objForm.setCh_codigo_directiva(request.getParameter("ch_codigo_directiva").toString().trim());
        objForm.setDt_fecha(request.getParameter("dt_fecha").toString().trim());
        objForm.setVc_alcance(request.getParameter("vc_alcance").toString().trim());
        objForm.setVc_responsabilidad(request.getParameter("vc_responsabilidad").toString().trim());
        objForm.setCh_estado(request.getParameter("ch_estado").toString().trim());

        PrintWriter writer;
        try
        {
           writer =response.getWriter();
            if( dao.guardarDirectiva(objForm,nombre,id ) )
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
            Logger.getLogger(Directivas_insertar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapping.findForward(SUCCESS);
    }
}
