
function formRof()
{
   this.ch_codigo_rof      = document.getElementById("ch_codigo_rof");
   this.vc_introduccion = document.getElementById("vc_introduccion");
   this.vc_naturaleza_finalidad = document.getElementById("vc_naturaleza_finalidad");
}

function fnl_regiRof()
{
   var objForm = new formRof();

   if(trim(objForm.ch_codigo_rof.value)=="")
   {
       alertALCON("El codigo no se genero","alert");
       objForm.ch_codigo_rof.focus();
       return false;
   }
   if(trim(objForm.vc_introduccion.value)=="")
   {
       alertALCON("Escriba la Introducción del ROF","alert");
       objForm.vc_introduccion.focus();
       return false;
   }

   if(trim(objForm.vc_naturaleza_finalidad.value)=="")
   {
       alertALCON("Escriba el campo Naturaleza y Finalidad del ROF","alert");
       objForm.vc_naturaleza_finalidad.focus();
       return false;
   }
   document.getElementById("frmRegiROF").submit();
   return true;
}

function fnl_regiRofParteDos()
{
   if(trim(document.getElementById("vc_alcance").value)=="")
   {
       alertALCON("Escriba el Alcance del ROF","alert");
       objForm.vc_introduccion.focus();
       return false;
   }

   if(trim(document.getElementById("in_codigo_estructura").value)=="")
   {
       alertALCON("Selecione el Organigrama que desea adjuntar","alert");
       return false;
   }
   document.getElementById("frmRegiROFDOS").submit();
   return true;
}

function fnl_regiRofParteTres()
{
   if(trim(document.getElementById("vc_relaciones_interinstitucionales").value)=="")
   {
       alertALCON("El campo Relaciones interinstitucionales del ROF es Necesario","alert");
       document.getElementById("vc_relaciones_interinstitucionales").focus();
       return false;
   }
   if(trim(document.getElementById("vc_disposiciones_finales").value) == "")
   {
       alertALCON("El campo disposiciones finales del ROF es Necesario","alert");
       document.getElementById("vc_disposiciones_finales").focus();
       return false;
   }
   
   document.getElementById("frmRegiROFTRES").submit();
   return true;
}

function fnl_AgregarRegistro()
{
    var in_codigo_rof = document.getElementById("in_codigo_rofRG").value;
    //var in_codigo_version = document.getElementById("in_codigo_versionRG").value;
    var in_codigo_organo = document.getElementById("in_codigo_organo").selectedIndex;
    var vc_nombre_unidad = document.getElementById("vc_nombre_unidad").value;
    var vc_descripcion_unidad = document.getElementById("vc_descripcion_unidad").value;


   if(trim(in_codigo_rof)=="")
   {
       alertALCON("No se Detecto el ID del ROF,Cierre y Vuelva a intentar","alert");
       return false;
   }

   if(in_codigo_organo == 0)
   {
       alertALCON("Es necesario seleccionar un Organo","alert");
       document.getElementById("in_codigo_organo").focus();
       return false;
   }

   if(trim(vc_nombre_unidad) == "")
   {
       alertALCON("Es necesario llenar el campo Nombre de la Unidad","alert");
       document.getElementById("vc_nombre_unidad").focus();
       return false;
   }
   if(trim(vc_descripcion_unidad) == "")
   {
       alertALCON("Es necesario llenar el campo Descripción","alert");
       document.getElementById("vc_descripcion_unidad").focus();
       return false;
   }

   document.getElementById("textAvisoRG").style.display="block";

   $.ajax({
        type: 'post',
        url:'/SGDUNI/guardarRegistroROF.uni',
        dataType: 'html',
        data:"in_codigo_rof="+in_codigo_rof+"&in_codigo_organo="+in_codigo_organo+"&vc_nombre_unidad="+vc_nombre_unidad+"&vc_descripcion_unidad="+document.getElementById("vc_descripcion_unidad").value,
        success: function(data)
        {
            if(Number(data) == 1)
            {
              //document.getElementById("textAviso_rof_base").innerHTML = "";
              alertALCON("El Registro fue guardado correctamente","alert");
              fnl_llamarGrillaRegistros(in_codigo_rof);
              fnl_cancelar();
            }
            else
            {
                document.getElementById("textAvisoRG").innerHTML = "ERROR! No se puedo Guardar el Registro";
            }
        },
        error: function(dato)
        {
            alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
            //precargaALCON("",true,0,"center");
            return false;
        }
    });

   return true;
}

function fnl_AgregarArea()
{
    var vc_nombre_unidad = document.getElementById("vc_nombre_unidad").value;
    var vc_nombre_area = document.getElementById("vc_nombre_area").value;
    var vc_descripcion_area = document.getElementById("vc_descripcion_area").value;
    var in_codigo_registro = document.getElementById("in_codigo_registro").value;

   if(trim(in_codigo_registro) == "")
   {
       alertALCON("No se detecto el ID del Registro, Cierre y vuelva a intentar ","alert");
       return false;
   }
   if(trim(vc_nombre_unidad) == "")
   {
       alertALCON("Es necesario llenar el campo Nombre de la Unidad","alert");
       document.getElementById("vc_nombre_unidad").focus();
       return false;
   }

   if(trim(vc_nombre_area)=="")
   {
       alertALCON("Es necesario llenar el campo Nombre del Área","alert");
       document.getElementById("vc_nombre_area").focus();
       return false;
   }

   if(vc_descripcion_area == 0)
   {
       alertALCON("Es necesario llenar el campo Descripción del Área","alert");
       document.getElementById("vc_descripcion_area").focus();
       return false;
   }

   document.getElementById("textAvisoArea").style.display="block";

   $.ajax({
        type: 'post',
        url:'/SGDUNI/agregarAreaUnidadROF.uni',
        dataType: 'html',
        data:"in_codigo_registro="+in_codigo_registro+"&vc_nombre_area="+vc_nombre_area+"&vc_descripcion_area="+document.getElementById("vc_descripcion_area").value,
        success: function(data)
        {
            if(Number(data) == 1)
            {
              //document.getElementById("textAviso_rof_base").innerHTML = "";
              alertALCON("El Área fue guardado correctamente","alert");
              fnl_llamarGrillaAreas(in_codigo_registro);
              fnl_cancelar();
            }
            else
            {
                document.getElementById("textAvisoArea").innerHTML = "ERROR! No se puedo Guardar el Área";
            }
        },
        error: function(dato)
        {
            alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
            //precargaALCON("",true,0,"center");
            return false;
        }
    });

   return true;
}

function fnl_llamarPopupArea(idRegistro,nomUnidad)
{
   precargaALCON("Cargando...",true,1,"center");
    windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/agregarAreaAUnidad.uni','urlVar':{'idRegistro':'"+idRegistro+"','nomUnidad' : '"+nomUnidad+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
    {
        precargaALCON("",true,0,"center");
    });
}


function fnl_llamarGrillaAreas(idRegistro)
{
   var div = "lstAreas"+idRegistro
   $.ajax({
        type: 'post',
        url:'/SGDUNI/listarAreaSegunUnidad.uni',
        dataType: 'html',
        data:'idRegistro='+idRegistro,
        success: function(data)
        {
            document.getElementById(div).innerHTML = data;
        },
        error: function(dato)
        {
            alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
            //precargaALCON("",true,0,"center");
            return false;
        }

    });
}

function fnl_llamarGrillaRegistros(idRof)
{
   $.ajax({
        type: 'post',
        url:'/SGDUNI/listarRegistroROF.uni',
        dataType: 'html',
        data:'idRof='+idRof,
        success: function(data)
        {
            document.getElementById("lstRegistro").innerHTML = data;
        },
        error: function(dato)
        {
            alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
            //precargaALCON("",true,0,"center");
            return false;
        }

    });
}

//abrir pop up de base legal
function fnl_llamarPopupBaseLegal(idRof)
{
    precargaALCON("Cargando...",true,1,"center");
    windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/insertarBaseLegalROF.uni','urlVar':{'idRof':'"+idRof+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
    {
        precargaALCON("",true,0,"center");
    });
}

//abrir pop up de registro
function fnl_llamarPopupRegistro(idRof)
{
    precargaALCON("Cargando...",true,1,"center");
    windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'385','url':'/SGDUNI/insertarRegistroROF.uni','urlVar':{'idRof':'"+idRof+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
    {
        precargaALCON("",true,0,"center");
    });
}

//limpiar agregar base legal
function fnl_nuevo()
{
    document.getElementById("orden").selectedIndex = 0;
    document.getElementById("vc_descripcion").value = "";
    var objAviso = $(".textAviso").html();
	$(".textAviso").css("display","none");
}

function fnl_regiBaseLegal(in_codigo_rof)
{
       var orden = document.getElementById("orden");
       var descrip = document.getElementById("vc_descripcion").value;

       //alert("id = "+in_codigo_rof+", orden = "+orden.value+",desc = "+descrip );

       if(trim(in_codigo_rof) =="")
       {
           alertALCON("Necesita el Codigo del ROF para poder almacenar la Base Legal,intente nuevamente porfavor","alert");
          // forTr.vc_responsabilidad.focus();
           return false;
       }

       if(orden.selectedIndex == 0)
       {
           alertALCON("Seleccione una Letra como Orden","alert");
           document.getElementById("orden").focus();
           return false;
       }

       if(trim(descrip) =="")
       {
           alertALCON("Escriba la descripción de la Base Legal","alert");
           document.getElementById("vc_descripcion").focus();
           return false;
       }

           document.getElementById("textAviso_rof_base").style.display="block";
           $.ajax({
                type: 'post',
                url:'/SGDUNI/guardarBaseLegalROF.uni',
                dataType: 'html',
                data:"id_rof="+in_codigo_rof+"&orden="+orden.value+"&descripcion="+document.getElementById("vc_descripcion").value,
                success: function(data)
                {
                    if(Number(data) == 1)
                    {
                      //document.getElementById("textAviso_rof_base").innerHTML = "";
                      alertALCON("La Base Lega fue registrado correctamente","alert");
                      fnl_llamarGrillaBaseLegal(in_codigo_rof);
                      fnl_cancelar();
                    }
                    else
                    {
                        document.getElementById("textAviso_rof_base").innerHTML = "ERROR! No se puedo Guardar el Registro";
                    }
                },
                error: function(dato)
                {
                    alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
                    //precargaALCON("",true,0,"center");
                    return false;
                }
        });
     return true;
}

function fnl_llamarGrillaBaseLegal(idRof)
{
                   $.ajax({
						type: 'post',
                        url:'/SGDUNI/listarBaseLegalRof.uni',
						dataType: 'html',
                        data:'idRof='+idRof,
						success: function(data)
                        {
                            document.getElementById("lstBasesLegales").innerHTML = data;
						},
						error: function(dato)
                        {
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});

}

///FUNCION GENERAL
///

function fnl_llamarPopupFuncionesGenerales(idRof)
{
    precargaALCON("Cargando...",true,1,"center");
    windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/insertarFuncionesGenerales.uni','urlVar':{'idRof':'"+idRof+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
    {
        precargaALCON("",true,0,"center");
    });
}

function fnl_regiFuncionGeneral(in_codigo_rof)
{
       var orden = document.getElementById("orden");
       var descrip = document.getElementById("vc_descripcion").value;

       //alert("id = "+in_codigo_rof+", orden = "+orden.value+",desc = "+descrip );

       if(trim(in_codigo_rof) =="")
       {
           alertALCON("Necesita el Codigo del ROF para poder almacenar la Base Legal,intente nuevamente porfavor","alert");
          // forTr.vc_responsabilidad.focus();
           return false;
       }

       if(orden.selectedIndex == 0)
       {
           alertALCON("Seleccione una Letra como Orden","alert");
           document.getElementById("orden").focus();
           return false;
       }

       if(trim(descrip) =="")
       {
           alertALCON("Escriba la descripción de la Base Legal","alert");
           document.getElementById("vc_descripcion").focus();
           return false;
       }

           document.getElementById("textAviso_rof_base").style.display="block";
           $.ajax({
                type: 'post',
                url:'/SGDUNI/guardarFuncionesGenerales.uni',
                dataType: 'html',
                data:"id_rof="+in_codigo_rof+"&orden="+orden.value+"&descripcion="+document.getElementById("vc_descripcion").value,
                success: function(data)
                {
                    if(Number(data) == 1)
                    {
                      //document.getElementById("textAviso_rof_base").innerHTML = "";
                      alertALCON("La Función fue registrado correctamente","alert");
                      fnl_llamarGrillaFuncionesGenerales(in_codigo_rof);
                      fnl_cancelar();
                    }
                    else
                    {
                        alertALCON("ERROR! No se puedo Guardar la Función","alert");
                    }
                },
                error: function(dato)
                {
                    alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
                    //precargaALCON("",true,0,"center");
                    return false;
                }
        });
     return true;
}


function fnl_llamarGrillaFuncionesGenerales(idRof)
{
   $.ajax({
        type: 'post',
        url:'/SGDUNI/listarFuncionGeneral.uni',
        dataType: 'html',
        data:'idRof='+idRof,
        success: function(data)
        {
            document.getElementById("lstFuncionesGenerales").innerHTML = data;
        },
        error: function(dato)
        {
            alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
            //precargaALCON("",true,0,"center");
            return false;
        }

    });
}

function fnl_cancelar()
{
   CloseWindowPopUpAlcon();//cerrar popup
}

//CARGAR ESTRUCTURA

function fnl_adjuntar_archivo()
   {
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},  adjuntarOrganigramaROF
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'380','url':'/SGDUNI/adjuntarOrganigramaROF.uni','urlVar':{},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_select_cod(cod,nom)
   {

      document.getElementById("in_codigo_estructura").value = cod;
      document.getElementById("dvl_in_codigo_estructura").innerHTML = '<img src="fileproject/img/iconos_archivos/icoAdjunArchivo.gif" width="22" height="20" title="'+nom+'"/>';
      CloseWindowPopUpAlcon();//Cerrar PopUp funcion global
   }

   function fnl_llamarPopupObservaciones(idRof)
   {
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/observacionesRof.uni','urlVar':{'idRof':'"+idRof+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
        });
   }

    function fnl_regiObservacionROF()
    {
      var in_codigo_cap=document.getElementById("in_codigo_rof");
      var vc_observacion=document.getElementById("vc_observacion");
      var ckaprobarCa=document.getElementById("ckaprobar");
      var ckaprobar="0";
      if(ckaprobarCa)
      {
          if(ckaprobarCa.checked==true)
               ckaprobar="1";
      }

      if(trim(in_codigo_cap.value)==""){
          alertALCON("Se perdio el codigo del Rof, intente nuevamente!","alert");
          return false;
      }
      //
      if(trim(vc_observacion.value)==""){
          alertALCON("Escriba la observación del rof","alert");
          vc_observacion.focus();
          return false;
      }
                $.ajax({
						type: 'post',
                        url:'/SGDUNI/guardarObservacionROF.uni',
						dataType: 'html',
                        data:'in_codigo_rof='+in_codigo_cap.value+'&vc_observacion='+document.getElementById("vc_observacion").value+"&ckaprobar="+ckaprobar,
						success: function(data)
                        {
                           var objAvisod=document.getElementById("dvl_avisopm");
                           objAvisod.style.display="block";
                            if(Number(data)==1)
                            {
                                vc_observacion.value="";
                                objAvisod.innerHTML="Observacion registrada correctamente";
                            }
                            else
                            {
                               objAvisod.innerHTML="Ocurrio un error al intentar guardar los datos";
                            }
						},
						error: function(dato)
                        {
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
                    return true;
  }
  
  function fnl_ver_lista_observaciones( idRof )
  {
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '580','height':'400','url':'/SGDUNI/listarObservacionesROF.uni','urlVar':{'idRof':'"+idRof+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
   }
