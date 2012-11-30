<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orpro_oficio_circular" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsOficioCircular.js");
</script>
<%
 String Rol=(String)session.getAttribute("xrol");
 Rol=Rol.trim().toUpperCase();
%>
<%
   ArrayList<orpro_oficio_circular> oficios=(ArrayList)request.getAttribute("oficios");
   String validarBoton = request.getAttribute("validarBoton").toString().trim();
   String titulo = request.getAttribute("titulo").toString().trim();
%>

    <div  class="tituloPag" align="center" style="width:250px">
     <strong> <%=titulo%> </strong>
    </div>
<div id="DivGrillaResul">
    <div class="formBody" style="height:450px; overflow:scroll">
      <div>
       <br/><br/>
      </div>

    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
        <tr>
          <th width=8%" height="35" align="center" bgcolor="#E5E5E5" class="titu">ID</th>
          <th width="25%" bgcolor="#E5E5E5" class="titu">COD. OFICIO</th>
          <th width="22%" bgcolor="#E5E5E5" class="titu">USUARIO EMISOR</th>
          <th width="25%" bgcolor="#E5E5E5" class="titu">FECHA</th>
          <%
          if(validarBoton == "1" || validarBoton == "4" || validarBoton == "5" || validarBoton == "6")
          {
          %>
            <th width="25%" bgcolor="#E5E5E5" class="titu">OPERACION</th>
          <%
          }
          else if(validarBoton == "2"  || validarBoton == "3" )
          {
          %>
          <th width="25%" bgcolor="#E5E5E5" class="titu" colspan="2">OPERACION</th>
          <%}%>
        </tr>
        <%for (orpro_oficio_circular Lisoficios : oficios)
        {
        %>
        <tr>
            <td height="38" align="center" bgcolor="#E5E5E5"><strong><%=Lisoficios.getIn_codigo_oficio() %></strong></td>
            <td class="textCont" align="center">
                <a href="javascript:;" class="BotonStan2" title="Ver Oficio" onclick="javascript:fnl_ver_oficio('<%=Lisoficios.getIn_usuario_emisor() %>','<%=Lisoficios.getIn_codigo_oficio()%>',null);"> <%=Lisoficios.getCh_codigo_oficio() %> </a>
            </td>
            <td class="textCont" align="center"><%=Lisoficios.getNom_usuario() %></td>
            <td class="textCont" align="center"><%=Lisoficios.getDt_fecha() %></td>
            <%
              if(validarBoton == "1")
              {
           %>
            <td width="4%" align="center" valign="middle">
                  <a href="actualizarOficioCircular.uni?codOfc=<%=Lisoficios.getIn_codigo_oficio() %>" title="Editar">
                    <img src="fileproject/img/sistema/formularios/icoModi.gif" width="18" height="18" border="0" title="Editar"/>
                 </a>
            </td>
            <%} 
              else if(validarBoton == "2")
              {
              %>
              <td height="2%" align="center" >
                <a href="javascript:;" class="BotonStan2" title="Desea Aprobar el Oficio Circular?" onclick="fnl_aprobar_oficio_por_ocdo('<%=Lisoficios.getIn_codigo_oficio() %>');"> Aprobar </a>
              </td>
              <td width="2%" align="center" valign="middle">
                  <a href="javascript:fnl_llamarPopupObservacionesOficio('<%=Lisoficios.getIn_codigo_oficio() %>');" title="Comentar">
                    <img src="fileproject/img/sistema/formularios/icoObservaciones.gif" width="18" height="18" border="0" title="Comentar"/>
                 </a>
              </td>
              <%}
               else if(validarBoton == "3" )
                {%>
                  <td width="2%" align="center" valign="middle">
                   <a href="javascript:fnl_llamarPopupObservacionesOficio('<%=Lisoficios.getIn_codigo_oficio() %>');" title="Comentar">
                     <img src="fileproject/img/sistema/formularios/icoObservaciones.gif" width="18" height="18" border="0" title="Comentar"/>
                   </a>
                  </td>
                  <td width="4%" align="center" valign="middle">
                      <a href="actualizarOficioCircular.uni?codOfc=<%=Lisoficios.getIn_codigo_oficio() %>" title="Editar Oficio">
                        <img src="fileproject/img/sistema/formularios/icoModi.gif" width="18" height="18" border="0" title="Editar Oficio"/>
                     </a>
                </td>
               <%}
               else if(validarBoton == "4" )
                 {%>
                      <td width="2%" align="center" valign="middle">
                        <a href="javascript:;" class="BotonStan2" title="Desea Desaprobar el Oficio Circular?" onclick="fnl_desaprobar_oficio_por_ocdo('<%=Lisoficios.getIn_codigo_oficio() %>');"> Desaprobar </a>
                      </td>
                 <%}
               else if(validarBoton == "5" )
                 {%>
                      <td width="2%" align="center" valign="middle">
                        <a href="oficioCircularEnviarOficioDependencias.uni?codOficio=<%=Lisoficios.getIn_codigo_oficio() %>" class="BotonStan2"> Enviar </a>
                      </td>
                 <%}
                 else if(validarBoton == "6" )
                 {%>
                      <td width="2%" align="center" valign="middle">
                          <a href="javascript:fnl_ver_lista_observaciones_oficio('<%=Lisoficios.getIn_codigo_oficio() %>',2);" title="Historial de Observaciones">
                            <img src="fileproject/img/sistema/formularios/icoObservaciones.gif" width="18" height="18" border="0" title="Historial de Observaciones"/>
                         </a>
                      </td>
                 <%}%>
        </tr>
        <%}%>
    </table>
  </div>
</div>
