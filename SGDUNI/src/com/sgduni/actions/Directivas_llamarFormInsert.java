/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_estado_DAO;
import com.sgduni.dao.orpro_ta_directivas_DAO;
import com.sgduni.forms.orgen_ta_estado;
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
public class Directivas_llamarFormInsert extends org.apache.struts.action.Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_estado_DAO daoEstado = new  orgen_ta_estado_DAO(dataSource);
        ArrayList<orgen_ta_estado> estados = daoEstado.getEstados();
        request.setAttribute("estados",estados);

        orpro_ta_directivas_DAO daoDir = new orpro_ta_directivas_DAO(dataSource);
        String nuevo_codigo = daoDir.getCodigoGenerado();
        request.setAttribute("nuevo_codigo", nuevo_codigo);
        System.out.println("el id generado es : "+nuevo_codigo);
        
        return  mapping.findForward("exitoso");
    }
}
