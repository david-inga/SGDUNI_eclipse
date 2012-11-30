/***********************
FORMULARIO FACULTAD
************************/
function fnl_form(){
    this.vc_nombre = document.getElementById("vc_nombre");
    this.vc_descripcion = document.getElementById("vc_descripcion");
    this.in_codigo_area = document.getElementById("in_codigo_area");
}


function fnl_nuevoCargoClasi(){
    var form = new  fnl_form();
    form.vc_nombre.value = "";
    form.vc_descripcion.value = "";
    form.in_codigo_area.selectedIndex = 0;
    form.vc_nombre.focus();
    var objAvios=$(".textAviso").html();
	   $(".textAviso").css("display","none");
	   return true;
    return true;
}

function fnl_regiCargoClasi()
{
    var form = new  fnl_form();
    if( trim(form.vc_nombre.value) == "" ) {
           alertALCON("Escriba el Nombre del Cargo", "alert");
           form.vc_nombre.focus();
          return false;
   }
   if( trim(form.vc_descripcion.value) == "" ) {
           alertALCON("Escriba la descripción de la Cargo", "alert");
           form.vc_descripcion.focus();
          return false;
   }
   if (form.in_codigo_area.selectedIndex == 0){
       alertALCON("Debe seleccionar una Área", "alert");
       form.in_codigo_area.focus();
       return false;
    }
   document.getElementById("frmRegiCargoClasi").submit();
   return true;
}

function fnl_ModiCargoClasi()
{
    var form = new  fnl_form();
    if( trim(form.vc_nombre.value) == "" ) {
           alertALCON("Escriba el Nombre del Cargo", "alert");
           form.vc_nombre.focus();
          return false;
   }
   if( trim(form.vc_descripcion.value) == "" ) {
           alertALCON("Escriba la descripción de la Cargo", "alert");
           form.vc_descripcion.focus();
          return false;
   }
   if (form.in_codigo_area.selectedIndex == 0){
       alertALCON("Debe seleccionar una Área", "alert");
       form.in_codigo_area.focus();
       return false;
    }
   document.getElementById("frmModCargo").submit();
   return true;
}

function fnl_estadoCargoClasificado(valorestado,xcod)
   {
       
	   
				precargaALCON("Procesando...",true,1,"center");
					$.ajax({
						type: 'post',
                        url:'/SGDUNI/cambiarEstadoCargoClasificado.uni',
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
								  link_r.href="javascript:void(0);fnl_estadoCargoClasificado('02','"+xcod+"');";
							  }else if(String(valorestado)=="02"){
								  divFon.src="fileproject/img/sistema/formularios/bien2.gif";
								  link_r.href="javascript:void(0);fnl_estadoCargoClasificado('01','"+xcod+"');";
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