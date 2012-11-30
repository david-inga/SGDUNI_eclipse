package com.sgduni.forms;

/**
 *
 * @author Programmer : Marco A. Estrella Cardenas
 */
public class orpro_ta_observacion_oficio extends org.apache.struts.action.ActionForm {
    
    private Integer in_codigo_obs_oficio;
    private Integer in_codigo_oficio;
    private String vc_observacion;
    private String vc_nombre_usuario;
    private Integer in_codigo_usuario;
    private String dt_fecha_crea;
    private String nombreUsuario;

    public orpro_ta_observacion_oficio() {
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getDt_fecha_crea() {
        return dt_fecha_crea;
    }

    public void setDt_fecha_crea(String dt_fecha_crea) {
        this.dt_fecha_crea = dt_fecha_crea;
    }

    public Integer getIn_codigo_obs_oficio() {
        return in_codigo_obs_oficio;
    }

    public void setIn_codigo_obs_oficio(Integer in_codigo_obs_oficio) {
        this.in_codigo_obs_oficio = in_codigo_obs_oficio;
    }

    public Integer getIn_codigo_oficio() {
        return in_codigo_oficio;
    }

    public void setIn_codigo_oficio(Integer in_codigo_oficio) {
        this.in_codigo_oficio = in_codigo_oficio;
    }

    public Integer getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(Integer in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public String getVc_nombre_usuario() {
        return vc_nombre_usuario;
    }

    public void setVc_nombre_usuario(String vc_nombre_usuario) {
        this.vc_nombre_usuario = vc_nombre_usuario;
    }

    public String getVc_observacion() {
        return vc_observacion;
    }

    public void setVc_observacion(String vc_observacion) {
        this.vc_observacion = vc_observacion;
    }


}
