function fnl_formUnidad(){
    this.vc_nombre = document.getElementById("vc_nombre");
    this.vc_descripcion = document.getElementById("vc_descripcion");
    this.in_codigo_subdependencia = document.getElementById("in_codigo_subdependecia");
}


function fnl_nuevaUnidad()
{
    var form = new  fnl_formUnidad();

    form.vc_nombre.value = "";
    form.vc_descripcion.value = "";
    form.in_codigo_subdependencia.selectedIndex = 0;
    form.vc_nombre.focus();
     var objAvios=$(".textAviso").html();
	   $(".textAviso").css("display","none");
    return true;
}

function fnl_ModificarUnidad()
{
    var form = new  fnl_formUnidad();
    if( trim(form.vc_nombre.value) == "" )
    {
           alertALCON("Escriba el Nombre de la Unidad", "alert");
           form.vc_nombre.focus();
          return false;
   }
   if( trim(form.vc_descripcion.value) == "" )
   {
           alertALCON("Escriba la descripción de la Unidad", "alert");
           form.vc_descripcion.focus();
          return false;
   }
   if (form.in_codigo_subdependencia.selectedIndex == 0)
   {
       alertALCON("Debe seleccionar una Subdependencia", "alert");
       form.in_codigo_subdependencia.focus();
       return false;
    }
   document.getElementById("frmModUnidad").submit();
   return true;
}

function fnl_regiUnidad()
{
     var form = new  fnl_formUnidad();
    if( trim(form.vc_nombre.value) == "" )
    {
           alertALCON("Escriba el Nombre de la Unidad", "alert");
           form.vc_nombre.focus();
          return false;
   }
   if( trim(form.vc_descripcion.value) == "" )
   {
           alertALCON("Escriba la descripción de la Unidad", "alert");
           form.vc_descripcion.focus();
          return false;
   }
   if (form.in_codigo_subdependencia.selectedIndex == 0)
   {
       alertALCON("Debe seleccionar una Subdependencia", "alert");
       form.in_codigo_subdependencia.focus();
       return false;
    }
   document.getElementById("frmRegiUnidad").submit();
   return true;
}

function fnl_estadoUnidad(valorestado,xcod)
   {
		  
				
				precargaALCON("Procesando...",true,1,"center");
					$.ajax({
						type: 'post',
                        url:'/SGDUNI/cambiarEstadoUnidad.uni',
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
								  link_r.href="javascript:void(0);fnl_estadoUnidad('02','"+xcod+"');";
							  }else if(String(valorestado)=="02"){
								  divFon.src="fileproject/img/sistema/formularios/bien2.gif";
								  link_r.href="javascript:void(0);fnl_estadoUnidad('01','"+xcod+"');";
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

