/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_tramite_documentos_DAO;
import com.sgduni.forms.orpro_ta_tramite_documentos;
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
public class Tramite_Documentos_listar extends org.apache.struts.action.Action {

    private final static String LISTAR = "listar";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    //Sesion
    HttpSession sesion = request.getSession();
    String Rol=(String)sesion.getAttribute("xrol");
    Rol=Rol.trim().toUpperCase();
    String idDepFacu=sesion.getAttribute("xiddepen_facul").toString();
    String tipDepFac=(String)sesion.getAttribute("xtipodepen_facul").toString();

    //dataSource
    DataSource dataSource = getDataSource(request, "DSconnection");

    //dao
    orpro_ta_tramite_documentos_DAO daoTram=new orpro_ta_tramite_documentos_DAO(dataSource);
    
    //array
    ArrayList<orpro_ta_tramite_documentos> lisTram=null;

       if(Rol.equals("ROL02"))
       {//SOLO PARA USUARIO FACULTAD Y/O DEPENDENCIA
         lisTram=daoTram.getListaTramitesPorUsuario(Integer.parseInt(idDepFacu), tipDepFac);
         request.setAttribute("tramites", lisTram); 
       }else{
          lisTram=daoTram.getListaTramites();
          request.setAttribute("tramites", lisTram);
       }
               
        return mapping.findForward(LISTAR);
    }
}
