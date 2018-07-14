package com.dorjear.sample.counter.viewmodel;

import java.util.List;

public class CSVDataView {

	private List<String[]> data;

	public CSVDataView(List<String[]> data) {
		this.data = data;
	}

	public List<String[]> getData() {
		return data;
	}

}
