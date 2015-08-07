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
import pt.memplus.web.models.Doctor;

@Controller
@RequestMapping(value = "/Doctor")
public class DoctorController {
	private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);
	private static final IRepository<Doctor> repo = DataRepositoryLocator.getDoctorRepository();
	
	public DoctorController() {}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		//TODO Handle the View and Model 
		return new ModelAndView("doctor-home");
	}
	
	/*
	 * CREATE NEW DOCTOR
	 */
	@RequestMapping(value = "/Create", method = RequestMethod.GET)
	public ModelAndView newRecord() {
		return new ModelAndView("doctor-new", "doctotModel",new Doctor());
	}
	@RequestMapping(value = "/Create", method = RequestMethod.POST)
	public String  newRecord( 
			@ModelAttribute("doctotModel") @Valid Doctor doctotModel,
			BindingResult result) {
		if (!result.hasErrors()) {
			doctotModel.setId(-1);
			if (repo.create(doctotModel))
				return "doctor-detail";
			else {
				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			}
		}
		logger.debug("Existem Erros:" +result.hasErrors());
		return "doctor-home";
	}
	/*
	 * READ A DOCTOR
	 */
	@RequestMapping(value = "/Detail", method = RequestMethod.GET)
	public ModelAndView viewRecord(int id) {
		return new ModelAndView("doctor-detail","doctotModel",repo.select(id));
	}
	/*
	 * UPDATE A DOCTOR
	 */
	@RequestMapping(value = "/Update", method = RequestMethod.GET)
	public ModelAndView editRecord(int id) {
		return new ModelAndView("doctor-edit","doctotModel",repo.select(id));
	}
	@RequestMapping(value = "/Update", method = RequestMethod.POST)
	public String  editRecord( 
			@ModelAttribute("doctotModel") @Valid Doctor doctotModel,
			BindingResult result) {
		if (!result.hasErrors()) {
			if (repo.update(doctotModel))
				return "doctor-detail";
			else {
				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			}
		}
		logger.debug("Existem Erros:" +result.hasErrors());
		return "doctor-edit";
	}
	/*
	 * DELETE A DOCTOR
	 */
	@RequestMapping(value = "/Delete", method = RequestMethod.GET)
	public ModelAndView deleteRecord(int id) {
		return new ModelAndView("doctor-edit","doctotModel",repo.select(id));
	}
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public String  deletedRecord(int id) {
		if (repo.delete(id))
			return "doctor-home";
		logger.debug("Ocorreram Erros:");
		return "doctor-home";
	}
}
