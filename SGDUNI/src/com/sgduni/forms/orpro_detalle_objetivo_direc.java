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
public class orpro_detalle_objetivo_direc extends org.apache.struts.action.ActionForm {
    
    private Integer in_codigo_det_obj_direc;
    private Integer in_codigo_directiva;
    private String vc_descripcion;

    @Override
    public void reset(
            ActionMapping mapping,
            HttpServletRequest request)
    {
       this.in_codigo_det_obj_direc = 0;
       this.in_codigo_directiva = 0;
       this.vc_descripcion = "";
    }

    public Integer getIn_codigo_directiva() {
        return in_codigo_directiva;
    }

    public void setIn_codigo_directiva(Integer in_codigo_directiva) {
        this.in_codigo_directiva = in_codigo_directiva;
    }

    public Integer getIn_codigo_det_obj_direc() {
        return in_codigo_det_obj_direc;
    }

    public void setIn_codigo_det_obj_direc(Integer in_codigo_det_obj_direc) {
        this.in_codigo_det_obj_direc = in_codigo_det_obj_direc;
    }

    public String getVc_descripcion() {
        return vc_descripcion;
    }

    public void setVc_descripcion(String vc_descripcion) {
        this.vc_descripcion = vc_descripcion;
    }
}
