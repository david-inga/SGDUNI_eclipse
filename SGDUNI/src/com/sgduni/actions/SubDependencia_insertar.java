package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_subdependencia_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_subdependencia;
import java.util.ArrayList;
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
public class SubDependencia_insertar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
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
        String id = sesion.getAttribute("xid").toString();
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_subdependencia_DAO dao = new orgen_ta_subdependencia_DAO(dataSource);
        orgen_ta_subdependencia objForm = (orgen_ta_subdependencia)form;
        boolean estado = dao.guardarSubDependencia(objForm,nombre,Integer.parseInt(id));
        if(estado == true)
        {
            objForm.setMensaje("Subdependencia guardado correctamente");
        }
        else{
            objForm.setMensaje("Error! ocurrio un error al registrar el subdependencia");
        }
        orgen_ta_dependencia_DAO daoDep = new orgen_ta_dependencia_DAO(dataSource);
        ArrayList<orgen_ta_dependencia> dependencias = daoDep.getDependencias();
        request.setAttribute("dependencias",dependencias);
        return mapping.findForward("registrarsubdep");
    }
}
