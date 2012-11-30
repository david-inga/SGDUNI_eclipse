/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_usuario_cargo_DAO;
import com.sgduni.forms.orgen_ta_usuario_cargo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Gerald
 */
public class Usuario_Cargo_Guardar extends org.apache.struts.action.Action
{ 
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
       DataSource dataSource = getDataSource(request, "DSconnection");

       orgen_ta_usuario_cargo_DAO dao;
       orgen_ta_usuario_cargo obj;

       int int_usuario_codigo = Integer.parseInt(request.getParameter("in_codigo_usuario"));
       int num_item = Integer.parseInt(request.getParameter("txtnumitem_cargo"));

       System.out.println("el valor Usuario es: "+int_usuario_codigo);

       dao = new orgen_ta_usuario_cargo_DAO(dataSource);
       dao.eliminarCargoDelUsuario(int_usuario_codigo);

       int  numError = 0;
       String error = "0";

       for(int i = 1; i<= num_item; i++)
       {
            String nombreParametro = "in_codigo_cargo_"+i;

            String in_codigo_cargo = request.getParameter(nombreParametro);

            if(in_codigo_cargo != null)
            {
               dao = new orgen_ta_usuario_cargo_DAO(dataSource);
               obj = new orgen_ta_usuario_cargo();

               obj.setIn_codigo_usuario(int_usuario_codigo);
               obj.setIn_codigo_cargo(Integer.parseInt( in_codigo_cargo) );

               if( !dao.guardarCargodelUsuario(obj) )
                  numError++;
            }
       }

       if(numError>0)
       {
           error="0";
       }
       else
       {
          error="1";
       }

        response.sendRedirect("SeleccionarCargoParaUsuarios.uni?coduser="+int_usuario_codigo+"&error="+error);
        return null;
    }
}
