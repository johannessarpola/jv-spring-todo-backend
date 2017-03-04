package fi.johannes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class LoginController {

	@RequestMapping(path="/logout", method=RequestMethod.GET)
	String logout(){
		return "logout";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(@RequestParam(required = false) String error) {
		return new ModelAndView("login", "error", Optional.ofNullable(error));
	}
}
