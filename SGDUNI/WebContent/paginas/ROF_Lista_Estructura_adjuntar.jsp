<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_estructura_organica " %>
<%@ page import="com.sgduni.utilitarios.ContarCaracteresVista" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%ArrayList<orgen_ta_estructura_organica> lista_Organigramas = (ArrayList)request.getAttribute("listaOrganigramasAprobadas");%>
<div  class="tituloPag" align="center" style="width:370px;">
     Lista de Organigramas Aprobadas que puede Adjuntar
</div>
<br/>
<div id="DivGrillaResul" style="width:630px; height:300px; overflow:auto; padding:0px;">
     <table width="590" border="0" align="center" cellpadding="2" cellspacing="2">
        <th width="21%" height="29" align="center" bgcolor="#E5E5E5" class="titu">CODIGO</th>
        <th width="18%" bgcolor="#E5E5E5" class="titu">FECHA</th>
        <%for (orgen_ta_estructura_organica organigrama : lista_Organigramas)
        {
        %>
            <tr>
                <td height="45" align="center" bgcolor="#E5E5E5" class="linkStyle2">
                    <strong>
                      <a href="javascript:;" onclick="fnl_select_cod('<%=organigrama.getIn_codigo_estructura() %>','<%=organigrama.getCh_codigo_estructura() %>')">
						  <%=organigrama.getCh_codigo_estructura()%>
                      </a>
                     </strong>
                 </td>
              <td class="textCont"><%=organigrama.getDt_fecha()%></td>
            </tr>
        <%}%>
    </table>
</div>
