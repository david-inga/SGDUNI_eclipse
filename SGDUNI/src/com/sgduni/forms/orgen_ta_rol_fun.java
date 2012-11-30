package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 * @author marco
 */
public class orgen_ta_rol_fun extends org.apache.struts.action.ActionForm {
    
    private String ch_codigo_rol;
    private String ch_codigo_funcionalidad;
    private Integer in_codigo_acceso_usuario;
    private String ch_default;
    private String mensaje;

    @Override
    public void reset(ActionMapping mapping,
            HttpServletRequest request)
    {
        this.ch_codigo_rol = "";
        this.ch_codigo_funcionalidad = "";
        this.in_codigo_acceso_usuario = 0;
        this.ch_default = "";
        this.mensaje = "";
    }

    public String getCh_codigo_funcionalidad() {
        return ch_codigo_funcionalidad;
    }

    public void setCh_codigo_funcionalidad(String ch_codigo_funcionalidad) {
        this.ch_codigo_funcionalidad = ch_codigo_funcionalidad;
    }

    public String getCh_codigo_rol() {
        return ch_codigo_rol;
    }

    public void setCh_codigo_rol(String ch_codigo_rol) {
        this.ch_codigo_rol = ch_codigo_rol;
    }

    public String getCh_default() {
        return ch_default;
    }

    public void setCh_default(String ch_default) {
        this.ch_default = ch_default;
    }

    public Integer getIn_codigo_acceso_usuario() {
        return in_codigo_acceso_usuario;
    }

    public void setIn_codigo_acceso_usuario(Integer in_codigo_acceso_usuario) {
        this.in_codigo_acceso_usuario = in_codigo_acceso_usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
