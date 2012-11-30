<%-- 
    Document   : Oficio_circular_enviar_nuevo
    Created on : 09-ene-2012, 0:41:29
    Author     : Sistemas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_dependencia" %>
<%@ page import="com.sgduni.forms.orgen_ta_facultad" %>
<%@ page import="com.sgduni.forms.orgen_ta_estado" %>
<%@ page import="com.sgduni.utilitarios.ContarCaracteresVista"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>

<script language="javascript" type="text/javascript" >
       fng_loadJs("fileproject/js/formularios/jsOficioCircular.js");
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
   ArrayList<orgen_ta_dependencia> dependencias=(ArrayList)request.getAttribute("dependencias");
   ArrayList<orgen_ta_facultad> facultad=(ArrayList)request.getAttribute("facultades");
   String in_codigo_oficio = ( request.getAttribute("in_codigo_oficio").toString() != "") ? request.getAttribute("in_codigo_oficio").toString() : "0";
   String ch_codigo_oficio = request.getAttribute("ch_codigo_oficio").toString();
   String id_emisor = request.getAttribute("emisor").toString();
  // String mensaje_de_exito = ( request.getAttribute("mensaje_de_exito").toString() != "") ? request.getAttribute("mensaje_de_exito").toString() : "0";
%>

 <fieldset style="border:none;">

   <div  class="tituloPag" align="center">
     <strong>El oficio se enviara a los siguientes Usuarios: </strong>
   </div>

   <div id="DivGrillaResul">
   <br/>
   <center > <font color="808080"> <strong>Código del Oficio: <%=ch_codigo_oficio%></strong> </font> </center>
   <br/>
   <html:form action="oficioCircularEnviar.uni" method="post" styleId="frmEnviar">
   <html:hidden property = "in_codigo_oficio" styleId = "in_codigo_oficio" value="<%=in_codigo_oficio%>" ></html:hidden>
   
   <table width="674" height="166" border="0" align="center" cellpadding="2" cellspacing="2">
     <tr>
        <td width="318" height="44" align="center" valign="middle" bgcolor="#F2F2F2" class="titu">
           <strong>Lista de Facultades</strong><br/>
        </td>
        <td width="342" height="44" align="center" valign="middle" bgcolor="#F2F2F2" class="titu">
           <strong>Lista de Dependencias</strong><br/>
        </td>
     </tr>
     <tr>
       <td align="left" valign="top">
          <table width="318" border="0" cellpadding="1" cellspacing="1" style="border:none">
              <%
              if(facultad.size() > 0 )
              {
                  int ContFac=0;
                  for(orgen_ta_facultad lisFac : facultad)
                  {
                    %>
                    <tr>
                       <td width="146" align="left" valign="middle" class="textCont" title="<%=lisFac.getVc_nombre()%>">
                           <%=lisFac.getVc_abrev_nom() %>
                       </td>
                       <td width="23">
                           <input style="border:none;padding:0px;border-style:none;" type="checkbox" name="ckFacultad[<%=ContFac%>]" id="ckFacultad[<%=ContFac%>]" value="<%=lisFac.getIn_codigo_facultad()%>" class="checkSelect" />
                       </td>
                       <td width="139">
                           <select name="receptorFac[<%=ContFac%>]" id="receptorFac[<%=ContFac%>]"  >
                           </select>
                       </td>
                    </tr>
                    <%
                    ContFac++;
                  }
              }
              %>
          </table>
          <input type="hidden" name="txtNumFacultad" id="txtNumFacultad" value="<%=facultad.size()%>"/>
         </td>
         <td align="left" valign="top">
            <table width="345" border="0" cellpadding="1" cellspacing="1" style="border:none">
              <%
              if(dependencias.size() > 0)
              {
                  int ContDepen=0;
                  for(orgen_ta_dependencia lisDep : dependencias)
                  {
                  %>
                     <tr>
                       <td width="159" align="left" valign="middle" class="textCont" title="<%=lisDep.getVc_nombre()%>">
                           <%=lisDep.getVc_abrev_nom() %>
                       </td>
                       <td width="21">
                           <input style="border:none;padding:0px;" type="checkbox" name="ckDependecia[<%=ContDepen%>]" id="ckDependecia[<%=ContDepen%>]" value="<%=lisDep.getIn_codigo_dependencia()%>"  class="checkSelect"/>
                       </td>
                       <td width="128">
                           <select name="receptorDep[<%=ContDepen%>]" id="receptorDep[<%=ContDepen%>]" >
                           </select>
                       </td>
                     </tr>
                  <%
                   ContDepen++;
                  }
              }
              %>
            </table>
           <input type="hidden" name="txtNumDependecia" id="txtNumDependecia" value="<%=dependencias.size()%>"/>
         </td>
     </tr>
     <tr>
       <td height="34" colspan="2" align="center">
          <a id="cmdVistaPrevia" href="javascript:;" onclick="fnl_ver_oficio('<%=id_emisor%>','<%=in_codigo_oficio%>',null);" class="BotonStan1" style="text-decoration:none;padding:20px;">Vista Previa</a>
          <a id="cmdEnviarPorTipoOficoCircular" href="javascript:;" onclick="fnl_enviarListaOficioC()" class="BotonStan2" style="text-decoration:none;padding:20px;">Enviar Oficio Circular</a>
          <a id="cmdRegresar" href="oficioCircularListar.uni?nump=4" class="BotonStan2" style="text-decoration:none;padding:20px;">Regresar</a>
       </td>
     </tr>
   </table>
   <p>&nbsp;</p>
   <p>&nbsp;</p>
   </html:form>
</div>
</fieldset>
