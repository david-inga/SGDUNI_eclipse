package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_subdependencia_DAO;
import com.sgduni.dao.orgen_ta_unidad_DAO;
import com.sgduni.forms.orgen_ta_subdependencia;
import com.sgduni.forms.orgen_ta_unidad;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/*
 * @author marco
 */
public class Unidad_Actualiza extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_subdependencia_DAO dao = new orgen_ta_subdependencia_DAO(dataSource);
        ArrayList<orgen_ta_subdependencia> subdependencias = dao.getSubDependenciasActivas();
        request.setAttribute("subdependencias",subdependencias);

        String codigo = request.getParameter("codigo");
        orgen_ta_unidad_DAO daoU = new orgen_ta_unidad_DAO(dataSource);
        orgen_ta_unidad obj = daoU.getUnidad(codigo);
        request.setAttribute("unidad",obj);

        return mapping.findForward("modunidad");
    }
}
