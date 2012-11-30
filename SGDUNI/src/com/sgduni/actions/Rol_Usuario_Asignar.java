package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_rol_DAO;
import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.forms.orgen_ta_rol;
import com.sgduni.forms.orgen_ta_usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/*
 * @author marco
 */
public class Rol_Usuario_Asignar extends org.apache.struts.action.Action
{
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_usuario_DAO daoUsuario = new orgen_ta_usuario_DAO(dataSource);

        orgen_ta_rol_DAO daoRol = new orgen_ta_rol_DAO(dataSource);

        String cod_usuio=request.getParameter("coduser");

        ArrayList<orgen_ta_usuario> listaUsuario = daoUsuario.getNombresUsuario();
        
        if(cod_usuio!=null)
        {
          ArrayList<orgen_ta_rol> listaRolesUsuario = daoRol.getNombreRolesUsuario(cod_usuio);
          request.setAttribute("roles",listaRolesUsuario);
        }
        request.setAttribute("coduser",cod_usuio);
        request.setAttribute("nombreusuarios",listaUsuario);
        return mapping.findForward("asignar");
    }
}
