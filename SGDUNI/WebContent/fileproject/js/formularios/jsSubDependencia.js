function fnl_formSubDependencia(){
    this.vc_nombre = document.getElementById("vc_nombre");
    this.vc_descripcion = document.getElementById("vc_descripcion");
    this.in_codigo_dependencia = document.getElementById("in_codigo_dependecia");
}


function fnl_nuevaSubDependencia()
{
    var formSubDep = new  fnl_formSubDependencia();
    formSubDep.vc_nombre.value = "";
    formSubDep.vc_descripcion.value = "";
    formSubDep.in_codigo_dependencia.selectedIndex = 0;
    formSubDep.vc_nombre.focus();
     var objAvios=$(".textAviso").html();
	   $(".textAviso").css("display","none");
    return true;
}

function fnl_regiSubDependencia()
{
    var formSubDep = new  fnl_formSubDependencia();
    if( trim(formSubDep.vc_nombre.value) == "" )
    {
           alertALCON("Escriba el Nombre de la Subdependencia", "alert");
           formSubDep.vc_nombre.focus();
          return false;
   }
   if( trim(formSubDep.vc_descripcion.value) == "" )
   {
           alertALCON("Escriba la descripción de la Subdependencia", "alert");
           formSubDep.vc_descripcion.focus();
          return false;
   }
   if (formSubDep.in_codigo_dependencia.selectedIndex == 0){
       alertALCON("Debe seleccionar una Dependencia", "alert");
       formSubDep.in_codigo_dependencia.focus();
       return false;
    }
   document.getElementById("frmRegiSubDependencia").submit();
   return true;
}


function fnl_modSubDependencia()
{
    var formSubDep = new  fnl_formSubDependencia();
    if( trim(formSubDep.vc_nombre.value) == "" )
    {
           alertALCON("Escriba el Nombre de la Subdependencia", "alert");
           formSubDep.vc_nombre.focus();
          return false;
   }
   if( trim(formSubDep.vc_descripcion.value) == "" )
   {
           alertALCON("Escriba la descripción de la Subdependencia", "alert");
           formSubDep.vc_descripcion.focus();
          return false;
   }
   if (document.getElementById("in_codigo_dependecia").selectedIndex == 0)
   {
       alertALCON("Debe seleccionar una Dependencia", "alert");
       document.getElementById("in_codigo_dependecia").focus();
       return false;
    }
   document.getElementById("frmModSubDep").submit();
   return true;
}

function fnl_estadoSubDependencia(valorestado,xcod)
   {
       
	   
				precargaALCON("Procesando...",true,1,"center");
					$.ajax({
						type: 'post',
                        url:'/SGDUNI/cambiarEstadoSubDependencia.uni',
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
								  link_r.href="javascript:void(0);fnl_estadoSubDependencia('02','"+xcod+"');";
							  }else if(String(valorestado)=="02"){
								  divFon.src="fileproject/img/sistema/formularios/bien2.gif";
								  link_r.href="javascript:void(0);fnl_estadoSubDependencia('01','"+xcod+"');";
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

