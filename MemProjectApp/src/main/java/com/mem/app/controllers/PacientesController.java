package com.mem.app.controllers;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mem.app.model.Paciente;
import com.mem.app.model.Tecnico;
import com.mem.app.services.PacienteService;

@Controller
@RequestMapping(value = "/Paciente")
public class PacientesController {

	private static final Logger logger = LoggerFactory.getLogger(UtilizadorController.class);
	PacienteService pacienteService;

	HttpSession session = null;

	@Autowired
	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	public PacientesController() {
	}

	public void removeFamilySessionAttributes() {
		Enumeration<?> lista = session.getAttributeNames();
		while (lista.hasMoreElements()) {
			String value = (String) lista.nextElement();
			System.out.println("Atttribute name " + value);
			if (value.contains("Familiar") || value.contains("Relacao")) {
				session.removeAttribute(value);
			}
		}
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView index() {
		removeFamilySessionAttributes();
		return new ModelAndView("home-private");
	}

	@RequestMapping(value = "/verPaciente", method = RequestMethod.GET)
	public ModelAndView verPaciente(HttpServletRequest request) {
		System.out.println("ver Paciente");
		session = request.getSession();
		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
//		removeFamilySessionAttributes();
		return new ModelAndView("ver-paciente", "currentPaciente", paciente);
	}

	@RequestMapping(value = "/verPaciente", method = RequestMethod.POST, params = { "idPaciente" })
	public ModelAndView getVerPaciente(@RequestParam(value = "error", required = false) boolean error, ModelMap model,
			HttpServletRequest request) {
		session = request.getSession();
		Tecnico tecnico = (Tecnico) session.getAttribute("currentTecnico");

		int idPaciente = 0;
		if (request.getParameter("idPaciente") != null) {
			System.out.println("ID Paciente: " + request.getParameter("idPaciente"));
			idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
			System.out.println("Vem do request");
		} else {
			if (session.getAttribute("curren") != null) {
				System.out.println("Vem do session");
				idPaciente = (Integer) session.getAttribute("idPaciente");
			}
		}

		if (error) {
			System.out.println("Houve Erros");
			model.put("viewProfile-error", "Não conseguiu mostrar os dados do paciente");
			return new ModelAndView("home-private", "currentTecnico", tecnico);
		} else {
			model.put("viewProfile-error", "");
			System.out.println("não houve erros");
			if (pacienteService.get(idPaciente) != null) {
				Paciente paciente = pacienteService.get(idPaciente);
				request.setAttribute("currentPaciente", paciente);

				// session.setAttribute("currentPaciente", paciente);
				removeFamilySessionAttributes();
				session.setAttribute("currentPaciente", paciente);
				request.setAttribute("currentPaciente", paciente);
				System.out.println("Pos o paciente no session");
				return new ModelAndView("ver-paciente", "currentPaciente", paciente);
			} else {
				model.put("login-error", "Não conseguiu mostrar os dados do paciente");
				removeFamilySessionAttributes();
				return new ModelAndView("home-private", "currentTecnico", tecnico);
			}
		}

	}

	@RequestMapping(value = "/editarPaciente", method = RequestMethod.GET)
	public ModelAndView editarPaciente(HttpServletRequest request) {
		System.out.println("editar Paciente");
		session = request.getSession();
		Paciente paciente = null;

		if (null != request.getAttribute("currentPaciente")) {
			paciente = (Paciente) request.getAttribute("currentPaciente");
			System.out.println("Vem do request");
		} else if (null != session.getAttribute("currentPaciente")) {
			paciente = (Paciente) session.getAttribute("currentPaciente");
			System.out.println("Vem do session");
		}

		System.out.println("Tem paciente: " + paciente);

		// removeFamilySessionAttributes();
		return new ModelAndView("editarPaciente", "currentPaciente", paciente);
	}

	@RequestMapping(value = "/editarPaciente", method = RequestMethod.POST)
	public ModelAndView editarPaciente(@ModelAttribute("currentPaciente") @Valid Paciente paciente,
			BindingResult result, HttpServletRequest request) {
		System.out.println("vou ver editar o paciente " + paciente.getIdPaciente() + " - " + paciente.getApelido()
				+ " - " + paciente.getNomeProprio());

		if (!result.hasErrors()) {
			System.out.println("Não tem erros");
			if (paciente.getIdPaciente() != 0 && pacienteService.get(paciente.getIdPaciente()) == null) {
				System.out.println("Esse paciente não existe");
				result.rejectValue("idPaciente", "CustomMessage", "Esse paciente não existe");
			} else {
				System.out.println("vai editar paciente");
				int idPaciente = pacienteService.saveOrUpdate(paciente);
				session.setAttribute("idPaciente", idPaciente);
				verPaciente(request);
				paciente.setNomeCompleto(paciente.getApelido() + ", " + paciente.getNomeProprio());
			}
			removeFamilySessionAttributes();
			return new ModelAndView("ver-paciente", "currentPaciente", paciente);
		} else {
			System.out.println(result.getAllErrors().toString());
			System.out.println("ocorreu um erro");
			logger.debug("Existem Erros:" + result.hasErrors());
			removeFamilySessionAttributes();
			return new ModelAndView("editarPaciente", "currentPaciente", paciente);
		}
	}

	@RequestMapping(value = "/inserirPaciente", method = RequestMethod.GET)
	public ModelAndView inserirPaciente() {
		System.out.println("inserirPaciente");
		removeFamilySessionAttributes();
		return new ModelAndView("inserirPaciente", "pacienteModel", new Paciente());
	}

	@RequestMapping(value = "/inserirPaciente", method = RequestMethod.POST)
	public ModelAndView inserirPaciente(@Valid @ModelAttribute("pacienteModel") Paciente paciente, BindingResult result,
			HttpServletRequest request) {

		session = request.getSession();
		Tecnico tecnico = (Tecnico) session.getAttribute("currentTecnico");
		paciente.setTecnico(tecnico);

		if (!result.hasErrors()) {
			System.out.println("Não tem erros");
			if (paciente.getIdPaciente() != 0 && pacienteService.get(paciente.getIdPaciente()) != null) {
				System.out.println("Já existe um paciente igual");
				result.rejectValue("idPaciente", "CustomMessage", "Já existe um paciente igual");
			} else {
				System.out.println("vai inserir paciente");
				int newId = pacienteService.saveOrUpdate(paciente);
				session.setAttribute("idPaciente", newId);
				verPaciente(request);
			}
			removeFamilySessionAttributes();
			return new ModelAndView("ver-paciente", "currentPaciente", paciente);
		} else {
			System.out.println(result.getAllErrors().toString());
			System.out.println("ocorreu um erro");
			logger.debug("Existem Erros:" + result.hasErrors());
			removeFamilySessionAttributes();
			return new ModelAndView("inserirPaciente", "pacienteModel", new Paciente());
		}
	}

	@RequestMapping(value = "/listarPacientes", method = RequestMethod.GET)
	public ModelAndView listarPaciente(HttpServletRequest request) {
		System.out.println("listar Paciente");

		session = request.getSession();
		Tecnico tecnico = (Tecnico) session.getAttribute("currentTecnico");

		List<Paciente> listPaciente = pacienteService.list(tecnico.getIdTecnico());
		tecnico.setPacientes(listPaciente);
		removeFamilySessionAttributes();
		return new ModelAndView("listarPacientes", "currentTecnico", tecnico);
	}
}
