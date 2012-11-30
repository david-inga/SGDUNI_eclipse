/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_rof_DAO;
import com.sgduni.forms.orpro_ta_rof_unidad_area;
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
 * @author pc
 */
public class ROF_agregarAreaUnidad_Listar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irListaArea";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        Integer idRegistro = Integer.parseInt(request.getParameter("idRegistro"));
        System.out.println("id reg = "+idRegistro);
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_rof_DAO daoRof = new orpro_ta_rof_DAO(dataSource);
        ArrayList<orpro_ta_rof_unidad_area> ListaAreas = daoRof.getListaAreasSegunUnidadRof(idRegistro);

        //System.out.println("area 1 "+ListaAreas.get(0).getVc_descripcion_area());
        request.setAttribute("ListaAreasROF", ListaAreas);
        return mapping.findForward(SUCCESS);
    }
}
