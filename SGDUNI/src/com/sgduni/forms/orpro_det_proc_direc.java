/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/*
 * @author marco
 */
public class orpro_det_proc_direc extends org.apache.struts.action.ActionForm {
    
    private Integer  in_codigo_directiva;
    private Integer in_codigo_procedimiento;
    private String nombre_procedimiento;
    private String descripcion_proce;


    @Override
    public void reset(
            ActionMapping mapping,
            HttpServletRequest request)
    {
        this.in_codigo_directiva = 0;
        this.nombre_procedimiento = "";
        this.in_codigo_procedimiento = 0;
        this.descripcion_proce = "";
    }

    public String getDescripcion_proce() {
        return descripcion_proce;
    }

    public void setDescripcion_proce(String descripcion_proce) {
        this.descripcion_proce = descripcion_proce;
    }
   
    public String getNombre_procedimiento() {
        return nombre_procedimiento;
    }

    public void setNombre_procedimiento(String nombre_procedimiento) {
        this.nombre_procedimiento = nombre_procedimiento;
    }



    public Integer getIn_codigo_directiva() {
        return in_codigo_directiva;
    }

    public void setIn_codigo_directiva(Integer in_codigo_directiva) {
        this.in_codigo_directiva = in_codigo_directiva;
    }

    public Integer getIn_codigo_procedimiento() {
        return in_codigo_procedimiento;
    }

    public void setIn_codigo_procedimiento(Integer in_codigo_procedimiento) {
        this.in_codigo_procedimiento = in_codigo_procedimiento;
    }
}
