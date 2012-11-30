/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_versiones_mapro_DAO;
import com.sgduni.dao.orpro_ta_versiones_mof_DAO;
import com.sgduni.dao.orpro_ta_versiones_rof_DAO;
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
public class Descargar_Archivos extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      //Session usuario
        HttpSession session=request.getSession();
        int idUser=Integer.parseInt(session.getAttribute("xid").toString());
        String Rol=(String)session.getAttribute("xrol");
        Rol=Rol.trim().toUpperCase();
        int idEstado=0;

        //conec
        DataSource dataSource = getDataSource(request, "DSconnection");
      
        String tip=(String)request.getParameter("tip");
        tip=tip.trim().toLowerCase();
        String acriv=(String)request.getParameter("acriv");
        String iddoc=(String)request.getParameter("iddoc");


        if(Rol.equals("ROL01"))
        {//OCDO
           idEstado=11;//REVISION OCOD
        }else if(Rol.equals("ROL02"))
        {//USUARIO
           idEstado=12;//REVISION USUARIO
        }else if(Rol.equals("ROL03"))
        {//ASESORIA LEGAL
          idEstado=14;//REVISION ASESORIA LEGAL
        }




        if(tip.equals("mapro"))
        {
          orpro_ta_versiones_mapro_DAO daoMapro=new orpro_ta_versiones_mapro_DAO(dataSource);
          daoMapro.modificarVersionEstadoMapro(Integer.parseInt(iddoc), idEstado);
          response.sendRedirect("documentos/mapro/"+acriv);
        }else if(tip.equals("rof"))
        {
          orpro_ta_versiones_rof_DAO daoRof=new orpro_ta_versiones_rof_DAO(dataSource);
          daoRof.modificarVersionEstadoRof(Integer.parseInt(iddoc), idEstado);
          response.sendRedirect("documentos/rof/"+acriv);
        }else if(tip.equals("mof"))
        {
          orpro_ta_versiones_mof_DAO daoMof=new orpro_ta_versiones_mof_DAO(dataSource);
          daoMof.modificarVersionEstadoMof(Integer.parseInt(iddoc), idEstado);
          response.sendRedirect("documentos/mof/"+acriv);
        }
        
        return null ;// mapping.findForward(SUCCESS);
    }
}
