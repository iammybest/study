package com.iammybest.qr;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MrDeng on 2017/3/10.
 */
public class QRCodeUtil {
    /**
     * @param filePath 生成文件路径
     * @param fileName 生成文件名称
     * @param content  二维码内容
     */
    public static void encodeQR(String filePath, String fileName, String content) throws WriterException, IOException {
        MultiFormatWriter writer = new MultiFormatWriter();
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        BitMatrix qrImg = writer.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
        Path path = FileSystems.getDefault().getPath(filePath, fileName);
        MatrixToImageWriter.writeToPath(qrImg, "png", path);
    }

    public static String decodeQR(String file) throws WriterException, IOException, NotFoundException {
        BufferedImage image;
        image = ImageIO.read(new File(file));
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
        return result.getText();
    }

    public static void main(String[] args) {
        try {
            String content = "https://v.qq.com/x/cover/mzc0020085s9l32/h3128gqxa09.html" ;
            QRCodeUtil.encodeQR("D:\\", "vqq.png", new String(content.getBytes(), "ISO-8859-1"));

            String contentISO = QRCodeUtil.decodeQR("D:\\vqq.png");
            System.out.println(content);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
