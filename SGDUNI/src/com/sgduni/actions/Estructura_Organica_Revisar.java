/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_estructura_organica_DAO;
import com.sgduni.forms.orpro_ta_observacion_estruc;
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
 * @author Administrador
 */
public class Estructura_Organica_Revisar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irFormularioRevisarOrg";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        int idEstructura = Integer.parseInt( request.getParameter("in_codigo_Estructura").toString() );

        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_estructura_organica_DAO daoEO = new orpro_ta_estructura_organica_DAO(dataSource);
        ArrayList<orpro_ta_observacion_estruc> listaObservaciones = daoEO.getListaObservacionesEO(idEstructura);

        request.setAttribute("listaObservaciones", listaObservaciones);
        request.setAttribute("in_codigo_estructura", String.valueOf(idEstructura) );
        request.setAttribute("ch_codigo_estructura",request.getParameter("ch_codigo_Estructura").toString());
        request.setAttribute("organigrama",request.getParameter("organigrama").toString());
        return mapping.findForward(SUCCESS);
    }
}
