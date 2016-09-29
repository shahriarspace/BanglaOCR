package com.banglaocr.renamer;

import java.io.File;

import com.banglaocr.imagej.CharContainer;

public class Renamer {
	static CharContainer charContainer = new CharContainer();
	public static void renameChars(String dirName, String sufix) {


		File charsDir = new File(dirName);

		String[] charfiles = charsDir.list();
		

		for (String filename : charfiles) {

			File input = new File(dirName + File.separator + filename);
			
			if (charContainer.getCharName(filename) == null)
				continue;

			File dest = new File(dirName + File.separator
					+ charContainer.getCharName(filename) + "_" + sufix + ".png");

			input.renameTo(dest);
		}
	}

}
