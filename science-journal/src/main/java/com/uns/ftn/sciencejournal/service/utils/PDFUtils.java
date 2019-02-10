package com.uns.ftn.sciencejournal.service.utils;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.apache.tika.parser.txt.CharsetDetector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PDFUtils {

    public static String readFromPDF(String filePath){
        PdfReader reader;

        try {

            reader = new PdfReader(filePath);
            StringBuilder text = new StringBuilder();
            for(int i=1; i<=reader.getNumberOfPages(); i++) {
                text.append(PdfTextExtractor.getTextFromPage(reader, i));
            }

            reader.close();

            return text.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String writeToPDF(String filePath, String content){
        return "";
    }
}
