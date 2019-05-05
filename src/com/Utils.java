package com;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.leptonica.global.lept;
import org.bytedeco.tesseract.TessBaseAPI;

public class Utils {
    
    public static String detect(String imgPath) {
    	
    	TessBaseAPI api = new TessBaseAPI();
    	api.Init(System.getProperty("user.dir") + "\\tessdata-master\\", "eng");
    	PIX image = lept.pixRead(imgPath);
    	api.SetImage(image);
    	BytePointer bp = api.GetUTF8Text();
    	String output = bp.getString();
    	api.close();
    	
    	return output;
    }
    
	public static void saveImage(String urlPart) throws IOException {

		URL url = new URL(urlPart);
		HttpURLConnection myURLConnection = (HttpURLConnection) url.openConnection();
		myURLConnection.setRequestProperty("User-Agent", "Chrome/51.0.2704.63");
		InputStream in = myURLConnection.getInputStream();

		Path path = Paths.get("D:\\Images\\" + urlPart.replaceAll("[/:.]", "") + ".png");
		Files.copy(in, path);
	}
	
	public static String generateUrl() {

		int targetStringLength = 7;
		//String all = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String all = "abcdefghijklmnopqrstuvwxyz0123456789";

		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt =(int) (random.nextFloat() * all.length());
			buffer.append(all.charAt(randomLimitedInt));
		}
		String generatedString = "http://prnt.sc/" + buffer.toString();

		return generatedString;
	}

}
