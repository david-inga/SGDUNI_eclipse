/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orpro_ta_mof_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
//import com.sgduni.dao.orgen_ta_estado_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orpro_ta_mof;
//import com.sgduni.forms.orgen_ta_estado;

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
public class MOF_Nuevo extends org.apache.struts.action.Action {
    /* forward name="success" path="" */
    private final static String NUEVO = "nuevo";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      //conec
      DataSource dataSource = getDataSource(request, "DSconnection");

      //var
      String cod=(request.getParameter("cod")!=null && Integer.parseInt(request.getParameter("cod").toString())>0)?request.getParameter("cod"):"0";

      //DAO
      orgen_ta_dependencia_DAO dao = new orgen_ta_dependencia_DAO(dataSource);
      orgen_ta_facultad_DAO daoFac=new orgen_ta_facultad_DAO(dataSource);
      //orgen_ta_estado_DAO daoEst=new orgen_ta_estado_DAO(dataSource);

      //array
      ArrayList<orgen_ta_dependencia> listaDependencia = dao.getDependencias();
      ArrayList<orgen_ta_facultad> listaFacultad = daoFac.getFacultades();
      orpro_ta_mof_DAO daoMofCod=new orpro_ta_mof_DAO(dataSource);
      //ArrayList<orgen_ta_estado> listaEstados = daoEst.getEstados();
      orpro_ta_mof forMof=new orpro_ta_mof();
      if(!cod.equals("0"))
      {//Buscamos los datos del MOF para modificar
          orpro_ta_mof_DAO daoMof=new orpro_ta_mof_DAO(dataSource);
          forMof=daoMof.BuscarMof(Integer.parseInt(cod));
      }
      request.setAttribute("forMof", forMof);//Formulario 
      request.setAttribute("dependencias", listaDependencia);
      request.setAttribute("facultad", listaFacultad);

      String codigo_generado = daoMofCod.getCodigoGenerado();
      request.setAttribute("codigo_generado", codigo_generado);
      return mapping.findForward(NUEVO);
    }
}
