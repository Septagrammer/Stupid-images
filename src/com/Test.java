package com;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test {

	public static void main(String[] args) throws IOException {
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
