// JavaScript Document
   function formUsuarioCargoEstructural()
   {
       this.in_codigo_usuario=document.getElementById("in_codigo_usuario");
       this.lstFacultad=document.getElementById("lstFacultad");
       this.lstDependencia=document.getElementById("lstDependencia");
       this.txtidfacdep=document.getElementById("txtidfacdep");
       this.rdtipofacdep=document.getElementById("rdtipofacdep");

       this.formUsuarioCargoEstructural=document.getElementById("formUsuarioCargoEstructural");

   }
   //   
   function fnl_tip_select(val)
   {
	   if(trim(val)=="f")
	   {
		  document.getElementById("lstFacultad").style.display="block";
		  document.getElementById("lstFacultad").value="0";
		  document.getElementById("lstDependencia").style.display="none";		   		  
	   }else if(trim(val)=="d"){
		    document.getElementById("lstDependencia").style.display="block";		   
			document.getElementById("lstDependencia").value="0";
		    document.getElementById("lstFacultad").style.display="none";		  
	   }
	   document.getElementById("txtidfacdep").value=""; 
   }
   //
   function fnl_In_Depend_Fac(val)
   {
	  document.getElementById("txtidfacdep").value=val; 
   }
   //
   //
   function fnl_guardarUsuarioCargoEstructural()
   {
	   var form=new formUsuarioCargoEstructural();

	   if(trim(form.in_codigo_usuario.value)=="" || form.in_codigo_usuario.value=="0")
       {
		       alertALCON("Seleccione el nombre del Usuario","alert");
		       form.in_codigo_usuario.focus();
			   return false;
	   }
	   if(trim(form.txtidfacdep.value)=="" || form.txtidfacdep.value=="0")
       {
		       alertALCON("Seleccione la Facultad o Dependecia","alert");		     
			   return false;
	   }

      form.formUsuarioCargoEstructural.submit();
	  return true;
   }