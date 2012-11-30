<%--
    Document   : registrar
    Created on : 03-dic-2011, 23:40:34
    Author     : marco antonio estrella cardenas
--%>
<%@ page import="com.sgduni.forms.orgen_ta_usuario" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsUsuario.js");
</script>
    <div  class="tituloPag" align="center">
     <strong>Registrar Usuario</strong>
    </div>
	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> > <a href="listarUsuario.uni">Usuarios</a> > Registrar</div>

      <div class="formBody" style="width:610px;">
           <div class="textAviso">
               <bean:write name="orgen_ta_usuario" property="mensaje" filter="true" />
           </div>
        <html:form action="registrarUsuario.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiUsuario" enctype="multipart/form-data">
          <fieldset>
            <legend id="dvl_xuser">Datos de Usuario</legend>            
            <table width="565" align="center">
            <tr>
                <td width="101" valign="top"><label>Usuario:</label></td>
                <td align="left" valign="top">
                  <html:text property="vc_usuario" styleId="vc_usuario" onblur="javascript:fnl_existeUsuario(this.value);"/>                  
                </td>
                 <td width="84"><label style="height:32px;">Grado Academico:</label></td>
                 <td><html:text property="vc_grado_academico" styleId="vc_grado_academico" style="width:40px;" value="Ing."></html:text></td>
            </tr>
            <tr>
                <td><label>Clave:</label></td>
                <td width="190"><html:password property="vc_clave" styleId="vc_clave"></html:password></td>
                <td width="84"><label>Repetir Clave:</label></td>
                <td><html:password property="vc_clave_dep" styleId="vc_clave_dep"></html:password></td>
            </tr>
            <tr>
                <td><label>Email:</label></td>
                <td><html:text property="vc_correo" styleId="vc_correo" onblur="javascript:fnl_correoUsuario(this.value);" ></html:text></td>
                <td><label>Nombres</label></td>
                <td><html:text property="vc_nombres" styleId="vc_nombres"></html:text></td>
            </tr>
            <tr>                
                <td><label>Apellido Paterno</label></td>
                <td><html:text property="vc_apellido_paterno" styleId="vc_apellido_paterno"></html:text></td>
                <td><label>Apellido Materno</label></td>
                <td><html:text property="vc_apellido_materno" styleId="vc_apellido_materno"></html:text></td>
            </tr>
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
                   <a href="javascript:;" id="cmdNuevoUsuario" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_nuevoUsuario();">Limpiar</a>
                   <a href="javascript:;" id="cmdRegiUsuario" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiUsuario();">Registrar</a>
                   <a href="listarUsuario.uni" id="cmdCancelUsuario" class="BotonStan1 paddingCmd" style="text-decoration:none;">Regresar</a>
                </fieldset>
            </div>
        </html:form>
        </div>