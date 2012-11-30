/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_estado_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orpro_ta_cap_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_estado;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orpro_ta_cap;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/*
 * @author marco
 */

public class Cap_llamarFormInsert extends org.apache.struts.action.Action
{    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        //conec
        DataSource dataSource = getDataSource(request, "DSconnection");

        orgen_ta_dependencia_DAO daoDepend = new orgen_ta_dependencia_DAO(dataSource);
        ArrayList<orgen_ta_dependencia> depend = daoDepend.getDependenciasActivas();
        request.setAttribute("dependencias",depend);

        orgen_ta_facultad_DAO daoFac = new orgen_ta_facultad_DAO(dataSource);
        ArrayList<orgen_ta_facultad> facultades = daoFac.getFacultades();
        request.setAttribute("facultades",facultades);

        //orgen_ta_estado_DAO daoEstado = new  orgen_ta_estado_DAO(dataSource);
        //ArrayList<orgen_ta_estado> estados = daoEstado.getEstados();
        //request.setAttribute("estados",estados);

        orpro_ta_cap_DAO daoCAP = new  orpro_ta_cap_DAO(dataSource);
        request.setAttribute("nuevo_codigo_cap",daoCAP.getCodigoGenerado());

        return mapping.findForward("registrarCap");
    }
}
