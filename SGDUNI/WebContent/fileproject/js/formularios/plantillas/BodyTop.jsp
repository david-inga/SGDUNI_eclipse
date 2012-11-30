<%-- 
    Document   : BodyTop
    Created on : 09-dic-2011, 14:47:58
    Author     : Sistemas
--%>
<%@page import="org.jdom.Document" %>
<%@page import="org.jdom.Element" %>
<%@page import="org.jdom.input.SAXBuilder" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>

<%
//Verificamos si existe el parametro get del rol(para cambiar las opciones del menu segun su rol)
if(request.getParameter("rol")!=null)
  session.setAttribute("tiporol", String.valueOf(request.getParameter("rol")));

//Leer variables de session
String xsessnom=(String) session.getAttribute("xnomus");
String xsesspat=(String) session.getAttribute("xapellus");
String xsessactivo=(String) session.getAttribute("xactivo");
String NivelUsuario="Visitante";
String tipomenu=(String) session.getAttribute("tiporol");

Boolean Autentificacion=false;
if(xsessactivo!=null && xsessactivo.equals("1")){
    NivelUsuario=(String) session.getAttribute("xnomrol");
    Autentificacion=true;
}else
{
   /*******************************************
    INI A:
   Validamos la sesiom activa. Si no existe lo redireccionamos al formulario login
   *******************************************/
    
   if(request.getParameter("login") !=  null)
   {
      if(!request.getParameter("login").equals("false"))
         out.println("<script> document.location.replace(\"login.uni?login=false\");</script>");      
   }else{
         out.println("<script> document.location.replace(\"login.uni?login=false\");</script>");
   }
  
   /*******************************************
    FIN A:
   *******************************************/
}

//Caprobando el dominio para leer el archivo XML
String path = request.getContextPath();
String dominio = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//Libreria para leer el archivo XML
%>
    <div id="contTop">      

      <div style="float:left; position:relative;">
		 <img src="fileproject/img/sistema/logo_uni.gif" width="98" height="146" alt="Logo Uni" style="position:absolute; top:5px; left:4px; z-index:3;"/>
         <div style="margin-left:100px; margin-top:7px;">
                 <div class="tituloUsuario">
                     <div>Nivel de usuario : <strong><%=NivelUsuario%></strong></div>
                 </div>

                
         </div>
       </div>
	  <div style="float:right; padding-right:8px; padding-top:5px;text-align:right;">
      <%
	  if(Autentificacion){
	  %>
		 <h1 class="txtStyleAd1" align="right" style="padding-right:4px;">Bienvenido: <strong><%=xsessnom + " "+ xsesspat%></strong></h1>
         <ul class="lisBoxStyle1" style="margin-top:4px;float:right;">
             <li>
               <a href="oficioCircularListar.uni?nump=true" title="Oficios Pendientes">
                 <img src="fileproject/img/sistema/administrador/icoPedidos.gif" width="24" height="20" hspace="3" vspace="0" border="0" align="absmiddle" />
                 Oficios <strong style="color:#ef0000" id="dvg_documentos_p"></strong>
               </a>
             </li>                          

             <li>
               <a href="javascript:;" title="Tramites Pendientes">
                 <img src="fileproject/img/sistema/administrador/icoMensajes.gif" width="24" height="20" hspace="3" vspace="0" border="0" align="absmiddle" />
                 Tramites <strong style="color:#ef0000" id="dvg_tramites_p"></strong>
               </a>
             </li>                          

             <li>
               <a href="outlogin.uni" title="Salir del sistema">
                 <img src="fileproject/img/sistema/administrador/icoCerrarSesion.gif" width="24" height="20" hspace="3" vspace="0" border="0" align="absmiddle" />
                 Cerrar Sesión
               </a>
             </li>                          
          </ul>
          <%
             }else{
          %>
            <h1 class="txtStyleAd1" align="right" style="padding-right:4px;"><strong>Inicio de Sesión</strong></h1>
          <%
             }
          %>
      </div>     
      <div style="clear:both;"></div>
    </div>


    <div class="contMenuPrincipal" style="padding-left:100px;">
     <%
      if(Autentificacion)
      {//Si existe la autentificaion visualizamos el Menu Superior
        //Si existe la autentificacionn cargamos el menu XML
        SAXBuilder builder=new SAXBuilder();
        String xml_path=dominio+"/plantillas/menus/menu_top.xml";
        Document DocXML=builder.build(xml_path);
        Element RaizXML=DocXML.getRootElement();
        Element MenuTopXML=(Element) RaizXML.getChild("menu"+tipomenu);
        List MenuItemTopXML=(List) MenuTopXML.getChildren("menu");
     %>
      <ul class="botonesMenuPrincipal">
          <%
          Element menuList=null;
          Element icono_T=null;
          for(int i=0;i<MenuItemTopXML.size();i++)
          {
              menuList=(Element) MenuItemTopXML.get(i);
              out.println("<li id=\""+menuList.getAttribute("idMat").getValue()+"\"><a href=\""+menuList.getAttribute("enlace").getValue()+"\"><img src=\"fileproject/img/sistema/administrador/"+menuList.getAttribute("icono").getValue()+"\"  border=\"0\"/><br />"+menuList.getAttribute("nom").getValue()+"</a></li>");
               
          }
        %>
      </ul>
    <%
     }
    %>      
    </div>   
<div id="contBodyC">