/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_tupa_DAO;
import com.sgduni.forms.orpro_ta_proc_tupa;
import com.sgduni.forms.orpro_ta_tupa;
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
 * @author Sistemas
 */
public class Tupa_Exportar extends org.apache.struts.action.Action
{    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        Integer idTup=Integer.parseInt(request.getParameter("idTupa").trim().toString() );

        DataSource dataSource = getDataSource(request, "DSconnection");

        orpro_ta_tupa_DAO daoTupa = new orpro_ta_tupa_DAO(dataSource);
        orpro_ta_tupa Tupa = daoTupa.buscarTupa(idTup);
        ArrayList<orpro_ta_proc_tupa> listaDetalle = daoTupa.listarDetalleSubDetalleTupa(idTup);


        request.setAttribute("Tupa", Tupa);
        request.setAttribute("listaDetalle", listaDetalle);

        return mapping.findForward("exportar");
    }
}
