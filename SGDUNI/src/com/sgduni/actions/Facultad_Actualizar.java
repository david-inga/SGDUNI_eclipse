package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orgen_ta_organo_DAO;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orgen_ta_organo;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * @author marco
 */
public class Facultad_Actualizar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String codigo = request.getParameter("codigo");
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_facultad_DAO dao = new orgen_ta_facultad_DAO(dataSource);
        orgen_ta_facultad obj = dao.getFacultad(codigo);
        request.setAttribute("facultad",obj);
        orgen_ta_organo_DAO daoOrg = new orgen_ta_organo_DAO(dataSource);
        ArrayList<orgen_ta_organo> organos = daoOrg.getOrganosActivos();
        request.setAttribute("organos",organos);
        return mapping.findForward("exitoso");
    }
}
