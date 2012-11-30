package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_facultad;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * @author marco
 */
public class Cap_llamarListado extends org.apache.struts.action.Action
{ 
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        
        orgen_ta_dependencia_DAO daoDepend = new orgen_ta_dependencia_DAO(dataSource);
        ArrayList<orgen_ta_dependencia> depend = daoDepend.getDependenciasActivas();
        request.setAttribute("dependencias",depend);

        orgen_ta_facultad_DAO daoFac = new orgen_ta_facultad_DAO(dataSource);
        ArrayList<orgen_ta_facultad> facultades = daoFac.getFacultadesActivas();
        request.setAttribute("facultades",facultades);
        
        return mapping.findForward("listadoCap");
    }
}
