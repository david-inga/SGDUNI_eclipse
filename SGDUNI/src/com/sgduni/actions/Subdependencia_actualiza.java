/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_subdependencia_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_subdependencia;
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
public class Subdependencia_actualiza extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String codigo = request.getParameter("codigo");
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_subdependencia_DAO dao = new orgen_ta_subdependencia_DAO(dataSource);
        orgen_ta_subdependencia obj = dao.getSubDependencia(codigo);
        request.setAttribute("subdependencia",obj);

        orgen_ta_dependencia_DAO daoDep = new orgen_ta_dependencia_DAO(dataSource);
        ArrayList<orgen_ta_dependencia> dependencias = daoDep.getDependenciasActivas();
        request.setAttribute("dependencias",dependencias);
        return mapping.findForward("exitoso");
    }
}
