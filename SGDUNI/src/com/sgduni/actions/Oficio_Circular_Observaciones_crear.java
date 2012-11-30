package com.sgduni.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Programmer : Marco A. Estrella Cardenas
 */
public class Oficio_Circular_Observaciones_crear extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "agregarcomentario";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    
    {
        int idOficio = Integer.parseInt(request.getParameter("idOficio").toString());

        request.setAttribute("idOficio", idOficio);
        return mapping.findForward(SUCCESS);
    }
}
