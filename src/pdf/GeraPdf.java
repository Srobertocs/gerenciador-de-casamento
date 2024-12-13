/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author SOUSA
 */
public class GeraPdf {
    
    public void gerarPdf(String nomeArquivo, String texto){
    
        Document document = new Document();
        
        try {
            //Criar e escrever no arquivo pdf
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo + ".pdf"));
            document.open();
            document.add(new Paragraph(texto));
        } catch (Exception e){
            System.out.println(e);
        }
        finally {
            document.close();
        }
        try {
        //Abrir o documento pdf   
        Desktop.getDesktop().open(new File(nomeArquivo + ".pdf"));
        } catch (Exception e2) {
            System.out.println(e2);
        }
    }   
}
