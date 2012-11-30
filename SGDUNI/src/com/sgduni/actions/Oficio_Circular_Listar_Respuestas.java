/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orpro_oficio_circular;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Programmer : Marco A. Estrella Cardenas
 */
public class Oficio_Circular_Listar_Respuestas extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irHistorialRespuesta";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
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
       String titulo = "";

       if(idRol.equals("ROL01"))
       {
           listaOficios = dao.getListaHistorialDeOficiosRespuestaDeDependenciasTotal();
           titulo = "Historial de Oficios de las Dependencias enviados como respuesta";
       }
       else if(idRol.equals("ROL02") || idRol.equals("ROL03"))
       {
          listaOficios = dao.getListaHistorialDeOficiosRespuestaSegunDependnecia(idDepFacu,tipDepFac);
          titulo = "Historial de Oficios Enviados como Respuesta";
       }

       request.setAttribute("titulo", titulo);
       request.setAttribute("oficios",listaOficios);
       return mapping.findForward(SUCCESS);
    }
}
