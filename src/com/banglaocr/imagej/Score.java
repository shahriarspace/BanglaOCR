package com.banglaocr.imagej;

import java.io.File;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

public class Score {

	public int getScore(String input, String train) {
		int score = 0;
		ImagePlus imagePlus = IJ.openImage(input);
		IJ.run(imagePlus, "Convert to Mask", "");
		ImageProcessor imageProcessor = imagePlus.getProcessor();
		// imagePlus.show();

		ImagePlus imagePlus2 = IJ.openImage("test" + File.separator + train);
		IJ.run(imagePlus2, "Convert to Mask", "");
		ImageProcessor imageProcessor2 = imagePlus2.getProcessor();
		// imagePlus2.show();

		for (int i = 0; i < imageProcessor.getHeight(); i++) {
			for (int j = 0; j < imageProcessor.getWidth(); j++) {
				if (imageProcessor.getPixel(j, i) == imageProcessor2.getPixel(
						j, i))
					score++;
			}

		}
		return score;
	}
}