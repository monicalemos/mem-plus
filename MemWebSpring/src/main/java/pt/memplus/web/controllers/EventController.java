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
import pt.memplus.web.models.Event;

@Controller
@RequestMapping(value = "/Patient/Event")
public class EventController {

	private static final Logger logger = LoggerFactory.getLogger(RelativeController.class);
	private static final IRepository<Event> repo = DataRepositoryLocator.getEventRepository();

	public EventController() {}


	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(int personId) {
		//TODO Devolver lista de familiares do Patientes 
		return new ModelAndView("patient-event-home", "personEventsCollection", repo.getAll());
	}
	
	/*
	 * CREATE NEW PACIENT
	 */
	@RequestMapping(value = "/Create", method = RequestMethod.GET)
	public ModelAndView newRecord(int personId) {
		Event e = new Event();
		e.setRelatedToPersonId(personId);
		return new ModelAndView("patient-event-new", "eventModel",e);
	}
	
	@RequestMapping(value = "/Create", method = RequestMethod.POST)
	public String  newRecord( 
			@ModelAttribute("eventModel") @Valid Event eventModel,
			BindingResult result) {
	
		if (!result.hasErrors()) {
			eventModel.setId(-1);
			if (repo.create(eventModel))
				return "patient-event-detail";
			else {
				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			}
		}
		logger.debug("Existem Erros:" +result.hasErrors());
		return "patient-event-new";

	}
	/*
	 * READ A PACIENT
	 */
	@RequestMapping(value = "/Detail", method = RequestMethod.GET)
	public ModelAndView viewRecord(int id) {
		return new ModelAndView("patient-event-detail","eventModel",repo.select(id));
	}
	
	/*
	 * UPDATE A PACIENT
	 */
	@RequestMapping(value = "/Update", method = RequestMethod.GET)
	public ModelAndView editRecord(int id) {
		return new ModelAndView("patient-event-edit","eventModel",repo.select(id));
	}
	@RequestMapping(value = "/Update", method = RequestMethod.POST)
	public String  editRecord( 
			@ModelAttribute("eventModel") @Valid Event eventModel,
			BindingResult result) {
		if (!result.hasErrors()) {
			if (repo.update(eventModel))
				return "patient-event-detail";
			else {
				result.rejectValue("id", "CustomMessage", "Ocorreu um erro");
			}
		}
		logger.debug("Existem Erros:" +result.hasErrors());
		return "patient-event-edit";
	}
	
	/*
	 * DELETE A PACIENT
	 */
	@RequestMapping(value = "/Delete", method = RequestMethod.GET)
	public ModelAndView deleteRecord(int id) {
		//TODO Handle the View and Model
		return new ModelAndView("patient-event-delete","eventModel",repo.select(id));
	}
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public String deletedRecord(int id) {
			if (repo.delete(id))
				return "patient-event-home";
		logger.debug("Ocorreram Erros:");
		return "patient-event-delete";
	}	
	
}
