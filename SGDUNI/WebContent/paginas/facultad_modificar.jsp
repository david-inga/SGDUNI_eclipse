<%--
    Document   : Modificar Facultad
    Created on : 03-dic-2011, 23:40:34
    Author     : marco antonio estrella cardenas
--%>
<%@ page import="com.sgduni.forms.orgen_ta_facultad" %>
<%@ page import="com.sgduni.forms.orgen_ta_organo" %>
<%@ page import="java.util.ArrayList" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsFacultad.js");
</script>
    <div  class="tituloPag" align="center">
     <strong>Modificar Facultad</strong>
    </div>
	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> > <a href="listarFacultad.uni">Facultades</a> > Modificar</div>


      <div class="formBody" style="width:610px;">
         <%
         orgen_ta_facultad facultad =(orgen_ta_facultad)request.getAttribute("facultad");
         ArrayList<orgen_ta_organo> organos = (ArrayList)request.getAttribute("organos");
         %>
           <div class="textAviso">
               <bean:write name="orgen_ta_facultad" property="mensaje" filter="true" />
           </div>
        <html:form action="modificarFacultad.uni" method="POST" styleClass="formulariosA1" styleId="frmModFacultad">
          <fieldset>
            <legend>Datos de la Facultad</legend>
            <html:hidden property="in_codigo_facultad" value="${facultad.in_codigo_facultad}"></html:hidden>
            <table width="565" align="center">
            <tr>
                <td width="40"><label>Nombre:</label></td>
                <td width="230" align="center" ><html:text property="vc_nombre"  value="${facultad.vc_nombre}" styleId="vc_nombre"></html:text></td>
                <td width="25" align="left" ><label>Abreviatura:</label></td>
                <td width="50" align="left" ><html:text property="vc_abrev_nom" styleId="vc_abrev_nom" value="${facultad.vc_abrev_nom}" /></td>
            </tr>
            <tr>
                <td valign="top"><label>Descripción:</label></td>
              <td colspan="3"><html:textarea cols="53" rows="5" style="width:99%" property="vc_descripcion" styleId="vc_descripcion" value="${facultad.vc_descripcion}"></html:textarea></td>
            </tr>
            <tr>
                <td><label>Organo:</label></td>
                <td colspan="3">
                 <select id="in_codigo_organo" name="in_codigo_organo">
                 <option >
                  <%
                  int codFacultad = 0;
                  if(facultad == null)
                  {
                     codFacultad = 0;
                  }
                  else
                  {
                     codFacultad = facultad.getIn_codigo_organo();
                  }

                  out.println("<option value='0'>Seleccione Organo</option>");
                  
                  if(organos != null)
                  {
                       for(orgen_ta_organo organo : organos)
                       {
                            //selected="selected" -> es para que se seleccione
                            if(codFacultad != 0 &&  codFacultad == organo.getIn_codigo_organo() )
                            {
                              out.println("<option value='"+organo.getIn_codigo_organo()+"' selected='selected'>"+organo.getVc_nombre().trim()+"</option>");
                            }
                            else
                            {
                                       out.println("<option value='"+organo.getIn_codigo_organo()+"'>"+organo.getVc_nombre().trim()+"</option>");
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
                <tr>
                  <td width="60" align="left"><label>Dirección:</label></td>
                  <td width="248" > <html:text property="vc_direccion" styleId="vc_direccion" value="${facultad.vc_direccion}" /></td>
                  <td width="74" align="left" ><label>Apartado:</label></td>
                  <td width="130" align="left" ><html:text property="vc_apartado" styleId="vc_apartado" value="${facultad.vc_apartado}"/></td>
                </tr>
                <tr>
                  <td width="93" align="left"><label>Telefono:</label></td>
                  <td width="248"> <html:text property="vc_telefono" styleId="vc_telefono" value="${facultad.vc_telefono}" /></td>
                  <td width="74" align="left" ><label>Telefono Central:</label></td>
                  <td width="130" align="left" ><html:text property="vc_telefono_central" styleId="vc_telefono_central" value="${facultad.vc_telefono_central}" /></td>
                </tr>
                <tr>
                  <td width="93" align="left"><label>Anexo:</label></td>
                  <td width="248"> <html:text property="vc_anexo" styleId="vc_anexo" value="${facultad.vc_anexo}" /></td>
                  <td width="74" align="left" ><label>Correo:</label></td>
                  <td width="130" align="left" ><html:text property="vc_correo" styleId="vc_correo" value="${facultad.vc_correo}" /></td>
                </tr>
              </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdModiFacultad" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_modiFacultad();">Modificar</a>
                   <a href="listarFacultad.uni" id="cmdCancelFacultad" class="BotonStan1 paddingCmd" style="text-decoration:none;">Regresar</a>
                </fieldset>
            </div>
        </html:form>
        </div>