package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/*
 * @author marco
 */
public class orgen_ta_usuario_rol_fun extends org.apache.struts.action.ActionForm {
    
    private String ch_codigo_rol;
    private Integer in_usuario_codigo;
    private String ch_codigo_compania;
    private String mensaje;

    public orgen_ta_usuario_rol_fun() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.ch_codigo_rol = "";
        this.in_usuario_codigo = 0;
        this.ch_codigo_compania = "";
        this.mensaje = "";
    }

    public String getCh_codigo_compania() {
        return ch_codigo_compania;
    }

    public void setCh_codigo_compania(String ch_codigo_compania) {
        this.ch_codigo_compania = ch_codigo_compania;
    }

    public String getCh_codigo_rol() {
        return ch_codigo_rol;
    }

    public void setCh_codigo_rol(String ch_codigo_rol) {
        this.ch_codigo_rol = ch_codigo_rol;
    }

    public Integer getIn_usuario_codigo() {
        return in_usuario_codigo;
    }

    public void setIn_usuario_codigo(Integer in_usuario_codigo) {
        this.in_usuario_codigo = in_usuario_codigo;
    }
}
