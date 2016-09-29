package com.banglaocr.imagej;

import ij.IJ;

import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.io.File;

public class WholeChecker {

	public static void main(String[] args) {
		WholeChecker checker = new WholeChecker();
		ImagePlus imagePlus = new ImagePlus("croppedTrainChars"
				+ File.separator + "ঠ_Vrinda.png");
		ImageProcessor imageProcessor = imagePlus.getProcessor();
		System.out.println(checker.isHole(imageProcessor));
	}

	public boolean isHole(String name) {

		ImagePlus imagePlus = new ImagePlus(name);
		IJ.run(imagePlus, "Fill Holes", "");
		ImageProcessor imageProcessor = imagePlus.getProcessor();

		ImagePlus imagePlusWithHoles = new ImagePlus(name);
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
