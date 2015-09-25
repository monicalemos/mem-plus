package com.mem.app.controllers;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
//	private static final IRepository<Evento> repo = DataRepositoryLocator.getEventoRepository();
	
	EventoService eventoService;
	RelacaoPacienteFamiliarService relacaoPacienteFamiliarService;
	FamiliarService familiarService;

	public EventosController() {}
	
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
	
	@RequestMapping(value = "/verEvento", method=RequestMethod.GET)
	public ModelAndView verEvento()
	{
		System.out.println("ver Evento");
		return new ModelAndView("verEvento");
	}
	
//	@RequestMapping(value = "/verEvento", method = RequestMethod.GET)
//	public ModelAndView verEvento(int id) {
//		return new ModelAndView("verEvento","eventoModel",repo.select(id));
//	}
	
	
	@RequestMapping(value="/editarEvento", method=RequestMethod.GET)
	public ModelAndView editarEvento()
	{
		System.out.println("editar Evento");
		return new ModelAndView("inserirEvento");
	}
	
//	@RequestMapping(value = "/editarEvento", method = RequestMethod.GET)
//	public ModelAndView editarEvento(int id) {
//		return new ModelAndView("editarEvento","eventoModel",repo.select(id));
//	}
	
	@RequestMapping(value = "/editarEvento", method = RequestMethod.POST)
	public String  editarEvento( int idEvento){
		//TODO
		return "editarEvento";
}
	
//	@RequestMapping(value = "/editarEvento", method = RequestMethod.POST)
//	public String  editarEvento( 
//			@ModelAttribute("eventoModel") @Valid Evento eventoModel,
//			BindingResult result) {
//		if (!result.hasErrors()) {
//			if (repo.update(eventoModel))
//				return "verEvento";
//			else {
//				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
//			}
//		}
//		logger.debug("Existem Erros:" +result.hasErrors());
//		return "editarEvento";
//	}
	
	@RequestMapping(value = "/inserirEvento",  method = RequestMethod.GET)
	public ModelAndView inserirEvento(HttpServletRequest request) {
		System.out.println("inserirEvento");
		
		session = request.getSession();
		
		Tecnico tecnico = (Tecnico) session.getAttribute("currentTecnico");
		System.out.println("tecnico: " + tecnico);
		
		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		System.out.println("paciente session" + paciente);
		
		Evento evento = new Evento();
		EventoId eventoId = new EventoId();
		
		eventoId.setIdPaciente(paciente.getIdPaciente());
		evento.setId(eventoId);
		evento.setPaciente(paciente);
		
		System.out.println("Tem paciente: " + evento.getPaciente());
		
		List<RelacaoPacienteFamiliar> listRelacaoPacienteFamiliar = relacaoPacienteFamiliarService.list(paciente);
		paciente.setRelacaoPacienteFamiliars(listRelacaoPacienteFamiliar);
		
		HashMap<String, Familiar> listFamiliar = new HashMap<String, Familiar>();
		
		for(RelacaoPacienteFamiliar relacao : listRelacaoPacienteFamiliar){
			listFamiliar.put(relacao.getTipoRelacao(), relacao.getFamiliar());
		}
		
		System.out.println("Número de familiares " + listFamiliar.size());
		session.setAttribute("listFamiliares", listFamiliar);
		
		return new ModelAndView("inserirEvento", "eventoModel", evento);
	}
	
	@RequestMapping(value = "/inserirEvento", method = RequestMethod.POST)
	public ModelAndView  inserirEvento( 
			@ModelAttribute("eventoModel") @Valid Evento evento,
			BindingResult result, HttpServletRequest request) {
			
			System.out.println("inserir evento post");
			session = request.getSession();

			int idFamiliar = evento.getFamiliar().getIdFamiliar();
			System.out.println("IdFamiliar " + idFamiliar);
			Paciente pacienteEvento = evento.getPaciente();
			System.out.println("Paciente evento" + pacienteEvento);
			
			EventoId eventoId = evento.getId();
			System.out.println("eventoId " + eventoId);
			
			if (!result.hasErrors()) {	
				Familiar familiar = null;
				if(idFamiliar > 0 ){
					familiar = familiarService.get(idFamiliar);
					System.out.println("Encontrou o familiar do evento: " + familiar);
				}
				evento.setFamiliar(familiar);
				
				return new ModelAndView("verEvento", "currentEvento", evento);
			}else {
				System.out.println(result.getAllErrors().toString());
				System.out.println("ocorreu um erro");
				logger.debug("Existem Erros:" + result.hasErrors());
				Evento eventoNew = new Evento();
				eventoNew.setPaciente(pacienteEvento);
				return new ModelAndView("inserirEvento", "currentEvento", eventoNew);
			}
	}
	
	@RequestMapping(value = "/listarEventos",  method = RequestMethod.GET)
	public ModelAndView listarEvento()
	{
		System.out.println("listar Evento");
		return new ModelAndView("listarEventos");
	}	
	
}
