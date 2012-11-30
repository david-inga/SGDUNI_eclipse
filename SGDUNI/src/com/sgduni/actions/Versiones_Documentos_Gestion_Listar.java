/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_versiones_mapro_DAO;
import com.sgduni.dao.orpro_ta_mapro_DAO;
import com.sgduni.dao.orpro_ta_versiones_mof_DAO;
import com.sgduni.dao.orpro_ta_mof_DAO;
import com.sgduni.dao.orpro_ta_versiones_rof_DAO;
import com.sgduni.dao.orpro_ta_rof_DAO;

import com.sgduni.forms.orpro_ta_versiones_mapro;
import com.sgduni.forms.orpro_ta_mapro;
import com.sgduni.forms.orpro_ta_versiones_mof;
import com.sgduni.forms.orpro_ta_mof;
import com.sgduni.forms.orpro_ta_versiones_rof;
import com.sgduni.forms.orpro_ta_rof;

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
public class Versiones_Documentos_Gestion_Listar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
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
            orpro_ta_versiones_rof_DAO daoRof=new orpro_ta_versiones_rof_DAO(dataSource);
            ArrayList<orpro_ta_versiones_rof> listaVersi=null;
            listaVersi=daoRof.getListaVersionesRof(idtipodoc);
            //System.out.println("DATOS RESLLS :"+listaVersi.size());

            orpro_ta_rof_DAO daoRf=new orpro_ta_rof_DAO(dataSource);
           // orpro_ta_rof formRf=daoRf.BuscarRof(idtipodoc);
           // request.setAttribute("id_estado", formRf.getIn_codigo_estado());
            request.setAttribute("listaVersiones", listaVersi);
       }else if(tipoDoc.equals("mof")){
            listar="listarMOF";
            orpro_ta_versiones_mof_DAO daoMof=new orpro_ta_versiones_mof_DAO(dataSource);
            ArrayList<orpro_ta_versiones_mof> listaVersi=null;
            listaVersi=daoMof.getListaVersionesMof(idtipodoc);

            orpro_ta_mof_DAO daoMf=new orpro_ta_mof_DAO(dataSource);
            orpro_ta_mof formMf=daoMf.BuscarMof(idtipodoc);
            request.setAttribute("id_estado", formMf.getIn_codigo_estado());

            request.setAttribute("listaVersiones", listaVersi);
       }else if(tipoDoc.equals("mapro")){
            listar="listarMAPRO";
            orpro_ta_versiones_mapro_DAO daoMapro=new orpro_ta_versiones_mapro_DAO(dataSource);
            ArrayList<orpro_ta_versiones_mapro> listaVersi=null;
            listaVersi=daoMapro.getListaVersionesMapro(idtipodoc);

            orpro_ta_mapro_DAO daoMap=new orpro_ta_mapro_DAO(dataSource);
            orpro_ta_mapro formMap=daoMap.BuscarMapro(idtipodoc);
            request.setAttribute("id_estado", formMap.getIn_codigo_estado());

            request.setAttribute("listaVersiones", listaVersi);            
       }
       request.setAttribute("idtipodoc", String.valueOf(idtipodoc));
       request.setAttribute("tipoDoc", String.valueOf(tipoDoc));
       System.out.println("Listar:"+listar+" codTop:"+tipoDoc+" IDpag:"+idtipodoc);
       return mapping.findForward(listar);
    }
}
