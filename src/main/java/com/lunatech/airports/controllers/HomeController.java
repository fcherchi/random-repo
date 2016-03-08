package com.lunatech.airports.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lunatech.airports.services.ReportService;

@Controller
public class HomeController {
	
	@Autowired
	private ReportService reportService;
	
	/**
	 * Goes to the home template
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }
	
	/**
	 * Goes to the query template
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query() {
        return "query";
    }
	
	/**
	 * Loads the data (model) and goes to report template
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/report", method = RequestMethod.GET)
    public String report(Model model) {
		
		List<Map<String, Object>> tenWithMore = this.reportService.getTenCountriesWithMoreAirports();
		List<Map<String, Object>> tenWithLess = this.reportService.getTenCountriesWithLessAirports();
		List<Map<String, Object>> surfaces = this.reportService.getSurfaces();
		
		model.addAttribute("tenWithMore", tenWithMore);
		model.addAttribute("tenWithLess", tenWithLess);
		model.addAttribute("surfaces", surfaces);
		
		return "report";
    }
	
}
