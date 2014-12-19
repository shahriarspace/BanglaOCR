package com.banglaocr.imagej;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {


		int countofwhitepix = 0;
		ImagePlus imagePlus = IJ.openImage("tst\\ko_AtraiMJ.png");
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
			 System.out.println(countofwhitepix);
		}

	}
}
