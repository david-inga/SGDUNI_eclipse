/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_rof_DAO;
import com.sgduni.forms.orpro_ta_rof;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author pc
 */
public class ROF_guardar_parte2 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irFormularioTres";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
            DataSource dataSource = getDataSource(request, "DSconnection");

            orpro_ta_rof_DAO daoRof = new orpro_ta_rof_DAO(dataSource);

            orpro_ta_rof objForm = (orpro_ta_rof)form;
            int idROF = objForm.getIn_codigo_rof();

            HttpSession sesion = request.getSession();
            int idVersion =Integer.parseInt( sesion.getAttribute("xidVersionROF").toString() );


                System.out.println("el ide del rof es "+idROF);
                if(daoRof.guardarSegundaParteRof(objForm,idVersion))
                {
                   System.out.println("dentro , el id es "+idROF);
                   request.setAttribute("idRof", idROF );
                   request.setAttribute("mensaje_de_exito", "Las Base Legales, EL Alcance, <br/>El Organigrama y Las Funciones Generales  <br/>Fueron Guardados Correctamente,<br/> ahora prosiga porfavor!");
                }
                else
                {
                   request.setAttribute("mensaje_de_exito", "Error! lo sentimos pero no se pudo guardar el ROF, <br/> porfavor intente denuevo!");
                }
            
        return mapping.findForward(SUCCESS);
    }
}
