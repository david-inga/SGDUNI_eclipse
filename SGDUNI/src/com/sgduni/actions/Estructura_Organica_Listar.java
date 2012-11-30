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
 * @author JMarcos
 */
public class Estructura_Organica_Listar extends org.apache.struts.action.Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        //System.out.println("inicio");
        DataSource dataSource = getDataSource(request, "DSconnection");

        //Session usuario
        HttpSession session=request.getSession();
        String Rol= session.getAttribute("xrol").toString().trim();
        int idDepFacu =  Integer.parseInt( session.getAttribute("xiddepen_facul").toString() );
        String tipDepFac = session.getAttribute("xtipodepen_facul").toString();
        //System.out.println("id "+idDepFacu+" tip "+tipDepFac);
        orpro_ta_estructura_organica_DAO dao = new orpro_ta_estructura_organica_DAO(dataSource);

        String ver = ( request.getParameter("ver")!= null ) ? request.getParameter("ver") : "0";
        String titulo = "";

        if( ver.equals("aprobado") )
        {
            if(Rol.equals("ROL02"))
            {
               ArrayList<orgen_ta_estructura_organica> lista = dao.getListaEstructurasAprobadasPorUsuario(idDepFacu, tipDepFac);
               request.setAttribute("listaEstructuraOrganica",lista);
            }
            else
            {
               ArrayList<orgen_ta_estructura_organica> lista = dao.getListaEstructurasAprobadas();
               request.setAttribute("listaEstructuraOrganica",lista);
            }
            titulo = "Lista de <br/> Estructuras Organicas <br/> Aprobadas";
            request.setAttribute("titulo", titulo);
        }
        else if( ver.equals("historial") )
        {
            if(Rol.equals("ROL02"))
            {
               ArrayList<orgen_ta_estructura_organica> lista = dao.getListaEstructurasHistorialPorUsuario(idDepFacu, tipDepFac);
               request.setAttribute("listaEstructuraOrganica",lista);
            }
            else
            {
               ArrayList<orgen_ta_estructura_organica> lista = dao.getListaEstructurasHistorial();
               request.setAttribute("listaEstructuraOrganica",lista);
            }
            titulo = "Historial de <br/> Estructuras Organicas ";
            request.setAttribute("titulo", titulo);
        }
        else
        {
            if(Rol.equals("ROL02"))
            {
               ArrayList<orgen_ta_estructura_organica> lista = dao.getListaEstructurasPendientesPorUsuario(idDepFacu, tipDepFac);
               request.setAttribute("listaEstructuraOrganica",lista);
            }
            else
            {
               ArrayList<orgen_ta_estructura_organica> lista = dao.getListaEstructuras();
               request.setAttribute("listaEstructuraOrganica",lista);
            }
            titulo = "Lista de <br/> Estructuras Organicas <br/> Pendientes";
            request.setAttribute("titulo", titulo);
        }
        
        return mapping.findForward("irlistado");
    }
}
