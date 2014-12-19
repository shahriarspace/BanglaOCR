package com.banglaocr.imagej;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;

import com.sun.jndi.toolkit.url.Uri;
import com.sun.org.apache.xerces.internal.util.URI;

public class CharContainer {

	// This function reads the text file Re.txt and find the approbate name for
	// the character. It takes the file name of the extracted character. And and
	// returns the approbate name of the character.
	public String getCharName(String filename) {
		String string = null;
		try {
			InputStream inputStream = CharContainer.class
					.getResourceAsStream("/" + "Re.txt");
			Scanner scanner = new Scanner(inputStream);
			while (scanner.hasNextLine()) {
				String string1 = scanner.nextLine();
				String[] strings = string1.split(" ");
				if (strings[0].equals(FilenameUtils.getBaseName(filename)))
					string = strings[2];
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}

	public static String getCharFileName(String filename) {
		String string = null;
		try {
			Scanner scanner = new Scanner(new File("Re.txt"));
			while (scanner.hasNextLine()) {
				String string1 = scanner.nextLine();
				String[] strings = string1.split(" ");
				if (strings[0].equals(FilenameUtils.getBaseName(filename)))
					string = strings[0];
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return string;
	}
}
