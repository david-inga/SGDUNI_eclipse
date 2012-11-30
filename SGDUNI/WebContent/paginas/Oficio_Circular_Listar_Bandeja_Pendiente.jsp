<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orpro_oficio_circular" %>
<%@ page import="com.sgduni.utilitarios.ContarCaracteresVista" %>
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
       ArrayList<orpro_oficio_circular> ListaOficios = (ArrayList)request.getAttribute("oficios");
       String validar_campo_tiempo = request.getAttribute("validar_campo_tiempo").toString();
    %>
    <div  class="tituloPag" align="center">
     <strong >Lista de Oficios  Por Revisar</strong>
    </div>


  <div id="DivGrillaResul">
   <div class="formBody" style="height:450px; overflow:scroll">
    <br/>
    <br/>
    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
       <tr>
          <th width="20%" height="35" align="center" bgcolor="#E5E5E5" class="titu">COD. OFICIO</th>
          <th width="18%" bgcolor="#E5E5E5" class="titu">DEPENDENCIA Y/O FACULTADES</th>
              <th width="12%" bgcolor="#E5E5E5" class="titu">PARA</th>
          <th width="9%" bgcolor="#E5E5E5" class="titu">FECHA</th>
          <%if( validar_campo_tiempo == "1" )
          {%>
          <th width="23%" bgcolor="#E5E5E5" class="titu" colspan="2">
          <strong class="txtStyleAd4Err" title="Mensaje: Si llega a los 10 días ya no podra hacer su tramite"> Días Transcurridos </strong>
          </th>
          <%}%>
       </tr>
        <%
        if(ListaOficios != null)
        {
          for (orpro_oficio_circular oficio : ListaOficios)
          {
        %>
        <tr>
           <td height="38" align="center" bgcolor="#E5E5E5">
                <a href="ExportarOficioPDF.uni?idOficio=<%=oficio.getIn_codigo_oficio()%>&in_cod_fac_Dep=<%=oficio.getIn_cod_fac_dep()%>&ch_tipo_fac_dep=<%=oficio.getCh_tipo_fac_dep()%>" class="BotonStan2" title="Ver Oficio" target="_blank" >
                    <%=oficio.getCh_codigo_oficio() %>
                </a>
           </td>
           <td class="textCont" title="<%=oficio.getNombreDependFacu()%>">
                     <%
                      ContarCaracteresVista texVis=new ContarCaracteresVista( oficio.getNombreDependFacu() );
                      out.print(" "+texVis.getTexMax(20, null));
                     %>
           </td>
           <td class="textCont"><%=oficio.getNom_usuario() %></td>
           <td class="textCont"><%=oficio.getDt_fecha() %></td>
           <%if( validar_campo_tiempo == "1" )
           {%>
           <td align="center" class="textCont">                   
                    <strong class="txtStyleAd4Err">                        
                        <%
                          if(oficio.getNumDiasTranscurridos() > 10)
                          {//Número de Dias VENCIDO
                            out.println("OFICIO VENCIDO");
                          }
                          else
                          {
                            out.println( oficio.getNumDiasTranscurridos() );
                          }
                        %>
                    </strong>
            </td>
           
            <td width="4%" align="center" valign="middle">
                <%
                if(Rol.equals("ROL02") || Rol.equals("ROL03"))
                 {//Visualizacion de la opcion iniciar tramite (SOLO PARA OCDO Y USUARIO)
                      if(Rol.equals("ROL02") && oficio.getNumDiasTranscurridos() > 10)
                      {
                      }
                      else
                      {
                        %>
                           <a href="oficioCircularNuevo.uni?in_cod_oficio=<%=oficio.getIn_codigo_oficio() %>" title="Es necesario responder el Oficio Circular">
                             <img src="fileproject/img/sistema/formularios/responderOficio.png" width="20" height="20" border="0" title="Es necesario responder el Oficio Circular"/>
                           </a>
                        <%
                        }
                 }
                %>
             </td>

             <%}%>
            </tr>
       <%}
    }
    %>
    </table>
  </div>
</div>