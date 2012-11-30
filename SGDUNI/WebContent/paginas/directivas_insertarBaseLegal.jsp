<%--
    Document   : REGISTAR BASE LEGAL DE DIRECTIVAS
    Created on : 03-dic-2011, 23:40:34
    Author     : marco antonio estrella cardenas
--%>
<%@ page import="com.sgduni.forms.orpro_detalle_objetivo_direc" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsDirectivas.js");
</script>
<%
String codDir = (String)request.getAttribute("codDir");
String idDir = (String)request.getAttribute("idDir");
%>
    <div  class="tituloPag" align="center">
     <strong>Agregar Base Legal a la Directiva <%=codDir%></strong>
    </div>

      <div class="formBody" style="width:610px;">
          <div class="textAviso" id="textAviso_direc_base">
           </div>
        <html:form action="guardarBaseLegalDirectivas.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiBaseLegal">
          <fieldset>
            <legend id="dvl_xuser">Datos de la Base Legal</legend>
            <table width="565" align="center">
            <tr>
                <td width="101" valign="top"></td>
                <td align="left" valign="top"><html:hidden property="in_codigo_directiva" value="${idDir}" styleId="in_codigo_directiva"></html:hidden></td>
              <td valign="top"></td>
              <td width="170" align="left" valign="top"></td>
            </tr>
            <tr>
                <td><label>Descripción:</label></td>
                <td width="190"><html:textarea cols="65" rows="8" property="vc_descripcion" styleId="vc_descripcion"></html:textarea></td>
                <td width="84"></td>
                <td></td>
            </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdNuevoObjDir" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_nuevo();">Nuevo</a>
                   <a href="javascript:;" id="cmdRegiNormaGenDir" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiNormaBaseLegal();">Agregar Norma</a>
                   <a href="javascript:;" id="cmdCancel" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_cancelar();">Cancelar</a>
                </fieldset>
            </div>
        </html:form>
        <div id="dvl_miprimeravez">
        </div>
        </div>
