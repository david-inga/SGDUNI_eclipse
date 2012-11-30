/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;
import com.sgduni.dao.orpro_resolucion_rectoral_DAO;
import com.sgduni.forms.orpro_resolucion_rectoral;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.upload.FormFile;
/**
 *
 * @author Sistemas
 */
public class Resolucion_Rectoral_Exportar extends org.apache.struts.action.Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      //conec
      DataSource dataSource = getDataSource(request, "DSconnection");

     String in_codigo_resolucion=(String)request.getParameter("idRes");
     orpro_resolucion_rectoral_DAO doaRR=new orpro_resolucion_rectoral_DAO(dataSource);
     orpro_resolucion_rectoral objRR=doaRR.BuscarResolucionRec(Integer.parseInt(in_codigo_resolucion));
     //System.out.println("DATO :"+in_codigo_resolucion);
     request.setAttribute("objRR", objRR);
     return mapping.findForward("exportar");
    }
}
