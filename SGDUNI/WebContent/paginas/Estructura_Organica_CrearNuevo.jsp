<%--
    Document   : Mof_nuevo
    Created on : 10-ene-2012, 22:25:50
    Author     : Jmarcos
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_dependencia" %>
<%@ page import="com.sgduni.forms.orgen_ta_facultad" %>
<%@ page import="com.sgduni.forms.orpro_ta_mof" %>
<%@ page import="com.sgduni.utilitarios.ContarCaracteresVista" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
  String  Rol=session.getAttribute("xrol").toString().toUpperCase().trim();

  String tipoDepenFacu=session.getAttribute("xtipodepen_facul").toString();
  String idDepenFacu=session.getAttribute("xiddepen_facul").toString();
  String codigo_generado = request.getAttribute("nuevo_codigo_generado").toString();
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

<%ArrayList<orgen_ta_dependencia> dependencias = (ArrayList)request.getAttribute("dependencias");%>
<%ArrayList<orgen_ta_facultad> facultades = (ArrayList)request.getAttribute("facultades");%>

<div  class="tituloPag" align="center">
     <strong>Crear Nueva Estructura Organica</strong></div>
    <!--
	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> > <a href="organigramaListar.uni">Estructuras Organicas Pendientes</a> > Crear </div>
   -->
      <div class="formBody" style="width:610px;">
           <div class="textAviso">
               <bean:write name="orgen_ta_estructura_organica" property="mensaje" filter="true" />
           </div>
           <div class="textAviso">
               <bean:write name="orgen_ta_estructura_organica" property="mensaje2" filter="true" />
           </div>
           <html:form action="guardarEstructuraOrganica.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiEstOrg" enctype="multipart/form-data">
          <fieldset>
            <legend id="dvl_xuser">Datos para la Estructura Organica
            </legend>
            <table width="596" align="center">
            <tr>
                <td width="89" valign="top"><label>Codigo :</label></td>
                <td align="left" valign="top">
                    <html:hidden property="ch_codigo_estructura" styleId="ch_codigo_estructura" value="<%=codigo_generado%>"></html:hidden>
                    <html:text property="ch_codigo_estructura" disabled="true" styleId="ch_codigo_estructura" value="<%=codigo_generado%>"></html:text>
                </td>
              <td width="94" align="right" valign="top">
                 <label>Fecha :</label>
                </td>
              <td width="119" align="left" valign="top">
                <html:text property="dt_fecha" readonly="true" styleId="dt_fecha" size="16" style="width:85%" value=""></html:text>
               </td>
              <td width="35" align="left" valign="middle"><img onclick="displayCalendar(document.forms[0].dt_fecha,'yyyy-mm-dd',this)" src="fileproject/img/sistema/formularios/ico_calendario.gif" width="16" height="16" style="cursor:pointer;"></td>
            </tr>
            <tr>
              <td height="26"><label>Cargar Archivo :</label></td>
              <td>
                <html:file property="vc_ruta_archivo" styleId="vc_ruta_archivo" onchange="javascript:fnl_validar_doc(this.value);">

                </html:file>
               </td>
              <td align="right">
                  <html:hidden property="in_codigo_oficio"  styleId="in_codigo_oficio" style="width:25px;"></html:hidden>
                    <a href="javascript:;" id="cmdNuevoUsuario" class="BotonStan2" style="text-decoration:none;" onclick="javascript:fnl_adjuntar_archivo();" title="Oficio Circular">
                        Adjuntar Oficio
                   </a>
              </td>
               <td width="119" align="left">
                  <div id="dvl_in_codigo_oficio" style="text-align:left;">
                       ...
                   </div>
                </td>
                <td width="35" align="left">
                  

                </td>
            </tr>
            <tr>
              <td height="36">
              <label>Nombre Doc.</label>
              </td>
              <td >
                 <input type="hidden" id="txtnom_archivo_db" name="txtnom_archivo_db"/>
                 <html:text property="vc_nombre_archivo" styleId="vc_nombre_archivo"></html:text>
              </td>
              <td colspan="1" align="right" >
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
            <%
            String EventoScript="fnl_regiEstructuraOrganica()";
            if(Rol.equals("ROL01"))
			{//SOLO SI ES OCDO
			%>
            <tr>
                <td height="30" valign="top"><label>Para :</label></td>
                <td width="235" align="left" class="txtStyleAd2">
                   <html:hidden property="in_facu_depend" styleId="in_facu_depend" value=""></html:hidden>
                   
                   <html:radio property="ch_tipo_fac_depend"  styleId="ch_tipo_fac_depend" value="f" style="width:15px;cursor:pointer;" onclick="javascript:fnl_Fact_Dep(this.value);"></html:radio>
                   Facultad 
                   <br/>
                   <html:radio property="ch_tipo_fac_depend" styleId="ch_tipo_fac_depend" value="d" style="width:15px;cursor:pointer;" onclick="javascript:fnl_Fact_Dep(this.value);"></html:radio>                   
                   Dependencia 
                 </td>
                <td colspan="3">
                <select name="lstDependencia" id="lstDependencia" style="display:none"   onchange="javascript:fnl_In_Depend_Fac(this.value);">
                         <option value="0"> - Seleccione el Dependencia -</option>
                         <%
                           if( dependencias.size() != 0 )
                           {
                              for(orgen_ta_dependencia dependencia : dependencias)
                              {
                                ContarCaracteresVista textShow = new ContarCaracteresVista(dependencia.getVc_nombre());
                         %>
                         <option value="<%=dependencia.getIn_codigo_dependencia()%>" title="<%=dependencia.getVc_nombre() %>" > <%=textShow.getTexMax(20, "...") %> </option>
                         <%
                              }
                           }
                         %>
                      </select>

                      <select name="lstFacultad" id="lstFacultad" style="display:none" onchange="javascript:fnl_In_Depend_Fac(this.value);">
                         <option value="0"> - Seleccione la Facultad -</option>
                         <%
                            if(facultades.size() != 0)
                            {
                              for(orgen_ta_facultad facultad : facultades)
                              {
                                  ContarCaracteresVista textShow=new ContarCaracteresVista(facultad.getVc_nombre());
                         %>
                         <option value="<%=facultad.getIn_codigo_facultad()%>" title="<%=facultad.getVc_nombre()%>" > <%=textShow.getTexMax(20, "...") %></option>
                         <%
                              }
                            }
                         %>
                      </select>
                </td>
              </tr>
             <%
			}else{
                EventoScript="fnl_regiEstructuraOrganicaUsuario()";
			 %>
              <html:hidden property="in_facu_depend" styleId="in_facu_depend" value="<%=idDepenFacu%>"></html:hidden>
              <html:hidden property="ch_tipo_fac_depend" styleId="ch_tipo_fac_depend" value="<%=tipoDepenFacu%>"></html:hidden>
             <%
			}
			 %>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="nuevoEstructuraOrganica.uni?" id="cmdNuevoUsuario" class="BotonStan1 paddingCmd" style="text-decoration:none;">Nuevo</a>
                   <a href="javascript:void(0);" id="cmdRegiUsuario" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:<%=EventoScript%>;">Guardar</a>
                   <a href="organigramaListar.uni" id="cmdCancelUsuario" class="BotonStan1 paddingCmd" style="text-decoration:none;">Regresar</a>
                </fieldset>
            </div>
        </html:form>
        </div>
<%
}//FIN - SOLO PARA USUARIOS OCDO Y USUARIO
%>
