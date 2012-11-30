package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.forms.orgen_ta_usuario;
import com.sgduni.forms.orgen_ta_usuario_lista;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Programmer : Marco A. Estrella Cardenas
 */
public class Usuario_ver_detalles extends org.apache.struts.action.Action
{
    /* forward name="success" path="" */
    private final static String SUCCESS = "verdetalles";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        int codigo = Integer.parseInt( request.getParameter("idUsuario") );
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_usuario_DAO dao = new orgen_ta_usuario_DAO(dataSource);
        orgen_ta_usuario obj = dao.getUsuario2(codigo);
        String dependencia = dao.getDEPENDENCIASegunIDUsuario(codigo);
        String rol = dao.getROLSegunIDUsuario(codigo);
        String cargo = dao.getCargoSegunIDUsuario(codigo);
        cargo = ( cargo != null  ) ? cargo : "No Asignado";
        dependencia = ( dependencia != null ) ? dependencia : "No Asignado";
        rol = ( rol != null ) ? rol : "No Asignado";
        request.setAttribute("usuario",obj);
        request.setAttribute("cargo",cargo);
        request.setAttribute("rol",rol);
        request.setAttribute("dependencia",dependencia);
        return mapping.findForward(SUCCESS);
    }
}
