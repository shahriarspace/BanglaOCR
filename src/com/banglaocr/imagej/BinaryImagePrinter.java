package com.banglaocr.imagej;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

public class BinaryImagePrinter {

	public BinaryImagePrinter(String inputfile) {
		ImagePlus imagePlus = IJ.openImage(inputfile);
		IJ.run(imagePlus, "Convert to Mask", "");
		ImageProcessor imageProcessor = imagePlus.getProcessor();

		imageProcessor.rotate(90);
		for (int i = 0; i < imageProcessor.getHeight(); i++) {
			for (int j = 0; j < imageProcessor.getWidth(); j++) {
				int pix = imageProcessor.getPixel(j, i);
				if (pix > 0)
					pix = 1;

				// if (pix > 0)
				// break;
				System.out.print(pix);

			}
			System.out.println();
		}
	}

}
