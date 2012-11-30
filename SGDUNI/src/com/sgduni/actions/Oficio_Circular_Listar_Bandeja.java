package com.sgduni.actions;

import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orpro_oficio_circular;

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
 * @author Sistemas
 */
public class Oficio_Circular_Listar_Bandeja extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private final static String LISTADO = "listado";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession sesion =  request.getSession();
        String idRol      =  sesion.getAttribute("xrol").toString().trim();
        int idDepFacu   =  Integer.parseInt(sesion.getAttribute("xiddepen_facul").toString().trim());
        String tipDepFac   =  sesion.getAttribute("xtipodepen_facul").toString().trim();


       DataSource dataSource = getDataSource(request, "DSconnection");
       orpro_oficio_circular_DAO dao = new orpro_oficio_circular_DAO(dataSource);
       ArrayList<orpro_oficio_circular> listaOficios = null;

       if(idRol.equals("ROL01"))
       {
           listaOficios = dao.getListaHistorialDeOficioTramitadosTotal();
       }
       else if(idRol.equals("ROL02") || idRol.equals("ROL03"))
       {
          listaOficios = dao.getListaHistorialDeOficioRecibidosPorDependencia(tipDepFac, idDepFacu);
       }

       request.setAttribute("oficios",listaOficios);
       return mapping.findForward(LISTADO);
    }
}
