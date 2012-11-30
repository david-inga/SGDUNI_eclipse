/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.forms;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
/**
 *
 * @author Sistemas
 */
public class orpro_ta_versiones_rof extends org.apache.struts.action.ActionForm
{

    private int in_codigo_versiones;
    private int in_codigo_rof;
    private int in_codigo_estado;

    public int getIn_codigo_estado() {
        return in_codigo_estado;
    }

    public void setIn_codigo_estado(int in_codigo_estado) {
        this.in_codigo_estado = in_codigo_estado;
    }
    private String vc_nombre_archivo;
    private FormFile vc_ruta_archivo;
    private String vc_usuario_crea;
    private String dt_fecha_crea;
    private String ch_estado;
    private int in_codigo_usuario;
    private String mensaje;

    private String nomUsuario;
    private String nomArchivoDB;
    private String nomEstado;

    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.in_codigo_versiones=0;
        this.in_codigo_rof=0;
        this.vc_nombre_archivo="";
        //this.vc_ruta_archivo="";
        this.vc_usuario_crea="";
        this.dt_fecha_crea="";
        this.ch_estado="";
        this.in_codigo_usuario=0;
        this.mensaje="";
        this.nomArchivoDB="";
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

    public int getIn_codigo_versiones() {
        return in_codigo_versiones;
    }

    public void setIn_codigo_versiones(int in_codigo_versiones) {
        this.in_codigo_versiones = in_codigo_versiones;
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

    public String getVc_usuario_crea() {
        return vc_usuario_crea;
    }

    public void setVc_usuario_crea(String vc_usuario_crea) {
        this.vc_usuario_crea = vc_usuario_crea;
    }


}
