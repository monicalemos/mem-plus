package com.mem.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mem.app.model.Tecnico;
import com.mem.app.model.Utilizador;
import com.mem.app.services.TecnicoService;
import com.mem.app.services.UtilizadorService;

@Controller
@RequestMapping(value = "/Utilizador")
@Scope("session")
public class UtilizadorController {

	private static final Logger logger = LoggerFactory.getLogger(UtilizadorController.class);

	HttpSession session = null;
	// private static final IRepository<Tecnico> repo =
	// DataRepositoryLocator.getTecnicoRepository();
	// private static final IRepository<Utilizador> repoUtil =
	// DataRepositoryLocator.getUtilizadorRepository();

	private UtilizadorService utilizadorService;
	private TecnicoService tecnicoService;

	@Autowired
	public void setUtilizadorService(UtilizadorService utilizadorService) {
		this.utilizadorService = utilizadorService;
	}

	@Autowired
	public void setTecnicoService(TecnicoService tecnicoService) {
		this.tecnicoService = tecnicoService;
	}

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
	public ModelAndView getLoginPage(@RequestParam(value = "error", required = false) boolean error,
			@ModelAttribute("utilizadorModel") Utilizador utilizadorModel, 
			@ModelAttribute("currentTecnico") Tecnico currentTecnico, ModelMap model, HttpServletRequest request) {
		System.out.println("login method post");
		ModelAndView modelView;
		
		session = request.getSession();
		
		if (error) {
			System.out.println("Houve Erros");
			model.put("login-error", "You have entered an invalid username or password!");
		} else {
			model.put("login-error", "");
			System.out.println("não houve erros");
			Utilizador utilizador = utilizadorService.get(utilizadorModel.getIdUtilizador());

			if (utilizadorModel.getIdUtilizador() != 0 && utilizador != null) {
				System.out
						.println("encontrou o utilizador? " + utilizadorService.get(utilizadorModel.getIdUtilizador()));
				
				Object obj = utilizadorService.encontrarUtilizador(utilizador);
				if (obj instanceof Tecnico) {
					modelView = new ModelAndView("home-private", "currentTecnico", (Tecnico)obj);
					session.setAttribute("currentTecnico", (Tecnico)obj);
					return modelView;
				}				
			} else {
				utilizador = utilizadorService.matchUser(utilizadorModel.getNomeUtilizador(),
						utilizadorModel.getPassword());

				if (utilizador != null) {

					utilizador = utilizadorService.matchUser(utilizadorModel.getNomeUtilizador(),
							utilizadorModel.getPassword());

					System.out.println("id do match: " + utilizador.getIdUtilizador());
					System.out.println("tipo de utilizador do match: " + utilizador.getTipoUtilizador());

					System.out.println(
							"encontrou o utilizador no match? " + utilizadorService.get(utilizador.getIdUtilizador()));
					
					System.out.println("o q esta no view? " + this.index().getModel().get("utilizadorModel"));
					
					Object obj = utilizadorService.encontrarUtilizador(utilizador);
					if (obj instanceof Tecnico) {
//						mv.addObject("currentTecnico", (Tecnico) obj);
						currentTecnico = (Tecnico)obj;
						modelView = new ModelAndView("home-private", "currentTecnico", (Tecnico)obj);
						System.out.println("o q esta no view? " + modelView.getViewName());
						session.setAttribute("currentTecnico", (Tecnico)obj);
						return modelView;
					}

				} else {
					model.put("login-error",
							"You have entered an invalid username or password or you're note registered");
				}
			}
		}
		return new ModelAndView("utilizador-login", "utilizadorModel", new Utilizador());
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		// TODO: Do Something to Remove Cookies, Authentication, etc...
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/registrarUtilizador", method = RequestMethod.GET)
	public ModelAndView registrar() {
		// TODO: Do Something to Remove Cookies, Authentication, etc...
		return new ModelAndView("utilizador-new", "tecnicoModel", new Tecnico());
	}

	@RequestMapping(value = "/registrarUtilizador", method = RequestMethod.POST)
	public String registrar(@Valid @ModelAttribute("tecnicoModel") Tecnico tecnicoModel, 
			BindingResult result, HttpServletRequest request) {

		if (!result.hasErrors()) {
			if (tecnicoModel.getIdTecnico() != 0 && tecnicoService.get(tecnicoModel.getIdTecnico()) != null) {
				System.out.println("Já existe um utilizador igual");
				result.rejectValue("idTecnico", "CustomMessage", "Já existe um utilizador igual");
			} else {
				System.out.println("vai inserir tecnico");
				tecnicoService.saveOrUpdate(tecnicoModel);
				System.out.println("ja inseriu");
				getLoginPage(false, tecnicoModel.getUtilizador(), null, new ModelMap(), request);
			}
			return null;
		} else {
			result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			logger.debug("Existem Erros:" + result.hasErrors());
			return "registrarUtilizador";
		}
	}
}
