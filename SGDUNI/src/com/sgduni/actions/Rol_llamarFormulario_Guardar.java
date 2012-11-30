/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_rof_DAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author JMarcos
 */
public class Rol_llamarFormulario_Guardar extends org.apache.struts.action.Action {
    
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String success = "irFormularioGuardarROF";


        DataSource dataSource = getDataSource( request , "DSconnection");


       orpro_ta_rof_DAO daoRof = new orpro_ta_rof_DAO(dataSource);
       String nuevo_codigo_rof = daoRof.getCodigoGenerado();


         request.setAttribute("nuevo_codigo_rof", nuevo_codigo_rof);
     
        return mapping.findForward(success);
    }
}
