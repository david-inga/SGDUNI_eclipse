/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_detalle_base_legal_DAO;
import com.sgduni.forms.orpro_detalle_base_legal;
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
public class Directivas_listarBaseLegal extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String codDirec = request.getParameter("cod_dirc");
        System.out.println("codid = "+codDirec);
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_detalle_base_legal_DAO dao = new orpro_detalle_base_legal_DAO(dataSource);
        ArrayList<orpro_detalle_base_legal> baselegal = dao.getBasesLegalesDir(codDirec);
        // System.out.println( "DESCRIPCION :"+objetivos.get(1).getVc_descripcion() );
        request.setAttribute("baselegal",baselegal);
        return mapping.findForward("listado");
    }
}
