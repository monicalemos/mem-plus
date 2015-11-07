package com.mem.app.controllers;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mem.app.model.Tecnico;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

	HttpSession session = null;

	@Autowired

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		System.out.println("ola home");

		return new ModelAndView("home");
	}

	@RequestMapping(value = "/Sobre", method = RequestMethod.GET)
	public ModelAndView about() {
		// TODO Handle the View and Model
		System.out.println("ola sobre");
		return new ModelAndView("sobre");
	}

	@RequestMapping(value = "/Contactos", method = RequestMethod.GET)
	public ModelAndView contacts() {
		// TODO Handle the View and Model
		System.out.println("ola contactos");
		return new ModelAndView("contactos");
	}

	@RequestMapping(value = "/home-private", method = RequestMethod.GET)
	public ModelAndView privateHome(HttpServletRequest request) {
		System.out.println("ola home");

		session = request.getSession();

		Enumeration<?> lista = session.getAttributeNames();
		while (lista.hasMoreElements()) {
			session.removeAttribute((String) lista.nextElement());
		}

		Tecnico tecnico = (Tecnico) session.getAttribute("currentTecnico");
		System.out.println("tecnico no home: " + tecnico);
		System.out.println("tem nome no home: " + tecnico.getNomeProprio());

		return new ModelAndView("home-private", "currentTecnico", tecnico);
	}
}
