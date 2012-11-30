/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.dao.orgen_ta_usuario_cargo_DAO;
import com.sgduni.forms.orgen_ta_usuario;
import com.sgduni.forms.orgen_ta_usuario_cargo;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author JMarcos
 */
public class Usuario_Cargo_llamarForm extends org.apache.struts.action.Action
{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception 
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_usuario_DAO daoUsuario = new orgen_ta_usuario_DAO(dataSource);
        orgen_ta_usuario_cargo_DAO daoCargo = new orgen_ta_usuario_cargo_DAO(dataSource);

        String cod_usuario = request.getParameter("coduser");

        ArrayList<orgen_ta_usuario> listaUsuario = daoUsuario.getNombresUsuario();
        if(cod_usuario != null)
        {
          ArrayList<orgen_ta_usuario_cargo> listaCargosUsuario = daoCargo.getNombreCargosDelUsuario(cod_usuario);
          request.setAttribute("cargos",listaCargosUsuario);
        }
        request.setAttribute("coduser",cod_usuario);
        request.setAttribute("nombreusuarios",listaUsuario);

        return mapping.findForward("irAlFormulario");
    }
}
