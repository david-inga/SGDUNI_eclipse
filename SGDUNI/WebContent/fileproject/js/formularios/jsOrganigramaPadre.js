/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/***********************
ESTRUCTURA ORGANICA
************************/

   function formEstrucOgM()
   {
       this.ch_tipo_fac_depend = document.getElementById("ch_tipo_fac_depend");
       this.lstFacultad        = document.getElementById("lstFacultad");
       this.lstDependencia     = document.getElementById("lstDependencia");
       this.ch_codigo_estructura      = document.getElementById("ch_codigo_estructura");
       this.dt_fecha           = document.getElementById("dt_fecha");
       this.in_facu_depend      = document.getElementById("in_facu_depend");
       this.in_codigo_estado      = document.getElementById("in_codigo_estado");

       this.frmEstructOrganica = document.getElementById("frmEstructOrganica");


   }
   function fnl_nuevoEstrucOrg()
   {
     var formEstrucOg=new formEstrucOgM();       
       formEstrucOg.lstFacultad.value        = "0";
       formEstrucOg.lstDependencia.value     = "0";
       formEstrucOg.ch_codigo_estructura.value      = "";
       formEstrucOg.dt_fecha.value           = "";
       formEstrucOg.in_facu_depend.value      = "";
       formEstrucOg.in_codigo_estado.value      = "0";
   }
   //
   function fnl_regiEstrucOrg()
   {
       var formEstrucOg=new formEstrucOgM();
	   if(trim(formEstrucOg.ch_codigo_estructura.value)==""){
           alertALCON("Escriba el codigo","alert");
           formEstrucOg.ch_codigo_estructura.focus();
           return false;
       }
       if(trim(formEstrucOg.dt_fecha.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           formEstrucOg.dt_fecha.focus();
           return false;
       }else{
           if(compare_dates(formEstrucOg.dt_fecha.value,"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }        
       }


       var numChec=document.forms[0].ch_tipo_fac_depend.length;
       var nuCheck=0;
       var txtCheck="";
       for(var i=0;i<numChec;i++){
          if(document.forms[0].ch_tipo_fac_depend[i].checked==true){
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
           if(formEstrucOg.lstFacultad.value=="0" || trim(formEstrucOg.lstFacultad.value)=="" || formEstrucOg.in_facu_depend.value=="0" || trim(formEstrucOg.in_facu_depend.value)=="")
           {
               alertALCON("Seleccione la Facultad","alert");
               formEstrucOg.lstFacultad.focus();
               return false;
           }
       }else if(txtCheck=="d")
       {//dependencia
            if(formEstrucOg.lstDependencia.value=="0" || trim(formEstrucOg.lstDependencia.value)=="" || formEstrucOg.in_facu_depend.value=="0" || trim(formEstrucOg.in_facu_depend.value)=="")
            {
               alertALCON("Seleccione la Dependecia","alert");
               formEstrucOg.lstDependencia.focus();
               return false;
            }
       }
      /*
       if(trim(formEstrucOg.in_codigo_estado.value)=="0"){
           alertALCON("Seleccione el estado","alert");
           formEstrucOg.in_codigo_estado.focus();
           return false;
       }
       */
       formEstrucOg.frmEstructOrganica.submit();
   }
//
   function fnl_regiEstrucOrgUsuario()
   {
       var formEstrucOg=new formEstrucOgM();
	   if(trim(formEstrucOg.ch_codigo_estructura.value)==""){
           alertALCON("Escriba el codigo","alert");
           formEstrucOg.ch_codigo_estructura.focus();
           return false;
       }
       if(trim(formEstrucOg.dt_fecha.value)==""){
           alertALCON("Seleccione la Fecha","alert");
           formEstrucOg.dt_fecha.focus();
           return false;
       }else{
           if(compare_dates(formEstrucOg.dt_fecha.value,"","/")){
               alertALCON("La fecha no debe ser mayor a la fecha actual","alert");
               return false;
           }        
       }
       
      //
       if(trim(formEstrucOg.ch_tipo_fac_depend.value)==""){
           alertALCON("Seleccione la Facultad o Dependencia","alert");           
           return false;
       }
       //alert(formEstrucOg.ch_tipo_fac_depend.value);
       //
       if(trim(formEstrucOg.in_facu_depend.value)==""){
           alertALCON("Seleccione la Facultad o Dependencia","alert");
           return false;
       }
       // alert(formEstrucOg.in_facu_depend.value);
              
       formEstrucOg.frmEstructOrganica.submit();
   }
   //
   function fnl_Fact_Dep(val){
      var formEstrucOg=new formEstrucOgM();
       if(String(val)!="")
       {
               if(val=="f"){
                   formEstrucOg.lstDependencia.style.display='none';
                   formEstrucOg.lstFacultad.style.display='block';
               }else if(val=="d"){
                   formEstrucOg.lstFacultad.style.display='none';
                   formEstrucOg.lstDependencia.style.display='block';
               }else{
                   alertALCON("Tipo no definido","alert");
               }
       }else{
                   alertALCON("Tipo no definido","alert");
       }
   }
   //
   function fnl_In_Depend_Fac(val){
      var formEstrucOg=new formEstrucOgM();
      formEstrucOg.in_facu_depend.value=val;
   }