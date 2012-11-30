<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_cargo_usuario" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsCargoEstructural.js");
</script>
    <%ArrayList<orgen_ta_cargo_usuario> cargoEstrucs =(ArrayList)request.getAttribute("cargosEstructurales");
    String iconoEstado=null;
    String Estado=null;
    %>

    <div  class="tituloPag" align="center">
     <strong>Lista de Cargos para los Usuario</strong>
    </div>

	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> >  Cargos de Usuario </div>

  <div id="DivGrillaResul">
    <div class="barra_menu_grilla" style="float:right;">
		<a href="llamarFormInsertCargoEstruct.uni" class="BotonStan2" style="text-decoration:none;">Agregar Cargo</a>
	</div>

    <div class="formBody" style="height:500px; overflow:auto">
      <div>
       <br/><br/>
      </div>

    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
        <th height="35" align="center" bgcolor="#E5E5E5" class="titu">ID</th>
        <th bgcolor="#E5E5E5" class="titu">NOMBRE</th>
        <th bgcolor="#E5E5E5" class="titu">DESCRIPCION</th>
        <th colspan="2" bgcolor="#E5E5E5"  class="titu">OPERACIONES</th>
        <%for (orgen_ta_cargo_usuario cargoEstruc : cargoEstrucs )
        {
        %>
            <tr>
                <td height="38" align="center" bgcolor="#E5E5E5"><strong><%=cargoEstruc.getIn_codigo_cargo_estruc() %></strong></td>
                <td class="textCont"><%=cargoEstruc.getVc_nombre()%></td>
                <td class="textCont"><%=cargoEstruc.getVc_descripcion()%></td>
                 <td align="center" valign="middle">
                       <a href="actualizarCargoEstructural.uni?codigo=<%=cargoEstruc.getIn_codigo_cargo_estruc()%>" title="Editar">
                         <img src="fileproject/img/sistema/formularios/icoModi.gif" width="18" height="18" border="0" title="Editar"/>
                       </a>
                 </td>
                 <td align="center" valign="middle">
                          <%
                          if(cargoEstruc.getCh_estado() != null && cargoEstruc.getCh_estado().equals("01"))
                              {
                              iconoEstado="bien.gif";
                              Estado="02";
                              }
                          else
                              {
                              iconoEstado="bien2.gif";
                              Estado="01";
                              }
						  %>
                          <a href="javascript:;"  id="a_ico_<%=cargoEstruc.getIn_codigo_cargo_estruc()%>" onclick="fnl_estadoCargoEstructural('<%=Estado%>','<%=cargoEstruc.getIn_codigo_cargo_estruc()%>');" >
                          <img id="img_ico_<%=cargoEstruc.getIn_codigo_cargo_estruc()%>" src="fileproject/img/sistema/formularios/<%=iconoEstado%>" width="18" height="18" border="0"/>
                        </a>
                  </td>
            </tr>
        <%}%>
    </table>
  </div>
</div>


