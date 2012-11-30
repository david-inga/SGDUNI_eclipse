package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orpro_oficio_circular;
import com.sgduni.forms.orpro_ta_observacion_oficio;
import com.sgduni.utilitarios.GestorDeEmails;
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
 * @author Programmer : Marco A. Estrella Cardenas
 *
 * Permite Almacenar las observaciones que se hacen a un oficio
 */
public class Oficio_Circular_Observacion_almacenar extends org.apache.struts.action.Action
{
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        
        int in_codigo_oficio = Integer.parseInt(request.getParameter("in_codigo_oficio"));
        String vc_observacion = request.getParameter("vc_observacion");

        HttpSession session=request.getSession(true);
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        //variable
        int idUser = Integer.parseInt(session.getAttribute("xid").toString());
        String xnombreUsu = session.getAttribute("xnomus").toString();
        String xnomdepen_facul = session.getAttribute("xnomdepen_facul").toString();
        int xcodcargo = Integer.parseInt(session.getAttribute("xcodcargo").toString());

        orpro_oficio_circular_DAO daoOficio = new orpro_oficio_circular_DAO(dataSource);
        orpro_oficio_circular oficio = daoOficio.getOficioParaModificar(in_codigo_oficio);
        
        orpro_ta_observacion_oficio obs_oficio;

        String tipoUsusario = " ";

        if(xcodcargo == 1)
        {
          tipoUsusario = "creador";
        }
        else if(xcodcargo == 2)
        {
          tipoUsusario = "emisor";
        }

        GestorDeEmails gestor = new GestorDeEmails();
        orgen_ta_usuario_DAO daoUsu = new orgen_ta_usuario_DAO(dataSource);       

        PrintWriter writer;

        try
        {
            obs_oficio = new orpro_ta_observacion_oficio();

            obs_oficio.setIn_codigo_oficio(in_codigo_oficio);
            obs_oficio.setVc_observacion(vc_observacion);
            obs_oficio.setVc_nombre_usuario(xnombreUsu);

            String correo = daoUsu.getCorreoElectronicoUsuarioSegunIDOFICIO(in_codigo_oficio,tipoUsusario );
            String cuerpo = gestor.cargarMensaje(xnomdepen_facul, xnombreUsu ,
            "Saludos. <br/> Mediante la Presente se le comunica que usted <br/> acaba de recibir una observación <br/> al documento <strong>"+oficio.getCh_codigo_oficio()+"</strong> que esta tramitando.");

            writer = response.getWriter();

            if( daoOficio.crearObservacionOficio(obs_oficio, idUser) )
            {
               gestor.enviarCorreoElectronico(correo, "Tramite Oficio Circular", cuerpo);
               writer.print("1");
            }
            else
            {
                writer.print("0");
            }

             writer.flush();
             writer.close();
        }
        catch (IOException ex)
        {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger( Oficio_Circular_Observacion_almacenar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
