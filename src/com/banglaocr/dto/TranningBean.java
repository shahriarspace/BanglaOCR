package com.banglaocr.dto;

import java.util.ArrayList;

public class TranningBean {
	public ArrayList<Bean> withHole;

	public ArrayList<Bean> withOutHole;

	public TranningBean() {
		withHole = new ArrayList<Bean>();
		withOutHole = new ArrayList<Bean>();
	}

	public ArrayList<Bean> getWithHole() {
		return withHole;
	}

	public void setWithHole(ArrayList<Bean> withHole) {
		this.withHole = withHole;
	}

	public ArrayList<Bean> getWithOutHole() {
		return withOutHole;
	}

	public void setWithOutHole(ArrayList<Bean> withOutHole) {
		this.withOutHole = withOutHole;
	}

}
