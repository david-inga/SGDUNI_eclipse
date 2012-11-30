/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_organo_DAO;
import com.sgduni.forms.orgen_ta_organo;
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
 * @author pc
 */
public class ROF_llamarFormularioAgregarRegistro extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irFormRegistro";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String idRof = request.getParameter("idRof").toString();
        System.out.println("ROF_agregarRegistro ");

        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_organo_DAO dao = new orgen_ta_organo_DAO(dataSource);
        ArrayList<orgen_ta_organo> organos = dao.getOrganosActivos();
        request.setAttribute("organos",organos);

        request.setAttribute("idRof", idRof);

        return mapping.findForward(SUCCESS);
    }
}
