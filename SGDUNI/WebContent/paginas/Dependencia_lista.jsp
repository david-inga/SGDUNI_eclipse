<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_dependencia" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsDependencia.js");
</script>
    <%ArrayList<orgen_ta_dependencia> dependencias =(ArrayList)request.getAttribute("dependencias");
    String iconoEstado=null;
    String iconoTitle=null;
    String Estado=null;

    String idUsu = session.getAttribute("xid").toString().trim();
    %>

    <div  class="tituloPag" align="center">
     <strong>Lista de Dependencias</strong>
    </div>

	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> >  Dependencias</div>

  <div id="DivGrillaResul">
    <div class="barra_menu_grilla" style="float:right;">
		<a href="llamarFormInsertDependencia.uni" class="BotonStan2" style="text-decoration:none;">Agregar Dependencia</a>
	</div>

    <div class="formBody" style="height:500px; overflow:scroll">
        <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
        <th height="35" align="center" bgcolor="#E5E5E5" class="titu">ID</th>
        <th bgcolor="#E5E5E5" class="titu">NOMBRE</th>
        <th bgcolor="#E5E5E5" class="titu">ABREV.</th>
        <th bgcolor="#E5E5E5" class="titu">DESCRIPCION</th>
        <th bgcolor="#E5E5E5" class="titu">ORGANO</th>
        <th colspan="2" bgcolor="#E5E5E5"  class="titu">OPERACIONES</th>
        <%for (orgen_ta_dependencia dependencia : dependencias)
        {
        %>
            <tr>
                <td height="38" align="center" bgcolor="#E5E5E5"><strong><%=dependencia.getIn_codigo_dependencia()%></strong></td>
                <td class="textCont"><%=dependencia.getVc_nombre()%></td>
                <td class="textCont"><%=dependencia.getVc_abrev_nom()%></td>
                <td class="textCont"><%=dependencia.getVc_descripcion()%></td>
                <td class="textCont"><%=dependencia.getNombre_organo()%></td>
                 <td align="center" valign="middle">
                       <a href="actualizarDependencia.uni?codigo=<%=dependencia.getIn_codigo_dependencia()%>" title="Editar">
                         <img src="fileproject/img/sistema/formularios/icoModi.gif" width="18" height="18" border="0" title="Editar"/>
                       </a>
                 </td>
                 <td align="center" valign="middle">
                          <%
                          if(dependencia.getCh_estado() != null && dependencia.getCh_estado().equals("01"))
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
                          <a id="a_ico_<%=dependencia.getIn_codigo_dependencia()%>" href="javascript:fnl_estadoDependencia('<%=Estado%>','<%=dependencia.getIn_codigo_dependencia()%>','<%=idUsu%>');" title="<%=iconoTitle%>">
                          <img id="img_ico_<%=dependencia.getIn_codigo_dependencia()%>" src="fileproject/img/sistema/formularios/<%=iconoEstado%>" width="18" height="18" border="0" title="<%=iconoTitle%>"/>
                        </a>
                  </td>
            </tr>
        <%}%>
    </table>
  </div>
</div>

