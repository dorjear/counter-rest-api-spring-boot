package com.dorjear.sample.counter.businesslogic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dorjear.sample.counter.datamodel.TextCount;

@Service
public interface TextCounter {

	List<TextCount> countTextOccurrence(List<String> searchTexts);

	List<TextCount> maxTextOccurrence(int numberOfTextLimit);

}
