package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_clasi_cargo_DAO;
import com.sgduni.forms.orgen_ta_clasi_cargo;
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
public class ClasiCargo_actualizar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String codigo = request.getParameter("codigo");
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_clasi_cargo_DAO dao = new orgen_ta_clasi_cargo_DAO(dataSource);
        orgen_ta_clasi_cargo obj = dao.getClasiCargo(codigo);
        request.setAttribute("clasicargo",obj);
        return mapping.findForward("exitoso");
    }
}
