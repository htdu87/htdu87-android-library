package com.htdu87.android.library.utils;

import android.util.Base64;
import android.util.Base64OutputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utility {

    /**
     * Create basic credential for Http request
     * @param usr
     * @param pwd
     * @return String credential
     */
    public static String createBasicCredential(String usr,String pwd){
        return "Basic " + new String(Base64.encode((usr+":"+pwd).getBytes(), Base64.NO_WRAP));
    }

    /**
     * Encode file to base64 string
     * @param path
     * @return Base64 string (use URL encode function when post as url encode)
     * @throws IOException
     */
    public static String base64FileEncode(String path) throws IOException {
        File file=new File(path);
        InputStream in=new FileInputStream(file.getAbsolutePath());
        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Base64OutputStream output64 = new Base64OutputStream(output, Base64.NO_WRAP);
        while ((bytesRead = in.read(buffer)) != -1) {
            output64.write(buffer, 0, bytesRead);
        }
        output64.close();
        return output.toString();
    }

    /**
     * Encode stream to base64 string
     * @param in
     * @return Base64 string (use URL encode function when post as url encode)
     * @throws IOException
     */
    public static String base64FileEncode(InputStream in) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Base64OutputStream output64 = new Base64OutputStream(output, Base64.NO_WRAP);
        while ((bytesRead = in.read(buffer)) != -1) {
            output64.write(buffer, 0, bytesRead);
        }
        output64.close();
        return output.toString();
    }

    /**
     * Decode base64 string to file
     * @param encode
     * @param path
     * @throws IOException
     */
    public static void base64FileDecode(String encode,String path) throws IOException {
        OutputStream out=new FileOutputStream(path);
        byte[] in=Base64.decode(encode,Base64.NO_WRAP);
        out.write(in);
        out.flush();
        out.close();
    }
}
