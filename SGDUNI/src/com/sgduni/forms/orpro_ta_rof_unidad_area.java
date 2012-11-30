package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author pc
 */
public class orpro_ta_rof_unidad_area extends org.apache.struts.action.ActionForm
{
    
    private Integer in_codigo_area_unidad;
    private Integer in_codigo_registro;
    private String vc_nombre_area;
    private String vc_descripcion_area;

    public orpro_ta_rof_unidad_area() 
    {
        
    }

    public Integer getIn_codigo_area_unidad() {
        return in_codigo_area_unidad;
    }

    public void setIn_codigo_area_unidad(Integer in_codigo_area_unidad) {
        this.in_codigo_area_unidad = in_codigo_area_unidad;
    }

    public Integer getIn_codigo_registro() {
        return in_codigo_registro;
    }

    public void setIn_codigo_registro(Integer in_codigo_registro) {
        this.in_codigo_registro = in_codigo_registro;
    }

    public String getVc_descripcion_area() {
        return vc_descripcion_area;
    }

    public void setVc_descripcion_area(String vc_descripcion_area) {
        this.vc_descripcion_area = vc_descripcion_area;
    }

    public String getVc_nombre_area() {
        return vc_nombre_area;
    }

    public void setVc_nombre_area(String vc_nombre_area) {
        this.vc_nombre_area = vc_nombre_area;
    }
}
