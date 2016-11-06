package fi.johannes.controllers;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.johannes.dto.MathOperationData;
import fi.johannes.services.ConversionService;
import fi.johannes.services.MathService;

@RestController
public class CalculatorController {
	
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
