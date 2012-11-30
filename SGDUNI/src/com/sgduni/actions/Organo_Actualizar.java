package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_organo_DAO;
import com.sgduni.forms.orgen_ta_organo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author marco
 */
public class Organo_Actualizar extends org.apache.struts.action.Action
{
    private final static String SUCCESS = "success";
    
    @Override
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {
        String codigo = request.getParameter("codigo");
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_organo_DAO dao = new orgen_ta_organo_DAO(dataSource);
        orgen_ta_organo obj = dao.getOrgano(codigo);
        request.setAttribute("organo",obj);
        return mapping.findForward("exitoso");
    }
}
