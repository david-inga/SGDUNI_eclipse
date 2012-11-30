/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_det_proc_direc_DAO;
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
public class Directivas_insertarObjetivo extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {
        String codDir = request.getParameter("codDir");
        //String idDir = request.getParameter("idDir");

        System.out.println("codigo = "+codDir);
        //System.out.println("id = "+idDir);

         DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_directivas_DAO dao = new orpro_ta_directivas_DAO(dataSource);
        String idDir = dao.getIdGeneradosegunCodigo(codDir.trim());

        System.out.println("id = "+idDir);
        
        request.setAttribute("codDir", codDir);
        request.setAttribute("idDir", idDir);
        
        return mapping.findForward("registrarObjetivoDirectiva");
    }
}
