package com.sgduni.actions;

import com.sgduni.dao.orpro_det_proc_direc_DAO;
import com.sgduni.forms.orpro_det_proc_direc;
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
public class Directivas_insertarProcedimiento extends org.apache.struts.action.Action
{
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
        String idDir  = request.getParameter("idDir");
        String idProc = request.getParameter("idProc");
        
        DataSource dataSource = getDataSource( request , "DSconnection");
        orpro_det_proc_direc_DAO dao = new orpro_det_proc_direc_DAO(dataSource);

        orpro_det_proc_direc objForm = new orpro_det_proc_direc();
        objForm.setIn_codigo_directiva(Integer.parseInt(idDir));
        objForm.setIn_codigo_procedimiento(Integer.parseInt(idProc));

        boolean estado = dao.agregarProcedimientoADirectiva(objForm,nombre,id);
        
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
            Logger.getLogger(Directivas_insertarProcedimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
