<%--
    Document   : registrar
    Created on : 03-dic-2011, 23:40:34
    Author     : marco antonio estrella cardenas
--%>
<%@ page import="com.sgduni.forms.orgen_ta_organo" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsOrgano.js");
</script>
    <div  class="tituloPag" align="center">
     <strong>Modificar Órgano</strong>
    </div>
      <div class="formBody" style="width:610px;">
         <%
         orgen_ta_organo organo =(orgen_ta_organo)request.getAttribute("organo");
         %>
        <html:form action="modificarOrgano.uni" method="POST" styleClass="formulariosA1" styleId="frmModOrgano">
          <fieldset>
            <legend>Datos del Órgano</legend>
            <html:hidden property="in_codigo_organo" value="${organo.in_codigo_organo}"></html:hidden>
            <table width="565" align="center">
            <tr>
                <td width="101"><label>Nombre:</label></td>
                <td colspan="2">
                    <html:hidden property="in_codigo_organo" value="${organo.in_codigo_organo}" styleId="in_codigo_organoM"></html:hidden>
                    <html:text property="vc_nombre" value="${organo.vc_nombre}" styleId="vc_nombreM" ></html:text>
                </td>
              </tr>
            <tr>
                <td width="101" valign="top"><label>Descripción:</label></td>
                <td colspan="2">
                    <html:textarea cols="45" rows="5" property="vc_descripcion" value="${organo.vc_descripcion}" styleId="vc_descripcionM" ></html:textarea>
                </td>
              </tr>

            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdModiOrgano" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_modiUsuario();">Modificar</a>
                   <a href="javascript:;" id="cmdCancel" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript: fnl_cancelar();" >Cerrar</a>
                </fieldset>
            </div>
        </html:form>
        </div>