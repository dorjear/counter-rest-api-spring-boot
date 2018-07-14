package com.dorjear.sample.counter.businesslogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dorjear.sample.counter.datamodel.TextCount;
import com.dorjear.sample.counter.repository.TextDataRepository;

@Service
public class TextCounterImpl implements TextCounter {

	@Value("${techEval.isCaseSensitive:false}")
	private boolean isCaseSensitive;

	@Autowired
	protected TextDataRepository textDataRepository;

	private Map<String, Integer> countAllTextOccurrence() {
		Map<String, Integer> occurrenceCounterMap = new HashMap<>();
		List<String> textData = textDataRepository.getTextData();
		for (String textDataItem : textData) {
			if (!isCaseSensitive) {
				textDataItem = textDataItem.toLowerCase();
			}
			Integer count = occurrenceCounterMap.get(textDataItem);
			if (count == null) {
				count = 1;
			} else {
				count++;
			}
			occurrenceCounterMap.put(textDataItem, count);
		}
		return occurrenceCounterMap;
	}

	@Override
	public List<TextCount> countTextOccurrence(List<String> searchTexts) {
		List<TextCount> result = new ArrayList<>();
		Map<String, Integer> occurrenceCounterMap = countAllTextOccurrence();
		for (String searchText : searchTexts) {
			Integer count = occurrenceCounterMap.get(isCaseSensitive ? searchText : searchText.toLowerCase());
			if (count != null) {
				result.add(new TextCount(searchText, count));
			}
		}
		return result;
	}

	@Override
	public List<TextCount> maxTextOccurrence(int numberOfTextLimit) {
		List<TextCount> result = Collections.emptyList();
		Map<String, Integer> occurrenceCounterMap = countAllTextOccurrence();
		List<TextCount> occurrenceSorterList = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : occurrenceCounterMap.entrySet()) {
			occurrenceSorterList.add(new TextCount(entry.getKey(), entry.getValue()));
		}
		Collections.sort(occurrenceSorterList, (o1, o2) -> Integer.compare(o2.getCount(), o1.getCount()));
		if (numberOfTextLimit > occurrenceSorterList.size()) {
			numberOfTextLimit = occurrenceSorterList.size();
		}
		result = occurrenceSorterList.subList(0, numberOfTextLimit);
		return result;
	}

}
