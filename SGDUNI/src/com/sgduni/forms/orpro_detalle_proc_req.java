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
public class orpro_detalle_proc_req extends org.apache.struts.action.ActionForm {
    
    private Integer in_codigo_det;
    private Integer in_codigo_procedimiento;
    private String nombre_procedimiento;
    private Integer in_codigo_req;
    private String nombre_requisito;
    private Integer in_codigo_tupa;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.in_codigo_det = 0;
        this.in_codigo_procedimiento = 0;
        this.in_codigo_req = 0;
        this.in_codigo_tupa = 0;
        this.nombre_procedimiento = "";
        this.nombre_requisito = "";
    }

    public Integer getIn_codigo_tupa() {
        return in_codigo_tupa;
    }

    public void setIn_codigo_tupa(Integer in_codigo_tupa) {
        this.in_codigo_tupa = in_codigo_tupa;
    }
    
    public String getNombre_procedimiento() {
        return nombre_procedimiento;
    }

    public void setNombre_procedimiento(String nombre_procedimiento) {
        this.nombre_procedimiento = nombre_procedimiento;
    }

    public String getNombre_requisito() {
        return nombre_requisito;
    }

    public void setNombre_requisito(String nombre_requisito) {
        this.nombre_requisito = nombre_requisito;
    }

    public Integer getIn_codigo_det() {
        return in_codigo_det;
    }

    public void setIn_codigo_det(Integer in_codigo_det) {
        this.in_codigo_det = in_codigo_det;
    }

    public Integer getIn_codigo_procedimiento() {
        return in_codigo_procedimiento;
    }

    public void setIn_codigo_procedimiento(Integer in_codigo_procedimiento) {
        this.in_codigo_procedimiento = in_codigo_procedimiento;
    }

    public Integer getIn_codigo_req() {
        return in_codigo_req;
    }

    public void setIn_codigo_req(Integer in_codigo_req) {
        this.in_codigo_req = in_codigo_req;
    }
}
