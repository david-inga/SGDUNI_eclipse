/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_clasi_cargo_DAO;
import com.sgduni.forms.orgen_ta_clasi_cargo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author marco
 */
public class ClasiCargo_modificar extends org.apache.struts.action.Action {
    
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
        HttpSession sesion = request.getSession();
        String nombre = sesion.getAttribute("xnomus").toString();
        String id = sesion.getAttribute("xid").toString();
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_clasi_cargo_DAO dao = new orgen_ta_clasi_cargo_DAO(dataSource);
        orgen_ta_clasi_cargo objForm = (orgen_ta_clasi_cargo)form;
        boolean estado =  dao.modificarClasiCargo(objForm,nombre,id);
        if(estado == true)
        {
            objForm.setMensaje("Clasificación Modificado Correctamente");
        }
        else
        {
            objForm.setMensaje("Error! ocurrio un error al modificar el clasificación");
        }
        return mapping.findForward("modificarClasi");
    }
}
