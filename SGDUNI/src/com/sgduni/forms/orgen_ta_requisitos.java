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
public class orgen_ta_requisitos extends org.apache.struts.action.ActionForm {
    
    private Integer in_codigo_req;
    private String ch_codigo_req;
    private String vc_nombre_req;
    private String vc_descripcion_req;
    private String vc_nombre_usuario;
    private String dt_fecha_crea;
    private String vc_usuario_modif;
    private String dt_fecha_modif;
    private String ch_estado;
    private String mensaje;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.in_codigo_req = 0;
        this.ch_codigo_req = "";
        this.vc_nombre_req = "";
        this.vc_descripcion_req = "";
        this.vc_nombre_usuario = "";
        this.dt_fecha_crea = "";
        this.vc_usuario_modif = "";
        this.dt_fecha_modif = "";
        this.ch_estado = "";
        this.mensaje = "";
    }

    public String getCh_codigo_req() {
        return ch_codigo_req;
    }

    public void setCh_codigo_req(String ch_codigo_req) {
        this.ch_codigo_req = ch_codigo_req;
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

    public String getDt_fecha_modif() {
        return dt_fecha_modif;
    }

    public void setDt_fecha_modif(String dt_fecha_modif) {
        this.dt_fecha_modif = dt_fecha_modif;
    }

    public Integer getIn_codigo_req() {
        return in_codigo_req;
    }

    public void setIn_codigo_req(Integer in_codigo_req) {
        this.in_codigo_req = in_codigo_req;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getVc_descripcion_req() {
        return vc_descripcion_req;
    }

    public void setVc_descripcion_req(String vc_descripcion_req) {
        this.vc_descripcion_req = vc_descripcion_req;
    }

    public String getVc_nombre_req() {
        return vc_nombre_req;
    }

    public void setVc_nombre_req(String vc_nombre_req) {
        this.vc_nombre_req = vc_nombre_req;
    }

    public String getVc_nombre_usuario() {
        return vc_nombre_usuario;
    }

    public void setVc_nombre_usuario(String vc_nombre_usuario) {
        this.vc_nombre_usuario = vc_nombre_usuario;
    }

    public String getVc_usuario_modif() {
        return vc_usuario_modif;
    }

    public void setVc_usuario_modif(String vc_usuario_modif) {
        this.vc_usuario_modif = vc_usuario_modif;
    }
    
}
