// JavaScript Document
   function formUsuarioConfigFlujo()
   {
       this.in_codigo_usuario=document.getElementById("in_codigo_usuario");
       this.in_codigo_cong_=document.getElementById("in_codigo_cong_");
       this.txtnumitem_confg_flujo=document.getElementById("txtnumitem_confg_flujo");
       this.form_usuario_config_flujo=document.getElementById("form_usuario_config_flujo");

   }
   //
   function fnl_guardarUsuarioConfigFlujo()
   {
	   var form=new formUsuarioConfigFlujo();
	   if(trim(form.in_codigo_usuario.value)=="" || form.in_codigo_usuario.value=="0")
       {
		       alertALCON("Seleccione el nombre del Usuario","alert");
		       form.in_codigo_usuario.focus();
			   return false;
	  }	 
      form.form_usuario_config_flujo.submit();
	  return true;
   }