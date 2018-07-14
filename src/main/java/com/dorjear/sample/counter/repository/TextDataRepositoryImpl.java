package com.dorjear.sample.counter.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class TextDataRepositoryImpl implements TextDataRepository {

	@Value("#{'file:'}${techEval.repository.textData.fileLocation}")
	private Resource textData;

	public List<String> getTextData() {
		List<String> textData = Collections.emptyList();
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(this.textData.getInputStream()))) {
			String rawTextData = buffer.lines().map(line -> line.replaceAll("\\.|,", "")).collect(Collectors.joining(" "));
			textData = Arrays.asList(rawTextData.replaceAll("\\.|,", " ").split("\\s+"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return textData;
	}
}
