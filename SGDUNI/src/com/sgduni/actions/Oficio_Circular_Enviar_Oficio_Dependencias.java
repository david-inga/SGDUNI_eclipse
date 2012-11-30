/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_facultad;
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
public class Oficio_Circular_Enviar_Oficio_Dependencias extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irAlFormularioENVIAR";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        int in_codigo_oficio = Integer.parseInt( request.getParameter("codOficio").toString().trim() );

        DataSource dataSource = getDataSource(request, "DSconnection");

        orgen_ta_dependencia_DAO daoDependencia = new orgen_ta_dependencia_DAO(dataSource);
        orgen_ta_facultad_DAO daoFacultad = new orgen_ta_facultad_DAO(dataSource);
        orpro_oficio_circular_DAO daoOficio = new orpro_oficio_circular_DAO(dataSource);

        ArrayList<orgen_ta_dependencia> listaDependencias = daoDependencia.getAllDependenciasActivas();
        ArrayList<orgen_ta_facultad> listaFacultades = daoFacultad.getAllFacultadesActivas();
        String ch_codigo_oficio = daoOficio.getCh_Codigo_OficioSegunID(in_codigo_oficio);
        String ususario_emisor = daoOficio.getUsuarioEmisorDelOficio(in_codigo_oficio).getIn_codigo_usuario().toString();
        
        request.setAttribute("dependencias"    , listaDependencias);
        request.setAttribute("facultades"      , listaFacultades);
        request.setAttribute("in_codigo_oficio", in_codigo_oficio);
        request.setAttribute("ch_codigo_oficio", ch_codigo_oficio);
        request.setAttribute("emisor"          , ususario_emisor);

        
        return mapping.findForward(SUCCESS);
    }
}
