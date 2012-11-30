<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList,com.sgduni.forms.orpro_ta_rof_funciones_generales" %>
<%
ArrayList<orpro_ta_rof_funciones_generales> funcioneslegales =(ArrayList)request.getAttribute("ListaFuncionGeneralROF");
%>
<div id="DivGrillaResul" style="width:550px; margin-top:8px; overflow:auto;" >
<table width="300" border="0" align="center" cellpadding="2" cellspacing="2">
       <tr>
        <th width="3%" bgcolor="#E5E5E5" class="titu">Orden</th>
        <th width="35%" bgcolor="#E5E5E5" class="titu">Descripción</th>
       </tr>
        <%
        if(funcioneslegales != null )
        {

        for(orpro_ta_rof_funciones_generales funcionLegal : funcioneslegales)
          {
        %>
        <tr>
            <td class="textCont">
                <%=funcionLegal.getCh_orden() %>
            </td>
            <td class="textCont">
                <%=funcionLegal.getVc_descripcion()%>
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