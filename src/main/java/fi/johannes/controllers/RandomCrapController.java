package fi.johannes.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.johannes.crapgen.StringGen;

@RestController
@RequestMapping("gen")
public class RandomCrapController {

	private static final Logger log = LoggerFactory.getLogger(RandomCrapController.class);

	@RequestMapping("/rand/string100")
	public String randomString(){
		// TODO Should be inside service
		int i =0;
		log.info("Requested random string");
		return StringGen.randomAlphaNumericString(100);
	}
}
