/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebs_pdf;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author Santiago
 */
public class extract_pages {
    public static void main(String [] args) throws IOException{
        
        File archivo_a_dividir = new File("C:\\Users\\Santiago\\Downloads\\PDF_prueba.pdf");
        
        PDDocument document = Loader.loadPDF(archivo_a_dividir);
        
        File nuevo_archivo_dividido = new File("C:\\Users\\Santiago\\Desktop\\prueba_pdf");
        nuevo_archivo_dividido.mkdirs();
        
        Splitter splitter = new Splitter();
        splitter.setStartPage(1);
        splitter.setEndPage(33);
        
        List<PDDocument> splitPages = splitter.split(document);
        
        PDDocument newDoc = new PDDocument();
        for(PDDocument mydoc : splitPages)
        {
            newDoc.addPage(mydoc.getPage(0));
        }
        newDoc.save(nuevo_archivo_dividido+"//dividido.pdf");
        
        newDoc.close();
        System.out.println("PDF Created");
             
    }
    
}
