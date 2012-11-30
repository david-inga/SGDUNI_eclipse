package com.sgduni.forms;

/*
 * @author JMarcos
 */
public class orgen_ta_usuario_cargo extends org.apache.struts.action.ActionForm {
    
    private Integer in_codigo_usuario;
    private Integer in_codigo_cargo;
    private String vc_nombre_usuario;
    private String vc_nombre_cargo;

    public orgen_ta_usuario_cargo()
    {

    }

    public String getVc_nombre_cargo() {
        return vc_nombre_cargo;
    }

    public void setVc_nombre_cargo(String vc_nombre_cargo) {
        this.vc_nombre_cargo = vc_nombre_cargo;
    }

    public String getVc_nombre_usuario() {
        return vc_nombre_usuario;
    }

    public void setVc_nombre_usuario(String vc_nombre_usuario) {
        this.vc_nombre_usuario = vc_nombre_usuario;
    }



    public Integer getIn_codigo_cargo() {
        return in_codigo_cargo;
    }

    public void setIn_codigo_cargo(Integer in_codigo_cargo) {
        this.in_codigo_cargo = in_codigo_cargo;
    }

    public Integer getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(Integer in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }


}
