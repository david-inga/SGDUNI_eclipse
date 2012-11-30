/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_estado_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orpro_ta_cap_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_estado;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orpro_ta_cap;
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
 * @author marco
 */
public class Cap_insertar extends org.apache.struts.action.Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession sesion = request.getSession();
        String nombre = sesion.getAttribute("xnomus").toString();
        String id =sesion.getAttribute("xid").toString();
        String codRol =sesion.getAttribute("xrol").toString().trim();
        System.out.println("el rol del usua es = "+codRol);
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_cap_DAO daoCap = new orpro_ta_cap_DAO(dataSource);
        orpro_ta_cap objForm = (orpro_ta_cap)form;
         boolean estado = false;
        if(codRol.toUpperCase().equals("ROL02"))
        {
            System.out.println("el usuario es usuario");
            int idEstado = 11;
            estado = daoCap.guardarCap(objForm, nombre, id,idEstado);
        }
        else
        {
             System.out.println("el usuario es ocdo");
            estado = daoCap.guardarCap(objForm, nombre, id);
        }      

        if(estado == true)
        {
            
            objForm.setMensaje("Cap registrado correctamente, Ahora agrege los detalles del CAP Creado");
        }
        else
        {
            objForm.setMensaje("Error! ocurrio un error al registrar el cap");
        }

        request.setAttribute("codCap", daoCap.getIn_codigo_cap().toString().trim() );
        
        orgen_ta_dependencia_DAO daoDepend = new orgen_ta_dependencia_DAO(dataSource);
        ArrayList<orgen_ta_dependencia> depend = daoDepend.getDependenciasActivas();
        request.setAttribute("dependencias",depend);
        
        orgen_ta_facultad_DAO daoFac = new orgen_ta_facultad_DAO(dataSource);
        ArrayList<orgen_ta_facultad> facultades = daoFac.getFacultades();
        request.setAttribute("facultades",facultades);
        
        orgen_ta_estado_DAO daoEstado = new  orgen_ta_estado_DAO(dataSource);
        ArrayList<orgen_ta_estado> estados = daoEstado.getEstados();
        request.setAttribute("estados",estados);       

        return mapping.findForward("registrarCap");
    }
}
