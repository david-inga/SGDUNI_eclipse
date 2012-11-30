package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_estructura_organica_DAO;
import com.sgduni.forms.orgen_ta_estructura_organica;
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
public class ROF_adjuntarOrganigrama extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irListaOrganigramaAprobado";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        System.out.println("comienzo ROF_adjuntarOrganigrama  ");
            //Sesion
    HttpSession sesion = request.getSession();
    String idDepFacu = sesion.getAttribute("xiddepen_facul").toString();
    String tipDepFac = sesion.getAttribute("xtipodepen_facul").toString();

    //data source
    DataSource dataSource = getDataSource(request, "DSconnection");
    orpro_ta_estructura_organica_DAO dao = new orpro_ta_estructura_organica_DAO(dataSource);

    ArrayList<orgen_ta_estructura_organica> lisOrganigrama = dao.getListaDeOrganigramasAprobadasSegunUsuario(idDepFacu, tipDepFac);
        //System.out.println("el organigrama de retorno es "+lisOrganigrama.get(0).getCh_codigo_estructura());
    request.setAttribute("listaOrganigramasAprobadas", lisOrganigrama);
    return mapping.findForward(SUCCESS);
    }
}
