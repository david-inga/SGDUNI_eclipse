<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList,com.sgduni.forms.orpro_ta_registro_rof" %>
<%
ArrayList<orpro_ta_registro_rof> registros =(ArrayList)request.getAttribute("ListaNombreUnidadROF");
%>
<div id="DivGrillaResul" style="width:550px; margin-top:8px; overflow:auto;" >
<table width="400" border="0" align="center" cellpadding="2" cellspacing="2">
       <tr>
        <th width="20%" bgcolor="#E5E5E5" class="titu">Unidad</th>
        <th width="5%" bgcolor="#E5E5E5" class="titu">Agregar Área</th>
        <th width="55%" bgcolor="#E5E5E5" class="titu">Lista de Áreas</th>
       </tr>
        <%
        if(registros != null )
        {

        for(orpro_ta_registro_rof registro : registros)
          {
        %>
        <tr>
            <td class="textCont">
               <strong><%=registro.getVc_nombre_unidad() %></strong>
            </td>
            <td class="textCont" align="center" valign="middle">
               <a href="javascript:;" title="Editar" onclick="javascript:fnl_llamarPopupArea('<%=registro.getIn_codigo_registro()%>','<%=registro.getVc_nombre_unidad()%>');">
                 <img src="fileproject/img/sistema/formularios/agregar.jpg" width="18" height="18" border="0" title="Agregar Area para la Unidad <%=registro.getVc_nombre_unidad() %>"/>
               </a>
            </td>
            <td class="textCont">
                <div id="lstAreas<%=registro.getIn_codigo_registro() %>">

                </div>
            </td>
        </tr>
        <% }
         }
         else
         {
         %>
         <tr>
            <td colspan="2" class="textCont">
                No se Encontraron Registros
            </td>
         </tr>
        <%
          }
        %>
</table>
</div>
