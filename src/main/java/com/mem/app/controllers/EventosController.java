package com.mem.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventosController {
	
//	private static final Logger logger = LoggerFactory.getLogger(EventosController.class);
//	private static final IRepository<Evento> repo = DataRepositoryLocator.getEventoRepository();
	
	public EventosController() {}

	
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
	public ModelAndView inserirEvento()
	{
		System.out.println("inserir Evento");
		return new ModelAndView("inserirEvento");
	}
	
//	@RequestMapping(value = "/inserirEvento", method = RequestMethod.POST)
//	public String  inserirEvento( 
//			@ModelAttribute("eventoModel") @Valid Doctor eventoModel,
//			BindingResult result) {
//		if (!result.hasErrors()) {
//			eventoModel.setIdEvento(-1);
//			if (repo.create(eventoModel))
//				return "verEvento";
//			else {
//				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
//			}
//		}
//		logger.debug("Existem Erros:" +result.hasErrors());
//		return "verEvento";
//	}
	
	@RequestMapping(value = "/listarEventos",  method = RequestMethod.GET)
	public ModelAndView listarEvento()
	{
		System.out.println("listar Evento");
		return new ModelAndView("listarEventos");
	}	
	
}
