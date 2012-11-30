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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Sistemas
 */
public class Observacion_Documentos_Gestion_Nuevo extends org.apache.struts.action.Action
{    
    
    private static String nuevo = null;
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
       orgen_ta_estado_DAO daoEst=new orgen_ta_estado_DAO(dataSource);
       ArrayList<orgen_ta_estado> listaEstados = daoEst.getEstados();

       if(tipoDoc.equals("rof"))
       {
           nuevo="nuevoROF";
       }else if(tipoDoc.equals("mof")){
            nuevo="nuevoMOF";
       }else if(tipoDoc.equals("mapro")){
            nuevo="nuevoMAPRO";
       }
       System.out.println("SALI:"+nuevo);
       request.setAttribute("estados", listaEstados);
       request.setAttribute("idtipodoc", String.valueOf(idtipodoc));
       request.setAttribute("tipoDoc", String.valueOf(tipoDoc));
       return mapping.findForward(nuevo);
    }
}
