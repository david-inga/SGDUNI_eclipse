package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_observaciones_rof_DAO;
import com.sgduni.dao.orpro_ta_rof_DAO;
import com.sgduni.forms.orpro_ta_observaciones_rof;
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
 *
 * @author pc
 */
public class ROF_observaciones_insertar_Guardar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession session=request.getSession(true);
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        //variable
        int idUser=Integer.parseInt(session.getAttribute("xid").toString());
        int in_codigo_rof=Integer.parseInt(request.getParameter("in_codigo_rof").toString());
        String vc_observacion=request.getParameter("vc_observacion").toString();

        int idEstado=0;

        String idROL=(String)session.getAttribute("xrol");
        String ckaprobar=(request.getParameter("ckaprobar")!=null && request.getParameter("ckaprobar").equals("1"))?request.getParameter("ckaprobar"):"0";

        orpro_ta_observaciones_rof obj = (orpro_ta_observaciones_rof)form;
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_observaciones_rof_DAO dao = new orpro_ta_observaciones_rof_DAO(dataSource);

        obj.setIn_codigo_rof(in_codigo_rof);
        obj.setVc_observacion(vc_observacion);
        PrintWriter writer;
        String estado = null;

        try
        {
                writer =response.getWriter();
                if(idROL.trim().toUpperCase().equals("ROL01"))
                {
                    idEstado=38;//RECHAZADO OCDO
                    if(ckaprobar.equals("1"))
                         idEstado=33;//aprobado OCO
                    try
                    {
                        if(dao.guardarObservacionRofparaOCDO(obj, idUser, idEstado))
                        {
                           estado = "1";
                        }
                        else
                        {
                            estado = "0";
                        }
                    }
                    catch (Exception ex)
                    {
                         estado = "0";
                    }
                }
                else if(idROL.trim().toUpperCase().equals("ROL02"))
                {
                    try
                    {
                        if(dao.guardarObservacionRo(obj, idUser))
                        {
                           estado = "1";
                        }
                        else
                        {
                            estado = "0";
                        }
                    }
                    catch (Exception ex)
                    {
                        estado = "0";
                    }
                }
                else if(idROL.trim().toUpperCase().equals("ROL03"))
                {
                    idEstado=39;//se va para revisión del usuario
                    if(ckaprobar.equals("1"))
                         idEstado=35;//APROBACION ASESORIA LEGAL

                     try
                     {
                        if(dao.guardarObservacionRofparaALEGAL(obj, idUser, idEstado) )
                        {
                            estado = "1";
                        }
                        else
                        {
                            estado = "0";
                        }
                    }
                    catch (Exception ex)
                    {
                        estado = "0";
                    }
                }
                else if(idROL.trim().toUpperCase().equals("ROL04"))
                {
                    idEstado=40;//RECHAZADO PRO EL RECTOR
                    if(ckaprobar.equals("1"))
                         idEstado=37;//APROBACION RECTOR

                    try
                    {
                        if(dao.guardarObservacionRofparaALEGAL(obj, idUser, idEstado) )
                        {
                            estado = "1";
                        }
                        else
                        {
                             estado = "0";
                        }
                    }
                    catch (Exception ex)
                    {
                         estado = "0";
                    }
                }
                System.out.println("el estado es "+estado);
                writer.print(estado);
                writer.flush();
                writer.close();
        }
        catch(IOException ex)
        {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger(ROF_observaciones_insertar_Guardar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;//mapping.findForward(SUCCESS);
    }
}
