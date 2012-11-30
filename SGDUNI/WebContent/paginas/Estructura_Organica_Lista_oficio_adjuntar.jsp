<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orpro_oficio_circular " %>
<%@ page import="com.sgduni.utilitarios.ContarCaracteresVista" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%ArrayList<orpro_oficio_circular> lista_Oficios = (ArrayList)request.getAttribute("listaOficiosPendientes");%>
<div  class="tituloPag" align="center" style="width:370px;">
     Lista de Oficios que puede Adjuntar
</div>
<br/>
<div id="DivGrillaResul" style="width:630px; height:300px; overflow:auto; padding:0px;">
     <table width="590" border="0" align="center" cellpadding="2" cellspacing="2">
        <th width="21%" height="29" align="center" bgcolor="#E5E5E5" class="titu">CODIGO</th>
        <th width="18%" bgcolor="#E5E5E5" class="titu">FECHA</th>
        <%for (orpro_oficio_circular oficio : lista_Oficios)
        {
        %>
            <tr>
                <td height="45" align="center" bgcolor="#E5E5E5" class="linkStyle2">
                    <strong>
                      <a href="javascript:;" onclick="fnl_select_cod('<%=oficio.getIn_codigo_oficio()%>','<%=oficio.getCh_codigo_oficio()%>')">
						  <%=oficio.getCh_codigo_oficio()%>
                      </a>
                     </strong>
                 </td>
              <td class="textCont"><%=oficio.getDt_fecha()%></td>
            </tr>
        <%}%>
    </table>
</div>
