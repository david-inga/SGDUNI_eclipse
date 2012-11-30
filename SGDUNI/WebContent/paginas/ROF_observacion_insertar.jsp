<%--
    Document   : ObservacionesROF
    Created on : 15-06.2012
    Author     : Sistemas
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%
    String codROF = request.getAttribute("idRof").toString();
    String Rol = session.getAttribute("xrol").toString().trim().toUpperCase();
   %>
    <div  class="tituloPag" align="center" style="width:320px">
      <strong>Observaciones ROF</strong>
    </div>

  	<div id="localizador" style="margin-top:7px;">
        <a href="javascript:;" onclick="javascript:fnl_ver_lista_observaciones('<%=codROF%>');">&raquo; Ver Todas las Observaciones</a> > Nuevo
    </div>
          <html:form action="guardarObservacionROF.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiObsROF">

           <fieldset style="border:0px;">
               <div class="textAviso" id="dvl_avisopm" style="display:none;"></div>
              <html:hidden property="in_codigo_rof" styleId="in_codigo_rof" value="<%=codROF%>"></html:hidden>
            <table width="449" align="center">

              <tr>
                <td width="104" valign="top"><label>Observacion :</label></td>
                <td width="360" rowspan="2" align="left" valign="top">
                    <html:textarea property="vc_observacion" styleId="vc_observacion" cols="55" rows="7"></html:textarea>
                  <%
                  if(Rol.equals("ROL01") || Rol.equals("ROL03") || Rol.equals("ROL04"))
                   {//Solo el usuario OCDO 
                  %>
                  <span>APROBAR DOCUMENTO ROF</span>
                  <br/><input type="checkbox" id="ckaprobar" name="ckaprobar" value="1" style="padding:0px; margin:0px;float:none;background-color:#000"/>
                  <br/>
                  <%
                   }
                  %>
                </td>
              </tr>

            <tr>
                <td>&nbsp;</td>
              </tr>

            </table>
            </fieldset>
            <div align="center">
              <fieldset style="border:none;">
                 <a href="javascript:void(0);"  id="cmdRegiROF" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiObservacionROF();">Enviar</a>
                 <a href="javascript:void(0);" id="cmdCancelROF" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_cancelar();">Cancelar</a>
                </fieldset>
            </div>
        </html:form>
