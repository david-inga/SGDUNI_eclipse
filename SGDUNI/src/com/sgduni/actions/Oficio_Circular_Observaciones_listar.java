/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orpro_ta_observacion_oficio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Programmer : Marco A. Estrella Cardenas
 */
public class Oficio_Circular_Observaciones_listar extends org.apache.struts.action.Action
{
    private final static String SUCCESS = "listarObservaciones";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        int idOficio = Integer.parseInt( request.getParameter("idOficio").toString() );
        int validarNuevo = Integer.parseInt( request.getParameter("validarNuevo").toString() );
        
        DataSource dataSource = getDataSource(request, "DSconnection");
        
        orpro_oficio_circular_DAO daoOficio = new orpro_oficio_circular_DAO(dataSource);

        ArrayList<orpro_ta_observacion_oficio> listaObservaciones = daoOficio.getListaObservacionesDeOficio(idOficio);

        request.setAttribute("listaObservaciones", listaObservaciones);
        request.setAttribute("codOficio", idOficio);
        request.setAttribute("validarNuevo", validarNuevo);
        return mapping.findForward(SUCCESS);
    }
}
