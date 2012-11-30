<%--
    Document   : registrar DEPENDENCIA
    Created on : 03-dic-2011, 23:40:34
    Author     : marco antonio estrella cardenas
--%>
<%@ page import="com.sgduni.forms.orgen_ta_facultad,com.sgduni.forms.orgen_ta_organo,java.util.ArrayList" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="com.sgduni.utilitarios.ContarCaracteresVista" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsDependencia.js");
</script>
<%
ArrayList<orgen_ta_organo> organos = (ArrayList)request.getAttribute("organos");
%>
    <div  class="tituloPag" align="center">
     <strong>Registrar Dependencia</strong>
    </div>
	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> > <a href="listarDependencia.uni">Dependencias</a> > Registrar</div>

      <div class="formBody" style="width:610px;">
           <div class="textAviso">
               <bean:write name="orgen_ta_dependencia"  property="mensaje" filter="true"/>
           </div>
        <html:form action="insertDependencia.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiDep">
          <fieldset>
            <legend id="dvl_xuser">Datos de la Dependencia</legend>
            <table width="565" align="center">
            <tr>
                <td width="93" align="left"><label>Nombre:</label></td>
                <td width="248" align="center"> <html:text property="vc_nombre" styleId="vc_nombre" /></td>
              <td width="74" align="left" ><label>Abreviatura:</label></td>
                <td width="130" align="left" ><html:text property="vc_abrev_nom" styleId="vc_abrev_nom"/></td>
            </tr>
            <tr>
                <td valign="top"><label>Descripción:</label></td>
              <td colspan="3"><html:textarea cols="58" rows="5" property="vc_descripcion" styleId="vc_descripcion"></html:textarea></td>
            </tr>
            <tr>
                <td><label>Organo:</label></td>
                <td colspan="3">
                    <select id="in_codigo_organo" name="in_codigo_organo">
		           <%
                   ContarCaracteresVista contador;
                   out.println("<option value='0'>Seleccione Organo</option>");
                   if(organos != null)
                   {
                        for(orgen_ta_organo organo : organos)
                        {
                         contador = new ContarCaracteresVista( organo.getVc_nombre().trim() );
                         out.println("<option value='"+organo.getIn_codigo_organo()+"' title='"+organo.getVc_nombre().trim()+"'>"+contador.getTexMax(35, null)+"</option>");
                        }
                   }
                   else
                   {
                       out.println("<option value='0'>No hay Organos</option>");
                   }
		          %>
		       </select>
                </td>
              </tr>
                <tr>
                  <td width="60" align="left"><label>Dirección:</label></td>
                  <td width="248" > <html:text property="vc_direccion" styleId="vc_direccion" /></td>
                  <td width="74" align="left" ><label>Apartado:</label></td>
                  <td width="130" align="left" ><html:text property="vc_apartado" styleId="vc_apartado"/></td>
                </tr>
                <tr>
                  <td width="93" align="left"><label>Telefono:</label></td>
                  <td width="248"> <html:text property="vc_telefono" styleId="vc_telefono" /></td>
                  <td width="74" align="left" ><label>Telefono Central:</label></td>
                  <td width="130" align="left" ><html:text property="vc_telefono_central" styleId="vc_telefono_central"/></td>
                </tr>
                <tr>
                  <td width="93" align="left"><label>Anexo:</label></td>
                  <td width="248"> <html:text property="vc_anexo" styleId="vc_anexo" /></td>
                  <td width="74" align="left" ><label>Correo:</label></td>
                  <td width="130" align="left" ><html:text property="vc_correo" styleId="vc_correo"/></td>
                </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdNuevoDependencia" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_nuevaDependencia();">Limpiar</a>
                   <a href="javascript:;" id="cmdRegiDependencia" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiDependencia();">Registrar</a>
                   <a href="listarDependencia.uni" id="cmdCancelDependencia" class="BotonStan1 paddingCmd" style="text-decoration:none;">Regresar</a>
                </fieldset>
            </div>
        </html:form>
        </div>