package com.project.util;

import com.project.entity.RequestController;
import net.sourceforge.tess4j.Tesseract;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

/**
 * @author taufik.budiyanto
 * @date 04/05/2021
 * com.bankmega.util
 */
public abstract class AbstractManagedBean {

    private Logger log = LoggerFactory.getLogger(getClass());
    protected String runningOcr(RequestController requestController){
        String dir =System.getProperty("user.dir") + File.separator +"tessdata";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(dir);
        log.info("{} :RUNNING OCR BASE64: {}",requestController.getId(),"OKE");
        try {
            return tesseract.doOCR(convertImage(requestController));
        }catch (Exception e){
            log.info("{} :ERROR RUNNING OCR: {}",requestController.getId(),e.getMessage());
            return e.getMessage();
        }
    }
    protected  BufferedImage convertImage(RequestController requestController){
        byte[] imageByte;
        BufferedImage image =null;
        log.info("{} :RUNNING CONVERT IMAGE: {}",requestController.getId(),"OKE");
        try {
            imageByte=Base64.decodeBase64(requestController.getImage());
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
//            FileUtils.writeByteArrayToFile(new File("D:\\Project\\Other\\DukcapilNew\\DukcapilUi\\download\\2202020\\"
//                    +filename), imageByte);
            bis.close();
        }catch (Exception e){
            log.info("{} :ERROR CONVERT IMAGE: {}",requestController.getId(),e.getMessage());
        }
        return image;
    }
}
