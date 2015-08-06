package com.mem.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FamiliaresController {
//	private static final Logger logger = LoggerFactory.getLogger(FamiliaresController.class);
//	private static final IRepository<Familiar> repo = DataRepositoryLocator.getFamiliarRepository();
	
	public FamiliaresController() {}
	
	
	@RequestMapping(value = "/verFamiliar", method=RequestMethod.GET)
	public ModelAndView verFamiliar()
	{
		System.out.println("ver Familiar");
		return new ModelAndView("verFamiliar");
	}
	
//	@RequestMapping(value = "/verFamiliar", method = RequestMethod.GET)
//	public ModelAndView verFamiliar(int id) {
//		return new ModelAndView("verFamiliar","familiarModel",repo.select(id));
//	}
	
	
	@RequestMapping(value="/editarFamiliar", method=RequestMethod.GET)
	public ModelAndView editarFamiliar()
	{
		System.out.println("editar Familiar");
		return new ModelAndView("inserirFamiliar");
	}
	
//	@RequestMapping(value = "/editarFamiliar", method = RequestMethod.GET)
//	public ModelAndView editarFamiliar(int id) {
//		return new ModelAndView("editarFamiliar","familiarModel",repo.select(id));
//	}
	
	@RequestMapping(value = "/editarFamiliar", method = RequestMethod.POST)
	public String  editarFamiliar( int idFamiliar){
		//TODO
		return "editarFamiliar";
}
	
//	@RequestMapping(value = "/editarFamiliar", method = RequestMethod.POST)
//	public String  editarFamiliar( 
//			@ModelAttribute("familiarModel") @Valid Familiar familiarModel,
//			BindingResult result) {
//		if (!result.hasErrors()) {
//			if (repo.update(familiarModel))
//				return "verFamiliar";
//			else {
//				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
//			}
//		}
//		logger.debug("Existem Erros:" +result.hasErrors());
//		return "editarFamiliar";
//	}
	
	@RequestMapping(value = "/inserirFamiliar",  method = RequestMethod.GET)
	public ModelAndView inserirFamiliar()
	{
		System.out.println("inserir Familiar");
		return new ModelAndView("inserirFamiliar");
	}
	
//	@RequestMapping(value = "/inserirFamiliar", method = RequestMethod.POST)
//	public String  inserirFamiliar( 
//			@ModelAttribute("familiarModel") @Valid Doctor familiarModel,
//			BindingResult result) {
//		if (!result.hasErrors()) {
//			familiarModel.setIdFamiliar(-1);
//			if (repo.create(familiarModel))
//				return "verFamiliar";
//			else {
//				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
//			}
//		}
//		logger.debug("Existem Erros:" +result.hasErrors());
//		return "verFamiliar";
//	}
	
	@RequestMapping(value = "/listarFamiliares",  method = RequestMethod.GET)
	public ModelAndView listarFamiliar()
	{
		System.out.println("listar Familiar");
		return new ModelAndView("listarFamiliares");
	}	
}
