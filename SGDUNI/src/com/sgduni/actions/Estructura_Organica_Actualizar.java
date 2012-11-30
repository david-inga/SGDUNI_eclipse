/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orpro_ta_estructura_organica_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_estructura_organica;
import com.sgduni.forms.orgen_ta_facultad;
import java.util.ArrayList;
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
 * @author JMarcos
 */
public class Estructura_Organica_Actualizar extends org.apache.struts.action.Action
{
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irFormularioModificar";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      //conec
      DataSource dataSource = getDataSource(request, "DSconnection");
      String codigoEstructura = request.getParameter("codigoEstructura").toString();
      orpro_ta_estructura_organica_DAO daoEO = new orpro_ta_estructura_organica_DAO(dataSource);
      orgen_ta_estructura_organica objEstructura = daoEO.getEstructuraOrganica( codigoEstructura );
      request.setAttribute("EstructuraOrganica", objEstructura );
      return mapping.findForward(SUCCESS);
    }
}
