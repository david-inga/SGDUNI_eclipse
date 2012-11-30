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
public class orpro_ta_estruc_org_oficio extends org.apache.struts.action.ActionForm {
    
    private Integer in_codigo_estructura;
    private Integer in_codigo_oficio;
    private String dt_fecha;
    private String dt_fecha_hora;

    public orpro_ta_estruc_org_oficio() {
    }

    public String getDt_fecha() {
        return dt_fecha;
    }

    public void setDt_fecha(String dt_fecha) {
        this.dt_fecha = dt_fecha;
    }

    public String getDt_fecha_hora() {
        return dt_fecha_hora;
    }

    public void setDt_fecha_hora(String dt_fecha_hora) {
        this.dt_fecha_hora = dt_fecha_hora;
    }

    public Integer getIn_codigo_estructura() {
        return in_codigo_estructura;
    }

    public void setIn_codigo_estructura(Integer in_codigo_estructura) {
        this.in_codigo_estructura = in_codigo_estructura;
    }

    public Integer getIn_codigo_oficio() {
        return in_codigo_oficio;
    }

    public void setIn_codigo_oficio(Integer in_codigo_oficio) {
        this.in_codigo_oficio = in_codigo_oficio;
    }
}
