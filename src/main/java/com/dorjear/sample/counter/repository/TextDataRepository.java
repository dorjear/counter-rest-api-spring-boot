package com.dorjear.sample.counter.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface TextDataRepository {
	List<String> getTextData();
}
