<%--
    Document   : Modificar Facultad
    Created on : 03-dic-2011, 23:40:34
    Author     : marco antonio estrella cardenas
--%>
<%@ page import="com.sgduni.forms.orgen_ta_facultad" %>
<%@ page import="com.sgduni.forms.orgen_ta_organo" %>
<%@ page import="com.sgduni.forms.orgen_ta_dependencia" %>
<%@ page import="java.util.ArrayList" %>
<%@page import="com.sgduni.utilitarios.ContarCaracteresVista" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsDependencia.js");
</script>
    <div  class="tituloPag" align="center">
     <strong>Modificar Dependencia</strong>
    </div>
	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> > <a href="listarDependencia.uni">Dependencias</a> > Modificar</div>
      <div class="formBody" style="width:610px;">
         <%
         orgen_ta_dependencia dependencia =(orgen_ta_dependencia)request.getAttribute("dependencia");
         ArrayList<orgen_ta_organo> organos = (ArrayList)request.getAttribute("organos");
         %>
           <div class="textAviso">
               <bean:write name="orgen_ta_dependencia" property="mensaje" filter="true" />
           </div>
        <html:form action="modificarDependencia.uni" method="POST" styleClass="formulariosA1" styleId="frmModDep">
          <fieldset>
            <legend>Datos de la Dependencia</legend>
            <html:hidden property="in_codigo_dependencia" value="${dependencia.in_codigo_dependencia}"></html:hidden>
            <table width="565" align="center">
            <tr>
              <td width="75"><label>Nombre:</label></td>
              <td width="251" align="center" ><html:text property="vc_nombre"  value="${dependencia.vc_nombre}" styleId="vc_nombre"></html:text></td>
              <td width="83" align="left" ><label>Abreviatura:</label></td>
              <td width="136" align="left" ><html:text property="vc_abrev_nom" styleId="vc_abrev_nom" value="${dependencia.vc_abrev_nom}" /></td>
            </tr>
            </tr>
            <tr>
                <td valign="top"><label>Descripción:</label></td>
              <td colspan="3"><html:textarea cols="59" rows="5" property="vc_descripcion" value="${dependencia.vc_descripcion}" styleId="vc_descripcion"></html:textarea></td>
              </tr>
            <tr>
                <td><label>Organo:</label></td>
                <td colspan="3">
                 <select id="in_codigo_organo" name="in_codigo_organo">
                 <option >
                  <%
                  int codOrgano= 0;

                  if(dependencia == null)
                  {
                     codOrgano = 0;
                  }
                  else
                  {
                     codOrgano = dependencia.getIn_codigo_organo();
                  }
                  ContarCaracteresVista contador;
                  out.println("<option value='0'>Seleccione Organo</option>");
                  if(organos != null)
                  {
                       for(orgen_ta_organo organo : organos)
                       {
                            //selected="selected" -> es para que se seleccione
                           contador = new ContarCaracteresVista( organo.getVc_nombre().trim() );
                            if(codOrgano != 0  && codOrgano == organo.getIn_codigo_organo() )
                            {
                              out.println("<option value='"+organo.getIn_codigo_organo()+"' selected='selected' title='"+organo.getVc_nombre().trim()+"'>"+contador.getTexMax(35, null)+"</option>");
                            }
                            else
                            {
                              out.println("<option value='"+organo.getIn_codigo_organo()+"' title='"+organo.getVc_nombre().trim()+"'>"+contador.getTexMax(35, null)+"</option>");
                            }
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
                  <td width="248" > <html:text property="vc_direccion" styleId="vc_direccion" value="${dependencia.vc_direccion}" /></td>
                  <td width="74" align="left" ><label>Apartado:</label></td>
                  <td width="130" align="left" ><html:text property="vc_apartado" styleId="vc_apartado" value="${dependencia.vc_apartado}"/></td>
                </tr>
                <tr>
                  <td width="93" align="left"><label>Telefono:</label></td>
                  <td width="248"> <html:text property="vc_telefono" styleId="vc_telefono" value="${dependencia.vc_telefono}" /></td>
                  <td width="74" align="left" ><label>Telefono Central:</label></td>
                  <td width="130" align="left" ><html:text property="vc_telefono_central" styleId="vc_telefono_central" value="${dependencia.vc_telefono_central}" /></td>
                </tr>
                <tr>
                  <td width="93" align="left"><label>Anexo:</label></td>
                  <td width="248"> <html:text property="vc_anexo" styleId="vc_anexo" value="${dependencia.vc_anexo}" /></td>
                  <td width="74" align="left" ><label>Correo:</label></td>
                  <td width="130" align="left" ><html:text property="vc_correo" styleId="vc_correo" value="${dependencia.vc_correo}" /></td>
                </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdModiDependencia" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_modiDependencia();">Modificar</a>
                   <a href="listarDependencia.uni" id="cmdCancelDependencia" class="BotonStan1 paddingCmd" style="text-decoration:none;">Regresar</a>
                </fieldset>
            </div>
        </html:form>
        </div>