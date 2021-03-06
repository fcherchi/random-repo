package com.lunatech.airports.controllers.exceptions;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;

@ControllerAdvice(basePackages = { "com.lunatech.airports.controllers" })
public class ExceptionHandlerController {

	/** The logger */
	final Logger logger = (Logger) LoggerFactory.getLogger(ExceptionHandlerController.class);

	/**
	 * Handles the exception and redirects to error page
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler
	public ModelAndView exception(Exception exception) {
		logger.error(exception.getMessage(), exception);
	
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", exception.getMessage());
		return modelAndView;
	}

}
