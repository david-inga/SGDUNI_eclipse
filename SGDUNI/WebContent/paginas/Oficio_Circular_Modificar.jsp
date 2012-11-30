<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ page import="com.sgduni.forms.orpro_oficio_circular" %>
<%@page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_usuario" %>
<%
String Rol=session.getAttribute("xrol").toString().trim().toUpperCase();
String cargo = session.getAttribute("xcodcargo").toString().trim();
%>
    <%
    orpro_oficio_circular oficio = (orpro_oficio_circular)request.getAttribute("oficio");
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
     <strong>Modificar Oficio Circular</strong>
    </div>
       <html:form action="soloModificarOficioCircular.uni" method="POST" styleClass="formulariosA1" styleId="frmModificarOficio">
         <div class="formBody" style="width:800px; margin:9px auto;">
           <div class="textAviso">
                <bean:write name="orpro_oficio_circular" property="mensaje"/>
            </div>
             <fieldset style="border:none">
                    <html:hidden property="in_codigo_oficio" styleId="in_codigo_oficio" value="${oficio.in_codigo_oficio}"></html:hidden>
                    <table width="792" align="center">
                         <tr>
                               <td width="95" >
                                 <label style="width:98%">Codigo Oficio :</label>
                               </td>
                               <td width="114" colspan="3">
                                   <html:text property="ch_codigo_oficio" styleId="ch_codigo_oficio"  size="15" style="width:110%;" value="${oficio.ch_codigo_oficio}" disabled="true"></html:text>
                               </td>
                               <td>
                                    <html:hidden property="ch_codigo_oficio" styleId="ch_codigo_oficio" value="${oficio.ch_codigo_oficio}"></html:hidden>
                               </td>
                               <td></td>
                               <td></td>
                         </tr>
                         <tr>
                             <td width="49" align="center">
                                <label style="width:98%">Fecha :</label>
                             </td>
                             <td width="96">
                                 <html:text property="dt_fecha" readonly="" styleId="dt_fecha" size="16" style="width:85%" value="${oficio.dt_fecha}"></html:text>
                             </td>
                             <td width="20" align="left" valign="middle">
                                 <img onclick="displayCalendar(document.forms[0].dt_fecha,'yyyy-mm-dd',this)" src="fileproject/img/sistema/formularios/ico_calendario.gif" width="16" height="16" style="cursor:pointer" />
                             </td>
                             <td width="96"></td>
                             <td width="90" align="center">
                                <label style="width:90%">Ciudad:</label>
                             </td>
                             <td>
                                 <html:text property="vc_ciudad" styleId="vc_ciudad" style="width:50%;" value="${oficio.vc_ciudad}"></html:text>
                             </td>
                             <td></td>
                         </tr>
                         <tr>
                             <td align="left">
                                 <label style="width:150%" >Nombre del Año Actual: </label>
                             </td>
                             <td colspan="6" align="center">
                                 <html:text property="vc_nombre_anio" styleId="vc_nombre_anio" style="width:80%;" value="${oficio.vc_nombre_anio}" ></html:text>
                             </td>
                         </tr>
                         <tr>
                             <td colspan="7">
                                 <label style="width:30%" >Cuerpo del Oficio Circular: </label>
                             </td>
                         </tr>
                         <tr>
                             <td colspan="7">
                               <html:textarea styleId="vc_cuerpo_doc" property="vc_cuerpo_doc" rows="15" cols="80" style="width: 100%" value="${oficio.vc_cuerpo_doc}"></html:textarea>
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
                                      int codEmisor = 0;
                                      if(oficio == null)
                                      {
                                         codEmisor = 0;
                                      }
                                      else
                                      {
                                         codEmisor = oficio.getIn_usuario_emisor();
                                      }
                                   if(usuariosOCDO != null)
                                   {
                                        out.println("<option value='0'>Seleccione Emisor</option>");

                                        for(orgen_ta_usuario usuarioOCDO : usuariosOCDO)
                                        {
                                            //selected="selected" -> es para que se seleccione
                                            if(codEmisor != 0  && codEmisor == usuarioOCDO.getIn_codigo_usuario() )
                                            {
                                              out.println("<option value='"+usuarioOCDO.getIn_codigo_usuario()+"' selected='selected'>"+usuarioOCDO.getVc_usuario().trim()+"</option>");
                                            }
                                            else
                                            {
                                              out.println("<option value='"+usuarioOCDO.getIn_codigo_usuario()+"'>"+usuarioOCDO.getVc_usuario().trim()+"</option>");
                                            }
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
                      <!-- <a href="javascript:;" id="cmdModiUsuario" onclick="javascript:fnl_ModificarOficioC();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Modificar y Siguiente</a> -->
                      <%
                        if( Rol.equals("ROL01") )
                        {
                          if( cargo.equals("1") )
                          {
                           %>
                           <a href="javascript:;" id="cmdBorrador" onclick="javascript:fnl_soloModificarOficio();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Guardar en Borrador</a>
                           <a href="javascript:;" id="cmdAprobarJefaOCDO" onclick="javascript:fnl_ModificarAprobarOficio();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Modificar y Aprobar</a>
                           <%
                          }
                          else if(cargo.equals("2"))
                          {
                           %>
                           <a href="javascript:;" id="cmdBorrador" onclick="javascript:fnl_soloModificarOficio();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Guardar en Borrador</a>
                           <a href="javascript:;" id="cmdEnviarJefaOCDO" onclick="javascript:fnl_ModificarOficioyEnviaJefeOCDO();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Enviar al jefe(a) OCDO</a>
                           <!-- <a href="javascript:;" id="cmdModiUsuario" onclick="javascript:fnl_guardarOficioC();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Guardar y Siguiente</a> -->
                           <%
                          }
                        }
                      else if( Rol.equals("ROL02") || Rol.equals("ROL03")  )
                        {%>
                         <a href="javascript:;" id="cmdBorrador" onclick="javascript:fnl_soloModificarOficio();" class="BotonStan2 paddingCmd" style="text-decoration:none;">Guardar en Borrador</a>
                         <a href="javascript:;" id="cmdEnviarRespuesta" onclick="javascript:alert('en construccion');" class="BotonStan2 paddingCmd" style="text-decoration:none;">Enviar Respuesta</a>
                      <%}%>
                    </div>
             </fieldset>
                   <div id="dvl_enviar_of_c" style="display:none;">
                   </div>
        </div>
    </html:form>
