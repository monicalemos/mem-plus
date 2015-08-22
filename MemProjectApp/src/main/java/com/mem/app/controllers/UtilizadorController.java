package com.mem.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mem.app.model.Utilizador;


@Controller
@RequestMapping(value = "/Utilizador")
public class UtilizadorController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		System.out.println("ola utilizador");
		return new ModelAndView("utilizador-login","Utilizador", new Utilizador());
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("login method get");
		return new ModelAndView("utilizador-login","Utilizador", new Utilizador());
	}	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error, ModelMap model) {
		System.out.println("Received request to show login page");
		System.out.println("login method post");
		
		if (error) {
			System.out.println("Houve Erros");
			model.put("login-error", "You have entered an invalid username or password!");
		} else {
			model.put("login-error", "");
			System.out.println("não houve erros");			
			return "home-private";
		}
		return "utilizador-login";
	}

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(){
		//TODO: Do Something to Remove Cookies, Authentication, etc...
		return new ModelAndView("home");
	}
}
