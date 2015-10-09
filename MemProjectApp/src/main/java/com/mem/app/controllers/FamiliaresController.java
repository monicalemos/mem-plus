package com.mem.app.controllers;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mem.app.model.Familiar;
import com.mem.app.model.Paciente;
import com.mem.app.model.RelacaoPacienteFamiliar;
import com.mem.app.model.RelacaoPacienteFamiliarId;
import com.mem.app.services.FamiliarService;
import com.mem.app.services.RelacaoPacienteFamiliarService;


@Controller
@RequestMapping(value = "/Familiar")
public class FamiliaresController {
	private static final Logger logger = LoggerFactory.getLogger(FamiliaresController.class);
	
	RelacaoPacienteFamiliarService relacaoPacienteFamiliarService;
	
	FamiliarService familiarService;

	
	@Autowired
	public void setRelacaoPacienteFamiliarService(RelacaoPacienteFamiliarService relacaoPacienteFamiliarService) {
		this.relacaoPacienteFamiliarService = relacaoPacienteFamiliarService;
	}
	
	@Autowired
	public void setFamiliarService(FamiliarService familiarService) {
		this.familiarService = familiarService;
	}
	public FamiliaresController() {}
	
	HttpSession session = null;
	
	
	@RequestMapping(value = "/verFamiliar", method=RequestMethod.GET)
	public ModelAndView verFamiliar(HttpServletRequest request)
	{
		System.out.println("ver Familiar");
		
		session = request.getSession();

		Familiar familiar = null;
		
		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		
		RelacaoPacienteFamiliar relacao = null;
		Integer relacaoId = 0;
		
		if((RelacaoPacienteFamiliar)session.getAttribute("currentRelacao") != null){
			relacao = (RelacaoPacienteFamiliar)session.getAttribute("currentRelacao");
		}
		else if(session.getAttribute("idRelacaoPacienteFamiliar")!= null){
			relacaoId = (Integer) session.getAttribute("idRelacaoPacienteFamiliar");
			relacao = relacaoPacienteFamiliarService.get(relacaoId);
		}
		
		int idRelacaoPacienteFamiliar = 0;
		if (request.getParameter("idRelacaoPacienteFamiliar") != null){
			idRelacaoPacienteFamiliar = Integer.parseInt(request.getParameter("idRelacaoPacienteFamiliar"));
		}else {
			if (session.getAttribute("idRelacaoPacienteFamiliar") != null){
				idRelacaoPacienteFamiliar = (Integer) session.getAttribute("idRelacaoPacienteFamiliar");
			}
		}
		if(idRelacaoPacienteFamiliar > 0){
			relacao = relacaoPacienteFamiliarService.get(idRelacaoPacienteFamiliar);
			familiar = relacao.getFamiliar();
		}
		else{
			Integer idFamiliar = 0;
			if (request.getParameter("idFamiliar") != null){
				idFamiliar = Integer.parseInt(request.getParameter("idFamiliar"));
			}
			else {
				if (session.getAttribute("idFamiliar") != null)
					idFamiliar = (Integer) session.getAttribute("idFamiliar");
			}
			familiar = familiarService.get(idFamiliar);
			relacao = relacaoPacienteFamiliarService.getWithPatientAndFamily(paciente, familiar);
		}
		session.setAttribute("currentRelacao", relacao);
		
		return new ModelAndView("verFamiliar", "currentRelacao", relacao);
	}
	
	@RequestMapping(value = "/verRelacao", method = RequestMethod.POST)
	public ModelAndView verFamiliar(@RequestParam(value = "error", required = false) boolean error, ModelMap model,
			HttpServletRequest request) {

		Familiar familiar = null;
		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		RelacaoPacienteFamiliar relacao = (RelacaoPacienteFamiliar)session.getAttribute("currentRelacao");
			
		if (error) {
			System.out.println("Houve Erros");
			model.put("viewFamiliar-error", "Não conseguiu mostrar os dados do familiar");
			return new ModelAndView("ver-paciente", "currentPaciente", paciente);
		} else {
			model.put("viewProfile-error", "");
			System.out.println("não houve erros");
			
			int idRelacaoPacienteFamiliar = 0;
			if (request.getParameter("idRelacaoPacienteFamiliar") != null){
				idRelacaoPacienteFamiliar = Integer.parseInt(request.getParameter("idRelacaoPacienteFamiliar"));
			}else {
				if (session.getAttribute("idRelacaoPacienteFamiliar") != null){
					idRelacaoPacienteFamiliar = (Integer) session.getAttribute("idRelacaoPacienteFamiliar");
				}
			}
			
			if(idRelacaoPacienteFamiliar > 0){
				relacao = relacaoPacienteFamiliarService.get(idRelacaoPacienteFamiliar);
				familiar = relacao.getFamiliar();
			}
			else{
				int idFamiliar = 0;
				if (request.getParameter("idFamiliar") != null){
					idFamiliar = Integer.parseInt(request.getParameter("idFamiliar"));
				}
				else {
					if (session.getAttribute("idFamiliar") != null)
						idFamiliar = (Integer) session.getAttribute("idFamiliar");
				}
				familiar = familiarService.get(idFamiliar);
			}
			
			if(familiar != null)
				return new ModelAndView("verFamiliar", "currentFamiliar", familiar);
			else {
				model.put("viewFamiliar-error", "Não conseguiu mostrar os dados do familiar");
				return new ModelAndView("ver-paciente", "currentPaciente", paciente);
			}
		}
	}
	
	
	@RequestMapping(value="/editarFamiliar", method=RequestMethod.GET)
	public ModelAndView editarFamiliar(HttpServletRequest request)
	{
		System.out.println("editarFamiliar");
		
		session = request.getSession();
		
		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
				
		int idFamiliar = 0;
		if (request.getParameter("idFamiliar") != null){
			idFamiliar = Integer.parseInt(request.getParameter("idFamiliar"));
		}
		else {
			if (session.getAttribute("idFamiliar") != null)
				idFamiliar = (Integer) session.getAttribute("idFamiliar");
		}
		Familiar familiar = familiarService.get(idFamiliar);
		RelacaoPacienteFamiliar relacao = relacaoPacienteFamiliarService.getWithPatientAndFamily(paciente, familiar);
		return new ModelAndView("editarFamiliar", "relacaoModel", relacao);
	}

	
	@RequestMapping(value = "/editarFamiliar", method = RequestMethod.POST)
	public ModelAndView  editarFamiliar(@ModelAttribute("currentRelacao") @Valid RelacaoPacienteFamiliar relacao , BindingResult result,
			HttpServletRequest request){
		System.out.println("Vou editar a relação " + relacao);
		
		session = request.getSession();

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		Paciente pacienteRel = relacao.getPaciente();
		
		RelacaoPacienteFamiliarId relacaoId = relacao.getId();
		
		if(relacaoId.getIdFamiliar() == 0)
			relacaoId.setIdPaciente(paciente.getIdPaciente());
		relacao.setId(relacaoId);
		relacao.setPaciente(paciente);
		
		if(pacienteRel == null){
			relacao.setPaciente(paciente);
		}
		
		int idRelacaoPacienteFamiliarId = relacaoId.getIdRelacaoPacienteFamiliar();
		
		
		if (!result.hasErrors()) {
			System.out.println("Não tem erros");
			if (idRelacaoPacienteFamiliarId == 0 && relacaoPacienteFamiliarService.get(idRelacaoPacienteFamiliarId) == null) {
				System.out.println("Essa relação não existe");
				result.rejectValue("idRelacaoPacienteFamiliar", "CustomMessage", "Essa relação não existe");
			} else {
				System.out.println("vai editar a relacao");
				familiarService.saveOrUpdate(relacao.getFamiliar());
				int updateId = relacaoPacienteFamiliarService.saveOrUpdate(relacao);
				System.out.println("ja editou a relacao " + updateId);
				verFamiliar(request);
			}
			return new ModelAndView("verFamiliar", "currentRelacao", relacao);
		} else {
			System.out.println(result.getAllErrors().toString());
			System.out.println("ocorreu um erro");
			logger.debug("Existem Erros:" + result.hasErrors());
			RelacaoPacienteFamiliar relacaoNew = new RelacaoPacienteFamiliar();
			relacaoNew.setPaciente(paciente);
			return new ModelAndView("editarFamiliar", "currentRelacao", relacaoNew);
		}
}

	
	@RequestMapping(value = "/inserirFamiliar", method = RequestMethod.GET)
	public ModelAndView inserirFamiliar(HttpServletRequest request) {
		System.out.println("inserirFamiliar");
		
		session = request.getSession();
		
		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		RelacaoPacienteFamiliar relacao = new RelacaoPacienteFamiliar();
		RelacaoPacienteFamiliarId relacaoId = new RelacaoPacienteFamiliarId();
		
		relacaoId.setIdPaciente(paciente.getIdPaciente());
		relacao.setId(relacaoId);
		relacao.setPaciente(paciente);
		return new ModelAndView("inserirFamiliar", "relacaoModel", relacao);
	}

	@RequestMapping(value = "/inserirFamiliar", method = RequestMethod.POST)
	public ModelAndView inserirFamiliar(@Valid @ModelAttribute("relacaoModel") RelacaoPacienteFamiliar relacao, BindingResult result,
			HttpServletRequest request) {
		
		System.out.println("inserir Familiar post");
		session = request.getSession();

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");

		Paciente pacienteRel = relacao.getPaciente();
		RelacaoPacienteFamiliarId relacaoId = new RelacaoPacienteFamiliarId();
		
		relacaoId.setIdPaciente(paciente.getIdPaciente());
		relacao.setId(relacaoId);
		relacao.setPaciente(paciente);
		
		if(pacienteRel == null){
			relacao.setPaciente(paciente);
		}
		int idRelacaoPacienteFamiliarId = relacao.getId().getIdRelacaoPacienteFamiliar();
		if (!result.hasErrors()) {
			System.out.println("Não tem erros");
			if (idRelacaoPacienteFamiliarId != 0 && relacaoPacienteFamiliarService.get(idRelacaoPacienteFamiliarId) != null) {
				System.out.println("Já existe essa relação");
				result.rejectValue("idRelacaoPacienteFamiliar", "CustomMessage", "Já existe uma relacao igual");
			} else {
				System.out.println("vai inserir a relacao");
				int newId = relacaoPacienteFamiliarService.saveOrUpdate(relacao);
				session.setAttribute("idRelacaoPacienteFamiliar", newId);
				verFamiliar(request);
			}
			return new ModelAndView("verFamiliar", "currentRelacao", relacao);
		} else {
			System.out.println(result.getAllErrors().toString());
			System.out.println("ocorreu um erro");
			logger.debug("Existem Erros:" + result.hasErrors());
			RelacaoPacienteFamiliar relacaoNew = new RelacaoPacienteFamiliar();
			relacaoNew.setPaciente(paciente);
			return new ModelAndView("inserirFamiliar", "currentRelacao", relacaoNew);
		}
	}
	
	@RequestMapping(value = "/listarFamiliares",  method = RequestMethod.GET)
	public ModelAndView listarFamiliar(HttpServletRequest request)
	{
		System.out.println("listar Familiar");
		
		session = request.getSession();
		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");

		List<RelacaoPacienteFamiliar> listRelacaoPacienteFamiliar = relacaoPacienteFamiliarService.list(paciente);
		paciente.setRelacaoPacienteFamiliars(listRelacaoPacienteFamiliar);
		
		HashMap<String, Familiar> listFamiliar = new HashMap<String, Familiar>();
		
		for(RelacaoPacienteFamiliar relacao : listRelacaoPacienteFamiliar){
			listFamiliar.put(relacao.getTipoRelacao(), relacao.getFamiliar());
		}
		
		return new ModelAndView("listarFamiliares", "listFamiliares", listFamiliar);
	}	
}
