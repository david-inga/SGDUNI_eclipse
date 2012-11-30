<%--
    Document   : Observaciones para Oficio
    Created on : 15-06.2012
    Author     : Sistemas
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%
    String idOficio = request.getAttribute("idOficio").toString();
   %>
    <div  class="tituloPag" align="center" style="width:320px">
      <strong>Crear una Observación para el oficio</strong>
    </div>

  	<div id="localizador" style="margin-top:7px;">
        <a href="javascript:;" onclick="javascript:fnl_ver_lista_observaciones_oficio('<%=idOficio%>',1);">&raquo; Ver Todas las Observaciones</a> > Nuevo
    </div>
          <html:form action="almacenarObservacionOficio.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiObsROF">

           <fieldset style="border:0px;">
               <div class="textAviso" id="dvl_avisooficio" style="display:none;">
                   
               </div>
              <html:hidden property="in_codigo_oficio" styleId="in_codigo_oficio" value="<%=idOficio%>"></html:hidden>
            <table width="449" align="center">
              <tr>
                <td width="104" valign="top"><label>Observacion :</label></td>
                <td width="360" rowspan="2" align="left" valign="top">
                    <html:textarea property="vc_observacion" styleId="vc_observacion" cols="55" rows="7"></html:textarea>
                </td>
              </tr>
            </table>
            </fieldset>
            <div align="center">
              <fieldset style="border:none;">
                 <a href="javascript:void(0);"  id="cmdRegiOficio" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiObservacionOficio();">Agregar Observación</a>
                 <a href="javascript:void(0);" id="cmdCancelOficio" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_cancelar();">Cancelar</a>
                </fieldset>
            </div>
        </html:form>
