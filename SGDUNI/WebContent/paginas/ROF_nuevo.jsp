<%-- 
    Document   : Rof_nuevo
    Created on : 01-ene-2012, 17:38:25
    Author     : Sistemas
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_dependencia" %>
<%@ page import="com.sgduni.forms.orgen_ta_facultad" %>
<%//@ page import="com.sgduni.forms.orgen_ta_estado" %>
<%@ page import="com.sgduni.utilitarios.ContarCaracteresVista" %>
<%@ page import="com.sgduni.forms.orpro_ta_rof" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
  String  Rol=(String)session.getAttribute("xrol");
  Rol=Rol.trim().toUpperCase();
  String tipoDepenFacu=session.getAttribute("xtipodepen_facul").toString();
  String idDepenFacu=session.getAttribute("xiddepen_facul").toString();
  orpro_ta_rof forRof=(orpro_ta_rof)request.getAttribute("forRof");
%>
<%;
if(Rol.equals("ROL01") || Rol.equals("ROL02"))
 {//SOLO PARA USUARIOS OCDO Y USUARIO
%>
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsRof.js");
</script>
<link type="text/css" rel="stylesheet" href="fileproject/css/calendarioSelct/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<script type="text/javascript" src="fileproject/js/dhtmlgoodies_calendar.js?random=20060118"></script>
<%ArrayList<orgen_ta_dependencia> dependencias=(ArrayList)request.getAttribute("dependencias");%>
<%ArrayList<orgen_ta_facultad> facultad=(ArrayList)request.getAttribute("facultad");%>
<%//ArrayList<orgen_ta_estado> estados=(ArrayList)request.getAttribute("estados");%>
<div  class="tituloPag" align="center">
     <strong>Documento Nuevo ROF</strong></div>
	<div id="localizador"><a href="menu.uni">Inicio</a> > <a href="listarRof.uni">ROF</a> > Nuevo</div>

      <div class="formBody" style="width:610px;">
           <div class="textAviso">
               <bean:write name="orpro_ta_rof" property="mensaje" filter="true" />
           </div>
           <html:form action="rofGuardar.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiDocRof" enctype="multipart/form-data">
          <fieldset>
            <legend id="dvl_xuser">Datos Documento ROF
            </legend>
            <table width="596" align="center">
            <tr>
                <td width="114" valign="top"><label>Codigo ROF :</label></td>
                <td align="left" valign="top">
                    <input type="hidden" name="in_codigo_rof" id="in_codigo_rof" value="<%=forRof.getIn_codigo_rof()%>" />
                    <html:text property="ch_codigo_rof" styleId="ch_codigo_rof" value="<%=forRof.getCh_codigo_rof()%>"></html:text>
                </td>
              <td width="57" valign="top">
                 <label>Fecha :</label>
                </td>
              <td width="131" align="left" valign="top">
                  <html:text property="dt_fecha" readonly="true" styleId="dt_fecha" size="16" style="width:85%" value="<%=forRof.getDt_fecha()%>"></html:text>
               </td>
              <td width="21" align="left" valign="middle"><img onclick="displayCalendar(document.forms[0].dt_fecha,'dd/mm/yyyy',this)" src="fileproject/img/sistema/formularios/ico_calendario.gif" width="16" height="16" style="cursor:pointer;"></td>
            </tr>
            <tr>
              <td><label>Cargar Archivo :</label></td>
              <td colspan="4">
                    <html:file property="vc_ruta_archivo_v1" styleId="vc_ruta_archivo_v1" onchange="javascript:fnl_validar_doc(this.value);"></html:file>
                    <span class="linkStyle2"><a href="documentos/rof/plantilla/PlantillaRof.docx" target="_blank">DESCARGAR PLANTILLA</a></span>
               </td>
              </tr>
            <tr>
              <td>
              <label>Nombre Doc.</label>
              </td>
              <td colspan="4">
                  <input type="hidden" id="txtnom_archivo_db" name="txtnom_archivo_db" value="<%=forRof.getNombre_Archivo_DB()%>"/>
                  <html:text property="vc_nombre_archivo" styleId="vc_nombre_archivo" value="<%=forRof.getVc_nombre_archivo()%>"></html:text>                  
              </td>
              </tr>
            <%
            String EventoScript="fnl_regiRof()";
            if(Rol.equals("ROL01"))
			{//SOLO SI ES OCDO
			%>
              
            <tr>
                <td><label>Para :</label></td>
                <td width="249">
                   <span style="padding:2px; float:left; font-size:12px;">
                       Facultad <html:radio property="ch_tipo_depend_fac" styleId="ch_tipo_depend_fac" value="f" style="width:15px;cursor:pointer;" onclick="javascript:fnl_Fact_Dep(this.value);"></html:radio>
                   </span>
                   <html:hidden property="in_depend_fac" styleId="in_depend_fac" value="<%=String.valueOf(forRof.getIn_depend_fac())%>"></html:hidden>
                   <span style="padding:2px; float:left; font-size:12px; margin-left:5px;">
                     Dependecia <html:radio property="ch_tipo_depend_fac" styleId="ch_tipo_depend_fac" value="d" style="width:15px;cursor:pointer;" onclick="javascript:fnl_Fact_Dep(this.value);"></html:radio>
                   </span>
                       <% if(forRof.getCh_tipo_depend_fac()!=null)
                        {
                        %>
                        <script language="javascript" type="text/javascript">
                          var tidepfac="<%=forRof.getCh_tipo_depend_fac().trim()%>";
                            if(tidepfac=="f"){
                                document.forms[0].ch_tipo_depend_fac[0].checked=true;
                            }else if(tidepfac=="d"){
                                document.forms[0].ch_tipo_depend_fac[1].checked=true;
                            }
                         </script>
                        <%
                        }
                        %>
                 </td>
                <td colspan="3">
                      <select name="lstDependencia" id="lstDependencia" style="<% if(forRof.getCh_tipo_depend_fac()!=null && forRof.getCh_tipo_depend_fac().equals("d")){out.print("display:block;");}else{out.print("display:none;");}%>" onchange="javascript:fnl_In_Depend_Fac(this.value);">
                         <option value="0"> - Seleccione el Dependencia -</option>
                         <%                            
                              for(orgen_ta_dependencia lisDep:dependencias)
                                  {
                                  ContarCaracteresVista texShow=new ContarCaracteresVista(lisDep.getVc_nombre());
                         %>
                           <option value="<%=lisDep.getIn_codigo_dependencia()%>" <% if(forRof.getCh_tipo_depend_fac()!=null && forRof.getCh_tipo_depend_fac().equals("d") && forRof.getIn_depend_fac()==lisDep.getIn_codigo_dependencia()){out.print("selected=\"true\"");}%> title="<%=lisDep.getVc_nombre()%>"><%=texShow.getTexMax(25,null)%></option>
                         <%
                              }
                           
                         %>
                      </select>

                      <select name="lstFacultad" id="lstFacultad" style="<% if(forRof.getCh_tipo_depend_fac()!=null && forRof.getCh_tipo_depend_fac().equals("f")){out.print("display:block;");}else{out.print("display:none;");}%>" onchange="javascript:fnl_In_Depend_Fac(this.value);">
                         <option value="0"> - Seleccione la Facultad -</option>
                         <%
                              for(orgen_ta_facultad lisFac:facultad)
                                  {
                                  ContarCaracteresVista texShow=new ContarCaracteresVista(lisFac.getVc_nombre());
                         %>
                           <option value="<%=lisFac.getIn_codigo_facultad()%>" <% if(forRof.getCh_tipo_depend_fac()!=null && forRof.getCh_tipo_depend_fac().equals("f") && forRof.getIn_depend_fac()==lisFac.getIn_codigo_facultad()){out.print("selected=\"true\"");}%> title="<%=lisFac.getVc_nombre()%>"><%=texShow.getTexMax(25,null)%></option>
                         <%
                              }

                         %>
                      </select>
                </td>
              </tr>
             <%
			}else{
                EventoScript="fnl_regiRofUsuario()";
			 %>
              <html:hidden property="in_depend_fac" styleId="in_depend_fac" value="<%=idDepenFacu%>"></html:hidden>
              <html:hidden property="ch_tipo_depend_fac" styleId="ch_tipo_depend_fac" value="<%=tipoDepenFacu%>"></html:hidden>
             <%
			}
			 %> 
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="nuevoRof.uni?" id="cmdNuevoUsuario" class="BotonStan1 paddingCmd" style="text-decoration:none;">Nuevo</a>
                   <a href="javascript:void(0);" id="cmdRegiUsuario" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:<%=EventoScript%>;">Guardar</a>
                   <a href="listarRof.uni" id="cmdCancelUsuario" class="BotonStan1 paddingCmd" style="text-decoration:none;">Cancelar</a>
                </fieldset>
            </div>
        </html:form>
        </div>
<%
}
%>