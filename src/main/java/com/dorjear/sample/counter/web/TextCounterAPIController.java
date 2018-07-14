package com.dorjear.sample.counter.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dorjear.sample.counter.businesslogic.TextCounter;
import com.dorjear.sample.counter.viewmodel.CSVDataView;
import com.dorjear.sample.counter.viewmodel.SearchRequestView;
import com.dorjear.sample.counter.viewmodel.SearchResponseView;
import com.dorjear.sample.counter.viewmodel.TextCountView;

@RestController
@RequestMapping("/counter-api")
public class TextCounterAPIController {

	@Autowired
	protected TextCounter textCounter;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<SearchResponseView> search(@RequestBody SearchRequestView request) {
		List<TextCountView> textCounts = textCounter.countTextOccurrence(request.getSearchText()).stream()
				.map(it -> new TextCountView(it.getText(), it.getCount())).collect(Collectors.toList());
		return new ResponseEntity<SearchResponseView>(new SearchResponseView(textCounts), HttpStatus.OK);
	}

	@RequestMapping(value = "/top/{limit}", produces = "text/csv")
	public ResponseEntity<CSVDataView> top(@PathVariable("limit") int limit) {
		List<String[]> textCounts = textCounter.maxTextOccurrence(limit).stream()
				.map(it -> new String[] { it.getText(), String.valueOf(it.getCount()) }).collect(Collectors.toList());
		return new ResponseEntity<CSVDataView>(new CSVDataView(textCounts), HttpStatus.OK);
	}

}
