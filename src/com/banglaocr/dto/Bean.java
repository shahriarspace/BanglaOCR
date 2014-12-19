package com.banglaocr.dto;

import java.util.ArrayList;

public class Bean {

	private String Name;
	private ArrayList<Integer> left;
	private ArrayList<Integer> rihgt;
	private ArrayList<Integer> bottom;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public ArrayList<Integer> getLeft() {
		return left;
	}

	public void setLeft(ArrayList<Integer> left) {
		this.left = left;
	}

	public ArrayList<Integer> getRihgt() {
		return rihgt;
	}

	public void setRihgt(ArrayList<Integer> rihgt) {
		this.rihgt = rihgt;
	}

	public ArrayList<Integer> getBottom() {
		return bottom;
	}

	public void setBottom(ArrayList<Integer> bottom) {
		this.bottom = bottom;
	}

}
