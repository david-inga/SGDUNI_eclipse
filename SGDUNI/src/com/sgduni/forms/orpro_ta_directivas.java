package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author marco
 */
public class orpro_ta_directivas extends org.apache.struts.action.ActionForm {
    
    private Integer in_codigo_directiva;
    private String ch_codigo_directiva;
    private String dt_fecha;
    private String vc_alcance;
    private String vc_responsabilidad;
    private String ch_estado;
    private String nombre_estado;
    private Integer in_codigo_usuario;
    private String mensaje;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.in_codigo_directiva = 0;
        this.ch_codigo_directiva = "";
        this.dt_fecha = "";
        this.vc_alcance = "";
        this.vc_responsabilidad = "";
        this.ch_estado = "";
        this.in_codigo_usuario = 0;
        this.mensaje = "";
        this.nombre_estado = "";
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    

    public String getCh_codigo_directiva() {
        return ch_codigo_directiva;
    }

    public void setCh_codigo_directiva(String ch_codigo_directiva) {
        this.ch_codigo_directiva = ch_codigo_directiva;
    }

    public String getCh_estado() {
        return ch_estado;
    }

    public void setCh_estado(String ch_estado) {
        this.ch_estado = ch_estado;
    }

    public String getDt_fecha() {
        return dt_fecha;
    }

    public void setDt_fecha(String dt_fecha) {
        this.dt_fecha = dt_fecha;
    }

    public Integer getIn_codigo_directiva() {
        return in_codigo_directiva;
    }

    public void setIn_codigo_directiva(Integer in_codigo_directiva) {
        this.in_codigo_directiva = in_codigo_directiva;
    }

    public Integer getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(Integer in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public String getVc_alcance() {
        return vc_alcance;
    }

    public void setVc_alcance(String vc_alcance) {
        this.vc_alcance = vc_alcance;
    }

    public String getVc_responsabilidad() {
        return vc_responsabilidad;
    }

    public void setVc_responsabilidad(String vc_responsabilidad) {
        this.vc_responsabilidad = vc_responsabilidad;
    }
    
}