package com.banglaocr.croppingpic;

import ij.process.ImageProcessor;

public class CroppingImage {

	/*
	 * This function takes argument the file path, the coordinates of upper left
	 * corner and the lower right corner
	 */

	public static ImageProcessor crop(ImageProcessor ip, int cropX, int cropY,
			int cropX1, int cropY1) {
		int targetWidth = Math.abs(cropX1 - cropX);
		int targetHeight = Math.abs(cropY1 - cropY);

		ip.setInterpolationMethod(ImageProcessor.BILINEAR);

		ip.setRoi(cropX, cropY, targetWidth, targetHeight);
		ip = ip.crop();

		return ip.resize(75, 75);

	}

}