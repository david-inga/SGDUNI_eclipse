
<%--
    Document   : registrar DEPENDENCIA
    Created on : 03-dic-2011, 23:40:34
    Author     : marco antonio estrella cardenas
--%>

<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsCargoEstructural.js");
</script>
    <div  class="tituloPag" align="center">
     <strong>Registrar Cargo de Usuario</strong>
    </div>
	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> > <a href="listarCargoEstructural.uni">Cargos de Usuario</a> > Registrar</div>

      <div class="formBody" style="width:610px;">
           <div class="textAviso">
               <bean:write name="orgen_ta_cargo_usuario"  property="mensaje" filter="true"/>
           </div>
        <html:form action="insertarCargoEstructural.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiCargoEstruct">
          <fieldset>
            <legend id="dvl_xuser">Datos del Cargo de Usuario</legend>
            <table width="565" align="center">
                <tr>
                    <td width="104" valign="top"><label>Nombre:</label></td>
                    <td align="left" valign="top">
                        <html:text property="vc_nombre" styleId="vc_nombre" />
                    </td>
                </tr>
                <tr>
                    <td valign="top"><label>Descripción:</label></td>
                    <td width="449">
                        <html:textarea cols="58" rows="5" property="vc_descripcion" styleId="vc_descripcion"></html:textarea></td>
                </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdNuevoDependencia" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_nuevoCargoEstructural();">Nuevo</a>
                   <a href="javascript:;" id="cmdRegiDependencia" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiCargoEstructural();">Registrar</a>
                   <a href="listarCargoEstructural.uni" id="cmdCancelDependencia" class="BotonStan1 paddingCmd" style="text-decoration:none;">Regresar</a>
                </fieldset>
            </div>
        </html:form>
        </div>