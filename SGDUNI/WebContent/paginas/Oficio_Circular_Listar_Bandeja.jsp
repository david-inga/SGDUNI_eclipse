<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orpro_oficio_circular" %>
<%@ page import="com.sgduni.utilitarios.ContarCaracteresVista" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsOficioCircular.js");
</script>
    <%ArrayList<orpro_oficio_circular> ListaOficios = (ArrayList)request.getAttribute("oficios");%>
    <div  class="tituloPag" align="center">
     <strong>Historial de Oficio Circulares Tramitados</strong>
    </div>

  <div id="DivGrillaResul">
    <div class="formBody" style="height:450px; overflow:scroll">
       <br/>
       <br/>

    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
        <tr>
          <th width="4%" height="35" align="center" bgcolor="#E5E5E5" class="titu">COD. OFICIO</th>
        <th width="25%" bgcolor="#E5E5E5" class="titu">CODIGO OFICIO</th>
        <th width="22%" bgcolor="#E5E5E5" class="titu">USUARIO EMISOR</th>
        <th width="22%" bgcolor="#E5E5E5" class="titu">USUARIO RECEPTOR</th>
        <th width="11%" bgcolor="#E5E5E5" class="titu">FECHA</th>
        <th width="30%" bgcolor="#E5E5E5" class="titu">ESTADO</th>
        <%        
        for (orpro_oficio_circular oficio : ListaOficios)
        {
        %>
            <tr>
                <td height="38" align="center" bgcolor="#E5E5E5"><strong><%=oficio.getIn_codigo_oficio() %></strong></td>
                <td height="38" align="center" bgcolor="#E5E5E5">
                    <a href="ExportarOficioPDF.uni?idOficio=<%=oficio.getIn_codigo_oficio()%>&in_cod_fac_Dep=<%=oficio.getIn_cod_fac_dep()%>&ch_tipo_fac_dep=<%=oficio.getCh_tipo_fac_dep()%>" class="BotonStan2" title="Ver Oficio" target="_blank" >
                      <%=oficio.getCh_codigo_oficio() %>
                    </a>
                </td>
                <td class="textCont" > <%=oficio.getNombre_usuario_emisor() %> </td>
                <td class="textCont" > <%=oficio.getNombre_usuario_receptor() %> </td>
                <td class="textCont" > <%=oficio.getDt_fecha() %> </td>
                <td class="textCont" > <%=oficio.getCh_estado() %> </td>
            </tr>
        <%}%>
    </table>
  </div>
</div>