package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/*
 * @author marco
 */
public class orpro_ta_cap extends org.apache.struts.action.ActionForm 
{
    private Integer in_codigo_cap;
    private String ch_codigo_cap;
    private Integer in_codigo_usuario;
    private String dt_fecha_cap;
    private String ch_estado_cap;
    private String nombre_estado;
    private Integer in_cod_depend_fac;
    private String ch_tipo_depend_fac;
    private String nombre_DependFac;
    private String mensaje;

    @Override
    public void reset(
            ActionMapping mapping,
            HttpServletRequest request)
    {
        this.in_codigo_cap = 0;
        this.ch_codigo_cap = "";
        this.in_codigo_usuario = 0;
        this.dt_fecha_cap = "";
        this.ch_estado_cap = "";
        this.in_cod_depend_fac = 0;
        this.ch_tipo_depend_fac = "";
        this.nombre_estado = "";
        this.nombre_DependFac = "";
        this.mensaje = "";
    }

    public String getNombre_DependFac() {
        return nombre_DependFac;
    }

    public void setNombre_DependFac(String nombre_DependFac) {
        this.nombre_DependFac = nombre_DependFac;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }

    public String getCh_codigo_cap() {
        return ch_codigo_cap;
    }

    public void setCh_codigo_cap(String ch_codigo_cap) {
        this.ch_codigo_cap = ch_codigo_cap;
    }

    public String getCh_estado_cap() {
        return ch_estado_cap;
    }

    public void setCh_estado_cap(String ch_estado_cap) {
        this.ch_estado_cap = ch_estado_cap;
    }

    public String getCh_tipo_depend_fac() {
        return ch_tipo_depend_fac;
    }

    public void setCh_tipo_depend_fac(String ch_tipo_depend_fac) {
        this.ch_tipo_depend_fac = ch_tipo_depend_fac;
    }

    public String getDt_fecha_cap() {
        return dt_fecha_cap;
    }

    public void setDt_fecha_cap(String dt_fecha_cap) {
        this.dt_fecha_cap = dt_fecha_cap;
    }

    public Integer getIn_cod_depend_fac() {
        return in_cod_depend_fac;
    }

    public void setIn_cod_depend_fac(Integer in_cod_depend_fac) {
        this.in_cod_depend_fac = in_cod_depend_fac;
    }

    public Integer getIn_codigo_cap() {
        return in_codigo_cap;
    }

    public void setIn_codigo_cap(Integer in_codigo_cap) {
        this.in_codigo_cap = in_codigo_cap;
    }

    public Integer getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(Integer in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
