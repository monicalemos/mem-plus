package com.mem.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	PacienteService pacienteService;
	
	@Autowired
	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}
	
//	private static final Logger logger = LoggerFactory.getLogger(PacientesController.class);
// 	private static final IRepository<Paciente> repo = DataRepositoryLocator.getPacienteRepository();
	
	public PacientesController() {}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		//TODO Handle the View and Model 
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/verPaciente", method=RequestMethod.GET)
	public ModelAndView verPaciente()
	{
		System.out.println("ver Paciente");
		return new ModelAndView("verPaciente");
	}
	
//	@RequestMapping(value = "/verPaciente", method = RequestMethod.GET)
//	public ModelAndView verPaciente(int id) {
//		return new ModelAndView("verPaciente","pacienteModel",repo.select(id));
//	}
	
	
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
	
	@RequestMapping(value = "/editarPaciente", method = RequestMethod.POST)
	public String  editarPaciente( int idPaciente){
		//TODO
		return "editarPaciente";
}
	
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
		System.out.println("inserir Paciente");
		return new ModelAndView("inserirPaciente");
	}
	
//	@RequestMapping(value = "/inserirPaciente", method = RequestMethod.POST)
//	public String  inserirPaciente( 
//			@ModelAttribute("pacienteModel") @Valid Doctor pacienteModel,
//			BindingResult result) {
//		if (!result.hasErrors()) {
//			pacienteModel.setIdPaciente(-1);
//			if (repo.create(pacienteModel))
//				return "verPaciente";
//			else {
//				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
//			}
//		}
//		logger.debug("Existem Erros:" +result.hasErrors());
//		return "verPaciente";
//	}
	
	@RequestMapping(value = "/listarPacientes",  method = RequestMethod.GET)
	public ModelAndView listarPaciente(@ModelAttribute("currentTecnico") Tecnico tecnico)
	{
		System.out.println("listar Paciente");
		System.out.println("tecnico: " + tecnico);
		List<Paciente> listPaciente = pacienteService.list(tecnico.getIdTecnico());
		tecnico.setPacientes(listPaciente);
		return new ModelAndView("listarPacientes", "currentTecnico", tecnico);
	}
}
