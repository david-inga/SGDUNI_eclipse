<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sgduni.forms.orgen_ta_dependencia" %>
<%@ page import="com.sgduni.forms.orgen_ta_facultad" %>
<%@ page import="com.sgduni.utilitarios.ContarCaracteresVista" %>
<%@ page import="com.sgduni.forms.orgen_ta_usuario_lista" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
   fng_loadJs("fileproject/js/formularios/jsUsuarioCargoEstructural.js");
</script>

<div  class="tituloPag" align="center" style="width:350px;">
     <strong>
          Asignar Entidad<br/>
          a los Usuarios</strong>
</div>
<br/>
        <%
        int IdDepenFacu=Integer.parseInt(request.getAttribute("DepenFacuId").toString());
        String tipDepenFacu=request.getAttribute("DepenFacuTip").toString();
         //Cod Usuario
        int codUsu=Integer.parseInt(request.getAttribute("codUsu").toString());
        String error=(request.getParameter("err")!=null)?request.getParameter("err"):"";         
         ArrayList<orgen_ta_usuario_lista> usuarios =(ArrayList)request.getAttribute("usuarios");;
        %>
		<%ArrayList<orgen_ta_dependencia> dependencias=(ArrayList)request.getAttribute("dependencias");%>
		<%ArrayList<orgen_ta_facultad> facultad=(ArrayList)request.getAttribute("facultad");%>        
<div class="formBody" style="width:610px;">
     <div class="textAviso">
         <%
         if(error!="" && error.equals("0")){
              out.println("Datos guardados correctamente");
          }else if(error!=""){
             out.println("Error al intentar guardar los datos");
          }
         %>
     </div>
    <form action="seleccionarEntidadParaUsuarioGuardar.uni" method="POST" styleClass="formulariosA1" id="formUsuarioCargoEstructural">
        <table width="334" border="0" align="center" cellpadding="2" cellspacing="2" aling="center">
            <tr>
                <td height="31" colspan="2" align="center" bgcolor="#EFEFEF">
                    <center><font color="696969">Seleccione un Usuario:</font></center>
                </td>
            </tr>
            <tr>
                <td height="38" colspan="2" align="center">
                    <select id="in_codigo_usuario" name="in_codigo_usuario" onchange="javascript:document.location.href='seleccionarEntidadParaUsuario.uni?coduser='+this.value;">
                       <%
                       out.println("<option value='0'>Seleccione Usuarios</option>");
                       if(usuarios != null)
                       {
                            for(orgen_ta_usuario_lista usu : usuarios)
                            {
                               if(codUsu!=0 && codUsu==usu.getIn_codigo_usuario())
                               {
                                   out.println("<option value='"+usu.getIn_codigo_usuario()+"' selected title='"+usu.getVc_nombres()+" "+usu.getVc_apellido_paterno()+" "+usu.getVc_apellido_materno()+"'>"+usu.getVc_usuario()+"</option>");
                               }else{
                                   out.println("<option value='"+usu.getIn_codigo_usuario()+"' title='"+usu.getVc_nombres()+" "+usu.getVc_apellido_paterno()+" "+usu.getVc_apellido_materno()+"'>"+usu.getVc_usuario()+"</option>");
                               }

                            }
                       }
                       else
                       {
                           out.println("<option value='0'>No hay documentos</option>");
                       }
                      %>
                   </select>
					<input type="hidden" name="txtidfacdep" id="txtidfacdep" value="<%=IdDepenFacu%>"/>
                </td>
            </tr>
            <tr>
                <td height="31" colspan="2" align="center" bgcolor="#EFEFEF">
                    <center>
                      <font color="696969">Seleccione la Facultad o Dependencia</font>
                    </center>
                </td>
            </tr>
            <tr>
              <td width="156" height="52" align="right" bgcolor="#F9F9F9" class="txtStyleAd2">
                 <center> <strong>Facultad</strong> : </center>
                 <center> <input type="radio" name="rdtipofacdep" id="rdtipofacdep" <% if(tipDepenFacu.trim().equals("f")){out.print("checked='true'");}%> value="f" onchange="javascript:fnl_tip_select(this.value);"/> </center>
              </td>
              <td width="164" align="left" bgcolor="#F9F9F9" class="txtStyleAd2" style="padding-left:10px;">
                <center> <strong>Dependencia</strong> : </center>
                 <center>  <input type="radio" name="rdtipofacdep" id="rdtipofacdep" <% if(tipDepenFacu.trim().equals("d")){out.print("checked='true'");}%> value="d" onchange="javascript:fnl_tip_select(this.value);"/> </center>
              </td>
            </tr>
            <tr>
              <td height="63" colspan="2" align="center" valign="middle" bgcolor="#EFEFEF">                 
                 <select name="lstDependencia" id="lstDependencia" style="<% if(tipDepenFacu.trim().equals("d")){out.print("display:block;");}else{out.print("display:none;");}%>" onchange="javascript:fnl_In_Depend_Fac(this.value);">
                         <option value="0"> - Seleccione el Dependencia -</option>
                         <%
                              for(orgen_ta_dependencia lisDep:dependencias)
                              {
                                  ContarCaracteresVista texShow=new ContarCaracteresVista(lisDep.getVc_nombre());
                         %>
                           <option value="<%=lisDep.getIn_codigo_dependencia()%>" <% if(IdDepenFacu==lisDep.getIn_codigo_dependencia()){%>selected<%}%> title="<%=lisDep.getVc_nombre()%>"><%=texShow.getTexMax(25,null)%></option>
                         <%
                              }

                         %>
                      </select>
                      <select name="lstFacultad" id="lstFacultad" style="<% if(tipDepenFacu.trim().equals("f")){out.print("display:block;");}else{out.print("display:none;");}%>" onchange="javascript:fnl_In_Depend_Fac(this.value);">
                         <option value="0"> - Seleccione la Facultad -</option>
                         <%
                              for(orgen_ta_facultad lisFac:facultad)
                              {
                                  ContarCaracteresVista texShow=new ContarCaracteresVista(lisFac.getVc_nombre());
                         %>
                           <option value="<%=lisFac.getIn_codigo_facultad()%>" <% if(IdDepenFacu==lisFac.getIn_codigo_facultad()){%>selected<%}%> title="<%=lisFac.getVc_nombre()%>"><%=texShow.getTexMax(25,null)%></option>
                         <%
                              }
                         %>
                      </select>  
              </td>
            </tr>
           </table>
       <div align="center">
              <a href="javascript:;" class="BotonStan2" style="text-decoration:none;" id="cmdGuarVD2"  onclick="fnl_guardarUsuarioCargoEstructural();">Asignar Facultad o Dependencia</a>
              <a href="?" class="BotonStan1" style="text-decoration:none;" >Cancelar</a>
       </div>
    </form>
</div>