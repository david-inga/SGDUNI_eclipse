package com.sgduni.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * @author JMarcos
 */
public class ROF_continuar_editando extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {
         String inpag = ( request.getParameter("inpag")!= null ) ? request.getParameter("inpag") : "0";
         System.out.println("inpag " +inpag);

         

        
        return mapping.findForward(SUCCESS);
    }
}
