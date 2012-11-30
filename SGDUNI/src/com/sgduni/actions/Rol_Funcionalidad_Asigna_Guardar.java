/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_rol_fun_DAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Sistemas
 */
public class Rol_Funcionalidad_Asigna_Guardar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String ASIGNAR = "asignar";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        //CONEXION DB
        DataSource dataSource = getDataSource(request, "DSconnection");

       //VARIABLES
       String CodRol=request.getParameter("ch_codigo_rol");//Codigo del rol
       String CodHerr=request.getParameter("ch_codigo_herramienta");//codigo de la herramienta
       int numItemFuncionalidad=Integer.parseInt(request.getParameter("txtnumitem_funcionalidades"));
       int numError=0;
       String txtError="Datos guardados correctamente";

       //DAO ROL - FUNCIONALIDAD
       orgen_ta_rol_fun_DAO dao = new orgen_ta_rol_fun_DAO(dataSource);
       
       if((CodRol!=null && CodHerr!=null) || (CodRol!="0" && CodHerr!="0"))
       {//Verificamos si existen datos
           //Eliminamos todas las funcionalidad por rol
           dao.eliminarRolFuncionalidad(CodRol,CodHerr);
           for(int i=1;i<=numItemFuncionalidad;i++)
           {//Revorremos los Item seleccionados
             String codFuncionalidad=request.getParameter("ch_codigo_funcionalidad_"+i);
                if(codFuncionalidad!=null)
                {
                    if(!dao.guardarRolFuncionalidad(CodRol, codFuncionalidad))
                        numError++;
                        //System.out.println("Num Item"+String.valueOf(i)+"::"+codFuncionalidad);

                }
           }

           //Verificamos si existio algun error
              if(numError>0)
                   txtError="Ocurrio un error al intentar guardar los datos";
       }
       response.sendRedirect("asignarFunRol.uni?codrol="+CodRol+"&codherr="+CodHerr+"&err="+txtError);
       return null;
                //mapping.findForward(ASIGNAR);
    }
}
