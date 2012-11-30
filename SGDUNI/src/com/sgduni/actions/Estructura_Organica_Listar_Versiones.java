/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_estructura_organica_DAO;
import com.sgduni.forms.orpro_ta_versiones_estruc;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author JMarcos
 */
public class Estructura_Organica_Listar_Versiones extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irVerVersionesEO";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {
        String idEstructura = request.getParameter("idEstructura").toString();
        //System.out.println("el codigo = "+idEstructura);
        DataSource dataSource = getDataSource(request, "DSconnection");

        orpro_ta_estructura_organica_DAO daoEO = new orpro_ta_estructura_organica_DAO(dataSource);
        ArrayList<orpro_ta_versiones_estruc> listaVersionesEstructura = daoEO.getListaVersionesEstructura(idEstructura);
        
        
        request.setAttribute("listaVersionesEstructura", listaVersionesEstructura);
        return mapping.findForward(SUCCESS);
    }
}
