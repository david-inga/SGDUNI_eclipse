/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_det_cap_DAO;
import com.sgduni.dao.orpro_ta_cap_DAO;
import com.sgduni.forms.orpro_det_cap;
import com.sgduni.forms.orpro_ta_cap;
import com.sgduni.forms.orpro_ta_listar_detalle_cap;
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
public class Cap_Exportar extends org.apache.struts.action.Action
{
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        Integer idCap = Integer.parseInt(request.getParameter("idCap").trim().toString() );

        DataSource dataSource = getDataSource(request, "DSconnection");

        orpro_ta_cap_DAO daoCap = new orpro_ta_cap_DAO(dataSource);
        orpro_ta_cap cap = daoCap.buscarCap(idCap);
         System.out.println("nombre fac: "+cap.getNombre_DependFac() );

        orpro_det_cap_DAO daoDetCap = new orpro_det_cap_DAO(dataSource);
        ArrayList<orpro_ta_listar_detalle_cap> det = daoDetCap.getDetallesSegunCap(idCap);
        System.out.println("EXISTE DETALLE LISTA: "+det.size());

        request.setAttribute("detalles",det);

        request.setAttribute("Cap", cap);

        return mapping.findForward("exportar");
    }
}
