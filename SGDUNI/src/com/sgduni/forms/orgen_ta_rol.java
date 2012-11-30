package com.sgduni.forms;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 * @author Marco Estrella Cardenas
 */
public class orgen_ta_rol extends org.apache.struts.action.ActionForm
{
    private String ch_codigo_rol;
    private String vc_nombre;
    private String estado;
    private String vc_usuario_crea;
    private String vc_usuario_modifica;
    private String dt_fecha_crea;
    private String dt_fecha_modifica;
    private String mensaje;
    //
    private Integer in_codigo_usuario;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
       ch_codigo_rol = "";
       vc_nombre = "";
       estado = "";
       vc_usuario_crea = "";
       vc_usuario_modifica = "";
       dt_fecha_crea = "";
       dt_fecha_modifica = "";
       mensaje = "";
       //
       in_codigo_usuario=0;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public orgen_ta_rol() {
    }

    public String getCh_codigo_rol() {
        return ch_codigo_rol;
    }

    public void setCh_codigo_rol(String ch_codigo_rol) {
        this.ch_codigo_rol = ch_codigo_rol;
    }

    public String getDt_fecha_crea() {
        return dt_fecha_crea;
    }

    public void setDt_fecha_crea(String dt_fecha_crea) {
        this.dt_fecha_crea = dt_fecha_crea;
    }

    public String getDt_fecha_modifica() {
        return dt_fecha_modifica;
    }

    public void setDt_fecha_modifica(String dt_fecha_modifica) {
        this.dt_fecha_modifica = dt_fecha_modifica;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVc_nombre() {
        return vc_nombre;
    }

    public void setVc_nombre(String vc_nombre) {
        this.vc_nombre = vc_nombre;
    }

    public String getVc_usuario_crea() {
        return vc_usuario_crea;
    }

    public void setVc_usuario_crea(String vc_usuario_crea) {
        this.vc_usuario_crea = vc_usuario_crea;
    }

    public String getVc_usuario_modifica() {
        return vc_usuario_modifica;
    }

    public void setVc_usuario_modifica(String vc_usuario_modifica) {
        this.vc_usuario_modifica = vc_usuario_modifica;
    }
    //
    public Integer getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(Integer in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }
}
