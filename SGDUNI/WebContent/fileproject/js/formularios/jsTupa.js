function form()
   {
       this.ch_codigo_tupa       =   document.getElementById("ch_codigo_tupa");
       this.dt_fecha             =   document.getElementById("dt_fecha");
       this.in_cod_depend_fac    =   document.getElementById("in_cod_depend_fac");
       this.ch_tipo_depend_fac   =   document.getElementById("ch_tipo_depend_fac");
       this.ch_estado            =   document.getElementById("ch_estado");
       this.lstDependencia       =   document.getElementById("lstDependencia");
       this.lstFacultad          =   document.getElementById("lstFacultad");
       this.formCap              =   document.getElementById("frmRegiTupa");
   }

   function fnl_nuevoDetalle()
   {
       document.getElementById("in_codigo_procedimiento").value = 0;
       document.getElementById("in_codigo_req").value = 0;
      var objAvios=$(".textAviso").html();
	   $(".textAviso").css("display","none");
       return true;
   }

   function fnl_regiDetalle()
   {
       var cod_tupa = document.getElementById("in_codigo_tupa");
       var procedimiento=document.getElementById("in_codigo_procedimiento").value;
       var requisito=document.getElementById("in_codigo_req").value;
       //alert(cod_tupa.value);
       if(trim(cod_tupa.value) == "0" )
       {
           alertALCON("Necesita el Codigo del tupa para poder almacenar el detalle","alert");
          // forTr.vc_responsabilidad.focus();
           return false;
       }

       if(document.getElementById("in_codigo_procedimiento").selectedIndex == 0 )
       {
           alertALCON("Seleccione el Procedimiento","alert");
           procedimiento.focus();
           return false;
       }

       if(document.getElementById("in_codigo_req").selectedIndex == 0 )
       {
           alertALCON("Seleccione Requisito","alert");
           requisito.focus();
           return false;
       }

           document.getElementById("textAviso").style.display="block";
           $.ajax({
						type: 'post',
                        url:'/SGDUNI/insertarDetalleTupa.uni',
						dataType: 'html',
                        data:'codTupa='+trim(cod_tupa.value)+"&codProc="+procedimiento+"&codReq="+requisito,
						success: function(data)
                        {
                            if(Number(data)==1)
                            {
                              document.getElementById("textAviso").innerHTML="Detalle registrado correctamente";
                              fnl_llamarGrillaListaDetalle(trim(cod_tupa.value));
                            }
                            else
                            {
                                document.getElementById("textAviso").innerHTML="ERROR! No se pudo guardar el Detalle, <BR/> Puede ser porque esta repitiendo los mismos datos";
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

   function fnl_llamarGrillaListaDetalle(tupa)
   {
       //alert(tupa);
                   $.ajax({
						type: 'post',
                        url:'/SGDUNI/listaRequisitoSegunProcTupa.uni',
						dataType: 'html',
                        data:'idTupa='+tupa,
						success: function(data)
                        {
                            document.getElementById("dvl_miprimeravez").innerHTML = data;
						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
   }

   function fnl_regiTupa()
   {
       var forTr=new form();
       
	   if(trim(forTr.ch_codigo_tupa.value)=="")
       {
           alertALCON("Escriba el codigo","alert");
           forTr.ch_codigo_tupa.focus();
           return false; 

       }

       if(trim(forTr.dt_fecha.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           forTr.dt_fecha.focus();
           return false;
       }
       //

       var numChec=document.forms[0].ch_tipo_depend_fac.length;
       var nuCheck=0;
       var txtCheck="";
       for(var i=0;i<numChec;i++)
       {
          if(document.forms[0].ch_tipo_depend_fac[i].checked==true)
          {
             nuCheck++;
             txtCheck=document.forms[0].ch_tipo_depend_fac[i].value;
          }
       }
       if(nuCheck==0)
       {
          alertALCON("Seleccione para quien va dirigido","alert");
          return false;
       }

       if(txtCheck=="f")
       {//facultad
           if(forTr.lstFacultad.value == "0" || trim(forTr.lstFacultad.value)=="" || forTr.in_cod_depend_fac.value=="0" || trim(forTr.in_cod_depend_fac.value)=="")
           {
               alertALCON("Seleccione la Facultad","alert");
               forTr.lstFacultad.focus();
               return false;
           }
       }
       else if(txtCheck=="d")
       {//dependencia
            if(forTr.lstDependencia.value=="0" || trim(forTr.lstDependencia.value)=="" || forTr.in_cod_depend_fac.value=="0" || trim(forTr.in_cod_depend_fac.value)=="")
            {
               alertALCON("Seleccione la Dependecia","alert");
               forTr.lstDependencia.focus();
               return false;
            }
       }

      document.getElementById("frmRegiTupa").submit();

      return true;
   }

   function fnl_regiTupaUsuario()
   {
       var forTr=new form();

	   if(trim(forTr.ch_codigo_tupa.value)=="")
       {
           alertALCON("Escriba el codigo","alert");
           forTr.ch_codigo_tupa.focus();
           return false;

       }

       if(trim(forTr.dt_fecha.value)=="")
       {
           alertALCON("Seleccione la Fecha","alert");
           forTr.dt_fecha.focus();
           return false;
       }
       //
       if(trim(document.getElementById("in_cod_depend_fac").value)=="")
       {
           alertALCON("Seleccione la Facultad","alert");
           document.getElementById("in_cod_depend_fac").focus();
           return false;
       }
       //
       if(trim(document.getElementById("ch_tipo_depend_fac").value)=="")
       {
           alertALCON("Seleccione la Fecha","alert");
           document.getElementById("ch_tipo_depend_fac").focus();
           return false;
       }
       //
       if(trim(document.getElementById("ch_estado").value)=="")
       {
           alertALCON("Seleccione la Fecha","alert");
           document.getElementById("ch_estado").focus();
           return false;
       }
       //
      document.getElementById("frmRegiTupa").submit();
      return true;
   }

   function fnl_nuevoTupa()
   {
       document.getElementById("ch_codigo_tupa").disabled =false;
       document.getElementById("ch_codigo_tupa").value ="";
       document.getElementById("ch_codigo_tupa").focus();
       document.getElementById("dt_fecha").value ="";
       document.getElementById("dt_fecha").disabled =false;
       document.getElementById("lstDependencia").selectedIndex = 0;
       document.getElementById("lstDependencia").disabled =false;
       document.getElementById("lstFacultad").selectedIndex = 0;
       document.getElementById("lstFacultad").disabled =false;
       document.getElementById("ch_estado").selectedIndex = 0;
       document.getElementById("ch_estado").disabled =false;
       var objAvios = $(".textAviso").html();
	   $(".textAviso").css("display","none");

       return true;
   }

   function fnl_nuevoSubDetalleTupa()
   {
       document.getElementById("de_tramitacion_porc").value ="0";
       document.getElementById("de_tramitacion_porc").focus();
       document.getElementById("de_tramitacion_sol").value ="0";
       document.getElementById("txt_evaluacion_previa").value =

       document.getElementById("ch_evaluacion_previa").value = 0;

       document.getElementById("in_plazo_resolver_dias").value = "";
       document.getElementById("vc_inicio_procedimiento").value ="";
       document.getElementById("in_autoridad_com_resolver").selectedIndex = 0;
       document.getElementById("vc_reconsideracion").value = "";
       document.getElementById("vc_apelacion").value = "";

       var objAvios = $(".textAviso").html();
	   $(".textAviso").css("display","none");

       return true;
   }


   var tipoFacDep;
   function fnl_Fact_Dep(val)
    {
      var forTr=new form();
       if(String(val)!="")
       {
               if(val=="f")
               {
                   forTr.lstDependencia.style.display='none';
                   forTr.lstFacultad.style.display='block';
                   tipoFacDep = "f";
               }
               else if(val=="d")
               {
                   forTr.lstFacultad.style.display='none';
                   forTr.lstDependencia.style.display='block';
                   tipoFacDep = "d";
               }else
               {
                   alertALCON("Tipo no definido","alert");
               }
       }
       else
       {
                   alertALCON("Tipo no definido","alert");
       }
   }

   function fnl_cancelar()
   {
     CloseWindowPopUpAlcon();//cerrar popup
   }

   function fnl_In_Depend_Fac(val)
   {
      var forTra=new form();
      forTra.in_cod_depend_fac.value=val;
   }

   function fnl_llamarPopupDetalle(idTupa)
   {
       var codTupa = document.getElementById("ch_codigo_tupa").value;

       // alert('id tupa: '+idTupa+" - cod tupa : "+codTupa);

        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/llamarInsertDetalleTupa.uni','urlVar':{'codTupa':'"+codTupa+"','idTupa':'"+idTupa+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_llamarPopupDetalleAdd(idTupa,codTupa)
   {
      // var codTupa = document.getElementById("ch_codigo_tupa").value;

        //alert('id tupa: '+idTupa+" - cod tupa : "+codTupa);

        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/llamarInsertDetalleTupa.uni','urlVar':{'codTupa':'"+codTupa+"','idTupa':'"+idTupa+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_llamarGrillaDetalle(idTupaDet)
   {

        //alert('id tupa Det: '+idTupaDet);
        precargaALCON("Cargando...",true,1,"center");  //'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '1000','height':'600','url':'/SGDUNI/llamarSubDetInsertTupa.uni','urlVar':{'idTupaDet':'"+idTupaDet+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
   }

  function fnl_GuardarSubDetalle()
  {
      //alert("num metodo fnl_GuardarSubDetalle");

       var in_codigo_det   =   document.getElementById("in_codigo_det").value;
       var de_tramitacion_porc = document.getElementById("de_tramitacion_porc").value;
       var de_tramitacion_sol = document.getElementById("de_tramitacion_sol").value;
       var in_plazo_resolver_dias = document.getElementById("in_plazo_resolver_dias").value;
       var vc_inicio_procedimiento = document.getElementById("vc_inicio_procedimiento").value;
       var in_autoridad_com_resolver = document.getElementById("in_autoridad_com_resolver").value;
       var vc_reconsideracion = document.getElementById("vc_reconsideracion").value;
       var vc_apelacion = document.getElementById("vc_apelacion").value;

       var numChec = document.forms[1].ch_evaluacion_previa.length;       
       var nuCheck=0;
       var txtCheck="";
       for(var i=0;i<numChec;i++)
       {
       }
       var evaluacion_previa = document.getElementById("txt_evaluacion_previa").value;//aqui esta si es a,p,....
       //alert("valor radio select = "+evaluacion_previa);
           document.getElementById("textAviso_tupa_subdet").style.display="block";
           $.ajax({
						type: 'post',
                        url:'/SGDUNI/subDetalleInsertTupa.uni',
						dataType: 'html',
                        data:'in_codigo_det='+in_codigo_det+"&de_tramitacion_porc="+de_tramitacion_porc+"&de_tramitacion_sol="+de_tramitacion_sol+"&ch_evaluacion_previa="+evaluacion_previa+"&in_plazo_resolver_dias="+in_plazo_resolver_dias+"&vc_inicio_procedimiento="+vc_inicio_procedimiento+"&in_autoridad_com_resolver="+in_autoridad_com_resolver+"&vc_reconsideracion="+vc_reconsideracion+"&vc_apelacion="+vc_apelacion  ,
                        success: function(data)
                        {
                            if(Number(data)==1)
                            {
                              document.getElementById("textAviso_tupa_subdet").innerHTML="SubDetalle registrado correctamente";
                              fnl_llamarGrillaListaSubDetalle( trim(in_codigo_det) );
                            }
                            else
                            {
                                document.getElementById("textAviso_tupa_subdet").innerHTML="ERROR! No se pudo guardar el SubDetalle";
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

  function fnl_llamarGrillaListaSubDetalle(dettupa)
  {
      //alert(dettupa);
                   $.ajax({
						type: 'post',
                        url:'/SGDUNI/listarSubDetTupa.uni',
						dataType: 'html',
                        data:'idTupaDet='+dettupa,
						success: function(data)
                        {
                            document.getElementById("dvl_listaSubDetalles").innerHTML = data;
						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}
					});
  }

  function fnl_llamarGrillaListaSubDetalle()
  {
      var dettupa = document.getElementById("in_codigo_det").value;
      //alert(dettupa);
                   $.ajax({
						type: 'post',
                        url:'/SGDUNI/listarSubDetTupa.uni',
						dataType: 'html',
                        data:'idTupaDet='+dettupa,
						success: function(data)
                        {
                            document.getElementById("dvl_listaSubDetalles").innerHTML = data;
						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}
					});
  }
  
  //
  function fnl_selec_evaluaacion_previa(valk)
  {
      document.getElementById("txt_evaluacion_previa").value=valk;
  }

 



  //ajax para lisatr cap si es ocdo
function fnl_llamarGrillaTupa()
{
    //var ch_tipo_depend_fac = trim(document.getElementById("ch_tipo_depend_fac").value);
    var in_cod_depend_fac = document.getElementById("in_cod_depend_fac").value;

    //alert('tipoFacDep = '+tipoFacDep+" : in_cod_depend_fac = "+in_cod_depend_fac);

                   $.ajax({
						type: 'post',
                        url:'/SGDUNI/resultadoListaDetallesTupa.uni',
						dataType: 'html',
                        data:'tipo='+tipoFacDep+'&codFacDep='+in_cod_depend_fac,
						success: function(data)
                        {
                            document.getElementById("dvl_listaTupa").innerHTML=data;
						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
}


function fnl_llamarGrillaTupaUsuario()
{
    var ch_tipo_depend_fac = trim(document.getElementById("ch_tipo_depend_fac").value);
    var in_cod_depend_fac  = trim(document.getElementById("in_cod_depend_fac").value);

    //alert('ch_tipo_depend_fac = '+ch_tipo_depend_fac+" : in_cod_depend_fac = "+in_cod_depend_fac);

                   $.ajax({
						type: 'post',
                        url:'/SGDUNI/resultadoListaDetallesTupa.uni',
						dataType: 'html',
                        data:'tipo='+ch_tipo_depend_fac+'&codFacDep='+in_cod_depend_fac,
						success: function(data)
                        {
                            document.getElementById("dvl_listaTupa").innerHTML=data;
						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
}

function fnl_llamarGrillaDetalleTupa(idTupa)
{

}







