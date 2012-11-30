/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function form_Observ()
{
 this.vc_observacion=document.getElementById("vc_observacion");
 this.in_codigo_estructura=document.getElementById("in_codigo_estructura");

}
function fnl_agrega_observ()
{
    var form=new form_Observ();    
    ShowHide("dvl_form_observacion",true);
    form.vc_observacion.focus();
}
//
function fnl_guardar_observ()
{
 var form=new form_Observ();
  if(trim(form.vc_observacion.value)==""){
      alertALCON("Escriba la observación","alert");
      form.vc_observacion.focus();
      return false;
  }
  if(confirm("Seguro de guardar y enviar?"))
  {
      document.forms[0].action="organigramaObservacionInsertar.uni?codEstc="+form.in_codigo_estructura.value;
      document.forms[0].submit();
  }
  return true;
}