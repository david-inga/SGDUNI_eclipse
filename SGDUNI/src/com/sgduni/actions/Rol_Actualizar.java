package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_rol_DAO;
import com.sgduni.forms.orgen_ta_rol;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * @programador Marco Estrella Cardenas
 */
public class Rol_Actualizar extends org.apache.struts.action.Action
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
        orgen_ta_rol_DAO dao = new orgen_ta_rol_DAO(dataSource);
        orgen_ta_rol obj = dao.getRol(codigo);
        request.setAttribute("rol",obj);
        return mapping.findForward("exitoso");
    }
}
