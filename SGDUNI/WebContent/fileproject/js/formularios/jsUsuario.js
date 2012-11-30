/***********************
FORMULARIO NUEVA ACTIVIDAD
************************/
 var usuarioDisponible=0;

   function formUsuario()
   {
       this.vc_usuario=document.getElementById("vc_usuario");	   
       this.vc_clave=document.getElementById("vc_clave");
	   this.vc_clave_dep=document.getElementById("vc_clave_dep");
	   this.vc_correo=document.getElementById("vc_correo");
	   this.vc_nombres=document.getElementById("vc_nombres");
	   this.vc_apellido_paterno=document.getElementById("vc_apellido_paterno");
	   this.vc_apellido_materno=document.getElementById("vc_apellido_materno");
       this.vc_nombre_archivo=document.getElementById("vc_nombre_archivo");
       this.vc_grado_academico=document.getElementById("vc_grado_academico");
   }

   function fnl_modiUsuarioFirma()
   {
       if(document.getElementById('vc_nombre_archivo').value == "")
        {
            alertALCON("Porfavor Carge la firma","alert");
            return false;
        }

       document.getElementById("frmModUsuarioFirma").submit();
       return true;
   }

   function fnl_modiUsuarioDatos()
   {
	   var objForm=new formUsuario();
       
	   if(trim(objForm.vc_usuario.value)=="")
       {
           alertALCON("Escriba el nombre de usuario","alert");
           objForm.vc_usuario.focus();
           return false;
       }
       if(trim(objForm.vc_grado_academico.value)=="")
       {
           alertALCON("Escriba el Grado Academico, ejemplo: Ing., Mr., Lic. ","alert");
           objForm.vc_grado_academico.focus();
           return false;
       }

	   if(trim(objForm.vc_clave.value)=="")
       {
           alertALCON("Escriba la clave del usuario","alert");
           objForm.vc_clave.focus();
           return false;
       }
	   if(trim(objForm.vc_clave_dep.value)=="")
       {
           alertALCON("Vuelva a escribir la clave del usuario","alert");
           objForm.vc_clave_dep.focus();
           return false;
       }
       if(objForm.vc_clave.value!=objForm.vc_clave_dep.value)
       {
           alertALCON("Las claves no coinciden","alert");
           return false;
       }
	   if(trim(objForm.vc_nombres.value)=="")
       {
           alertALCON("Escriba el nombre","alert");
           objForm.vc_nombres.focus();
           return false;
       }
	   if(trim(objForm.vc_apellido_paterno.value)=="")
       {
           alertALCON("Escriba el apellido paterno","alert");
           objForm.vc_apellido_paterno.focus();
           return false;
       }
	   if(trim(objForm.vc_apellido_materno.value)=="")
       {
       alertALCON("Escriba el apellido materno","alert");
       objForm.vc_apellido_materno.focus();
       return false;
       }

       document.getElementById("frmModUsuario").submit();
       return true;
   }

   function fnl_regiUsuario()
   {
	   var objForm=new formUsuario();

	   if(trim(objForm.vc_usuario.value)==""){alertALCON("Escriba el nombre de usuario","alert");objForm.vc_usuario.focus();return false;}
	   if(trim(objForm.vc_grado_academico.value)=="")
       {
           alertALCON("Escriba el Grado Academico, ejemplo: Ing., Mr., Lic. ","alert");
           objForm.vc_grado_academico.focus();
           return false;
       }
       if(trim(objForm.vc_clave.value)==""){alertALCON("Escriba la clave del usuario","alert");objForm.vc_clave.focus();return false;}
	   if(trim(objForm.vc_clave_dep.value)==""){alertALCON("Vuelva a escribir la clave del usuario","alert");objForm.vc_clave_dep.focus();return false;}
	   if(objForm.vc_clave.value!=objForm.vc_clave_dep.value){alertALCON("Las claves no coinciden","alert");return false;}
	   if(trim(objForm.vc_nombres.value)==""){alertALCON("Escriba el nombre","alert");objForm.vc_nombres.focus();return false;}
	   if(trim(objForm.vc_apellido_paterno.value)==""){alertALCON("Escriba el apellido paterno","alert");objForm.vc_apellido_paterno.focus();return false;}
	   if(trim(objForm.vc_apellido_materno.value)==""){alertALCON("Escriba el apellido materno","alert");objForm.vc_apellido_materno.focus();return false;}
       if(document.getElementById('vc_nombre_archivo').value == "")
        {
            alertALCON("Porfavor Carge la firma","alert");
            return false;
        }
	   if(trim(objForm.vc_correo.value)=="")
       {
		   alertALCON("Escriba el correo electronico","alert");objForm.vc_correo.focus();return false;
	   }
       else
       {
		   var formValid=new ValidarFormulario();
		   if(!formValid.Email(objForm.vc_correo.value)){
			   alertALCON("El correo electronico no es valido","alert");
			   return false;
		   }
	    }
	    //
		  if(objForm.vc_usuario.value.length<=5 && objForm.vc_usuario.value.length>=15)
		  {
			 alertALCON("El nombre de usuario tiene que tener entre 6 y 15 caracteres","alert");
			 return false; 
		  }
	    //
	   if(usuarioDisponible==1)
	   {
	     document.getElementById("frmRegiUsuario").submit();
	   }else
       {
	     alertALCON("El usuario que intenta registrar ya existe","error");
		 return false;
	   }
       return true;
   }
   //
   function fnl_nuevoUsuario()
   {
       var objForm=new formUsuario();
	   objForm.vc_apellido_materno.value="";
	   objForm.vc_apellido_paterno.value="";
	   objForm.vc_clave.value="";
	   objForm.vc_clave_dep.value="";
	   objForm.vc_correo.value="";
	   objForm.vc_nombres.value="";
	   objForm.vc_usuario.value="";
	   objForm.vc_usuario.focus();
       objForm.vc_nombre_archivo.value = "";
       objForm.vc_grado_academico.value = "";
	   $(".textAviso").css("display","none");
	   return true;
   }

   function fnl_correoUsuario(correox)
   {
	   if(trim(correox)=="")
           return false;
       precargaALCON("Comprobando...",true,1);
       $.ajax({
                type: 'post',
                url:'/SGDUNI/comprobarExisteCorreo.uni',
                dataType: 'html',
                data:'correox='+correox,
                success: function(data)
                {
                    if(Number(data)==1)
                    {
                       alertALCON("El correo que intenta registrar ya esta en uso.","error");
                       usuarioDisponible=0;
                       $("#dvl_xuser").html('Datos de Usuario - <strong class="txtStyleAd4Err">[ El Correo esta en uso ]</strong>');
                    }
                    else if(Number(data)==0)
                    {
                        usuarioDisponible=1;
                        $("#dvl_xuser").html('Datos de Usuario - <strong class="txtStyleAd4Ok">[ Correo disponible ]</strong>');
                    }else
                    {
                        usuarioDisponible=0;
                        alertALCON("Ups! ocurrio un error, intentelo nuevamente gracias","alert");
                    }
                    precargaALCON("",true,0);
                    return false;
                },
                error: function(dato)
                {
                    alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
                    precargaALCON("",true,0);
                    return false;
                }
           });
   }
   //
   function fnl_existeUsuario(userx)
   {
	   if(trim(userx)=="") return false; 
        precargaALCON("Comprobando...",true,1);
            $.ajax({
                type: 'post',
                url:'/SGDUNI/comprobarExisteCreaUsuario.uni',
                dataType: 'html',
                data:'userx='+userx,
                success: function(data)
                {
                    if(Number(data)==1)
                    {
                       alertALCON("El usuario que intenta registrar ya existe.","error");
                       usuarioDisponible=0;
                       $("#dvl_xuser").html('Datos de Usuario - <strong class="txtStyleAd4Err">[ El usuario ya existe ]</strong>');
                    }
                    else if(Number(data)==0)
                    {
                        usuarioDisponible=1;
                        $("#dvl_xuser").html('Datos de Usuario - <strong class="txtStyleAd4Ok">[ Usuario disponible ]</strong>');
                    }else
                    {
                        usuarioDisponible=0;
                        alertALCON("Ups! ocurrio un error, intentelo nuevamente gracias","alert");
                    }
                    precargaALCON("",true,0);
                    return false;
                },
                error: function(dato)
                {
                    alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
                    precargaALCON("",true,0);
                    return false;
                }

            });
   }
   //
   function fnl_estadoUsuario(valorestado,xcod)
   {
        precargaALCON("Procesando...",true,1,"center");
            $.ajax({
                type: 'post',
                url:'/SGDUNI/cambiarEstadoUsuario.uni',
                dataType: 'html',
                data:'estado='+valorestado+'&xcod='+xcod,
                success: function(data)
                {
                 var divFon=document.getElementById("img_ico_"+xcod);
                 var link_r=document.getElementById("a_ico_"+xcod);
                 if(Number(data)==1)
                 {//Si no hay errores
                      if(String(valorestado)=="01"){
                          divFon.src="fileproject/img/sistema/formularios/bien.gif";
                          link_r.href="javascript:void(0);fnl_estadoUsuario('02','"+xcod+"');";
                      }else if(String(valorestado)=="02"){
                          divFon.src="fileproject/img/sistema/formularios/bien2.gif";
                          link_r.href="javascript:void(0);fnl_estadoUsuario('01','"+xcod+"');";
                      }
                }else{
                    alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias","alert");
                    precargaALCON("",true,0,"center");
                    return;
                }
                    precargaALCON("",true,0,"center");
                    return;
                },
                error: function(dato){
                    alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
                    precargaALCON("",true,0,"center");
                    return;
                }

            });
			return;
   }


   // llenar caja de texto con el nombre del archivo del a ruta
function fnl_validar_doc(valDoc)
{
    document.getElementById('vc_nombre_archivo').value = "";
    filerro=0;
    var NomDoc=(  valDoc.substring( valDoc.lastIndexOf("\\") ) ).toLowerCase();

    if( trim(document.getElementById('vc_nombre_archivo').value) == "")
        document.getElementById('vc_nombre_archivo').value = NomDoc.substring( NomDoc.length ,0);

    fng_validar_archivo(valDoc,'imagen',function()
    {
        filerro = 1;
    });
}

function fnl_llamarPopupVerDetalles(idUsuario)
{
    precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
    windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '400','height':'600','url':'/SGDUNI/verDetallesUsuario.uni','urlVar':{'idUsuario':'"+idUsuario+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
    {
        precargaALCON("",true,0,"center");
    });
}




