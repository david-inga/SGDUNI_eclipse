<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,com.sgduni.forms.orgen_ta_usuario_cargo" %>
<%@ page import="com.sgduni.forms.orgen_ta_usuario" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsCargoUsuario.js");
</script>
<div  class="tituloPag" align="center">
     <strong>
          Asignar <br/>
          Cargo a los Usuarios
     </strong>
</div>
<br/>
<div class="formBody" style="width:610px;">
<%
String error = (request.getParameter("error") != null) ? request.getParameter("error") : "";
%>
           <div class="textAviso">
               <%
               if(error!="" && error.equals("0"))
               {
                   out.print("Ocurrio un error al intentar registrar los datos");
               }
               else if(error!="" && error.equals("1"))
                {
                    out.print("Datos registrados correctamente");
                }
               %>
           </div>
    <form action="guardarUsuarioCargo.uni" method="POST" styleClass="formulariosA1" id="form_cargo_usuario">
        <%
         String coduser = String.valueOf(request.getAttribute("coduser"));
         int coduser_db =0;
         
         if(coduser != "null")
             coduser_db = Integer.parseInt(coduser);

         ArrayList<orgen_ta_usuario> usuarios = (ArrayList)request.getAttribute("nombreusuarios");
         ArrayList<orgen_ta_usuario_cargo> cargos = null;

         if( coduser_db != 0 )
         {
            cargos =(ArrayList)request.getAttribute("cargos");
         }
        %>
        <table border="0" align="center" cellpadding="2" cellspacing="2" aling="center">
            <tr>
                <td align="center">
                    <select id="in_codigo_usuario" name="in_codigo_usuario" onchange="javascript:document.location.href='SeleccionarCargoParaUsuarios.uni?coduser='+this.value;">
                       <%
                       out.println("<option value='0'>Seleccione Usuario</option>");

                       if(usuarios != null)
                       {
                            for(orgen_ta_usuario usuario : usuarios)
                            {
                               if(coduser_db!=0 && coduser_db==usuario.getIn_codigo_usuario()){
                                 out.println("<option value='"+usuario.getIn_codigo_usuario()+"' selected='selected'>"+usuario.getVc_usuario()+"</option>");
                               }else{
                                 out.println("<option value='"+usuario.getIn_codigo_usuario()+"'>"+usuario.getVc_usuario()+"</option>");
                               }
                            }
                       }
                       else
                       {
                           out.println("<option value='0'>No hay usuarios</option>");
                       }
                      %>
                   </select>
                </td>
            </tr>
           </table>
          <div id="DivGrillaResul" style="width:80%; padding:8px;">
           <table border="0" align="center" cellpadding="2" cellspacing="2" aling="center" style="padding:5px;">
           <tr>
             <td colspan="2" align="center" bgcolor="#E5E5E5" class="titu" style="padding:10px;">
               <strong>SELECCIONE EL CARGO A ASIGNAR</strong>
             </td>
             </tr>
             <%
                       if(cargos != null)
                       {
                            int i=0;
                            for(orgen_ta_usuario_cargo cargo : cargos)
                            {
                               i++;
                               out.println("<tr>");
                               out.println("<td class='textCont'>"+cargo.getVc_nombre_cargo()+"</td>");
                               if(cargo.getIn_codigo_usuario() != 0)
                               {
                                 out.println("<td class='textCont'><input value='"+cargo.getIn_codigo_cargo()+"' type='checkbox' checked='checked' name='in_codigo_cargo_"+i+"' id='in_codigo_cargo_"+i+"'/></td>");
                               }
                               else
                               {
                                 out.println("<td class='textCont'><input value='"+cargo.getIn_codigo_cargo()+"' type='checkbox' name='in_codigo_cargo_"+i+"' id='in_codigo_cargo_"+i+"'/></td>");
                               }
                               out.println("</tr>");
                            }
							out.println("<input value='"+i+"' type='hidden' name='txtnumitem_cargo' id='txtnumitem_cargo'/>");
                       }
                       else
                       {
                           out.println("<tr>");
                           out.println("<td class='txtSinDato' align='center'>No hay Cargos</td>");
                           out.println("<td></td>");
                           out.println("</tr>");
                       }
             %>
           </table>
</div>
       <div align="center">
              <a href="javascript:;" class="BotonStan2" style="text-decoration:none;" id="cmdGuarVD2"  onclick="fnl_guardarCargoUsuario();">Asignar Cargos</a>
              <a href="?" class="BotonStan1" style="text-decoration:none;" >Cancelar</a>
       </div>
    </form>
</div>