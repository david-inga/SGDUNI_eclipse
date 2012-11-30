<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_usuario_lista" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsUsuario.js");
</script>

    <%ArrayList<orgen_ta_usuario_lista> usuarios=(ArrayList)request.getAttribute("usuarios");%>
    <%
    String iconoEstado=null;
    String Estado=null;
    %>

    <div  class="tituloPag" align="center">
     <strong>Lista de Usuarios</strong>
    </div>

	<div id="localizador"><a href="oficioCircularListar.uni?nump=1">Inicio</a> > Usuarios</div>

  <div id="DivGrillaResul">    
    <div class="barra_menu_grilla" style="float:right;">
		<a href="registrarusuario.uni" class="BotonStan2" style="text-decoration:none;">Agregar Usuario</a>
	</div>

    <div class="formBody" style="height:500px; overflow:scroll">
      <div>
       <br/><br/>
      </div>

    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
        <th  height="35" bgcolor="#E5E5E5" class="titu">USUARIO</th>
        <th  bgcolor="#E5E5E5" class="titu">CORREO</th>
        <th  bgcolor="#E5E5E5" class="titu">NOMBRE COMPLETO</th>
        <th colspan="3" bgcolor="#E5E5E5"  class="titu">OPERACIONES</th>
        <%for (orgen_ta_usuario_lista usuario : usuarios)
        {
            String nombreCompleto = usuario.getVc_nombres()+" "+usuario.getVc_apellido_paterno()+" "+usuario.getVc_apellido_materno();
        %>
            <tr>
                <td height="32" class="textCont"><%=usuario.getVc_usuario() %></td>
                <td class="textCont"><%=usuario.getVc_correo() %></td>
                <td class="textCont"><%=nombreCompleto%></td>
                 <td align="center" valign="middle">
                       <a href="javascript:;" onclick="javascript:fnl_llamarPopupVerDetalles('<%=usuario.getIn_codigo_usuario() %>')" title="Ver Detalles">
                         <img src="fileproject/img/sistema/formularios/icoPro4.gif" width="18" height="18" border="0" title="Ver Detalles"/>
                       </a>
                 </td>
                 <td align="center" valign="middle">
                       <a href="actualizarUsuario.uni?codigo=<%=usuario.getIn_codigo_usuario() %>" title="Editar">
                         <img src="fileproject/img/sistema/formularios/icoModi.gif" width="18" height="18" border="0" title="Editar"/>
                       </a>
                 </td>
                 <td align="center" valign="middle">
                          <%
                          if(usuario.getVc_estado()!=null && usuario.getVc_estado().equals("01"))
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
                        <a id="a_ico_<%=usuario.getIn_codigo_usuario()%>" href="javascript:fnl_estadoUsuario('<%=Estado%>','<%=usuario.getIn_codigo_usuario() %>');">
                          <img id="img_ico_<%=usuario.getIn_codigo_usuario()%>" src="fileproject/img/sistema/formularios/<%=iconoEstado%>" width="18" height="18" border="0"/>
                        </a>
                  </td>
            </tr>
        <%}%>
    </table>
  </div>
</div>