package com.sgduni.actions.ajax;

import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orgen_ta_usuario;
import com.sgduni.forms.orpro_oficio_circular;
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
 */
public class Oficio_aprobar_por_la_jefe_ocdo extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession session=request.getSession();
        int idUser=Integer.parseInt(session.getAttribute("xid").toString());
         String xnombreUsu = session.getAttribute("xnomus").toString();
        String xnomdepen_facul = session.getAttribute("xnomdepen_facul").toString();

        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        int in_cod_oficio = Integer.parseInt(request.getParameter("in_cod_oficio").toString());
        int in_estado = Integer.parseInt(request.getParameter("in_estado").toString());

        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_oficio_circular_DAO daoOficio = new orpro_oficio_circular_DAO(dataSource);

        //para enviar correo
        GestorDeEmails gestor = new GestorDeEmails();
        
        orpro_oficio_circular oficio = daoOficio.getOficioParaModificar(in_cod_oficio);

        orgen_ta_usuario_DAO usudao = new orgen_ta_usuario_DAO(dataSource);
        orgen_ta_usuario usuario = usudao.getUsuario2(oficio.getIn_codigo_usuario());
        String correo = usuario.getVc_correo();

        PrintWriter writer;

        try
        {
            String cuerpo = gestor.cargarMensaje(xnomdepen_facul, xnombreUsu ,
            "Saludos. <br/> Mediante la Presente se le comunica que el Oficio <strong>"+oficio.getCh_codigo_oficio()+"</strong> acaba de ser Aprobado, <br/> Por favor continue con el tramite.");

            writer =response.getWriter();
            if(daoOficio.aprobarOficioProOcdo(in_cod_oficio, in_estado,idUser))
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
            Logger.getLogger(Oficio_aprobar_por_la_jefe_ocdo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapping.findForward(SUCCESS);
    }
}
