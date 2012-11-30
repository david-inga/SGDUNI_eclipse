/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_tupa_DAO;
import com.sgduni.forms.orpro_ta_tupa;
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
public class Tupa_resultadoListaDetalles extends org.apache.struts.action.Action {
    
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
        String tipoFacDep = request.getParameter("tipo").trim().toString();
        String codFacDep = request.getParameter("codFacDep").trim().toString();

        System.out.println("tipo = "+tipoFacDep.trim().toString());
        System.out.println("cod = "+codFacDep.trim().toString());

        if(tipoFacDep.equals("f"))
        {
           DataSource dataSource = getDataSource( request , "DSconnection");
           orpro_ta_tupa_DAO dao = new orpro_ta_tupa_DAO(dataSource);
           ArrayList<orpro_ta_tupa> lista = dao.getCapDeFacultades(codFacDep);
           request.setAttribute("tupa",lista);
        }
        else if(tipoFacDep.equals("d"))
        {
           DataSource dataSource = getDataSource( request , "DSconnection");
           orpro_ta_tupa_DAO dao = new orpro_ta_tupa_DAO(dataSource);
           ArrayList<orpro_ta_tupa> lista = dao.getTupaDeDependencias(codFacDep);
           request.setAttribute("tupa",lista);
        }

        return mapping.findForward("listarResultado");
    }
}
