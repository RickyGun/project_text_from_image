package com.ricky.project_text_from_image.service;

import com.ricky.project_text_from_image.constant.LanguageConstant;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
@Service
public class WriteOutputService {

    public static void write(String text, String lang) {
        String outputFIle = "";
        if(LanguageConstant.ENG.equals(lang)){
            outputFIle = LanguageConstant.OUTPUT_FILE_ENG;
        } else {
            outputFIle = LanguageConstant.OUTPUT_FILE_CHI;
        }

        File file = new File(outputFIle);
        if (file.exists()) {
            System.out.println("delete already exist File");
            file.delete();
        }
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            String htmlPage = "<html><body style=’background-color:#ccc’><b><h3><center><u>OUTPUT ENG</u></center></h3></b>";
            bufferedWriter.write(htmlPage);
            if (text.toLowerCase().contains("o") && LanguageConstant.ENG.equals(lang)) {
                bufferedWriter.append("<span style=\"color:blue;\">");
            } else {
                bufferedWriter.append("<span>");
            }
            bufferedWriter.append(text);
            bufferedWriter.append("</span></body></html>");
            System.out.println("Html page created");
            bufferedWriter.flush();
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
