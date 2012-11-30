<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsDependencia.js");
</script>
<%
String idRegistro = String.valueOf( request.getAttribute("idRegistro") );
String nomUnidad = String.valueOf( request.getAttribute("nomUnidad") );
%>
    <div  class="tituloPag" align="center">
     <strong>Agregar Área</strong>
    </div>

      <div class="formBody" style="width:610px;">
           <div class="textAviso" id="textAvisoArea">
               
           </div>
        <html:form action="agregarAreaUnidadROF.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiDep">
          <fieldset>
            <legend id="dvl_xuser">Datos del Registro</legend>
            <table width="565" align="center">
            <tr>
                <td width="93" align="left"><label>Unidad: </label></td>
                <td width="210">
                    <html:hidden property="in_codigo_registro" styleId="in_codigo_registro" value="<%=idRegistro%>" />
                    <html:text property="vc_nombre_unidad" styleId="vc_nombre_unidad" value="<%=nomUnidad%>" />
                </td>
            </tr>
            <tr>
                <td width="93" align="left"><label>Area: </label></td>
                <td width="210">
                  <html:text property="vc_nombre_area" styleId="vc_nombre_area" />
                </td>
            </tr>
            <tr>
                <td width="74" align="left" ><label>Descripción de la Unidad:</label></td>
                <td width="130" align="left" ><html:textarea property="vc_descripcion_area" rows="4"  cols="44" styleId="vc_descripcion_area"/></td>
            </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdAgregarAREA" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_AgregarArea();">Registrar</a>
                </fieldset>
            </div>
        </html:form>
        </div>
