/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author JMarcos
 */
public class Estructura_Organica_llamarForm_Guardar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irGuardarObservacionesEO";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String idEstructura = request.getParameter("idEstructura").trim();
        //System.out.println("Estructura_Organica_llamarForm_Guardar - codigo - "+idEstructura);
        request.setAttribute("codEstructura", idEstructura);
        return mapping.findForward(SUCCESS);
    }
}
