package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_observaciones_rof_DAO;
import com.sgduni.forms.orpro_ta_observaciones_rof;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * @author JMarcos
 */
public class ROF_listarObservaciones extends org.apache.struts.action.Action
{    
    private final static String SUCCESS = "listarObsRof";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String idRof = request.getParameter("idRof").toString().trim();

        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_observaciones_rof_DAO dao =new orpro_ta_observaciones_rof_DAO(dataSource);
        ArrayList<orpro_ta_observaciones_rof> listaObserv = dao.getListaObservacionesRof( Integer.parseInt(idRof) );
        request.setAttribute("listaObservaciones", listaObserv);
        request.setAttribute("idRof", idRof);
        return mapping.findForward(SUCCESS);
    }
}
