package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * @author Marco
 */
public class orgen_ta_organo extends org.apache.struts.action.ActionForm {
    
    private Integer in_codigo_organo;
    private String vc_nombre;
    private String vc_descripcion;
    private String ch_estado;
    private String vc_usuario_crea;
    private String dt_fecha_crea;
    private String vc_usuario_modifica;
    private String dt_usuario_modifica;
    private String mensaje;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.in_codigo_organo = 0;
        this.vc_nombre = "";
        this.vc_descripcion = "";
        this.ch_estado = "";
        this.vc_usuario_crea = "";
        this.dt_fecha_crea = "";
        this.vc_usuario_modifica = "";
        this.dt_usuario_modifica = "";
        this.mensaje = "";
    }

    public String getCh_estado() {
        return ch_estado;
    }

    public void setCh_estado(String ch_estado) {
        this.ch_estado = ch_estado;
    }

    public String getDt_fecha_crea() {
        return dt_fecha_crea;
    }

    public void setDt_fecha_crea(String dt_fecha_crea) {
        this.dt_fecha_crea = dt_fecha_crea;
    }

    public String getDt_usuario_modifica() {
        return dt_usuario_modifica;
    }

    public void setDt_usuario_modifica(String dt_usuario_modifica) {
        this.dt_usuario_modifica = dt_usuario_modifica;
    }

    public Integer getIn_codigo_organo() {
        return in_codigo_organo;
    }

    public void setIn_codigo_organo(Integer in_codigo_organo) {
        this.in_codigo_organo = in_codigo_organo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getVc_descripcion() {
        return vc_descripcion;
    }

    public void setVc_descripcion(String vc_descripcion) {
        this.vc_descripcion = vc_descripcion;
    }

    public String getVc_nombre() {
        return vc_nombre;
    }

    public void setVc_nombre(String vc_nombre) {
        this.vc_nombre = vc_nombre;
    }

    public String getVc_usuario_crea() {
        return vc_usuario_crea;
    }

    public void setVc_usuario_crea(String vc_usuario_crea) {
        this.vc_usuario_crea = vc_usuario_crea;
    }

    public String getVc_usuario_modifica() {
        return vc_usuario_modifica;
    }

    public void setVc_usuario_modifica(String vc_usuario_modifica) {
        this.vc_usuario_modifica = vc_usuario_modifica;
    }
}
