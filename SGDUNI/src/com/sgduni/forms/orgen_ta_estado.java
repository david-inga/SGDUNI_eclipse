package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author marco
 */
public class orgen_ta_estado extends org.apache.struts.action.ActionForm {
    
    private Integer in_codigo_estado;
    private String vc_nombre_estado;
    private String ch_estado;
    private String vc_usuario_crea;
    private String dt_fecha_crea;
    private String vc_usuario_modif;
    private String dt_fecha_modif;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.in_codigo_estado = 0;
        this.vc_nombre_estado = "";
        this.ch_estado = "";
        this.vc_usuario_crea = "";
        this.dt_fecha_crea = "";
        this.vc_usuario_modif = "";
        this.dt_fecha_modif = "";

    }

    public String getCh_estado() {
        return ch_estado;
    }

    public void setCh_estado(String ch_estado) {
        this.ch_estado = ch_estado;
    }

    public String getDt_fecha_crea() {
        return dt_fecha_crea;
    }

    public void setDt_fecha_crea(String dt_fecha_crea) {
        this.dt_fecha_crea = dt_fecha_crea;
    }

    public String getDt_fecha_modif() {
        return dt_fecha_modif;
    }

    public void setDt_fecha_modif(String dt_fecha_modif) {
        this.dt_fecha_modif = dt_fecha_modif;
    }

    public Integer getIn_codigo_estado() {
        return in_codigo_estado;
    }

    public void setIn_codigo_estado(Integer in_codigo_estado) {
        this.in_codigo_estado = in_codigo_estado;
    }

    public String getVc_nombre_estado() {
        return vc_nombre_estado;
    }

    public void setVc_nombre_estado(String vc_nombre_estado) {
        this.vc_nombre_estado = vc_nombre_estado;
    }

    public String getVc_usuario_crea() {
        return vc_usuario_crea;
    }

    public void setVc_usuario_crea(String vc_usuario_crea) {
        this.vc_usuario_crea = vc_usuario_crea;
    }

    public String getVc_usuario_modif() {
        return vc_usuario_modif;
    }

    public void setVc_usuario_modif(String vc_usuario_modif) {
        this.vc_usuario_modif = vc_usuario_modif;
    }

}
