package org.utils.qr;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRGen {

    public static enum Formats {
        PNG("png"),
        JPG("jpg");

        private final String fmt;

        Formats(String _fmt) {
            fmt = _fmt;
        }

        public String getFMT() {
            return fmt;
        }
    }

    public QRGen(String content, String path, Formats fmt) throws WriterException {
        // Create the ByteMatrix for the QR-Code that encodes the given String.
        System.out.println("Encoding: " + content.length() + " to QR Code");
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 100, 100, hintMap);

        // Make the BufferedImage that are to hold the QRCode 
        int matrixWidth = byteMatrix.getWidth();

        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D)image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);

        // Paint and save the image using the ByteMatrix 

        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j) == true) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        File outputfile = new File(path + "." + fmt.getFMT());
    
        try {
            ImageIO.write(image, fmt.getFMT(), outputfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
