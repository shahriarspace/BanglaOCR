package com.banglaocr.ocr;

import java.util.ArrayList;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

public class ScannerOcr {

	public ArrayList<Integer> scanLeft(String filepath) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int countofwhitepix = 0;
		ImagePlus imagePlus = IJ.openImage(filepath);
		IJ.run(imagePlus, "Convert to Mask", "");
		ImageProcessor imageProcessor = imagePlus.getProcessor();

		for (int i = 0; i < imageProcessor.getHeight(); i++) {
			countofwhitepix = 0;
			for (int j = 0; j < imageProcessor.getWidth(); j++) {
				// imageProcessor.getPixel(j, i);
				if (imageProcessor.getPixel(j, i) == 0)
					countofwhitepix++;

				if (imageProcessor.getPixel(j, i) != 0)
					break;
				// System.out.print(imageProcessor.getPixel(j, i));
			}
			// System.out.println();
			// System.out.println(countofwhitepix);
			arrayList.add(new Integer(countofwhitepix));
		}
		return arrayList;

	}

	public ArrayList<Integer> scanRight(String filepath) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int countofwhitepix = 0;
		ImagePlus imagePlus = IJ.openImage(filepath);
		IJ.run(imagePlus, "Convert to Mask", "");
		ImageProcessor imageProcessor = imagePlus.getProcessor();
		imageProcessor.flipHorizontal();

		for (int i = 0; i < imageProcessor.getHeight(); i++) {
			countofwhitepix = 0;
			for (int j = 0; j < imageProcessor.getWidth(); j++) {
				// imageProcessor.getPixel(j, i);
				if (imageProcessor.getPixel(j, i) == 0)
					countofwhitepix++;

				if (imageProcessor.getPixel(j, i) != 0)
					break;
				// System.out.print(imageProcessor.getPixel(j, i));
			}
			// System.out.println();
			// System.out.println(countofwhitepix);
			arrayList.add(new Integer(countofwhitepix));
		}
		return arrayList;

	}

	public ArrayList<Integer> scanBottom(String filepath) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int countofwhitepix = 0;
		ImagePlus imagePlus = IJ.openImage(filepath);
		IJ.run(imagePlus, "Convert to Mask", "");
		ImageProcessor imageProcessor = imagePlus.getProcessor();
		imageProcessor.rotate(90);

		for (int i = 0; i < imageProcessor.getHeight(); i++) {
			countofwhitepix = 0;
			for (int j = 0; j < imageProcessor.getWidth(); j++) {
				// imageProcessor.getPixel(j, i);
				if (imageProcessor.getPixel(j, i) == 0)
					countofwhitepix++;

				if (imageProcessor.getPixel(j, i) != 0)
					break;
				// System.out.print(imageProcessor.getPixel(j, i));
			}
			// System.out.println();
			// System.out.println(countofwhitepix);
			arrayList.add(new Integer(countofwhitepix));
		}
		return arrayList;

	}


	
	
	public ArrayList<Integer> scanLeft(ImageProcessor imageProcessor) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int countofwhitepix = 0;

		for (int i = 0; i < imageProcessor.getHeight(); i++) {
			countofwhitepix = 0;
			for (int j = 0; j < imageProcessor.getWidth(); j++) {
				// imageProcessor.getPixel(j, i);
				if (imageProcessor.getPixel(j, i) == 0)
					countofwhitepix++;

				if (imageProcessor.getPixel(j, i) != 0)
					break;
				// System.out.print(imageProcessor.getPixel(j, i));
			}
			// System.out.println();
			// System.out.println(countofwhitepix);
			arrayList.add(new Integer(countofwhitepix));
		}
		return arrayList;

	}

	public ArrayList<Integer> scanRight(ImageProcessor imageProcessor) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int countofwhitepix = 0;

		imageProcessor.flipHorizontal();

		for (int i = 0; i < imageProcessor.getHeight(); i++) {
			countofwhitepix = 0;
			for (int j = 0; j < imageProcessor.getWidth(); j++) {
				// imageProcessor.getPixel(j, i);
				if (imageProcessor.getPixel(j, i) == 0)
					countofwhitepix++;

				if (imageProcessor.getPixel(j, i) != 0)
					break;
				// System.out.print(imageProcessor.getPixel(j, i));
			}
			// System.out.println();
			// System.out.println(countofwhitepix);
			arrayList.add(new Integer(countofwhitepix));
		}
		return arrayList;

	}

	public ArrayList<Integer> scanBottom(ImageProcessor imageProcessor) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int countofwhitepix = 0;

		imageProcessor.rotate(90);

		for (int i = 0; i < imageProcessor.getHeight(); i++) {
			countofwhitepix = 0;
			for (int j = 0; j < imageProcessor.getWidth(); j++) {
				// imageProcessor.getPixel(j, i);
				if (imageProcessor.getPixel(j, i) == 0)
					countofwhitepix++;

				if (imageProcessor.getPixel(j, i) != 0)
					break;
				// System.out.print(imageProcessor.getPixel(j, i));
			}
			// System.out.println();
			// System.out.println(countofwhitepix);
			arrayList.add(new Integer(countofwhitepix));
		}
		return arrayList;

	}

}