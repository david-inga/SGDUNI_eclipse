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
public class orpro_ta_rof extends org.apache.struts.action.ActionForm {

    private int in_codigo_rof;
    private String ch_codigo_rof;
    private int in_depend_fac;
    private String ch_tipo_depend_fac;
    private String dt_fecha;
    private String vc_introduccion;
    private String vc_naturaleza_finalidad;
    private String vc_alcance;
    private int in_codigo_estructura;
    private String vc_relaciones_interinstitucionales;
    private String vc_disposiciones_finales;
    private String ch_estado;
    private Integer in_codigo_estado_ocdo;
    private Integer in_codigo_estado_a_legal;
    private int in_codigo_estado;
    private int in_codigo_usuario;
    private String mensaje;
    private String ch_codigo_estructura;
    private int in_codigo_guardado;
    private String nombreUsuario;
    private String nombreEstructura;
    private String nombreFaculDepen;
    private String nomUsuarioOCDO;
    private String nomUsuarioALegal;
    

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.in_codigo_rof=0;
        this.ch_codigo_rof="";
        this.in_depend_fac=0;
        this.ch_tipo_depend_fac="";
        this.dt_fecha="";
        //this.vc_ruta_archivo_v1="";
        this.in_codigo_estado=0;
        this.in_codigo_usuario=0;
        this.mensaje="";
        this.ch_estado="";
        this.nombreUsuario="";
        this.nombreFaculDepen="";

    }

    public int getIn_codigo_guardado() {
        return in_codigo_guardado;
    }

    public void setIn_codigo_guardado(int in_codigo_guardado) {
        this.in_codigo_guardado = in_codigo_guardado;
    }

    public String getCh_codigo_estructura() {
        return ch_codigo_estructura;
    }

    public void setCh_codigo_estructura(String ch_codigo_estructura) {
        this.ch_codigo_estructura = ch_codigo_estructura;
    }

   
    public String getVc_naturaleza_finalidad() {
        return vc_naturaleza_finalidad;
    }

    public void setVc_naturaleza_finalidad(String vc_naturaleza_finalidad) {
        this.vc_naturaleza_finalidad = vc_naturaleza_finalidad;
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

    public String getCh_codigo_rof() {
        return ch_codigo_rof;
    }

    public void setCh_codigo_rof(String ch_codigo_rof) {
        this.ch_codigo_rof = ch_codigo_rof;
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

    public int getIn_depend_fac() {
        return in_depend_fac;
    }

    public void setIn_depend_fac(int in_depend_fac) {
        this.in_depend_fac = in_depend_fac;
    }

    public Integer getIn_codigo_estado_a_legal() {
        return in_codigo_estado_a_legal;
    }

    public void setIn_codigo_estado_a_legal(Integer in_codigo_estado_a_legal) {
        this.in_codigo_estado_a_legal = in_codigo_estado_a_legal;
    }

    public Integer getIn_codigo_estado_ocdo() {
        return in_codigo_estado_ocdo;
    }

    public void setIn_codigo_estado_ocdo(Integer in_codigo_estado_ocdo) {
        this.in_codigo_estado_ocdo = in_codigo_estado_ocdo;
    }

    public int getIn_codigo_estructura() {
        return in_codigo_estructura;
    }

    public void setIn_codigo_estructura(int in_codigo_estructura) {
        this.in_codigo_estructura = in_codigo_estructura;
    }

    public String getNomUsuarioALegal() {
        return nomUsuarioALegal;
    }

    public void setNomUsuarioALegal(String nomUsuarioALegal) {
        this.nomUsuarioALegal = nomUsuarioALegal;
    }

    public String getNomUsuarioOCDO() {
        return nomUsuarioOCDO;
    }

    public void setNomUsuarioOCDO(String nomUsuarioOCDO) {
        this.nomUsuarioOCDO = nomUsuarioOCDO;
    }

    public String getNombreEstructura() {
        return nombreEstructura;
    }

    public void setNombreEstructura(String nombreEstructura) {
        this.nombreEstructura = nombreEstructura;
    }

    public String getVc_alcance() {
        return vc_alcance;
    }

    public void setVc_alcance(String vc_alcance) {
        this.vc_alcance = vc_alcance;
    }

    public String getVc_disposiciones_finales() {
        return vc_disposiciones_finales;
    }

    public void setVc_disposiciones_finales(String vc_disposiciones_finales) {
        this.vc_disposiciones_finales = vc_disposiciones_finales;
    }

    public String getVc_introduccion() {
        return vc_introduccion;
    }

    public void setVc_introduccion(String vc_introduccion) {
        this.vc_introduccion = vc_introduccion;
    }

    public String getVc_relaciones_interinstitucionales() {
        return vc_relaciones_interinstitucionales;
    }

    public void setVc_relaciones_interinstitucionales(String vc_relaciones_interinstitucionales) {
        this.vc_relaciones_interinstitucionales = vc_relaciones_interinstitucionales;
    }
}

