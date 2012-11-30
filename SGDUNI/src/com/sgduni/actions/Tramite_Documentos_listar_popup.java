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

import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
/**
 *
 * @author Sistemas
 */
public class Tramite_Documentos_listar_popup extends org.apache.struts.action.Action
{       
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
   {
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_tramite_documentos_DAO daoTram=new orpro_ta_tramite_documentos_DAO(dataSource);
        ArrayList<orpro_ta_tramite_documentos> lisTram=daoTram.getListaTramites();
        request.setAttribute("tramites", lisTram);
        return mapping.findForward("listar");
    }
}
