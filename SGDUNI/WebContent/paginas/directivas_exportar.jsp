<%--
    Document   : Oficio_Circular_Imprimir
    Created on : 25-ene-2012, 11:32:46
    Author     : Sistemas
--%>
<%
String proceso=(String)request.getParameter("proc");
if(proceso!=null)
{//SOLO SI EXISTE LA VARIABLE "PROC"
    if(proceso.equals("word"))
    {
        response.setContentType("application/vnd.ms-word");
        response.setHeader("Content-disposition","attachment;filename=Directivas.doc");
    }
%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sgduni.forms.orpro_ta_directivas" %>
<%@ page import="com.sgduni.forms.orpro_detalle_objetivo_direc" %>
<%@ page import="com.sgduni.forms.orpro_detalle_base_legal" %>
<%@ page import="com.sgduni.forms.orpro_detalle_normas_gen" %>
<%@ page import="com.sgduni.forms.orpro_det_proc_direc" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
orpro_ta_directivas objDirec=(orpro_ta_directivas)request.getAttribute("objDirec");
ArrayList<orpro_detalle_objetivo_direc> lisObjetivos=(ArrayList)request.getAttribute("lisObjetivos");
ArrayList<orpro_detalle_base_legal> lisbaseslegales = (ArrayList)request.getAttribute("lisBasesLegales");
ArrayList<orpro_detalle_normas_gen> lisNormasGenerales = (ArrayList)request.getAttribute("lisNormasGenerales");
ArrayList<orpro_det_proc_direc> lisProcedimientos = (ArrayList)request.getAttribute("lisProcedimientos");

 String alcance =new String(objDirec.getVc_alcance().getBytes("ISO-8859-1"),"UTF-8").toUpperCase();
  String responsabilidad =new String(objDirec.getVc_responsabilidad().getBytes("ISO-8859-1"),"UTF-8").toUpperCase();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="fileproject/icono/favicon.ico" />
        <link rel="icon" type="image/gif" href="fileproject/icono/animated_favicon1.gif"/>
        <title>.</title>
    </head>
    <body>
     <div id="dvl_impri_ofc" style="width:650px; margin:10px auto; padding:10px;">
         <p style="text-align: center;" align="center"><span style="font-size: small;"><strong>DIRECTIVA: OTORGAMIENTO Y CONTROL DE VIATICOS Y RENDICIÓN DE CUENTAS POR COMISIÓN DE SERVICIOS.</strong></span></p>
         <p style="text-align: center;"><span style="color: #ff0000; font-size: medium;"><br /></span></p>
         <p align="center"><span style="font-size: small;"><strong>CAPITULO I</strong></span></p>
         <p align="center"><span style="font-size: small;"><strong>DISPOSICIONES GENERALES</strong></span></p>
         <p align="center"><span style="font-size: small;"><strong>&nbsp;</strong></span></p>
         <p><span style="font-size: small;"><strong><strong>Art&iacute;culo 1&ordm;.&nbsp; OBJETIVO.</strong><br /></strong></span></p>
         <p>
         <%
              if(lisObjetivos!=null)
                  out.println("<ul>");
                  {
                  for(orpro_detalle_objetivo_direc liObj : lisObjetivos)
                  {
                      String descripcion =new String(liObj.getVc_descripcion().getBytes("ISO-8859-1"),"UTF-8");
                      out.println("<li><span style=\"font-size: x-small;\"> "+descripcion.toUpperCase()+"</span></li>");
                  }
                 out.println("</ul>");
              }
          %>
          </p>
         <p><span style="font-size: small;"><strong>Articulo 2&deg;. &nbsp;ALCANCE.</strong></span></p>
         <p><span style="font-size: x-small;"><%=alcance%></span></p>
         <p><span style="font-size: small;"><strong>Art&iacute;culo 3&ordm;. &nbsp;BASE LEGAL.</strong></span></p>
         <p>
          <%
              if(lisbaseslegales != null)
                  out.println("<ul>");
                  {
                  for(orpro_detalle_base_legal base : lisbaseslegales)
                  {
                      String descripcionBase =new String(base.getVc_descripcion().getBytes("ISO-8859-1"),"UTF-8");
                      out.println("<li><span style=\"font-size: x-small;\"> "+descripcionBase.toUpperCase()+"</span></li>");
                  }
                 out.println("</ul>");
              }
          %>
         </p>
         <p><span style="font-size: small;"><strong>Art&iacute;culo 4&ordm;. NORMAS GENERALES.</strong></span></p>
         <p>
          <%
              if(lisNormasGenerales != null)
                  out.println("<ul>");
                  {
                  for(orpro_detalle_normas_gen norma : lisNormasGenerales)
                  {
                       String descripcionNorma =new String(norma.getVc_descripcion().getBytes("ISO-8859-1"),"UTF-8");
                      out.println("<li><span style=\"font-size: x-small;\"> "+descripcionNorma.toUpperCase()+"</span></li>");
                  }
                 out.println("</ul>");
              }
          %>
         </p>
         <p><span style="font-size: small;"><strong><br /></strong></span></p>
         <p align="center"><span style="font-size: small;"><strong>CAPITULO II</strong></span></p>
         <p align="center"><span style="font-size: small;"><strong>PROCEDIMIENTO</strong></span></p>
         <p><span style="font-size: small;"><strong><br /></strong></span></p>
         <p>
          <%
              if(lisProcedimientos != null)
               {
                  for(orpro_det_proc_direc proce : lisProcedimientos)
                  {
                      out.println("<p><span style=\"font-size: small;\"><strong>"+proce.getNombre_procedimiento().trim()+"</strong></span></p>");
                      //out.println("<p align=\"center\"><span style=\"font-size: small;\"><strong>&nbsp;</strong></span></p>");
                      out.println("<p><span style=\"font-size: x-small;\"> "+proce.getDescripcion_proce().trim()+"</span></p>");
                  }
              }
          %>
         </p>
         <p><span style="font-size: small;"><strong>RESPONSABILIDAD.</strong></span></p>
         <p><span style="font-size: x-small;"><%=objDirec.getVc_responsabilidad().trim()%></span></p>
         
     </div>
    </body>
</html>
<%
}
%>