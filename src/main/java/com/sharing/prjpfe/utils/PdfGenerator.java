package com.sharing.prjpfe.utils;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;
@Service
public class PdfGenerator {
    public void pdfAcuse(LocalDate date,String PrenomNomClient,Long idDemande,String typeDemande,String agence) throws FileNotFoundException, MalformedURLException {
        String path="C:\\Users\\adib\\Desktop\\sharing\\PrjPfe\\src\\main\\java\\com\\sharing\\prjpfe\\utils\\test.pdf";
        PdfWriter pdfw=new PdfWriter(path);
        PdfDocument pdfDoc=new PdfDocument(pdfw);
        pdfDoc.addNewPage();
        Document doc=new Document(pdfDoc);
        Paragraph header = new Paragraph("date:"+LocalDate.now()+"\n"+"agence: el mourouj").setFontSize(10).setMarginTop(-40);
        ImageData imgdt= ImageDataFactory.create("C:\\Users\\adib\\Desktop\\logo.gif");
        Image img=new Image(imgdt)
                .scale(0.7F, 0.7F)
                .setMarginLeft(400);
        Paragraph title = new Paragraph("Accusé de reception de demande")
                .setFontSize(14)
                .setBold()
                .setMarginTop(100)
                .setTextAlignment(TextAlignment.CENTER);
        Paragraph description = new Paragraph("cher "+PrenomNomClient+", \n je vous informe que la demande numero "+idDemande+" de que vous avez deposé le "+date+"  de type accés est validé et pris en considerations par l'equipe gdpr.\nLes resultats seront delivrées dans le delai maximal de huite semaines.\nCordialement.")
                .setFontSize(10)
                .setMarginLeft(80)
                .setMarginRight(80)
                .setMarginTop(100);

        doc.add(img);
        doc.add(header);
        doc.add(title);

        doc.add(description);

        doc.close();
    }
    public byte[] pdfRapportSuccesAccess(String dep, Long id, String des, String type_de, String name, String resultat, List<String> noms) throws FileNotFoundException, MalformedURLException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(baos);

        // create a PdfDocument instance from the PdfWriter
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);

        // create a Document instance from the PdfDocument
        Document doc = new Document(pdfDoc);
        pdfDoc.addNewPage();


        Color color = new DeviceRgb(71, 187, 190);
        Text res=new Text(resultat).setFontColor(color);
        Paragraph header = new Paragraph("date:"+LocalDate.now()+"\n"+"service:"+dep).setFontSize(10).setMarginTop(-40);
        ImageData imgdt= ImageDataFactory.create("C:\\Users\\adib\\Desktop\\logo.gif");
        Image img=new Image(imgdt)
                .scale(0.7F, 0.7F)
                .setMarginLeft(400);
        Paragraph title = new Paragraph("rapport de tache effectué")
                .setFontSize(14)
                .setBold()
                .setMarginTop(100)
                .setTextAlignment(TextAlignment.CENTER);
        Paragraph description = new Paragraph("reference de tache: "+id +
                "\n  description de tache:" +des+
                ".\ntype de demande: " +type_de+
                "\nclient: " +name+
                "\nrésultat :").add(res)
                .setFontSize(10)
                .setMarginLeft(80)
                .setMarginRight(80)
                .setMarginTop(100);
        Paragraph fich = new Paragraph( )
                .setFontSize(10)
                .setMarginLeft(80)
                .setMarginRight(80)
                .setMarginTop(10);
        if(type_de.equals("acces")){
        fich.add("documents liés au tache: \n");
        for (int i=0;i<noms.size();i++){
            Text f=new Text("document n°"+i+" : "+noms.get(i)+"\n");
            fich.add(f);
        }}

        doc.add(img);
        doc.add(header);
        doc.add(title);
        doc.add(description);
        doc.add(fich);
        doc.close();
        return baos.toByteArray();
    }
    public byte[] pdfRapportrefuse(String dep, Long id, String des, String type_de, String name, String resultat, String comment) throws FileNotFoundException, MalformedURLException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        Document doc = new Document(pdfDoc);
        pdfDoc.addNewPage();


        Color color = new DeviceRgb(211, 74, 107);
        Text res=new Text("refuse").setFontColor(color);
        Paragraph header = new Paragraph("date:"+LocalDate.now()+"\n"+"service:"+dep).setFontSize(10).setMarginTop(-40);
        ImageData imgdt= ImageDataFactory.create("C:\\Users\\adib\\Desktop\\logo.gif");
        Image img=new Image(imgdt)
                .scale(0.7F, 0.7F)
                .setMarginLeft(400);
        Paragraph title = new Paragraph("rapport de tache effectué")
                .setFontSize(14)
                .setBold()
                .setMarginTop(100)
                .setTextAlignment(TextAlignment.CENTER);
        Paragraph description = new Paragraph("reference de tache: "+id +
                "\n  description de tache:" +des+
                ".\ntype de demande: " +type_de+
                "\nclient: " +name+
                "\nrésultat :").add(res)
                .setFontSize(10)
                .setMarginLeft(80)
                .setMarginRight(80)
                .setMarginTop(100);
        Paragraph commentaire = new Paragraph("commentaire:").add(comment)
                .setFontSize(10)
                .setMarginLeft(80)
                .setMarginRight(80)
                .setMarginTop(10);

        doc.add(img);
        doc.add(header);
        doc.add(title);
        doc.add(description);
        doc.add(commentaire);
        doc.close();
        return baos.toByteArray();
    }

    public byte[] pdfRapportSucces(String dep, Long id, String des, String type_de, String name, String resultat) throws MalformedURLException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        Document doc = new Document(pdfDoc);
        pdfDoc.addNewPage();


        Color color = new DeviceRgb(71, 187, 190);
        Text res=new Text("Succés").setFontColor(color);
        Paragraph header = new Paragraph("date:"+LocalDate.now()+"\n"+"service:"+dep).setFontSize(10).setMarginTop(-40);
        ImageData imgdt= ImageDataFactory.create("C:\\Users\\adib\\Desktop\\logo.gif");
        Image img=new Image(imgdt)
                .scale(0.7F, 0.7F)
                .setMarginLeft(400);
        Paragraph title = new Paragraph("rapport de tache effectué")
                .setFontSize(14)
                .setBold()
                .setMarginTop(100)
                .setTextAlignment(TextAlignment.CENTER);
        Paragraph description = new Paragraph("reference de tache: "+id +
                "\n  description de tache:" +des+
                ".\ntype de demande: " +type_de+
                "\nclient: " +name+
                "\nrésultat :").add(res)
                .setFontSize(10)
                .setMarginLeft(80)
                .setMarginRight(80)
                .setMarginTop(100);


        doc.add(img);
        doc.add(header);
        doc.add(title);
        doc.add(description);
        doc.close();
        return baos.toByteArray();
    }
}