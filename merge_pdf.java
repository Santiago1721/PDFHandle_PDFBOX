/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebs_pdf;

import java.io.File;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

/**
 *
 * @author Santiago
 */
public class merge_pdf {
    public static void main(String [] args){
        File primerArchivo = new File("C:\\Users\\Santiago\\Downloads\\PDF_prueba.pdf");
        
        
        File nuevoArchivo = new File("C:\\Users\\Santiago\\Desktop\\prueba_pdf");
        
        nuevoArchivo.mkdirs();
        
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        pdfMergerUtility.setDestinationFileName(nuevoArchivo+"nuevoarchivo.pdf");
        
        //pdfMergerUtility.addSource(primerArchivo);
    }
    
}
