package org.utils;

import org.utils.qr.QRGen;

import com.google.zxing.WriterException;

/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("unused")
    public static void main( String[] args )
    {
        try {
            QRGen code = new QRGen(args[0], args[1], QRGen.Formats.PNG);    
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
