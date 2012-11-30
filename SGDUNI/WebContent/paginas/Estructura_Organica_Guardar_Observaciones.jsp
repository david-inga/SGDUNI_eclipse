<%--
    Document   : ObservacionesEOnuevo
    Created on : 04-ene-2012, 10:56:44
    Author     : Sistemas
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

   <%
   String idEstructura = request.getAttribute("codEstructura").toString().trim();
    String Rol=(String)session.getAttribute("xrol");
    Rol=Rol.trim().toUpperCase();
   %>

    <div  class="tituloPag" align="center" style="width:320px">
      <strong>Nueva Observación</strong>
    </div>
  	<div id="localizador" style="margin-top:7px;">
        <a href="javascript:;" onclick="javascript:fnl_llamarPopupObservaciones('<%=idEstructura%>');">Ver Observaciones </a> > Hacer una observación
    </div>
      <html:form action="guardarObservacionEO.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiObsEO">

       <fieldset style="border:0px;">
          <div class="textAviso" id="dvl_avisopm" style="display:none;">
              
          </div>
          <html:hidden property="in_codigo_estructura" styleId="in_codigo_estructura" value="<%=idEstructura%>"></html:hidden>
          <table width="449" align="center">
          <tr>
            <td width="104" valign="top">
                <label>Observación :</label>
            </td>
            <td width="360" rowspan="2" align="left" valign="top">
              <html:textarea property="vc_observacion" styleId="vc_observacion" cols="70" rows="12"></html:textarea>
              <%
               if(Rol.equals("ROL01") || Rol.equals("ROL03"))
               {//Solo el usuario OCDO PUEDE APROBAR LA ESTRUCTURA
              %>
              <span>APROBAR ESTRUCTURA ORGANICA</span>
              <br/>
                 <input type="checkbox" id="ckaprobar" name="ckaprobar" value="1" style="padding:0px; margin:0px;float:none;background-color:#000"/>
              <br/>
              <%
               }
              %>
            </td>
          </tr>
          <tr>
            <td></td>
            <td></td>
          </tr>
        </table>
    </fieldset>
    <div align="center">
      <fieldset style="border:none;">
         <a href="javascript:void(0);"  id="cmdRegiOrgano" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiObservacionEO();">Enviar</a>
         <a href="javascript:void(0);" id="cmdCancelOrgano" class="BotonStan1 paddingCmd" style="text-decoration:none;" title="Limpia el Campo" onclick="javascript:fnl_canlObrvEO();">Cancelar</a>
      </fieldset>
    </div>
    </html:form>