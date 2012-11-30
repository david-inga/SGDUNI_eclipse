package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orpro_ta_estructura_organica_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_facultad;
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
public class Estructura_Organica_Nuevo extends org.apache.struts.action.Action {
    
    private final static String SUCCESS = "irFormularioNuevo";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {
      DataSource dataSource = getDataSource(request, "DSconnection");
      //var
      //String cod=(request.getParameter("cod")!=null && Integer.parseInt(request.getParameter("cod").toString())>0)?request.getParameter("cod"):"0";

      // DAO
      orgen_ta_dependencia_DAO dao = new orgen_ta_dependencia_DAO(dataSource);
      orgen_ta_facultad_DAO daoFac=new orgen_ta_facultad_DAO(dataSource);
      orpro_ta_estructura_organica_DAO daoEO = new orpro_ta_estructura_organica_DAO(dataSource);

      //array de facultades y dependnecias activas
      ArrayList<orgen_ta_dependencia> listaDependencia = dao.getDependenciasActivas();
      ArrayList<orgen_ta_facultad> listaFacultad = daoFac.getFacultadesActivas();
       String nuevo_codigo_generado = daoEO.getCodigoGenerado();

      // System.out.println("codigo generado "+nuevo_codigo_generado);
      request.setAttribute("nuevo_codigo_generado",nuevo_codigo_generado);
      request.setAttribute("dependencias", listaDependencia);
      request.setAttribute("facultades", listaFacultad);

      return mapping.findForward(SUCCESS);
    }
}
