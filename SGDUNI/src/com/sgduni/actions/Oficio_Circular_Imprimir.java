/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orpro_oficio_circular;
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
public class Oficio_Circular_Imprimir extends org.apache.struts.action.Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        //Variables
        int inOfic = Integer.parseInt(request.getParameter("idOfc"));
        int idEmisor = Integer.parseInt(request.getParameter("idEmisor"));
        //Data Source
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_oficio_circular_DAO daoOF=new orpro_oficio_circular_DAO(dataSource);
        orpro_oficio_circular ResulOfi= daoOF.getOficioParalaVista(idEmisor,inOfic);
        request.setAttribute("ObjOfico", ResulOfi);
        return mapping.findForward("imprimir");
    }
}