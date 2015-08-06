package com.mem.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mem.app.model.Utilizador;


@Controller
@RequestMapping(value = "/Account")
public class UtilizadorController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("utilizador-login","UtilizadorModel", new Utilizador());
	}

	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("utilizador-login","UtilizadorModel", new Utilizador());
	}	
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
		System.out.println("Received request to show login page");

		if (error) {
			System.out.println("Houve Erros");
			model.put("login-error", "You have entered an invalid username or password!");
		} else {
			model.put("login-error", "");
			return "home";
		}
		return "utilizador-login";
	}

	
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public ModelAndView logout(){
		//TODO: Do Something to Remove Cookies, Authentication, etc...
		return new ModelAndView("utilizador-logout");
	}
}
