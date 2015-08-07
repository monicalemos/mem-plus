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
import pt.memplus.web.models.Patient;

@Controller
@RequestMapping(value = "/Patient")
public class PatientController {
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	private static final IRepository<Patient> repo = DataRepositoryLocator.getPacientRepository();

	public PatientController() {}


	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		//TODO Devolver lista de Pacientes 
		return new ModelAndView("patient-home","patientModelCollection",repo.getAll());
	}
	
	/*
	 * CREATE NEW PACIENT
	 */
	@RequestMapping(value = "/Create", method = RequestMethod.GET)
	public ModelAndView newRecord() {
		return new ModelAndView("patient-new", "patientModel",new Patient());
	}
	
	@RequestMapping(value = "/Create", method = RequestMethod.POST)
	public String  newRecord( 
			@ModelAttribute("patientModel") @Valid Patient patientModel,
			BindingResult result) {
		if (!result.hasErrors()) {
			patientModel.setId(-1);
			if (repo.create(patientModel))
				return "patient-detail";
			else {
				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			}
		}
		logger.debug("Existem Erros:" +result.hasErrors());
		return "patient-new";

	}
	/*
	 * READ A PACIENT
	 */
	@RequestMapping(value = "/Detail", method = RequestMethod.GET)
	public ModelAndView viewRecord(int id) {
		return new ModelAndView("patient-detail","patientModel",repo.select(id));
	}
	
	/*
	 * UPDATE A PACIENT
	 */
	@RequestMapping(value = "/Update", method = RequestMethod.GET)
	public ModelAndView editRecord(int id) {
		return new ModelAndView("patient-edit","patientModel",repo.select(id));
	}
	@RequestMapping(value = "/Update", method = RequestMethod.POST)
	public String  editRecord( 
			@ModelAttribute("patientModel") @Valid Patient patientModel,
			BindingResult result) {
		if (!result.hasErrors()) {
			if (repo.update(patientModel))
				return "patient-detail";
			else {
				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			}
		}
		logger.debug("Existem Erros:" +result.hasErrors());
		return "patient-edit";
	}
	
	/*
	 * DELETE A PACIENT
	 */
	@RequestMapping(value = "/Delete", method = RequestMethod.GET)
	public ModelAndView deleteRecord(int id) {
		//TODO Handle the View and Model
		return new ModelAndView("patient-delete","patientModel",repo.select(id));
	}
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public String deletedRecord(int id) {
			if (repo.delete(id))
				return "patient-home";
		logger.debug("Ocorreram Erros:");
		return "patient-delete";
	}	
	
}
