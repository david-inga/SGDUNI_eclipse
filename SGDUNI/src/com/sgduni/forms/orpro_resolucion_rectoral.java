/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class orpro_resolucion_rectoral extends org.apache.struts.action.ActionForm
{

private int in_codigo_resolucion;
private String ch_codigo_resolucion;
private String dt_fecha;
private String vc_nombre_archivo;
private String vc_ruta_archivo;
private String ch_estado;
private String dt_fecha_creada;
private int in_codigo_proc;
private String ch_codigo_proc;
private int in_codigo_usuario;

private String nomArchivoDB;
private String mensaje;
private String nomUsuario;
    
  @Override
    public void reset(ActionMapping mapping,
            HttpServletRequest request)
    {
       this.in_codigo_resolucion=0;
       this.ch_codigo_resolucion="";
       this.dt_fecha="";
       this.vc_nombre_archivo="";

       this.ch_estado="";
       this.dt_fecha_creada="";
       this.in_codigo_proc=0;
       this.ch_codigo_proc="";
       this.in_codigo_usuario=0;

        this.nomArchivoDB="";
        //this.mensaje="";
        this.nomUsuario="";
    }

    public String getVc_ruta_archivo() {
        return vc_ruta_archivo;
    }

    public void setVc_ruta_archivo(String vc_ruta_archivo) {
        this.vc_ruta_archivo = vc_ruta_archivo;
    }

    public String getCh_codigo_proc() {
        return ch_codigo_proc;
    }

    public void setCh_codigo_proc(String ch_codigo_proc) {
        this.ch_codigo_proc = ch_codigo_proc;
    }

  

    public String getCh_codigo_resolucion() {
        return ch_codigo_resolucion;
    }

    public void setCh_codigo_resolucion(String ch_codigo_resolucion) {
        this.ch_codigo_resolucion = ch_codigo_resolucion;
    }

    public String getCh_estado() {
        return ch_estado;
    }

    public void setCh_estado(String ch_estado) {
        this.ch_estado = ch_estado;
    }

    public String getDt_fecha() {
        return dt_fecha;
    }

    public void setDt_fecha(String dt_fecha) {
        this.dt_fecha = dt_fecha;
    }

    public String getDt_fecha_creada() {
        return dt_fecha_creada;
    }

    public void setDt_fecha_creada(String dt_fecha_creada) {
        this.dt_fecha_creada = dt_fecha_creada;
    }

    public int getIn_codigo_proc() {
        return in_codigo_proc;
    }

    public void setIn_codigo_proc(int in_codigo_proc) {
        this.in_codigo_proc = in_codigo_proc;
    }

    public int getIn_codigo_resolucion() {
        return in_codigo_resolucion;
    }

    public void setIn_codigo_resolucion(int in_codigo_resolucion) {
        this.in_codigo_resolucion = in_codigo_resolucion;
    }

    public int getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(int in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNomArchivoDB() {
        return nomArchivoDB;
    }

    public void setNomArchivoDB(String nomArchivoDB) {
        this.nomArchivoDB = nomArchivoDB;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getVc_nombre_archivo() {
        return vc_nombre_archivo;
    }

    public void setVc_nombre_archivo(String vc_nombre_archivo) {
        this.vc_nombre_archivo = vc_nombre_archivo;
    }




}
