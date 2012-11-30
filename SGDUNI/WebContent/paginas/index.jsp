<%
String xsessactivo=(String) session.getAttribute("xactivo");

if(xsessactivo != null && xsessactivo.equals("1"))
{
  response.sendRedirect("oficioCircularListar.uni?nump=1");
}
else
{
  response.sendRedirect("login.uni");
}
%>