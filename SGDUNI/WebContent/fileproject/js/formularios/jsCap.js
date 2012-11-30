function form()
   {
       this.ch_codigo_cap       =   document.getElementById("ch_codigo_cap");
       this.dt_fecha_cap             =   document.getElementById("dt_fecha_cap");
       this.in_cod_depend_fac       =   document.getElementById("in_cod_depend_fac");
       this.ch_tipo_depend_fac  =   document.getElementById("ch_tipo_depend_fac");
       this.ch_estado_cap  =   document.getElementById("ch_estado_cap");
       this.lstDependencia       =   document.getElementById("lstDependencia");
       this.lstFacultad          =   document.getElementById("lstFacultad");
       this.formCap         =   document.getElementById("frmRegiCap");
   }

   function fnl_nuevoCap()
   {
       document.getElementById("ch_codigo_cap").disabled =false;
       //document.getElementById("ch_codigo_cap").value ="";
       document.getElementById("dt_fecha_cap").focus();
       document.getElementById("dt_fecha_cap").value ="";
       document.getElementById("dt_fecha_cap").disabled =false;
       document.getElementById("lstDependencia").selectedIndex = 0;
       document.getElementById("lstDependencia").disabled =false;
       document.getElementById("lstFacultad").selectedIndex = 0;
       document.getElementById("lstFacultad").disabled =false;
       document.getElementById("ch_estado_cap").selectedIndex = 0;
       document.getElementById("ch_estado_cap").disabled =false;
       var objAvios=$(".textAviso").html();
	   $(".textAviso").css("display","none");
       return true;
   }

   function fnl_regiCapUsuario()
   {
       var forTr=new form();
       
	   if(trim(forTr.ch_codigo_cap.value)=="")
       {
           alertALCON("Escriba el codigo","alert");
           forTr.ch_codigo_cap.focus();
           return false;

       }

       if(trim(forTr.dt_fecha_cap.value)=="")
       {
           alertALCON("Seleccione la Fecha","alert");
           forTr.dt_fecha_cap.focus();
           return false;
       }
       else
       {
           if(compare_dates(trim(forTr.dt_fecha_cap.value),"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }
       }
       //
       if(trim(forTr.ch_tipo_depend_fac.value)==""){
           alertALCON("Seleccione la Facultad o Dependencia","alert");
           return false;
       }
       //alert(formEstrucOg.ch_tipo_fac_depend.value);
       //
       if(trim(forTr.in_cod_depend_fac.value)==""){
           alertALCON("Seleccione la Facultad o Dependencia","alert");
           return false;
       }
       // alert(formEstrucOg.in_facu_depend.value);
      document.getElementById("frmRegiCap").submit();

      return true;
         
   }

function fnl_regiCap()
   {
       var forTr=new form();
	   if(trim(forTr.ch_codigo_cap.value)=="")
       {
           alertALCON("Escriba el codigo","alert");
           forTr.ch_codigo_cap.focus();
           return false;

       }

       if(trim(forTr.dt_fecha_cap.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           forTr.dt_fecha_cap.focus();
           return false;
       }
       else
       {
           if(compare_dates(trim(forTr.dt_fecha_cap.value),"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }
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

      document.getElementById("frmRegiCap").submit();

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
   //
   function fnl_In_Depend_Fac(val)
   {
      var forTra=new form();
      forTra.in_cod_depend_fac.value=val;
   }
   //
   function fnl_llamarPopupDetalle(idCap)
   {
       var codCap = document.getElementById("ch_codigo_cap").value;
      //alert(idCap);
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/llamarFormCapDetalleInsertar.uni','urlVar':{'codCap':'"+codCap+"','idCap':'"+idCap+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
   }


   //OBSERVACIONES

   //
   function fnl_llamarPopupObservaciones(idCap)
   {
       //alert(idCap);
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/llamarFormInsertObservacionesCap.uni','urlVar':{'idCap':'"+idCap+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_regiObrvCap()
    {
      var in_codigo_cap=document.getElementById("in_codigo_cap");
      var vc_observacion=document.getElementById("vc_observacion");
      var ckaprobarCa=document.getElementById("ckaprobar");
      var ckaprobar="0";
      if(ckaprobarCa)
      {
          if(ckaprobarCa.checked==true)
               ckaprobar="1";
      }

      if(trim(in_codigo_cap.value)==""){
          alertALCON("Se perdio el codigo del cap","alert");
          return false;
      }
      //
      if(trim(vc_observacion.value)==""){
          alertALCON("Escriba la observación","alert");
          vc_observacion.focus();
          return false;
      }
                $.ajax({
						type: 'post',
                        url:'/SGDUNI/insertarObservacionesCap.uni',
						dataType: 'html',
                        data:'in_codigo_cap='+in_codigo_cap.value+'&vc_observacion='+vc_observacion.value+"&ckaprobar="+ckaprobar,
						success: function(data)
                        {
                           var objAvisod=document.getElementById("dvl_avisopm");
                           objAvisod.style.display="block";
                            if(Number(data)==1){
                                vc_observacion.value="";
                                objAvisod.innerHTML="Observacion registrada correctamente";
                            }else{
                               objAvisod.innerHTML="Ocurrio un error al intentar guardar los datos";
                            }
						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
  }

   //
    function fnl_ver_lista_observaciones(idCap)
    {
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '580','height':'400','url':'/SGDUNI/listarObservacionesCap.uni','urlVar':{'idCap':'"+idCap+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
    }

//------------------------------------------------


   function fnl_activarBotonDetalle(dataInsertada)
   {

       if(trim(dataInsertada) == "")
       {
           document.getElementById("cmdAgregarDetCap").style.display = "none" ;
       }
       else{
           document.getElementById("cmdAgregarDetCap").style.display = "block" ;
       }
   }

   function fnl_regiDetCap()
   {

     if(trim(document.getElementById("in_codigo_cap").value) == "")
       {
           alertALCON("Necesita el Codigo del CAP para poder almacenar el detalle","alert");
          // forTr.vc_responsabilidad.focus();
           return false;
       }

     if (document.getElementById("in_codigo_cargo_estruc").selectedIndex == 0)
      {
       alertALCON("Debe seleccionar un Cargo Estructural", "alert");
       document.getElementById("in_codigo_cargo_estruc").focus();
       return false;
      }

      if(trim(document.getElementById("in_total").value)=="")
      {
       alertALCON("Escriba el codigo","alert");
       document.getElementById("in_total").focus();
       return false;
      }

      if(trim(document.getElementById("in_situa_cargo_prev").value)=="")
      {
       alertALCON("Escriba la Situación al Cargo","alert");
       document.getElementById("in_situa_cargo_prev").focus();
       return false;
      }

      if(trim(document.getElementById("in_situa_cargo_ocupado").value)=="")
      {
       alertALCON("Escriba la Situación al Cargo","alert");
       document.getElementById("in_situa_cargo_ocupado").focus();
       return false;
      }

     document.getElementById("frmRegiDetaCap").submit();
       
     //CloseWindowPopUpAlcon();//cerrar popup

     return true;
   }

   function fnl_cancelarRegiDetCap()
   {
     CloseWindowPopUpAlcon();//cerrar popup
   }


//ajax para lisatr cap si es ocdo
function fnl_llamarGrillaCap()
{
    //var ch_tipo_depend_fac = trim(document.getElementById("ch_tipo_depend_fac").value);
    var in_cod_depend_fac = document.getElementById("in_cod_depend_fac").value;

                   $.ajax({
						type: 'post',
                        url:'/SGDUNI/listarResultadoCap.uni',
						dataType: 'html',
                        data:'tipo='+tipoFacDep+'&codFacDep='+in_cod_depend_fac,
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

function fnl_llamarGrillaCapUsuario()
{
    var ch_tipo_depend_fac = trim(document.getElementById("ch_tipo_depend_fac").value);
    var in_cod_depend_fac  = trim(document.getElementById("in_cod_depend_fac").value);

                   $.ajax({
						type: 'post',
                        url:'/SGDUNI/listarResultadoCap.uni',
						dataType: 'html',
                        data:'tipo='+ch_tipo_depend_fac+'&codFacDep='+in_cod_depend_fac,
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

function fnl_llamarGrillaDetalleCap(codCap)
{
    //var ch_tipo_depend_fac = trim(document.getElementById("ch_tipo_depend_fac").value);
    //var in_cod_depend_fac = document.getElementById("in_cod_depend_fac").value;

                   $.ajax({
						type: 'post',
                        url:'/SGDUNI/listarDetalleCap.uni',
						dataType: 'html',
                        data:'CodCap='+codCap,
						success: function(data)
                        {
                            document.getElementById("dvl_miprimeravez").innerHTML=data;
						},
						error: function(dato)
                        {
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}
					});
}

//
function fnl_resul_proc_detall_cap(va){
    if(Number(va)==1){
        alertALCON("DATOS GUARDADOS CORRECTAMENTE","alert");
    }else{
        alertALCON("ERROR! AL INTENTAR GUARDAR LOS DATOS","error");
    }
}


   //function fnl_select_cod(cod)
   //{
     // document.getElementById(this.identiDocTxt).value=cod;
      //CloseWindowPopUpAlcon();//Cerrar PopUp funcion global
  // }