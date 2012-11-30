/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_rol_DAO;
import com.sgduni.dao.orgen_ta_herramientas_DAO;

import com.sgduni.forms.orgen_ta_rol;
import com.sgduni.forms.orgen_ta_herramientas;

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
public class Protocolos_Usuarios_Verifica extends org.apache.struts.action.Action
{    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession sesion = request.getSession();        
        String xIdUsuario = sesion.getAttribute("xid").toString();
        String xrolSelect =request.getParameter("rol").toString();       
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_rol_DAO daoRol = new orgen_ta_rol_DAO(dataSource);
        System.out.println("Cod Usu"+xIdUsuario+" cod rol :"+xrolSelect);
        if(daoRol.getRoleSegunUsuarioValidar(Integer.parseInt(xIdUsuario), xrolSelect))
        {
           orgen_ta_herramientas_DAO daoHer=new orgen_ta_herramientas_DAO(dataSource);
           ArrayList<orgen_ta_herramientas> listaHerramienta=daoHer.getHerramientasSegunProtocolo(xrolSelect);           
           sesion.setAttribute("listaHerramienta", listaHerramienta);
           orgen_ta_rol objRol=new orgen_ta_rol();
           objRol=daoRol.getRol(xrolSelect);
           sesion.setAttribute("xrol",  objRol.getCh_codigo_rol());
           sesion.setAttribute("xnomrol",objRol.getVc_nombre());
           response.sendRedirect("menu.uni");
        }else{
            response.sendRedirect("menu.uni?rol=false");
        }
        
        return null;
    }
}
