package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_observaciones_cap_DAO;
import com.sgduni.forms.orpro_ta_observaciones_cap;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author marco
 */
public class Cap_observaciones_listar extends org.apache.struts.action.Action {
    
    private final static String LISTAR = "listar";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String idCap = request.getParameter("idCap").toString().trim();

        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_observaciones_cap_DAO daoCap =new orpro_ta_observaciones_cap_DAO(dataSource);
        ArrayList<orpro_ta_observaciones_cap> listaObserv = daoCap.getObservacionesPorCap(Integer.parseInt(idCap));
        request.setAttribute("listaObservaciones", listaObserv);
        request.setAttribute("idCap", idCap);
        return mapping.findForward(LISTAR);
    }
}
