package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_cargo_usuario_DAO;
import com.sgduni.forms.orgen_ta_cargo_usuario;
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
public class CapDetalleInsertar_llamarForm extends org.apache.struts.action.Action
{
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_cargo_usuario_DAO dao = new orgen_ta_cargo_usuario_DAO(dataSource);
        ArrayList<orgen_ta_cargo_usuario> cargos = dao.getCargosEstructuralesActivos();
        request.setAttribute("cargosEstructurales",cargos);

        String idCap = request.getParameter("idCap");
        request.setAttribute("idCap", idCap);

        String codCap = request.getParameter("codCap");
        request.setAttribute("codCap", codCap);
        
        return mapping.findForward("registrarDetalleCAP");
    }
}
