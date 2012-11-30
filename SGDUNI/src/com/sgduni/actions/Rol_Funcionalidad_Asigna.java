/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_rol_DAO;
import com.sgduni.dao.orgen_ta_herramientas_DAO;
import com.sgduni.dao.orgen_ta_rol_fun_DAO;
import com.sgduni.dao.orgen_ta_funcionalidad_DAO;

import com.sgduni.forms.orgen_ta_rol;
import com.sgduni.forms.orgen_ta_herramientas;
import com.sgduni.forms.orgen_ta_funcionalidad;



import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author marco
 */
public class Rol_Funcionalidad_Asigna extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String asignar = "asignar";    
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DataSource dataSource = getDataSource(request, "DSconnection");
        //Roles
        orgen_ta_rol_DAO daoRol = new orgen_ta_rol_DAO(dataSource);
        ArrayList<orgen_ta_rol> listaRoles = daoRol.getRoles();
        //Herramientas
        orgen_ta_herramientas_DAO daoHerr= new orgen_ta_herramientas_DAO(dataSource);
        ArrayList<orgen_ta_herramientas> listaHerramientas=daoHerr.getHerramientas();

        //Funcionalidades de las Herramientas array
        ArrayList<orgen_ta_funcionalidad> listaFuncionalidades=null;

        //Verificamos si las variables GET existes
        String codherr=(request.getParameter("codherr")!=null)?request.getParameter("codherr"):"0";//codigo de la herramienta
        String codrol=(request.getParameter("codrol")!=null)?request.getParameter("codrol"):"0";//codigo del rol

        //Verificamos si existe el codigo de la herramienta para filtrar sus funcionalidades
        if(codrol!="0")
         {
            orgen_ta_funcionalidad_DAO daoFun=new orgen_ta_funcionalidad_DAO(dataSource);
            listaFuncionalidades=daoFun.getFuncionalidadSegunHerramientarRol(codherr,codrol);
         }

        request.setAttribute("roles",listaRoles);
        request.setAttribute("herramientas",listaHerramientas);
        request.setAttribute("funcionalidades",listaFuncionalidades);
        //
        request.setAttribute("codrol", codrol);
        request.setAttribute("codherr", codherr);


        return mapping.findForward(asignar);
    }
}
