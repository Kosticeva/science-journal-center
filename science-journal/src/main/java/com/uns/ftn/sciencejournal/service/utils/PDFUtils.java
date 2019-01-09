package com.uns.ftn.sciencejournal.service.utils;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;

public class PDFUtils {

    public static String readFromPDF(String filePath){
        PdfReader reader;

        try {

            reader = new PdfReader(filePath);
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);
            reader.close();
            return textFromPage;

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String writeToPDF(String filePath, String content){
        return "";
    }
}
