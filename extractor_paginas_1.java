/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebs_pdf;

/**
 *
 * @author Santiago
 */
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.Loader;

public class extractor_paginas_1 {
    public static void main(String[] args) {
        String inputFilePath = ("C:\\Users\\Santiago\\Downloads\\PDF_prueba.pdf");
        String outputFilePath = ("C:\\Users\\Santiago\\Desktop\\PDFS XENOGENE\\prueba2.pdf");
        int startPage = 34; // Starting page index (1-based)
        int endPage = 70; // Ending page index

        try {
            extractPages(inputFilePath, outputFilePath, startPage, endPage);
            System.out.println("Pages extracted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void extractPages(String inputFilePath, String outputFilePath, int startPage, int endPage) throws IOException {
        try (PDDocument document = Loader.loadPDF(new File(inputFilePath))) {
            // Create a new document to hold extracted pages
            PDDocument extractedDocument = new PDDocument();

            // Iterate through pages and add selected pages to the new document
            for (int i = startPage - 1; i < endPage; i++) {
                PDPage page = document.getPage(i);
                extractedDocument.addPage(page);
            }

            // Save the extracted pages to a new PDF file
            extractedDocument.save(outputFilePath);
            extractedDocument.close();
        }
    }
}
