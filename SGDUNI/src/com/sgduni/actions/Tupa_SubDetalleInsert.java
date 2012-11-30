/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_proc_tupa_DAO;
import com.sgduni.forms.orpro_ta_proc_tupa;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author marco
 */
public class Tupa_SubDetalleInsert extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String in_codigo_det = request.getParameter("in_codigo_det").toString().trim();
        String de_tramitacion_porc = request.getParameter("de_tramitacion_porc").toString().trim();
        String de_tramitacion_sol = request.getParameter("de_tramitacion_sol").toString().trim();
        String calificacion = request.getParameter("ch_evaluacion_previa").toString().trim();
        String in_plazo_resolver_dias = request.getParameter("in_plazo_resolver_dias").toString().trim();
        String vc_inicio_procedimiento = request.getParameter("vc_inicio_procedimiento").toString().trim();
        String in_autoridad_com_resolver = request.getParameter("in_autoridad_com_resolver").toString().trim();
        String vc_reconsideracion = request.getParameter("vc_reconsideracion").toString().trim();
        String vc_apelacion = request.getParameter("vc_apelacion").toString().trim();

        String evaluacion_previa = "";
        String automatico = "";

        if(calificacion.trim().equals("a"))
        {
            automatico = calificacion;
        }
        else if( calificacion.trim().equals("p") || calificacion.trim().equals("n") )
        {
           evaluacion_previa = calificacion;
        }
        else
        {
           evaluacion_previa = "";
           automatico = "";
        }

        orpro_ta_proc_tupa obj = new orpro_ta_proc_tupa();
        obj.setIn_codigo_det(Integer.parseInt(in_codigo_det) );
        obj.setDe_tramitacion_porc( Integer.parseInt(de_tramitacion_porc) );
        obj.setDe_tramitacion_sol( Integer.parseInt(de_tramitacion_sol) );
        obj.setIn_plazo_resolver_dias( Integer.parseInt(in_plazo_resolver_dias) );
        obj.setVc_inicio_procedimiento(vc_inicio_procedimiento.trim());
        obj.setIn_autoridad_com_resolver( Integer.parseInt(in_autoridad_com_resolver) );
        obj.setVc_reconsideracion(vc_reconsideracion.trim());
        obj.setVc_apelacion(vc_apelacion.trim());
        obj.setCh_evaluacion_previa(evaluacion_previa);
        obj.setCh_calif_automa(automatico);

//        System.out.println("in_codigo_det "+in_codigo_det);
//        System.out.println("de_tramitacion_porc "+de_tramitacion_porc);
//        System.out.println("de_tramitacion_sol "+de_tramitacion_sol);
//        System.out.println("in_plazo_resolver_dias "+in_plazo_resolver_dias);
//        System.out.println("vc_inicio_procedimiento "+vc_inicio_procedimiento);
//        System.out.println("in_autoridad_com_resolver "+in_autoridad_com_resolver);
//        System.out.println("vc_reconsideracion "+vc_reconsideracion);
//        System.out.println("vc_apelacion "+vc_apelacion);
//        System.out.println("CALIFICACION!");
//        System.out.println("evaluacion_previa: "+evaluacion_previa);
//        System.out.println("automatico: "+automatico);


        HttpSession sesion = request.getSession();
        String id = sesion.getAttribute("xid").toString();
        String nom = sesion.getAttribute("xnomus").toString();

        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_proc_tupa_DAO dao = new orpro_ta_proc_tupa_DAO(dataSource);

        PrintWriter writer;
        try {
           writer =response.getWriter();
            if(dao.guardarTupaSubDet(obj,nom,id ) )
            {
               writer.print("1");
            } else
            {
                writer.print("0");
            }
             writer.flush();
             writer.close();
        } catch (IOException ex) {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger(Tupa_SubDetalleInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapping.findForward(SUCCESS);
    }
}
