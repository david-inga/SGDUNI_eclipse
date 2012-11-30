package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Sistemas
 */
public class orgen_ta_herramientas extends org.apache.struts.action.ActionForm {

    private String ch_codigo_herramienta;
    private String vc_nombre;
    private String vc_url_herramienta;
    private String vc_target;
    private String vc_icono;
    private String ch_estado;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.ch_codigo_herramienta="";
        this.vc_nombre="";
        this.vc_url_herramienta="";
        this.vc_target="";
        this.vc_icono="";
        this.ch_estado="";

    }

    public String getCh_codigo_herramienta() {
        return ch_codigo_herramienta;
    }

    public void setCh_codigo_herramienta(String ch_codigo_herramienta) {
        this.ch_codigo_herramienta = ch_codigo_herramienta;
    }

    public String getCh_estado() {
        return ch_estado;
    }

    public void setCh_estado(String ch_estado) {
        this.ch_estado = ch_estado;
    }

    public String getVc_icono() {
        return vc_icono;
    }

    public void setVc_icono(String vc_icono) {
        this.vc_icono = vc_icono;
    }

    public String getVc_nombre() {
        return vc_nombre;
    }

    public void setVc_nombre(String vc_nombre) {
        this.vc_nombre = vc_nombre;
    }

    public String getVc_target() {
        return vc_target;
    }

    public void setVc_target(String vc_target) {
        this.vc_target = vc_target;
    }

    public String getVc_url_herramienta() {
        return vc_url_herramienta;
    }

    public void setVc_url_herramienta(String vc_url_herramienta) {
        this.vc_url_herramienta = vc_url_herramienta;
    }
}
