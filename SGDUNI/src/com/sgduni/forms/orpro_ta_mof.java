/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.forms;

/**
 *
 * @author Sistemas
 */
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


/**
 *
 * @author Sistemas
 */
public class orpro_ta_mof extends org.apache.struts.action.ActionForm {

    private int in_codigo_mof;
    private String ch_codigo_mof;
    private int in_depend_fac;
    private String ch_tipo_depend_fac;
    private String dt_fecha;
    private String vc_nombre_archivo;
    private FormFile vc_ruta_archivo_v1;
    private String NombreArchivo;
    private int in_codigo_estado;
    private int in_codigo_usuario;
    private String mensaje;
    private String ch_estado;
    private String nombreUsuario;
    private String nombreEstado;
    private String nombreFaculDepen;


    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.in_codigo_mof=0;
        this.ch_codigo_mof="";
        this.in_depend_fac=0;
        this.ch_tipo_depend_fac="";
        this.dt_fecha="";
        this.vc_nombre_archivo="";
        //this.vc_ruta_archivo_v1="";
        this.in_codigo_estado=0;
        this.in_codigo_usuario=0;
        this.mensaje="";
        this.ch_estado="";

        this.nombreUsuario="";
        this.nombreEstado="";
        this.nombreFaculDepen="";

    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getNombreFaculDepen() {
        return nombreFaculDepen;
    }

    public void setNombreFaculDepen(String nombreFaculDepen) {
        this.nombreFaculDepen = nombreFaculDepen;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    public String getCh_estado() {
        return ch_estado;
    }

    public void setCh_estado(String ch_estado) {
        this.ch_estado = ch_estado;
    }
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCh_codigo_mof() {
        return ch_codigo_mof;
    }

    public void setCh_codigo_mof(String ch_codigo_mof) {
        this.ch_codigo_mof = ch_codigo_mof;
    }

    public String getCh_tipo_depend_fac() {
        return ch_tipo_depend_fac;
    }

    public void setCh_tipo_depend_fac(String ch_tipo_depend_fac) {
        this.ch_tipo_depend_fac = ch_tipo_depend_fac;
    }

    public String getDt_fecha() {
        return dt_fecha;
    }

    public void setDt_fecha(String dt_fecha) {
        this.dt_fecha = dt_fecha;
    }

    public int getIn_codigo_estado() {
        return in_codigo_estado;
    }

    public void setIn_codigo_estado(int in_codigo_estado) {
        this.in_codigo_estado = in_codigo_estado;
    }

    public int getIn_codigo_mof() {
        return in_codigo_mof;
    }

    public void setIn_codigo_mof(int in_codigo_mof) {
        this.in_codigo_mof = in_codigo_mof;
    }

    public int getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(int in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public int getIn_depend_fac() {
        return in_depend_fac;
    }

    public void setIn_depend_fac(int in_depend_fac) {
        this.in_depend_fac = in_depend_fac;
    }

    public String getVc_nombre_archivo() {
        return vc_nombre_archivo;
    }

    public void setVc_nombre_archivo(String vc_nombre_archivo) {
        this.vc_nombre_archivo = vc_nombre_archivo;
    }

    public FormFile getVc_ruta_archivo_v1() {
        return vc_ruta_archivo_v1;
    }

    public void setVc_ruta_archivo_v1(FormFile vc_ruta_archivo_v1) {
        this.vc_ruta_archivo_v1 = vc_ruta_archivo_v1;
    }

    public String getNombre_Archivo_DB() {
        return NombreArchivo;
    }

    public void setNombre_Archivo_DB(String NombreArchivo) {
        this.NombreArchivo = NombreArchivo;
    }


}