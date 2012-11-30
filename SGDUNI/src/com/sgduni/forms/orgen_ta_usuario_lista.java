/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author marco
 */
public class orgen_ta_usuario_lista extends org.apache.struts.action.ActionForm {
    
    private Integer in_codigo_usuario;
    private String vc_usuario;
    private String vc_clave;
    private String vc_clave_dep;
    private String vc_correo;
    private String vc_cargo;
    private String vc_nombres;
    private String vc_apellido_paterno;
    private String vc_apellido_materno;
    private String vc_estado;
    private String vc_usuario_crea;
    private String dt_fecha_crea;
    private String vc_usuario_modifica;
    private String vc_fecha_modifica;

    @Override
    public void reset(
            ActionMapping mapping,
            HttpServletRequest request)
    {
        //inicia con valores nulos, sirve para limpiar los valores ...si repetimos
        //la accion
        in_codigo_usuario = 0;
        vc_usuario = "";
        vc_clave = "";
        vc_clave_dep = "";
        vc_correo = "";
        vc_cargo = "";
        vc_nombres = "";
        vc_apellido_paterno = "";
        vc_apellido_materno = "";
        vc_estado = "";
        vc_usuario_crea = "";
        dt_fecha_crea = "";
        vc_usuario_modifica = "";
        vc_fecha_modifica = "";
    }


    private void testString(String valor,String campo,String claveErr,ActionErrors errores)
    {
        if( valor == null || valor.length() == 0 )
                errores.add(campo, new ActionMessage(claveErr));
    }

    public orgen_ta_usuario_lista() {
    }



    public String getDt_fecha_crea() {
        return dt_fecha_crea;
    }

    public void setDt_fecha_crea(String dt_fecha_crea) {
        this.dt_fecha_crea = dt_fecha_crea;
    }

    public Integer getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(Integer in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public String getVc_apellido_materno() {
        return vc_apellido_materno;
    }

    public void setVc_apellido_materno(String vc_apellido_materno) {
        this.vc_apellido_materno = vc_apellido_materno;
    }

    public String getVc_apellido_paterno() {
        return vc_apellido_paterno;
    }

    public void setVc_apellido_paterno(String vc_apellido_paterno) {
        this.vc_apellido_paterno = vc_apellido_paterno;
    }

    public String getVc_cargo() {
        return vc_cargo;
    }

    public void setVc_cargo(String vc_cargo) {
        this.vc_cargo = vc_cargo;
    }

    public String getVc_clave() {
        return vc_clave;
    }

    public void setVc_clave(String vc_clave) {
        this.vc_clave = vc_clave;
    }

    public String getVc_clave_dep() {
        return vc_clave_dep;
    }

    public void setVc_clave_dep(String vc_clave_dep) {
        this.vc_clave_dep = vc_clave_dep;
    }

    public String getVc_correo() {
        return vc_correo;
    }

    public void setVc_correo(String vc_correo) {
        this.vc_correo = vc_correo;
    }

    public String getVc_estado() {
        return vc_estado;
    }

    public void setVc_estado(String vc_estado) {
        this.vc_estado = vc_estado;
    }

    public String getVc_fecha_modifica() {
        return vc_fecha_modifica;
    }

    public void setVc_fecha_modifica(String vc_fecha_modifica) {
        this.vc_fecha_modifica = vc_fecha_modifica;
    }

    public String getVc_nombres() {
        return vc_nombres;
    }

    public void setVc_nombres(String vc_nombres) {
        this.vc_nombres = vc_nombres;
    }

    public String getVc_usuario() {
        return vc_usuario;
    }

    public void setVc_usuario(String vc_usuario) {
        this.vc_usuario = vc_usuario;
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
