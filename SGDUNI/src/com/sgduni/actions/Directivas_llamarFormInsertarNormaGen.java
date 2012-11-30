/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_directivas_DAO;
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
public class Directivas_llamarFormInsertarNormaGen extends org.apache.struts.action.Action {
    
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
        System.out.println(" NORMA GENERAL ");

        String codDir = request.getParameter("codDir");
        System.out.println("codigo = "+codDir);

         DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_directivas_DAO dao = new orpro_ta_directivas_DAO(dataSource);
        String idDir = dao.getIdGeneradosegunCodigo(codDir.trim());

        System.out.println("id = "+idDir);

        request.setAttribute("codDir", codDir);
        request.setAttribute("idDir", idDir);
        
        return mapping.findForward("registrarNormaGen");
    }
}
