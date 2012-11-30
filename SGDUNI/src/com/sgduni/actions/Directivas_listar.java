package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_directivas_DAO;
import com.sgduni.forms.orpro_ta_directivas;
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
 * @author marco
 */
public class Directivas_listar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
       HttpSession sesion = request.getSession();
       String Rol=(String)sesion.getAttribute("xrol");
       Rol=Rol.trim().toUpperCase();
       String idDepFacu=sesion.getAttribute("xiddepen_facul").toString();
       String tipDepFac=(String)sesion.getAttribute("xtipodepen_facul").toString();

        DataSource dataSource = getDataSource(request, "DSconnection");

        if(Rol.equals("ROL02"))
       {//SOLO PARA USUARIO FACULTAD Y/O DEPENDENCIA
        orpro_ta_directivas_DAO dao = new orpro_ta_directivas_DAO(dataSource);
        ArrayList<orpro_ta_directivas> lista = dao.getDirectivasUsuario(tipDepFac, idDepFacu);
        request.setAttribute("directivas",lista);
        }
        else
        {
        orpro_ta_directivas_DAO dao = new orpro_ta_directivas_DAO(dataSource);
        ArrayList<orpro_ta_directivas> lista = dao.getDirectivas();
        request.setAttribute("directivas",lista);
        }

        return mapping.findForward("listarDirectivas");
    }
}
