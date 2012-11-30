/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions.ajax;

import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.forms.orgen_ta_usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * @author Marco
 */
public class CargarComboboxReceptor extends org.apache.struts.action.Action {
  private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        System.out.println("inicio CargarComboboxReceptor");
        HttpSession sesion = request.getSession();
        String nombre = sesion.getAttribute("xnomus").toString();
        String id = sesion.getAttribute("xid").toString();

        int in_codigo_depen_facu = Integer.parseInt( request.getParameter("in_codigo_depen_facu") );
        String ch_tipo_depen_facu = request.getParameter("ch_tipo_depen_facu").toString();

        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        String codigo = request.getParameter("xcod");
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_usuario_DAO daoUsuario = new orgen_ta_usuario_DAO(dataSource);
        ArrayList<orgen_ta_usuario> ListaUsuario = daoUsuario.getListaUsuariosSegunFacDepen(in_codigo_depen_facu ,ch_tipo_depen_facu );
        
        PrintWriter writer;
        try
        {
           writer =response.getWriter();

           writer.print("[");

           int con=1;

           if(ListaUsuario.size()>0)
           {
               for( orgen_ta_usuario usuario : ListaUsuario )
               {
                  writer.print("{'codigo':'"+usuario.getIn_codigo_usuario()+"','nombre':'"+usuario.getVc_usuario()+" - "+usuario.getVc_cargo()+"' }");
                  if( con < ListaUsuario.size() )
                      writer.print(" , ");
                  con++;
               }
           }

           writer.print(" ]");
           writer.flush(); 
           writer.close();
           
        }
        catch (IOException ex)
        {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger(CargarComboboxReceptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
