/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_cargo_usuario_DAO;
import com.sgduni.forms.orgen_ta_cargo_usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author marco
 */
public class Tupa_llamarSubDetInsert extends org.apache.struts.action.Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_cargo_usuario_DAO dao = new orgen_ta_cargo_usuario_DAO(dataSource);
        ArrayList<orgen_ta_cargo_usuario> cargos = dao.getCargosEstructuralesActivos();
        request.setAttribute("cargosEstructurales",cargos);

        String idTupaDet = request.getParameter("idTupaDet").toString().trim();

        System.out.println("idTupaDaet: "+idTupaDet);


        request.setAttribute("idTupaDet", idTupaDet);
        
        return mapping.findForward("registrarSubDet");
    }
}
