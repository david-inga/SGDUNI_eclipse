package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_observaciones_mapro_DAO;
import com.sgduni.dao.orpro_ta_observaciones_rof_DAO;
import com.sgduni.dao.orpro_ta_observaciones_mof_DAO;

import com.sgduni.forms.orpro_ta_observaciones_mapro;
import com.sgduni.forms.orpro_ta_observaciones_rof;
import com.sgduni.forms.orpro_ta_observaciones_mof;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Sistemas
 */
public class Observacion_Documentos_Gestion_Listar extends org.apache.struts.action.Action
{        
  private static String listar=null;
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
       //Variables
       String tipoDoc=request.getParameter("tip");
       int idtipodoc = Integer.parseInt(request.getParameter("idtipodoc").trim());

       //datasource
       DataSource dataSource = getDataSource(request, "DSconnection");

       //DAOS
       if(tipoDoc.equals("rof"))
       {
            listar="listarROF";
            orpro_ta_observaciones_rof_DAO daoRof=new orpro_ta_observaciones_rof_DAO(dataSource);
            ArrayList<orpro_ta_observaciones_rof> listaObserv=null;
            listaObserv=daoRof.getListaObservacionesRof(idtipodoc);            
            request.setAttribute("listaObservaciones", listaObserv);
       }else if(tipoDoc.equals("mof")){
            listar="listarMOF";
            orpro_ta_observaciones_mof_DAO daoMof=new orpro_ta_observaciones_mof_DAO(dataSource);
            ArrayList<orpro_ta_observaciones_mof> listaObserv=null;
            listaObserv=daoMof.getListaObservacionesMof(idtipodoc);            
            request.setAttribute("listaObservaciones", listaObserv);
       }else if(tipoDoc.equals("mapro"))
       {
            listar="listarMAPRO";
            orpro_ta_observaciones_mapro_DAO daoMof=new orpro_ta_observaciones_mapro_DAO(dataSource);
            ArrayList<orpro_ta_observaciones_mapro> listaObserv=null;
            listaObserv=daoMof.getListaObservacionesMapro(idtipodoc);
            request.setAttribute("listaObservaciones", listaObserv);
       }
       request.setAttribute("idtipodoc", String.valueOf(idtipodoc));
       request.setAttribute("tipoDoc", String.valueOf(tipoDoc));
       
       return mapping.findForward(listar);

    }
}