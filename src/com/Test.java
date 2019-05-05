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

	public static final int MAX_KEY_SIZE = 5;
    
    public static void main(String[] args) {
    	
    	ArrayList<Character> symbols = generateSymbols();
         
        Generator gen = initGens(symbols, MAX_KEY_SIZE);
         
        StringBuilder key = new StringBuilder();
        while(true) {
        	System.out.println(key);
            gen.next();
            key.setLength(0);
            gen.getKey(key);
        }    
    }
 
    private static Generator initGens(ArrayList<Character> symbols, int level) {
        if (level>0) {
            return new Generator(symbols, initGens(symbols, level-1));
        }
        return null;
    }
     
    private static ArrayList<Character> generateSymbols() {
        ArrayList<Character> symbols = new ArrayList<Character>();
        for (char c='0'; c<='9'; c++) symbols.add(c);
        for (char c='A'; c<='Z'; c++) symbols.add(c);
        for (char c='a'; c<='z'; c++) symbols.add(c);
        return symbols;
    }
	
	public static void find() throws IOException {
		FileUtils.cleanDirectory(new File("D:\\Images\\")); 
		System.out.println("Search started");
		int count = 0;
		while (count < 10) {
			try {	
				
				Document doc = Jsoup.connect(new String(Utils.generateUrl())).get();
				Elements img = doc.getElementsByTag("img");
				String src = img.first().absUrl("src");
				String pathToFile = Utils.saveImage(src);
				System.out.println(count + 1 + ". image found;" );
				System.out.println(src);
				System.out.println(Utils.detect(pathToFile));
				count++;
				Thread.sleep(1);
				
			} catch (FileAlreadyExistsException ex) {
				continue;
			} catch (IOException ex) {
				System.err.println("There was an error:\r\n" + ex.toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Totally " + count + " images found.");
	}
	

}
