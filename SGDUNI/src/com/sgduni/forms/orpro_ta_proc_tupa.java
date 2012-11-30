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
public class orpro_ta_proc_tupa extends org.apache.struts.action.ActionForm
{
   private String nomproc;
   private String nomreq;
   private Integer de_tramitacion_porc;
   private Integer de_tramitacion_sol;
   private String ch_calif_automa;
   private String ch_evaluacion_previa;
   private Integer in_plazo_resolver_dias;
   private String vc_inicio_procedimiento;
   private String vc_reconsideracion;
   private String vc_apelacion;
   private Integer in_autoridad_com_resolver;
   private String nombre_autoridad;
   private Integer in_codigo_det;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.de_tramitacion_porc = 0;
        this.de_tramitacion_sol = 0;
        this.ch_calif_automa = "";
        this.ch_evaluacion_previa = "";
        this.in_plazo_resolver_dias = 0;
        this.vc_inicio_procedimiento = "";
        this.vc_reconsideracion = "";
        this.vc_apelacion = "";
        this.in_autoridad_com_resolver = 0;
        this.in_codigo_det = 0;
        this.nombre_autoridad = "";
        this.nomproc = "";
        this.nomreq = "";
    }

    public String getNomproc() {
        return nomproc;
    }

    public void setNomproc(String nomproc) {
        this.nomproc = nomproc;
    }

    public String getNomreq() {
        return nomreq;
    }

    public void setNomreq(String nomreq) {
        this.nomreq = nomreq;
    }

    public String getNombre_autoridad() {
        return nombre_autoridad;
    }

    public void setNombre_autoridad(String nombre_autoridad) {
        this.nombre_autoridad = nombre_autoridad;
    }
   
    public String getCh_calif_automa() {
        return ch_calif_automa;
    }

    public void setCh_calif_automa(String ch_calif_automa) {
        this.ch_calif_automa = ch_calif_automa;
    }

    public String getCh_evaluacion_previa() {
        return ch_evaluacion_previa;
    }

    public void setCh_evaluacion_previa(String ch_evaluacion_previa) {
        this.ch_evaluacion_previa = ch_evaluacion_previa;
    }

    public Integer getDe_tramitacion_porc() {
        return de_tramitacion_porc;
    }

    public void setDe_tramitacion_porc(Integer de_tramitacion_porc) {
        this.de_tramitacion_porc = de_tramitacion_porc;
    }

    public Integer getDe_tramitacion_sol() {
        return de_tramitacion_sol;
    }

    public void setDe_tramitacion_sol(Integer de_tramitacion_sol) {
        this.de_tramitacion_sol = de_tramitacion_sol;
    }

    public Integer getIn_autoridad_com_resolver() {
        return in_autoridad_com_resolver;
    }

    public void setIn_autoridad_com_resolver(Integer in_autoridad_com_resolver) {
        this.in_autoridad_com_resolver = in_autoridad_com_resolver;
    }

    public Integer getIn_codigo_det() {
        return in_codigo_det;
    }

    public void setIn_codigo_det(Integer in_codigo_det) {
        this.in_codigo_det = in_codigo_det;
    }

    public Integer getIn_plazo_resolver_dias() {
        return in_plazo_resolver_dias;
    }

    public void setIn_plazo_resolver_dias(Integer in_plazo_resolver_dias) {
        this.in_plazo_resolver_dias = in_plazo_resolver_dias;
    }

    public String getVc_apelacion() {
        return vc_apelacion;
    }

    public void setVc_apelacion(String vc_apelacion) {
        this.vc_apelacion = vc_apelacion;
    }

    public String getVc_inicio_procedimiento() {
        return vc_inicio_procedimiento;
    }

    public void setVc_inicio_procedimiento(String vc_inicio_procedimiento) {
        this.vc_inicio_procedimiento = vc_inicio_procedimiento;
    }

    public String getVc_reconsideracion() {
        return vc_reconsideracion;
    }

    public void setVc_reconsideracion(String vc_reconsideracion) {
        this.vc_reconsideracion = vc_reconsideracion;
    }
    
}
