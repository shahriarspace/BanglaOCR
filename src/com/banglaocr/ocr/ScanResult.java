package com.banglaocr.ocr;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import com.banglaocr.dto.Bean;
import com.banglaocr.traning.ImageAdder;

public class ScanResult {

	public static String traningDir = ImageAdder.croppedTrainCharDirName;

	public String getScanResultforleft(String inputfile) {

		String matchedfilename = null;

		ScannerOcr ocr = new ScannerOcr();
		ArrayList<Integer> arrayList = ocr.scanLeft(inputfile);

		MeanSquare meanSquare = new MeanSquare();
		HashMap<String, Double> hashMap = new HashMap<String, Double>();

		File file = new File(traningDir);
		String[] files = file.list();
		for (String filename : files) {
			ArrayList<Integer> arrayList2 = ocr.scanLeft(traningDir
					+ File.separator + filename);
			hashMap.put(filename,
					meanSquare.getmeansquare(arrayList, arrayList2));
		}

		Double minValue = (Collections.min(hashMap.values()));

		for (Entry<String, Double> entry : hashMap.entrySet()) {
			if (entry.getValue() == minValue) {
				matchedfilename = entry.getKey();
				// System.out.println(entry.getKey());
			}
		}
		return matchedfilename;

	}

	public String getScanResultforright(String inputfile) {

		String matchedfilename = null;

		ScannerOcr ocr = new ScannerOcr();
		ArrayList<Integer> arrayList = ocr.scanRight(inputfile);

		MeanSquare meanSquare = new MeanSquare();
		HashMap<String, Double> hashMap = new HashMap<String, Double>();

		File file = new File(traningDir);
		String[] files = file.list();
		for (String filename : files) {
			ArrayList<Integer> arrayList2 = ocr.scanRight(traningDir
					+ File.separator + filename);
			hashMap.put(filename,
					meanSquare.getmeansquare(arrayList, arrayList2));
		}

		Double minValue = (Collections.min(hashMap.values()));

		for (Entry<String, Double> entry : hashMap.entrySet()) {
			if (entry.getValue() == minValue) {
				matchedfilename = entry.getKey();
				// System.out.println(entry.getKey());
			}
		}
		return matchedfilename;

	}

	public String getScanResultforbothside(String inputfile) {

		String matchedfilename = null;

		ScannerOcr ocr = new ScannerOcr();
		ArrayList<Integer> arrayList = ocr.scanLeft(inputfile);
		ArrayList<Integer> arrayList3 = ocr.scanRight(inputfile);

		MeanSquare meanSquare = new MeanSquare();
		HashMap<String, Double> hashMap = new HashMap<String, Double>();
		HashMap<String, Double> hashMap1 = new HashMap<String, Double>();

		File file = new File("test");
		String[] files = file.list();
		for (String filename : files) {
			ArrayList<Integer> arrayList2 = ocr.scanLeft(traningDir
					+ File.separator + filename);
			hashMap.put(filename,
					meanSquare.getmeansquare(arrayList, arrayList2));
		}

		for (String filename : files) {
			ArrayList<Integer> arrayList4 = ocr.scanRight(traningDir
					+ File.separator + filename);
			hashMap1.put(filename,
					meanSquare.getmeansquare(arrayList3, arrayList4));
		}

		HashMap<String, Double> hashMap3 = new HashMap<String, Double>();

		for (String filename : files) {
			hashMap3.put(filename,
					hashMap.get(filename) + hashMap1.get(filename));
		}

		Double minValue = (Collections.min(hashMap3.values()));

		for (Entry<String, Double> entry : hashMap3.entrySet()) {
			if (entry.getValue() == minValue) {
				matchedfilename = entry.getKey();
				// System.out.println(entry.getKey());
			}
		}
		return matchedfilename;

	}

	public String getScanResultforthreeside(String inputfile,
			ArrayList<Bean> beans) {

		String matchedfilename = null;

		ScannerOcr ocr = new ScannerOcr();
		ArrayList<Integer> inputLeft = ocr.scanLeft(inputfile);
		ArrayList<Integer> inputRight = ocr.scanRight(inputfile);
		ArrayList<Integer> inputBottom = ocr.scanBottom(inputfile);

		MeanSquare meanSquare = new MeanSquare();
		HashMap<String, Double> sumofLeftRightBottomDistances = new HashMap<String, Double>();

		for (Bean bean : beans) {

			sumofLeftRightBottomDistances.put(
					bean.getName(),
					meanSquare.getmeansquare(inputLeft, bean.getLeft())
							+ meanSquare.getmeansquare(inputRight,
									bean.getRihgt())
							+ meanSquare.getmeansquare(inputBottom,
									bean.getBottom()));
		}

		Double minValue = (Collections.min(sumofLeftRightBottomDistances
				.values()));

		for (Entry<String, Double> entry : sumofLeftRightBottomDistances
				.entrySet()) {
			if (entry.getValue() == minValue) {
				matchedfilename = entry.getKey();
			}
		}

		return matchedfilename + "_" + minValue;

	}

}
