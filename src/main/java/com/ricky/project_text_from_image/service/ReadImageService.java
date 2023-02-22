package com.ricky.project_text_from_image.service;

import com.recognition.software.jdeskew.ImageDeskew;
import com.ricky.project_text_from_image.constant.LanguageConstant;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
@Service
public class ReadImageService {
    static final double MINIMUM_DESKEW_THRESHOLD = 0.05d;

    public static String read(File imageFile, String lang) {
        String result = "";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(LanguageConstant.TESSDATA_PATH);
        tesseract.setTessVariable(LanguageConstant.USER_DEFINED_DPI, "300");
        tesseract.setLanguage(lang);
        tesseract.setLanguage("eng");
		tesseract.setLanguage("chi_sim");

        BufferedImage bi;
        try {
            bi = ImageIO.read(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageDeskew id = new ImageDeskew(bi);
        double imageSkewAngle = id.getSkewAngle();
        if ((imageSkewAngle > MINIMUM_DESKEW_THRESHOLD || imageSkewAngle < -(MINIMUM_DESKEW_THRESHOLD))) {
            bi = ImageHelper.rotateImage(bi, -imageSkewAngle);
        }

        try {
            result = tesseract.doOCR(bi);
            result = result.replaceAll("[^a-zA-Z0-9\\s]", "");
//            System.out.println(result);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
