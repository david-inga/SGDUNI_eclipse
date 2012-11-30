/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

public class orpro_ta_tramite_documentos extends org.apache.struts.action.ActionForm
{
    private int in_codigo_proc;
    private String ch_codigo_proc;
    private String dt_fecha;
    private int in_depend_facu;
    private String ch_tipo_depend_facu;
    private int in_codigo_oficio;
    private int in_codigo_usuario;
    private int in_codigo_estructura;
    private int in_codigo_rof;
    private int in_codigo_mof;
    private int in_codigo_mapro;
    private int in_codigo_resolucion;
    private String ch_estado;
    private String mensaje;
    private String vc_observacion;

    //
    private String NomArchivoMof;
    private String NomArchivoDBMof;
    private String NomArchivoRof;
    private String NomArchivoDBRof;
    private String NomArchivoMapro;
    private String NomArchivoDBMapro;
    private String NomUsuario;
    private String NomDependenFacu;

    private String NomEstadoRof;
    private int in_codigo_estado_rof;
    private String NomEstadoMof;
    private int in_codigo_estado_mof;
    private String NomEstadoMapro;
    private int in_codigo_estado_mapro;




    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
            this.in_codigo_proc=0;
            this.ch_codigo_proc="";
            this.dt_fecha="";
            this.in_depend_facu=0;
            this.ch_tipo_depend_facu="";
            this.in_codigo_oficio=0;
            this.in_codigo_usuario=0;
            this.in_codigo_estructura=0;
            this.in_codigo_rof=0;
            this.in_codigo_mof=0;
            this.in_codigo_mapro=0;
            this.in_codigo_resolucion=0;
            this.ch_estado="";
            this.mensaje="";
            this.vc_observacion="";
    }

    public String getVc_observacion() {
        return vc_observacion;
    }

    public void setVc_observacion(String vc_observacion) {
        this.vc_observacion = vc_observacion;
    }

    public int getIn_codigo_estado_mapro() {
        return in_codigo_estado_mapro;
    }

    public void setIn_codigo_estado_mapro(int in_codigo_estado_mapro) {
        this.in_codigo_estado_mapro = in_codigo_estado_mapro;
    }

    public int getIn_codigo_estado_mof() {
        return in_codigo_estado_mof;
    }

    public void setIn_codigo_estado_mof(int in_codigo_estado_mof) {
        this.in_codigo_estado_mof = in_codigo_estado_mof;
    }

    public int getIn_codigo_estado_rof() {
        return in_codigo_estado_rof;
    }

    public void setIn_codigo_estado_rof(int in_codigo_estado_rof) {
        this.in_codigo_estado_rof = in_codigo_estado_rof;
    }

    public String getNomEstadoMapro() {
        return NomEstadoMapro;
    }

    public void setNomEstadoMapro(String NomEstadoMapro) {
        this.NomEstadoMapro = NomEstadoMapro;
    }

    public String getNomEstadoMof() {
        return NomEstadoMof;
    }

    public void setNomEstadoMof(String NomEstadoMof) {
        this.NomEstadoMof = NomEstadoMof;
    }

    public String getNomEstadoRof() {
        return NomEstadoRof;
    }

    public void setNomEstadoRof(String NomEstadoRof) {
        this.NomEstadoRof = NomEstadoRof;
    }

    public String getNomArchivoDBMapro() {
        return NomArchivoDBMapro;
    }

    public void setNomArchivoDBMapro(String NomArchivoDBMapro) {
        this.NomArchivoDBMapro = NomArchivoDBMapro;
    }

    public String getNomArchivoDBMof() {
        return NomArchivoDBMof;
    }

    public void setNomArchivoDBMof(String NomArchivoDBMof) {
        this.NomArchivoDBMof = NomArchivoDBMof;
    }

    public String getNomArchivoDBRof() {
        return NomArchivoDBRof;
    }

    public void setNomArchivoDBRof(String NomArchivoDBRof) {
        this.NomArchivoDBRof = NomArchivoDBRof;
    }

    public String getNomArchivoMapro() {
        return NomArchivoMapro;
    }

    public void setNomArchivoMapro(String NomArchivoMapro) {
        this.NomArchivoMapro = NomArchivoMapro;
    }

    public String getNomArchivoMof() {
        return NomArchivoMof;
    }

    public void setNomArchivoMof(String NomArchivoMof) {
        this.NomArchivoMof = NomArchivoMof;
    }

    public String getNomArchivoRof() {
        return NomArchivoRof;
    }

    public void setNomArchivoRof(String NomArchivoRof) {
        this.NomArchivoRof = NomArchivoRof;
    }

    public String getNomDependenFacu() {
        return NomDependenFacu;
    }

    public void setNomDependenFacu(String NomDependenFacu) {
        this.NomDependenFacu = NomDependenFacu;
    }

    public String getNomUsuario() {
        return NomUsuario;
    }

    public void setNomUsuario(String NomUsuario) {
        this.NomUsuario = NomUsuario;
    }


    public String getCh_codigo_proc() {
        return ch_codigo_proc;
    }

    public void setCh_codigo_proc(String ch_codigo_proc) {
        this.ch_codigo_proc = ch_codigo_proc;
    }

    

    public String getCh_estado() {
        return ch_estado;
    }

    public void setCh_estado(String ch_estado) {
        this.ch_estado = ch_estado;
    }

    public String getCh_tipo_depend_facu() {
        return ch_tipo_depend_facu;
    }

    public void setCh_tipo_depend_facu(String ch_tipo_depend_facu) {
        this.ch_tipo_depend_facu = ch_tipo_depend_facu;
    }

    public String getDt_fecha() {
        return dt_fecha;
    }

    public void setDt_fecha(String dt_fecha) {
        this.dt_fecha = dt_fecha;
    }

    public int getIn_codigo_estructura() {
        return in_codigo_estructura;
    }

    public void setIn_codigo_estructura(int in_codigo_estructura) {
        this.in_codigo_estructura = in_codigo_estructura;
    }

    public int getIn_codigo_mapro() {
        return in_codigo_mapro;
    }

    public void setIn_codigo_mapro(int in_codigo_mapro) {
        this.in_codigo_mapro = in_codigo_mapro;
    }

    public int getIn_codigo_mof() {
        return in_codigo_mof;
    }

    public void setIn_codigo_mof(int in_codigo_mof) {
        this.in_codigo_mof = in_codigo_mof;
    }

    public int getIn_codigo_oficio() {
        return in_codigo_oficio;
    }

    public void setIn_codigo_oficio(int in_codigo_oficio) {
        this.in_codigo_oficio = in_codigo_oficio;
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

    public int getIn_codigo_rof() {
        return in_codigo_rof;
    }

    public void setIn_codigo_rof(int in_codigo_rof) {
        this.in_codigo_rof = in_codigo_rof;
    }

    public int getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(int in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public int getIn_depend_facu() {
        return in_depend_facu;
    }

    public void setIn_depend_facu(int in_depend_facu) {
        this.in_depend_facu = in_depend_facu;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

        
}

