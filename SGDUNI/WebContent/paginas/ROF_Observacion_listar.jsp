<%--
    Document   : ObservacionesROFlistar
    Created on : 12-ene-2012, 17:43:41
    Author     : Sistemas
--%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orpro_ta_observaciones_rof" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>


<div  class="tituloPag" align="center">
     <strong>Observaciones ROF</strong>
</div>
<%
String idRof=request.getAttribute("idRof").toString();
int idUserSes = Integer.parseInt(session.getAttribute("xid").toString());
%>

<%ArrayList<orpro_ta_observaciones_rof > lisobservaciones =(ArrayList)request.getAttribute("listaObservaciones");%>
<div id="localizador"> <a href="javascript:;" onclick="fnl_llamarPopupObservaciones('<%=idRof%>');">Hacer una observación</a> > Observaciones</div>

<div class="formBody" style="max-height:280px;overflow:auto; width:500px;">
      <div class="formBody" style="max-height:700px;overflow:auto; width:500px;">
      <%
      String cssObj = null;//Tipo de CSS para los comentarios

      for(orpro_ta_observaciones_rof observacion : lisobservaciones)
      {
          cssObj="2";
          if(observacion.getIn_codigo_usuario() == idUserSes )
              cssObj="1";
      %>

    <div class="FondoComentario<%=cssObj%>">
      <div align="left" class="txtStyleAd1" style="margin-bottom:3px;">
           <strong><%=observacion.getNombreUsuario() %></strong> Dice :
      </div>
        <p class="txtStyleAd2">
            <%=observacion.getVc_observacion()%>
        </p>
        <div align="right" class="txtStyleAd3">
             <%=observacion.getDt_fecha_crea()%>
        </div>
        <span class="imgIcoL<%=cssObj%>"></span>
      </div>
      <%
      }
      %>
</div>
</div>