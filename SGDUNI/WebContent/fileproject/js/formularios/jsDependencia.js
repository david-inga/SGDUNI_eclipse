
/***********************
FORMULARIO FACULTAD
************************/

function fnl_formDependencia(){
    this.vc_abrev_nom = document.getElementById("vc_abrev_nom");
    this.vc_nombre = document.getElementById("vc_nombre");
    this.vc_descripcion = document.getElementById("vc_descripcion");
    this.in_codigo_organo = document.getElementById("in_codigo_organo");
    //this.in_codigo_facultad = document.getElementById("in_codigo_facultad");
    //29/07/2012
    this.vc_direccion = document.getElementById("vc_direccion");
    this.vc_apartado = document.getElementById("vc_apartado");
    this.vc_telefono = document.getElementById("vc_telefono");
    this.vc_telefono_central = document.getElementById("vc_telefono_central");
    this.vc_anexo = document.getElementById("vc_anexo");
    this.vc_correo = document.getElementById("vc_correo");
}

function fnl_nuevaDependencia()
{
    var formDep = new  fnl_formDependencia();
    formDep.vc_nombre.value = "";
    formDep.vc_abrev_nom.value = "";
    formDep.vc_descripcion.value = "";
    formDep.in_codigo_organo.selectedIndex = 0;
    formDep.vc_abrev_nom.value = "";
    //formDep.in_codigo_facultad.selectedIndex = 0;
    //29/07/2012
    formFacultad.vc_direccion.value = "";
    formFacultad.vc_apartado.value = "";
    formFacultad.vc_telefono.value = "";
    formFacultad.vc_telefono_central.value = "";
    formFacultad.vc_anexo.value = "";
    formFacultad.vc_correo.value = "";
    formDep.vc_nombre.focus();
    
	   $(".textAviso").css("display","none");
	   return true;
   return true;
}

function fnl_regiDependencia()
{
    var formDep = new  fnl_formDependencia();
    
    if( trim(formDep.vc_nombre.value) == "" ) {
           alertALCON("Escriba el Nombre de la Dependencia", "alert");
           formDep.vc_nombre.focus();
          return false;
    }

   if( trim(formDep.vc_abrev_nom.value) == "" ) {
           alertALCON("Escriba la Abreviatura del Nombre de la Dependencia", "alert");
           formDep.vc_abrev_nom.focus();
          return false;
   }

   if( trim(formDep.vc_descripcion.value) == "" ) {
           alertALCON("Escriba la descripción de la Dependencia", "alert");
           formDep.vc_descripcion.focus();
          return false;
   }
   if (formDep.in_codigo_organo.selectedIndex == 0){
       alertALCON("Debe seleccionar un Organo", "alert");
       formDep.in_codigo_organo.focus();
       return false;
    }

    //29/07/2012
    if( trim(formDep.vc_direccion .value) == "" ) {
           alertALCON("Escriba la dirección de la Dependencia", "alert");
           formDep.vc_direccion.focus();
          return false;
    }
    if( trim(formDep.vc_apartado.value) == "" ) {
           alertALCON("Escriba el Apartado de la Dependencia", "alert");
           formDep.vc_apartado.focus();
          return false;
    }
    if( trim(formDep.vc_telefono.value) == "" ) {
           alertALCON("Escriba el Telefono de la Dependencia", "alert");
           formDep.vc_telefono.focus();
          return false;
    }
    if( trim(formDep.vc_telefono_central.value) == "" )
    {
           alertALCON("Escriba el Telefono Central de la Dependencia", "alert");
           formDep.vc_telefono_central.focus();
          return false;
    }
    if( trim(formDep.vc_anexo.value) == "" )
    {
           alertALCON("Escriba el Anexo de la Dependencia", "alert");
           formDep.vc_anexo.focus();
          return false;
    }
    if( trim(formDep.vc_correo.value) == "" )
    {
           alertALCON("Escriba el Correo de la Dependencia", "alert");
           formDep.vc_correo.focus();
          return false;
    }

   document.getElementById("frmRegiDep").submit();
   return true;
}

function fnl_modiDependencia()
   {
	var formDep = new  fnl_formDependencia();
    
    if( trim(formDep.vc_nombre.value) == "" )
    {
           alertALCON("Escriba el Nombre de la Dependencia", "alert");
           formDep.vc_nombre.focus();
          return false;
   }

   if( trim(formDep.vc_abrev_nom.value) == "" )
   {
           alertALCON("Escriba la Abreviatura del Nombre de la Dependencia", "alert");
           formDep.vc_abrev_nom.focus();
          return false;
   }

   if( trim(formDep.vc_descripcion.value) == "" ) {
           alertALCON("Escriba la descripción de la Dependencia", "alert");
           formDep.vc_descripcion.focus();
          return false;
   }
   if (formDep.in_codigo_organo.selectedIndex == 0){
       alertALCON("Debe seleccionar un Organo", "alert");
       formDep.in_codigo_organo.focus();
       return false;
    }
    //29/07/2012
    if( trim(formDep.vc_direccion .value) == "" ) {
           alertALCON("Escriba la dirección de la Dependencia", "alert");
           formDep.vc_direccion.focus();
          return false;
    }
    if( trim(formDep.vc_apartado.value) == "" ) {
           alertALCON("Escriba el Apartado de la Dependencia", "alert");
           formDep.vc_apartado.focus();
          return false;
    }
    if( trim(formDep.vc_telefono.value) == "" ) {
           alertALCON("Escriba el Telefono de la Dependencia", "alert");
           formDep.vc_telefono.focus();
          return false;
    }
    if( trim(formDep.vc_telefono_central.value) == "" )
    {
           alertALCON("Escriba el Telefono Central de la Dependencia", "alert");
           formDep.vc_telefono_central.focus();
          return false;
    }
    if( trim(formDep.vc_anexo.value) == "" )
    {
           alertALCON("Escriba el Anexo de la Dependencia", "alert");
           formDep.vc_anexo.focus();
          return false;
    }
    if( trim(formDep.vc_correo.value) == "" )
    {
           alertALCON("Escriba el Correo de la Dependencia", "alert");
           formDep.vc_correo.focus();
          return false;
    }

   document.getElementById("frmModDep").submit();
   return true;
   }

function fnl_estadoDependencia(valorestado,xcod,xcodusu)
{ 
    precargaALCON("Procesando...",true,1,"center");
        $.ajax({
            type: 'post',
            url:'/SGDUNI/cambiarEstadoDependencia.uni',
            dataType: 'html',
            data:'estado='+valorestado+'&xcod='+xcod+'&codusu='+xcodusu,
            success: function(data)
            {
             var divFon=document.getElementById("img_ico_"+xcod);
             var link_r=document.getElementById("a_ico_"+xcod);
             
                if(Number(data)==1)
                {//Si no hay errores
                      if(String(valorestado)=="01")
                      {
                          divFon.src="fileproject/img/sistema/formularios/bien.gif";
                          link_r.href="javascript:void(0);fnl_estadoDependencia('02','"+xcod+"','"+xcodusu+"');";
                      }
                      else if(String(valorestado)=="02")
                      {
                          divFon.src="fileproject/img/sistema/formularios/bien2.gif";
                          link_r.href="javascript:void(0);fnl_estadoDependencia('01','"+xcod+"','"+xcodusu+"');";
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


