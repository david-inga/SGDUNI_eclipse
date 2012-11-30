/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_cap_DAO;
import com.sgduni.forms.orpro_ta_cap;
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
public class Cap_listarResultado extends org.apache.struts.action.Action
{
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
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
           orpro_ta_cap_DAO dao = new orpro_ta_cap_DAO(dataSource);
           ArrayList<orpro_ta_cap> lista = dao.getCapDeFacultades(codFacDep);
           request.setAttribute("cap",lista);
        }
        else if(tipoFacDep.equals("d"))
        {
           DataSource dataSource = getDataSource( request , "DSconnection");
           orpro_ta_cap_DAO dao = new orpro_ta_cap_DAO(dataSource);
           ArrayList<orpro_ta_cap> lista = dao.getCapDeDependnecias(codFacDep);
           request.setAttribute("cap",lista);
        }
        
        return mapping.findForward("listarResultado");
    }
}
