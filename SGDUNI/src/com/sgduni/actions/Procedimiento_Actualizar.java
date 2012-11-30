package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_procedimiento_DAO;
import com.sgduni.forms.orgen_ta_procedimiento;
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
public class Procedimiento_Actualizar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {
        String codigo = request.getParameter("codigo");
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_procedimiento_DAO dao = new orgen_ta_procedimiento_DAO(dataSource);
        orgen_ta_procedimiento obj = dao.getProcedimiento(codigo);
        request.setAttribute("procedimiento",obj);
        return mapping.findForward("exitoso");
    }
}
