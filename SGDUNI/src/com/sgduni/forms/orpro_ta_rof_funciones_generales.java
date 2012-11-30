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
 * @author pc
 */
public class orpro_ta_rof_funciones_generales extends org.apache.struts.action.ActionForm {
    
    private Integer in_codigo_funcion;
    private Integer in_codigo_rof;
    private Integer in_codigo_version;
    private String ch_orden;
    private String vc_descripcion;

    public orpro_ta_rof_funciones_generales() {
    }

    public String getCh_orden() {
        return ch_orden;
    }

    public void setCh_orden(String ch_orden) {
        this.ch_orden = ch_orden;
    }

    public Integer getIn_codigo_funcion() {
        return in_codigo_funcion;
    }

    public void setIn_codigo_funcion(Integer in_codigo_funcion) {
        this.in_codigo_funcion = in_codigo_funcion;
    }

    public Integer getIn_codigo_rof() {
        return in_codigo_rof;
    }

    public void setIn_codigo_rof(Integer in_codigo_rof) {
        this.in_codigo_rof = in_codigo_rof;
    }

    public Integer getIn_codigo_version() {
        return in_codigo_version;
    }

    public void setIn_codigo_version(Integer in_codigo_version) {
        this.in_codigo_version = in_codigo_version;
    }

    public String getVc_descripcion() {
        return vc_descripcion;
    }

    public void setVc_descripcion(String vc_descripcion) {
        this.vc_descripcion = vc_descripcion;
    }

}
