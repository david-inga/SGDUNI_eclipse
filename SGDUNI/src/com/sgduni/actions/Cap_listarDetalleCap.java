/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_det_cap_DAO;
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
public class Cap_listarDetalleCap extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {

        DataSource dataSource = getDataSource(request, "DSconnection");
        
        String codCap = request.getParameter("CodCap");
        System.out.println("Detalle : Cod Cap: "+codCap);

        orpro_det_cap_DAO daoDetCap = new orpro_det_cap_DAO(dataSource);
        ArrayList<orpro_ta_listar_detalle_cap> det = daoDetCap.getDetallesSegunCap(Integer.parseInt(codCap));
        System.out.println("EXISTE DETALLE LISTA: "+det.size());

        request.setAttribute("detalles",det);
        
        return mapping.findForward("listarResultadoDetalle");
    }
}
