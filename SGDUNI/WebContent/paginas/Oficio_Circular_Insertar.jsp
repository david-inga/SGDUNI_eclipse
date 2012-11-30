<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ page import="com.sgduni.forms.orpro_oficio_circular" %>
<%@ page import="com.sgduni.utilitarios.ValidarCadena" %>
<%@page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_usuario" %>
<%
String Rol=session.getAttribute("xrol").toString().trim().toUpperCase();
String cargo = session.getAttribute("xcodcargo").toString().trim();
//String in_codigo_oficio_en_tramite = request.getAttribute("in_codigo_oficio_en_tramite").toString();
String in_codigo_oficio_en_tramite = ( request.getAttribute("in_codigo_oficio_en_tramite")!=null && ValidarCadena.isNumeric(request.getAttribute("in_codigo_oficio_en_tramite").toString() ) == true   ) ? request.getAttribute("in_codigo_oficio_en_tramite").toString() : "0";
%>
    <%
    //String nuevo_codigo_generado = request.getAttribute("nuevo_codigo_generado").toString().trim();
    ArrayList<orgen_ta_usuario> usuariosOCDO = (ArrayList)request.getAttribute("usuariosOCDO");
    %>
    <script type="text/javascript" src="fileproject/editor_html/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
    <script language="javascript" type="text/javascript" >
       fng_loadJs("fileproject/js/formularios/jsOficioCircular.js");
    </script>

    <link type="text/css" rel="stylesheet" href="fileproject/css/calendarioSelct/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
    <script type="text/javascript" src="fileproject/js/dhtmlgoodies_calendar.js?random=20060118"></script>
    <script type="text/javascript">
        tinyMCE.init({
            // General options
            mode : "textareas",
            theme : "advanced",
            skin : "o2k7",
            //		skin_variant : "silver",
            plugins : "autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,inlinepopups,autosave",
            theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleselect,formatselect,fontselect,fontsizeselect",
            theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
            theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
            theme_advanced_toolbar_location : "top",
            theme_advanced_resizing : true,
            template_replace_values : {
            username : "Some User",
            staffid : "991234"
            }
        });
    </script>
    <div  class="tituloPag" align="center">
     <strong>Crear Nuevo Oficio Circular</strong>
    </div>
    <%
      String descripDocument=
    "<p style=\"text-align: left;\"><span style=\"color: #ff0000; font-size: medium; font-family: arial, helvetica, sans-serif;\"><span style=\"color: #000000;\"><strong></strong>De mi consideraci&oacute;n:</span></span></p>"+
    "<p style=\"text-align: left;\"><span style=\"color: #ff0000; font-size: medium; font-family: arial, helvetica, sans-serif;\"><span style=\"color: #000000;\"><br /></span></span></p>"+
    "<p style=\"text-align: left;\"><span style=\"color: #ff0000; font-size: medium; font-family: arial, helvetica, sans-serif;\"><span style=\"color: #000000;\">Hago propicia la ocasi&oacute;n para saludarlo y adjuntar el \"Manual de Organizaci&oacute;n y Funciones de la Oficina Central de Admisi&oacute;n\", coordinado en reunines de trabajo desarrolladas en la OCDO as&iacute; como en la OCAD.</span></span></p>"+
    "<p style=\"text-align: left;\"><span style=\"color: #ff0000; font-size: medium; font-family: arial, helvetica, sans-serif;\"><span style=\"color: #000000;\">Se sugiere dar tr&aacute;mite a la Resoluci&oacute;n Rectoral. respectiva para su aprobaci&oacute;n.</span></span></p>"+
    "<p style=\"text-align: left;\"><span style=\"color: #ff0000; font-size: medium; font-family: arial, helvetica, sans-serif;\"><span style=\"color: #000000;\">Sin otro particular, quedo de usted.</span></span></p>";
    %>
       <html:form action="soloGuardarOficioCircular.uni" method="POST" styleClass="formulariosA1" styleId="frmEditarOficio">
         <div class="formBody" style="width:800px; margin:9px auto;">
           <div class="textAviso">
                       <bean:write name="orpro_oficio_circular" property="mensaje"/>
            </div>
             <fieldset style="border:none">
                    <table width="792" align="center">
                        <tr>
                               <td></td>
                               <td colspan="5"><strong><font size="2" > <font color="808080">Formato : OFICIO-N-</font><font color="#831123">[NUMERO]</font><font color="808080">-</font><font color="#831123">[AÑO]</font><font color="808080">-</font><font color="#831123">[MES]</font> - <font color="808080">Ejemplo : OFICIO-N-</font><font color="#831123">168</font><font color="808080">-</font><font color="#831123">2012</font><font color="808080">-</font><font color="#831123">06</font></font></strong></td>
                         </tr>
                         <tr>
                               <td width="95" >
                                 <label style="width:98%">Codigo Oficio :</label>
                               </td>
                               <td width="114" colspan="3">
                                   <html:text property="ch_codigo_oficio" styleId="ch_codigo_oficio"  size="15" style="width:110%;" ></html:text>
                               </td>
                               <td>
                                   <html:hidden property="in_codigo_oficio_en_tramite" styleId="in_codigo_oficio_en_tramite"  value="<%=in_codigo_oficio_en_tramite%>" ></html:hidden>
                               </td>
                               <td></td>
                               <td></td>
                         </tr>
                         <tr>
                             <td width="95" align="center">
                                <label style="width:98%">Fecha :</label>
                             </td>
                             <td width="114">
                                <html:text property="dt_fecha" readonly="" styleId="dt_fecha" size="16" style="width:85%"></html:text>
                             </td>
                             <td width="20" align="left" valign="middle">
                                 <img onclick="displayCalendar(document.forms[0].dt_fecha,'yyyy-mm-dd',this)" src="fileproject/img/sistema/formularios/ico_calendario.gif" width="16" height="16" style="cursor:pointer" />
                             </td>
                             <td width="96"></td>
                             <td width="90" align="center">
                                <label style="width:90%">Ciudad:</label>
                             </td>
                             <td>
                                 <html:text property="vc_ciudad" styleId="vc_ciudad" style="width:50%;" value="Lima" ></html:text>
                             </td>
                             <td></td>
                         </tr>
                         <tr>
                             <td align="left">
                                 <label style="width:150%" >Nombre del Año Actual: </label>
                             </td>
                             <td colspan="6" align="center">
                                 <html:text property="vc_nombre_anio" styleId="vc_nombre_anio" style="width:80%;" ></html:text>
                             </td>
                         </tr>
                         <tr>
                             <td colspan="7">
                                 <label style="width:30%" >Cuerpo del Oficio Circular: </label>
                             </td>
                         </tr>
                         <tr>
                             <td colspan="7">
                               <html:textarea styleId="vc_cuerpo_doc" property="vc_cuerpo_doc" rows="15" cols="80" style="width: 100%" value="<%=descripDocument%>"></html:textarea>
                             </td>
                         </tr>
                         <br/>
                         <tr>
                           <td>
                               <label style="width:5%" >De: </label>
                           </td>
                           <td>
                               <select id="in_usuario_emisor" name="in_usuario_emisor">
                               <%

                                   if(usuariosOCDO != null)
                                   {
                                        out.println("<option value='0'>Seleccione Emisor</option>");
                                        for(orgen_ta_usuario usuarioOCDO : usuariosOCDO)
                                        {
                                         out.println("<option value='"+usuarioOCDO.getIn_codigo_usuario()+"'>"+usuarioOCDO.getVc_usuario().trim()+"</option>");
                                        }
                                   }
                                   else
                                   {
                                       out.println("<option value='0'>No hay Usuarios OCDO</option>");
                                   }
                                %>
                              </select>
                         </td>
                         </tr>
                    </table>
                    <div align="center" style="padding:5px;">
                        <%
                        if( Rol.equals("ROL01") )
                        {
                          if( cargo.equals("1") )
                          {//los botones de la jefa ocdo
                           %>
                           <a href="javascript:;" id="cmdBorrador" onclick="javascript:fnl_soloGuardarOficio();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Guardar en Borrador</a>
                           <a href="javascript:;" id="cmdAprobarOCDO" onclick="javascript:fnl_guardaryAprobarPorLaOCDO();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Guardar y Aprobar</a>
                           <%
                          }
                          else if(cargo.equals("2"))
                          {//los botones de la secretaria
                           %>
                           <a href="javascript:;" id="cmdBorrador" onclick="javascript:fnl_soloGuardarOficio();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Guardar en Borrador</a>
                           <a href="javascript:;" id="cmdEnviarJefaOCDO" onclick="javascript:fnl_enviarOficioalJefeOCDO();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Enviar al jefe(a) OCDO</a>
                           <!-- <a href="javascript:;" id="cmdModiUsuario" onclick="javascript:fnl_guardarOficioC();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Guardar y Siguiente</a> -->
                           <%
                          }
                        }
                      else if( Rol.equals("ROL02") || Rol.equals("ROL03")  )
                        {////los botones de los usuarios
                          %>
                         <!-- <a href="javascript:;" id="cmdBorrador" onclick="javascript:fnl_soloGuardarOficio();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Guardar en Borrador</a>  -->
                         <a href="javascript:;" id="cmdEnviarRespuesta" onclick="javascript:fnl_guardaryAprobarPorLaOCDO();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Enviar Respuesta</a>
                      <%}%>
                   </div>
             </fieldset>
                   <div id="dvl_enviar_of_c" style="display:none;">
                   </div>
        </div>
    </html:form>