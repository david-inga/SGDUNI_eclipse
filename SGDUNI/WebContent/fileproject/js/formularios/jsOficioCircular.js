//INI
$(document).ready(
function()
  {
  fnl_load_datos_combos();
  }
);

//Cargar Datos a los Combos
function fnl_load_datos_combos()
{
    $(".checkSelect").each(
    function(index)
    {//Recorremos todos los che que tengas esta clase (css)

       var StringTipo=$(this).attr("id");
       var arrayTipo=StringTipo.split("[");
       var  arrayTipoMAS=arrayTipo[1].split("]");
       var tipo=arrayTipo[0];
       var indexCK=arrayTipoMAS[0];
       var idFacuDep=$(this).val();
       $(this).click(function()
        {//Cuando haguen uso del evento change cada objeto checked           
            var objSelect="";
            
            if(String(tipo)=="ckFacultad")
            {
                fnl_ajax_combo_facultad(indexCK,idFacuDep);
               
            }
            else if(String(tipo)=="ckDependecia"  )
            {
                fnl_ajax_combo_dependencia(indexCK,idFacuDep);
            }
               
            
        });
      });

}
//
function fnl_ajax_combo_facultad(indexCK,idFAcu)
{

//alert("CODIGO FACULTAD :"+idFAcu);
objSelect=document.getElementById("receptorFac["+indexCK+"]");
objSelect.options[0]=new Option("Cargando...","");
$.ajax({
    type:'post',
    url:'/SGDUNI/cargarComboboxReceptor.uni',//talvez es porque no hay datos para ese fac
    dataType:'html',
    data:'in_codigo_depen_facu='+idFAcu+'&ch_tipo_depen_facu=f',//si ahora lo hago, asi como cargando...
    success:function(data)
    {
       var objJSON=eval(data);
       if(objJSON.length > 0)
       {
           objSelect.options[0]=new Option("Seleccione...","0");
           objSelect.options[0].selected=true;
           for(var t=0;t<objJSON.length;t++)
           {
               objSelect.options[(t + 1)] = new Option(objJSON[t].nombre,objJSON[t].codigo);
           }
       }
       else
       {
          objSelect.options[0]=new Option("Sin resultados","0");
          objSelect.options[0].selected=true;
       }
    }
});
  
}

function fnl_ajax_combo_dependencia(indexCK,idDepe)
{
      // alert("CODIGO DEPEN :"+idDepe);
    objSelect = document.getElementById("receptorDep["+indexCK+"]");
    objSelect.options[0]=new Option("Cargando...","");
    $.ajax(
      {
        type:'post',
        url:'/SGDUNI/cargarComboboxReceptor.uni',//talvez es porque no hay datos para ese fac
        dataType:'html',
        data:'in_codigo_depen_facu='+idDepe+'&ch_tipo_depen_facu=d',//si ahora lo hago, asi como cargando...
        success:function(data)
        {
           var objJSON=eval(data);
           if(objJSON.length>0)
           {
               objSelect.options[0] = new Option("Seleccione...","0");
               objSelect.options[0].selected=true;
               for(var t=0;t<objJSON.length;t++)
               {
                 objSelect.options[t+1] = new Option(objJSON[t].nombre,objJSON[t].codigo);
               }
           }
           else
           {
               objSelect.options[0]=new Option("Sin resultados","0");
               objSelect.options[0].selected=true;
           }
        }
      }
    );
}
//FOrmulario
function formEnviarOficioC()
{
    this.formEditarOficio=document.getElementById("frmEditarOficio");
    this.frmModificarOficio=document.getElementById("frmModificarOficio");
    this.in_codigo_oficio=document.getElementById("in_codigo_oficio");
    this.dt_fecha=document.getElementById("dt_fecha");
    this.vc_ciudad = document.getElementById("vc_ciudad");
    this.vc_nombre_anio = document.getElementById("vc_nombre_anio");
    this.vc_cuerpo_doc = document.getElementById("vc_cuerpo_doc");
    this.in_usuario_emisor=document.getElementById("in_usuario_emisor");
    this.ch_codigo_oficio = document.getElementById("ch_codigo_oficio");
}

//SOLO GUARDAR
function fnl_soloGuardarOficio()
{
    var formEnF = new formEnviarOficioC();

    if(trim(formEnF.ch_codigo_oficio.value)=="")
    {
      alertALCON("Escriba el Codigo del Oficio","alert");
      formEnF.ch_codigo_oficio.focus();
      return false;
    }

    if(trim(formEnF.dt_fecha.value)=="")
    {
      alertALCON("Seleccione la fecha","alert");
      formEnF.dt_fecha.focus();
      return false;
    }
    else
    {
         if(compare_dates(formEnF.dt_fecha.value,"","-"))
         {
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
          }
    }

    if(trim(formEnF.vc_ciudad.value)=="")
    {
      alertALCON("Escriba la Ciudad","alert");
      formEnF.vc_ciudad.focus();
      return false;
    }

    if(trim(formEnF.vc_nombre_anio.value)=="")
    {
      alertALCON("Escriba el nombre del Año","alert");
      formEnF.vc_nombre_anio.focus();
      return false;
    }

    if (formEnF.in_usuario_emisor.selectedIndex == 0 )
    {
       alertALCON("Debe seleccionar un Usuario Emisor", "alert");
       formEnF.in_usuario_emisor.focus();
       return false;
    }

    formEnF.formEditarOficio.action = "soloGuardarOficioCircular.uni";
    formEnF.formEditarOficio.submit();
    return true;
}

//ENVIA EL OFICIO A LA OCDO
function fnl_enviarOficioalJefeOCDO()
{
    var formEnF = new formEnviarOficioC();

    if(trim(formEnF.ch_codigo_oficio.value)=="")
    {
      alertALCON("Escriba el Codigo del Oficio","alert");
      formEnF.ch_codigo_oficio.focus();
      return false;
    }

    if(trim(formEnF.dt_fecha.value)=="")
    {
      alertALCON("Seleccione la fecha","alert");
      formEnF.dt_fecha.focus();
      return false;
    }
    else
    {
         if(compare_dates(formEnF.dt_fecha.value,"","-"))
         {
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
         }
    }

    if(trim(formEnF.vc_ciudad.value)=="")
    {
      alertALCON("Escriba la Ciudad","alert");
      formEnF.vc_ciudad.focus();
      return false;
    }

    if(trim(formEnF.vc_nombre_anio.value)=="")
    {
      alertALCON("Escriba el nombre del Año","alert");
      formEnF.vc_nombre_anio.focus();
      return false;
    }

    if (formEnF.in_usuario_emisor.selectedIndex == 0 )
    {
       alertALCON("Debe seleccionar un Usuario Emisor", "alert");
       formEnF.in_usuario_emisor.focus();
       return false;
    }

    formEnF.formEditarOficio.action = "guardarEnviarJefeOcdoOficioCircular.uni";
    formEnF.formEditarOficio.submit();
    return true;
}

// OFICIO GUARDADO Y APROBADO POR LA OCDO
function fnl_guardaryAprobarPorLaOCDO()
{
    var formEnF = new formEnviarOficioC();

    if(trim(formEnF.ch_codigo_oficio.value)=="")
    {
      alertALCON("Escriba el Codigo del Oficio","alert");
      formEnF.ch_codigo_oficio.focus();
      return false;
    }

    if(trim(formEnF.dt_fecha.value)=="")
    {
      alertALCON("Seleccione la fecha","alert");
      formEnF.dt_fecha.focus();
      return false;
    }
    else
    {
         if(compare_dates(formEnF.dt_fecha.value,"","-"))
         {
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
          }
    }

    if(trim(formEnF.vc_ciudad.value)=="")
    {
      alertALCON("Escriba la Ciudad","alert");
      formEnF.vc_ciudad.focus();
      return false;
    }

    if(trim(formEnF.vc_nombre_anio.value)=="")
    {
      alertALCON("Escriba el nombre del Año","alert");
      formEnF.vc_nombre_anio.focus();
      return false;
    }

    if (formEnF.in_usuario_emisor.selectedIndex == 0 )
    {
       alertALCON("Debe seleccionar un Usuario Emisor", "alert");
       formEnF.in_usuario_emisor.focus();
       return false;
    }

    formEnF.formEditarOficio.action = "guardaryAprobarOficio.uni";
    formEnF.formEditarOficio.submit();
    return true;
}

//GUARDAR Y ENVIAR
/*
function fnl_guardarOficioC()
{
    var formEnF = new formEnviarOficioC();

    if(trim(formEnF.dt_fecha.value)=="")
    {
      alertALCON("Seleccione la fecha","alert");
      formEnF.dt_fecha.focus();
      return false;
    }
    else
    {
         if(compare_dates(formEnF.dt_fecha.value,"","-"))
         {
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
          }
    }

    if(trim(formEnF.vc_ciudad.value)=="")
    {
      alertALCON("Escriba la Ciudad","alert");
      formEnF.vc_ciudad.focus();
      return false;
    }
    
    if(trim(formEnF.vc_nombre_anio.value)=="")
    {
      alertALCON("Escriba el nombre del Año","alert");
      formEnF.vc_nombre_anio.focus();
      return false;
    }

    if (formEnF.in_usuario_emisor.selectedIndex == 0 )
    {
       alertALCON("Debe seleccionar un Usuario Emisor", "alert");
       formEnF.in_usuario_emisor.focus();
       return false;
    }

    formEnF.formEditarOficio.submit();
    return true;
} */

//SOLO MODIFICA EL OFICIO
function fnl_soloModificarOficio()
{
    var formEnF = new formEnviarOficioC();

    if(trim(formEnF.dt_fecha.value)=="")
    {
      alertALCON("Seleccione la fecha","alert");
      formEnF.dt_fecha.focus();
      return false;
    }
    else
    {
         if(compare_dates(formEnF.dt_fecha.value,"","-"))
         {
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
          }
    }

    if(trim(formEnF.vc_ciudad.value)=="")
    {
      alertALCON("Escriba la Ciudad","alert");
      formEnF.vc_ciudad.focus();
      return false;
    }

    if(trim(formEnF.vc_nombre_anio.value)=="")
    {
      alertALCON("Escriba el nombre del Año","alert");
      formEnF.vc_nombre_anio.focus();
      return false;
    }

    if (formEnF.in_usuario_emisor.selectedIndex == 0 )
    {
       alertALCON("Debe seleccionar un Usuario Emisor", "alert");
       formEnF.in_usuario_emisor.focus();
       return false;
    }

    formEnF.frmModificarOficio.action = "soloModificarOficioCircular.uni";
    formEnF.frmModificarOficio.submit();
    return true;
}

//SOLO MODIFICA EL OFICIO
function fnl_ModificarOficioyEnviaJefeOCDO()
{
    var formEnF = new formEnviarOficioC();

    if(trim(formEnF.dt_fecha.value)=="")
    {
      alertALCON("Seleccione la fecha","alert");
      formEnF.dt_fecha.focus();
      return false;
    }
    else
    {
         if(compare_dates(formEnF.dt_fecha.value,"","-"))
         {
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
          }
    }

    if(trim(formEnF.vc_ciudad.value)=="")
    {
      alertALCON("Escriba la Ciudad","alert");
      formEnF.vc_ciudad.focus();
      return false;
    }

    if(trim(formEnF.vc_nombre_anio.value)=="")
    {
      alertALCON("Escriba el nombre del Año","alert");
      formEnF.vc_nombre_anio.focus();
      return false;
    }

    if (formEnF.in_usuario_emisor.selectedIndex == 0 )
    {
       alertALCON("Debe seleccionar un Usuario Emisor", "alert");
       formEnF.in_usuario_emisor.focus();
       return false;
    }

    formEnF.frmModificarOficio.action = "modificarOficioCircularyEnviarJefeOCDO.uni";
    formEnF.frmModificarOficio.submit();
    return true;
}

//MODIFICAR y APRUEBAR EL OFICIO
function fnl_ModificarAprobarOficio()
{
    var formEnF = new formEnviarOficioC();

    if(trim(formEnF.dt_fecha.value)=="")
    {
      alertALCON("Seleccione la fecha","alert");
      formEnF.dt_fecha.focus();
      return false;
    }
    else
    {
         if(compare_dates(formEnF.dt_fecha.value,"","-"))
         {
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
          }
    }

    if(trim(formEnF.vc_ciudad.value)=="")
    {
      alertALCON("Escriba la Ciudad","alert");
      formEnF.vc_ciudad.focus();
      return false;
    }

    if(trim(formEnF.vc_nombre_anio.value)=="")
    {
      alertALCON("Escriba el nombre del Año","alert");
      formEnF.vc_nombre_anio.focus();
      return false;
    }

    if (formEnF.in_usuario_emisor.selectedIndex == 0 )
    {
       alertALCON("Debe seleccionar un Usuario Emisor", "alert");
       formEnF.in_usuario_emisor.focus();
       return false;
    }

    formEnF.frmModificarOficio.action = "modificarAprobarOficio.uni";
    formEnF.frmModificarOficio.submit();
    return true;
}

//MODIFICA Y SIGUIENTE
function fnl_ModificarOficioC()
{
    var formEnF = new formEnviarOficioC();


    if(trim(formEnF.in_codigo_oficio.value)=="")
    {
      alertALCON("El ID es Nulo","alert");
      return false;
    }

    if(trim(formEnF.dt_fecha.value)=="")
    {
      alertALCON("Seleccione la fecha","alert");
      formEnF.dt_fecha.focus();
      return false;
    }
    else
    {
         if(compare_dates(formEnF.dt_fecha.value,"","-"))
         {
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
          }
    }

    if(trim(formEnF.vc_ciudad.value)=="")
    {
      alertALCON("Escriba la Ciudad","alert");
      formEnF.vc_ciudad.focus();
      return false;
    }
    
    if(trim(formEnF.vc_nombre_anio.value)=="")
    {
      alertALCON("Escriba el nombre del Año","alert");
      formEnF.vc_nombre_anio.focus();
      return false;
    }

    if (formEnF.in_usuario_emisor.selectedIndex == 0 )
    {
       alertALCON("Debe seleccionar un Usuario Emisor", "alert");
       formEnF.in_usuario_emisor.focus();
       return false;
    }

    formEnF.frmModificarOficio.submit();
    return true;
}







/* VER VISTA PREVIA DEL OFICIO*/
function fnl_ver_oficio(idEmisor,idOfc,idReceptor)
 {
   precargaALCON("Cargando...",true,1,"center");
   windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '700','height':'350','url':'/SGDUNI/oficioCircularVista.uni','urlVar':{'idOfc':'"+idOfc+"','idEmisor' : '"+idEmisor+"','idReceptor' : '"+idReceptor+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
   {
      precargaALCON("",true,0,"center");
   });
 }


 //SELECCIONA TODOSL OS CHECK FACULTAD Y DEPENDENCIA
function fnl_select_TodoDepFac()
{
    var ObjFcDep=document.getElementById("checkbckTodoDepFac");
    //
    var objFacul=document.getElementById("ckTodoFacultad");
    var numFacul=document.getElementById("txtNumFacultad");
    //
    var objDepen=document.getElementById("ckTodoDependencia");
    var numDepen=document.getElementById("txtNumDependecia");

    if(ObjFcDep.checked==true)
    {
       for(var f=0;f<numFacul.value;f++)
         document.getElementById("ckFacultad["+f+"]").checked=true;

       for(var d=0;d<numDepen.value;d++)
        document.getElementById("ckDependecia["+d+"]").checked=true;

       objDepen.checked=true;
       objFacul.checked=true;
    }
    else
    {
       for(var f=0;f<numFacul.value;f++)
         document.getElementById("ckFacultad["+f+"]").checked=false;
       for(var d=0;d<numDepen.value;d++)
        document.getElementById("ckDependecia["+d+"]").checked=false;

       objDepen.checked=false;
       objFacul.checked=false;
    }
    fnl_load_combos_facultad_this();//CARGA SOLO FACULTADA
    fnl_load_combos_dependencia_this();//CARGA SOLO DEPENDECIA
}

//Seleccionar todas las facultads
function fnl_selec_facultades()
{
 var objFacul=document.getElementById("ckTodoFacultad");
 var numFacul=document.getElementById("txtNumFacultad");
   if(objFacul.checked==true)
   {
     for(var f=0;f<numFacul.value;f++)
       document.getElementById("ckFacultad["+f+"]").checked=true;
   }else{
     for(var f=0;f<numFacul.value;f++)
       document.getElementById("ckFacultad["+f+"]").checked=false;
   }

   //Cargando los combos solo facultad
   fnl_load_combos_facultad_this();
}
//cargamos solo para facultada
function fnl_load_combos_facultad_this()
{   
    var numeroFacultades=document.getElementById("txtNumFacultad").value;   
    for(var i=0;i<numeroFacultades;i++)
    {
        objSelect=document.getElementById("receptorFac["+i+"]");
        if(objSelect)
        {
           objSelect.options[0]=new Option("Cargando...","");
        }
    }
}

//SELECCIONA TODOS LOS CHECK DE LAS DEPENDENCIAS
function fnl_selec_dependencias()
{
 var objDepen=document.getElementById("ckTodoDependencia");
 var numDepen=document.getElementById("txtNumDependecia");
    if(objDepen.checked==true)
    {
      for(var d=0;d<numDepen.value;d++)
        document.getElementById("ckDependecia["+d+"]").checked=true;

    }
    else
    {
       for(var d=0;d<numDepen.value;d++)
        document.getElementById("ckDependecia["+d+"]").checked=false;
    }
    fnl_load_combos_dependencia_this();
}

//cargamos solo para facultada
function fnl_load_combos_dependencia_this()
{
    var numeroDependnecias = document.getElementById("txtNumDependecia").value;
    for(var i=0;i<numeroDependnecias;i++)
    {
        objSelect = document.getElementById("receptorDep["+i+"]");
        if(objSelect)
        {
           objSelect.options[0]=new Option("Cargando...","");
        }
    }
}

//AL SELECIONAR ALGUN CHECK DEPENDENCIA - DESACTIVA LOS CHECK TODOS Y FAC Y DEPEDN
function fnl_chekThisDep(objCk)
{
  var ObjFcDep=document.getElementById("checkbckTodoDepFac");
  var objDepen=document.getElementById("ckTodoDependencia");
  if(objCk.checked==false)
  {
      ObjFcDep.checked=false;
      objDepen.checked=false;
  }
}

//AL SELECIONAR ALGUN CHECK FACULTAD - DESACTIVA LOS CHECK TODOS Y FAC Y DEPEND
function fnl_chekThisFac(objCk)
{
  var ObjFcDep=document.getElementById("checkbckTodoDepFac");
  var objFacul=document.getElementById("ckTodoFacultad");

  if(objCk.checked==false)
  {
      ObjFcDep.checked=false;
      objFacul.checked=false;
  }
}

//funcion para enviar
function fnl_enviarListaOficioC()
{
    var formEnF=new formEnviarOficioC();

    if(trim(formEnF.in_codigo_oficio.value)=="")
    {
      alertALCON("No se encontro el Identificador del Oficio, intente nuevamente","alert");
      formEnF.ch_codigo_oficio.focus();
      return false;
    }

    //VALIDANDO LA LISTA DE DEPENDECIAS Y/o FACULTADES
    var numDepen = document.getElementById("txtNumDependecia").value;
    var numFacul = document.getElementById("txtNumFacultad").value;
    var numCkheckFacu=0;
    var numCkheckDepe=0;

    for(var fa=0;fa<numFacul;fa++)
    {//recorremos las facultades
      var objChecFacu = document.getElementById("ckFacultad["+fa+"]");
         if(objChecFacu.checked == true)
               numCkheckFacu++;
    }

    for(var depe=0;depe<numDepen;depe++)
    {//recorremos las dependecias
      var objChecDepe=document.getElementById("ckDependecia["+depe+"]");
         if(objChecDepe.checked==true)
               numCkheckDepe++;
    }
    if(numCkheckFacu == 0 && numCkheckDepe == 0)
     {
         alertALCON("Seleccione como minimo una Dependecia ó Facultad","alert");
         return false;
     }

    if(!confirm("¿Está seguro de enviar el Oficio Circular?")){
        return false;
    }
    
    //document.getElementById("frmEnviar").action="oficioCircularEnviar.uni";//Action
    document.getElementById("frmEnviar").submit();
}

//FORMULARIO ESCOGER DESTINATARIO
function fnl_enviarOficioC()
{
    var objHTMLenviar=document.getElementById("dvl_enviar_of_c");
    precargaALCON("Cargando...",true,1,"center");
      $.ajax({
            type: 'post',
            url:'/SGDUNI/oficioCircularEnviarNuevo.uni',
            dataType: 'html',
            success: function(data)
            {
                objHTMLenviar.innerHTML=data;
                ShowHide("dvl_enviar_of_c",true,1);
                precargaALCON("",true,0,"center");
                setTimeout("document.getElementById('cmdEnviarPorTipoOficoCircular').scrollIntoView(true)",400);
            },
            error: function(dato){
                alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
                precargaALCON("",true,0,"center");
                return false;
            }
        });
     return true;
}

 function cargarComboboxReceptor(numSelect,tipo,idFacDep)
 {
   var s_receptor = document.getElementById("receptor["+numSelect+"]");
   $.ajax({
            type: 'post',
            url:'/SGDUNI/listarObjetivosDirectivas.uni',
            dataType: 'html',
            data:'tipo='+tipo+"&idFacDep="+idFacDep,
            success: function(data)
            {
                document.getElementById(s_receptor).innerHTML = data;
            },
            error: function(dato)
            {
                alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
                return false;
            }
        });
  }


  function fnl_aprobar_oficio_por_ocdo( in_cod_oficio )
  {
        var r=confirm("  ¿Desea aprobar el Oficio Circular?  ");
        if (r==true)
          {
             $.ajax({
                type: 'post',
                url:'/SGDUNI/aprobarPorLaJefeOcdo.uni',
                dataType: 'html',
                data:'in_cod_oficio='+in_cod_oficio+'&in_estado=42',
                success: function(data)
                {
                    if(Number(data)==1) {
                      alertALCON("El Oficio fue Aprobado Correctamente!","alert");
                      location.reload();
                      return true;
                    }
                    else {
                      alertALCON("Error al Aprobar el Oficio, intente nuevamente ","alert");
                      return true;
                    }
                },
                error: function(dato)
                {
                    alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias; "+dato,"alert");
                    return false;
                }
            });
          }
        else
          {
           
          }
  }

  function fnl_desaprobar_oficio_por_ocdo( in_cod_oficio )
  {
        var r=confirm("  ¿Desea desaprobar el Oficio Circular?  ");
        if (r==true)
          {
             $.ajax({
                type: 'post',
                url:'/SGDUNI/aprobarPorLaJefeOcdo.uni',
                dataType: 'html',
                data:'in_cod_oficio='+in_cod_oficio+'&in_estado=41',
                success: function(data)
                {
                    if(Number(data)==1)
                    {
                      alertALCON("El Oficio Circular fue Desaprobado","alert");
                      location.reload();
                      return true;
                    }
                    else
                    {
                      alertALCON("Error al Desaprobar el Oficio, intente nuevamente ","alert");
                      return true;
                    }
                },
                error: function(dato)
                {
                    alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias; "+dato,"alert");
                    return false;
                }
            });
          }
        else
          {

          }
  }

//llamar formulario para agregar un comentario
  function fnl_llamarPopupObservacionesOficio(idOficio)
   {
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '580','height':'400','url':'/SGDUNI/crearNuevaObservacionOficio.uni','urlVar':{'idOficio':'"+idOficio+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
        });
   }

   //registrar un comentario
   function fnl_regiObservacionOficio()
    {
      var in_codigo_oficio = document.getElementById("in_codigo_oficio");
      var vc_observacion = document.getElementById("vc_observacion");

      if(trim(in_codigo_oficio.value) == "")
      {
          alertALCON("Se perdio el codigo del Oficio, intente nuevamente!","alert");
          return false;
      }
      //
      if(trim(vc_observacion.value)=="")
      {
          alertALCON("Escriba la observación para el Oficio","alert");
          vc_observacion.focus();
          return false;
      }
        $.ajax({
            type: 'post',
            url:'/SGDUNI/almacenarObservacionOficio.uni',
            dataType: 'html',
            data:'in_codigo_oficio='+in_codigo_oficio.value+'&vc_observacion='+document.getElementById("vc_observacion").value,
            success: function(data)
            {
               var objAvisod=document.getElementById("dvl_avisooficio");
               objAvisod.style.display="block";
                if(Number(data)==1)
                {
                    vc_observacion.value="";
                    objAvisod.innerHTML="La Observación fue agregada correctamente";
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

  function fnl_ver_lista_observaciones_oficio( idOficio,validarNuevo )
  {
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '580','height':'400','url':'/SGDUNI/listarObservacionesOficio.uni','urlVar':{'idOficio':'"+idOficio+"', 'validarNuevo':'"+validarNuevo+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
        });
   }

    function fnl_cancelar()
    {
       CloseWindowPopUpAlcon();//cerrar popup
    }




