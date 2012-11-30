$(document).ready(
function()
  {
  fnl_llamarListaOrganos();
  }
);

//abrir pop up de registro
function fnl_llamarPopupModificar(codigo)
{
    precargaALCON("Cargando...",true,1,"center");
    windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'340','url':'/SGDUNI/actualizarOrgano.uni','urlVar':{'codigo':'"+codigo+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
    {
        precargaALCON("",true,0,"center");
    });
}

/***********************
FORMULARIO NUEVA ACTIVIDAD
************************/
 var usuarioDisponible=0;
   function formOrganoM()
   {
       this.vc_nombre      = document.getElementById("vc_nombreM");
       this.vc_descripcion = document.getElementById("vc_descripcionM");
   }

   function formOrgano()
   {
       this.vc_nombre      = document.getElementById("vc_nombre");
       this.vc_descripcion = document.getElementById("vc_descripcion");
   }

   function fnl_regiOrgano()
   {
	   var objForm=new formOrgano();
	   if(trim(objForm.vc_nombre.value)=="")
       {
           alertALCON("Escriba el nombre del organo","alert");
           objForm.vc_nombre.focus();
           return false;
       }
	   if(trim(objForm.vc_descripcion.value)=="")
       {
           alertALCON("Escriba la descripcion del organo","alert");
           objForm.vc_descripcion.focus();
           return false;
       }
       document.getElementById("textAviso").style.display = 'block';
       $.ajax({
                    type: 'post',
                    url:'/SGDUNI/insertarOrgano.uni',
                    dataType: 'html',
                    data:'vc_nombre='+trim(objForm.vc_nombre.value)+"&vc_descripcion="+trim(objForm.vc_descripcion.value),
                    success: function(data)
                    {
                        if(Number(data)==1)
                        {
                          document.getElementById("textAviso").innerHTML = "El Organo fue registrado correctamente";
                          fnl_llamarListaOrganos();
                        }
                        else
                        {
                            document.getElementById("textAviso").innerHTML = "ERROR! No se pudo guardar el Organo";
                        }

                    },
                    error: function(dato){
                        alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
                        return false;
                    }

                });
       return true;
   }

   function fnl_llamarListaOrganos()
   {
       $.ajax({
            type: 'post',
            url:'/SGDUNI/listarOrgano.uni',
            dataType: 'html',
            success: function(data)
            {
                document.getElementById("dvl_ListaOrganos").innerHTML = data;
            },
            error: function(dato){
                alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
                //precargaALCON("",true,0,"center");
                return false;
            }
        });
   }

   //
   function fnl_modiUsuario()
   {
	   var objForm=new formOrganoM();
	   if(trim(objForm.vc_nombre.value)=="")
       {
           alertALCON("Escriba el nombre del organo","alert");
           objForm.vc_nombre.focus();
           return false;
       }
	   if(trim(objForm.vc_descripcion.value)=="")
       {
           alertALCON("Escriba la descripcion del organo","alert");
           objForm.vc_descripcion.focus();
           return false;
       }
	   document.getElementById("frmModOrgano").submit();
       fnl_cancelar();
       alertALCON("El Organo fue modificado Correctamente","alert");
       return true;
   }
   //
   function fnl_nuevoOrgano()
   {
       var objForm=new formOrgano();
	   objForm.vc_nombre.value="";
	   objForm.vc_descripcion.value="";
	   objForm.vc_nombre.focus();
	   $(".textAviso").css("display","none");
	   return true;
   }

function fnl_cancelar()
{
   CloseWindowPopUpAlcon();//cerrar popup
}

   //
   function fnl_estadoOrgano(valorestado,xcod)
   {
       
	   
    precargaALCON("Procesando...",true,1,"center");
        $.ajax({
            type: 'post',
            url:'/SGDUNI/cambiarEstadoOrgano.uni',
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
                      link_r.href="javascript:void(0);fnl_estadoOrgano('02','"+xcod+"');";
                  }else if(String(valorestado)=="02")
                  {
                      divFon.src="fileproject/img/sistema/formularios/bien2.gif";
                      link_r.href="javascript:void(0);fnl_estadoOrgano('01','"+xcod+"');";
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

