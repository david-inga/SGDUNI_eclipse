<%--
    Document   : registrar DETALLE DEL CAP
    Created on : 03-dic-2011, 23:40:34
    Author     : marco antonio estrella cardenas
--%>
<%@ page import="com.sgduni.forms.orgen_ta_procedimiento,java.util.ArrayList" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsDirectivas.js");
</script>
<%
ArrayList<orgen_ta_procedimiento> procedimientos = (ArrayList)request.getAttribute("procedimientos");
String codDir = (String)request.getAttribute("codDir");
String idDir = (String)request.getAttribute("idDir");
%>
    <div  class="tituloPag" align="center">
     <strong>Agregar Procedimiento a la Directiva <%=codDir%></strong>
    </div>

      <div class="formBody" style="width:400px;">
          <div class="textAviso" id="textAviso_direc_pro" >
          </div>
        <html:form action="insertarProcedimientoDirectivas.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiProcDir">
          <fieldset>
            <legend id="dvl_xuser">Seleccione el Procedimiento</legend>
            <table width="380" align="center">
            <tr>
                <td width="101" valign="top"></td>
                <td align="left" valign="top"><html:hidden property="in_codigo_directiva" value="${idDir}" styleId="in_codigo_directiva"></html:hidden></td>
              <td valign="top"></td>
              <td width="170" align="left" valign="top"></td>
            </tr>
            <tr>
                <td><label>Procedimiento:</label></td>
                <td>
                    <select id="in_codigo_procedimiento" name="in_codigo_procedimiento">
		           <%
                   out.println("<option value='0'>Seleccione un Procedimiento</option>");
                   if(procedimientos != null)
                   {
                        for(orgen_ta_procedimiento proc : procedimientos)
                        {
                         out.println("<option value='"+proc.getIn_codigo_procedimiento() +"'>"+proc.getVc_nombre().trim()+"</option>");
                        }
                   }
                   else
                   {
                       out.println("<option value='0'>No hay Procedimientos</option>");
                   }
		          %>
		       </select>
                </td>
                <td></td>
                <td></td>
            </tr>
            </table>
            <br/>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdRegiDetalleCap" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_NuevoProcedimiento();">Nuevo</a>
                   <a href="javascript:;" id="cmdRegiDetalleCap" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiProcedimiento();">Registrar Procedimiento</a>
                   <a href="javascript:;" id="cmdCancel" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_cancelar();">Cancelar</a>
                </fieldset>
            </div>
        </html:form>
        <div id="dvl_miprimeravez">
        </div>
        </div>
