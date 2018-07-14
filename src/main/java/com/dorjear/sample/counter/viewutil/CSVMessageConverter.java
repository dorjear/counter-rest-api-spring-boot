package com.dorjear.sample.counter.viewutil;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.dorjear.sample.counter.viewmodel.CSVDataView;

public class CSVMessageConverter extends AbstractHttpMessageConverter<CSVDataView> {
	public CSVMessageConverter() {
		super(new MediaType("text", "csv"));
	}

	@Override
	protected CSVDataView readInternal(Class<? extends CSVDataView> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return CSVDataView.class.isAssignableFrom(clazz);
	}

	@Override
	protected void writeInternal(CSVDataView t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withDelimiter('|').withRecordSeparator("\n");
		try (CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(outputMessage.getBody()), csvFileFormat)) {
			for (String[] record : t.getData()) {
				csvPrinter.printRecord(Arrays.asList(record));
			}
		}
	}
}
