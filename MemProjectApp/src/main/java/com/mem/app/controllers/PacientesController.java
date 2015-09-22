package com.mem.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	public PacientesController() {}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		//TODO Handle the View and Model 
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/verPaciente", method=RequestMethod.GET)
	public ModelAndView verPaciente(int id)
	{
		System.out.println("ver Paciente");
		return new ModelAndView("verPaciente");
	}
	
	@RequestMapping(value = "/verPaciente", method = RequestMethod.POST)
	public ModelAndView  getVerPaciente( int id, BindingResult result) {
		System.out.println("vou ver o paciente q inseri");
		if (!result.hasErrors()) {
			if (pacienteService.get(id) != null){
				System.out.println("encontrou o paciente");
				return new ModelAndView("verPaciente", "currentPaciente", pacienteService.get(id));
			}
			else {
				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			}
		}
		logger.debug("Existem Erros:" +result.hasErrors());
		return new ModelAndView("verPaciente", "currentPaciente", null);
	}
	
	
	@RequestMapping(value="/editarPaciente", method=RequestMethod.GET)
	public ModelAndView editarPaciente()
	{
		System.out.println("editar Paciente");
		return new ModelAndView("inserirPaciente");
	}
	
//	@RequestMapping(value = "/editarPaciente", method = RequestMethod.GET)
//	public ModelAndView editarPaciente(int id) {
//		return new ModelAndView("editarPaciente","pacienteModel",repo.select(id));
//	}
	
//	@RequestMapping(value = "/editarPaciente", method = RequestMethod.GET)
//	public String  editarPaciente( int idPaciente){
//		//TODO
//		return "editarPaciente";
//}
	
//	@RequestMapping(value = "/editarPaciente", method = RequestMethod.POST)
//	public String  editarPaciente( 
//			@ModelAttribute("pacienteModel") @Valid Paciente pacienteModel,
//			BindingResult result) {
//		if (!result.hasErrors()) {
//			if (repo.update(pacienteModel))
//				return "verPaciente";
//			else {
//				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
//			}
//		}
//		logger.debug("Existem Erros:" +result.hasErrors());
//		return "editarPaciente";
//	}
	
	@RequestMapping(value = "/inserirPaciente",  method = RequestMethod.GET)
	public ModelAndView inserirPaciente()
	{
		System.out.println("inserirPaciente");
		return new ModelAndView("inserirPaciente", "pacienteModel", new Paciente());
	}
	
	@RequestMapping(value = "/inserirPaciente", method = RequestMethod.POST)
	public String  inserirPaciente( 
			@Valid @ModelAttribute("pacienteModel") Paciente paciente,
			BindingResult result,
			HttpServletRequest request) {
				
		session = request.getSession();
		Tecnico tecnico = (Tecnico)session.getAttribute("currentTecnico");
		System.out.println("tecnico: " + tecnico);
		System.out.println("tem nome: " + tecnico.getNomeProprio());
		
		System.out.println(paciente.getNomeProprio());
		paciente.setTecnico(tecnico);
		
		if (!result.hasErrors()) {
			System.out.println("Não tem erros");
			if (paciente.getIdPaciente() != 0 && pacienteService.get(paciente.getIdPaciente()) != null) {
				System.out.println("Já existe um paciente igual");
				result.rejectValue("idPaciente", "CustomMessage", "Já existe um paciente igual");
			} else {
				System.out.println("vai inserir");
				pacienteService.saveOrUpdate(paciente);
				System.out.println("ja inseriu");
				getVerPaciente(paciente.getIdPaciente(), null);
			}
			return null;
		} else {
			System.out.println(result.getAllErrors().toString());
		//	result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			System.out.println("ocorreu um erro");
			logger.debug("Existem Erros:" + result.hasErrors());
			return "inserirPaciente";
		}
	}
	
	@RequestMapping(value = "/listarPacientes",  method = RequestMethod.GET)
	public ModelAndView listarPaciente(HttpServletRequest request)
	{
		System.out.println("listar Paciente");

		session = request.getSession();
		Tecnico tecnico = (Tecnico)session.getAttribute("currentTecnico");
		System.out.println("tecnico: " + tecnico);
		System.out.println("tem nome 2: " + tecnico.getNomeProprio());
		
		List<Paciente> listPaciente = pacienteService.list(tecnico.getIdTecnico());
		tecnico.setPacientes(listPaciente);
		return new ModelAndView("listarPacientes", "currentTecnico", tecnico);
	}
}
