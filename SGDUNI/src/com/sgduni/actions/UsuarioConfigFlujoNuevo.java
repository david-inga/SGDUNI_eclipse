/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.forms.orgen_ta_usuario;
import com.sgduni.forms.orgen_ta_configura_flujo;
import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.dao.orgen_ta_configura_flujo_DAO;

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
 * @author marco
 */
public class UsuarioConfigFlujoNuevo extends org.apache.struts.action.Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        //dao
        orgen_ta_usuario_DAO daoUsu=new orgen_ta_usuario_DAO(dataSource);
        orgen_ta_configura_flujo_DAO daoConf=new orgen_ta_configura_flujo_DAO(dataSource);

        //
        String codUsu= ( request.getParameter("coduser") != null && request.getParameter("coduser") != "0") ? request.getParameter("coduser") : "0";

        //Lista ARRAY
        ArrayList<orgen_ta_usuario_lista> usuarios=daoUsu.getUsuariosActivos();
        ArrayList<orgen_ta_configura_flujo> configuracion_flujo=daoConf.getListaUsuarioConfigFlujo(Integer.parseInt(codUsu));
        
        request.setAttribute("codUsu", codUsu);
        request.setAttribute("usuarios", usuarios);
        request.setAttribute("configuracion_flujo", configuracion_flujo);
        
        return mapping.findForward("nuevo");
    }
}
