package pt.memplus.web.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pt.memplus.web.model.repository.DataRepositoryLocator;
import pt.memplus.web.model.repository.IRepository;
import pt.memplus.web.models.Relative;

@Controller
@RequestMapping(value = "/Patient/Relative")
public class RelativeController {
	private static final Logger logger = LoggerFactory.getLogger(RelativeController.class);
	private static final IRepository<Relative> repo = DataRepositoryLocator.getRelativeRepository();

	public RelativeController() {}


	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(int patientId) {
		//TODO Devolver lista de familiares do Patientes 
		return new ModelAndView("patient-relative-home","patientRelativesCollection", repo.getAll());
	}
	
	/*
	 * CREATE NEW PACIENT
	 */
	@RequestMapping(value = "/Create", method = RequestMethod.GET)
	public ModelAndView newRecord() {
		return new ModelAndView("patient-relative-new", "relativeModel",new Relative());
	}
	
	@RequestMapping(value = "/Create", method = RequestMethod.POST)
	public String  newRecord( 
			@ModelAttribute("relativeModel") @Valid Relative relativeModel,
			BindingResult result) {
	
		if (!result.hasErrors()) {
			relativeModel.setId(-1);
			if (repo.create(relativeModel))
				return "patient-relative-detail";
			else {
				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			}
		}
		logger.debug("Existem Erros:" +result.hasErrors());
		return "patient-relative-new";

	}
	/*
	 * READ A PACIENT
	 */
	@RequestMapping(value = "/Detail", method = RequestMethod.GET)
	public ModelAndView viewRecord(int id) {
		return new ModelAndView("patient-relative-detail","relativeModel",repo.select(id));
	}
	
	/*
	 * UPDATE A PACIENT
	 */
	@RequestMapping(value = "/Update", method = RequestMethod.GET)
	public ModelAndView editRecord(int id) {
		return new ModelAndView("patient-relative-edit","relativeModel",repo.select(id));
	}
	@RequestMapping(value = "/Update", method = RequestMethod.POST)
	public String  editRecord( 
			@ModelAttribute("relativeModel") @Valid Relative relativeModel,
			BindingResult result) {
		if (!result.hasErrors()) {
			if (repo.update(relativeModel))
				return "patient-relative-detail";
			else {
				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			}
		}
		logger.debug("Existem Erros:" +result.hasErrors());
		return "patient-relative-edit";
	}
	
	/*
	 * DELETE A PACIENT
	 */
	@RequestMapping(value = "/Delete", method = RequestMethod.GET)
	public ModelAndView deleteRecord(int id) {
		//TODO Handle the View and Model
		return new ModelAndView("patient-relative-delete","relativeModel",repo.select(id));
	}
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public String deletedRecord(int id) {
			if (repo.delete(id))
				return "patient-relative-home";
		logger.debug("Ocorreram Erros:");
		return "patient-relative-delete";
	}	
	


}
