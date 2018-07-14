package com.dorjear.sample.counter.viewmodel;

import java.util.List;

public class SearchResponseView {
	private List<TextCountView> counts;

	public SearchResponseView() {
	}

	public SearchResponseView(List<TextCountView> counts) {
		setCounts(counts);
	}

	public List<TextCountView> getCounts() {
		return counts;
	}

	public void setCounts(List<TextCountView> counts) {
		this.counts = counts;
	}

}
