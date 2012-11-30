package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.forms.orgen_ta_usuario;
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
public class Usuario_Actualizar extends org.apache.struts.action.Action
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
        int codigo = Integer.parseInt( request.getParameter("codigo") );
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_usuario_DAO dao = new orgen_ta_usuario_DAO(dataSource);
        orgen_ta_usuario obj = dao.getUsuario2(codigo);
        request.setAttribute("usuario",obj);
        return mapping.findForward("exitoso");
    }
}
