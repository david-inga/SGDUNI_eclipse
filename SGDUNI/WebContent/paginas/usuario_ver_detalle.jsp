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
   //fng_loadJs("fileproject/js/formularios/jsUsuario.js");
</script>
<%
  orgen_ta_usuario usuario = (orgen_ta_usuario)request.getAttribute("usuario");
  String cargo = request.getAttribute("cargo").toString();
  String rol = request.getAttribute("rol").toString();
  String nombreCompleto = usuario.getVc_nombres()+" "+usuario.getVc_apellido_paterno()+" "+usuario.getVc_apellido_materno();
  String dependencia = request.getAttribute("dependencia").toString();
%>
<div  class="tituloPag" align="center">
 <strong>Detalles de Usuario</strong>
</div>
<br/>
<table width="311" border="0" align="center" cellpadding="10" cellspacing="10">
<tr>
    <td width="130" align="left" valign="top" class="txtStyleAd2"><strong>
        Usuario:
    </strong></td>
    <td width="169" align="left" valign="top" class="txtStyleAd4Err">
			<%=usuario.getVc_usuario() %>
    </td>
</tr>
<tr>
    <td width="130" align="left" valign="top" class="txtStyleAd2"><strong>
        Clave:
    </strong></td>
    <td width="169" align="left" valign="top" class="txtStyleAd4Err" >
         <%=usuario.getVc_clave() %>
    </td>
</tr>
<tr>
    <td align="left" valign="top" class="txtStyleAd2"><strong>
        Nombre Completo:
    </strong></td>
    <td valign="top" class="txtStyleAd1"><%=nombreCompleto %></td>
</tr>
<tr>
    <td width="130" align="left" class="txtStyleAd2"><strong>
       E-Mail:
    </strong></td>
    <td width="169" align="left" valign="top" class="txtStyleAd1" >
        <%=usuario.getVc_correo() %>
    </td>
</tr>
<tr>
    <td width="130" align="left" class="txtStyleAd2"><strong>
       ROL:
    </strong></td>
    <td width="169" align="left" valign="top" class="txtStyleAd1" >
        <%=rol %>
    </td>
</tr>
<tr>
    <td width="130" align="left" class="txtStyleAd2"><strong>
       Dependencia:
    </strong></td>
    <td width="169" align="left" valign="top" class="txtStyleAd1" >
        <%=dependencia %>
    </td>
</tr>
<tr>
    <td width="130" align="left" class="txtStyleAd2"><strong>
       Cargo:
    </strong></td>
    <td width="169" align="left" valign="top" class="txtStyleAd1" >
       <%=cargo %>
    </td>
</tr>
<tr>
    <td colspan="2" align="center" valign="top">     
        <img src="documentos/firmas/<%=usuario.getVc_nombre_archivo() %>" width="151.18" height="096.37" border="0"/>     
    </td>
</tr>
</table>

