<%@page import="java.util.ArrayList" %>
<%@page import="com.sgduni.forms.orgen_ta_herramientas" session="true" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>

<%
String xsessnom="Usuario no identificado";
String nomDepenFacultad="";
String NivelUsuario="Visitante";
String Rol="";
String cargo = "";
Boolean Autentificacion=false;

//VERIFICANDO QUE EL USUARIO SE A LOGEADO Y LA SESION EXISTA
if(session.getAttribute("xid") != null    &&    session.getAttribute("xnomus") != null)
{   //Verificamos que la Sesión exista
    NivelUsuario=(String) session.getAttribute("xnomrol");
    Autentificacion=true;
    xsessnom=(String)session.getAttribute("xnomus");
    nomDepenFacultad=(String)session.getAttribute("xnomdepen_facul");
    Rol = session.getAttribute("xrol").toString().trim().toUpperCase();
    cargo = session.getAttribute("xcodcargo").toString().trim();
}
else
{
   /*******************************************
    INI A:
    Validamos la sesiom activa. Si no existe lo redireccionamos al formulario login
   *******************************************/    
   if(request.getParameter("login") !=  null)
   {
      if(!request.getParameter("login").equals("false"))
         out.println("<script> document.location.replace(\"login.uni?login=false\");</script>");      
   }
   else
   {
         out.println("<script> document.location.replace(\"login.uni?login=false\");</script>");
   }  
   /*******************************************
    FIN A:
   *******************************************/
}
%>
    <div id="contTop">      

      <div style="float:left; position:relative;">
		 <img src="fileproject/img/sistema/logo_uni.gif" width="98" height="146" alt="Logo Uni" style="position:absolute; top:5px; left:4px; z-index:3;"/>
         <div style="margin-left:100px; margin-top:7px;">
            <div class="tituloUsuario">
                <div>Usuario : <strong><%=NivelUsuario%></strong></div>
            </div>
            <%
            if(session.getAttribute("xid")==null && session.getAttribute("xnomus")==null)
            {
             %>
            <div  align="center" style="padding-right:4px;">
                
            </div>
            <%
            }
            %>
         </div>
       </div>
	  <div style="float:right; padding-right:8px; padding-top:5px;text-align:right;">
      <%
	  if(Autentificacion)
      {
	  %>      
		 <h1 class="txtStyleAd1 linkStyle2" align="right" style="padding-right:4px;">
             Bienvenido: <strong><%=xsessnom%></strong> - <span class="txtStyleAd3"><%=nomDepenFacultad%></span>
             &nbsp;&nbsp;&nbsp;&nbsp;<a href="outlogin.uni" title="Salir del sistema" style="text-decoration:none;"><STRONG>. SALIR .</STRONG></a>
         </h1>
         <ul class="lisBoxStyle1" style="margin-top:4px;float:right;">
           <%
           if(Rol.equals("ROL01") || Rol.equals("ROL02") || Rol.equals("ROL03") || Rol.equals("ROL04"))
           {//Visualizacion para los usuarios OCOD,USUARIO,RECTORADO, ASESORA LEGAL
           %>
           <!--MENU DESPLEGABLE - MOF-->
             <li style="position:relative" class="li_menu_desplegable_top">
               <a  href="#" title="Documentos MOF">
                 <img src="fileproject/img/sistema/formularios/rofAlert.png" width="24" height="20" hspace="3" vspace="0" border="0" align="absmiddle" />
                 MOF <strong style="color:#ef0000" id="dvg_rof_org_p"></strong>
               </a>
             
                 
                
             </li>
            <li style="position:relative" class="li_menu_desplegable_top">
               <a  href="listarRof.uni" title="Documentos ROF">
                 <img src="fileproject/img/sistema/formularios/rofAlert.png" width="24" height="20" hspace="3" vspace="0" border="0" align="absmiddle" />
                 ROF <strong style="color:#ef0000" id="dvg_rof_org_p"></strong>
               </a>
                <!--MENU DESPLEGABLE - ROF-->
                 <ul class="menu_sub_top">
                    <li><a href="javascript:;">Por Revisar</a></li>
                    <li><a href="javascript:;">Aun no Revisados por el Usuario</a></li>
                 </ul>
                
             </li>
           <%
           }
           %>
           <%
           if(Rol.equals("ROL01") || Rol.equals("ROL02") || Rol.equals("ROL03") || Rol.equals("ROL04"))
           {//Visualizacion para los usuarios OCDO,USUARIO,RECTORADO, ASESORA LEGAL
           %>
             <li style="position:relative" class="li_menu_desplegable_top">
               <a href="javascript:;" >
                 <img src="fileproject/img/sistema/administrador/imgEmailUncheck16x16.gif" width="24" height="20" hspace="3" vspace="0" border="0" align="absmiddle" />
                 Oficios 
               </a>
                  <ul class="menu_sub_top">
                      <% if(Rol.equals("ROL01") )
                          {
                              if(cargo.equals("1"))
                              {
                                 %>
                                 <li><a href="oficioCircularListar.uni">Borrador <strong style="color:#ef0000" id="dvg_borrador" ></strong></a></li>
                                 <li><a href="oficioCircularListar.uni?nump=3"> Enviados por la Secretaria <strong style="color:#ef0000" id="dvg_enviado_jefe_ocdo"></strong></a></li>
                                 <li><a href="oficioCircularListar.uni?nump=4">Aprobados por mi <strong style="color:#ef0000" id="dvg_aprobados_por_jefe"></strong></a></li>
                                 <li><a href="oficioCircularListar.uni?nump=2">Historial de Enviados <strong style="color:#ef0000" id="dvg_historial_enviados_p"></strong></a></li>
                                 <li><a href="oficioCircularListarOficioTramitado.uni">Historial de Oficios Tramitados <strong style="color:#ef0000" id="dvg_historial_tramitados_p"></strong></a></li>
                                 <li><a href="listarRespuestaDependencias.uni">Historial de Oficios de las Dependencias enviados como respuesta <strong style="color:#ef0000" id="dvg_historial_respuestas_p"></strong></a></li>
                                 <%
                              }
                              else if(cargo.equals("2"))
                              {
                                 %>
                                 <li><a href="oficioCircularListar.uni">Borrador <strong style="color:#ef0000" id="dvg_borrador" ></strong></a></li>
                                 <li><a href="oficioCircularListar.uni?nump=3">Enviados al jefe(a) ocdo <strong style="color:#ef0000" id="dvg_enviado_jefe_ocdo"></strong></a></li>
                                 <li><a href="oficioCircularListar.uni?nump=4">Aprobados por el jefe(a) ocdo <strong style="color:#ef0000" id="dvg_aprobados_por_jefe"></strong></a></li>
                                 <li><a href="oficioCircularListar.uni?nump=1">Enviados a las dependencias(Pendientes) <strong style="color:#ef0000" id="dvg_enviado_dependencias"></strong></a></li>
                                 <!-- <li><a href="oficioCircularListar.uni?nump=5">Revisados por las dependencias <strong style="color:#ef0000" id="dvg_oficios_revisados"></strong></a></li> -->
                                 <li><a href="oficioCircularListar.uni?nump=2">Historial de Oficios Enviados <strong style="color:#ef0000" id="dvg_historial_enviados_p"></strong></a></li>
                                 <li><a href="oficioCircularListarOficioTramitado.uni">Historial de Oficios Tramitados <strong style="color:#ef0000" id="dvg_historial_tramitados_p"></strong></a></li>
                                 <li><a href="listarRespuestaDependencias.uni">Historial de Oficios de las Dependencias enviados como respuesta <strong style="color:#ef0000" id="dvg_historial_respuestas_p"></strong></a></li>
                                 <%
                              }
                          }
                          else if(Rol.equals("ROL02") || Rol.equals("ROL03"))
                          {
                             %>
                             <li><a href="oficioCircularListar.uni">Borrador <strong style="color:#ef0000" id="dvg_borrador" ></strong></a></li>
                             <li><a href="oficioCircularListar.uni?nump=1">Oficios Por Revisar <strong style="color:#ef0000" id="dvg_enviado_dependencias"></strong></a></li>
                             <li><a href="oficioCircularListarOficioTramitado.uni">Historial de Oficio Atendidos <strong style="color:#ef0000" id="dvg_historial_tramitados_p" ></strong></a></li>
                             <li><a href="listarRespuestaDependencias.uni">Historial de Oficios Enviados <strong style="color:#ef0000" id="dvg_historial_respuestas_p" ></strong></a></li>
                             <%
                          }    
                      %>
                  </ul>
             </li>                          
           <%
           }
           %>
           <%
           if(Rol.equals("ROL01") || Rol.equals("ROL02"))
           {//Visualizacion para los usuarios OCOD,USUARIO,RECTORADO, ASESORA LEGAL
           %>
             <li style="position:relative" class="li_menu_desplegable_top">
               <a  href="javascript:;" title="Organigramas Pendientes">
                 <img src="fileproject/img/sistema/administrador/icoPedidos.gif" width="24" height="20" hspace="3" vspace="0" border="0" align="absmiddle" />
                 Organigramas 
               </a>
                <!--MENU DESPLEGABLE - ESTRUCTURA ORGANICA-->
                 <ul class="menu_sub_top">
                 <%
                 if( Rol.equals("ROL01") )
                 {
                 %>
                    <li><a href="organigramaListar.uni">Tramitandose <strong style="color:#ef0000" id="dvg_estructuras_org_p" ></strong></a></li>
                    <li><a href="organigramaListar.uni?ver=aprobado">Aprobados <strong style="color:#ef0000" id="dvg_estr_aprob_org_p" ></strong></a></li>
                    <li><a href="organigramaListar.uni?ver=historial">Historial <strong style="color:#ef0000" id="dvg_estr_historial_org_p" ></strong></a></li>
                 <%
                 } else if(Rol.equals("ROL02") || Rol.equals("ROL03") ){%>
                    <li><a href="organigramaListar.uni">Tramitandose  <strong style="color:#ef0000" id="dvg_estructuras_org_p" ></strong></a></li>
                    <li><a href="organigramaListar.uni?ver=aprobado">Aprobados  <strong style="color:#ef0000" id="dvg_estr_aprob_org_p" ></strong></a></li>
                    <li><a href="organigramaListar.uni?ver=historial">Historial  <strong style="color:#ef0000" id="dvg_estr_historial_org_p" ></strong></a></li>
                 <%}%>
                 </ul> 
             </li>
           <%
           }
           %>                     
          </ul>
          <%
          }
          else
          {
          %>
            <h1 class="txtStyleAd1" align="right" style="padding-right:4px;">
                <strong>Inicio de Sesión</strong>
            </h1>
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
        ArrayList<orgen_ta_herramientas> listaHerramienta=(ArrayList)session.getAttribute("listaHerramienta");
     %>
      <ul class="botonesMenuPrincipal">        
        <%
        out.println("<li id=\"idInicioTop\"><a href=\"oficioCircularListar.uni?nump=1\"><img src=\"fileproject/img/sistema/administrador/imgInicio.gif\"  border=\"0\"/><br />Inicio</a></li>");
         for(orgen_ta_herramientas Her:listaHerramienta)
          {
            String urlDestino=Her.getVc_url_herramienta();
            if(Her.getVc_url_herramienta().equals("submenuScript"))
            {
                urlDestino="javascript:fnl_lisSubMenu('"+Her.getCh_codigo_herramienta()+"');void(0);";
            }
            out.println("<li id=\""+Her.getCh_codigo_herramienta()+"\"><a href=\""+urlDestino+"\"><img src=\"fileproject/img/sistema/administrador/"+Her.getVc_icono()+"\"  border=\"0\"/><br />"+Her.getVc_nombre()+"</a></li>");
         }
      // out.println("<li id=\"idMenRoles\"><a href=\"javascript:fnl_lisRoles();void(0);\"><img src=\"fileproject/img/sistema/administrador/imgClientes.gif\"  border=\"0\"/><br />Mi Perfil</a></li>");
        %>
      </ul>
    <%    
    }
    else
    {
    %>
   <center> <marquee  width=800 hspace=10 BEHAVIOR=ALTERNATE scrolldelay=1 >
       <font color="808080" style="font-size:30px" >
         <strong>Oficina Central de Desarrollo Organizacional
         </strong>
       </font>
    </marquee></center>
    <%
    }
    %>
    </div>   
<div id="contBodyC">