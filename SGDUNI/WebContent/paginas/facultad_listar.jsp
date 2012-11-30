<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_facultad" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsFacultad.js");
</script>

    <%ArrayList<orgen_ta_facultad> facultades =(ArrayList)request.getAttribute("facultades");%>
    <%
    String iconoEstado=null;
    String iconoTitle=null;
    String Estado=null;
    %>

    <div  class="tituloPag" align="center">
     <strong>Lista de Facultades</strong>
    </div>

	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> >  Facultades</div>

  <div id="DivGrillaResul">
    <div class="barra_menu_grilla" style="float:right;">
		<a href="llamarFormInsertar.uni" class="BotonStan2" style="text-decoration:none;">Agregar Facultad</a>
	</div>
    <div class="formBody" style="height:500px; overflow:scroll">
      <div>
       <br/><br/>
      </div>

    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
        <th height="35" align="center" bgcolor="#E5E5E5" class="titu">ID</th>
        <th bgcolor="#E5E5E5" class="titu">NOMBRE</th>
        <th bgcolor="#E5E5E5" class="titu">ABREV.</th>
        <th bgcolor="#E5E5E5" class="titu">DESCRIPCION</th>
        <th bgcolor="#E5E5E5" class="titu">ORGANO</th>
        <th colspan="2" bgcolor="#E5E5E5"  class="titu">OPERACIONES</th>
        <%for (orgen_ta_facultad facultad : facultades)
        {
        %>
            <tr>
                <td height="38" align="center" bgcolor="#E5E5E5"><strong><%=facultad.getIn_codigo_facultad()%></strong></td>
                <td class="textCont"><%=facultad.getVc_nombre()%></td>
                <td class="textCont"><%=facultad.getVc_abrev_nom() %></td>
                <td class="textCont"><%=facultad.getVc_descripcion()%></td>
                <td class="textCont"><%=facultad.getVc_nombre_organo()%></td>
                 <td align="center" valign="middle">
                       <a href="actualizarFacultad.uni?codigo=<%=facultad.getIn_codigo_facultad()%>" title="Editar">
                         <img src="fileproject/img/sistema/formularios/icoModi.gif" width="18" height="18" border="0" title="Editar"/>
                       </a>
                 </td>
                 <td align="center" valign="middle">
                          <%
                          if(facultad.getCh_estado() != null && facultad.getCh_estado().equals("01"))
                              {
                              iconoEstado="bien.gif";
                              iconoTitle="Click para dar de baja";
                              Estado="02";
                              }
                          else
                              {
                              iconoEstado="bien2.gif";
                              iconoTitle="Click para activar";
                              Estado="01";
                              }

						  %>
                        <a id="a_ico_<%=facultad.getIn_codigo_facultad()%>" href="javascript:fnl_estadoFacultad('<%=Estado%>','<%=facultad.getIn_codigo_facultad()%>');" title="<%=iconoTitle%>">
                          <img id="img_ico_<%=facultad.getIn_codigo_facultad()%>" src="fileproject/img/sistema/formularios/<%=iconoEstado%>" width="18" height="18" border="0" title="<%=iconoTitle%>"/>
                        </a>
                  </td>
            </tr>
        <%}%>
    </table>
  </div>
</div>
