package com;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test {

	public static void main(String[] args) throws IOException {
		
		/*
		 * Thread six = new Thread() {
		 * 
		 * @Override public void run() {
		 * 
		 * } };
		 */
		
		int count = 0;
		for (int i = 0; i < 999999; i++) {

			try {	

				Document doc = Jsoup.connect(new String("http://prnt.sc/" + generateUrl())).get();
				Elements img = doc.getElementsByTag("img");
				String src = img.first().absUrl("src");
				saveImage(src);
				System.out.println(count + "  " + i);
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
		System.out.println(count + " images found.");
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
		String generatedString = buffer.toString();

		return generatedString;
	}
}
