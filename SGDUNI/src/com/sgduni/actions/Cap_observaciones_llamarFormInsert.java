/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

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
public class Cap_observaciones_llamarFormInsert extends org.apache.struts.action.Action
{
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String idCap = request.getParameter("idCap").toString().trim();
        System.out.println("idCap = "+idCap);

        DataSource dataSource = getDataSource( request , "DSconnection");
        //orpro_ta_cap_DAO dao = new orpro_ta_cap_DAO(dataSource);

        request.setAttribute("idCap", idCap);
        return mapping.findForward("observaciones");
    }
}
