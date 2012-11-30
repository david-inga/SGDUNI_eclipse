<%-- 
    Document   : outlogin
    Created on : 12-dic-2011, 20:53:09
    Author     : Sistemas
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%
   HttpSession sesionOk = request.getSession();
   sesionOk.invalidate();//cerramos la session activa
   response.sendRedirect("login.uni");
   %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UNI</title>
    </head>
    <body>
        
    </body>
</html>
