/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orpro_oficio_circular;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * @author JMarcos
 */
public class Estructura_Organica_adjuntar_oficio extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irLista";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {
    //Sesion
    HttpSession sesion = request.getSession();

    String idDepFacu = sesion.getAttribute("xiddepen_facul").toString();
    String tipDepFac = (String)sesion.getAttribute("xtipodepen_facul").toString();

    //data source
    DataSource dataSource = getDataSource(request, "DSconnection");
    orpro_oficio_circular_DAO dao = new orpro_oficio_circular_DAO(dataSource);

    ArrayList<orpro_oficio_circular> lisOfc = dao.getListaDeOficiosAdjuntoSegunUsuario(idDepFacu, tipDepFac);

    request.setAttribute("listaOficiosPendientes", lisOfc);
    return mapping.findForward(SUCCESS);
    }
}
