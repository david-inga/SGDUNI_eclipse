package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author marco
 */
public class orpro_det_cap extends org.apache.struts.action.ActionForm {
    
    private String ch_codigo_cap;
    private Integer in_codigo_cap;
    private Integer in_codigo_cargo_estruc;
    private Integer in_total;
    private Integer in_situa_cargo_prev;
    private Integer in_situa_cargo_ocupado;
    private String  ch_cargo_confianza;
    private String  vc_observacion;
    private String mensaje;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.ch_codigo_cap = "";
        this.in_codigo_cargo_estruc = 0;
        this.in_total = 0;
        this.in_situa_cargo_prev = 0;
        this.in_situa_cargo_ocupado = 0;
        this.ch_cargo_confianza = "";
        this.vc_observacion = "";
        this.mensaje  = "";
    }

    public Integer getIn_codigo_cap() {
        return in_codigo_cap;
    }

    public void setIn_codigo_cap(Integer in_codigo_cap) {
        this.in_codigo_cap = in_codigo_cap;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCh_cargo_confianza() {
        return ch_cargo_confianza;
    }

    public void setCh_cargo_confianza(String ch_cargo_confianza) {
        this.ch_cargo_confianza = ch_cargo_confianza;
    }

    public String getCh_codigo_cap() {
        return ch_codigo_cap;
    }

    public void setCh_codigo_cap(String ch_codigo_cap) {
        this.ch_codigo_cap = ch_codigo_cap;
    }

    public Integer getIn_codigo_cargo_estruc() {
        return in_codigo_cargo_estruc;
    }

    public void setIn_codigo_cargo_estruc(Integer in_codigo_cargo_estruc) {
        this.in_codigo_cargo_estruc = in_codigo_cargo_estruc;
    }

    public Integer getIn_situa_cargo_ocupado() {
        return in_situa_cargo_ocupado;
    }

    public void setIn_situa_cargo_ocupado(Integer in_situa_cargo_ocupado) {
        this.in_situa_cargo_ocupado = in_situa_cargo_ocupado;
    }

    public Integer getIn_situa_cargo_prev() {
        return in_situa_cargo_prev;
    }

    public void setIn_situa_cargo_prev(Integer in_situa_cargo_prev) {
        this.in_situa_cargo_prev = in_situa_cargo_prev;
    }

    public Integer getIn_total() {
        return in_total;
    }

    public void setIn_total(Integer in_total) {
        this.in_total = in_total;
    }

    public String getVc_observacion() {
        return vc_observacion;
    }

    public void setVc_observacion(String vc_observacion) {
        this.vc_observacion = vc_observacion;
    }
}
