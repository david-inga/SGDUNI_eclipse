<%-- 
    Document   : Estructura_Organica_Revisar
    Created on : 18/05/2012, 11:44:51 AM
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList,com.sgduni.forms.orpro_ta_observacion_estruc" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsEstructura_Organica.js");
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vista - Estructura Organica</title>
    </head>
    <body>
        <%
          
          int idUserSes = Integer.parseInt(session.getAttribute("xid").toString());
          ArrayList<orpro_ta_observacion_estruc> lisobservaciones =(ArrayList)request.getAttribute("listaObservaciones");

          String in_codigo_estructura = request.getAttribute("in_codigo_estructura").toString();
          String ch_codigo_estructura = request.getAttribute("ch_codigo_estructura").toString();
          String organigrama = request.getAttribute("organigrama").toString();

        %>
       <div  class="tituloPag" align="center" style="width:300px;">
         <strong> VISTA <BR/> Estructura Organica: <%=ch_codigo_estructura%></strong>
       </div>
       <BR/>
       <div id="localizador"> <a href="organigramaListar.uni" >Estructuras Organicas Pendientes</a> > Vista </div>
       <BR/>
        <table width="75%" border="0" align="center" cellpadding="2" cellspacing="2">
           <th width="27%" align="center"  class="titu"></th>
            <tr>
                <td height="38" align="center" bgcolor="#E5E5E5">
                  <center>
                    <img src="documentos/EstructurasOrganicas/<%=organigrama%>" width="595.2756" height="841.8898" border="0"/>
                  </center>
                </td>
             </tr>
              <tr>
                <td class="textCont"  valign="top" >
                    <br/>
                   <strong> <font color="808080">OBSERVACIONES</font> </strong>
                    <br/>
                  <div class="barra_menu_grilla" style="float:left;">
	             	<a href="javascript:;" class="BotonStan2" style="text-decoration:none;" onclick="fnl_llamarPopupGuardarObservaciones('<%=in_codigo_estructura%>');" >Nueva Observación</a>
                    <a href="javascript:;" class="BotonStan2" style="text-decoration:none;" onclick="fnl_Refrescar('<%=in_codigo_estructura%>');" >Refrescar</a>
	              </div>
                   <br/>
                  <div id="list_Obs" class="formBody" style="max-height:700px;overflow:auto; width:500px;">
                          <%
                          String cssObj = null;//Tipo de CSS para los comentarios

                          for(orpro_ta_observacion_estruc observacion : lisobservaciones)
                          {
                              cssObj="2";
                              if(observacion.getIn_codigo_usuario() == idUserSes )
                                  cssObj="1";
                          %>
                          <div class="FondoComentario<%=cssObj%>">
                             <div align="left" class="txtStyleAd1" style="margin-bottom:3px;">
                                <strong><%=observacion.getVc_nombre_usuario()%></strong> Dice :
                             </div>
                             <p class="txtStyleAd2">
                                <%=observacion.getVc_observacion()%>
                             </p>
                             <div align="right" class="txtStyleAd3">
                                 <%=observacion.getDt_fecha_crea()%>
                              </div>
                              <span class="imgIcoL<%=cssObj%>"></span>
                          </div>
                          <%
                          }
                          %>
                    </div>
                </td>
            </tr>
       </table>
        
    </body>
</html>
