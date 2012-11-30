package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_cargo_usuario_DAO;
import com.sgduni.forms.orgen_ta_cargo_usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * @author marco
 */
public class CargoEstructural_actualizar extends org.apache.struts.action.Action {
    
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


        String codigo = request.getParameter("codigo");
        orgen_ta_cargo_usuario_DAO daoCargoEst = new orgen_ta_cargo_usuario_DAO(dataSource);
        orgen_ta_cargo_usuario objCargoUsu = daoCargoEst.getCargoUsuarioSegunCodigo(codigo);

        request.setAttribute("objCargoUsu",objCargoUsu);
        return mapping.findForward("exitoso");
    }
}
