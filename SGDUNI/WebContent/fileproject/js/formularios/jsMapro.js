/***********************
DOCUMENTOS MAPRO
************************/

   function formMAPRO()
   {
       this.vc_nombre_archivo  = document.getElementById("vc_nombre_archivo");
       this.ch_tipo_depend_fac = document.getElementById("ch_tipo_depend_fac");
       this.vc_ruta_archivo_v1 = document.getElementById("vc_ruta_archivo_v1");
       this.txtnom_archivo_db = document.getElementById("txtnom_archivo_db");
       
       this.lstFacultad        = document.getElementById("lstFacultad");
       this.lstDependencia     = document.getElementById("lstDependencia");
       this.ch_codigo_mapro    = document.getElementById("ch_codigo_mapro");
       this.dt_fecha           = document.getElementById("dt_fecha");
       this.in_depend_fac      = document.getElementById("in_depend_fac");
       this.in_codigo_estado   = document.getElementById("in_codigo_estado");


       this.formlMapro = document.getElementById("frmRegiDocMapro");


   }
//
 function fnl_nueviMapro()
 {
      var forMAPRO=new formMAPRO();
       forMAPRO.vc_nombre_archivo.value  = "";
       //forMAPRO.ch_tipo_depend_fac = "";
       forMAPRO.vc_ruta_archivo_v1.value = "";
       forMAPRO.lstFacultad.value        = "0";
       forMAPRO.lstDependencia.value     = "0";
       //forMAPRO.ch_codigo_mapro      = "";
       forMAPRO.dt_fecha.value           = "";
       forMAPRO.in_depend_fac.value      = "";
       forMAPRO.in_codigo_estado.value      = "0";
      //forMAPRO.formlMapro.reset();
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
   function fnl_regiMapro()
   {
       var forMAPRO=new formMAPRO();
	   if(trim(forMAPRO.ch_codigo_mapro.value)==""){
           alertALCON("Escriba el codigo","alert");
           forMAPRO.ch_codigo_mapro.focus();
           return false;
       }
       if(trim(forMAPRO.vc_nombre_archivo.value)==""){
           alertALCON("Escriba el nombre del documento","alert");
           forMAPRO.vc_nombre_archivo.focus();
           return false;
       }
       if(trim(forMAPRO.dt_fecha.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           forMAPRO.dt_fecha.focus();
           return false;
       }else
       {
           if(compare_dates(forMAPRO.dt_fecha.value,"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }
       }


       var numChec=document.forms[0].ch_tipo_depend_fac.length;
       var nuCheck=0;
       var txtCheck="";
       for(var i=0;i<numChec;i++){
          if(document.forms[0].ch_tipo_depend_fac[i].checked==true){
             nuCheck++;
             txtCheck=document.forms[0].ch_tipo_depend_fac[i].value;
          }
       }
       if(nuCheck==0)
       {
          alertALCON("Seleccione el tipo de usuario","alert");
          return false;
       }
       if(txtCheck=="f")
       {//facultad
           if(forMAPRO.lstFacultad.value=="0" || trim(forMAPRO.lstFacultad.value)=="" || forMAPRO.in_depend_fac.value=="0" || trim(forMAPRO.in_depend_fac.value)=="")
           {
               alertALCON("Seleccione la Facultad","alert");
               forMAPRO.lstFacultad.focus();
               return false;
           }
       }else if(txtCheck=="d")
       {//dependencia
            if(forMAPRO.lstDependencia.value=="0" || trim(forMAPRO.lstDependencia.value)=="" || forMAPRO.in_depend_fac.value=="0" || trim(forMAPRO.in_depend_fac.value)=="")
            {
               alertALCON("Seleccione la Dependecia","alert");
               forMAPRO.lstDependencia.focus();
               return false;
            }
       }

       //
       if(trim(forMAPRO.txtnom_archivo_db.value)=="" || trim(forMAPRO.txtnom_archivo_db.value)=="null")
        {
           if(trim(forMAPRO.vc_ruta_archivo_v1.value)=="")
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
      /*
       if(forMAPRO.in_codigo_estado.value=="0"){
           alertALCON("Seleccione el Estado","alert");
           return false;
       }
       */

       forMAPRO.formlMapro.submit();
   }
//
   function fnl_regiMaproUsuario()
   {
       
       var forMAPRO=new formMAPRO();
	   if(trim(forMAPRO.ch_codigo_mapro.value)==""){
           alertALCON("Escriba el codigo","alert");
           forMAPRO.ch_codigo_mapro.focus();
           return false;
       }
       if(trim(forMAPRO.vc_nombre_archivo.value)==""){
           alertALCON("Escriba el nombre del documento","alert");
           forMAPRO.vc_nombre_archivo.focus();
           return false;
       }
       if(trim(forMAPRO.dt_fecha.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           forMAPRO.dt_fecha.focus();
           return false;
       }else
       {
           if(compare_dates(forMAPRO.dt_fecha.value,"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }
       }

        if(trim(forMAPRO.ch_tipo_depend_fac.value)==""){
			alertALCON("Seleccione la Dependencia o Facultad");
			return false;
		}
		if(trim(forMAPRO.ch_tipo_depend_fac.value)==""){
			alertALCON("Seleccione la Dependencia o Facultad");
			return false;
		}

       //
        if(trim(forMAPRO.txtnom_archivo_db.value)=="" || trim(forMAPRO.txtnom_archivo_db.value)=="null")
        {
           if(trim(forMAPRO.vc_ruta_archivo_v1.value)=="")
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

       forMAPRO.formlMapro.submit();
   }
   //
   function fnl_Fact_Dep(val){
      var forMAPRO=new formMAPRO();
       if(String(val)!="")
       {
               if(val=="f"){
                   forMAPRO.lstDependencia.style.display='none';
                   forMAPRO.lstFacultad.style.display='block';
               }else if(val=="d"){
                   forMAPRO.lstFacultad.style.display='none';
                   forMAPRO.lstDependencia.style.display='block';
               }else{
                   alertALCON("Tipo no definido","alert");
               }
       }else{
                   alertALCON("Tipo no definido","alert");
       }
   }
   //
   function fnl_In_Depend_Fac(val){
      var forMAPRO=new formMAPRO();
      forMAPRO.in_depend_fac.value=val;
   }