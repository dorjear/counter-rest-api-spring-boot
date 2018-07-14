package com.dorjear.sample.counter.viewmodel;

import com.dorjear.sample.counter.datamodel.TextCount;
import com.dorjear.sample.counter.viewutil.TextCountViewSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = TextCountViewSerializer.class)
public class TextCountView extends TextCount {

	public TextCountView() {
		super();
	}

	public TextCountView(String text, int count) {
		super(text, count);
	}

}
