/* 
 * FUNCIONES JS PARA EL FORMULARIO DE DIRECTIVAS
 */

function form()
   {
       this.ch_codigo_directiva  =  document.getElementById("ch_codigo_directiva");
       this.dt_fecha             =  document.getElementById("dt_fecha");
       this.vc_alcance           =   document.getElementById("vc_alcance");
       this.vc_responsabilidad  =   document.getElementById("vc_responsabilidad");
       this.ch_estado  =   document.getElementById("ch_estado");
       this.formDirectiva        =   document.getElementById("frmRegiDirectiva");
   }


function fnl_nuevaDirectiva()
   {
       document.getElementById("ch_codigo_directiva").disabled =false;
       document.getElementById("ch_codigo_directiva").value ="";
       document.getElementById("ch_codigo_directiva").focus();
       document.getElementById("dt_fecha").value ="";
       document.getElementById("dt_fecha").disabled =false;
       document.getElementById("vc_alcance").value ="";
       document.getElementById("vc_alcance").disabled =false;
       document.getElementById("vc_responsabilidad").value ="";
       document.getElementById("vc_responsabilidad").disabled =false;
       document.getElementById("ch_estado").selectedIndex = 0;
       document.getElementById("ch_estado").disabled =false;
       var objAvios=$(".textAviso").html();
	   $(".textAviso").css("display","none");
       return true;
   }



function fnl_regiDirectivaUsuario()
   {
       var forTr=new form();
	   if(trim(forTr.ch_codigo_directiva.value)=="")
       {
           alertALCON("Escriba el codigo","alert");
           forTr.ch_codigo_directiva.focus();
           return false;

       }

       if(trim(forTr.dt_fecha.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           forTr.dt_fecha.focus();
           return false;
       }
       else
       {
           if(compare_dates(trim(forTr.dt_fecha.value),"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               forTr.dt_fecha.focus();
               return false;
           }
       }
       //
       if(trim(forTr.vc_alcance.value)==""){
           alertALCON("Escriba el Alcance","alert");
           forTr.vc_alcance.focus();
           return false;
       }

       if(trim(forTr.vc_responsabilidad.value)==""){
           alertALCON("Escriba la Responsabilidad","alert");
           forTr.vc_responsabilidad.focus();
           return false;
       }

       var estado = trim(document.getElementById("ch_estado").value);

       //alert('ch_codigo_directiva='+forTr.ch_codigo_directiva.value+"&dt_fecha="+trim(forTr.dt_fecha.value)+"&vc_alcance="+forTr.vc_alcance.value+"&vc_responsabilidad="+trim(forTr.vc_responsabilidad.value)+"&ch_estado="+forTr.ch_estado.selectedIndex);
           document.getElementById("textAviso_direc").style.display="block";
           $.ajax({
						type: 'post',
                        url:'/SGDUNI/insertarDirectivas.uni',
						dataType: 'html',
                        data:'ch_codigo_directiva='+forTr.ch_codigo_directiva.value+"&dt_fecha="+trim(forTr.dt_fecha.value)+"&vc_alcance="+forTr.vc_alcance.value+"&vc_responsabilidad="+trim(forTr.vc_responsabilidad.value)+"&ch_estado="+estado,
                        success: function(data)
                        {
                            if(Number(data) == 1)
                            {
                              document.getElementById("textAviso_direc").innerHTML="La Directiva se guardado correctamente Ahora Agrege los detalles";
                            }
                            else
                            {
                                document.getElementById("textAviso_direc").innerHTML="Error! ocurrio un error al registrar la directiva";
                            }
						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							return false;
						}

					});
      return true;
   }


function fnl_regiDirectiva()
   {
       var forTr=new form();
	   if(trim(forTr.ch_codigo_directiva.value)=="")
       {
           alertALCON("Escriba el codigo","alert");
           forTr.ch_codigo_directiva.focus();
           return false;

       }

       if(trim(forTr.dt_fecha.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           forTr.dt_fecha.focus();
           return false;
       }
       else
       {
           if(compare_dates(trim(forTr.dt_fecha.value),"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               forTr.dt_fecha.focus();
               return false;
           }
       }
       //
       if(trim(forTr.vc_alcance.value)==""){
           alertALCON("Escriba el Alcance","alert");
           forTr.vc_alcance.focus();
           return false;
       }

       if(trim(forTr.vc_responsabilidad.value)==""){
           alertALCON("Escriba la Responsabilidad","alert");
           forTr.vc_responsabilidad.focus();
           return false;
       }
      
      if (forTr.ch_estado.selectedIndex == 0)
      {
       alertALCON("Debe seleccionar un Estado", "alert");
       forTr.ch_estado.focus();
       return false;
      }
       //alert('ch_codigo_directiva='+forTr.ch_codigo_directiva.value+"&dt_fecha="+trim(forTr.dt_fecha.value)+"&vc_alcance="+forTr.vc_alcance.value+"&vc_responsabilidad="+trim(forTr.vc_responsabilidad.value)+"&ch_estado="+forTr.ch_estado.selectedIndex);
           document.getElementById("textAviso_direc").style.display="block";
           $.ajax({
						type: 'post',
                        url:'/SGDUNI/insertarDirectivas.uni',
						dataType: 'html',
                        data:'ch_codigo_directiva='+forTr.ch_codigo_directiva.value+"&dt_fecha="+trim(forTr.dt_fecha.value)+"&vc_alcance="+forTr.vc_alcance.value+"&vc_responsabilidad="+trim(forTr.vc_responsabilidad.value)+"&ch_estado="+forTr.ch_estado.selectedIndex,
                        success: function(data)
                        {
                            if(Number(data) == 1)
                            {
                              document.getElementById("textAviso_direc").innerHTML="La Directiva se guardado correctamente Ahora Agrege los detalles";
                            }else{
                                document.getElementById("textAviso_direc").innerHTML="Error! ocurrio un error al registrar la directiva";
                            }

						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
      return true;
   }

//abrir pop up de objetivos
   function fnl_llamarPopupObjetivo()
   {
       var codDir = trim(document.getElementById("ch_codigo_directiva").value);// 'codDir':'"+codDir+"',

        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/insertarObjetivoDirectivas.uni','urlVar':{'codDir':'"+codDir+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_llamarPopupNormaGeneral()
   {
       var codDir = trim(document.getElementById("ch_codigo_directiva").value);

        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/llamarFormInsertarNormaGenDirectivas.uni','urlVar':{'codDir':'"+codDir+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_llamarPopupProcedimiento()
   {
        var codDir = trim(document.getElementById("ch_codigo_directiva").value);

        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '500','height':'550','url':'/SGDUNI/llamarFormInsertProcedimientoDirectivas.uni','urlVar':{'codDir':'"+codDir+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_llamarPopupBaseLegal()
   {
       var codDir = trim(document.getElementById("ch_codigo_directiva").value);

        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/llamarFormInsertBaseLegalDirectivas.uni','urlVar':{'codDir':'"+codDir+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_cancelar(){
       CloseWindowPopUpAlcon();//cerrar popup
   }


   function fnl_regiObjetivoDir(idDir)
   {
       //var cod_dirc=document.getElementById("in_codigo_directiva").value;
       var descrip=document.getElementById("vc_descripcion").value;
       if(trim(idDir) =="")
       {
           alertALCON("Necesita el Codigo de la Directiva para poder almacenar el objetivo","alert");
          // forTr.vc_responsabilidad.focus();
           return false;
       }

       if(trim(descrip) =="")
       {
           alertALCON("Escriba el Objetivo","alert");
           document.getElementById("vc_descripcion").focus();
           return false;
       }
           //alert(idDir);
           document.getElementById("textAviso_direc_obj").style.display="block";
           $.ajax({
						type: 'post',
                        url:'/SGDUNI/guardarObjetivoDirectivas.uni',
						dataType: 'html',
                        data:'cod_dirc='+idDir+"&descrip="+descrip,
						success: function(data)
                        {
                            if(Number(data) == 1)
                            {
                              document.getElementById("textAviso_direc_obj").innerHTML="Objetivos registrados correctamente";
                              fnl_llamarGrillaDirecObjetivos(idDir);
                            }else{
                                document.getElementById("textAviso_direc_obj").innerHTML="ERROR!!!";
                            }

						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
      return true;
   }
//
function fnl_llamarGrillaDirecObjetivos(coddirec)
{
                   $.ajax({
						type: 'post',
                        url:'/SGDUNI/listarObjetivosDirectivas.uni',
						dataType: 'html',
                        data:'cod_dirc='+coddirec,
						success: function(data)
                        {
                            document.getElementById("dvl_miprimeravez").innerHTML=data;
						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
             
}

function fnl_nuevo(){
    document.getElementById("vc_descripcion").value = "";
    var objAvios=$(".textAviso").html();
	$(".textAviso").css("display","none");
}
//
   function fnl_regiNormaGenDir()
   {
       var cod_dirc=document.getElementById("in_codigo_directiva").value;
       var descrip=document.getElementById("vc_descripcion").value;
       
       if(trim(cod_dirc) =="")
       {
           alertALCON("Necesita el Codigo de la Directiva para poder almacenar la Norma","alert");
          // forTr.vc_responsabilidad.focus();
           return false;
       }

       if(trim(descrip) =="")
       {
           alertALCON("Escriba la Norma","alert");
           document.getElementById("vc_descripcion").focus();
           return false;
       }
            //alert(cod_dirc);
           document.getElementById("textAviso_direc_nor").style.display="block";
           $.ajax({
						type: 'post',
                        url:'/SGDUNI/guardarNormaGenDirectiva.uni',
						dataType: 'html',
                        data:'cod_dirc='+cod_dirc+"&descrip="+descrip,
						success: function(data)
                        {
                            if(Number(data)==1)
                            {
                              document.getElementById("textAviso_direc_nor").innerHTML="Norma registrado correctamente";
                              fnl_llamarGrillaDirecNormaGen(cod_dirc);
                            }else{
                                document.getElementById("textAviso_direc_nor").innerHTML="ERROR! No se pudo guardar la norma";
                            }

						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
      return true;
   }


   function fnl_llamarGrillaDirecNormaGen(coddirec)
{
                   $.ajax({
						type: 'post',
                        url:'/SGDUNI/listarNormaGenDirectivas.uni',
						dataType: 'html',
                        data:'cod_dirc='+coddirec,
						success: function(data)
                        {
                            document.getElementById("dvl_miprimeravez").innerHTML=data;
						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
}



   function fnl_regiNormaBaseLegal()
   {
       var cod_dirc=document.getElementById("in_codigo_directiva").value;
       var descrip=document.getElementById("vc_descripcion").value;

       if(trim(cod_dirc) =="")
       {
           alertALCON("Necesita el Codigo de la Directiva para poder almacenar la Norma","alert");
          // forTr.vc_responsabilidad.focus();
           return false;
       }

       if(trim(descrip) =="")
       {
           alertALCON("Escriba la Base Legal","alert");
           forTr.descrip.focus();
           return false;
       }
           //alert(cod_dirc);
           document.getElementById("textAviso_direc_base").style.display="block";
           $.ajax({
						type: 'post',
                        url:'/SGDUNI/guardarBaseLegalDirectivas.uni',
						dataType: 'html',
                        data:'cod_dirc='+cod_dirc+"&descrip="+descrip,
						success: function(data)
                        {
                            if(Number(data)==1)
                            {
                              document.getElementById("textAviso_direc_base").innerHTML="Base Legal registrado correctamente";
                              fnl_llamarGrillaDirecBaseLegal(cod_dirc);
                            }else{
                                document.getElementById("textAviso_direc_base").innerHTML="ERROR! No se pudo guardar la norma";
                            }

						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
      return true;
   }


   function fnl_llamarGrillaProcDir(coddirec)
   {
       $.ajax({
						type: 'post',
                        url:'/SGDUNI/listarProcedimientosDirectivas.uni',
						dataType: 'html',
                        data:'cod_dirc='+coddirec,
						success: function(data)
                        {
                            document.getElementById("dvl_miprimeravez").innerHTML=data;
						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
   }

      function fnl_llamarGrillaDirecBaseLegal(coddirec)
{
                   $.ajax({
						type: 'post',
                        url:'/SGDUNI/listarBaseLegalDirectivas.uni',
						dataType: 'html',
                        data:'cod_dirc='+coddirec,
						success: function(data)
                        {
                            document.getElementById("dvl_miprimeravez").innerHTML=data;
						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
}

function fnl_NuevoProcedimiento()
{
  document.getElementById("in_codigo_procedimiento").value = 0;
  var objAvios=$(".textAviso").html();
  $(".textAviso").css("display","none");
}

   function fnl_regiProcedimiento()
   {
       var idDir = document.getElementById("in_codigo_directiva").value;
       var idProc = document.getElementById("in_codigo_procedimiento").value;
       
       if(trim(idDir) == "")
       {
           alertALCON("Necesita el Codigo de la Directiva para poder almacenar el objetivo","alert");
           return false;
       }

      if (document.getElementById("in_codigo_procedimiento").selectedIndex == 0)
      {
       alertALCON("Debe seleccionar un Procedimiento", "alert");
       document.getElementById("in_codigo_procedimiento").focus();
       return false;
      }
      
       document.getElementById("textAviso_direc_pro").style.display="block";
           $.ajax({
						type: 'post',
                        url:'/SGDUNI/insertarProcedimientoDirectivas.uni',
						dataType: 'html',
                        data:'idDir='+idDir+"&idProc="+idProc,
						success: function(data)
                        {
                            if(Number(data)==1)
                            {
                              document.getElementById("textAviso_direc_pro").innerHTML="Procedimiento registrado correctamente";
                              fnl_llamarGrillaProcDir(idDir);
                            }else{
                                document.getElementById("textAviso_direc_pro").innerHTML="ERROR! No se pudo guardar el Procedimiento";
                            }

						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
      return true;
   }

   function fnl_llamarPopupVerObjetivos(codDir)
   {
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '800','height':'350','url':'/SGDUNI/listarObjetivosDirectivas.uni','urlVar':{'cod_dirc':'"+codDir+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_llamarPopupVerNormasGen(codDir)
   {
      precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '800','height':'350','url':'/SGDUNI/listarNormaGenDirectivas.uni','urlVar':{'cod_dirc':'"+codDir+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_llamarPopupVerBaseLegal(codDir)
   {
       precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '800','height':'350','url':'/SGDUNI/listarBaseLegalDirectivas.uni','urlVar':{'cod_dirc':'"+codDir+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_llamarPopupVerProcedimiento(codDir)
   {
       precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '450','height':'280','url':'/SGDUNI/listarProcedimientosDirectivas.uni','urlVar':{'cod_dirc':'"+codDir+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_cancelar()
   {
     CloseWindowPopUpAlcon();//cerrar popup
   }



