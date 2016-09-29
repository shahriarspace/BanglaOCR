package com.banglaocr.imagej;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class BinaryComparator {
	public String binocr(String inputfile){
		String matchedfilename = null;
		Score score = new Score();
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

		File file = new File("test");
		String[] files = file.list();
		for (String filename : files) {
			hashMap.put(filename, score.getScore(inputfile, filename));
		}
		// System.out.println(hashMap);

		int maxValue = (Collections.max(hashMap.values()));
//		System.out.println(maxValue);

		for (Entry<String, Integer> entry : hashMap.entrySet()) {
			if (entry.getValue() == maxValue) {
				matchedfilename = entry.getKey();
//				System.out.println(entry.getKey());
			}
		}
		return matchedfilename;
	}
}
