package com.mem.app.controllers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mem.app.model.Evento;
import com.mem.app.model.EventoId;
import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.RelacaoPacienteFamiliar;
import com.mem.app.model.Tecnico;
import com.mem.app.services.EventoService;
import com.mem.app.services.FamiliarService;
import com.mem.app.services.RelacaoPacienteFamiliarService;

@Controller
@RequestMapping(value = "/Evento")
public class EventosController {

	private static final Logger logger = LoggerFactory.getLogger(EventosController.class);

	EventoService eventoService;
	RelacaoPacienteFamiliarService relacaoPacienteFamiliarService;
	FamiliarService familiarService;

	public EventosController() {
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

	@Autowired
	public void setEventoService(EventoService eventoService) {
		this.eventoService = eventoService;
	}

	@Autowired
	public void setRelacaoPacienteFamiliarService(RelacaoPacienteFamiliarService relacaoPacienteFamiliarService) {
		this.relacaoPacienteFamiliarService = relacaoPacienteFamiliarService;
	}

	@Autowired
	public void setFamiliarService(FamiliarService familiarService) {
		this.familiarService = familiarService;
	}

	HttpSession session = null;

	@RequestMapping(value = "/verEvento", method = RequestMethod.GET)
	public ModelAndView verEvento(HttpServletRequest request) {
		System.out.println("ver Evento");
		removeFamilySessionAttributes();

		session = request.getSession();
		Tecnico tecnico = (Tecnico) session.getAttribute("currentTecnico");
		System.out.println("tecnico: " + tecnico);

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		System.out.println("paciente session " + paciente);

		Evento evento = (Evento) session.getAttribute("currentEvento");
		System.out.println("evento session " + evento);

		int idEvento = 0;
		if (request.getParameter("idEvento") != null) {
			idEvento = Integer.parseInt(request.getParameter("idEvento"));
			System.out.println("vem do request");
		} else {
			if (session.getAttribute("idEvento") != null) {
				idEvento = (Integer) session.getAttribute("idEvento");
				System.out.println("vem da sessao");
			}
		}
		System.out.println("tem id?  " + idEvento);
		if (idEvento > 0 && evento == null) {
			evento = eventoService.get(idEvento);
		}

		session.setAttribute("currentEvento", evento);
		System.out.println("currentEvento sesssion " + session.getAttribute("currentEvento"));

		session.setAttribute("familiarEvento",
				evento.getFamiliar().getNomeProprio() + " " + evento.getFamiliar().getApelido());
		return new ModelAndView("verEvento", "currentEvento", evento);
	}

	@RequestMapping(value = "/verEvento", method = RequestMethod.POST)
	public ModelAndView verEvento(@RequestParam(value = "error", required = false) boolean error, ModelMap model,
			HttpServletRequest request) {

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		Evento evento = (Evento) session.getAttribute("currentEvento");

		if (error) {
			System.out.println("Houve Erros");
			model.put("viewEvento-error", "Não conseguiu mostrar os dados do evento");
			return new ModelAndView("ver-paciente", "currentPaciente", paciente);
		} else {
			model.put("viewEvento-error", "");
			System.out.println("não houve erros");

			int idEvento = 0;
			if (request.getParameter("idEvento") != null) {
				idEvento = Integer.parseInt(request.getParameter("idEvento"));
			} else {
				if (session.getAttribute("idEvento") != null) {
					idEvento = (Integer) session.getAttribute("idEvento");
				}
			}
			if (idEvento > 0) {
				evento = eventoService.get(idEvento);
			}
			if (evento != null)
				return new ModelAndView("verEvento", "currentEvento", evento);
			else {
				model.put("verEvento-error", "Não conseguiu mostrar os dados do evento");
				return new ModelAndView("ver-paciente", "currentPaciente", paciente);
			}
		}
	}

	@RequestMapping(value = "/editarEvento", method = RequestMethod.GET)
	public ModelAndView editarEvento(HttpServletRequest request) {
		System.out.println("editar Evento");

		session = request.getSession();

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");

		int idEvento = 0;
		if (request.getParameter("idEvento") != null) {
			idEvento = Integer.parseInt(request.getParameter("idEvento"));
		} else {
			if (session.getAttribute("idEvento") != null)
				idEvento = (Integer) session.getAttribute("idEvento");
		}
		Evento evento = eventoService.get(idEvento);

		List<RelacaoPacienteFamiliar> listRelacaoPacienteFamiliar = relacaoPacienteFamiliarService.list(paciente);
		paciente.setRelacaoPacienteFamiliars(listRelacaoPacienteFamiliar);

		HashMap<String, Familiar> listFamiliar = new HashMap<String, Familiar>();

		for (RelacaoPacienteFamiliar relacao : listRelacaoPacienteFamiliar) {
			listFamiliar.put(relacao.getTipoRelacao(), relacao.getFamiliar());
		}

		session.setAttribute("listFamiliares", listFamiliar);

		return new ModelAndView("editarEvento", "eventoModel", evento);
	}

	@RequestMapping(value = "/editarEvento", method = RequestMethod.POST)
	public ModelAndView editarEvento(@ModelAttribute("currentEvento") @Valid Evento evento, BindingResult result,
			HttpServletRequest request) {
		System.out.println("Vou editar o evento " + evento);

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");

		int idEventoId = evento.getId().getIdEvento();

		if (!result.hasErrors()) {
			System.out.println("Não teve erros");
			if (idEventoId == 0 && eventoService.get(idEventoId) == null) {
				System.out.println("Esse evento não existe");
				result.rejectValue("idEvento", "CustomMessage", "Esse evento não existe");
			} else {
				System.out.println("vai editar a relacao");
				int updateId = 0;
				if (evento.getFamiliar() != null) {
					updateId = eventoService.saveOrUpdateWithFamily(evento);
				} else {
					updateId = eventoService.saveOrUpdateWithoutFamily(evento);
				}
				session.setAttribute("idEvento", updateId);
				verEvento(request);
			}
			return new ModelAndView("verEvento", "currentEvento", evento);
		} else {
			System.out.println(result.getAllErrors().toString());
			System.out.println("ocorreu um erro");
			logger.debug("Existem Erros:" + result.hasErrors());
			Evento eventoNew = new Evento();
			eventoNew.setPaciente(paciente);
			return new ModelAndView("editarEvento", "currentEvento", eventoNew);
		}
	}

	@RequestMapping(value = "/inserirEvento", method = RequestMethod.GET)
	public ModelAndView inserirEvento(HttpServletRequest request) {
		System.out.println("inserirEvento");
		removeFamilySessionAttributes();

		session = request.getSession();

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		Evento evento = new Evento();
		EventoId eventoId = new EventoId();

		eventoId.setIdPaciente(paciente.getIdPaciente());
		evento.setId(eventoId);
		evento.setPaciente(paciente);

		List<RelacaoPacienteFamiliar> listRelacaoPacienteFamiliar = relacaoPacienteFamiliarService.list(paciente);
		paciente.setRelacaoPacienteFamiliars(listRelacaoPacienteFamiliar);

		HashMap<String, Familiar> listFamiliar = new HashMap<String, Familiar>();

		for (RelacaoPacienteFamiliar relacao : listRelacaoPacienteFamiliar) {
			listFamiliar.put(relacao.getTipoRelacao(), relacao.getFamiliar());
		}

		session.setAttribute("listFamiliares", listFamiliar);

		return new ModelAndView("inserirEvento", "eventoModel", evento);
	}

	@RequestMapping(value = "/inserirEvento", method = RequestMethod.POST)
	public ModelAndView inserirEvento(@ModelAttribute("eventoModel") @Valid Evento evento, BindingResult result,
			HttpServletRequest request) {

		System.out.println("inserir evento post");
		session = request.getSession();

		int idFamiliar = 0;
		if (request.getAttribute("temFamiliar") != null) {
			idFamiliar = evento.getFamiliar().getIdFamiliar();
		}

		Paciente pacienteEvento = evento.getPaciente();

		EventoId eventoId = evento.getId();
		int idEventoId = eventoId.getIdEvento();
		if (!result.hasErrors()) {
			System.out.println("Não tem erros");
			if (idEventoId != 0 && eventoService.get(idEventoId) != null) {
				System.out.println("Já existe esse evento");
				result.rejectValue("idEventoId", "CustomMessage", "Já existe um evento igual");
			} else {
				int newId = 0;

				if (idFamiliar > 0) {
					Familiar familiar = familiarService.get(idFamiliar);
					evento.setFamiliar(familiar);
					newId = eventoService.saveOrUpdateWithFamily(evento);
				} else {
					newId = eventoService.saveOrUpdateWithoutFamily(evento);
				}

				session.setAttribute("idEvento", newId);
				session.removeAttribute("listFamiliares");
				verEvento(request);
			}
			return new ModelAndView("verEvento", "currentEvento", evento);
		} else {
			System.out.println(result.getAllErrors().toString());
			System.out.println("ocorreu um erro");
			logger.debug("Existem Erros:" + result.hasErrors());
			Evento eventoNew = new Evento();
			eventoNew.setPaciente(pacienteEvento);
			return new ModelAndView("inserirEvento", "currentEvento", eventoNew);
		}
	}

	@RequestMapping(value = "/listarEventos", method = RequestMethod.GET)
	public ModelAndView listarEvento(HttpServletRequest request) {
		System.out.println("listar Eventos");

		session = request.getSession();

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");

		List<Evento> listEvento = eventoService.list(paciente);
		paciente.setEventos(listEvento);
		removeFamilySessionAttributes();
		return new ModelAndView("listarEventos", "listEventos", listEvento);
	}

}
