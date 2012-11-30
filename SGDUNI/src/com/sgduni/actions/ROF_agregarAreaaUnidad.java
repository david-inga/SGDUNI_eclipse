/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author pc
 */
public class ROF_agregarAreaaUnidad extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irLisArea";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String idRegistro = request.getParameter("idRegistro").toString();
        String nomUnidad = request.getParameter("nomUnidad").toString();


        request.setAttribute("idRegistro", idRegistro);
        request.setAttribute("nomUnidad", nomUnidad);
        return mapping.findForward(SUCCESS);
    }
}
