/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function fnl_remov_org(idOg,nomOg)
{
       
	
  if(confirm("Seguro de Borrar ("+nomOg+")"))
  {
      if(confirm("Se perderan los detalles,¿Quiere continuar?"))
      {          
      				precargaALCON("Borrando...",true,1,"center");
                    $.ajax({
						type: 'post',
                        url:'/SGDUNI/eliminarItemOrganigrama.uni',
						dataType: 'html',
                        data:'idOg='+idOg,
						success: function(data)
                        {                            
                            if(Number(data)==1){
                                document.getElementById("item_txt_"+idOg).style.display="none";
                            }else{
                               alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias","alert"); 
                            }
                            precargaALCON("",true,0,"center");
						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							precargaALCON("",true,0,"center");
							return false;
						}

					});

      }
  }
    
}
function fnl_add_org(idOg,nomOg)
{
  precargaALCON("Cargando...",true,1,"center");
  var idEstruc=document.getElementById("txtidEstruc").value;
    windowPopUpALCON("[{'fondo':{'zIndex':'10','opacidad':'85'},'opciones':{'botonCerrar':true,'width': '400','height':'300','url':'/SGDUNI/adicionarItemOrganigrama.uni','urlVar':{'idOg':'"+idOg+"','nomOg':'"+nomOg+"','idEstruc':'"+idEstruc+"'},'precarga':'Cargando...','iniClose':true}}];",false,function(){
        precargaALCON("",true,0,"center");
    });
}
//
function formDetOrg(){
  this.fromdetllorganigrama=document.getElementById("fromdetllorganigrama");
  this.vc_descripcion_entidad=document.getElementById("vc_descripcion_entidad");
  this.vc_nombre=document.getElementById("vc_nombre");
  this.in_codigo_estructura=document.getElementById("in_codigo_estructura");
}
//
function fnl_regiItemOrgnigrama()
{
  var formD=new formDetOrg();
  if(trim(formD.in_codigo_estructura.value)=="")
  {
      alertALCON("Para poder crear la Estructura Organiza debe Seleccionar La Estructura","alert");
      return false;
  }
  //
  if(trim(formD.vc_nombre.value)=="")
  {
     alertALCON("Escriba el nombre","alert");
     formD.vc_nombre.focus();
     return false;
  }
  formD.fromdetllorganigrama.action="organigramaDetalleInsert.uni?codEstc="+trim(formD.in_codigo_estructura.value);
  formD.fromdetllorganigrama.submit();
  return true;
}