/***********************
DOCUMENTOS MOF
************************/

   function formMOF()
   {
       this.vc_nombre_archivo  = document.getElementById("vc_nombre_archivo");
       this.ch_tipo_depend_fac = document.getElementById("ch_tipo_depend_fac");
       this.vc_ruta_archivo_v1 = document.getElementById("vc_ruta_archivo_v1");
       this.txtnom_archivo_db = document.getElementById("txtnom_archivo_db");

       this.lstFacultad        = document.getElementById("lstFacultad");
       this.lstDependencia     = document.getElementById("lstDependencia");
       this.ch_codigo_mof      = document.getElementById("ch_codigo_mof");
       this.dt_fecha           = document.getElementById("dt_fecha");
       this.in_depend_fac      = document.getElementById("in_depend_fac");
       this.in_codigo_estado      = document.getElementById("in_codigo_estado");


       this.formlMof = document.getElementById("frmRegiDocMof");


   }
//
 function fnl_nuevoMof()
 {
      var forMOF=new formMOF();
       forMOF.vc_nombre_archivo.value  = "";
       //forMOF.ch_tipo_depend_fac = "";
       forMOF.vc_ruta_archivo_v1.value = "";
       forMOF.lstFacultad.value        = "0";
       forMOF.lstDependencia.value     = "0";
       //forMOF.ch_codigo_mof      = "";
       forMOF.dt_fecha.value           = "";
       forMOF.in_depend_fac.value      = "";
       forMOF.in_codigo_estado.value      = "0";
      //forMOF.formlMof.reset();
 }
function fnl_validar_doc(valDoc)
{
    filerro=0;
    var NomDoc=(valDoc.substring(valDoc.lastIndexOf("\\"))).toLowerCase();

    if(trim(document.getElementById('vc_nombre_archivo').value)=="")
        document.getElementById('vc_nombre_archivo').value = NomDoc.substring(NomDoc.length,1);

    fng_validar_archivo(valDoc,'archivo',function()
    {
        filerro=1;
    });
}
//
 var filerro=0;
   function fnl_regiMof()
   {
       var forMOF=new formMOF();
	   if(trim(forMOF.ch_codigo_mof.value)==""){
           alertALCON("Escriba el codigo","alert");
           forMOF.ch_codigo_mof.focus();
           return false;
       }
       if(trim(forMOF.vc_nombre_archivo.value)==""){
           alertALCON("Escriba el nombre del documento","alert");
           forMOF.vc_nombre_archivo.focus();
           return false;
       }
       if(trim(forMOF.dt_fecha.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           forMOF.dt_fecha.focus();
           return false;
       }else
       {
           if(compare_dates(forMOF.dt_fecha.value,"","/")){
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
           if(forMOF.lstFacultad.value=="0" || trim(forMOF.lstFacultad.value)=="" || forMOF.in_depend_fac.value=="0" || trim(forMOF.in_depend_fac.value)=="")
           {
               alertALCON("Seleccione la Facultad","alert");
               forMOF.lstFacultad.focus();
               return false;
           }
       }else if(txtCheck=="d")
       {//dependencia
            if(forMOF.lstDependencia.value=="0" || trim(forMOF.lstDependencia.value)=="" || forMOF.in_depend_fac.value=="0" || trim(forMOF.in_depend_fac.value)=="")
            {
               alertALCON("Seleccione la Dependecia","alert");
               forMOF.lstDependencia.focus();
               return false;
            }
       }

       //
       if(trim(forMOF.txtnom_archivo_db.value)=="" || trim(forMOF.txtnom_archivo_db.value)=="null")
        {
           if(trim(forMOF.vc_ruta_archivo_v1.value)=="")
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

       forMOF.formlMof.submit();
   }
 //

    function fnl_regiMofUsuario()
   {
       
       var forMOF=new formMOF();
	   if(trim(forMOF.ch_codigo_mof.value)==""){
           alertALCON("Escriba el codigo","alert");
           forMOF.ch_codigo_mof.focus();
           return false;
       }
       if(trim(forMOF.vc_nombre_archivo.value)==""){
           alertALCON("Escriba el nombre del documento","alert");
           forMOF.vc_nombre_archivo.focus();
           return false;
       }
       if(trim(forMOF.dt_fecha.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           forMOF.dt_fecha.focus();
           return false;
       }else
       {
           if(compare_dates(forMOF.dt_fecha.value,"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }
       }

		if(trim(forMOF.ch_tipo_depend_fac.value)==""){
			alertALCON("Seleccione la Dependencia o Facultad");
			return false;
		}
		if(trim(forMOF.ch_tipo_depend_fac.value)==""){
			alertALCON("Seleccione la Dependencia o Facultad");
			return false;
		}

       //
        if(trim(forMOF.txtnom_archivo_db.value)=="" || trim(forMOF.txtnom_archivo_db.value)=="null")
        {
           if(trim(forMOF.vc_ruta_archivo_v1.value)=="")
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
       forMOF.formlMof.submit();
   }

   //
   function fnl_Fact_Dep(val)
   {
      var forMOF=new formMOF();
       if(String(val)!="")
       {
           if(val=="f")
           {
               forMOF.lstDependencia.style.display='none';
               forMOF.lstFacultad.style.display='block';
           }
           else if(val=="d")
           {
               forMOF.lstFacultad.style.display='none';
               forMOF.lstDependencia.style.display='block';
           }
           else
           {
               alertALCON("Tipo no definido","alert");
           }
       }else
       {
           alertALCON("Tipo no definido","alert");
       }
   }
   //
   function fnl_In_Depend_Fac(val){
      var forMOF=new formMOF();
      forMOF.in_depend_fac.value=val;
   }