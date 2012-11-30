package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_requisitos_DAO;
import com.sgduni.forms.orgen_ta_requisitos;
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
public class Requisitos_insertar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession sesion = request.getSession();
        String nombre = sesion.getAttribute("xnomus").toString();
        String id = sesion.getAttribute("xid").toString();
        DataSource dataSource = getDataSource( request , "DSconnection");
        orgen_ta_requisitos_DAO dao = new orgen_ta_requisitos_DAO(dataSource);
        orgen_ta_requisitos objForm = (orgen_ta_requisitos)form;
        boolean estado = dao.guardarRequisito(objForm,nombre,id);

        if(estado == true)
        {
            objForm.setMensaje("Requisito guardado correctamente");
        }
        else
        {
            objForm.setMensaje("Error! ocurrio un error al registrar el requisito");
        }
        return mapping.findForward("registrarreq");
    }
}