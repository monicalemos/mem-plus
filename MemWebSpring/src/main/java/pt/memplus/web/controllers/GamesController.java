package pt.memplus.web.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Games")
public class GamesController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		return  new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/Jigsaw", method = RequestMethod.GET)
	public ModelAndView jigsaw() {
		System.out.println("Estou a  entrar na pagina");
		return new ModelAndView("Games-jigsaw");
	}

	@RequestMapping(value = "/Phone", method = RequestMethod.GET)
	public ModelAndView phone() {
		System.out.println("Estou a  entrar na pagina");
		return new ModelAndView("Games-phone");
	}
	@RequestMapping(value = "/Money", method = RequestMethod.GET)
	public ModelAndView money() {
		System.out.println("Estou a  entrar na pagina");
		return new ModelAndView("Games-money");
	}	
	@RequestMapping(value = "/Jigsaw/{id}", method = RequestMethod.GET)
	public BufferedImage image(@PathVariable("id") long id,HttpServletRequest request,HttpServletResponse response)
	{
		
		BufferedImage image = null;
		try {
			response.setContentType("image/jpeg");  
			 image = ImageIO.read(new File("C:/Dev/gitbub/masterzdran/MemWe/MemWebSpring/src/main/webapp/resources/static/img/games/jigsaw/flapjack.jpg"));
			 OutputStream out = response.getOutputStream();
			 ImageIO.write(image, "jpg", out);
		} catch (IOException e) {
			 image = null;
		}
		return image;
	}
	
}
