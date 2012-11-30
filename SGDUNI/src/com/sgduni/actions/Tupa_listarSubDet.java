/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_proc_tupa_DAO;
import com.sgduni.forms.orpro_ta_proc_tupa;
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
public class Tupa_listarSubDet extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String codDetTupa = request.getParameter("idTupaDet");
        System.out.println("listar - codDetTupa = "+codDetTupa);
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_proc_tupa_DAO dao = new orpro_ta_proc_tupa_DAO(dataSource);
        ArrayList<orpro_ta_proc_tupa>  subdetalles = dao.getSubDetallesTupaSegunDetalle(codDetTupa.trim());
        // System.out.println( "NomAutoridad :"+subdetalles.get(1).getNombre_autoridad() );
        request.setAttribute("subdetalles",subdetalles);
        return mapping.findForward("listado");
    }
}
