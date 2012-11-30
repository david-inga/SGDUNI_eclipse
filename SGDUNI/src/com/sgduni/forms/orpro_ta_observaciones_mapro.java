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
 * @author Sistemas
 */
public class orpro_ta_observaciones_mapro extends org.apache.struts.action.ActionForm {
    private int in_codigo_observa;
     private int in_codigo_mapro;
     private String vc_observacion;
     private String vc_usuario_crea;
     private String dt_fecha_crea;
     private String ch_estado;
     private int in_codigo_usuario;
     private String mensaje;

     private String nombreUsuario;
     private String FechaWeb;
     private String HoraWeb;
    
     @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
     this.in_codigo_observa=0;
     this.in_codigo_mapro=0;
     this.vc_observacion="";
     this.vc_usuario_crea="";
     this.dt_fecha_crea="";
     this.ch_estado="";
     this.in_codigo_usuario=0;
     this.mensaje="";
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

    public int getIn_codigo_mapro() {
        return in_codigo_mapro;
    }

    public void setIn_codigo_mapro(int in_codigo_mapro) {
        this.in_codigo_mapro = in_codigo_mapro;
    }

    public int getIn_codigo_observa() {
        return in_codigo_observa;
    }

    public void setIn_codigo_observa(int in_codigo_observa) {
        this.in_codigo_observa = in_codigo_observa;
    }

    public int getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(int in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public String getVc_observacion() {
        return vc_observacion;
    }

    public void setVc_observacion(String vc_observacion) {
        this.vc_observacion = vc_observacion;
    }

    public String getVc_usuario_crea() {
        return vc_usuario_crea;
    }

    public void setVc_usuario_crea(String vc_usuario_crea) {
        this.vc_usuario_crea = vc_usuario_crea;
    }

     
}
