package com.dorjear.sample.counter.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlerControllerAdvise {
	private static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandlerControllerAdvise.class);

	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "I/O problem encountered")
	@ExceptionHandler(IOException.class)
	public void ioProblem(IOException e) {
		LOG.warn("I/O problem encountered", e);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Undefined error")
	@ExceptionHandler(Exception.class)
	public void undefinedError(Exception e) {
		LOG.error("Undefined error", e);
	}

}
