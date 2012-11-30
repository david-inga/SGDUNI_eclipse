/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orpro_oficio_circular;
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
 * @author Sistemas
 */
public class Oficio_Circular_Listar extends org.apache.struts.action.Action
{
    
    private String LISTADO = null;

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        
     HttpSession sesion =  request.getSession();
     String idROL      =  sesion.getAttribute("xrol").toString().trim();
     String cargo = sesion.getAttribute("xcodcargo").toString().trim();;

     String idDepFacu   =  sesion.getAttribute("xiddepen_facul").toString().trim();
     String tipDepFac   =  sesion.getAttribute("xtipodepen_facul").toString().trim();

     DataSource dataSource = getDataSource(request, "DSconnection");
     orpro_oficio_circular_DAO dao = new orpro_oficio_circular_DAO(dataSource);

     //nump = numero pendiente de oficios
     //este parametro viene del boton(FACEBOOk) con valor 1
     // si precionan el boton del submenu sería NULL
     //SI NUMP es difernete a CERO o igual a 1 sera el mismo valor SINO  sera CERO
     String nump = ( request.getParameter("nump")!=null ) ? request.getParameter("nump") : "0";

     String validar_boton_editar = "1";
     String titulo = "";
     String validar_campo_tiempo = "0";
     //System.out.println("el rol es "+idROL);
     //System.out.println("num = "+nump);

     if(nump.equals("1"))
     {
         //28 - OFICIO PENDIENTE
          if( idROL.toUpperCase().equals("ROL02") || idROL.toUpperCase().equals("ROL03") )
          {//NIVEL DE USUARIO "USUARIO"
              //lista de oficio enviados pendientes segun usuario
              ArrayList<orpro_oficio_circular> listaOficios = dao.getListaDeOficiosEnviadosPendientesSegunUsuario(idDepFacu, tipDepFac);
              request.setAttribute("oficios",listaOficios);
              validar_campo_tiempo = "1";
          }
          else
          {
              //lista de oficio enviados pendientes totales
              ArrayList<orpro_oficio_circular> listaOficios = dao.getListaTotalOficiosPendientes();
              request.setAttribute("oficios",listaOficios);
              validar_campo_tiempo = "1";
          }
          LISTADO = "listado_pendiente";
     }
     else if(nump.equals("2"))
     {
         //27 - HISTORIAL DE ENVIADOS
          //lista de oficio enviados solo para la ocdo
          ArrayList<orpro_oficio_circular> listaOficios = dao.getListaOficiosEnviadosOCDO();
          validar_boton_editar = "6";
          titulo = "Historial de Oficios Circulares Enviados";
          request.setAttribute("oficios",listaOficios);
          LISTADO = "listado";
     }
     else if(nump.equals("3"))
     {
       if(idROL.toUpperCase().equals("ROL01") )
       {
           if(cargo.equals("1"))
           {
             ArrayList<orpro_oficio_circular> listaOficios = dao.getListaOficiosEnviadosAlJefeOCDO();
             validar_boton_editar = "2";
             titulo = "Lista de Oficios Enviados por la Secretaria";
             request.setAttribute("oficios",listaOficios);
             LISTADO = "listado";
           }
           else if(cargo.equals("2"))
           {
             ArrayList<orpro_oficio_circular> listaOficios = dao.getListaOficiosEnviadosAlJefeOCDO();
             titulo = "Lista de Oficios Enviados al Jefe(a) OCDO ";
             validar_boton_editar = "3";
             request.setAttribute("oficios",listaOficios);
             LISTADO = "listado";
           }
       }
     }
     else if(nump.equals("4"))
     {
       if(idROL.toUpperCase().equals("ROL01") )
       {
           if(cargo.equals("1"))
           {
             ArrayList<orpro_oficio_circular> listaOficios = dao.getListaOficiosAprobadosProElJefeOCDO();
             validar_boton_editar = "4";
             titulo = "Lista de Oficios Aprobados por mí";
             request.setAttribute("oficios",listaOficios);
             LISTADO = "listado";
           }
           else if(cargo.equals("2"))
           {
             ArrayList<orpro_oficio_circular> listaOficios = dao.getListaOficiosAprobadosProElJefeOCDO();
             titulo = "Lista de Oficios Aprobados por el Jefe(a) OCDO ";
             validar_boton_editar = "5";
             request.setAttribute("oficios",listaOficios);
             LISTADO = "listado";
           }
       }
     }
     else if(nump.equals("5"))
     {
         //28 - OFICIO PENDIENTE
          if( idROL.toUpperCase().equals("ROL01") )
          {//NIVEL DE USUARIO "USUARIO"
              //lista de oficio enviados pendientes segun usuario
              ArrayList<orpro_oficio_circular> listaOficios = dao.getListaDeOficiosenTramiteRevisados();
              request.setAttribute("oficios",listaOficios);
          }
          validar_campo_tiempo = "0";
          LISTADO = "listado_pendiente";
     }
     else
     {// 26 - guardados
      //obtiene la lista de oficios que no se han enviado, es para la modificación
      ArrayList<orpro_oficio_circular> listaOficios = dao.getListaOficiosGuardados(Integer.parseInt(idDepFacu),tipDepFac );
      request.setAttribute("oficios",listaOficios);
      titulo = "Lista de Oficios Circulares Guardados";
      validar_boton_editar = "1";
      LISTADO = "listado";
     }

       request.setAttribute("validar_campo_tiempo", validar_campo_tiempo);
       request.setAttribute("validarBoton", validar_boton_editar);
       request.setAttribute("titulo", titulo);
       return mapping.findForward(LISTADO);
    }
}
