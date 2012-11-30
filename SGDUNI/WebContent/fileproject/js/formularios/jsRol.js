/***********************
FORMULARIO NUEVA ACTIVIDAD
************************/
 var usuarioDisponible=0;
   function formRol()
   {
       this.ch_codigo_rol=document.getElementById("ch_codigo_rol");
       this.vc_nombre=document.getElementById("vc_nombre");
   }
   //
   function fnl_modiRol()
   {
	   var objForm=new formRol();
	   if(trim(objForm.vc_nombre.value)=="")
       {
           alertALCON("Escriba el Nombre del Rol","alert");
           objForm.vc_nombre.focus();
           return false;
       }
	   document.getElementById("frmModProtocolo").submit();
       return true;
   }
   //
   function fnl_regiROL()
   {
	   var objForm=new formRol();
	   if(trim(objForm.ch_codigo_rol.value)=="")
       {
           alertALCON("Escriba el Codigo del Rol","alert");
           objForm.ch_codigo_rol.focus();
           return false;
       }
	   if(trim(objForm.vc_nombre.value)=="")
       {
           alertALCON("Escriba el Nombre del Rol","alert");
           objForm.vc_nombre.focus();
           return false;
       }
	   document.getElementById("frmRegiRol").submit();
       return true;
   }
   //
   function fnl_nuevoROL(){
       var objForm=new formRol();
       objForm.ch_codigo_rol.value="";
       objForm.vc_nombre.value="";
       objForm.ch_codigo_rol.focus();
	   var objAvios=$(".textAviso").html();
	   $(".textAviso").css("display","none");
	   return true;
   }
   //
   function fnl_estadoRol(valorestado,xcod){
    
				precargaALCON("Procesando...",true,1,"center");
					$.ajax({
						type: 'post',
                        url:'/SGDUNI/cambiarEstadoRol.uni',
						dataType: 'html',
						data:'estado='+valorestado+'&xcod='+trim(xcod),
						success: function(data)
                        {                            
						 var divFon=document.getElementById("img_ico_"+String(xcod));
                         var link_r=document.getElementById("a_ico_"+String(xcod));
                         if(Number(data)==1)
						 {//Si no hay errores
							  if(String(valorestado)=="01"){
								  divFon.src="fileproject/img/sistema/formularios/bien.gif";
								  link_r.href="javascript:void(0);fnl_estadoRol('02','"+xcod+"');";
							  }else if(String(valorestado)=="02"){
								  divFon.src="fileproject/img/sistema/formularios/bien2.gif";
								  link_r.href="javascript:void(0);fnl_estadoRol('01','"+xcod+"');";
							  }
						}
                        else
                        {
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias","alert");
                            precargaALCON("",true,0,"center");
							return;
						}
							precargaALCON("",true,0,"center");
							return;
						},
						error: function(dato)
                        {
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							precargaALCON("",true,0,"center");
							return;
						}
					});
			return;
   }