package com.sgduni.forms;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Sistemas
 */


public class orpro_oficio_circular extends org.apache.struts.action.ActionForm
{

    private int in_codigo_oficio;
    private int in_codigo_usuario;
    private String ch_codigo_oficio;
    private String dt_fecha;
    private String vc_ciudad;
    private String vc_nombre_anio;
    private String vc_cuerpo_doc;
    //private Date dt_fecha_web;
    //private String vc_ruta_doc;
    private String ch_estado;
    private int in_usuario_emisor;
    private String nombre_usuario_emisor;
    private String nombre_usuario_receptor;
    private String mensaje;
    private String nom_usuario;
    private String cargo_usuario;
    private int numDiasTranscurridos;
    //private String estado_oficio;
    //private boolean opcion_modificar;
    private String nombreDependFacu;
    private Integer in_codigo_estado;
    private Integer in_cod_fac_dep;
    private String ch_tipo_fac_dep;



    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.in_codigo_oficio=0;
        this.in_codigo_usuario=0;
        //this.ch_codigo_oficio="";
        //this.vc_nombre_doc="";
        this.ch_codigo_oficio = "";
        this.dt_fecha="";
        this.vc_ciudad = "";
        this.vc_nombre_anio = "";
        this.vc_cuerpo_doc = "";
        this.in_usuario_emisor = 0;
        //this.vc_ruta_doc = "";
        this.ch_estado="";
        this.mensaje="";
        this.nom_usuario = "";
    }

    public String getNombre_usuario_emisor() {
        return nombre_usuario_emisor;
    }

    public void setNombre_usuario_emisor(String nombre_usuario_emisor) {
        this.nombre_usuario_emisor = nombre_usuario_emisor;
    }

    public String getNombre_usuario_receptor() {
        return nombre_usuario_receptor;
    }

    public void setNombre_usuario_receptor(String nombre_usuario_receptor) {
        this.nombre_usuario_receptor = nombre_usuario_receptor;
    }

    public String getCh_tipo_fac_dep() {
        return ch_tipo_fac_dep;
    }

    public void setCh_tipo_fac_dep(String ch_tipo_fac_dep) {
        this.ch_tipo_fac_dep = ch_tipo_fac_dep;
    }

    public Integer getIn_cod_fac_dep() {
        return in_cod_fac_dep;
    }

    public void setIn_cod_fac_dep(Integer in_cod_fac_dep) {
        this.in_cod_fac_dep = in_cod_fac_dep;
    }

    public Integer getIn_codigo_estado() {
        return in_codigo_estado;
    }

    public void setIn_codigo_estado(Integer in_codigo_estado) {
        this.in_codigo_estado = in_codigo_estado;
    }
    
    public int getNumDiasTranscurridos() {
        return numDiasTranscurridos;
    }

    public void setNumDiasTranscurridos(int numDiasTranscurridos) {
        this.numDiasTranscurridos = numDiasTranscurridos;
    }

    public String getNombreDependFacu() {
        return nombreDependFacu;
    }

    public void setNombreDependFacu(String nombreDependFacu) {
        this.nombreDependFacu = nombreDependFacu;
    }

    

    public String getCargo_usuario() {
        return cargo_usuario;
    }

    public void setCargo_usuario(String cargo_usuario) {
        this.cargo_usuario = cargo_usuario;
    }


    public String getNom_usuario() {
        return nom_usuario;
    }

    public void setNom_usuario(String nom_usuario) {
        this.nom_usuario = nom_usuario;
    }

    public String getCh_codigo_oficio() {
        return ch_codigo_oficio;
    }

    public void setCh_codigo_oficio(String ch_codigo_oficio) {
        this.ch_codigo_oficio = ch_codigo_oficio;
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

    public int getIn_codigo_oficio() {
        return in_codigo_oficio;
    }

    public void setIn_codigo_oficio(int in_codigo_oficio) {
        this.in_codigo_oficio = in_codigo_oficio;
    }

    public int getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(int in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public int getIn_usuario_emisor() {
        return in_usuario_emisor;
    }

    public void setIn_usuario_emisor(int in_usuario_emisor) {
        this.in_usuario_emisor = in_usuario_emisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getVc_ciudad() {
        return vc_ciudad;
    }

    public void setVc_ciudad(String vc_ciudad) {
        this.vc_ciudad = vc_ciudad;
    }

    public String getVc_cuerpo_doc() {
        return vc_cuerpo_doc;
    }

    public void setVc_cuerpo_doc(String vc_cuerpo_doc) {
        this.vc_cuerpo_doc = vc_cuerpo_doc;
    }

    public String getVc_nombre_anio() {
        return vc_nombre_anio;
    }

    public void setVc_nombre_anio(String vc_nombre_anio) {
        this.vc_nombre_anio = vc_nombre_anio;
    }
}
