<%--
    Document   : registrar
    Created on : 03-dic-2011, 23:40:34
    Author     : marco antonio estrella cardenas
--%>
<%@ page import="com.sgduni.forms.orgen_ta_usuario" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsUsuario.js");
</script>
    <div  class="tituloPag" align="center">
     <strong>Modificar Usuario</strong>
    </div>
	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> > <a href="listarUsuario.uni">Usuarios</a> > Modificar</div>

      
      <div class="formBody" style="width:610px;"> 
         <%
         orgen_ta_usuario usuario =(orgen_ta_usuario)request.getAttribute("usuario");
         %>
           <div class="textAviso">
               <bean:write name="orgen_ta_usuario" property="mensaje" filter="true" />
           </div>
        <html:form action="modUsuario.uni" method="POST" styleClass="formulariosA1" styleId="frmModUsuario">
          <fieldset>
            <legend>Datos Personales</legend>
            <html:hidden property="in_codigo_usuario" value="${usuario.in_codigo_usuario}"></html:hidden>
            <html:hidden property="tipomod" value="1"></html:hidden>
            <table width="565" align="center">
            <tr>
                <td width="101"><label>Usuario:</label></td>
                <td colspan="1">
                     <html:text property="vc_usuario_oc" value="${usuario.vc_usuario}" styleId="vc_usuario_oc" disabled="true"></html:text>
                     <html:hidden property="vc_usuario" value="${usuario.vc_usuario}" styleId="vc_usuario"></html:hidden>
                </td>
                <td width="84"><label style="height:32px;">Grado Academico:</label></td>
                <td><html:text property="vc_grado_academico" styleId="vc_grado_academico" style="width:40px;" value="${usuario.vc_grado_academico}"></html:text></td>
            </tr>
            <tr>
                <td><label>Clave:</label></td>
                <td width="190"><html:password property="vc_clave" value="${usuario.vc_clave}" styleId="vc_clave"></html:password></td>
                <td width="96"><label>Repetir Clave:</label></td>
                <td><html:password property="vc_clave_dep" value="${usuario.vc_clave}" styleId="vc_clave_dep"></html:password></td>
            </tr>
            <tr>
                <td><label>Email:</label></td>
                <td><html:text property="vc_correo" styleId="vc_correo" value="${usuario.vc_correo}"></html:text></td>
                <td><label>Nombres:</label></td>
                <td><html:text property="vc_nombres" styleId="vc_nombres" value="${usuario.vc_nombres}"></html:text></td>
            </tr>
            <tr>
                <td><label>Apellido Paterno</label></td>
                <td><html:text property="vc_apellido_paterno" styleId="vc_apellido_paterno" value="${usuario.vc_apellido_paterno}"></html:text></td>
                <td><label>Apellido Materno:</label></td>
                <td><html:text property="vc_apellido_materno" styleId="vc_apellido_materno" value="${usuario.vc_apellido_materno}"></html:text></td>
            </tr>
            </table>
            </fieldset>


            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdModiUsuario" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_modiUsuarioDatos();">Modificar Datos</a>
                   <a href="listarUsuario.uni" id="cmdCancelUsuario" class="BotonStan1 paddingCmd" style="text-decoration:none;">Regresar</a>
                </fieldset>
            </div>
        </html:form>
        <html:form action="modUsuario.uni" method="POST" styleClass="formulariosA1" styleId="frmModUsuarioFirma" enctype="multipart/form-data">
            <fieldset>
            <legend>Firma</legend>
            <html:hidden property="vc_nombres" value="${usuario.vc_nombres}"></html:hidden>
            <html:hidden property="vc_apellido_paterno" value="${usuario.vc_apellido_paterno}"></html:hidden>
            <html:hidden property="in_codigo_usuario" value="${usuario.in_codigo_usuario}"></html:hidden>
            <html:hidden property="tipomod" value="2"></html:hidden>
            <table width="565" align="center">
                <tr>
              <td width="50"><label style="height:35px;">Cargar Firma como Imagen :</label></td>
              <td colspan="3" width="120">
                  <html:file property="vcfirma" styleId="vcfirma" onchange="javascript:fnl_validar_doc(this.value);"></html:file>
              </td>
            </tr>
            <tr>
              <td width="50"><label style="height:35px;">Nombre del Archivo :</label></td>
              <td colspan="3" width="120">
                <input type="hidden" id="txtnom_archivo_db" name="txtnom_archivo_db"/>
                <html:text property="vc_nombre_archivo" styleId="vc_nombre_archivo"></html:text>
              </td>
            </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdModiUsuario" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_modiUsuarioFirma();">Modificar Firma</a>
                   <a href="listarUsuario.uni" id="cmdCancelUsuario" class="BotonStan1 paddingCmd" style="text-decoration:none;">Regresar</a>
                </fieldset>
            </div>
        </html:form>
        </div>