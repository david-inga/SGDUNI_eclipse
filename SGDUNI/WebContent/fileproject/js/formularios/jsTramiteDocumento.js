/***********************
Tramite documentario
************************/

   function formTramDocNuevo()
   {
       this.ch_codigo_proc       = document.getElementById("ch_codigo_proc");
       this.dt_fecha             = document.getElementById("dt_fecha");

       this.in_depend_facu       = document.getElementById("in_depend_facu");
       this.ch_tipo_depend_facu  = document.getElementById("ch_tipo_depend_facu");

       this.lstDependencia       = document.getElementById("lstDependencia");
       this.lstFacultad       = document.getElementById("lstFacultad");


       this.in_codigo_oficio     = document.getElementById("in_codigo_oficio");
       this.in_codigo_estructura     = document.getElementById("in_codigo_estructura");
       this.in_codigo_rof             = document.getElementById("in_codigo_rof");
       this.in_codigo_mof        = document.getElementById("in_codigo_mof");
       this.in_codigo_mapro        = document.getElementById("in_codigo_mapro");

       this.formlTramDoc = document.getElementById("formlTramDoc");


   }
 
   function fnl_regiTram()
   {
       var forTr=new formTramDocNuevo();
	   if(trim(forTr.ch_codigo_proc.value)=="")
       {            
           alertALCON("Escriba el codigo","alert");
           forTr.ch_codigo_proc.focus();
           return false;
       }
       
       if(trim(forTr.dt_fecha.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           forTr.dt_fecha.focus();
           return false;
       }else{
           if(compare_dates(forTr.dt_fecha.value,"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }
       }
       //       
       if(trim(forTr.in_codigo_oficio.value)=="" || trim(forTr.in_codigo_oficio.value)=="0"){
           alertALCON("Adjunte el Oficio Circular ","alert");
           return false;
       }
       //
       if(trim(forTr.in_codigo_estructura.value)=="" || trim(forTr.in_codigo_estructura.value)=="0"){
           alertALCON("Adjunte la estructura organica","alert");
           return false;
       }
       //
        if(trim(forTr.in_codigo_rof.value)=="" || trim(forTr.in_codigo_rof.value)=="0"){
           alertALCON("Adjunte el archivo ROF","alert");
           return false;
       }
       //
        if(trim(forTr.in_codigo_mof.value)=="" || trim(forTr.in_codigo_mof.value)=="0"){
           alertALCON("Adjunte el archivo MOF","alert");
           return false;
       }
       //
        if(trim(forTr.in_codigo_mapro.value)=="" || trim(forTr.in_codigo_mapro.value)=="0"){
           alertALCON("Adjunte el archivo MAPRO","alert");
           return false;
       }
       //
       var numChec=document.forms[0].ch_tipo_depend_facu.length;
       var nuCheck=0;
       var txtCheck="";
       for(var i=0;i<numChec;i++){
          if(document.forms[0].ch_tipo_depend_facu[i].checked==true){
             nuCheck++;
             txtCheck=document.forms[0].ch_tipo_depend_facu[i].value;
          }
       }
       if(nuCheck==0)
       {
          alertALCON("Seleccione para quien va dirigido","alert");
          return false;
       }
       if(txtCheck=="f")
       {//facultad
           if(forTr.lstFacultad.value=="0" || trim(forTr.lstFacultad.value)=="" || forTr.in_depend_facu.value=="0" || trim(forTr.in_depend_facu.value)=="")
           {
               alertALCON("Seleccione la Facultad","alert");
               forTr.lstFacultad.focus();
               return false;
           }
       }else if(txtCheck=="d")
       {//dependencia
            if(forTr.lstDependencia.value=="0" || trim(forTr.lstDependencia.value)=="" || forTr.in_depend_facu.value=="0" || trim(forTr.in_depend_facu.value)=="")
            {
               alertALCON("Seleccione la Dependecia","alert");
               forTr.lstDependencia.focus();
               return false;
            }
       }
      if(confirm("¿Está seguro de iniciar el trámite?"))
      {
          if(confirm("¿Confirmar para iniciar el trámite"))
          {
            forTr.formlTramDoc.submit();
         }
      }
      return true;
      
   }
   function fnl_regiTramUsuario()
   {       
       var forTr=new formTramDocNuevo();
	   if(trim(forTr.ch_codigo_proc.value)=="")
       {
           alertALCON("Escriba el codigo","alert");
           forTr.ch_codigo_proc.focus();
           return false;
       }

       if(trim(forTr.dt_fecha.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           forTr.dt_fecha.focus();
           return false;
       }else{
           if(compare_dates(forTr.dt_fecha.value,"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }
       }
       //
       if(trim(forTr.in_codigo_oficio.value)=="" || trim(forTr.in_codigo_oficio.value)=="0"){
           alertALCON("Adjunte el Oficio Circular ","alert");
           return false;
       }
       //
       if(trim(forTr.in_codigo_estructura.value)=="" || trim(forTr.in_codigo_estructura.value)=="0"){
           alertALCON("Adjunte la estructura organica","alert");
           return false;
       }
       //
        if(trim(forTr.in_codigo_rof.value)=="" || trim(forTr.in_codigo_rof.value)=="0"){
           alertALCON("Adjunte el archivo ROF","alert");
           return false;
       }
       //
        if(trim(forTr.in_codigo_mof.value)=="" || trim(forTr.in_codigo_mof.value)=="0"){
           alertALCON("Adjunte el archivo MOF","alert");
           return false;
       }
       //
        if(trim(forTr.in_codigo_mapro.value)=="" || trim(forTr.in_codigo_mapro.value)=="0"){
           alertALCON("Adjunte el archivo MAPRO","alert");
           return false;
       }
       //
	   if(trim(forTr.in_depend_facu.value)=="" || trim(forTr.in_depend_facu.value)=="0")
       {
           alertALCON("Seleccione la Dependencia o Facultad","alert");
           forTr.ch_codigo_proc.focus();
           return false;
       }
       //
	   if(trim(forTr.ch_tipo_depend_facu.value)=="")
       {
           alertALCON("Seleccione la Dependencia o Facultad","alert");
           forTr.ch_codigo_proc.focus();
           return false;
       }
      if(confirm("¿Está seguro de iniciar el trámite?"))
      {
          if(confirm("¿Confirmar para iniciar el trámite"))
          {
            forTr.formlTramDoc.submit();
         }
      }
      return true;
   }
   //
   function fnl_Fact_Dep(val)
   {
      var forTr=new formTramDocNuevo();
       if(String(val)!="")
       {
               if(val=="f"){
                   forTr.lstDependencia.style.display='none';
                   forTr.lstFacultad.style.display='block';
               }else if(val=="d"){
                   forTr.lstFacultad.style.display='none';
                   forTr.lstDependencia.style.display='block';
               }else{
                   alertALCON("Tipo no definido","alert");
               }
       }else{
                   alertALCON("Tipo no definido","alert");
       }
   }
   //
   function fnl_In_Depend_Fac(val){
      var forTra=new formTramDocNuevo();
      forTra.in_depend_facu.value=val;
   }
   //
   function fnl_adjuntar_archivo(tip,idef)
   {
       this.identiDocTxt=idef;
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '650','height':'380','url':'/SGDUNI/listaDocumentosGestionBasica.uni','urlVar':{'tip':'"+tip+"','idef':'"+idef+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
   }
   //
   function fnl_select_cod(cod,nom)
   {
      
      document.getElementById(this.identiDocTxt).value=cod;      
      document.getElementById("dvl_"+this.identiDocTxt).innerHTML='<img src="fileproject/img/iconos_archivos/icoAdjunArchivo.gif" width="22" height="20" title="'+nom+'"/>';
      CloseWindowPopUpAlcon();//Cerrar PopUp funcion global
   }
/********************************************
    ESTRUCTURA Y OFICIO
  *****************************t***************/
 function fnl_ver_estructura(idEstruc)
 {
   precargaALCON("Cargando...",true,1,"center");
   windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '700','height':'350','url':'/SGDUNI/organigramaVista.uni','urlVar':{'idEstruc':'"+idEstruc+"','ifram':'1'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
      precargaALCON("",true,0,"center");
   });
 }

function fnl_ver_oficio(idOfc)
 {
   precargaALCON("Cargando...",true,1,"center");
   windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '700','height':'350','url':'/SGDUNI/oficioCircularVista.uni','urlVar':{'idOfc':'"+idOfc+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
      precargaALCON("",true,0,"center");
   });
 }
  /********************************************
    LISTA
  *****************************t***************/
  function fnl_archivos_d_t(id)
  {
      
     var contTR=document.getElementById("tr_archivos_gd_"+id);
     var contDV=document.getElementById("dvl_archivos_gd_"+id);
     if(contDV.style.display=='none')
     {
          contTR.style.backgroundImage='url(fileproject/img/iconos_archivos/icoBacgr.gif)';
          contTR.style.backgroundRepeat='no-repeat';
          contTR.style.backgroundPosition='bottom center';
          ShowHide("dvl_archivos_gd_"+id,true,1);
     }else
     {
          ShowHide("dvl_archivos_gd_"+id,true,0);
          contTR.style.backgroundImage='';
     }
  }
   //
    function fnl_ver_versiones(tip,idtipodoc)
    {        
      precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '700','height':'350','url':'/SGDUNI/versionesDocumentosGestionListar.uni','urlVar':{'tip':'"+tip+"','idtipodoc':'"+idtipodoc+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
    }
    //
    function fnl_agregar_version(idtipodoc,tip)
    {
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '570','height':'350','url':'/SGDUNI/versionesDocumentosGestionNuevo.uni','urlVar':{'tip':'"+tip+"','idtipodoc':'"+idtipodoc+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
    }

    //REGISTRA NUEVAS VERSIONES - MAPRO-ROF-MOF
    function fnl_validar_doc(valDoc)
    {
        filerro=0;
        var NomDoc=(valDoc.substring(valDoc.lastIndexOf("\\"))).toLowerCase();
        if(trim(document.getElementById('vc_nombre_archivo').value)=="")
            document.getElementById('vc_nombre_archivo').value=NomDoc.substring(NomDoc.length,1);
           fng_validar_archivo(valDoc,'archivo',function(){
            filerro=1;
        });
    }
    //
    function fnl_regiVerDocMapro()
    {
       var in_codigo_mapro=document.getElementById("in_codigo_mapro");
       var rutDoc=document.getElementById("vc_ruta_archivo");
       var nomDoc=document.getElementById("vc_nombre_archivo");
       var estado=document.getElementById("in_codigo_estado");

	   if(trim(in_codigo_mapro.value)==""){
           alertALCON("Seleccione el el documento MAPRO","alert");
           return false;
       }
       if(trim(rutDoc.value)==""){
           alertALCON("Seleccione el archivo","alert");           
           return false;
       }

       if(trim(nomDoc.value)==""){
           alertALCON("Escriba el nombre del documento","alert");
           return false;
       }
      /*
       if(trim(estado.value)=="" || trim(estado.value)=="0"){
           alertALCON("Selecciona el estado","alert");
           return false;
       }
        */
       //
       if(filerro>0)
       {
         alertALCON("Verifique la extención del archivo","alert");
         return false;
       }
       document.getElementById("frmRegiVersDocMapro").submit();
    }
    //
    function fnl_doc_mapro_resul(v)
    {
      var docAviso=document.getElementById("textAvisoPpDocG");
      docAviso.style.display='block';
      if(Number(v)==1){
         docAviso.innerHTML="Documento registrado correctamente";
         document.getElementById("frmRegiVersDocMapro").reset();
      }else if(Number(v)==0){
        docAviso.innerHTML="Ocurrio un error al registrar el documento";
      }else{
        docAviso.innerHTML="Ocurrio un error";
      }
    }
 //rof
    function fnl_regiVerDocRof()
    {
       var in_codigo_rof=document.getElementById("in_codigo_rof");
       var rutDoc=document.getElementById("vc_ruta_archivo");
       var nomDoc=document.getElementById("vc_nombre_archivo");
       var estado=document.getElementById("in_codigo_estado");

	   if(trim(in_codigo_rof.value)==""){
           alertALCON("Seleccione el el documento ROF","alert");
           return false;
       }
       if(trim(rutDoc.value)==""){
           alertALCON("Seleccione el archivo","alert");           
           return false;
       }

       if(trim(nomDoc.value)==""){
           alertALCON("Escriba el nombre del documento","alert");
           return false;
       }
   /*
       if(trim(estado.value)=="" || trim(estado.value)=="0"){
           alertALCON("Selecciona el estado","alert");
           return false;
       }
        */
       //
       if(filerro>0)
       {
         alertALCON("Verifique la extención del archivo","alert");
         return false;
       }
       document.getElementById("frmRegiVersDocRof").submit();
    }
    //
    function fnl_doc_rof_resul(v)
    {
      var docAviso=document.getElementById("textAvisoPpDocG");
      docAviso.style.display='block';
      if(Number(v)==1){
         docAviso.innerHTML="Documento registrado correctamente";
         document.getElementById("frmRegiVersDocRof").reset();
      }else if(Number(v)==0){
        docAviso.innerHTML="Ocurrio un error al registrar el documento";
      }else{
        docAviso.innerHTML="Ocurrio un error";
      }
    }

//mof
    function fnl_regiVerDocMof()
    {
       var in_codigo_mof=document.getElementById("in_codigo_mof");
       var rutDoc=document.getElementById("vc_ruta_archivo");
       var nomDoc=document.getElementById("vc_nombre_archivo");
       var estado=document.getElementById("in_codigo_estado");

	   if(trim(in_codigo_mof.value)==""){
           alertALCON("Seleccione el el documento MOF","alert");
           return false;
       }
       if(trim(rutDoc.value)==""){
           alertALCON("Seleccione el archivo","alert");
           return false;
       }

       if(trim(nomDoc.value)==""){
           alertALCON("Escriba el nombre del documento","alert");
           return false;
       }

       /*
       if(trim(estado.value)=="" || trim(estado.value)=="0"){
           alertALCON("Selecciona el estado","alert");
           return false;
       }
        */
       //
       if(filerro>0)
       {
         alertALCON("Verifique la extención del archivo","alert");
         return false;
       }
       document.getElementById("frmRegiVersDocMof").submit();
    }
    //
    function fnl_doc_mof_resul(v)
    {
      var docAviso=document.getElementById("textAvisoPpDocG");
      docAviso.style.display='block';
      if(Number(v)==1){
         docAviso.innerHTML="Documento registrado correctamente";
         document.getElementById("frmRegiVersDocMof").reset();
      }else if(Number(v)==0){
        docAviso.innerHTML="Ocurrio un error al registrar el documento";
      }else{
        docAviso.innerHTML="Ocurrio un error";
      }
    }

 /*********************************************
  OBSERVACIONES DOCUMENTOS DE GESTION
  *********************************************/
    //
    function fnl_ver_observaciones(tip,idtipodoc)
    {
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '500','height':'350','url':'/SGDUNI/observacionDocumentosGestionNuevo.uni','urlVar':{'tip':'"+tip+"','idtipodoc':'"+idtipodoc+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
    }
    //
    function fnl_ver_lista_observaciones(tip,idtipodoc)
    {
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '580','height':'400','url':'/SGDUNI/observacionDocumentosGestionListar.uni','urlVar':{'tip':'"+tip+"','idtipodoc':'"+idtipodoc+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
    }

    //MOF
    function fnl_canlObrvMof()
    {
      var vc_observacion=document.getElementById("vc_observacion");
      vc_observacion.value="";
    }

    function fnl_regiObrvMof()
    {
      var in_codigo_mof=document.getElementById("in_codigo_mof");
      var vc_observacion=document.getElementById("vc_observacion");
      var ckaprobarCa=document.getElementById("ckaprobar");
      var ckaprobar="0";
      if(ckaprobarCa){
          if(ckaprobarCa.checked==true)
               ckaprobar="1";
      }
      if(trim(in_codigo_mof.value)==""){
          alertALCON("Seleccione el documento MOF","alert");
          return false;
      }
      //
      if(trim(vc_observacion.value)==""){
          alertALCON("Escriba la observación","alert");
          vc_observacion.focus();
          return false;
      }
      //
                $.ajax({
						type: 'post',
                        url:'/SGDUNI/observacionDocumentosGestionMofGuardar.uni',
						dataType: 'html',
                        data:'in_codigo_mof='+in_codigo_mof.value+'&vc_observacion='+vc_observacion.value+"&ckaprobar="+ckaprobar,
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
      //
  }

    //ROF
    function fnl_canlObrvRof(){
      var vc_observacion=document.getElementById("vc_observacion");
      vc_observacion.value="";
    }
    function fnl_regiObrvRof()
    {
      var in_codigo_rof=document.getElementById("in_codigo_rof");
      var vc_observacion=document.getElementById("vc_observacion");
      var ckaprobarCa=document.getElementById("ckaprobar");
      var ckaprobar="0";
      if(ckaprobarCa){
          if(ckaprobarCa.checked==true)
               ckaprobar="1";
      }
      if(trim(in_codigo_rof.value)==""){
          alertALCON("Seleccione el documento MOF","alert");
          return false;
      }

      if(trim(in_codigo_rof.value)==""){
          alertALCON("Seleccione el documento ROF","alert");
          return false;
      }
      //
      if(trim(vc_observacion.value)==""){
          alertALCON("Escriba la observación","alert");
          vc_observacion.focus();
          return false;
      }
      //
                $.ajax({
						type: 'post',
                        url:'/SGDUNI/observacionDocumentosGestionRofGuardar.uni',
						dataType: 'html',
                        data:'in_codigo_rof='+in_codigo_rof.value+'&vc_observacion='+vc_observacion.value+"&ckaprobar="+ckaprobar,
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
      //
  }
      //MAPRO
    function fnl_canlObrvMapro()
    {
      var vc_observacion=document.getElementById("vc_observacion");
      vc_observacion.value="";
    }
    function fnl_regiObrvMapro()
    {
		
      var in_codigo_mapro=document.getElementById("in_codigo_mapro");
      var vc_observacion=document.getElementById("vc_observacion");
      var ckaprobarCa=document.getElementById("ckaprobar");
      var ckaprobar="0";
      if(ckaprobarCa){
          if(ckaprobarCa.checked==true)
               ckaprobar="1";
      }

      if(trim(in_codigo_mapro.value)==""){
          alertALCON("Seleccione el documento MAPRO","alert");
          return false;
      }
      //
      if(trim(vc_observacion.value)==""){
          alertALCON("Escriba la observación","alert");
          vc_observacion.focus();
          return false;
      }
      //
                $.ajax({
						type: 'post',
                        url:'/SGDUNI/observacionDocumentosGestionMaproGuardar.uni',
						dataType: 'html',
                        data:'in_codigo_mapro='+in_codigo_mapro.value+'&vc_observacion='+vc_observacion.value+"&ckaprobar="+ckaprobar,
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
      //
  }