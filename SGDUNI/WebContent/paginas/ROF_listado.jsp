<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orpro_ta_rof" %>
<%@ page import="com.sgduni.utilitarios.ContarCaracteresVista" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsRof.js");
</script>
<%
String Rol = session.getAttribute("xrol").toString().trim().toUpperCase();
String titulo = request.getAttribute("titulo").toString().trim();
String borrador = request.getAttribute("borrador").toString().trim();
ArrayList<orpro_ta_rof> lisRof = (ArrayList)request.getAttribute("ListaRof");
String pag_guardado = null;
%>
<div  class="tituloPag" align="center" style="width:300px; margin:auto;">
 <strong><%=titulo%></strong>
</div>

<div id="DivGrillaResul" style="width:850px;">
    <%
    if(Rol.equals("ROL01") || Rol.equals("ROL02") || Rol.equals("ROL03"))
    {//SOLO PARA USUARIOS OCDO Y USUARIO
    %>
    <div class="barra_menu_grilla" style="float:right;">
        <a href="listarRof.uni" class="BotonStan2" style="text-decoration:none;">Listar Documentos ROF en tramite</a>
        <a href="listarRof.uni?ver=borrador" class="BotonStan1" style="text-decoration:none;">Listar Documentos ROF no Terminados</a>
		<a href="llamarFormularioGuardarRof.uni?numpagrof=1" class="BotonStan2" style="text-decoration:none;">Nuevo ROF</a>
	</div>
    <%
    }
    %>

    <div class="formBody" style="height:400px; overflow:scroll;">
      <div>
       <br/><br/>
      </div>

    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">

        <th width="5%" height="35" align="center" bgcolor="#E5E5E5" class="titu">ID</th>
        <th width="15%" bgcolor="#E5E5E5" class="titu">CODIGO</th>
        <th width="30%" bgcolor="#E5E5E5" class="titu">FACULTAD ó DEPENDENCIA</th>
        <th width="20%" bgcolor="#E5E5E5" class="titu">ORGANIGRAMA</th>
        <th bgcolor="#E5E5E5" colspan="6"  class="titu">OPERACIONES</th>
        <%for (orpro_ta_rof rowRof : lisRof)
        {
        pag_guardado = String.valueOf(rowRof.getIn_codigo_guardado());
        %>
            <tr>
                <td height="30" align="center" bgcolor="#E5E5E5"><strong><%=rowRof.getIn_codigo_rof() %></strong></td>
                <td class="textCont" align="center" ><%=rowRof.getCh_codigo_rof() %></td>
                <td class="textCont"  align="center" ><%=rowRof.getNombreFaculDepen() %></td>
                <%
                if( rowRof.getNombreEstructura() == null )
                {
                 %>
                <td class="textCont" align="center">
                    No tiene Organigrama
                </td>
                 <%
                }
                else
                {
                %>
                <td  class="textCont" align="center">
                    <a href="" title="Ver Organigrama" onclick="window.open('documentos/EstructurasOrganicas/<%=rowRof.getNombreEstructura() %>','','width=550,height=650','toolbar=no')"> <%=rowRof.getCh_codigo_estructura() %>
                    </a>
                </td>
                <%
                }

                if( borrador.equals("1") )
                {
                %>
                 <td colspan="6" align="center" valign="middle">
                       <a href="" onclick="alert('En Construcción');" title="Continuar Editando el ROF">
                         <img src="fileproject/img/sistema/formularios/img_la_e.gif" width="18" height="18" border="0" title="Continuar Editando con el ROF"/>
                       </a>
                 </td>
                <%
                }
                else
                {
                %>
                <td align="center" valign="middle">
                       <a href="" title="Ver Versiones" onclick="alert('En Construcción');">
                         <img src="fileproject/img/sistema/administrador/version3.png" width="18" height="18" border="0" title="Ver Versiones"/>
                       </a>
                 </td>
                 <td align="center" valign="middle">                 <td align="center" valign="middle">

                       <a href="exportarROFaPDF.uni?idRof=2&idVersionRof=2" title="Exportar a PDF" >
                         <img src="fileproject/img/sistema/formularios/menu_pdf.gif" width="18" height="18" border="0" title="Exportar a PDF"/>
                       </a>
                 </td>
                 <td align="center" valign="middle">
                       <a href="" title="Ver Online" onclick="alert('En Construcción');">
                         <img src="fileproject/img/sistema/administrador/ver_online.png" width="18" height="18" border="0" title="Ver Online"/>
                       </a>
                 </td>
                 <td align="center" valign="middle">
                       <a href="javascript:;" title="Observaciones" onclick="javascript:fnl_llamarPopupObservaciones('<%=rowRof.getIn_codigo_rof()%>')">
                         <img src="fileproject/img/sistema/formularios/icoObservaciones.gif" width="18" height="18" border="0" title="Observaciones"/>
                       </a>
                 </td>
                 <td align="center" valign="middle">
                       <a href="" title="Modificar" onclick="alert('En Construcción');">
                         <img src="fileproject/img/sistema/administrador/cog_edit.png" width="18" height="18" border="0" title="Modificar"/>
                       </a>
                 </td>
                 <%
                 }
                 %>
            </tr>
        <%}%>
    </table>
  </div>
</div>