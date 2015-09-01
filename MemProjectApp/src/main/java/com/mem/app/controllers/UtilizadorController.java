package com.mem.app.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mem.app.model.Tecnico;
import com.mem.app.model.Utilizador;
import com.mem.app.model.repository.DataRepositoryLocator;
import com.mem.app.model.repository.IRepository;

@Controller
@RequestMapping(value = "/Utilizador")
public class UtilizadorController {

	private static final Logger logger = LoggerFactory.getLogger(UtilizadorController.class);
	
	private static final IRepository<Tecnico> repo = DataRepositoryLocator.getTecnicoRepository();
	private static final IRepository<Utilizador> repoUtil = DataRepositoryLocator.getUtilizadorRepository();

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		System.out.println("ola utilizador");
		return new ModelAndView("utilizador-login", "utilizadorModel", new Utilizador());
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("login method get");
		return new ModelAndView("utilizador-login", "utilizadorModel", new Utilizador());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getLoginPage(@RequestParam(value = "error", required = false) boolean error, 
			@ModelAttribute("utilizadorModel") Utilizador utilizadorModel,  
			ModelMap model) {
		System.out.println("login method post");

		if (error) {
			System.out.println("Houve Erros");
			model.put("login-error", "You have entered an invalid username or password!");
		} else {
			model.put("login-error", "");
			System.out.println("não houve erros");
			if (repoUtil.selectObject(utilizadorModel) != null){
				return "home-private";
			}
				else{
					model.put("login-error", "You have entered an invalid username or password or you're note registered");
				}
		}
		return "utilizador-login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		// TODO: Do Something to Remove Cookies, Authentication, etc...
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/registrarUtilizador", method = RequestMethod.GET)
	public ModelAndView registrar() {
		// TODO: Do Something to Remove Cookies, Authentication, etc...		
		return  new ModelAndView("utilizador-new", "tecnicoModel", new Tecnico());
	}


	@RequestMapping(value = "/registrarUtilizador", method = RequestMethod.POST)
	public String registrar(@Valid  @ModelAttribute("tecnicoModel") Tecnico tecnicoModel, BindingResult result) {

		if (!result.hasErrors()) {
			tecnicoModel.setIdTecnico(-1);
			if (repo.create(tecnicoModel))
				getLoginPage(false, tecnicoModel.getUtilizador(), new ModelMap()); 
			else {
				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			}
		}
		logger.debug("Existem Erros:" + result.hasErrors());
		return "registrarUtilizador";
	}

}
