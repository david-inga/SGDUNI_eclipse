package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_organo_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_organo;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author marco
 */
public class Dependencia_modificar extends org.apache.struts.action.Action {
    
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
        int id = Integer.parseInt( sesion.getAttribute("xid").toString() );
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_dependencia_DAO dao = new orgen_ta_dependencia_DAO(dataSource);
        orgen_ta_dependencia objForm = (orgen_ta_dependencia)form;
        boolean estado = dao.modificarDependencia(objForm,nombre,id);
        if(estado == true)
        {
            objForm.setMensaje("La Dependencia se Modifico Correctamente");
        }
        else
        {
            objForm.setMensaje("Error al modificar la dependencia");
        }
        orgen_ta_organo_DAO daoOrg = new orgen_ta_organo_DAO(dataSource);
        ArrayList<orgen_ta_organo> organos = daoOrg.getOrganosActivos();
        request.setAttribute("organos",organos);
        
        return mapping.findForward("modificarDep");
    }
}
