/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.forms;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
/**
 *
 * @author Sistemas
 */
public class orpro_ta_observacion_estruc extends org.apache.struts.action.ActionForm {

 private int in_codigo_observa;
 private int in_codigo_estructura;
 private String vc_observacion;
 private String dt_fecha_crea;
 private String ch_estado;
 private int in_codigo_usuario;
 private String vc_nombre_usuario;
 private String mensaje;
 private String FechaWeb;
 private String HoraWeb;

public void reset(ActionMapping mapping,
            HttpServletRequest request)
    {
         this.in_codigo_observa=0;
         this.in_codigo_estructura=0;
         this.vc_observacion="";
         this.dt_fecha_crea="";
         this.ch_estado="";
         this.in_codigo_usuario=0;
         this.mensaje="";
         this.FechaWeb="";
         this.HoraWeb="";
    }

    public String getVc_nombre_usuario() {
        return vc_nombre_usuario;
    }

    public void setVc_nombre_usuario(String vc_nombre_usuario) {
        this.vc_nombre_usuario = vc_nombre_usuario;
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

    public int getIn_codigo_estructura() {
        return in_codigo_estructura;
    }

    public void setIn_codigo_estructura(int in_codigo_estructura) {
        this.in_codigo_estructura = in_codigo_estructura;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getVc_observacion() {
        return vc_observacion;
    }

    public void setVc_observacion(String vc_observacion) {
        this.vc_observacion = vc_observacion;
    }

}
