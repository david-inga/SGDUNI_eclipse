[
<%
int numMax=3;
 for(int i=0;i<=numMax;i++){
%>
     { "id": "<%=i%>", "nombre": "Rol <%=i%>" }
  <%
     if(i<numMax) out.print(",");
  }
  %>
]