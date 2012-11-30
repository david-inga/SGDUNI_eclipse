package com.sgduni.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author marco
 */
public class orpro_ta_listar_detalle_cap extends org.apache.struts.action.ActionForm {
    
  private  String c_clasi;
  private  String codigo;
  private  String c_est;
  private  String clasificacion;
  private Integer in_total;
  private Integer ocupado;
  private Integer previsto;
  private  String c_confianza;

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.c_clasi = "";
        this.codigo = "";
        this.c_est = "";
        this.clasificacion = "";
        this.in_total = 0;
        this.ocupado = 0;
        this.previsto = 0;
        this.c_confianza = "";
    }

    public String getC_clasi() {
        return c_clasi;
    }

    public void setC_clasi(String c_clasi) {
        this.c_clasi = c_clasi;
    }

    public String getC_confianza() {
        return c_confianza;
    }

    public void setC_confianza(String c_confianza) {
        this.c_confianza = c_confianza;
    }

    public String getC_est() {
        return c_est;
    }

    public void setC_est(String c_est) {
        this.c_est = c_est;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getIn_total() {
        return in_total;
    }

    public void setIn_total(Integer in_total) {
        this.in_total = in_total;
    }

    public Integer getOcupado() {
        return ocupado;
    }

    public void setOcupado(Integer ocupado) {
        this.ocupado = ocupado;
    }

    public Integer getPrevisto() {
        return previsto;
    }

    public void setPrevisto(Integer previsto) {
        this.previsto = previsto;
    }

}
