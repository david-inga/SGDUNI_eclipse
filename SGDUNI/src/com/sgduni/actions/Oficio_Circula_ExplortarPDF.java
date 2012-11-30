package com.sgduni.actions;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orgen_ta_usuario;
import com.sgduni.forms.orpro_oficio_circular;
import com.sgduni.utilitarios.FormatoFecha;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Programmer : Marco A. Estrella Cardenas
 */

public class Oficio_Circula_ExplortarPDF extends org.apache.struts.action.Action
{
    orpro_oficio_circular oficio = null;

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        // -------------------------------------------
        //  CONSULTAS DE BASE DE DATOS
        // -------------------------------------------

        int idOficio =  Integer.parseInt( request.getParameter("idOficio").trim() );
        String numE = ( request.getParameter("enviados")!=null ) ? request.getParameter("enviados") : "0";

        DataSource dataSource = getDataSource(request, "DSconnection");

        orpro_oficio_circular_DAO daoOficio = new orpro_oficio_circular_DAO(dataSource);
        orgen_ta_dependencia_DAO daoDep = new orgen_ta_dependencia_DAO(dataSource);
        orgen_ta_facultad_DAO daoFac = new orgen_ta_facultad_DAO(dataSource);
        orgen_ta_usuario_DAO daoUsu = new orgen_ta_usuario_DAO(dataSource);

        if( numE.trim().equals("1")  )
        {
          oficio = daoOficio.getOficioParaModificar(idOficio);
        }
        else
        {
          int in_cod_fac_Dep = Integer.parseInt( request.getParameter("in_cod_fac_Dep").trim() );
          String ch_tipo_fac_dep = request.getParameter("ch_tipo_fac_dep").toString().trim();
          oficio = daoOficio.getOficioParaExportarPDF(idOficio,in_cod_fac_Dep,ch_tipo_fac_dep);
        }

        String direccion = "";
        String apartado = "";
        String telefono = "";
        String telefono_central = "";
        String anexo = "";
        String correo = "";
        String NombreOficina = "";

            if( oficio.getCh_tipo_fac_dep().trim().equals("d") )
            {
               orgen_ta_dependencia dependencia = daoDep.getDependencias(oficio.getIn_cod_fac_dep().toString());

               direccion = dependencia.getVc_direccion();
               apartado = dependencia.getVc_apartado();
               telefono = dependencia.getVc_telefono();
               telefono_central = dependencia.getVc_telefono_central();
               anexo = dependencia.getVc_anexo();
               correo = dependencia.getVc_correo();
               NombreOficina = dependencia.getVc_nombre();
            }
            else if( oficio.getCh_tipo_fac_dep().trim().equals("f") )
            {
               orgen_ta_facultad facultad = daoFac.getFacultad(oficio.getIn_cod_fac_dep().toString());

               direccion = facultad.getVc_direccion();
               apartado = facultad.getVc_apartado();
               telefono = facultad.getVc_telefono();
               telefono_central = facultad.getVc_telefono_central();
               anexo = facultad.getVc_anexo();
               correo = facultad.getVc_correo();
               NombreOficina = facultad.getVc_nombre();
            }

        //consultas respecto al usuario emisor y receptor

        orgen_ta_usuario usuarioEmisor = new orgen_ta_usuario();
        orgen_ta_usuario usuarioReceptor = new orgen_ta_usuario();

        usuarioEmisor = daoUsu.getUsuarioParaExportarOficio(oficio.getIn_usuario_emisor() );

        if(numE.trim().equals("1"))
        {
          usuarioReceptor = daoUsu.getUsuarioUsuarioJefeOCDO();
        }
        else
        {
          usuarioReceptor = daoUsu.getUsuarioParaExportarOficio(oficio.getIn_codigo_usuario() );
        }
        
        // --------------------------------------------
        //  CONSTRUCCION DEL DOCUMENTO
        // --------------------------------------------

        Document document = new Document(PageSize.A4,70,50,10,10);
        ByteArrayOutputStream baos = null;
        try
        {
          //fuentes para el tituto
          Font  fontTitulos = FontFactory .getFont("Arial", 20 , Font.NORMAL);
          fontTitulos.setColor(BaseColor.GRAY);
          Font  fontSubTitulos = FontFactory .getFont("Arial", 14 , Font.BOLD);
          Font  fechaCodigioCargo = FontFactory .getFont("Arial", 9 , Font.NORMAL);
          Font  fontNomAnio = FontFactory .getFont("Times-Roman", 14 , Font.BOLDITALIC);
          Font  fontPie = FontFactory .getFont("Arial", 9 , Font.NORMAL);
          Font  fontSubrayada = FontFactory .getFont("Arial", 9 , Font.ITALIC);

         //creando documento PDF
         baos = new ByteArrayOutputStream();
         PdfWriter writer = PdfWriter.getInstance(document, baos);
         //Rectangle rct = new Rectangle(36, 54, 559, 788);//36, 54, 559, 788..36,1,600,800
         Rectangle rct = new Rectangle(36, 54, 559, 788);//36, 54, 559, 788..36,1,600,800
         //Definimos un nombre y un tama�o para el PageBox los nombres posibles son: �crop�, �trim�, �art� and �bleed�.
         writer.setBoxSize("art", rct);
         //document.setPageSize(rct);
         document.setMarginMirroring(true);
         //df.setEncryption(PdfWriter.STRENGTH128BITS, "Hello", "World",0);
         //writer.setEncryption(USER, OWNER,0, PdfWriter.STANDARD_ENCRYPTION_128);
         //writer.setEncryption(USER, OWNER,0,PdfWriter.ALLOW_SCREENREADERS);
         //writer.setEncryption(USER, OWNER, PdfWriter.ALLOW_MODIFY_CONTENTS, PdfWriter.STANDARD_ENCRYPTION_128);
         writer.createXmpMetadata();

         document.open();
         HeaderFooter event = new HeaderFooter(fontPie, direccion,apartado,telefono,telefono_central,anexo,correo );

         writer.setPageEvent(event);
         
         PdfPTable tablalogo = new PdfPTable(2);
         //Establece el porcentaje de ancho que la tabla va a ocupar en la página.
         tablalogo.setWidthPercentage(100);
         //Establece el ancho de la tabla
         tablalogo.setTotalWidth(510f);
         //Utiliza el valor de setTotalWidth () en Document.add ().
         tablalogo.setLockedWidth(true);
         //Establece las anchuras relativas de la tabla.
         //float[] headerWidths={60,420,60};
         //tablalogo.setWidths(headerWidths);
         //tablalogo.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
         float[] columnWidths = {20f, 80f};
         tablalogo.setWidths(columnWidths);

         String rutalogo = getServlet().getServletContext().getRealPath("/") +"fileproject/img/sistema/logo.png";
         Image imglogo = Image.getInstance(rutalogo);
         imglogo.setAlignment(Image.RIGHT);
         imglogo.scaleAbsolute(85f, 110f);

         String rutafirmaemisor = getServlet().getServletContext().getRealPath("/") +"documentos/firmas/"+usuarioEmisor.getVc_nombre_archivo();
         Image firmaemisor = Image.getInstance(rutafirmaemisor);
         firmaemisor.setAlignment(Image.LEFT);
         firmaemisor.scaleAbsolute(90f, 70f);
         
         Paragraph titulo = new Paragraph(new Chunk("\nUNIVERSIDAD NACIONAL DE INGENIERIA",fontTitulos));
         Paragraph subtitulo = new Paragraph(new Chunk(NombreOficina,fontSubTitulos));
         //subtitulo.setAlignment(Element.ALIGN_MIDDLE|Element.ALIGN_CENTER);
         PdfPCell celltitulo = new PdfPCell(titulo);
         celltitulo.setHorizontalAlignment(20);
         celltitulo.setHorizontalAlignment(Element.ALIGN_MIDDLE);
         celltitulo.setVerticalAlignment(Element.ALIGN_CENTER);
         celltitulo.setBorder(PdfPCell.BOTTOM);

         PdfPCell cellsubtitulo = new PdfPCell(subtitulo);
         cellsubtitulo.setBorder(PdfPCell.NO_BORDER);
         cellsubtitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
         cellsubtitulo.setVerticalAlignment(Element.ALIGN_CENTER);
         
         PdfPCell cellImg = new PdfPCell(imglogo);
         cellsubtitulo.setVerticalAlignment(Element.ALIGN_CENTER);
         cellImg.setRowspan(2);
         cellImg.setBorder(PdfPCell.NO_BORDER);

         tablalogo.addCell(cellImg);
         tablalogo.addCell(celltitulo);
         tablalogo.addCell(cellsubtitulo);

         document.add(tablalogo);

         // nombre del anio
         String nomanio = "\""+oficio.getVc_nombre_anio()+"\"";
         Paragraph NombreAnio = new Paragraph(nomanio, fontNomAnio);
         NombreAnio.setAlignment(Paragraph.ALIGN_CENTER);
         document.add(NombreAnio);
         
         //cargar lugar y fecha
         String fechaylugar = oficio.getVc_ciudad()+", "+FormatoFecha.getFechaFormateada(oficio.getDt_fecha());
         Paragraph FechayLugar = new Paragraph(new Chunk(fechaylugar,fechaCodigioCargo));
         
         //cargar codigo del oficio
         String codigoOficio = "\n"+oficio.getCh_codigo_oficio();
         Paragraph CodigoOficio = new Paragraph(new Chunk(codigoOficio,fechaCodigioCargo));

         Paragraph nombre_receptor = new Paragraph(new Chunk("\n"+usuarioReceptor.getVc_grado_academico()+" "+usuarioReceptor.getVc_nombres(),fechaCodigioCargo));

         Paragraph cargo = new Paragraph(new Chunk(usuarioReceptor.getVc_cargo(),fechaCodigioCargo));

         Paragraph presente = new Paragraph(new Chunk("Presente.-",fontSubrayada));

         document.add(FechayLugar);
         document.add(CodigoOficio);
         document.add(nombre_receptor);
         document.add(cargo);
         document.add(presente);

         String str = oficio.getVc_cuerpo_doc();

         HTMLWorker htmlWorker = new HTMLWorker(document);
         htmlWorker.parse(new StringReader(str));
         htmlWorker.setMargins(35, 30,0,0);
         htmlWorker.newLine();

         Paragraph nombre_emisor = new Paragraph(new Chunk(usuarioEmisor.getVc_grado_academico()+" "+usuarioEmisor.getVc_nombres(),fechaCodigioCargo));

         Paragraph cargo_emisor = new Paragraph(new Chunk(""+usuarioEmisor.getVc_cargo(),fechaCodigioCargo ));

         document.add(firmaemisor);
         document.add(nombre_emisor);
         document.add(cargo_emisor);

         document.close();
        }
        catch(DocumentException e)
        {
            System.out.println("Error al crear el documento: "+e);
        }
        catch(Exception e)
        {
            System.out.println("Error al crear el documento, "+e);
        }
        
        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        ServletOutputStream out = response.getOutputStream();
        baos.writeTo(out);

        return null;
    }

    static class HeaderFooter extends PdfPageEventHelper
    {
        private Font fontpie;
        private String direccion;
        private String apartado;
        private String telefono;
        private String telefono_central;
        private String anexo;
        private String correo;

        public HeaderFooter(Font pie,String direccion,String apartado,String telefono,String telefono_central,String anexo,String correo)
        {
            this.fontpie = pie;
            this.direccion = direccion;
            this.telefono = telefono;
            this.telefono_central = telefono_central;
            this.anexo = anexo;
            this.correo = correo;
            this.apartado = apartado;
        }
        public void onEndPage (PdfWriter writer, Document document)
        {
         Rectangle rect = writer.getBoxSize("art");
         //Pie
         Paragraph linea1 = new Paragraph(new Chunk(direccion+" , Apartado "+apartado,fontpie));
         Paragraph linea2 = new Paragraph(new Chunk("Teléfono: "+telefono+" Central Telefónica: "+telefono_central+"  Anexos: "+anexo,fontpie));
         Paragraph linea3 = new Paragraph(new Chunk("E-Mail: "+correo,fontpie));

         ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, linea1, (rect.getLeft() + rect.getRight()) / 2 ,    rect.getBottom() - 18, 0  );
         ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, linea2, (rect.getLeft() + rect.getRight()) / 2 ,    rect.getBottom() - 28, 0  );
         ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, linea3, (rect.getLeft() + rect.getRight()) / 2 ,    rect.getBottom() - 38, 0  );
        }
    }
}
