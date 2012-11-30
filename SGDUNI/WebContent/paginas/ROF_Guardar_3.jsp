<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsRof.js");
</script>
<%
String mensaje = request.getAttribute("mensaje_de_exito").toString();
String idRof = String.valueOf( request.getAttribute("idRof") );
%>

    <div  class="tituloPag" align="center">
     <strong>Registrar Nuevo ROF</strong>
    </div>
	<div id="localizador"><a href="menu.uni">Inicio</a> > <a href="listarRof.uni">Lista de Documentos ROF</a> > Crear Nuevo ROF</div>

      <div class="formBody" style="width:610px;">
           <div class="textAviso" style="display:block">
               <%=mensaje%>
           </div>
        <html:form action="guardarRofParteTres.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiROFTRES">
            <html:hidden property="in_codigo_rof" styleId="in_codigo_rof" value="<%=idRof%>"></html:hidden>
            <fieldset>
            <legend id="dvl_xuser">Datos del ROF</legend>
            <table width="565" align="center">
                <tr>
                    <td width="129"><label>Estructura Organica: </label></td>
                    <td>
                        <a href="javascript:;" id="cmdAgregarRegistro" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_llamarPopupRegistro('<%=idRof%>');">Agregar Registro</a>
                    </td>
                </tr>
                <tr>
                   <td colspan="2"> <div id="lstRegistro"><br></br></div> </td>
                </tr>
                <tr>
                   <td><label>Relaciones InterInstitucionales: </label></td>
                    <td  >
                        <html:textarea cols="63" rows="6" property="vc_relaciones_interinstitucionales" styleId="vc_relaciones_interinstitucionales"></html:textarea>
                    </td>
                </tr>
                <tr>
                   <td><label>Disposiciones Finales: </label></td>
                    <td>
                        <html:textarea cols="63" rows="6" property="vc_disposiciones_finales" styleId="vc_disposiciones_finales"></html:textarea>
                    </td>
                </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdRegiROF" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiRofParteTres();">Finalizar y Enviar</a>
                   <a href="listarRof.uni" id="cmdCancelROF" class="BotonStan1 paddingCmd" style="text-decoration:none;">Salir</a>
                </fieldset>
            </div>
        </html:form>
        </div>

