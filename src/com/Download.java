package com;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Download {

	public Download() {

	}

	public static String generateUrl() {

		int targetStringLength = 9;
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

	public static void saveImage(String urlPart) throws IOException {

		URL url = new URL(urlPart);
		HttpURLConnection myURLConnection = (HttpURLConnection) url.openConnection();
		myURLConnection.setRequestProperty("User-Agent", "Chrome/51.0.2704.63");
		InputStream in = myURLConnection.getInputStream();

		Path path = Paths.get("D:\\Images\\" + urlPart.replaceAll("[/:.]", "") + ".png");
		Files.copy(in, path);
	}
}
