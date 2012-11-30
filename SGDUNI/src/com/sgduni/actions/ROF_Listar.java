package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_rof_DAO;
import com.sgduni.forms.orpro_ta_rof;
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
public class ROF_Listar extends org.apache.struts.action.Action
{    
    private final static String LISTAR = "listar";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        //Session usuario
        HttpSession sesion = request.getSession();
        String Rol = sesion.getAttribute("xrol").toString().trim().toUpperCase() ;
        String idDepFacu=sesion.getAttribute("xiddepen_facul").toString();
        String tipDepFac=(String)sesion.getAttribute("xtipodepen_facul").toString();

        String ver = ( request.getParameter("ver")!= null ) ? request.getParameter("ver") : "0";
        String titulo = "";
        String borrador = null;

        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_rof_DAO daoRof = new orpro_ta_rof_DAO(dataSource);
        
        if( ver.equals("borrador") )
        {
           if( Rol.equals("ROL02") )
           {//Filtra sol para los usuario
              ArrayList<orpro_ta_rof> rofLis = daoRof.getListaRofPorUsuarioBorrador(Integer.parseInt(idDepFacu), tipDepFac);
              request.setAttribute("ListaRof", rofLis);
           }
           else
           {
              ArrayList<orpro_ta_rof> rofLis = daoRof.getListaRofBorrador();
              request.setAttribute("ListaRof", rofLis);
           }
           titulo = "Lista de <br/> ROF no terminados";
           borrador = "1";
           request.setAttribute("borrador", borrador);
           request.setAttribute("titulo", titulo);
        }
        else
        {
           if( Rol.equals("ROL02") )
           {//Filtra sol para los usuario
              ArrayList<orpro_ta_rof> rofLis = daoRof.getListaRofPorUsuario(Integer.parseInt(idDepFacu), tipDepFac);
              request.setAttribute("ListaRof", rofLis);
           }
           else
           {
              ArrayList<orpro_ta_rof> rofLis = daoRof.getListaRof();
              request.setAttribute("ListaRof", rofLis);
           }
           titulo = "Lista de <br/> ROF en Tramite";
           borrador = "0";
           request.setAttribute("borrador", borrador);
           request.setAttribute("titulo", titulo);
        }
        return mapping.findForward(LISTAR);
    }
}
