/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions.ajax;

import com.sgduni.dao.orgen_ta_rol_DAO;
import com.sgduni.forms.orgen_ta_rol;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Sistemas
 */
public class ListaRolesUsuarioLogueado extends org.apache.struts.action.Action {
    private final static String LISTADO = "listado";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        HttpSession xsession=request.getSession(true);
        PrintWriter writer;
        DataSource dataSource = getDataSource(request, "DSconnection");
        int in_usuario=Integer.parseInt(xsession.getAttribute("xid").toString());       
        try
        {
           orgen_ta_rol_DAO dao=new orgen_ta_rol_DAO(dataSource);
           writer =response.getWriter();
           ArrayList<orgen_ta_rol> listaRoles = dao.getRolesSegunUsuario(in_usuario);
           String jsonTEX="[";
           int numItem=0;
           for(orgen_ta_rol lisRol:listaRoles){
              jsonTEX+="{ \"id\": \""+lisRol.getCh_codigo_rol().trim()+"\", \"nombre\": \""+lisRol.getVc_nombre()+"\"}";               
              if((numItem + 1)<listaRoles.size())
                 jsonTEX+=",";//Al final del JSON ya no se pone COMAS
               numItem++;
           }          
           jsonTEX+="]";
           writer.print(jsonTEX);//Imprimimos el objeto JSON
           writer.flush();
           writer.close();
        } catch (IOException ex) {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger(NumeroDocumentosOficioPendientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        //return mapping.findForward(LISTADO);
    }
}
