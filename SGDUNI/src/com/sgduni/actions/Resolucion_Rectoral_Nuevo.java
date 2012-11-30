/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_estado_DAO;
import com.sgduni.forms.orgen_ta_estado;

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
 * @author Sistemas
 */
public class Resolucion_Rectoral_Nuevo extends org.apache.struts.action.Action
{
    private final static String NUEVO = "nuevo";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      //conec
      DataSource dataSource = getDataSource(request, "DSconnection");

      //DAO
      orgen_ta_estado_DAO daoEst=new orgen_ta_estado_DAO(dataSource);

      //array
      ArrayList<orgen_ta_estado> listaEstados = daoEst.getEstados();
      request.setAttribute("estados", listaEstados);
      return mapping.findForward(NUEVO);
    }
}
