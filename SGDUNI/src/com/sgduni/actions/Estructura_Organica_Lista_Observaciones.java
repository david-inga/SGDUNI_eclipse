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
 * @author JMarcos
 */
public class Estructura_Organica_Lista_Observaciones extends org.apache.struts.action.Action
{ 
    private final static String SUCCESS = "irVerObservacionesEO";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        int idEstructura = Integer.parseInt( request.getParameter("idEstructura").toString() );

        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_estructura_organica_DAO daoEO = new orpro_ta_estructura_organica_DAO(dataSource);
        ArrayList<orpro_ta_observacion_estruc> listaObservaciones = daoEO.getListaObservacionesEO(idEstructura);

        request.setAttribute("listaObservaciones", listaObservaciones);
        request.setAttribute("codEstructura", idEstructura);
        return mapping.findForward(SUCCESS);
    }
}
