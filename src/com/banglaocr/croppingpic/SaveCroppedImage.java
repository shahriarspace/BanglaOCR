package com.banglaocr.croppingpic;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.io.File;
import java.util.HashMap;

public class SaveCroppedImage {

	public static HashMap<String, ImageProcessor> crop(String inputDirname,
			String outputDirName) {

		BoundsFinder boundsFinder = new BoundsFinder();
		HashMap<String, ImageProcessor> croped = new HashMap<String, ImageProcessor>();
		ImagePlus imagePlus;
		ImageProcessor imageProcessor;
		File inputDir = new File(inputDirname);
		String[] inputfiles = inputDir.list();
		for (String filename : inputfiles) {
			String filePath = inputDirname + File.separator + filename;
			try {
				imagePlus = IJ.openImage(filePath);
				IJ.run(imagePlus, "Convert to Mask", "");
				imageProcessor = imagePlus.getProcessor();
				imagePlus.setProcessor(CroppingImage.crop(imageProcessor,
						boundsFinder.leftbound(imageProcessor),
						boundsFinder.upperbound(imageProcessor),
						boundsFinder.rightbound(imageProcessor),
						boundsFinder.lowerbound(imageProcessor)));
				IJ.run(imagePlus, "Convert to Mask", "");
				croped.put(filename, imagePlus.getProcessor());
				IJ.saveAs(imagePlus, "png", outputDirName + File.separator
						+ filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
		return croped;

	}

}
