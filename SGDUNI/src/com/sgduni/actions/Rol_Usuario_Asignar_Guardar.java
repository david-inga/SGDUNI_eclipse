package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_usuario_rol_fun_DAO;
import com.sgduni.forms.orgen_ta_usuario_rol_fun;
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
public class Rol_Usuario_Asignar_Guardar extends org.apache.struts.action.Action
{
    public ActionForward execute
            (
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response
            )
            throws Exception
    {
       DataSource dataSource = getDataSource(request, "DSconnection");

       orgen_ta_usuario_rol_fun_DAO dao;
       orgen_ta_usuario_rol_fun obj;

       int int_usuario_codigo = Integer.parseInt(request.getParameter("in_codigo_usuario"));
       int num_item=Integer.parseInt(request.getParameter("txtnumitem_rol"));
       //System.out.println("el valor Usuario es: "+int_usuario_codigo);
       dao = new orgen_ta_usuario_rol_fun_DAO(dataSource);
       dao.eliminarRolUsuario(int_usuario_codigo);
       int  numError=0;
       String error="0";
       for(int i = 1;i<=num_item;i++)
       {
        String nombreParametro = "ch_codigo_rol_"+i;
        String ch_codigo_rol = request.getParameter(nombreParametro);
        //System.out.println("el valor del param"+i+" es: "+ch_codigo_rol);
        if(ch_codigo_rol!=null)
        {
           dao = new orgen_ta_usuario_rol_fun_DAO(dataSource);
           obj = new orgen_ta_usuario_rol_fun();
           obj.setIn_usuario_codigo(int_usuario_codigo);
           obj.setCh_codigo_rol(ch_codigo_rol.trim());
           if(!dao.guardarRol(obj))
                 numError++;

        }       
       }

       if(numError>0){
           error="0";
       }else{
          error="1";
       }

        response.sendRedirect("asignarRolUsuario.uni?coduser="+int_usuario_codigo+"&error="+error);
        return null;
        //return mapping.findForward("lista");
    }
}
