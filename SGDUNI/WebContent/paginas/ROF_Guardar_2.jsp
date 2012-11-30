<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ page import="com.sgduni.forms.orpro_ta_rof" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsRof.js");
</script>
<%
String mensaje = request.getAttribute("mensaje_de_exito").toString();
orpro_ta_rof rof = (orpro_ta_rof)request.getAttribute("rof");
String idRof = String.valueOf( rof.getIn_codigo_rof() );
%>

    <div  class="tituloPag" align="center">
     <strong>Registrar Nuevo ROF</strong>
    </div>
	<div id="localizador"><a href="menu.uni">Inicio</a> > <a href="listarRof.uni">Lista de Documentos ROF</a> > Crear Nuevo ROF</div>

      <div class="formBody" style="width:610px;">
           <div class="textAviso" style="display:block">
               <%=mensaje%>
           </div>
        <html:form action="guardarRofParteDos.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiROFDOS">
            <html:hidden property="in_codigo_rof" styleId="in_codigo_rof" value="<%=idRof%>"></html:hidden>
            <fieldset>
            <legend id="dvl_xuser">Datos del ROF</legend>
            <table width="565" align="center">
            <tr>
                <td width="85"><label>Base Legal: </label></td>
                <td>
                    <a href="javascript:;" id="cmdAgregarBaseLegal" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_llamarPopupBaseLegal('<%=rof.getIn_codigo_rof()%>');">Agregar Base Legal</a>
                </td>
            </tr>
            <tr>
               <td colspan="2"> <div id="lstBasesLegales"></div> </td>
            </tr>
            <tr>
               <td valign="top"><label>Alcance: </label></td>
                <td  >
                  <html:textarea cols="63" rows="3" property="vc_alcance" styleId="vc_alcance"></html:textarea>
                </td>
            </tr>
            <tr>
                <td width="85" align="right" valign="top">
<html:hidden property="in_codigo_estructura"  styleId="in_codigo_estructura" style="width:25px;"></html:hidden>
                    <a href="javascript:;" id="cmdAdjuntarOrganigrama" class="BotonStan1" style="text-decoration:none;" onclick="javascript:fnl_adjuntar_archivo();" title="Organigrama">
                        Adjuntar Organigrama
                    </a>                
                </td>
                <td width="468" align="left">
                   <div id="dvl_in_codigo_estructura" >
                       ...
                   </div>
                  
                </td>
            </tr>
            <tr>
                <td valign="top"><label style="height:35px;">Funciones Generales: </label></td>
                <td>
                    <a href="javascript:;" id="cmdAgregarFuncionGeneral" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_llamarPopupFuncionesGenerales('<%=rof.getIn_codigo_rof()%>');">Agregar Función General</a>
                </td>
              </tr>
            <tr>
               <td colspan="2"> <div id="lstFuncionesGenerales"><br></br></div> </td>
            </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdRegiOrgano" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiRofParteDos();">Guardar y Siguiente</a>
                   <a href="listarRof.uni" id="cmdCancelOrgano" class="BotonStan1 paddingCmd" style="text-decoration:none;">Salir</a>
                </fieldset>
            </div>
        </html:form>
        </div>
