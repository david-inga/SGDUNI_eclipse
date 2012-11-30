<%--
    Document   : Mof_nuevo
    Created on : 10-ene-2012, 22:25:50
    Author     : Jmarcos
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="com.sgduni.forms.orgen_ta_estructura_organica" %>
<%@ page import="com.sgduni.utilitarios.ContarCaracteresVista" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
  String  Rol=session.getAttribute("xrol").toString().toUpperCase().trim();
%>

<%
if(Rol.equals("ROL01") || Rol.equals("ROL02"))
{//SOLO PARA USUARIOS OCDO Y USUARIO
%>
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsEstructura_Organica.js");
</script>
<link type="text/css" rel="stylesheet" href="fileproject/css/calendarioSelct/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<script type="text/javascript" src="fileproject/js/dhtmlgoodies_calendar.js?random=20060118"></script>

<%
  orgen_ta_estructura_organica objEstructuraOrganica  = (orgen_ta_estructura_organica)request.getAttribute("EstructuraOrganica");
  String in_codigo_estructura = objEstructuraOrganica.getIn_codigo_estructura().toString();
%>

    <div  class="tituloPag" align="center">
     <strong>Modificar Estructura Organica</strong></div>
	<div id="localizador"><a href="menu.uni">Inicio</a> > <a href="organigramaListar.uni">Estructuras Organicas Pendientes</a> > Modificar </div>

      <div class="formBody" style="width:610px;">
           <div class="textAviso">
               <bean:write name="orgen_ta_estructura_organica" property="mensaje" filter="true" />
           </div>
           <html:form action="modificarEstructuraOrganica.uni" method="POST" styleClass="formulariosA1" styleId="frmModEstOrg" enctype="multipart/form-data">
          <fieldset>
            <legend id="dvl_xuser">Datos para la Estructura Organica
            </legend>
            <table width="596" align="center">
            <tr>
              <td width="114" valign="top"><label>Codigo :</label>
                <html:hidden property="in_codigo_estructura" styleId="in_codigo_estructura" value="<%=in_codigo_estructura%>"></html:hidden>
              </td>
              <td align="left" valign="top">
                    <html:hidden property="ch_codigo_estructura" styleId="ch_codigo_estructura" value="<%=objEstructuraOrganica.getCh_codigo_estructura()%>"></html:hidden>
                    <html:text property="ch_codigo_estructura" disabled="true" styleId="ch_codigo_estructura" value="<%=objEstructuraOrganica.getCh_codigo_estructura()%>"></html:text>
              </td>
              <td width="57" valign="top">
                 <label>Fecha :</label>
              </td>
              <td width="131" align="left" valign="top">
                    <html:text property="dt_fecha" readonly="true" styleId="dt_fecha" size="16" style="width:85%" value="<%=objEstructuraOrganica.getDt_fecha()%>" ></html:text>
              </td>
              <td width="21" align="left" valign="middle">
                  <img onclick="displayCalendar(document.forms[0].dt_fecha,'yyyy-mm-dd',this)" src="fileproject/img/sistema/formularios/ico_calendario.gif" width="16" height="16" style="cursor:pointer;">
              </td>
            </tr>
            <tr>
              <td><label>Cargar Archivo :</label></td>
              <td colspan="4">
                    <html:file property="vc_ruta_archivo" styleId="vc_ruta_archivo" onchange="javascript:fnl_validar_doc(this.value);" ></html:file>
               </td>
            </tr>
            <tr>
              <td height="30">
              <label>Nombre Doc.</label>
              </td>
              <td >
                <input type="hidden" id="txtnom_archivo_db" name="txtnom_archivo_db"/>
                    <html:text property="vc_nombre_archivo" styleId="vc_nombre_archivo"></html:text>
              </td>
              <td colspan="1" >
              <label>Orientación:</label>
              </td>
              <td colspan="2" >
                    <select id="in_formato" name="in_formato">
                        <option value="0"  >Seleccione...</option>
                        <option value="1"  >Vertical</option>
                        <option value="2" >Horizontal</option>
                    </select>
              </td>
              </tr>
            </table>
            </fieldset>
            <div align="center">             
            <fieldset>
                 <legend></legend>
                   <a href="javascript:void(0);" id="cmdModificarEO" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_modificarEstructuraOrganica();">Modificar</a>
                   <a href="organigramaListar.uni" id="cmdCancel" class="BotonStan1 paddingCmd" style="text-decoration:none;">Regresar</a>
                </fieldset>
            </div>
        </html:form>
        </div>
<%
}//FIN - SOLO PARA USUARIOS OCDO Y USUARIO
%>


