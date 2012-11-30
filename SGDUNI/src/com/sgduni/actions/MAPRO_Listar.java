/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;
import com.sgduni.dao.orpro_ta_mapro_DAO;
import com.sgduni.forms.orpro_ta_mapro;
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
public class MAPRO_Listar extends org.apache.struts.action.Action {
    private final static String LISTAR = "listar";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      //Session usuario
    HttpSession sesion = request.getSession();
    String Rol=(String)sesion.getAttribute("xrol");
    Rol=Rol.trim().toUpperCase();
    
    String idDepFacu=sesion.getAttribute("xiddepen_facul").toString();
    String tipDepFac=(String)sesion.getAttribute("xtipodepen_facul").toString();

       DataSource dataSource = getDataSource(request, "DSconnection");
       orpro_ta_mapro_DAO daoRof=new orpro_ta_mapro_DAO(dataSource);
       if(Rol.equals("ROL02"))
       {//Filtra sol para los usuario
            ArrayList<orpro_ta_mapro> maproLis=daoRof.getListaMaproPorUsuario(Integer.parseInt(idDepFacu), tipDepFac);
            request.setAttribute("mapro", maproLis);
       }else{
            ArrayList<orpro_ta_mapro> maproLis=daoRof.getListaMapro();
            request.setAttribute("mapro", maproLis);
       }

       return mapping.findForward(LISTAR);
    }
}
