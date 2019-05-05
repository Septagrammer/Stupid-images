package com;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

public class Utils {
    
    public static String detect(String imgPath) {
    	
    	ITesseract instance = new Tesseract1();
    	instance.setDatapath("D:\\Media\\Sync\\Workspace\\Images\\tessdata");
    	
    	String result = "";
		try {
			result = instance.doOCR(new File(imgPath));
		} catch (TesseractException e) {
			e.printStackTrace();
		}
    	
    	return result;
    }
    
	public static String saveImage(String urlPart) throws IOException {

		URL url = new URL(urlPart);
		HttpURLConnection myURLConnection = (HttpURLConnection) url.openConnection();
		myURLConnection.setRequestProperty("User-Agent", "Chrome/51.0.2704.63");
		InputStream in = myURLConnection.getInputStream();

		Path path = Paths.get("D:\\Images\\" + urlPart.replaceAll("[/:.]", "") + ".png");
		Files.copy(in, path);
		return path.toString();
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
