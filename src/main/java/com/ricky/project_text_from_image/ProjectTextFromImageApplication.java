package com.ricky.project_text_from_image;

import com.recognition.software.jdeskew.ImageDeskew;
import com.ricky.project_text_from_image.constant.LanguageConstant;
import com.ricky.project_text_from_image.service.ReadImageService;
import com.ricky.project_text_from_image.service.WriteOutputService;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ProjectTextFromImageApplication {

    public static void main(String[] args) throws IOException {
        File imageWithWords1 = new File("src\\main\\resources\\ImageWithWords1.jpg");
        File imageWithWords2 = new File("src\\main\\resources\\ImageWithWords2.png");
        File imageWithWords3 = new File("src\\main\\resources\\ImageWithWords3.jpg");
        File imageWithWords4 = new File("src\\main\\resources\\ImageWithWords4.jpg");
        StringBuilder sbEng = new StringBuilder();
        sbEng.append(ReadImageService.read(imageWithWords1, LanguageConstant.ENG));
        sbEng.append(ReadImageService.read(imageWithWords2, LanguageConstant.ENG));
        sbEng.append(ReadImageService.read(imageWithWords3, LanguageConstant.ENG));
        WriteOutputService.write(sbEng.toString(), LanguageConstant.ENG);

        String chiText = ReadImageService.read(imageWithWords4, LanguageConstant.OUTPUT_FILE_CHI);
        WriteOutputService.write(chiText, LanguageConstant.OUTPUT_FILE_CHI);
    }
}
