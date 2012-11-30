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
String idRof = String.valueOf( request.getAttribute("idRof") );
%>
    <div  class="tituloPag" align="center">
     <strong>Agregar Registro</strong>
    </div>

      <div class="formBody" style="width:610px;">
           <div class="textAviso" id="textAvisoRG">
               <bean:write name="orpro_ta_registro_rof"  property="mensaje" filter="true"/>
           </div>
        <html:form action="guardarRegistroROF.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiDep">
          <fieldset>
            <legend id="dvl_xuser">Datos del Registro</legend>
            <table width="565" align="center">
              <tr>
                <td><label>Seleccione Organo:</label></td>
                <td colspan="3">
                    <html:hidden property="in_codigo_rof" styleId="in_codigo_rofRG" value="<%=idRof%>"></html:hidden>
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
                <td width="93" align="left"><label>Unidad: </label></td>
                <td width="210">
                  <html:text property="vc_nombre_unidad" styleId="vc_nombre_unidad" />
                </td>
            </tr>
            <tr>
                <td width="74" align="left" ><label>Descripción de la Unidad:</label></td>
                <td width="130" align="left" ><html:textarea property="vc_descripcion_unidad" rows="4"  cols="44" styleId="vc_descripcion_unidad"/></td>
            </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdAgregarRegistro" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_AgregarRegistro();">Registrar</a>
                </fieldset>
            </div>
        </html:form>
        </div>