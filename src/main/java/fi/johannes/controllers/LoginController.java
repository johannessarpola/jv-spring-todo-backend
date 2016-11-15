package fi.johannes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
// @RequestMapping(path="/login")
public class LoginController {
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	String index(){
		return "index";
	}
	@RequestMapping(path="/login", method=RequestMethod.GET)
	String loginPage(){
		return "login";
	}
	@RequestMapping(path="/success", method=RequestMethod.GET)
	String success(){
		return "success";
	}
	@RequestMapping(path="/logout", method=RequestMethod.GET)
	String logout(){
		return "logout";
	}
}
