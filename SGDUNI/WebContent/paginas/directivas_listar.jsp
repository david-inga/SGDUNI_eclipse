<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orpro_ta_directivas" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsDirectivas.js");
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<% ArrayList<orpro_ta_directivas> directivas =(ArrayList)request.getAttribute("directivas"); %>
<%

 String CodRol=session.getAttribute("xrol").toString();
 String tipoDepenFacu=session.getAttribute("xtipodepen_facul").toString();
 String idDepenFacu=session.getAttribute("xiddepen_facul").toString();

 //out.println("COIGO DE ROL DEL USUARIO = "+CodRol);
 //out.println("tipo usuario = "+tipoDepenFacu);
 //out.println("id fac o dep = "+idDepenFacu);
%>

    <div  class="tituloPag" align="center">
     <strong>Lista de Directivas</strong>
    </div>

<div id="localizador"><a href="menu.uni">Inicio</a> > Directivas > <a href="llamarFormInsertDirectiva.uni"> Nuevo </a></div>


  <div id="DivGrillaResul">
    <div class="formBody" style="height:250px; overflow:scroll">
      <div>
       <br/><br/>
      </div>

    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
        <th width="13%" height="35" align="center" bgcolor="#E5E5E5" class="titu">CODIGO</th>
        <th width="11%" bgcolor="#E5E5E5" class="titu">FECHA</th>
        <th width="19%" bgcolor="#E5E5E5" class="titu">ALCANCE</th>
        <th width="28%" bgcolor="#E5E5E5" class="titu">RESPONSABILIDAD</th>
        <th bgcolor="#E5E5E5" class="titu" colspan="5" >VER DETALLES</th>

        <%
        for (orpro_ta_directivas directiva : directivas)
        {
            //int inicio = directiva.getDt_fecha().indexOf(" ");
            //String fecha = directiva.getDt_fecha().substring(0, inicio);
        %>
        <tr>
            <td height="38" align="center" bgcolor="#E5E5E5"><strong><%=directiva.getCh_codigo_directiva() %></strong></td>
            <td class="textCont"><%=directiva.getDt_fecha() %></td>
            <td class="textCont"><%=directiva.getVc_alcance() %></td>
            <td class="textCont"><%=directiva.getVc_responsabilidad() %></td>
            <td width="5%" align="center" valign="middle">
               <a href="directivasExportar.uni?iddire=<%=directiva.getIn_codigo_directiva()%>&proc=word" title="Exportar a Word" target="_blank">
                 <img src="fileproject/img/iconos_archivos/icoword.gif" width="16" height="16" border="0" alt="ico_word"/>
               </a>
            </td>
            <td width="6%" align="center" valign="middle">
				<a href="javascript:;" title="Ver Objetivos" onclick="javascript:fnl_llamarPopupVerObjetivos('<%=directiva.getIn_codigo_directiva() %>');">
                	<img src="fileproject/img/sistema/formularios/icon_man_2.gif" border="0"/>
              	</a>            
            </td>
            <td width="6%" align="center" valign="middle">
                <a href="javascript:;" title="Ver Normas Generales" onclick="javascript:fnl_llamarPopupVerNormasGen('<%=directiva.getIn_codigo_directiva() %>');">
                <img src="fileproject/img/sistema/formularios/icon_man_3.gif" border="0"/>
              </a>
            </td>
            <td width="6%" align="center" valign="middle">
                <a href="javascript:;" title="Ver Bases Legales" onclick="javascript:fnl_llamarPopupVerBaseLegal('<%=directiva.getIn_codigo_directiva() %>');">
                <img src="fileproject/img/sistema/formularios/icon_man_1.gif" border="0"/>
              </a>
           </td>
           <td width="6%" align="center" valign="middle">
                <a href="javascript:;" title="Ver Procedimientos" onclick="javascript:fnl_llamarPopupVerProcedimiento('<%=directiva.getIn_codigo_directiva() %>');">
                <img src="fileproject/img/sistema/formularios/icon_man_2.gif" border="0"/>
              </a>
           </td>
        </tr>
        <%
        }
        %>
    </table>
  </div>
</div>
