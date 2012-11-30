package com.sgduni.actions;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.sgduni.dao.orpro_ta_rof_DAO;
import com.sgduni.forms.orpro_ta_base_legal_rof;
import com.sgduni.forms.orpro_ta_registro_rof;
import com.sgduni.forms.orpro_ta_rof;
import com.sgduni.forms.orpro_ta_rof_funciones_generales;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;


/**
 *
 * @author Gerald
 */
public class ROF_generarInformePDF extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        
        Integer idRof = Integer.parseInt(request.getParameter("idRof"));
        Integer idVersionRof = Integer.parseInt(request.getParameter("idVersionRof"));

        DataSource dataSource = getDataSource(request, "DSconnection");
        System.out.println("CAdena PDF  "+dataSource );
        orpro_ta_rof_DAO daoRof = new orpro_ta_rof_DAO(dataSource);

        // listado del documento
        ArrayList<orpro_ta_base_legal_rof> listaBaseRof= daoRof.getListBaseLegales(idRof,idVersionRof);
        ArrayList<orpro_ta_rof> listaRof= daoRof.getListasReporteRof(idRof,idVersionRof);
        ArrayList<orpro_ta_rof_funciones_generales> listaFunGenerales= daoRof.getListaFuncionGeneralSegunIDRof(idRof, idVersionRof);
        ArrayList<orpro_ta_registro_rof> listaRegistro= daoRof.getListaRegistrosRof(idRof, idVersionRof);

       

        //listaROF -  Variables para retonar contenidos
        String urlOrganigrama = null;
        String nombFacDep= null;
        String fechaDeElaboracionROF = null;
        String introduccionROF =null;
        String finalidadROF=null;
        String alcanceROF=null;

        if(listaRof.size() >= 0 )
         {
            for( orpro_ta_rof rof : listaRof)
            {
              urlOrganigrama = getServlet().getServletContext().getRealPath("/") +"documentos/EstructurasOrganicas/"+rof.getNombreEstructura();
              nombFacDep = rof.getNombreFaculDepen();
              fechaDeElaboracionROF =  getFechaFormateadaParaLaCaratula( rof.getDt_fecha() );
              introduccionROF= rof.getVc_introduccion();
              finalidadROF=rof.getVc_naturaleza_finalidad();
              alcanceROF = rof.getVc_alcance();
              System.out.println("la url de la imagen es "+urlOrganigrama);
              System.out.println("la facdep es  "+nombFacDep);
              System.out.println("la fecha es  "+fechaDeElaboracionROF);
              System.out.println("la fecha es  "+introduccionROF);
            }
        }
        else
        {
          System.out.println(" no hay datos en la lista ");
        }

         try
         {

         //creando documento PDF
         Document document = new Document(PageSize.A4);
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         PdfWriter writer = PdfWriter.getInstance(document, baos);

         Rectangle rct = new Rectangle(36, 54, 559, 788);//36, 54, 559, 788..36,1,600,800
         //Definimos un nombre y un tama�o para el PageBox los nombres posibles son: �crop�, �trim�, �art� and �bleed�.
         writer.setBoxSize("art", rct);
         
        
         
         //Abrimos el documento
         document.open();

         //tipos de espacio
         Paragraph espacioGrande=new Paragraph("        ");
         Chunk espacioChico=new Chunk("           ");

         //fuentes de caratula
         Font  fontNegrita18 = FontFactory .getFont("Times-Roman", 18, Font .BOLD);
         Font  fontNegrita25 = FontFactory .getFont("Times-Roman", 25, Font .BOLD);
         Font  fontTitulos = FontFactory .getFont("Arial", 16 , Font .NORMAL);
         Font  fontDocumentosContenido = FontFactory .getFont("Arial", 10 , Font .NORMAL);

 //-----------------------------------------------------INICIO DE CARATULA -----------------------------------//
         //margenes de documento
         document.setMargins(30, 30, 30, 30);
         //agregando titulo/nombre de la UNI
         String contenidoUni="UNIVERSIDAD NACIONAL DE INGENIERIA ";
         Paragraph nombreUniversidad=new Paragraph(contenidoUni,fontNegrita25 );
         nombreUniversidad.setAlignment(Paragraph.ALIGN_CENTER);
         nombreUniversidad.setSpacingBefore(100);
         nombreUniversidad.setSpacingAfter(50);
         document.add(new Paragraph(nombreUniversidad));


         //agregarLineasEnBlanco(espacioGrande, 3);
         //agregamos nombre de facultad dependencia
         Paragraph nombreFacultad=new Paragraph(nombFacDep,fontNegrita18);
         nombreFacultad.setAlignment(Paragraph.ALIGN_CENTER);
         nombreFacultad.setSpacingAfter(50);
         document.add(new Paragraph(nombreFacultad));

         //agregarLineasEnBlanco(espacioGrande, 3);
         //agregamos imagen del logo de la uni
         String docSimboloUNI = getServlet().getServletContext().getRealPath("/") +"fileproject/img/sistema/uni_caratula.jpg";
         System.out.println("la ruta es : "+docSimboloUNI);
         Image img = Image.getInstance(docSimboloUNI);
         img.setAlignment(Image.ALIGN_CENTER);
         img.setSpacingBefore(30);
         img.scalePercent(40f);
         document.add(img);
         
          //agregando Nombrecreacion del ROF
         String ReglamentoRof ="REGLAMENTO DE ORGANIZACION Y FUNCIONES ";
         Paragraph ReglamFunci=new Paragraph(ReglamentoRof,fontNegrita25);
         ReglamFunci.setAlignment(Paragraph.ALIGN_CENTER);
         ReglamFunci.setSpacingBefore(80);
         document.add(new Paragraph(ReglamFunci));

         //agregando Fecha de creacion del ROF
         Paragraph fechaCreacionRof=new Paragraph(fechaDeElaboracionROF,fontNegrita18 );
         fechaCreacionRof.setAlignment(Paragraph.ALIGN_CENTER);
         fechaCreacionRof.setSpacingBefore(40);
         document.add(new Paragraph(fechaCreacionRof));

//-----------------------------------------------------FIn de CARATULA -----------------------------------//
//-----------------------------------------------------Inicio de Documento -----------------------------------//


         document.newPage();
         document.setMargins(60, 60,35,30);
         //margenes de documento
         
                  
         // iniciar cabecera despues de caratula
         HeaderFooter event = new HeaderFooter(nombFacDep);
         writer.setPageEvent(event);

         document.add(espacioGrande);
         document.add(espacioGrande);
         document.add(espacioGrande);

         //Contenido INTRODUCCION
         Paragraph ConteIntro=new Paragraph(new Chunk("INTRODUCCION",fontTitulos));
         document.add(espacioChico);
         ConteIntro.setAlignment(Chunk.ALIGN_CENTER);
         ConteIntro.add(new Paragraph(introduccionROF,fontDocumentosContenido));
         ConteIntro.add(new Paragraph(Paragraph.ALIGN_JUSTIFIED));
         ConteIntro.setSpacingBefore(30);
         document.add(ConteIntro);

         document.newPage();
         
         document.add(espacioGrande);
         document.add(espacioGrande);
         document.add(espacioGrande);

         //Contenido Naturaleza y Finalidad
         Paragraph ConteFinalidad=new Paragraph(new Chunk("FINALIDAD",fontTitulos));
         document.add(espacioChico);
         ConteFinalidad.setAlignment(Chunk.ALIGN_CENTER);
         ConteFinalidad.add(new Paragraph(finalidadROF,fontDocumentosContenido));
         ConteFinalidad.add(new Paragraph(Paragraph.ALIGN_JUSTIFIED_ALL));
         ConteFinalidad.setSpacingBefore(30);
         document.add(ConteFinalidad);

         document.newPage();

         document.add(espacioGrande);
         document.add(espacioGrande);
         document.add(espacioGrande);

         //Contenido alcance
         Paragraph ConteAlcance=new Paragraph(new Chunk("ALCANCE",fontTitulos));
         document.add(espacioChico);
         ConteAlcance.setAlignment(Chunk.ALIGN_CENTER);
         ConteAlcance.add(new Paragraph(alcanceROF,fontDocumentosContenido));
         ConteAlcance.add(new Paragraph(Paragraph.ALIGN_JUSTIFIED_ALL));
         ConteAlcance.setSpacingBefore(30);
         document.add(ConteAlcance);

         //Trayendo Base legal documento
         String baseLegalROF= null;

         document.add(espacioGrande);
         document.add(espacioGrande);
         document.add(espacioGrande);
         Paragraph ConteBaseLegal=new Paragraph(new Chunk("BASE LEGAL",fontTitulos));
         ConteBaseLegal.setAlignment(Chunk.ALIGN_CENTER);
         document.add(espacioChico);
         if(listaBaseRof.size() >= 0 )
         {
            for( orpro_ta_base_legal_rof legal : listaBaseRof)
            {
                ConteBaseLegal.setSpacingBefore(30);
                ConteBaseLegal.add(new Paragraph(legal.getOrden()+" "+ legal.getVc_descripcion(),fontDocumentosContenido ));
                ConteBaseLegal.add(new Paragraph(Element.ALIGN_JUSTIFIED));
                ConteBaseLegal.add(new Chunk(Chunk.NEWLINE));
                ConteBaseLegal.add(new Chunk(Chunk.NEWLINE));
                ConteBaseLegal.setSpacingAfter(20);

            }
         }
         else
         {
           System.out.println(" no hay datos en la lista ");
         }
         document.add(ConteBaseLegal);
         
         
           //TrayendoFUNCIONES GENERALES  documento
         String funcionesGeneralesROF= null;

         document.add(espacioGrande);
         document.add(espacioGrande);
         document.add(espacioGrande);
         Paragraph ConteFuncionesGenerales=new Paragraph(new Chunk("FUNCIONES GENERALES",fontTitulos));
         ConteFuncionesGenerales.setAlignment(Chunk.ALIGN_CENTER);
         document.add(espacioChico);
         if(listaFunGenerales.size() >= 0 )
         {
            for( orpro_ta_rof_funciones_generales funcion : listaFunGenerales)
            {
                ConteFuncionesGenerales.setSpacingBefore(30);
                ConteFuncionesGenerales.add(new Paragraph(funcion.getCh_orden()+" "+ funcion.getVc_descripcion(),fontDocumentosContenido ));
                ConteFuncionesGenerales.add(new Paragraph(Element.ALIGN_JUSTIFIED));
                ConteFuncionesGenerales.add(new Chunk(Chunk.NEWLINE));
                ConteFuncionesGenerales.add(new Chunk(Chunk.NEWLINE));
                ConteFuncionesGenerales.setSpacingAfter(20);

            }
         }
         else
         {
           System.out.println(" no hay datos en la lista ");
         }
         document.add(ConteFuncionesGenerales);

         //EN LA NUEVA PAGINA NACE EL ORGANIGRAMA
         document.newPage();
         Chunk espacio=new Chunk(Chunk.NEWLINE);//manda lienas en blanco
         document.add(espacio);
         Paragraph a=new Paragraph(new Chunk("ORGANIGRAMA DE ORGANIZACION"));
         a.setAlignment(Paragraph.ALIGN_CENTER);
         document.add(espacio);
         a.add(Image.getInstance(urlOrganigrama));
         a.setAlignment(Image.ALIGN_MIDDLE);
         document.add(a);
         document.newPage();

         //Trayendo REGISTROS  y ORGANOS
         document.add(espacioGrande);
         document.add(espacioGrande);
         document.add(espacioGrande);
         Paragraph ConteRegistros=new Paragraph(new Chunk("REGISTROS",fontTitulos));
         ConteRegistros.setAlignment(Chunk.ALIGN_CENTER);
         document.add(espacioChico);
         if(listaRegistro.size() >= 0 )
         {
            for( orpro_ta_registro_rof registro : listaRegistro)
            {
                
// generar section escalonados segir block de notas 
            }
         }
         else
         {
           System.out.println(" no hay datos en la lista ");
         }
         document.add(ConteFuncionesGenerales);

         document.close();//cerrar el documento
         // mime
         response.setContentType("application/pdf");
         response.setContentLength(baos.size());
         ServletOutputStream out = response.getOutputStream();
         baos.writeTo(out);
         System.out.println("PASO BIEN EL ACTION" );
         }
         catch(Exception e)
         {
              System.out.println("Error al generar el informe"+e);
         }

         return null;
    }

    static class HeaderFooter extends PdfPageEventHelper
    {
        String nomFacDep;
        public HeaderFooter() {
        }

        public HeaderFooter(String nombreFacDep)
        {
            this.nomFacDep = nombreFacDep;
        }




        public void onEndPage (PdfWriter writer, Document document)
        {
            Rectangle rect = writer.getBoxSize("art");
            //Cabecera
//            Paragraph nombreUniversidad=new Paragraph(contenidoUni,fontNegrita25 );
            ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase(nomFacDep),(rect.getLeft() + rect.getRight()) / 2, rect.getTop(), 0 );
            //Pie
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(String.format("page %d pagina", writer.getPageNumber())),(rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0  );
        }
    }

     public static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas)
    {
        for (int i = 0; i < nLineas; i++)
        parrafo.add(new Paragraph("espacio"));
    }

     public String getFechaFormateadaParaLaCaratula(String fechaDeElaboracion)
     {
            String[] arrayFecha = fechaDeElaboracion.split("-");
            int anio = Integer.parseInt( arrayFecha[0]);
            int mes  = Integer.parseInt( arrayFecha[1]);
            String nombreMes = null;
            switch(mes)
            {
                 case 1:
                     nombreMes = "ENERO";
                     break;
                 case 2:
                      nombreMes = "FEBRERO";
                     break;
                 case 3:
                      nombreMes = "MARZO";
                     break;
                 case 4:
                      nombreMes = "ABRIL";
                     break;
                 case 5:
                     nombreMes = "MAYO";
                     break;
                 case 6:
                     nombreMes = "JUNIO";
                     break;
                 case 7:
                     nombreMes = "JULIO";
                     break;
                 case 8:
                     nombreMes = "AGOSTO";
                     break;
                 case 9:
                     nombreMes = "SETIEMBRE";
                     break;
                 case 10:
                     nombreMes = "OCTUBRE";
                     break;
                 case 11:
                     nombreMes = "NOVIEMBRE";
                     break;
                 case 12:
                     nombreMes = "DICIEMBRE";
                     break;
                 default:
                     nombreMes = "OTRO-XD";
                   break;
             }
             return nombreMes+"-"+anio;
     }
}
