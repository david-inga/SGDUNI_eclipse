/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_det_proc_direc_DAO;
import com.sgduni.forms.orpro_det_proc_direc;
import java.util.ArrayList;
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
public class Directivas_ListarProcedimientos extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String codDirec = request.getParameter("cod_dirc");

        System.out.println("codid = "+codDirec);
        
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_det_proc_direc_DAO dao = new orpro_det_proc_direc_DAO(dataSource);
        ArrayList<orpro_det_proc_direc> proced = dao.getProcedimientoDir(codDirec);
        // System.out.println( "DESCRIPCION :"+objetivos.get(1).getVc_descripcion() );
        request.setAttribute("procedimientos",proced);
        return mapping.findForward("listado");
    }
}
