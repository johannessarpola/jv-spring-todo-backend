package fi.johannes.controllers;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.johannes.dto.MathOperationData;
import fi.johannes.services.ConversionService;
import fi.johannes.services.MathService;

// XXX Note to self, RestController does not work with ThymeLeaf and will result only string to be returned
// as is, not as a template
@Controller
public class CalculatorController {
	
	// TODO Manage this as a backend (RestController) which will be used from another service as a 
	// engine to do calculation as purpose is not to return templates if there is calculation engine.
	
	@Autowired
	MathService mathService;
	
	@Autowired 
	ConversionService conversionService;
	
	@RequestMapping(path="/math/add", method=RequestMethod.POST)
	String sum(@RequestBody MathOperationData body, Model model) {
		BigInteger[] bigints = conversionService.StringsToBigInts(body.getNumbers());
		BigInteger result = mathService.sum(bigints);
		model.addAttribute("CalculationResult", result.toString());
		return "results";
	}
}
