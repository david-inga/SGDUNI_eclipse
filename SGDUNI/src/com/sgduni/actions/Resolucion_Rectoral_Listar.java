/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_resolucion_rectoral_DAO;
import com.sgduni.forms.orpro_resolucion_rectoral;
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
public class Resolucion_Rectoral_Listar extends org.apache.struts.action.Action
{
    private final static String LISTAR = "listar";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
       DataSource dataSource = getDataSource(request, "DSconnection");
       orpro_resolucion_rectoral_DAO daoRR=new orpro_resolucion_rectoral_DAO(dataSource);
       ArrayList<orpro_resolucion_rectoral> lisResol=daoRR.getListaResolucion();
       request.setAttribute("lisResol", lisResol);
        return mapping.findForward(LISTAR);
    }
}
