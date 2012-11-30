<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_organo" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsOrgano.js");
</script>

    <%ArrayList<orgen_ta_organo> organos =(ArrayList)request.getAttribute("organos");%>
    <%
    String iconoEstado=null;
    String iconoTitle=null;
    String Estado=null;
    %>
  <div id="DivGrillaResul" style="width:630px;height:250px; overflow:auto; padding:0px;" class="formBody">
    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
        <th height="35" align="center" bgcolor="#E5E5E5" class="titu">ID</th>
        <th bgcolor="#E5E5E5" class="titu">NOMBRE</th>
        <th bgcolor="#E5E5E5" class="titu">DESCRIPCION</th>
        <th colspan="2" bgcolor="#E5E5E5"  class="titu">OPERACIONES</th>
        <%for (orgen_ta_organo organo : organos)
        {
        %>
            <tr>
                <td height="38" align="center" bgcolor="#E5E5E5"><strong><%=organo.getIn_codigo_organo() %></strong></td>
                <td class="textCont"><%=organo.getVc_nombre()%></td>
                <td class="textCont"><%=organo.getVc_descripcion()%></td>
                 <td align="center" valign="middle">
                       <a href="javascript:;" id="cmdModificarOrg" title="Editar" onclick="javascript:fnl_llamarPopupModificar('<%=organo.getIn_codigo_organo()%>');">
                         <img src="fileproject/img/sistema/formularios/icoModi.gif" width="18" height="18" border="0" title="Editar"/>
                       </a>
                 </td>
                 <td align="center" valign="middle">
                          <%
                          if(organo.getCh_estado() != null && organo.getCh_estado().equals("01"))
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
                        <a id="a_ico_<%=organo.getIn_codigo_organo()%>" href="javascript:fnl_estadoOrgano('<%=Estado%>','<%=organo.getIn_codigo_organo()%>');" title="<%=iconoTitle%>">
                          <img id="img_ico_<%=organo.getIn_codigo_organo()%>" src="fileproject/img/sistema/formularios/<%=iconoEstado%>" width="18" height="18" border="0" title="<%=iconoTitle%>"/>
                        </a>
                  </td>
            </tr>
        <%}%>
    </table>
</div>
