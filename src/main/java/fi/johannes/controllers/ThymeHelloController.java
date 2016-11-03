package fi.johannes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.johannes.services.ListService;

@Controller
public class ThymeHelloController {

	@Autowired
	ListService listing;
	
	@RequestMapping("/thyme")
	public String index(Model model){
		model.addAttribute("name", "Bob Benson");
		return "thymei";
	}
	
	@RequestMapping("/listofstuff")
	public String listofstuff (Model model){
		List<String> s = listing.giveTen();
		model.addAttribute("stuff", s);
		return "listing";
	}
	
}
