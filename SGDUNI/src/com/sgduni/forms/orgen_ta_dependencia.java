package com.sgduni.forms;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/*
 * @author marco
 */
public class orgen_ta_dependencia extends org.apache.struts.action.ActionForm
{
    private Integer in_codigo_dependencia;
    private String vc_nombre;
    private String vc_descripcion;
    private String ch_estado;
    private String vc_usuario_crea;
    private String dt_fecha_crea;
    private String vc_usuario_modifica;
    private String dt_usuario_modifica;
    private Integer in_codigo_organo;
    private String nombre_organo;
    private Integer in_codigo_facultad;
    private String nombre_facultad;
    private String mensaje;
    private String vc_abrev_nom;
    private String vc_direccion;
    private String vc_apartado;
    private String vc_telefono;
    private String vc_telefono_central;
    private String vc_anexo;
    private String vc_correo;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.in_codigo_dependencia = 0;
        this.vc_nombre = "";
        this.vc_descripcion = "";
        this.ch_estado = "";
        this.vc_usuario_crea = "";
        this.dt_fecha_crea = "";
        this.vc_usuario_modifica = "";
        this.dt_usuario_modifica = "";
        this.in_codigo_organo = 0;
        this.nombre_organo = "";
        this.in_codigo_facultad = 0;
        this.nombre_facultad = "";
        this.mensaje = "";
    }

    public String getVc_anexo() {
        return vc_anexo;
    }

    public void setVc_anexo(String vc_anexo) {
        this.vc_anexo = vc_anexo;
    }

    public String getVc_apartado() {
        return vc_apartado;
    }

    public void setVc_apartado(String vc_apartado) {
        this.vc_apartado = vc_apartado;
    }

    public String getVc_correo() {
        return vc_correo;
    }

    public void setVc_correo(String vc_correo) {
        this.vc_correo = vc_correo;
    }

    public String getVc_direccion() {
        return vc_direccion;
    }

    public void setVc_direccion(String vc_direccion) {
        this.vc_direccion = vc_direccion;
    }

    public String getVc_telefono() {
        return vc_telefono;
    }

    public void setVc_telefono(String vc_telefono) {
        this.vc_telefono = vc_telefono;
    }

    public String getVc_telefono_central() {
        return vc_telefono_central;
    }

    public void setVc_telefono_central(String vc_telefono_central) {
        this.vc_telefono_central = vc_telefono_central;
    }



    public String getVc_abrev_nom() {
        return vc_abrev_nom;
    }

    public void setVc_abrev_nom(String vc_abrev_nom) {
        this.vc_abrev_nom = vc_abrev_nom;
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

    public String getDt_usuario_modifica() {
        return dt_usuario_modifica;
    }

    public void setDt_usuario_modifica(String dt_usuario_modifica) {
        this.dt_usuario_modifica = dt_usuario_modifica;
    }

    public Integer getIn_codigo_dependencia() {
        return in_codigo_dependencia;
    }

    public void setIn_codigo_dependencia(Integer in_codigo_dependencia) {
        this.in_codigo_dependencia = in_codigo_dependencia;
    }

    public Integer getIn_codigo_facultad() {
        return in_codigo_facultad;
    }

    public void setIn_codigo_facultad(Integer in_codigo_facultad) {
        this.in_codigo_facultad = in_codigo_facultad;
    }

    public Integer getIn_codigo_organo() {
        return in_codigo_organo;
    }

    public void setIn_codigo_organo(Integer in_codigo_organo) {
        this.in_codigo_organo = in_codigo_organo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre_facultad() {
        return nombre_facultad;
    }

    public void setNombre_facultad(String nombre_facultad) {
        this.nombre_facultad = nombre_facultad;
    }

    public String getNombre_organo() {
        return nombre_organo;
    }

    public void setNombre_organo(String nombre_organo) {
        this.nombre_organo = nombre_organo;
    }

    public String getVc_descripcion() {
        return vc_descripcion;
    }

    public void setVc_descripcion(String vc_descripcion) {
        this.vc_descripcion = vc_descripcion;
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
}
