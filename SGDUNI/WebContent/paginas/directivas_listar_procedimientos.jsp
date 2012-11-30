<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orpro_det_proc_direc" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
    <% ArrayList<orpro_det_proc_direc> procedimientos =(ArrayList)request.getAttribute("procedimientos"); %>

    <div  class="tituloPag" align="center">
     <strong>Procedimientos Agregados</strong>
    </div>

  <div id="DivGrillaResul">
    <div class="formBody" style="height:250px; overflow:scroll">
      <div>
       <br/><br/>
      </div>

    <table width="50%" border="0" align="left" cellpadding="2" cellspacing="2">
        <th bgcolor="#E5E5E5" class="titu">NOMBRE</th>

        <%
        for ( orpro_det_proc_direc procedimiento : procedimientos )
        {
        %>
        <tr>
            <td class="textCont"><%=procedimiento.getNombre_procedimiento() %></td>
        </tr>
        <%
        }
        %>
    </table>
  </div>
</div>

