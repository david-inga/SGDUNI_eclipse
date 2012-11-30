/***********************
RESOLUCION RECTORAL
************************/

   function formRR()
   {
       this.ch_codigo_resolucion  = document.getElementById("ch_codigo_resolucion");
       this.dt_fecha = document.getElementById("dt_fecha");
       this.vc_nombre_archivo      = document.getElementById("vc_nombre_archivo");
       this.vc_ruta_archivo           = document.getElementById("vc_ruta_archivo");
       this.in_codigo_proc      = document.getElementById("in_codigo_proc");
       this.in_codigo_estado      = document.getElementById("in_codigo_estado");
       this.frmRegiResolRec = document.getElementById("frmRegiResolRec");
   }
//
 function fnl_nuevoRR()
 {
      var forResRec=new formRR();
       forResRec.ch_codigo_resolucion.value="";
       forResRec.dt_fecha.value="";
       forResRec.vc_nombre_archivo.value="";
       forResRec.vc_ruta_archivo.value="";
       forResRec.in_codigo_proc.value="";
       //forResRec.in_codigo_estado.value="0";
       //forResRec.frmRegiResolRec.reset();
       
 }
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
 var filerro=0;
   function fnl_regiRR()
   {
     var forResRec=new formRR();

	   if(trim(forResRec.ch_codigo_resolucion.value)==""){
           alertALCON("Escriba el codigo","alert");
           forResRec.ch_codigo_resolucion.focus();
           return false;
       }
       if(trim(forResRec.vc_nombre_archivo.value)==""){
           alertALCON("Escriba el nombre del documento","alert");
           forResRec.vc_nombre_archivo.focus();
           return false;
       }

       if(trim(forResRec.dt_fecha.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           forResRec.dt_fecha.focus();
           return false;
       }else
       {
           if(compare_dates(forResRec.dt_fecha.value,"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }
       }
       //
       if(trim(forResRec.in_codigo_proc.value)=="" || trim(forResRec.in_codigo_proc.value)=="0")
       {
           alertALCON("Seleccione la documentación del Tramite","alert");
           return false;
       }
       if(confirm("¿Está seguro de efectuar la Resolución Rectoral?"))
       {
           if(confirm("Confirmar para continuar")){
             forResRec.frmRegiResolRec.submit();
           }
       }
       //     
       
   }
   //
   function fnl_adjuntar_tram()
   {       
        precargaALCON("Cargando...",true,1,"center");//'fondo':{'zIndex':'10','opacidad':'85'},
        windowPopUpALCON("[{'opciones':{'botonCerrar':true,'width': '830','height':'380','url':'/SGDUNI/tramiteDocumentosListarPopup.uni','urlVar':{'Sin':'Variable'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
            precargaALCON("",true,0,"center");
        });
   }
   //
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
   function fnl_adjuntar_tram_env(incodtram,nom)
   {
       var forResRec=new formRR();
       forResRec.in_codigo_proc.value=incodtram;
       document.getElementById("dvl_in_codigo_proc").innerHTML='<img src="fileproject/img/iconos_archivos/icoAdjunArchivo.gif" width="22" height="20" title="'+nom+'"/>';
       CloseWindowPopUpAlcon();//Cerramos el POPUP
   }