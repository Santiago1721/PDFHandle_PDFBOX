import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.Loader;

public class contenido_entre_macadores {
    public static void main(String[] args) {
        String inputFilePath = ("C:\\Users\\Santiago\\Desktop\\PDF_prueba1.pdf");
        String outputDirectory = ("C:\\Users\\Santiago\\Desktop\\pdfpornumeros");

        try {
            splitPDFByBookmarkRange(inputFilePath, outputDirectory, "AMC072403224473", "MBI012403194319");
            System.out.println("PDF dividido por rango de marcadores exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void splitPDFByBookmarkRange(String inputFilePath, String outputDirectory, String startBookmarkName, String endBookmarkName) throws IOException {
        try (PDDocument document = Loader.loadPDF(new File(inputFilePath))) {
            PDDocumentOutline outline = document.getDocumentCatalog().getDocumentOutline();
            
            // Verificar si hay marcadores en el documento
            if (outline != null) {
                processBookmarksByRange(outline.getFirstChild(), document, outputDirectory, startBookmarkName, endBookmarkName);
            } else {
                System.out.println("El documento no contiene marcadores.");
            }
        }
    }

    private static void processBookmarksByRange(PDOutlineItem bookmark, PDDocument document, String outputDirectory, String startBookmarkName, String endBookmarkName) throws IOException {
        Map<String, PDDocument> extractedDocuments = new HashMap<>();
        boolean withinRange = false;

        while (bookmark != null) {
            String bookmarkName = bookmark.getTitle();

            if (bookmarkName.equals(startBookmarkName)) {
                System.out.println("Comienzo del rango encontrado: " + bookmarkName);
                withinRange = true;
            }

            if (withinRange) {
                PDDocument extractedDocument = extractedDocuments.get(bookmarkName);

                if (extractedDocument == null) {
                    extractedDocument = new PDDocument();
                    extractedDocuments.put(bookmarkName, extractedDocument);
                }

                PDPage page = bookmark.findDestinationPage(document);
                extractedDocument.addPage(page);
            }

            if (bookmarkName.equals(endBookmarkName)) {
                System.out.println("Fin del rango encontrado: " + bookmarkName);
                withinRange = false;
            }

            bookmark = bookmark.getNextSibling();
        }

        for (Map.Entry<String, PDDocument> entry : extractedDocuments.entrySet()) {
            String bookmarkName = entry.getKey();
            PDDocument extractedDocument = entry.getValue();

            String outputFilePath = outputDirectory + bookmarkName + ".pdf";
            extractedDocument.save(outputFilePath);
            extractedDocument.close();
            System.out.println("PDF creado: " + outputFilePath);
        }
    }
}
