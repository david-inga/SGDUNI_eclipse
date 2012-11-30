

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_rof_DAO;
import com.sgduni.forms.orpro_ta_rof_funciones_generales;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author pc
 */
public class ROF_insertarFuncionesGenerales_Guardar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession sesion = request.getSession();
        String idVersion = sesion.getAttribute("xidVersionROF").toString();

        //System.out.println("guardando la funcion general");
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        //variables request
        String id_rof=request.getParameter("id_rof");
        String descripcion=request.getParameter("descripcion").toString();
        String orden=request.getParameter("orden");

        DataSource dataSource = getDataSource( request , "DSconnection");
        orpro_ta_rof_DAO daoRof = new orpro_ta_rof_DAO(dataSource);

        orpro_ta_rof_funciones_generales obj = new orpro_ta_rof_funciones_generales();
        obj.setIn_codigo_rof(Integer.parseInt(id_rof) );
        obj.setCh_orden(orden);
        obj.setVc_descripcion(descripcion);
        obj.setIn_codigo_version(Integer.parseInt(idVersion) );

        boolean estado = daoRof.guardarFuncionGeneral(obj);
        PrintWriter writer;
        try
        {
           writer =response.getWriter();
           if(estado)
               writer.print("1");
           else
               writer.print("0");
             writer.flush();
             writer.close();
        }
        catch (IOException ex)
        {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger(ROF_insertarFuncionesGenerales_Guardar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
