package com;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test {

	public static final int MAX_KEY_SIZE = 7;
    
    public static void main(String[] args) throws IOException {
    	
    	System.out.println("Search started");
    	
    	//clean directory for images before start
    	FileUtils.cleanDirectory(new File("D:\\Images\\")); 
		ArrayList<Character> symbols = Utils.generateSymbols();
        
        Generator gen = Utils.initGens(symbols, MAX_KEY_SIZE);
        StringBuilder URLpart = new StringBuilder();
        
		int count = 0;
		while (count < 10) {
			try {	
				
				//generate part of URL
				gen.next();
	            URLpart.setLength(0);
	            gen.getKey(URLpart);
	            
	            //try to find image on generated URL 
				Document doc = Jsoup.connect(new String("http://prnt.sc/" + URLpart)).get();
				Elements img = doc.getElementsByTag("img");
				String src = img.first().absUrl("src");
				String pathToFile = Utils.saveImage(src);
				count++;
				
				//try to find text on image 
				System.out.println(count + 1 + ". image found; \nText:" );
				System.out.println(Utils.detectText(pathToFile));

			} catch (FileAlreadyExistsException ex) {
				continue;
			} catch (IOException ex) {
				System.err.println("There was an error:\r\n" + ex.toString());
			}
		}
		
		System.out.println("Totally " + count + " images found.");  
    }
}
