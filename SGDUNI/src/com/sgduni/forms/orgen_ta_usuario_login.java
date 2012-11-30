/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author marco
 */
public class orgen_ta_usuario_login extends org.apache.struts.action.ActionForm
{
    private String vc_usuario;
    private String vc_clave;
    private String mensaje;

    public orgen_ta_usuario_login() {
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.vc_usuario = "";
        this.vc_clave = "";
        this.mensaje = "";
    }
    
//    @Override
//    public ActionErrors validate(
//            ActionMapping mapping,
//            HttpServletRequest request)
//    {
//       ActionErrors errors = new ActionErrors();
//       testString(getVc_usuario(), "vc_usuario", "usuario.requerido", errors);
//       testString(getVc_clave(), "vc_clave", "clave.requerido", errors);
//       return errors;
//    }

    private void testString(String valor,String campo,String claveErr,ActionErrors errores)
    {
        if( valor == null || valor.length() == 0 )
                errores.add(campo, new ActionMessage(claveErr));
    }

    public String getVc_clave() {
        return vc_clave;
    }

    public void setVc_clave(String vc_clave) {
        this.vc_clave = vc_clave;
    }

    public String getVc_usuario() {
        return vc_usuario;
    }

    public void setVc_usuario(String vc_usuario) {
        this.vc_usuario = vc_usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
