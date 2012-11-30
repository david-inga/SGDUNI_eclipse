/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orgen_ta_organo_DAO;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orgen_ta_organo;
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
public class Facultad_insert extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession sesion = request.getSession();
        String nombre = sesion.getAttribute("xnomus").toString();
        String id = sesion.getAttribute("xid").toString();
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_facultad_DAO dao = new orgen_ta_facultad_DAO(dataSource);
        orgen_ta_facultad objForm = (orgen_ta_facultad)form;
        boolean estado = dao.guardarFacultad(objForm,nombre,id);
        if(estado == true)
        {
            objForm.setMensaje("Facultad guardado correctamente");
        }
        else{
            objForm.setMensaje("Error! ocurrio un error al registrar el facultad");
        }
        orgen_ta_organo_DAO daoOrg = new orgen_ta_organo_DAO(dataSource);
        ArrayList<orgen_ta_organo> organos = daoOrg.getOrganos();
        request.setAttribute("organos",organos);
        return mapping.findForward("registrarFacultad");

//        HttpSession sesion = request.getSession();
//        String nombre = sesion.getAttribute("xnomus").toString();
//        String id = sesion.getAttribute("xid").toString();
//
//        response.setContentType("text/html");
//        response.setHeader("Cache-Control", "no-cache");
//
//        //variables
//        String vc_nombre = request.getParameter("vc_nombre");
//        String vc_nom_abrev = request.getParameter("vc_abrev_nom");
//        String vc_descripcion = request.getParameter("vc_descripcion");
//        String in_codigo_organo = request.getParameter("in_codigo_organo");
//
//        DataSource dataSource = getDataSource( request , "DSconnection");
//
//        orgen_ta_organo_DAO dao = new orgen_ta_organo_DAO(dataSource);
//        orgen_ta_organo objForm = new orgen_ta_organo();
//        objForm.setVc_nombre(vc_nombre);
//        objForm.setVc_descripcion(vc_descripcion);
//
//        boolean estado = dao.guardarOrgano(objForm,nombre,id);
//        PrintWriter writer;
//        try
//        {
//           writer =response.getWriter();
//           if(estado)
//               writer.print("1");
//           else
//               writer.print("0");
//             writer.flush();
//             writer.close();
//        }
//        catch (IOException ex)
//        {
//            writer =response.getWriter();
//            writer.print("0");
//            writer.flush();
//            writer.close();
//            Logger.getLogger(Tupa_insertarDetalle.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
    }
}
