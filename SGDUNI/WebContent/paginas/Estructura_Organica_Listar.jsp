<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_estructura_organica" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsEstructura_Organica.js");
</script>
    <%
    ArrayList<orgen_ta_estructura_organica> listaEstructuraOrganica =(ArrayList)request.getAttribute("listaEstructuraOrganica");
    String Rol = session.getAttribute("xrol").toString().toUpperCase().trim();
    String titulo = request.getAttribute("titulo").toString().trim();
    %>

    <div  class="tituloPag" align="center">
     <strong> <%=titulo%> </strong>
    </div>

	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> > Lista de Estructuras Organicas</div>

  <div id="DivGrillaResul">
      <!--
    <div class="barra_menu_grilla" style="float:right;">
        <a href="nuevoEstructuraOrganica.uni" class="BotonStan2" style="text-decoration:none;">Agregar</a>
	</div> -->
    <div class="formBody" style="height:350px; overflow:scroll">
      <br/>
    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
        <th width="5%" height="35" align="center" bgcolor="#E5E5E5" class="titu">ID</th>
        <th width="13%" bgcolor="#E5E5E5" class="titu">CODIGO</th>
        <th width="10%" bgcolor="#E5E5E5" class="titu">FECHA</th>
        <th width="50%" bgcolor="#E5E5E5" class="titu">FACULTAD O DEPENDENCIA</th>
         <% if( Rol.equals("ROL01"))
            { %>
            <th width="22%" colspan="2" bgcolor="#E5E5E5"  class="titu">OPERACIONES</th>
         <% } else if( Rol.equals("ROL02"))
            { %>
            <th width="22%" colspan="4" bgcolor="#E5E5E5"  class="titu">OPERACIONES</th>
          <%}%>
        <%for (orgen_ta_estructura_organica estructura_organica : listaEstructuraOrganica )
        {
        %>
            <tr>
                <td height="38" align="center" bgcolor="#E5E5E5"><strong><%=estructura_organica.getIn_codigo_estructura() %></strong></td>
                <td align="center" class="textCont"><%=estructura_organica.getCh_codigo_estructura() %></td>
                <td align="center" class="textCont"><%=estructura_organica.getDt_fecha() %></td>
                <td align="center" class="textCont" title="Creado Por <%=estructura_organica.getNombre_Usuario() %>"><%=estructura_organica.getNombre_FacDep() %></td>
                <% if( Rol.equals("ROL02"))
                   {  %>
                <td align="center" valign="middle">
                       <a onclick="javascript:fnl_llamarPopupVersiones('<%=estructura_organica.getIn_codigo_estructura()%>');" title="Ver Versiones">
                         <img src="fileproject/img/sistema/formularios/icoPro4.gif" width="18" height="18" border="0" title="Ver Versiones"/>
                       </a>
                </td>
                <td align="center" valign="middle">
                       <a href="documentos/EstructurasOrganicas/<%=estructura_organica.getVc_nombre_archivo()%>" title="Descargar ultima versión">
                         <img src="fileproject/img/sistema/formularios/b_regi2.gif" width="18" height="18" border="0" title="Descargar ultima versión"/>
                       </a>
                </td>
                <td align="center" valign="middle">
                       <a href="actualizarEstructuraOrganica.uni?codigoEstructura=<%=estructura_organica.getIn_codigo_estructura() %>" title="Modificar">
                         <img src="fileproject/img/sistema/formularios/icoModi.gif" width="18" height="18" border="0" title="Modificar"/>
                       </a>
                </td>
                <td align="center" valign="middle">
                       <a href="revisarEstructuraOrganica.uni?in_codigo_Estructura=<%=estructura_organica.getIn_codigo_estructura() %>&ch_codigo_Estructura=<%=estructura_organica.getCh_codigo_estructura()%>&organigrama=<%=estructura_organica.getVc_nombre_archivo()%>" title="Revisar Organigrama">
                         <img src="fileproject/img/sistema/formularios/icoOrganigrama.gif" width="18" height="18" border="0" title="Revisar Organigrama"/>
                       </a>
                </td>
                <%} else if( Rol.equals("ROL01") ){%>
                <td align="center" valign="middle">
                       <a onclick="javascript:fnl_llamarPopupVersiones('<%=estructura_organica.getIn_codigo_estructura()%>');" title="Ver Versiones">
                         <img src="fileproject/img/sistema/formularios/icoPro4.gif" width="18" height="18" border="0" title="Ver Versiones"/>
                       </a>
                </td>
                <td align="center" valign="middle">
                       <a href="revisarEstructuraOrganica.uni?in_codigo_Estructura=<%=estructura_organica.getIn_codigo_estructura() %>&ch_codigo_Estructura=<%=estructura_organica.getCh_codigo_estructura()%>&organigrama=<%=estructura_organica.getVc_nombre_archivo()%>" title="Revisar Organigrama">
                         <img src="fileproject/img/sistema/formularios/icoOrganigrama.gif" width="18" height="18" border="0" title="Revisar Organigrama"/>
                       </a>
                </td>
                <%}%>
            </tr>
       <%}%>
    </table>
   <br/>
  </div>
</div>
