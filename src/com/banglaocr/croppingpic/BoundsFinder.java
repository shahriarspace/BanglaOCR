package com.banglaocr.croppingpic;

import ij.IJ;
import ij.process.ImageProcessor;

public class BoundsFinder {

	public int upperbound(ImageProcessor imageProcessor) {
		for (int i = 0; i < imageProcessor.getWidth(); i++) {

			for (int j = 0; j < imageProcessor.getHeight(); j++) {
				int pix = imageProcessor.getPixel(j, i);
				if (pix > 0) {
					return i;
				}
				// System.out.print(pix);
			}
		}
		return 0;
	}

	public int lowerbound(ImageProcessor imageProcessor) {

		for (int i = imageProcessor.getWidth(); i > 0; i--) {

			for (int j = imageProcessor.getHeight(); j > 0; j--) {
				int pix = imageProcessor.getPixel(j, i);
				if (pix > 0) {
					return i;
				}
			}

		}

		return 0;
	}

	public int leftbound(ImageProcessor imageProcessor) {

		for (int i = 0; i < imageProcessor.getWidth(); i++) {
			for (int j = 0; j < imageProcessor.getHeight(); j++) {
				int pix = imageProcessor.getPixel(i, j);
				if (pix > 0)
					return i;
			}
		}

		return 0;
	}

	public int rightbound(ImageProcessor imageProcessor) {

		for (int i = imageProcessor.getWidth(); i > 0; i--) {
			for (int j = imageProcessor.getHeight(); j > 0; j--) {
				int pix = imageProcessor.getPixel(i, j);
				if (pix > 0)
					return i;
			}
		}

		return 0;
	}

}
