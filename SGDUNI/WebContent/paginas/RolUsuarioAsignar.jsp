<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.sgduni.forms.*" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
    <form action="guardarAsignacionRolUsuario.uni" method="POST">
    <center>
    <h1>Asignar Rol a Usuario</h1>
    <%
    //int codUser=(request.getParameter("codu")!=null)?Integer.parseInt(request.getParameter("codu")):-1;

     ArrayList<orgen_ta_usuario> usuarios = (ArrayList)request.getAttribute("nombreusuarios");
     ArrayList<orgen_ta_rol> roles =(ArrayList)request.getAttribute("roles");
    %>
    <table>
		<tr>
            Seleccione Usuario
        </tr>
        <tr>
		    <td>
                <select id="in_codigo_usuario" name="in_codigo_usuario">
		           <%
                   out.println("<option value='0'>Seleccione Usuario</option>");
                   if(usuarios != null)
                   {
                        for(orgen_ta_usuario usuario : usuarios)
                        {
                         out.println("<option value='"+usuario.getIn_codigo_usuario()+"'>"+usuario.getVc_usuario()+"</option>");
                        }
                   }
                   else
                   {
                       out.println("<option value='0'>No hay usuarios</option>");
                   }
		          %>
		       </select>
		    </td>
		</tr>
       </table>
       Seleccione los Roles a Asignar:
       <table border="0" style="font-size: 10px; font-family: Tahoma, Verdana, Arial, Sans-Serif; font-weight: bold;">
         <%
                   if(roles != null)
                   {
                       int i=0;
                        for(orgen_ta_rol rol : roles)
                        {

                          i++;
                          out.println("<tr>");
                          out.println("<td>"+rol.getVc_nombre()+"</td>");
                          out.println("<td><input value='"+rol.getCh_codigo_rol().trim()+"' type='checkbox' name='ch_codigo_rol_"+i+"'/></td>");
                          out.println("</tr>");
                        }
                   }
                   else
                   {
                       out.println("<tr>");
                       out.println("<td>No hay Roles</td>");
                       out.println("<td></td>");
                       out.println("</tr>");
                   }
		 %>
       </table>
       <html:submit value="Asignar Roles"></html:submit>
  </center>
 </form>
</body>
</html>