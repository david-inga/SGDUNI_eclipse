package com.sgduni.forms;


/*
 * @author JMarcos
 */
public class orpro_ta_versiones_estruc extends org.apache.struts.action.ActionForm {
    
   private Integer in_codigo_versiones;
   private Integer in_codigo_estruc;
   private String vc_nombre_archivo;
   private String vc_ruta_archivo;
   private String vc_nombre_usuario;
   private Integer in_codigo_usuario;
   private Integer in_codigo_estado;
   private String dt_fecha_hora;
   private String dt_fecha;
   private String num_version;

    public orpro_ta_versiones_estruc() {
    }

    public String getNum_version() {
        return num_version;
    }

    public void setNum_version(String num_version) {
        this.num_version = num_version;
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

    public Integer getIn_codigo_estado() {
        return in_codigo_estado;
    }

    public void setIn_codigo_estado(Integer in_codigo_estado) {
        this.in_codigo_estado = in_codigo_estado;
    }

    public Integer getIn_codigo_estruc() {
        return in_codigo_estruc;
    }

    public void setIn_codigo_estruc(Integer in_codigo_estruc) {
        this.in_codigo_estruc = in_codigo_estruc;
    }

    public Integer getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(Integer in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public Integer getIn_codigo_versiones() {
        return in_codigo_versiones;
    }

    public void setIn_codigo_versiones(Integer in_codigo_versiones) {
        this.in_codigo_versiones = in_codigo_versiones;
    }

    public String getVc_nombre_archivo() {
        return vc_nombre_archivo;
    }

    public void setVc_nombre_archivo(String vc_nombre_archivo) {
        this.vc_nombre_archivo = vc_nombre_archivo;
    }

    public String getVc_nombre_usuario() {
        return vc_nombre_usuario;
    }

    public void setVc_nombre_usuario(String vc_nombre_usuario) {
        this.vc_nombre_usuario = vc_nombre_usuario;
    }

    public String getVc_ruta_archivo() {
        return vc_ruta_archivo;
    }

    public void setVc_ruta_archivo(String vc_ruta_archivo) {
        this.vc_ruta_archivo = vc_ruta_archivo;
    }

}
