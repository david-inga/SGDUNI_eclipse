/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.forms;

/**
 *
 * @author pc
 */
public class orpro_ta_base_legal_rof extends org.apache.struts.action.ActionForm
{
    private Integer in_base_legal_rof;
    private Integer in_codigo_rof;
    private String orden;
    private String vc_descripcion;
    private Integer in_codigo_version;

    public orpro_ta_base_legal_rof() {
    }

    public Integer getIn_base_legal_rof() {
        return in_base_legal_rof;
    }

    public void setIn_base_legal_rof(Integer in_base_legal_rof) {
        this.in_base_legal_rof = in_base_legal_rof;
    }

    public Integer getIn_codigo_version() {
        return in_codigo_version;
    }

    public void setIn_codigo_version(Integer in_codigo_version) {
        this.in_codigo_version = in_codigo_version;
    }
    
    public Integer getIn_codigo_rof() {
        return in_codigo_rof;
    }

    public void setIn_codigo_rof(Integer in_codigo_rof) {
        this.in_codigo_rof = in_codigo_rof;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getVc_descripcion() {
        return vc_descripcion;
    }

    public void setVc_descripcion(String vc_descripcion) {
        this.vc_descripcion = vc_descripcion;
    }

    
}
