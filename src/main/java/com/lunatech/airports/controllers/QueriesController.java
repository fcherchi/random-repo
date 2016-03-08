package com.lunatech.airports.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lunatech.airports.services.Page;
import com.lunatech.airports.services.QueryService;



/**
 * Controller for the queries 
 * @author Fernando
 */

@Controller
public class QueriesController {
	
	
	/** Context goes in front of all methods url */
	private static final String CONTEXT = "query";
	
	
	/** Access to the service layer */
	@Autowired
	private QueryService queryService;

	/** Find airport by code or by airport name */
	@RequestMapping(value = CONTEXT + "/findByCode/{offset}", method = RequestMethod.POST)
    public ModelAndView findByCode(@RequestParam String code, 
    		@PathVariable(value="offset")int offset) {
		
		//getting the info
		List<Map<String, Object>> airports = queryService.getAirports(code, offset, 10);
		
		//computing the next and previous links
		Page next = queryService.getNextPage(code, offset, 10);
		Page previous = queryService.getPreviousPage(code, offset, 10);
		
		//preparing the links
		String prevLink = String.format("%d", previous.getOffset());
		String nextLink = String.format("%d", next.getOffset());
		
		
		ModelAndView modelAndView = new ModelAndView("airports");
		modelAndView.addObject("airports", airports);
		modelAndView.addObject("prev", prevLink);
		modelAndView.addObject("next", nextLink);
		modelAndView.addObject("code", code);
		
		return modelAndView;
    }
}
