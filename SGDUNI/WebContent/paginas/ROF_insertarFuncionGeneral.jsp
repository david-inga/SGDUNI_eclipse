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
String idRof = request.getAttribute("idRof").toString().trim();
%>
    <div  class="tituloPag" align="center">
     <strong>Agregar Función General</strong>
    </div>

      <div class="formBody" style="width:610px;">
          <div class="textAviso" id="textAviso_rof_base">
           </div>
        <html:form action="guardarFuncionesGenerales.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiBaseLegal">
          <fieldset>
            <legend id="dvl_xuser">Datos de la Función</legend>
            <table width="565" align="center">
            <tr>
                <td width="101" valign="top"></td>
                <td align="left" valign="top"><html:hidden property="in_codigo_rof" value="<%=idRof%>" styleId="in_codigo_rof"></html:hidden></td>
              <td valign="top"></td>
              <td width="170" align="left" valign="top"></td>
            </tr>
            <tr>
                <td><label>Seleccione el Orden :</label></td>
                <td width="190">
                    <select name="orden" id="orden">
                        <option  value="0">Seleccione...</option>
                        <option  value="a)">a)</option>
                        <option  value="b)">b)</option>
                        <option  value="c)">c)</option>
                        <option  value="d)">d)</option>
                        <option  value="e)">e)</option>
                        <option  value="f)">f)</option>
                        <option  value="g)">g)</option>
                        <option  value="h)">h)</option>
                        <option  value="i)">i)</option>
                        <option  value="j)">j)</option>
                        <option  value="k)">k)</option>
                        <option  value="l)">l)</option>
                        <option  value="m)">m)</option>
                        <option  value="n)">n)</option>
                        <option  value="o)">o)</option>
                    </select>
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td><label>Descripción:</label></td>
                <td colspan="3"><html:textarea cols="65" rows="8" property="vc_descripcion" styleId="vc_descripcion"></html:textarea></td>
            </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdNuevoObjDir" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_nuevo();">Nuevo</a>
                   <a href="javascript:;" id="cmdRegiNormaGenDir" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiFuncionGeneral('<%=idRof%>');">Agregar</a>
                   <a href="javascript:;" id="cmdCancel" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_cancelar();">Salir</a>
                </fieldset>
            </div>
        </html:form>
        </div>
