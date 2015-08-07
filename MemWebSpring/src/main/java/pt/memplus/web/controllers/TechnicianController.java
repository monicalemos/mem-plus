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
import pt.memplus.web.models.Technician;

@Controller
@RequestMapping(value = "/Technician")
public class TechnicianController {
	private static final Logger logger = LoggerFactory.getLogger(TechnicianController.class);
	private static final IRepository<Technician> repo = DataRepositoryLocator.getTechnitianRepository();
	
	public TechnicianController() {}
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		//TODO Handle the View and Model 
		return new ModelAndView("technician-home");
	}
	
	/*
	 * CREATE NEW PACIENT
	 */
	@RequestMapping(value = "/Create", method = RequestMethod.GET)
	public ModelAndView newRecord() {
		return new ModelAndView("technician-new","technicianModel",new Technician());
	}
	@RequestMapping(value = "/Create", method = RequestMethod.POST)
	public String  newRecord(@ModelAttribute("technicianModel") @Valid Technician technicianModel,
			BindingResult result) {
		if (!result.hasErrors()) {
			technicianModel.setId(-1);
			if (repo.create(technicianModel))
				return "technician-detail";
			else {
				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			}
		}
		logger.debug("Existem Erros:" +result.hasErrors());
		return "technician-home";
	}
	/*
	 * READ A PACIENT
	 */
	@RequestMapping(value = "/Detail", method = RequestMethod.GET)
	public ModelAndView viewRecord(int id) {
		return new ModelAndView("technician-detail","technicianModel",repo.select(id));
	}
	/*
	 * UPDATE A PACIENT
	 */
	@RequestMapping(value = "/Update", method = RequestMethod.GET)
	public ModelAndView editRecord(int id) {
		return new ModelAndView("technician-edit","technicianModel",repo.select(id));
	}
	@RequestMapping(value = "/Update", method = RequestMethod.POST)
	public String  editRecord(@ModelAttribute("technicianModel") @Valid Technician technicianModel,
			BindingResult result) {
		if (!result.hasErrors()) {
			if (repo.update(technicianModel))
				return "technician-detail";
			else {
				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			}
		}
		logger.debug("Existem Erros:" +result.hasErrors());
		return "technician-edit";
	}
	/*
	 * DELETE A PACIENT
	 */
	@RequestMapping(value = "/Delete", method = RequestMethod.GET)
	public ModelAndView deleteRecord(int id) {
		return new ModelAndView("technician-delete","technicianModel",repo.select(id));
	}
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public String  deletedRecord(int id) {
		if (repo.delete(id))
			return "technician-home";
		logger.debug("Ocorreram Erros:");
		return "technician-delete";
	}	
	

}
