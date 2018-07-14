package com.dorjear.sample.counter;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.dorjear.sample.counter.businesslogic.TextCounter;
import com.dorjear.sample.counter.businesslogic.TextCounterImpl;
import com.dorjear.sample.counter.datamodel.TextCount;
import com.dorjear.sample.counter.repository.TextDataRepository;

@RunWith(MockitoJUnitRunner.class)
public class TextCounterImplTest {

	@Spy
	private TextDataRepository textDataRepository = new TextDataRepository() {

		@Override
		public List<String> getTextData() {
			return Arrays.asList(new String[] { "Donec", "Duis", "Sed", "Duis", "Sed", "Duis" });
		}

	};

	@InjectMocks
	private TextCounter textCounter = new TextCounterImpl();

	private void assertTextCount(List<TextCount> textCounts, String expectedText, int expectedCount) throws Exception {
		Predicate<TextCount> filterPredicate = textCount -> expectedText.equals(textCount.getText());
		assertEquals(1, textCounts.stream().filter(filterPredicate).count());
		Optional<TextCount> textCount = textCounts.stream().filter(filterPredicate).findFirst();
		assertTrue(textCount.isPresent());
		assertEquals(expectedText, textCount.get().getText());
		assertEquals(expectedCount, textCount.get().getCount());
	}

	@Test
	public void testCountTextOccurrence() throws Exception {
		List<String> searchTexts = Arrays.asList(new String[] { "Duis", "Sed", "Donec" });
		List<TextCount> textCounts = textCounter.countTextOccurrence(searchTexts);

		assertNotNull(textCounts);
		assertEquals(3, textCounts.size());

		assertTextCount(textCounts, "Duis", 3);
		assertTextCount(textCounts, "Sed", 2);
		assertTextCount(textCounts, "Donec", 1);
	}

	private void assertTextCount(List<TextCount> textCounts, String expectedText, int expectedCount, int orderIndex)
			throws Exception {
		TextCount textCount = textCounts.get(orderIndex);
		assertEquals(expectedText, textCount.getText());
		assertEquals(expectedCount, textCount.getCount());
	}

	@Test
	public void testMaxTextOccurrence() throws Exception {
		List<TextCount> textCounts = textCounter.maxTextOccurrence(3);

		assertNotNull(textCounts);
		assertEquals(3, textCounts.size());

		assertTextCount(textCounts, "duis", 3, 0);
		assertTextCount(textCounts, "sed", 2, 1);
		assertTextCount(textCounts, "donec", 1, 2);
	}
}