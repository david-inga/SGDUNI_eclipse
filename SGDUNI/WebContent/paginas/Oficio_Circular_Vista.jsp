<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="com.sgduni.forms.orpro_oficio_circular" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
 orpro_oficio_circular objOfic=(orpro_oficio_circular)request.getAttribute("ObjOfico");
%>

<div  class="tituloPag" align="center" style="width:400px;">
        <strong>Vista Oficio Circular</strong><br/>
        <%=objOfic.getCh_codigo_oficio() %>
</div>

<div align="right" style="margin:10px;border:#ccc solid 1px;padding:5px;background-color:#f6f6f6">
  <a href="oficioCircularImprimir.uni?idOfc=<%=objOfic.getIn_codigo_oficio()%>&proc=print&idEmisor=<%=objOfic.getIn_usuario_emisor()%>" target="_blank" title="Imprimir">
  		<img src="fileproject/img/sistema/formularios/menu_imprimir.gif" width="30" height="29" border="0"></a>
  <!-- <a href="oficioCircularImprimir.uni?idOfc=<%=objOfic.getIn_codigo_oficio()%>&proc=word&idEmisor=<%=objOfic.getIn_usuario_emisor()%>" target="_blank" title="Exportar a Word">
      <img src="fileproject/img/sistema/formularios/menu_word.gif" width="30" height="29" border="0"></a>  -->
</div>

<div style="margin:10px auto;padding:8px;">
    <p style="text-align: center;"><span style="color: #ff0000; font-size: medium;">UNIVERSIDAD NACIONAL DE INGENIERIA</span></p>
    <hr />
    <p style="text-align: center;"><span style="font-size: medium;"><strong><span style="color: #ff0000;"><span style="color: #000000;">Oficio Central de Desarrollo Organizacional</span></span></strong></span></p>
    <p style="text-align: center;"><span style="color: #ff0000; font-size: small;">&nbsp; <strong><em><span style="color: #000000;">"<%=objOfic.getVc_nombre_anio() %>"</span></em></strong></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"><strong><%=objOfic.getCh_codigo_oficio()%></strong><br /></span></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"><%=objOfic.getVc_ciudad() %>, <%=objOfic.getDt_fecha()%><br /></span></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"><strong><br /></strong></span></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"></span></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"><span style="text-decoration: underline;"> Presente.-</span></span></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"><strong></strong><%=objOfic.getVc_cuerpo_doc() %></span></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"><br /></span></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"><strong>Sr(a). <%=objOfic.getNom_usuario() %></strong></span></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%=objOfic.getCargo_usuario() %></span></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"><br /></span></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"><strong><span style="font-size: x-small;">GTHH/caa.</span></strong></span></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"><br /></span></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"><br /></span></span></p>
    <p style="text-align: left;"><span style="font-size: xx-small;"><strong><span style="color: #ff0000; font-family: arial,helvetica,sans-serif;"><span style="color: #000000;">Av. Tupac Amaru n&ordm;.210-LIMA 25, Apartado 1301-Peru</span></span></strong></span></p>
    <p style="text-align: left;"><span style="font-size: xx-small;"><strong><span style="color: #ff0000; font-family: arial,helvetica,sans-serif;"><span style="color: #000000;">Telefono: (511)481-8894 Central Telefonica: (511)4811070 Anexos:359-242</span></span></strong></span></p>
    <p style="text-align: left;"><span style="color: #ff0000; font-size: small; font-family: arial, helvetica, sans-serif;"><span style="color: #000000;"><span style="font-size: xx-small;"><strong>E-mail <span style="text-decoration: underline;">ocdo@uni.edu.pe</span></strong></span><br /></span></span></p>
</div>

<div align="left" style="margin:10px;border:#ccc solid 1px;padding:5px;background-color:#f6f6f6">
  <a href="oficioCircularImprimir.uni?idOfc=<%=objOfic.getIn_codigo_oficio()%>&proc=print&idEmisor=<%=objOfic.getIn_usuario_emisor()%>" target="_blank" title="Imprimir">
  		<img src="fileproject/img/sistema/formularios/menu_imprimir.gif" width="30" height="29" border="0"></a>
  <!--
  <a href="oficioCircularImprimir.uni?idOfc=<%=objOfic.getIn_codigo_oficio()%>&proc=word&idEmisor=<%=objOfic.getIn_usuario_emisor()%>" target="_blank" title="Exportar a Word">
      <img src="fileproject/img/sistema/formularios/menu_word.gif" width="30" height="29" border="0"></a> -->

</div>