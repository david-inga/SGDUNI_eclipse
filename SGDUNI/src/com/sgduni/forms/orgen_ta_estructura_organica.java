package com.sgduni.forms;

import org.apache.struts.upload.FormFile;

/**
 * @author JMarcos
 */
public class orgen_ta_estructura_organica extends org.apache.struts.action.ActionForm {
    
    private Integer in_codigo_estructura;
    private String ch_codigo_estructura;
    private String dt_fecha;
    private Integer in_facu_depend;
    private String ch_tipo_fac_depend;
    private String vc_nombre_archivo;
    private FormFile vc_ruta_archivo;
    private Integer in_codigo_usuario;
    private Integer in_codigo_estado;
    private String dt_fecha_hora;
    private String nombre_FacDep;
    private String nombre_Usuario;
    private String vc_observacion;
    private String mensaje;
    private String mensaje2;
    private int in_formato;
    private int in_codigo_oficio;


    public orgen_ta_estructura_organica() {
    }

    public String getMensaje2() {
        return mensaje2;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }
    
    public int getIn_codigo_oficio() {
        return in_codigo_oficio;
    }

    public void setIn_codigo_oficio(int in_codigo_oficio) {
        this.in_codigo_oficio = in_codigo_oficio;
    }

    public int getIn_formato() {
        return in_formato;
    }

    public void setIn_formato(int in_formato) {
        this.in_formato = in_formato;
    }
    
    public String getVc_observacion() {
        return vc_observacion;
    }

    public void setVc_observacion(String vc_observacion) {
        this.vc_observacion = vc_observacion;
    }
   
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    

    public String getNombre_Usuario() {
        return nombre_Usuario;
    }

    public void setNombre_Usuario(String nombre_Usuario) {
        this.nombre_Usuario = nombre_Usuario;
    }
    
    public String getNombre_FacDep() {
        return nombre_FacDep;
    }

    public void setNombre_FacDep(String nombre_FacDep) {
        this.nombre_FacDep = nombre_FacDep;
    }

    

    public String getCh_codigo_estructura() {
        return ch_codigo_estructura;
    }

    public void setCh_codigo_estructura(String ch_codigo_estructura) {
        this.ch_codigo_estructura = ch_codigo_estructura;
    }

    public String getCh_tipo_fac_depend() {
        return ch_tipo_fac_depend;
    }

    public void setCh_tipo_fac_depend(String ch_tipo_fac_depend) {
        this.ch_tipo_fac_depend = ch_tipo_fac_depend;
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

    public Integer getIn_codigo_estructura() {
        return in_codigo_estructura;
    }

    public void setIn_codigo_estructura(Integer in_codigo_estructura) {
        this.in_codigo_estructura = in_codigo_estructura;
    }

    public Integer getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(Integer in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public Integer getIn_facu_depend() {
        return in_facu_depend;
    }

    public void setIn_facu_depend(Integer in_facu_depend) {
        this.in_facu_depend = in_facu_depend;
    }

    public String getVc_nombre_archivo() {
        return vc_nombre_archivo;
    }

    public void setVc_nombre_archivo(String vc_nombre_archivo) {
        this.vc_nombre_archivo = vc_nombre_archivo;
    }

    public FormFile getVc_ruta_archivo() {
        return vc_ruta_archivo;
    }

    public void setVc_ruta_archivo(FormFile vc_ruta_archivo) {
        this.vc_ruta_archivo = vc_ruta_archivo;
    }
  
}
