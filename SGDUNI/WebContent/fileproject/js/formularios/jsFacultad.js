/***********************
FORMULARIO FACULTAD
************************/
function fnl_formFacultad()
{
    this.vc_nombre = document.getElementById("vc_nombre");
    this.vc_abrev_nom = document.getElementById("vc_abrev_nom");
    this.vc_descripcion = document.getElementById("vc_descripcion");
    this.in_codigo_organo = document.getElementById("in_codigo_organo");
    //29/07/2012
    this.vc_direccion = document.getElementById("vc_direccion");
    this.vc_apartado = document.getElementById("vc_apartado");
    this.vc_telefono = document.getElementById("vc_telefono");
    this.vc_telefono_central = document.getElementById("vc_telefono_central");
    this.vc_anexo = document.getElementById("vc_anexo");
    this.vc_correo = document.getElementById("vc_correo");
}

function fnl_nuevoFacultad()
{
    var formFacultad = new  fnl_formFacultad();
    formFacultad.vc_nombre.value = "";
    formFacultad.vc_nombre.focus();
    formFacultad.vc_abrev_nom.value = "";
    formFacultad.vc_descripcion.value = "";
    //29/07/2012
    formFacultad.vc_direccion.value = "";
    formFacultad.vc_apartado.value = "";
    formFacultad.vc_telefono.value = "";
    formFacultad.vc_telefono_central.value = "";
    formFacultad.vc_anexo.value = "";
    formFacultad.vc_correo.value = "";
    
    var objAvios = $(".textAviso").html();
	   $(".textAviso").css("display","none");
	   return true;
    return true;
}

function fnl_regiFacultad()
{
    var formFacultad = new  fnl_formFacultad();
    if( trim(formFacultad.vc_nombre.value) == "" ) {
           alertALCON("Escriba el Nombre de la Facultad", "alert");
           formFacultad.vc_nombre.focus();
          return false;
   }

   if( trim(formFacultad.vc_abrev_nom.value) == "" ) {
           alertALCON("Escriba la Abreviatura del Nombre de la Facultad", "alert");
           formFacultad.vc_abrev_nom.focus();
          return false;
   }

   if( trim(formFacultad.vc_descripcion.value) == "" ) {
           alertALCON("Escriba la descripción de la Facultad", "alert");
           formFacultad.vc_descripcion.focus();
          return false;
   }
   if (formFacultad.in_codigo_organo.selectedIndex == 0){
       alertALCON("Debe seleccionar un Organo", "alert");
       formFacultad.in_codigo_organo.focus();
       return false;
    }

    //29/07/2012
    if( trim(formFacultad.vc_direccion .value) == "" ) {
           alertALCON("Escriba la dirección de la Facultad", "alert");
           formFacultad.vc_direccion.focus();
          return false;
    }
    if( trim(formFacultad.vc_apartado.value) == "" ) {
           alertALCON("Escriba el Apartado de la Facultad", "alert");
           formFacultad.vc_apartado.focus();
          return false;
    }
    if( trim(formFacultad.vc_telefono.value) == "" ) {
           alertALCON("Escriba el Telefono de la Facultad", "alert");
           formFacultad.vc_telefono.focus();
          return false;
    }
    if( trim(formFacultad.vc_telefono_central.value) == "" )
    {
           alertALCON("Escriba el Telefono Central de la Facultad", "alert");
           formFacultad.vc_telefono_central.focus();
          return false;
    }
    if( trim(formFacultad.vc_anexo.value) == "" )
    {
           alertALCON("Escriba el Anexo de la Facultad", "alert");
           formFacultad.vc_anexo.focus();
          return false;
    }
    if( trim(formFacultad.vc_correo.value) == "" )
    {
           alertALCON("Escriba el Correo de la Facultad", "alert");
           formFacultad.vc_correo.focus();
          return false;
    }

   document.getElementById("frmRegiFacultad").submit();

   return true;
}

function fnl_modiFacultad()
{
	var formFacultad = new  fnl_formFacultad();
    
    if( trim(formFacultad.vc_nombre.value) == "" )
    {
           alertALCON("Escriba el Nombre de la Facultad", "alert");
           formFacultad.vc_nombre.focus();
          return false;
    }

    if( trim(formFacultad.vc_abrev_nom.value) == "" ) {
           alertALCON("Escriba la Abreviatura del Nombre de la Facultad", "alert");
           formFacultad.vc_abrev_nom.focus();
          return false;
   }
   
   if( trim(formFacultad.vc_descripcion.value) == "" )
   {
           alertALCON("Escriba la descripción de la Facultad", "alert");
           formFacultad.vc_descripcion.focus();
          return false;
   }
   if (formFacultad.in_codigo_organo.selectedIndex==0)
   {
       alertALCON("Debe seleccionar un Organo", "alert");
       formFacultad.in_codigo_organo.focus();
       return false;
    }
    //29/07/2012
    if( trim(formFacultad.vc_direccion .value) == "" ) {
           alertALCON("Escriba la dirección de la Facultad", "alert");
           formFacultad.vc_direccion.focus();
          return false;
    }
    if( trim(formFacultad.vc_apartado.value) == "" ) {
           alertALCON("Escriba el Apartado de la Facultad", "alert");
           formFacultad.vc_apartado.focus();
          return false;
    }
    if( trim(formFacultad.vc_telefono.value) == "" ) {
           alertALCON("Escriba el Telefono de la Facultad", "alert");
           formFacultad.vc_telefono.focus();
          return false;
    }
    if( trim(formFacultad.vc_telefono_central.value) == "" )
    {
           alertALCON("Escriba el Telefono Central de la Facultad", "alert");
           formFacultad.vc_telefono_central.focus();
          return false;
    }
    if( trim(formFacultad.vc_anexo.value) == "" )
    {
           alertALCON("Escriba el Anexo de la Facultad", "alert");
           formFacultad.vc_anexo.focus();
          return false;
    }
    if( trim(formFacultad.vc_correo.value) == "" )
    {
           alertALCON("Escriba el Correo de la Facultad", "alert");
           formFacultad.vc_correo.focus();
          return false;
    }

   document.getElementById("frmModFacultad").submit();
   return true;
   }

function fnl_estadoFacultad(valorestado,xcod)
   {
  
	   
				precargaALCON("Procesando...",true,1,"center");
					$.ajax({
						type: 'post',
                        url:'/SGDUNI/cambiarEstadoFacultad.uni',
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
								  link_r.href="javascript:void(0);fnl_estadoFacultad('02','"+xcod+"');";
							  }else if(String(valorestado)=="02"){
								  divFon.src="fileproject/img/sistema/formularios/bien2.gif";
								  link_r.href="javascript:void(0);fnl_estadoFacultad('01','"+xcod+"');";
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

