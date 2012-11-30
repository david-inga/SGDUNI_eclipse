<%-- 
    Document   : Uni
    Created on : 09-dic-2011, 14:06:28
    Author     : Sistemas
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   //fng_loadCss("fileproject/css/cssForm.css","idformcss");
   fng_loadJs("fileproject/js/formularios/jsLogin.js");
</script>
<p>&nbsp;</p>
<%
/*
HttpSession sesionOk = request.getSession();
String xsessactivo = (String) sesionOk.getId();
out.println("la sesion es : "+xsessactivo);
if(xsessactivo != null)
{
  //response.sendRedirect("oficioCircularListar.uni?nump=1");
    out.println("HAY SESION");
}
else
{
    out.println("NO HAY SESION");
  //response.sendRedirect("login.uni");
} */
%>
<html:form action="/logear" method="POST" styleClass="formulariosA1" styleId="formLogn">
<fieldset style="border:none;margin:0px; padding:0px;">
    <div align="center" style="margin:0px; padding:0px;">
     <img src="fileproject/img/sistema/administrador/top_login.gif" width="616" height="72"/>
    </div>
    <div style="background-color:#F3F3F3; width:582px; margin:0px auto;padding:0px; overflow:hidden; border-left:#CCC solid 1px;border-bottom:#CCC solid 1px; border-right:#CCC solid 1px;">
        <table width="582" border="0" align="center" cellspacing="2">
          <tr>
            <td colspan="4">
                 <div class="textAviso">
                     <bean:write name="orgen_ta_usuario" property="mensaje"/>                     
                  </div>
            </td>
          </tr>
          <tr>
            <td width="177" rowspan="5" valign="top"><img src="fileproject/img/sistema/administrador/imglogin_m.jpg" width="177" height="174"></td>
            <td width="69" height="52"><label for="vc_usuario" style="float:right"> Usuario</label></td>
            <td width="3">:</td>
            <td width="315"><html:text property="vc_usuario" styleId="vc_usuario" style="width:90%"></html:text></td>
          </tr>
          <tr>
            <td height="40"><label for="vc_clave" style="float:right"> Contraseña</label></td>
            <td>:</td>
            <td><html:password property="vc_clave" styleId="vc_clave" style="width:90%" onkeypress="presskey(event);" ></html:password></td>
          </tr>
          <tr>
            <td height="35" align="center" class="linkStyle1">&nbsp;</td>
            <td height="35" align="center" class="linkStyle1">&nbsp;</td>
          <!--  <td height="35" align="center" class="linkStyle1"><a href="javascript:;">¿Olvidastes tu contraseña?</a></td> -->
          </tr>
          <tr>
            <td height="34" align="center">&nbsp;</td>
            <td align="center">&nbsp;</td>
            <td align="center">
              <a href="javascript:;" class="BotonStan2" style="text-decoration:none;" id="cmdGuarVD2"  onclick="fnl_valid_login();">Ingresar</a>
              <a href="?" class="BotonStan1" style="text-decoration:none;" >Cancelar</a>
            </td>
          </tr>
          <tr>
            <td colspan="3" align="center">&nbsp;
              
            </td>
          </tr>
        </table>
    </div>
</fieldset>
</html:form>
