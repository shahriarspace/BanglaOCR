package com.banglaocr.traning;

import ij.process.ImageProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.sourceforge.javaocr.ocrPlugins.CharacterExtractor;
import net.sourceforge.javaocr.ocrPlugins.LineExtractor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.banglaocr.croppingpic.SaveCroppedImage;
import com.banglaocr.dto.Bean;
import com.banglaocr.dto.TranningBean;
import com.banglaocr.imagej.WholeChecker;
import com.banglaocr.ocr.ScannerOcr;
import com.banglaocr.renamer.Renamer;

/**
 * @author Shahriar Robbani and Aklima Zaman
 *
 */
public class ImageAdder {

	public static final String lineDirName = "lines";
	public static File linesDir = new File(lineDirName);

	public static final String charDirName = "Chars";
	public static File charDir = new File(charDirName);

	public static final String croppedTrainCharDirName = "croppedTrainChars";
	public static File croppedTrainCharDir = new File(croppedTrainCharDirName);

	static File fontFile;

	// This Function extracts the characters of an given image.
	public static void extractChars(String filepath) {

		fontFile = new File(filepath);

		if (!linesDir.exists()) {
			linesDir.mkdir();
		}

		LineExtractor extractor = new LineExtractor();

		extractor.slice(fontFile, linesDir);

		CharacterExtractor characterExtractor = new CharacterExtractor();

		if (!charDir.exists()) {
			charDir.mkdir();
		}

		String[] files = linesDir.list();

		for (int i = 0; i < files.length; i++) {
			String string = "line_" + i + ".png";
			try {
				characterExtractor.slice(new File(lineDirName + File.separator
						+ string), charDir, 75, 75);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}

		FileUtils.deleteQuietly(linesDir);

	}

	public static void rename() {
		String filename = FilenameUtils.getBaseName(fontFile.getName());

		Renamer.renameChars(charDirName, filename);

	}

	public static TranningBean addTrainingImage(String filepath) {
		extractChars(filepath);
		rename();
		if (!croppedTrainCharDir.exists()) {
			croppedTrainCharDir.mkdir();
		}

		HashMap<String, ImageProcessor> croped = SaveCroppedImage.crop(
				charDirName, croppedTrainCharDirName);

		Iterator it = croped.entrySet().iterator();

		ArrayList<Bean> beansWithHole = new ArrayList<Bean>();
		ArrayList<Bean> beansWithOutHole = new ArrayList<Bean>();

		ScannerOcr ocr = new ScannerOcr();
		WholeChecker checker = new WholeChecker();
		TranningBean tBean = new TranningBean();
		while (it.hasNext()) {
			Map.Entry<String, ImageProcessor> entry = (Entry<String, ImageProcessor>) it
					.next();
			Bean bean = new Bean();
			bean.setName(entry.getKey());
			bean.setLeft(ocr.scanLeft(entry.getValue()));
			bean.setRihgt(ocr.scanRight(entry.getValue()));
			bean.setBottom(ocr.scanBottom(entry.getValue()));
			if (checker.isHole(entry.getValue())) {
				beansWithHole.add(bean);
			} else {
				beansWithOutHole.add(bean);
			}
		}
		tBean.setWithHole(beansWithHole);
		tBean.setWithOutHole(beansWithOutHole);

		FileUtils.deleteQuietly(charDir);

		return tBean;

	}

	public static void clearAllTempImages() {

		FileUtils.deleteQuietly(croppedTrainCharDir);

	}
}