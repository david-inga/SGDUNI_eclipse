<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList,com.sgduni.forms.orpro_ta_rof_unidad_area" %>
<%
ArrayList<orpro_ta_rof_unidad_area> areas =(ArrayList)request.getAttribute("ListaAreasROF");
%>
<div id="DivGrillaResul" style="width:550px; margin-top:8px; overflow:auto;" >
<table width="100" border="0" align="center" cellpadding="2" cellspacing="2">
       <tr>
        <th width="" bgcolor="#E5E5E5" class="titu">Nombre</th>
       </tr>
        <%
        if(areas != null )
        {

        for(orpro_ta_rof_unidad_area area : areas)
          {
        %>
        <tr>
            <td class="textCont">
               <%=area.getVc_nombre_area() %>
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