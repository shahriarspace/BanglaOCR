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
