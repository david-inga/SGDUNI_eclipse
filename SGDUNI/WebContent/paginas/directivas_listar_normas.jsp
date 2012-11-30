<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orpro_detalle_normas_gen" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
    <% ArrayList<orpro_detalle_normas_gen> normas =(ArrayList)request.getAttribute("normas"); %>

    <div  class="tituloPag" align="center">
     <strong>Lista de Normas</strong>
    </div>

  <div id="DivGrillaResul">
    <div class="formBody" style="height:250px; overflow:scroll">
      <div>
       <br/><br/>
      </div>

    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
        <th height="35" align="center" bgcolor="#E5E5E5" class="titu">ID</th>
        <th bgcolor="#E5E5E5" class="titu">DESCRIPCION</th>

        <%
        for (orpro_detalle_normas_gen norma : normas)
        {
        %>
        <tr>
            <td height="38" align="center" bgcolor="#E5E5E5"><strong><%=norma.getIn_codigo_normas_gen() %></strong></td>
            <td class="textCont"><%=norma.getVc_descripcion() %></td>
        </tr>
        <%
        }
        %>
    </table>
  </div>
</div>



