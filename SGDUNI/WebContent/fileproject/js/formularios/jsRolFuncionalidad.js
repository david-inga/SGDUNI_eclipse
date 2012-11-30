/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function formRolFuncionalidad(){
   this.ch_codigo_rol=document.getElementById("ch_codigo_rol");
   this.ch_codigo_herramienta=document.getElementById("ch_codigo_herramienta");
   this.formRolFun=document.getElementById("form_rol_funcionalidad");
}
function fnl_listaFuncionalidades(xherr)
{    
    var formFh=new formRolFuncionalidad();
    if(trim(xherr)==""){
        alertALCON("Seleccione una Herramienta para continuar","alert");
        formFh.ch_codigo_herramienta.focus();
        return false;
    }

    if(Number(formFh.ch_codigo_rol.value)==0){
        alertALCON("Seleccione el Protocolo para continuar","alert");
        formFh.ch_codigo_rol.focus();
        formFh.ch_codigo_herramienta.value="0";
        return false;
    }
    var codrol=formFh.ch_codigo_rol.value;
    document.location.href="asignarFunRol.uni?codrol="+codrol+"&codherr="+xherr+"";
    return true;
}
//
function fnl_guardarRolFuncionalidad(){
  var formFh=new formRolFuncionalidad();
   if(formFh.ch_codigo_herramienta.value=="0"){
        alertALCON("Seleccione una Herramienta para continuar","alert");
        formFh.ch_codigo_herramienta.focus();
        return false;
    }

    if(Number(formFh.ch_codigo_rol.value)==0){
        alertALCON("Seleccione el Protocolo para continuar","alert");
        formFh.ch_codigo_rol.focus();
        formFh.ch_codigo_herramienta.value="0";
        return false;
    }
    formFh.formRolFun.submit();
    return true;
}


