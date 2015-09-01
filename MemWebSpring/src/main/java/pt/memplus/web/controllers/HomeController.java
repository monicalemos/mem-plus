package pt.memplus.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pt.memplus.web.models.Address;
import pt.memplus.web.services.AddressService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {
	@Autowired
	AddressService addressService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		Iterable<Address>id =  addressService.getAll();
		
		for(Address d : id)
		{
			logger.info(d.getCountry());
		}
		
		return new ModelAndView("home");
	}
	@RequestMapping(value = "/About", method = RequestMethod.GET)
	public ModelAndView about() {
		//TODO Handle the View and Model
		return new ModelAndView("about");
	}
	@RequestMapping(value = "/Contacts", method = RequestMethod.GET)
	public ModelAndView contacts() {
		//TODO Handle the View and Model
		return new ModelAndView("contacts");
	}
	@RequestMapping(value = "/Mission", method = RequestMethod.GET)
	public ModelAndView mission() {
		//TODO Handle the View and Model
		return new ModelAndView("mission");
	}
	@RequestMapping(value = "/Values", method = RequestMethod.GET)
	public ModelAndView values() {
		//TODO Handle the View and Model
		return new ModelAndView("values");
	}
	@RequestMapping(value = "/Vision", method = RequestMethod.GET)
	public ModelAndView vision() {
		//TODO Handle the View and Model
		return new ModelAndView("vision");
	}
}
