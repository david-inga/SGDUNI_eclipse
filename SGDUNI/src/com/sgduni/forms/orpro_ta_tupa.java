package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/*
* @author marco
*/


public class orpro_ta_tupa extends org.apache.struts.action.ActionForm
{
    private Integer in_codigo_tupa;
    private String ch_codigo_tupa;
    private String dt_fecha;
    private String ch_estado;
    private String nombre_estado;
    private Integer in_cod_depend_fac;
    private String nombre_DependFac;
    private String ch_tipo_depend_fac;
    private Integer in_codigo_usuario;
    private String mensaje;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.in_codigo_tupa = 0;
        this.ch_codigo_tupa = "";
        this.dt_fecha = "";
        this.ch_estado = "";
        this.in_cod_depend_fac = 0;
        this.ch_tipo_depend_fac = "";
        this.in_codigo_usuario = 0;
        this.nombre_DependFac = "";
        this.mensaje = "";
    }

    public String getNombre_DependFac() {
        return nombre_DependFac;
    }

    public void setNombre_DependFac(String nombre_DependFac) {
        this.nombre_DependFac = nombre_DependFac;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCh_codigo_tupa() {
        return ch_codigo_tupa;
    }

    public void setCh_codigo_tupa(String ch_codigo_tupa) {
        this.ch_codigo_tupa = ch_codigo_tupa;
    }

    public String getCh_estado() {
        return ch_estado;
    }

    public void setCh_estado(String ch_estado) {
        this.ch_estado = ch_estado;
    }

    public String getCh_tipo_depend_fac() {
        return ch_tipo_depend_fac;
    }

    public void setCh_tipo_depend_fac(String ch_tipo_depend_fac) {
        this.ch_tipo_depend_fac = ch_tipo_depend_fac;
    }

    public String getDt_fecha() {
        return dt_fecha;
    }

    public void setDt_fecha(String dt_fecha) {
        this.dt_fecha = dt_fecha;
    }

    public Integer getIn_cod_depend_fac() {
        return in_cod_depend_fac;
    }

    public void setIn_cod_depend_fac(Integer in_cod_depend_fac) {
        this.in_cod_depend_fac = in_cod_depend_fac;
    }

    public Integer getIn_codigo_tupa() {
        return in_codigo_tupa;
    }

    public void setIn_codigo_tupa(Integer in_codigo_tupa) {
        this.in_codigo_tupa = in_codigo_tupa;
    }

    public Integer getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(Integer in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }
}
