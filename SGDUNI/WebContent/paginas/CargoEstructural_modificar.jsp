<%--
    Document   : registrar 
    Created on : 03-dic-2011, 23:40:34
    Author     : marco antonio estrella cardenas
--%>
<%@ page import="com.sgduni.forms.orgen_ta_cargo_usuario" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsCargoEstructural.js");
</script>
    <div  class="tituloPag" align="center">
     <strong>Modificar el Cargo de Usuario</strong>
    </div>
	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> > <a href="listarCargoEstructural.uni">Cargos de Usuario</a> > Modificar</div>
      <div class="formBody" style="width:610px;">
         <%
         orgen_ta_cargo_usuario cargo = (orgen_ta_cargo_usuario)request.getAttribute("objCargoUsu");
         String codigo = cargo.getIn_codigo_cargo_estruc().toString();
         String nombre = cargo.getVc_nombre();
         String descripcion = cargo.getVc_descripcion();
         %>
       <div class="textAviso" id="textAviso">
       </div>
        <html:form action="modificarCargoEstructural.uni" method="POST" styleClass="formulariosA1" styleId="frmModCargoEst">
          <fieldset>
            <legend>Datos del Cargo de Usuario</legend>

            <html:hidden property="in_codigo_cargo_estruc" value="<%=codigo%>" styleId="in_codigo_cargo_estruc"></html:hidden>

            <table width="565" align="center">
                <tr>
                    <td width="101"><label>Nombre:</label></td>
                    <td >
                        <html:text property="vc_nombre" value="<%=nombre%>" styleId="vc_nombre" ></html:text>
                    </td>
                </tr>
                <tr>
                    <td width="101" valign="top"><label>Descripción:</label></td>
                    <td >
                      <html:textarea cols="45" rows="5" property="vc_descripcion" value="<%=descripcion%>" styleId="vc_descripcion" ></html:textarea>
                    </td>
                </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdModiCargoEstruct" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_modiCargoEstructural();">Modificar</a>
                   <a href="listarCargoEstructural.uni" id="cmdCancelCargoEstruct" class="BotonStan1 paddingCmd" style="text-decoration:none;">Regresar</a>
                </fieldset>
            </div>
        </html:form>
        </div>
