package com;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

public class Utils {
    
    public static String detectText(String imgPath) {
    	
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
	
	public static Generator initGens(ArrayList<Character> symbols, int level) {
		
        if (level>0) {
            return new Generator(symbols, initGens(symbols, level-1));
        }
        
        return null;
    }
     
    public static ArrayList<Character> generateSymbols() {
        ArrayList<Character> symbols = new ArrayList<Character>();
        for (char c='0'; c<='9'; c++) symbols.add(c);
        for (char c='A'; c<='Z'; c++) symbols.add(c);
        for (char c='a'; c<='z'; c++) symbols.add(c);
        
        return symbols;
    }

}
