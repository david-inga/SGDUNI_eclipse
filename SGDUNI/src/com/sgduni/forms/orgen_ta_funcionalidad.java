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
public class orgen_ta_funcionalidad extends org.apache.struts.action.ActionForm {
    
    private String ch_codigo_funcionalidad;
    private String vc_nombre;
    private String vc_url_funcionalidad;
    private String ch_estado;
    private String ch_codigo_rol;
    private String vc_icono;

    public String getVc_icono() {
        return vc_icono;
    }

    public void setVc_icono(String vc_icono) {
        this.vc_icono = vc_icono;
    }

    public orgen_ta_funcionalidad() {
    }

    public String getCh_codigo_rol() {
        return ch_codigo_rol;
    }

    public void setCh_codigo_rol(String ch_codigo_rol) {
        this.ch_codigo_rol = ch_codigo_rol;
    }

    

    public String getCh_codigo_funcionalidad() {
        return ch_codigo_funcionalidad;
    }

    public void setCh_codigo_funcionalidad(String ch_codigo_funcionalidad) {
        this.ch_codigo_funcionalidad = ch_codigo_funcionalidad;
    }

    public String getCh_estado() {
        return ch_estado;
    }

    public void setCh_estado(String ch_estado) {
        this.ch_estado = ch_estado;
    }

    public String getVc_nombre() {
        return vc_nombre;
    }

    public void setVc_nombre(String vc_nombre) {
        this.vc_nombre = vc_nombre;
    }

    public String getVc_url_funcionalidad() {
        return vc_url_funcionalidad;
    }

    public void setVc_url_funcionalidad(String vc_url_funcionalidad) {
        this.vc_url_funcionalidad = vc_url_funcionalidad;
    }

}
