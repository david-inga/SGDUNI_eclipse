package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_procedimiento_DAO;
import com.sgduni.forms.orgen_ta_procedimiento;
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
public class Procedimiento_listar extends org.apache.struts.action.Action
{
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_procedimiento_DAO dao = new orgen_ta_procedimiento_DAO(dataSource);
        ArrayList<orgen_ta_procedimiento> listaProc = dao.getProcedimientos();
        request.setAttribute("procedimientos",listaProc);
        return mapping.findForward("listado");
    }
}
