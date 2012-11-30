package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.dao.orgen_ta_herramientas_DAO;
import com.sgduni.forms.orgen_ta_usuario;
import com.sgduni.forms.orgen_ta_herramientas;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * @author Luis Alcon y Marco Estrella
 * ADAPTADO PARA MYSQL
 * REVISADO
 */
public class LoginAction extends org.apache.struts.action.Action
{
    private final static String FORWARD_ok = "ok";
    private final static String FORWARD_error = "error";
    private String mensaje_login;

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_usuario_DAO dao = new orgen_ta_usuario_DAO(dataSource);
        orgen_ta_usuario obj = (orgen_ta_usuario)form;
        ActionForward forward = mapping.findForward("login");
        String usuario = obj.getVc_usuario();
        String clave = obj.getVc_clave();
        try
        {
            if(dao.logear(usuario, clave))
            {
               
                HttpSession xsession = request.getSession(true);
                xsession.setAttribute("xid", dao.getIn_codigo_usuario());//id del usuario
                xsession.setAttribute("xnomus", dao.getVc_nombre_usuario());//nombres y apellidos del usuario               
                xsession.setAttribute("xrol",  dao.getIn_codigo_rol());//id rol del usuario
                xsession.setAttribute("xnomrol",  dao.getVc_nombre_rol());//nombre del rol
                xsession.setAttribute("xiddepen_facul",dao.getIn_codigo_depen_facul());//id de la facultad o dependencia
                xsession.setAttribute("xnomdepen_facul", dao.getVc_nom_depen_facul().trim());//nombre de la facultad o dependencia
                xsession.setAttribute("xtipodepen_facul", dao.getVc_tipo_depen_facul().trim());//tipo (facultad o dependencia)
                xsession.setAttribute("xcodcargo",dao.getIn_codigo_cargo() );
                orgen_ta_herramientas_DAO daoHer = new orgen_ta_herramientas_DAO(dataSource);
                ArrayList<orgen_ta_herramientas> listaHerramienta = daoHer.getHerramientasSegunProtocolo(dao.getIn_codigo_rol());
                xsession.setAttribute("listaHerramienta", listaHerramienta);                
                forward = mapping.findForward(FORWARD_ok);
            }
            else
            {
                obj.setMensaje("Error! Usuario o contraseña incorrectos");                
                forward = mapping.findForward(FORWARD_error);
            }
        }
        catch(Exception e)
        {
             obj.setMensaje("Error! Usuario o contraseña incorrectos");             
             forward = mapping.findForward(FORWARD_error);
        }       
        
        return forward;
    }
}
