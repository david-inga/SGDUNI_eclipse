
   function formEstructura_Organica()
   {
       this.in_codigo_estructura  = document.getElementById("in_codigo_estructura");
       this.ch_codigo_estructura  = document.getElementById("ch_codigo_estructura");
       this.vc_ruta_archivo       = document.getElementById("vc_ruta_archivo");
       this.vc_nombre_archivo     = document.getElementById("vc_nombre_archivo");
       this.txtnom_archivo_db     = document.getElementById("txtnom_archivo_db");
       this.lstFacultad           = document.getElementById("lstFacultad");
       this.lstDependencia        = document.getElementById("lstDependencia");
       this.ch_tipo_fac_depend    = document.getElementById("ch_tipo_fac_depend");
       this.in_facu_depend        = document.getElementById("in_facu_depend");
       this.dt_fecha              = document.getElementById("dt_fecha");
       this.in_formato = document.getElementById("in_formato");
       
       this.formEstOrgGuardar = document.getElementById("frmRegiEstOrg");
       this.frmModEstOrg = document.getElementById("frmModEstOrg");
   }

function strBlanco(str)
{
var numCaracStr = str.length;
var espacios = str.split(" ");
var numEspacios = espacios.length - 1;
if(numEspacios > 0 )
{
return false;
}
else
{
return true;
}
}

// llenar caja de texto con el nombre del archivo del a ruta
function fnl_validar_doc(valDoc)
{
    document.getElementById('vc_nombre_archivo').value = "";
    filerro=0;
    var NomDoc=(  valDoc.substring( valDoc.lastIndexOf("\\") ) ).toLowerCase();

    if( trim(document.getElementById('vc_nombre_archivo').value) == "")
        document.getElementById('vc_nombre_archivo').value = NomDoc.substring( NomDoc.length ,1);

    if( strBlanco(NomDoc)== false )
    {
        alertALCON("El Nombre del Documento tiene Espacios en Blanco, porfavor cambiar de nombre en documento!","alert");
        document.getElementById('vc_nombre_archivo').value = "";
    }

    fng_validar_archivo(valDoc,'archivo',function()
    {
        filerro = 1;
    });
}

// hacer aparecer el comobobox segun el check al radio button
function fnl_Fact_Dep(val)
{
  var formEstructuraOrganica = new formEstructura_Organica();
   if(String(val)!="")
   {
       if(val=="f")
       {
           formEstructuraOrganica.lstDependencia.style.display='none';
           formEstructuraOrganica.lstFacultad.style.display='block';
       }
       else if(val=="d")
       {
           formEstructuraOrganica.lstFacultad.style.display='none';
           formEstructuraOrganica.lstDependencia.style.display='block';
       }
       else
       {
           alertALCON("Tipo no definido","alert");
       }
   }
   else
   {
       alertALCON("Tipo no definido","alert");
   }
}

// asigna valor segun el item selecionado del combobox
function fnl_In_Depend_Fac(val)
{
  document.getElementById("in_facu_depend").value = val;
}


//falta adecuar

   var filerro=0;

   function fnl_regiEstructuraOrganica()
   {
       var forEstrucOrganica = new formEstructura_Organica();

	   if(trim(forEstrucOrganica.ch_codigo_estructura.value)=="")
       {
           alertALCON("Escriba el codigo","alert");
           forEstrucOrganica.ch_codigo_mof.focus();
           return false;
       }

       if(trim(forEstrucOrganica.dt_fecha.value)=="")
       {
           alertALCON("Seleccione la Fecha","alert");
           forEstrucOrganica.dt_fecha.focus();
           return false;
       }
       else
       {
           if(compare_dates(forEstrucOrganica.dt_fecha.value,"","/"))
           {
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }
       }
       
       if(trim(forEstrucOrganica.vc_nombre_archivo.value)=="")
       {
           alertALCON("Seleccione el Documento","alert");
           forEstrucOrganica.vc_nombre_archivo.focus();
           return false;
       }

       alert("  "+document.getElementById("in_codigo_oficio").value);

       if(document.getElementById("in_codigo_oficio").value == 0)
       {
           alertALCON("Adjunte El Oficio Circular a Responder","alert");
           return false;
       }

       if(document.getElementById("in_formato").selectedIndex == 0)
       {
			alertALCON("Seleccione la orientación de la hoja","alert");
            document.getElementById("in_formato").focus();
			return false;
		}
       

       var numChec=document.forms[0].ch_tipo_fac_depend.length;
       var nuCheck=0;
       var txtCheck="";

       for(var i=0;i<numChec;i++)
       {
          if(document.forms[0].ch_tipo_fac_depend[i].checked==true)
          {
             nuCheck++;
             txtCheck=document.forms[0].ch_tipo_fac_depend[i].value;
          }
       }
       if(nuCheck==0)
       {
          alertALCON("Seleccione el tipo de usuario","alert");
          return false;
       }
       if(txtCheck=="f")
       {//facultad
           if(forEstrucOrganica.lstFacultad.value=="0" || trim(forEstrucOrganica.lstFacultad.value)=="" || forEstrucOrganica.in_facu_depend.value=="0" || trim(forEstrucOrganica.in_facu_depend.value)=="")
           {
               alertALCON("Seleccione la Facultad","alert");
               forEstrucOrganica.lstFacultad.focus();
               return false;
           }
       }
       else if(txtCheck=="d")
       {//dependencia
            if(forEstrucOrganica.lstDependencia.value=="0" || trim(forEstrucOrganica.lstDependencia.value)=="" || forEstrucOrganica.in_facu_depend.value=="0" || trim(forEstrucOrganica.in_facu_depend.value)=="")
            {
               alertALCON("Seleccione la Dependencia","alert");
               forEstrucOrganica.lstDependencia.focus();
               return false;
            }
       }

       //
       if(trim(forEstrucOrganica.txtnom_archivo_db.value) == "" || trim(forEstrucOrganica.txtnom_archivo_db.value) == "null")
       {
           if(trim(forEstrucOrganica.vc_ruta_archivo.value) == "")
           {
             alertALCON("Seleccione el archivo","alert");
             return false;
           }
       }
        //
       if(filerro>0)
       {
         alertALCON("Verifique la extención del archivo","alert");
         return false;
       }

       forEstrucOrganica.formEstOrgGuardar.submit();
       return true;
   }
 //

   function fnl_regiEstructuraOrganicaUsuario()
   {
       var forEstrucOrganica = new formEstructura_Organica();
       
	   if(trim(forEstrucOrganica.ch_codigo_estructura.value)=="")
       {
           alertALCON("Escriba el codigo","alert");
           forEstrucOrganica.ch_codigo_estructura.focus();
           return false;
       }

       if(trim(forEstrucOrganica.dt_fecha.value)=="")
       {
           alertALCON("Seleccione la Fecha","alert");
           forEstrucOrganica.dt_fecha.focus();
           return false;
       }
       else
       {
           if(compare_dates(forEstrucOrganica.dt_fecha.value,"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }
       }
       
       if(trim(forEstrucOrganica.vc_nombre_archivo.value)==""){
           alertALCON("Escriba el nombre del documento","alert");
           forEstrucOrganica.vc_nombre_archivo.focus();
           return false;
       }

       if(document.getElementById("in_codigo_oficio").value==0){
           alertALCON("Adjunte El Oficio Circular a Responder","alert");
           return false;
       }
       

		if(trim(forEstrucOrganica.ch_tipo_fac_depend.value)==""){
			alertALCON("Seleccione la Dependencia o Facultad");
			return false;
		}
		if(trim(forEstrucOrganica.ch_tipo_fac_depend.value)==""){
			alertALCON("Seleccione la Dependencia o Facultad");
			return false;
		}

       //
        if(trim(forEstrucOrganica.txtnom_archivo_db.value)=="" || trim(forEstrucOrganica.txtnom_archivo_db.value)=="null")
        {
           if(trim(forEstrucOrganica.vc_ruta_archivo.value)=="")
           {
             alertALCON("Seleccione el archivo","alert");
             return false;
           }
        }

       if(filerro>0)
       {
         alertALCON("Verifique la extención del archivo","alert");
         return false;
       }

       if(document.getElementById("in_formato").selectedIndex == 0){
			alertALCON("Seleccione la orientación de la hoja","alert");
            document.getElementById("in_formato").focus();
			return false;
		}

       forEstrucOrganica.formEstOrgGuardar.submit();
       return true;
   }

   function fnl_modificarEstructuraOrganica()
   {
       var forEstrucOrganica = new formEstructura_Organica();
    
    
       if(trim(forEstrucOrganica.in_codigo_estructura.value)=="")
       {
           alertALCON("no hay codigo identificador","alert");
           return false;
       }

	   if(trim(forEstrucOrganica.ch_codigo_estructura.value)=="")
       {
           alertALCON("Escriba el codigo","alert");
           forEstrucOrganica.ch_codigo_mof.focus();
           return false;
       }

       if(trim(forEstrucOrganica.dt_fecha.value)=="")
       {
           alertALCON("Seleccione la Fecha","alert");
           forEstrucOrganica.dt_fecha.focus();
           return false;
       }
       else
       {
           if(compare_dates(forEstrucOrganica.dt_fecha.value,"","/"))
           {
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }
       }

       if(trim(forEstrucOrganica.vc_nombre_archivo.value)==""){
           alertALCON("Seleccione el Documento a importar","alert");
           forEstrucOrganica.vc_nombre_archivo.focus();
           return false;
       }

       //
       if(trim(forEstrucOrganica.txtnom_archivo_db.value) == "" || trim(forEstrucOrganica.txtnom_archivo_db.value) == "null")
       {
           if(trim(forEstrucOrganica.vc_ruta_archivo.value) == "")
           {
             alertALCON("Seleccione el archivo a importar","alert");
             return false;
           }
       }
       
        //
       if(filerro>0)
       {
         alertALCON("Verifique la extención del archivo","alert");
         return false;
       }
       
       forEstrucOrganica.frmModEstOrg.submit();
       return true;
   }
  
   //abrir pop up de ver versiones
   function fnl_llamarPopupVersiones(idEstructura)
   {
        precargaALCON("Cargando...",true,1,"center");
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/listarVersionesEstructuraOrganica.uni','urlVar':{'idEstructura':'"+idEstructura+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
        });
   }

   //abrir pop up de ver lista de observaciones
   function fnl_llamarPopupObservaciones(idEstructura)
   {
        precargaALCON("Cargando...",true,1,"center");
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/listarObservacionesEO.uni','urlVar':{'idEstructura':'"+idEstructura+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
        });
   }

   //abrir pop up de guardar observaciones
   function fnl_llamarPopupGuardarObservaciones(idEstructura)
   {
        precargaALCON("Cargando...",true,1,"center");
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'450','url':'/SGDUNI/llamarFOrmularioNuevaObservacion.uni','urlVar':{'idEstructura':'"+idEstructura+"'},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
         });
   }

   function fnl_regiObservacionEO()
    {
      var in_codigo_estructura   = document.getElementById("in_codigo_estructura");
      var vc_observacion  = document.getElementById("vc_observacion");
      var ckaprobarCa     = document.getElementById("ckaprobar");

      var ckaprobar="0";

      if( ckaprobarCa )
      {
        if(ckaprobarCa.checked == true)
          ckaprobar="1";
      }
      if(trim(in_codigo_estructura.value)=="")
      {
          alertALCON("no se encontro el codigo identificador del la E.O.","alert");
          return false;
      }
      //
      if(trim(vc_observacion.value)=="")
      {
          alertALCON("Escriba la observación","alert");
          vc_observacion.focus();
          return false;
      }
      //
        $.ajax({
                type: 'post',
                url:'/SGDUNI/guardarObservacionEO.uni',
                dataType: 'html',
                data:'in_codigo_estructura='+in_codigo_estructura.value+'&vc_observacion='+vc_observacion.value+"&ckaprobar="+ckaprobar,
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
     fnl_cancelar();
     return true;
            //fnl_Refrescar(in_codigo_estructura);
  }

  
  function fnl_canlObrvEO()
  {
    var vc_observacion=document.getElementById("vc_observacion");
    vc_observacion.value="";
  }

  function fnl_adjuntar_archivo()
   {
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'380','url':'/SGDUNI/adjuntarOficioaEO.uni','urlVar':{},'precarga':'Cargando...','iniClose':true}}];",false,function()
        {
            precargaALCON("",true,0,"center");
        });
   }

   function fnl_select_cod(cod,nom)
   {

      document.getElementById("in_codigo_oficio").value = cod;
      document.getElementById("dvl_in_codigo_oficio").innerHTML = '<img src="fileproject/img/iconos_archivos/icoAdjunArchivo.gif" width="22" height="20" title="'+nom+'"/>';
      CloseWindowPopUpAlcon();//Cerrar PopUp funcion global
   }

   function fnl_Refrescar(idEstructura)
   {
       $.ajax({
            type: 'post',
            url:'/SGDUNI/listarObservacionesEO.uni',
            dataType: 'html',
            data:'idEstructura='+idEstructura,
            success: function(data)
            {
                document.getElementById("list_Obs").innerHTML = data;
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


