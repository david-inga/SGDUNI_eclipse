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
public class orpro_ta_registro_rof extends org.apache.struts.action.ActionForm
{
    private String vc_nombreOrgano;
    private Integer in_codigo_registro;
    private Integer in_codigo_rof;
    private Integer in_codigo_version;
    private Integer in_codigo_organo;
    private String vc_nombre_unidad;
    private String vc_descripcion_unidad;
    private String mensaje;

    public orpro_ta_registro_rof() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getIn_codigo_organo() {
        return in_codigo_organo;
    }

    public void setIn_codigo_organo(Integer in_codigo_organo) {
        this.in_codigo_organo = in_codigo_organo;
    }

    public Integer getIn_codigo_registro() {
        return in_codigo_registro;
    }

    public void setIn_codigo_registro(Integer in_codigo_registro) {
        this.in_codigo_registro = in_codigo_registro;
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

    public String getVc_descripcion_unidad() {
        return vc_descripcion_unidad;
    }

    public void setVc_descripcion_unidad(String vc_descripcion_unidad) {
        this.vc_descripcion_unidad = vc_descripcion_unidad;
    }

    public String getVc_nombre_unidad() {
        return vc_nombre_unidad;
    }

    public void setVc_nombre_unidad(String vc_nombre_unidad) {
        this.vc_nombre_unidad = vc_nombre_unidad;
    }

    public String getVc_nombreOrgano() {
        return vc_nombreOrgano;
    }

    public void setVc_nombreOrgano(String vc_nombreOrgano) {
        this.vc_nombreOrgano = vc_nombreOrgano;
    }
}
