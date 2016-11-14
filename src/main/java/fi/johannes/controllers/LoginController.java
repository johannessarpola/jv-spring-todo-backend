package fi.johannes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(path="/login")
public class LoginController {

	@RequestMapping(path="/", method=RequestMethod.GET)
	String loginPage(){
		return "login";
	}
}
