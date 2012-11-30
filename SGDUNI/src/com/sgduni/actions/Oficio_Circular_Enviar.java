/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orgen_ta_usuario_DAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orpro_oficio_circular;
import com.sgduni.utilitarios.GestorDeEmails;
import java.util.ArrayList;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Sistemas
 */
public class Oficio_Circular_Enviar extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String ENVIAR = "enviado";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession session=request.getSession();
        int idUser = Integer.parseInt(session.getAttribute("xid").toString());
        String xnombreUsu = session.getAttribute("xnomus").toString();
        String xnomdepen_facul = session.getAttribute("xnomdepen_facul").toString();

        DataSource dataSource = getDataSource(request, "DSconnection");

        //DAO
        orgen_ta_dependencia_DAO daoDep = new orgen_ta_dependencia_DAO(dataSource);
        orgen_ta_facultad_DAO daoFac = new orgen_ta_facultad_DAO(dataSource);

        //array
        ArrayList<orgen_ta_dependencia> listaDependencia = daoDep.getDependencias();
        ArrayList<orgen_ta_facultad> listaFacultad = daoFac.getFacultades();

        orpro_oficio_circular_DAO dao = new orpro_oficio_circular_DAO(dataSource);
        orpro_oficio_circular objForm = (orpro_oficio_circular)form;

        int NumFacultad = Integer.parseInt(request.getParameter("txtNumFacultad"));
        int NumDependecia = Integer.parseInt(request.getParameter("txtNumDependecia"));

       Integer [] listaFacultades = new Integer[NumFacultad];
       Integer [] listaReceptoresFac = new Integer[NumFacultad];
       Integer [] listaDependencias = new Integer[NumDependecia];
       Integer [] listaReceptoresDep = new Integer[NumDependecia];

       for(int fac=0;fac < NumFacultad;fac++)
       {//LISTA DE FACULTADES
           String  codFacultad = ( request.getParameter("ckFacultad["+fac+"]") != null && Integer.parseInt(request.getParameter("ckFacultad["+fac+"]").toString()) > 0) ? request.getParameter("ckFacultad["+fac+"]") : "0";
           String  codReceptor = ( request.getParameter("receptorFac["+fac+"]") != null && Integer.parseInt(request.getParameter("receptorFac["+fac+"]").toString()) > 0) ? request.getParameter("receptorFac["+fac+"]") : "0";
           listaFacultades[fac] = Integer.parseInt(codFacultad);
           listaReceptoresFac[fac] = Integer.parseInt(codReceptor);
       }   
      
       for(int dep=0;dep < NumDependecia;dep++)
       {//LISTA DE DEPENDECIAS
           String  codDependencia = ( request.getParameter("ckDependecia["+dep+"]") != null && Integer.parseInt(request.getParameter("ckDependecia["+dep+"]").toString()) > 0) ? request.getParameter("ckDependecia["+dep+"]") : "0";
           String  codReceptorDep = ( request.getParameter("receptorDep["+dep+"]") != null && Integer.parseInt( request.getParameter("receptorDep["+dep+"]").toString()) > 0) ? request.getParameter("receptorDep["+dep+"]") : "0";
           listaDependencias [dep] = Integer.parseInt(codDependencia);
           listaReceptoresDep[dep] = Integer.parseInt(codReceptorDep);
       }

       orgen_ta_usuario_DAO daoUsu = new orgen_ta_usuario_DAO(dataSource);

        //cargando correos de las dependencias seleccionadas
        String [] ListaDeCorreoFacultad = new String[listaReceptoresFac.length];
        for(int i = 0; i < listaReceptoresFac.length;i++ ){
            ListaDeCorreoFacultad[i] = daoUsu.getUsuario2(listaReceptoresFac[i]).getVc_correo();
        }

       //cargando correos de las facultades seleccionadas
       String [] ListaDeCorreoDependencia = new String[listaReceptoresDep.length];
       for(int i = 0 ; i < listaReceptoresDep.length;i++ ){
            ListaDeCorreoDependencia[i] = daoUsu.getUsuario2(listaReceptoresDep[i]).getVc_correo();
        }

       //copiando los datos de la lista de correos de faucltasd y dependnecia en un solo array
       String ListaCorreosTotal [] = new String[ListaDeCorreoDependencia.length+ListaDeCorreoFacultad.length];

       if( ListaDeCorreoDependencia.length > 0 ){
       System.arraycopy(ListaDeCorreoDependencia, 0, ListaCorreosTotal, 0, ListaDeCorreoDependencia.length);
       }
       if(ListaDeCorreoFacultad.length > 0){
       System.arraycopy(ListaDeCorreoFacultad, 0, ListaCorreosTotal, ListaDeCorreoDependencia.length, ListaDeCorreoFacultad.length);
       }

       //creando un arraylist para cargar los email con datos
       ArrayList<String> listaCorreoParaEnviar = new ArrayList<String>();
       for( int i = 0; i < ListaCorreosTotal.length;i++ )
       {
          if( ListaCorreosTotal[i] != null)
              listaCorreoParaEnviar.add(ListaCorreosTotal[i]);
       }

       GestorDeEmails gestor = new GestorDeEmails();
       String cuerpo = gestor.cargarMensaje(xnomdepen_facul, xnombreUsu ,"Saludos. <br/> Mediante la Presente se le comunica que usted <br/> acaba de recibir un Oficio Circular <br/> Por favor Revíselo.");

        if(dao.enviarOficio(objForm, idUser,listaFacultades,listaDependencias,listaReceptoresFac,listaReceptoresDep))
        {
           gestor.enviarCorreoElectronicoMuchosReceptores(listaCorreoParaEnviar, "Tramite Oficio Circular", cuerpo);

           objForm.setMensaje("Oficio Circular Enviado Correctamente");
           request.setAttribute("mensaje_de_exito", "El Oficio Circular fue Enviado Correctamente");
           request.setAttribute("in_codigo_oficio", objForm.getIn_codigo_oficio());
           request.setAttribute("ch_codigo_oficio", objForm.getCh_codigo_oficio());
        } else
        {
           objForm.setMensaje("Error al Enviar el Oficio Circular");
           request.setAttribute("mensaje_de_exito", "Error! lo sentimos pero no se pudo Enviar el Oficio");
        }

       request.setAttribute("dependencias", listaDependencia);
       request.setAttribute("facultad", listaFacultad);
       return mapping.findForward( ENVIAR );
    }
}
