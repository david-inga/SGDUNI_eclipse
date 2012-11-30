package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_rof_DAO;
import com.sgduni.forms.orpro_ta_registro_rof;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author pc
 */
public class ROF_agregarRegistro_Listar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irLisRegistro";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession sesion = request.getSession();
        String idVersion = sesion.getAttribute("xidVersionROF").toString();

        Integer idRof = Integer.parseInt(request.getParameter("idRof"));
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_rof_DAO daoRof = new orpro_ta_rof_DAO(dataSource);
        ArrayList<orpro_ta_registro_rof> RegLis = daoRof.getListaRegistrosRof(idRof,Integer.parseInt(idVersion));

        //System.out.println("la base legal es "+RegLis.get(0).getVc_nombre_unidad());

        request.setAttribute("ListaNombreUnidadROF", RegLis);
        return mapping.findForward(SUCCESS);
    }
}
