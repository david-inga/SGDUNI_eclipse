<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orpro_ta_versiones_estruc" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsEstructura_Organica.js");
</script>
    <%
    ArrayList<orpro_ta_versiones_estruc> listaVersionesEstructura =(ArrayList)request.getAttribute("listaVersionesEstructura");
    %>

    <div  class="tituloPag" align="center">
     <strong>Lista de<br/>Versiones de la <br/> Estructuras Organicas <br/> </strong>
    </div>

  <div id="DivGrillaResul" >
    <div class="formBody" style="height:250px;overflow:scroll">

    <table width="80%" border="0" align="left" cellpadding="2" cellspacing="2">
        <th width="5%" height="35" align="center" bgcolor="#E5E5E5" class="titu">VERSIÓN</th>
        <th width="10%" bgcolor="#E5E5E5" class="titu">FECHA</th>
        <th width="22%" colspan="2" bgcolor="#E5E5E5"  class="titu">OPERACIONES</th>
        <%for (orpro_ta_versiones_estruc version_estructura_organica : listaVersionesEstructura )
        {
        %>
            <tr>
                <td height="38" align="center" bgcolor="#E5E5E5"><strong><%=version_estructura_organica.getNum_version() %></strong></td>
                <td class="textCont" align="center"><%=version_estructura_organica.getDt_fecha() %></td>

                <td align="center" valign="middle">
                       <a href="documentos/EstructurasOrganicas/<%=version_estructura_organica.getVc_nombre_archivo()%>" target="_blank" title="Descargar versión">
                         <img src="fileproject/img/sistema/formularios/b_regi2.gif" width="18" height="18" border="0" title="Descargar versión"/>
                       </a>
                </td>
            </tr>
       <%}%>
    </table>
   <br/>
  </div>
</div>






