<%--
    Document   : CREAR CAP
    Created on : 01-ene-2012, 17:38:25
    Author     : Sistemas
--%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_dependencia" %>
<%@ page import="com.sgduni.forms.orgen_ta_facultad" %>
<%@ page import="com.sgduni.forms.orgen_ta_estado" %>
<%@ page import="com.sgduni.utilitarios.ContarCaracteresVista" %>
<%@ page import="com.sgduni.forms.orpro_ta_cap" %>
<%@ page import="com.sgduni.forms.orpro_det_cap" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsDirectivas.js");
</script>
<%ArrayList<orgen_ta_estado> estados=(ArrayList)request.getAttribute("estados");%>
<%
String idDir = (String)request.getAttribute("idDir");
String nuevo_codigo = (String)request.getAttribute("nuevo_codigo");
%>

<%

 String CodRol=session.getAttribute("xrol").toString();
 String tipoDepenFacu=session.getAttribute("xtipodepen_facul").toString();
 String idDepenFacu=session.getAttribute("xiddepen_facul").toString();

 //out.println("COIGO DE ROL DEL USUARIO = "+CodRol);
 //out.println("tipo usuario = "+tipoDepenFacu);
 //out.println("id fac o dep = "+idDepenFacu);
%>

<link type="text/css" rel="stylesheet" href="fileproject/css/calendarioSelct/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<script type="text/javascript" src="fileproject/js/dhtmlgoodies_calendar.js?random=20060118"></script>

    <div  class="tituloPag" align="center">
     <strong>Crear Nueva Directiva</strong></div>
	<div id="localizador"><a href="menu.uni">Inicio</a> > <a href="listarDirectivas.uni">Directivas</a> > Nuevo</div>

      <div class="formBody" style="width:660px;">
           <div class="textAviso" id="textAviso_direc">
           </div>
           <html:form action="insertarDirectivas.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiDirectiva">
          <fieldset>
            <legend id="dvl_xuser">Datos de la Directiva
            </legend>
            <table width="565" align="center">
             <tr>
              <td width="118" valign="top"><label>Codigo Directiva :</label></td>
              <td width="229" align="left" valign="top" >
                  <html:text property = "ch_codigo_directiva" styleId = "ch_codigo_directiva" value="<%=nuevo_codigo%>" ></html:text>
              </td>

              <td width="51" valign="top">
                 <label>Fecha :</label>
              </td>

              <td width="125" align="left" valign="top">
                  <html:text property="dt_fecha" readonly="" styleId="dt_fecha" size="16" style="width:85%"></html:text>
              </td>

              <td width="18" align="left" valign="middle"><img onclick="displayCalendar(document.forms[0].dt_fecha,'dd/mm/yyyy',this)" src="fileproject/img/sistema/formularios/ico_calendario.gif" width="16" height="16" style="cursor:pointer;"></td>
            </tr>
            <tr>
              <td width="118" valign="top"><label>Alcance :</label></td>
              <td colspan="4" align="left" valign="top" >
              <html:textarea cols="65" rows="5" property = "vc_alcance" styleId = "vc_alcance" ></html:textarea>                </td>
              </tr>
            <tr>
              <td width="118" valign="top"><label>Responsabilidad:</label></td>
              <td colspan="4" align="left" valign="top" >
              <html:textarea cols="65" rows="5" property = "vc_responsabilidad" styleId = "vc_responsabilidad" ></html:textarea>                </td>
            </tr>
           <%
            String EventoScript="fnl_regiDirectivaUsuario()";

            if(CodRol.trim().toUpperCase().equals("ROL01"))
			{
			%>
             <html:hidden property="ch_estado" styleId="ch_estado" value="13"></html:hidden>
            <%
			}
            else
            {
                EventoScript="fnl_regiDirectivaUsuario()";
			 %>
              <html:hidden property="ch_estado" styleId="ch_estado" value="13"></html:hidden>
             <%
			}
			 %>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="llamarFormInsertDirectiva.uni" id="cmdNuevoDir" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="">Crear Nuevo</a>
                   <a href="javascript:;" id="cmdRegiDir" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiDirectivaUsuario();">Guardar Directiva</a>
                   <a href="listarDirectivas.uni" id="cmdCancelDir" class="BotonStan1 paddingCmd" style="text-decoration:none;"  onclick="javascript:fnl_nuevaDirectiva();">Cancelar</a>
                   <br/>
                   <br/>
                   <a href="javascript:;" id="cmdAgregarObetivo" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_llamarPopupObjetivo();">Agregar Objetivo</a>
                   <a href="javascript:;" id="cmdAgregarNorma" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_llamarPopupNormaGeneral();">Agregar Norma General</a>
                   <a href="javascript:;" id="cmdAgregarBaseLegal" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_llamarPopupBaseLegal();">Agregar Base Legal</a>
                   <a href="javascript:;" id="cmdAgregarProcedimiento" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_llamarPopupProcedimiento();">Agregar Procedimiento</a>
                </fieldset>
            </div>
</html:form>
 </div>