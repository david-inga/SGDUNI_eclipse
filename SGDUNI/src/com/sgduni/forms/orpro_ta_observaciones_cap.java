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
public class orpro_ta_observaciones_cap extends org.apache.struts.action.ActionForm {
   
 private Integer in_codigo_observa;
 private Integer in_codigo_cap;
 private String vc_observacion;
 private String dt_fecha_crea;
 private String ch_estado;
 private Integer in_codigo_usuario;
 private String mensaje;
 private String nombreUsuario;
 private String FechaWeb;
 private String HoraWeb;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
       this.in_codigo_observa=0;
         this.in_codigo_cap=0;
         this.vc_observacion="";
         this.dt_fecha_crea="";
         this.ch_estado="";
         this.in_codigo_usuario=0;
         this.mensaje="";
         this.nombreUsuario="";
         this.FechaWeb="";
         this.HoraWeb="";
    }

    public String getFechaWeb() {
        return FechaWeb;
    }

    public void setFechaWeb(String FechaWeb) {
        this.FechaWeb = FechaWeb;
    }

    public String getHoraWeb() {
        return HoraWeb;
    }

    public void setHoraWeb(String HoraWeb) {
        this.HoraWeb = HoraWeb;
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

    public Integer getIn_codigo_cap() {
        return in_codigo_cap;
    }

    public void setIn_codigo_cap(Integer in_codigo_cap) {
        this.in_codigo_cap = in_codigo_cap;
    }

    public Integer getIn_codigo_observa() {
        return in_codigo_observa;
    }

    public void setIn_codigo_observa(Integer in_codigo_observa) {
        this.in_codigo_observa = in_codigo_observa;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getVc_observacion() {
        return vc_observacion;
    }

    public void setVc_observacion(String vc_observacion) {
        this.vc_observacion = vc_observacion;
    }
   
}
