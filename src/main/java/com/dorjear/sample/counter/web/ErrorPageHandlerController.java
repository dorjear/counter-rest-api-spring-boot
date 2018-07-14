package com.dorjear.sample.counter.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dorjear.sample.counter.viewmodel.ErrorDataView;

@RestController
public class ErrorPageHandlerController implements ErrorController {

	private static final String PATH = "/error";

	@Value("${techEval.web.debug:false}")
	private boolean debug;

	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping(value = PATH)
	ErrorDataView error(HttpServletRequest request, HttpServletResponse response) {
		return new ErrorDataView(response.getStatus(), getErrorAttributes(request, debug));
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
	}

}
