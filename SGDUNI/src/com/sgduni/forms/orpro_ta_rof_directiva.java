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
 * @author JMarcos
 */
public class orpro_ta_rof_directiva extends org.apache.struts.action.ActionForm {
    
    private String orden;
    private String vc_descripcion;
    private int in_codigo_rof;

    public int getIn_codigo_rof() {
        return in_codigo_rof;
    }

    public void setIn_codigo_rof(int in_codigo_rof) {
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
