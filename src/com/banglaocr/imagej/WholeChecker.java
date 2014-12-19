package com.banglaocr.imagej;

import ij.IJ;

import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.io.File;

public class WholeChecker {


	public boolean isHole(ImageProcessor ip) {

		ImagePlus imagePlus = new ImagePlus(null, ip.getBufferedImage());
		IJ.run(imagePlus, "Fill Holes", "");
		ImageProcessor imageProcessor = imagePlus.getProcessor();

		ImagePlus imagePlusWithHoles = new ImagePlus(null,
				ip.getBufferedImage());
		ImageProcessor imageProcessorHole = imagePlusWithHoles.getProcessor();

		for (int i = 0; i < imageProcessor.getHeight(); i++) {
			for (int j = 0; j < imageProcessor.getWidth(); j++) {
				if (imageProcessor.getPixel(i, j) != imageProcessorHole
						.getPixel(i, j)) {
					return true;
				}
			}
		}
		return false;
	}
}
