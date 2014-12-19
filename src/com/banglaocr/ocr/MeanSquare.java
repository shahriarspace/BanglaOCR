package com.banglaocr.ocr;

import java.util.ArrayList;

public class MeanSquare {
	public double getmeansquare(ArrayList<Integer> arrayList,
			ArrayList<Integer> arrayList2) {
		int sum = 0;
		for (int i = 0; i < arrayList.size(); i++) {
			int distance = 0;
			distance = arrayList.get(i) - arrayList2.get(i);
			sum = sum + distance * distance;
		}

		return Math.sqrt(sum);
	}
}
