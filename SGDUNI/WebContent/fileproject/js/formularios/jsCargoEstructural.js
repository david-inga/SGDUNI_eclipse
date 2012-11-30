function fnl_form()
{
   this.in_codigo_cargo_estruc = document.getElementById("in_codigo_cargo_estruc");
   this.vc_nombre = document.getElementById("vc_nombre");
   this.vc_descripcion = document.getElementById("vc_descripcion");
}

function fnl_nuevoCargoEstructural()
{
   var form = new  fnl_form();
   form.vc_nombre.value = "";
   form.vc_descripcion.value = "";
   form.vc_nombre.focus();
   var objAvios = $(".textAviso").html();
	   $(".textAviso").css("display","none");
   return true;
}

function fnl_regiCargoEstructural()
{
   var form = new  fnl_form();
   if( trim(form.vc_nombre.value) == "" )
   {
          alertALCON("Escriba el Nombre del Cargo", "alert");
          form.vc_nombre.focus();
          return false;
   }
   if( trim(form.vc_descripcion.value) == "" )
   {
           alertALCON("Escriba la descripción del Cargo", "alert");
           form.vc_descripcion.focus();
          return false;
   }

   document.getElementById("frmRegiCargoEstruct").submit();
   return true;
}


function fnl_modiCargoEstructural()
{
    
    var in_codigo_cargo_estruc = document.getElementById("in_codigo_cargo_estruc").value;
    var vc_nombre             = document.getElementById("vc_nombre").value;
    var vc_descripcion        = document.getElementById("vc_descripcion").value;

    if( trim(in_codigo_cargo_estruc) == "" )
    {
       alertALCON("El ID el nulo", "alert");
       return false;
    }
    if( trim(vc_nombre) == "" )
    {
       alertALCON("Escriba el Nombre del Cargo", "alert");
       return false;
    }
    if( trim(vc_descripcion) == "" )
    {
      alertALCON("Escriba la descripción del Cargo", "alert");
      return false;
    }
    //alert('id ='+in_codigo_cargo_estruc+', nombre = '+vc_nombre+', descricion = '+vc_descripcion);
   document.getElementById("textAviso").style.display="block";
   $.ajax({
                type: 'post',
                url:'/SGDUNI/modificarCargoEstructural.uni',
                dataType: 'html',
                data:'in_codigo_cargo_estruc='+in_codigo_cargo_estruc+"&vc_nombre="+vc_nombre+"&vc_descripcion="+vc_descripcion,
                success: function(data)
                {
                    if(Number(data) == 1)
                    {
                      document.getElementById("textAviso").innerHTML="El Cargo se modifico correctamente!";
                    }
                    else
                    {
                        document.getElementById("textAviso").innerHTML="Error! ocurrio un error al modificar el cargo.";
                    }
                },
                error: function(dato)
                {
                    alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
                    return false;
                }
           });
   return true;
}

function fnl_estadoCargoEstructural(valorestado,xcod)
   {      
        precargaALCON("Procesando...",true,1,"center");
        $.ajax({
            type: 'post',
            url:'/SGDUNI/cambiarEstadoCargoEstructural.uni',
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
                      link_r.href="javascript:void(0);fnl_estadoCargoEstructural('02','"+xcod+"');";
                  }
                  else if(String(valorestado)=="02")
                  {
                      divFon.src="fileproject/img/sistema/formularios/bien2.gif";
                      link_r.href="javascript:void(0);fnl_estadoCargoEstructural('01','"+xcod+"');";
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

