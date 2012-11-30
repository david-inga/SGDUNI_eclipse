function formClasiCargo()
   {
       this.vc_nombre      = document.getElementById("vc_nombre");
       this.vc_descripcion = document.getElementById("vc_descripcion");
   }

      function fnl_nuevoClasiCargo()
   {
       var objForm = new formClasiCargo();
	   objForm.vc_nombre.value="";
	   objForm.vc_descripcion.value="";
	   objForm.vc_nombre.focus();
	   var objAvios=$(".textAviso").html();
	   $(".textAviso").css("display","none");
	   return true;
   }

   function fnl_modClasiCargo()
   {
	   var objForm = new formClasiCargo();
	   if(trim(objForm.vc_nombre.value)=="")
       {
           alertALCON("Escriba el nombre de la Clasificación","alert");
           objForm.vc_nombre.focus();
           return false;
       }
	   if(trim(objForm.vc_descripcion.value)=="")
       {
           alertALCON("Escriba la descripcion del Clasificación","alert");
           objForm.vc_descripcion.focus();
           return false;
       }
	   document.getElementById("frmModClasiCargo").submit();
       return true;
   }

      function fnl_regiClasiCargo()
   {
	   var objForm = new formClasiCargo();
	   if(trim(objForm.vc_nombre.value)=="")
       {
           alertALCON("Escriba el nombre de la Clasificación","alert");
           objForm.vc_nombre.focus();
           return false;
       }
	   if(trim(objForm.vc_descripcion.value)=="")
       {
           alertALCON("Escriba la descripcion del Clasificación","alert");
           objForm.vc_descripcion.focus();
           return false;
       }
	   document.getElementById("frmRegiClasiCargo").submit();
       return true;
   }



function fnl_estadoClasiCargo(valorestado,xcod)
   {
 
	   
				precargaALCON("Procesando...",true,1,"center");
					$.ajax({
						type: 'post',
                        url:'/SGDUNI/cambiarEstadoClasiCargo.uni',
						dataType: 'html',
						data:'estado='+valorestado+'&xcod='+xcod,
						success: function(data)
                        {
						 var divFon=document.getElementById("img_ico_"+xcod);
						 var link_r=document.getElementById("a_ico_"+xcod);
						 if(Number(data)==1)
						 {//Si no hay errores
							  if(String(valorestado)=="01")
                              {
								  divFon.src="fileproject/img/sistema/formularios/bien.gif";
								  link_r.href="javascript:void(0);fnl_estadoClasiCargo('02','"+xcod+"');";
							  }
                              else if(String(valorestado)=="02")
                              {
								  divFon.src="fileproject/img/sistema/formularios/bien2.gif";
								  link_r.href="javascript:void(0);fnl_estadoClasiCargo('01','"+xcod+"');";
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

