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
public class orgen_ta_configura_flujo extends org.apache.struts.action.ActionForm
{
private int in_codigo_cong;
private String vc_nombre;
private int in_codigo_usuario_crea;
private String dt_fecha_crea;
private int in_codigo_usuario_modi;
private String dt_fecha_modif;
private String mensaje;

private String ch_estado;


private String usuarioCrea;
private String usuarioModi;


    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.in_codigo_cong=0;
        this.vc_nombre="";
        this.in_codigo_usuario_crea=0;
        this.dt_fecha_crea="";
        this.in_codigo_usuario_modi=0;
        this.dt_fecha_modif="";
        this.mensaje = "";
    }

    public String getCh_estado() {
        return ch_estado;
    }

    public void setCh_estado(String ch_estado) {
        this.ch_estado = ch_estado;
    }
    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getUsuarioModi() {
        return usuarioModi;
    }

    public void setUsuarioModi(String usuarioModi) {
        this.usuarioModi = usuarioModi;
    }

    public String getDt_fecha_crea() {
        return dt_fecha_crea;
    }

    public void setDt_fecha_crea(String dt_fecha_crea) {
        this.dt_fecha_crea = dt_fecha_crea;
    }

    public String getDt_fecha_modif() {
        return dt_fecha_modif;
    }

    public void setDt_fecha_modif(String dt_fecha_modif) {
        this.dt_fecha_modif = dt_fecha_modif;
    }

    public int getIn_codigo_cong() {
        return in_codigo_cong;
    }

    public void setIn_codigo_cong(int in_codigo_cong) {
        this.in_codigo_cong = in_codigo_cong;
    }

    public int getIn_codigo_usuario_crea() {
        return in_codigo_usuario_crea;
    }

    public void setIn_codigo_usuario_crea(int in_codigo_usuario_crea) {
        this.in_codigo_usuario_crea = in_codigo_usuario_crea;
    }

    public int getIn_codigo_usuario_modi() {
        return in_codigo_usuario_modi;
    }

    public void setIn_codigo_usuario_modi(int in_codigo_usuario_modi) {
        this.in_codigo_usuario_modi = in_codigo_usuario_modi;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getVc_nombre() {
        return vc_nombre;
    }

    public void setVc_nombre(String vc_nombre) {
        this.vc_nombre = vc_nombre;
    }


}
