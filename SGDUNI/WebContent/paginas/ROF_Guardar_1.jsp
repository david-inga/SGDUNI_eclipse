<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsRof.js");
</script>
<%
String nuevo_codigo_rof = request.getAttribute("nuevo_codigo_rof").toString();
%> 
    <div  class="tituloPag" align="center">
     <strong>Registrar Nuevo ROF</strong>
    </div>
	<div id="localizador"><a href="menu.uni">Inicio</a> > <a href="listarRof.uni">Lista de Documentos ROF</a> > Crear Nuevo ROF</div>

      <div class="formBody" style="width:610px;">
           <div class="textAviso">
               <bean:write name="orpro_ta_rof" property="mensaje" filter="true"/>
           </div>
        <html:form action="guardarParteUnoROF.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiROF">
           <fieldset>
            <legend id="dvl_xuser">Datos del ROF</legend>
            <table width="565" align="center">
            <tr>
                <td width="156" valign="top"><label>Codigo:</label></td>
                <td align="left" valign="top">
                    <html:hidden property="ch_codigo_rof" styleId="ch_codigo_rof" value="<%=nuevo_codigo_rof%>"/>
                    <html:text property="ch_codigo_rof" disabled="true" styleId="ch_codigo_rof" value="<%=nuevo_codigo_rof%>"/>
              </td>
              </tr>
            <tr>
                <td valign="top"><label>Introducción:</label></td>
                <td width="397">
                    <html:textarea cols="60" rows="7" property="vc_introduccion" styleId="vc_introduccion"></html:textarea>
                </td>
              </tr>
            <tr>
                <td valign="top"><label>Naturaleza y Finalidad: </label></td>
                <td width="397">
                    <html:textarea cols="60" rows="7" property="vc_naturaleza_finalidad" styleId="vc_naturaleza_finalidad"></html:textarea>
                </td>
              </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdNuevoOrgano" class="BotonStan1 paddingCmd" style="text-decoration:none;" >Nuevo</a>
                   <a href="javascript:;" id="cmdRegiOrgano" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiRof();">Guardar y Siguiente</a>
                   <a href="listarRof.uni" id="cmdCancelOrgano" class="BotonStan1 paddingCmd" style="text-decoration:none;">Regresar</a>
                </fieldset>
            </div>
        </html:form>
        </div>
