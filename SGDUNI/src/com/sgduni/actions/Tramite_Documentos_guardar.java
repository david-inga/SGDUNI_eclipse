/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orpro_ta_tramite_documentos_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orpro_ta_tramite_documentos;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
//import org.apache.catalina.connector.Request;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Sistemas
 */
public class Tramite_Documentos_guardar extends org.apache.struts.action.Action
{        
    private final static String NUEVO = "nuevo";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      //Session
        HttpSession sesion= request.getSession(true);
        int idUser=Integer.parseInt(sesion.getAttribute("xid").toString());

         int idEstado=16;//por defecto tramite pendiente
      //conec
      DataSource dataSource = getDataSource(request, "DSconnection");

      //DAO
      orgen_ta_dependencia_DAO dao = new orgen_ta_dependencia_DAO(dataSource);
      orgen_ta_facultad_DAO daoFac=new orgen_ta_facultad_DAO(dataSource);

      //form
      orpro_ta_tramite_documentos daoForT = (orpro_ta_tramite_documentos)form;
      //orpro_ta_tramite_documentos daoForT=new orpro_ta_tramite_documentos();


      //array
      ArrayList<orgen_ta_dependencia> listaDependencia = dao.getDependencias();
      ArrayList<orgen_ta_facultad> listaFacultad = daoFac.getFacultades();

      request.setAttribute("dependencias", listaDependencia);
      request.setAttribute("facultad", listaFacultad);
      //
      orpro_ta_tramite_documentos_DAO daoTram=new orpro_ta_tramite_documentos_DAO(dataSource);
      orpro_ta_tramite_documentos thisForm=(orpro_ta_tramite_documentos)form;

      //
      if(daoTram.guardarTramite(thisForm, idUser,idEstado))
      {
         daoForT.reset(mapping, request);
         daoForT.setMensaje("Trámite guardado e iniciado correctamente");
      }else{
          daoForT.setMensaje("Ocurrio un error al intentar guardar el trámite");
      }
      
        return mapping.findForward(NUEVO);
    }
}
