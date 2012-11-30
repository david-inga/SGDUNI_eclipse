<%--
    Document   : registrar ORGANO
    Created on : 03-dic-2011, 23:40:34
    Author     : marco antonio estrella cardenas
--%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsOrgano.js");
</script>
    <div  class="tituloPag" align="center">
     <strong>Registrar Órgano</strong>
    </div>
     <br></br>
      <div class="formBody" style="width:610px;">
           <div class="textAviso" id="textAviso">
           </div>
        <html:form action="insertarOrgano.uni" method="POST" styleClass="formulariosA1" styleId="frmRegiOrgano">
          <fieldset>
            <legend id="dvl_xuser">Datos del Órgano</legend>
            <table width="565" align="center">
            <tr>
                <td width="135" valign="top"><label>Nombre:</label></td>
                <td align="left" valign="top">
                  <html:text property="vc_nombre" styleId="vc_nombre"/>
              </td>
            </tr>
            <tr>
                <td valign="top">
                    <label>Descripción:</label>
                </td>
                <td width="418">
                  <html:textarea cols="55" rows="3" property="vc_descripcion" styleId="vc_descripcion"></html:textarea>
                </td>
            </tr>
            </table>
            </fieldset>
            <div align="center">
                <fieldset>
                 <legend></legend>
                   <a href="javascript:;" id="cmdNuevoOrgano" class="BotonStan1 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_nuevoOrgano();">Limpiar</a>
                   <a href="javascript:;" id="cmdRegiOrgano" class="BotonStan2 paddingCmd" style="text-decoration:none;" onclick="javascript:fnl_regiOrgano();">Grabar Órgano</a>
                 </fieldset>
            </div>
        </html:form>
</div>
<div id="dvl_ListaOrganos" style="width:630px; border-top:#999 solid 3px; margin:0px auto;">
</div>

        

