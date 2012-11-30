/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;


import com.sgduni.dao.orgen_ta_cargo_usuario_DAO;
import com.sgduni.forms.orgen_ta_cargo_usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * @author marco
 */
public class CargoEstructural_insert extends org.apache.struts.action.Action {
    
    private final static String SUCCESS = "success";
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {
        HttpSession sesion = request.getSession();
        String nombre = sesion.getAttribute("xnomus").toString();
        String id =sesion.getAttribute("xid").toString();
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_cargo_usuario_DAO dao = new orgen_ta_cargo_usuario_DAO(dataSource);
        orgen_ta_cargo_usuario objForm = (orgen_ta_cargo_usuario)form;
        boolean estado = dao.guardarCargoEstructural(objForm, nombre, id);

        if(estado == true)
        {
            objForm.setMensaje("Cargo registrado correctarmente");
        }
        else
        {
            objForm.setMensaje("Error! ocurrio un error al registrar el cargo");
        }
        return mapping.findForward("registrarCargoEstruct");

        //
    }
}
