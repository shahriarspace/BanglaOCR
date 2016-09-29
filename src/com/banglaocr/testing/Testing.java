package com.banglaocr.testing;

import ij.ImagePlus;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.io.FileUtils;

import com.banglaocr.croppingpic.SaveCroppedImage;
import com.banglaocr.dto.Bean;
import com.banglaocr.dto.TranningBean;
import com.banglaocr.imagej.CharContainer;
import com.banglaocr.imagej.WholeChecker;
import com.banglaocr.ocr.ScanResult;
import com.banglaocr.traning.ImageAdder;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Testing extends ImageAdder {

	public static final String croppedTestingCharDirName = "croppedTestingChars";
	public static File croppedTestingCharDir = new File(
			croppedTestingCharDirName);

	ArrayList<Object[]> rowData = new ArrayList<Object[]>();
	BufferedImage subImg = null;
	ImageIcon icon;

	CharContainer charContainer = new CharContainer();

	public void addTestingImage(String filepath, TranningBean tBean) {
		try {
			extractChars(filepath);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		if (!croppedTestingCharDir.exists()) {
			croppedTestingCharDir.mkdir();
		}

		try {
			SaveCroppedImage.crop(charDirName, croppedTestingCharDirName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		FileUtils.deleteQuietly(charDir);
		scanTraningImage(tBean);
	}

	public void scanTraningImage(TranningBean tBean) {

		String[] files = new File(croppedTestingCharDirName).list();
		WholeChecker checker = new WholeChecker();
		ScanResult result = new ScanResult();
		ArrayList<String> actualFileNames = new ArrayList<String>();
		ArrayList<String> foundFileNames = new ArrayList<String>();
		for (String file : files) {
			String realfilepath = croppedTestingCharDirName + File.separator
					+ file;
			actualFileNames.add(charContainer.getCharName(file));
			String[] actuSt;
			if (checker.isHole(new ImagePlus(realfilepath).getProcessor())) {
				actuSt = result.getScanResultforthreeside(realfilepath,
						tBean.getWithHole()).split("_");
				System.out.println(actuSt[0]+"Hole");
			} else {
				actuSt = result.getScanResultforthreeside(realfilepath,
						tBean.getWithOutHole()).split("_");
				System.out.println(actuSt[0]+"HoleNai");
			}

//			System.out.println(actuSt[2]);

			double th = Double.parseDouble(actuSt[2]);

			if (th < 600.00) {
				foundFileNames.add(actuSt[0]);
				try {
					subImg = ImageIO.read(new File(realfilepath));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
				icon = new ImageIcon(subImg);
				Object[] rowDataarray = { actuSt[0], icon, actuSt[2] };
				rowData.add(rowDataarray);
			}

		}

		try {
			getStatistic(actualFileNames, foundFileNames);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void getStatistic(ArrayList<String> actualFileNames,
			ArrayList<String> foundFileNames) {
		int counter = 0;
		for (int i = 0; i < actualFileNames.size(); i++) {
			if (actualFileNames.get(i).equals(foundFileNames.get(i))) {

				// System.out.println("Actually it is: " +
				// actualFileNames.get(i)
				// + " And it detects it as: " + foundFileNames.get(i));
				counter++;
			} else {
				// System.out.println("Actually it is: " +
				// actualFileNames.get(i)
				// + " But it detects it as: " + foundFileNames.get(i));
			}
		}

		int total = actualFileNames.size();

		// System.out.println("Total Matched " + counter + " out of " + total);

		try {
			InputStream inputStream = Testing.class.getResourceAsStream("/"
					+ "matched.png");
			subImg = ImageIO.read(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		icon = new ImageIcon(subImg);
		Object[] rowDataarray = {
				"Total Matched " + counter + " out of " + total, icon };
		rowData.add(rowDataarray);

		int missmatched = actualFileNames.size() - counter;
		// System.out.print("Total Mismatched Characters:" + mismatched);

		try {
			InputStream inputStream = Testing.class.getResourceAsStream("/"
					+ "errors.png");
			subImg = ImageIO.read(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		icon = new ImageIcon(subImg);
		Object[] rowDataarray1 = {
				"Total Mismatched Characters:" + missmatched, icon };
		rowData.add(rowDataarray1);

	}

	public ArrayList<Object[]> getRowData() {
		return rowData;
	}

	public static void clearAllTempImages() {

		FileUtils.deleteQuietly(croppedTestingCharDir);

	}

}
