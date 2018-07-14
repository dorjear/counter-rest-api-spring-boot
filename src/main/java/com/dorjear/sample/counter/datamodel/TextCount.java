package com.dorjear.sample.counter.datamodel;

public class TextCount {
	private String text;
	private int count;

	public TextCount() {
	}

	public TextCount(String text, int count) {
		setText(text);
		setCount(count);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
