package com.mem.app.controllers;

import java.util.Enumeration;
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
import com.mem.app.model.RelacaoFamiliarFamiliar;
import com.mem.app.model.RelacaoFamiliarFamiliarId;
import com.mem.app.model.RelacaoPacienteFamiliar;
import com.mem.app.model.RelacaoPacienteFamiliarId;
import com.mem.app.services.FamiliarService;
import com.mem.app.services.RelacaoFamiliarFamiliarService;
import com.mem.app.services.RelacaoPacienteFamiliarService;

@Controller
@RequestMapping(value = "/Familiar")
public class FamiliaresController {
	private static final Logger logger = LoggerFactory.getLogger(FamiliaresController.class);

	RelacaoPacienteFamiliarService relacaoPacienteFamiliarService;

	RelacaoFamiliarFamiliarService relacaoFamiliarFamiliarService;

	FamiliarService familiarService;

	@Autowired
	public void setRelacaoPacienteFamiliarService(RelacaoPacienteFamiliarService relacaoPacienteFamiliarService) {
		this.relacaoPacienteFamiliarService = relacaoPacienteFamiliarService;
	}

	@Autowired
	public void setRelacaoFamiliarFamiliarService(RelacaoFamiliarFamiliarService relacaoFamiliarFamiliarService) {
		this.relacaoFamiliarFamiliarService = relacaoFamiliarFamiliarService;
	}

	@Autowired
	public void setFamiliarService(FamiliarService familiarService) {
		this.familiarService = familiarService;
	}

	public FamiliaresController() {
	}

	HttpSession session = null;

	/* Familiares 1º Grau: */

	@RequestMapping(value = "/verFamiliar", method = RequestMethod.GET)
	public ModelAndView verFamiliar(HttpServletRequest request) {
		System.out.println("ver Familiar directo");

		session = request.getSession();
		//session.removeAttribute("idRelacaoFamiliarFamiliar");

		Familiar familiar = null;

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");

		RelacaoPacienteFamiliar relacao = null;
		Integer relacaoId = 0;

		if ((RelacaoPacienteFamiliar) session.getAttribute("currentRelacao") != null) {
			relacao = (RelacaoPacienteFamiliar) session.getAttribute("currentRelacao");
		} else if (session.getAttribute("idRelacaoPacienteFamiliar") != null) {
			relacaoId = (Integer) session.getAttribute("idRelacaoPacienteFamiliar");
			relacao = relacaoPacienteFamiliarService.get(relacaoId);
		}

		int idRelacaoPacienteFamiliar = 0;
		if (request.getParameter("idRelacaoPacienteFamiliar") != null) {
			idRelacaoPacienteFamiliar = Integer.parseInt(request.getParameter("idRelacaoPacienteFamiliar"));
		} else {
			if (session.getAttribute("idRelacaoPacienteFamiliar") != null) {
				idRelacaoPacienteFamiliar = (Integer) session.getAttribute("idRelacaoPacienteFamiliar");
			}
		}
		if (idRelacaoPacienteFamiliar > 0) {
			relacao = relacaoPacienteFamiliarService.get(idRelacaoPacienteFamiliar);
			familiar = relacao.getFamiliar();
		} else {
			Integer idFamiliar = 0;
			//na tabela vem daqui
			if (request.getParameter("idFamiliar") != null) {
				System.out.println("id Familiar: " + request.getParameter("idFamiliar"));
				idFamiliar = Integer.parseInt(request.getParameter("idFamiliar"));
				familiar = familiarService.get(idFamiliar);
			} else {
				if (session.getAttribute("idFamiliar") != null){
					idFamiliar = (Integer) session.getAttribute("idFamiliar");
					familiar = familiarService.get(idFamiliar);
				}
				else if(null != session.getAttribute("currentFamiliar")){
					System.out.println("tem familiar na session");
					familiar = (Familiar)session.getAttribute("currentFamiliar");
				}
			}
		
			relacao = relacaoPacienteFamiliarService.getWithPatientAndFamily(paciente, familiar);
		}
		session.setAttribute("currentRelacao", relacao);

		session.setAttribute("currentFamiliar", familiar);
		Familiar fam = (Familiar) session.getAttribute("currentFamiliar");
		System.out.println("familiar: " + fam);

		System.out.println("listar Familiar");

		session.removeAttribute("currentRelacao");

		session = request.getSession();

		List<RelacaoFamiliarFamiliar> listRelacaoFamiliarFamiliar = relacaoFamiliarFamiliarService
				.listFromFamily(paciente, familiar);
		paciente.setRelacaoFamiliarFamiliars(listRelacaoFamiliarFamiliar);

		HashMap<String, Familiar> listFamiliar = new HashMap<String, Familiar>();

		for (RelacaoFamiliarFamiliar relacaoF : listRelacaoFamiliarFamiliar) {
			listFamiliar.put(relacaoF.getTipoRelacao(), relacaoF.getFamiliarByIdFamiliar1());
		}

		session.setAttribute("listFamiliares", listFamiliar);

		System.out.println("chegou ao fim do método:");
		return new ModelAndView("verFamiliar", "currentRelacao", relacao);
	}

	@RequestMapping(value = "/verFamiliar", method = RequestMethod.POST)
	public ModelAndView verFamiliar(@RequestParam(value = "error", required = false) boolean error, ModelMap model,
			HttpServletRequest request) {

		System.out.println("Vamos tentar o post");
		Familiar familiar = null;
		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		RelacaoPacienteFamiliar relacao = (RelacaoPacienteFamiliar) session.getAttribute("currentRelacao");

		if (error) {
			System.out.println("Houve Erros");
			model.put("viewFamiliar-error", "Não conseguiu mostrar os dados do familiar");
			return new ModelAndView("ver-paciente", "currentPaciente", paciente);
		} else {
			model.put("viewProfile-error", "");
			System.out.println("não houve erros");

			int idRelacaoPacienteFamiliar = 0;
			if (request.getParameter("idRelacaoPacienteFamiliar") != null) {
				idRelacaoPacienteFamiliar = Integer.parseInt(request.getParameter("idRelacaoPacienteFamiliar"));
			} else {
				if (session.getAttribute("idRelacaoPacienteFamiliar") != null) {
					idRelacaoPacienteFamiliar = (Integer) session.getAttribute("idRelacaoPacienteFamiliar");
				}
			}

			if (idRelacaoPacienteFamiliar > 0) {
				relacao = relacaoPacienteFamiliarService.get(idRelacaoPacienteFamiliar);
				familiar = relacao.getFamiliar();
			} else {
				int idFamiliar = 0;
				if (request.getParameter("idFamiliar") != null) {
					idFamiliar = Integer.parseInt(request.getParameter("idFamiliar"));
				} else {
					if (session.getAttribute("idFamiliar") != null)
						idFamiliar = (Integer) session.getAttribute("idFamiliar");
				}
				familiar = familiarService.get(idFamiliar);
				relacao = relacaoPacienteFamiliarService.getWithPatientAndFamily(paciente, familiar);
			}

			session.setAttribute("currentRelacao", relacao);

			session.setAttribute("currentFamiliar", familiar);
			Familiar fam = (Familiar) session.getAttribute("currentFamiliar");
			System.out.println("familiar: " + fam);
			System.out.println("Tem id? " + fam.getIdFamiliar());
			System.out.println("tem relação? " + relacao);

			System.out.println("listar Familiar");

			session.removeAttribute("currentRelacao");

			session = request.getSession();

			List<RelacaoFamiliarFamiliar> listRelacaoFamiliarFamiliar = relacaoFamiliarFamiliarService
					.listFromFamily(paciente, familiar);
			paciente.setRelacaoFamiliarFamiliars(listRelacaoFamiliarFamiliar);

			HashMap<String, Familiar> listFamiliar = new HashMap<String, Familiar>();

			for (RelacaoFamiliarFamiliar relacaoF : listRelacaoFamiliarFamiliar) {
				listFamiliar.put(relacaoF.getTipoRelacao(), relacaoF.getFamiliarByIdFamiliar1());
			}

			session.setAttribute("listFamiliares", listFamiliar);

			System.out.println("chegou ao fim do método post");
			return new ModelAndView("verFamiliar", "currentRelacao", relacao);
		}
	}

	@RequestMapping(value = "/editarFamiliar", method = RequestMethod.GET)
	public ModelAndView editarFamiliar(HttpServletRequest request) {
		System.out.println("editarFamiliar");

		session = request.getSession();

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		Familiar familiar = null;
		int idFamiliar = 0;
		if (request.getParameter("idFamiliar") != null) {
			idFamiliar = Integer.parseInt(request.getParameter("idFamiliar"));
			familiar = familiarService.get(idFamiliar);
		} else {
			if (session.getAttribute("idFamiliar") != null){
				idFamiliar = (Integer) session.getAttribute("idFamiliar");
				familiar = familiarService.get(idFamiliar);
			}else if(null != session.getAttribute("currentFamiliar")){
				System.out.println("tem familiar na session");
				familiar = (Familiar)session.getAttribute("currentFamiliar");
			}
		}
		
		RelacaoPacienteFamiliar relacao = relacaoPacienteFamiliarService.getWithPatientAndFamily(paciente, familiar);
		return new ModelAndView("editarFamiliar", "relacaoModel", relacao);
	}

	@RequestMapping(value = "/editarFamiliar", method = RequestMethod.POST)
	public ModelAndView editarFamiliar(@ModelAttribute("currentRelacao") @Valid RelacaoPacienteFamiliar relacao,
			BindingResult result, HttpServletRequest request) {
		System.out.println("Vou editar a relação " + relacao);

		session = request.getSession();

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		Paciente pacienteRel = relacao.getPaciente();

		RelacaoPacienteFamiliarId relacaoId = relacao.getId();

		if (relacaoId.getIdFamiliar() == 0)
			relacaoId.setIdPaciente(paciente.getIdPaciente());
		relacao.setId(relacaoId);
		relacao.setPaciente(paciente);

		if (pacienteRel == null) {
			relacao.setPaciente(paciente);
		}

		int idRelacaoPacienteFamiliarId = relacaoId.getIdRelacaoPacienteFamiliar();

		if (!result.hasErrors()) {
			System.out.println("Não tem erros");
			if (idRelacaoPacienteFamiliarId == 0
					&& relacaoPacienteFamiliarService.get(idRelacaoPacienteFamiliarId) == null) {
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

		session.removeAttribute("currentFamiliar");
		session.removeAttribute("idFamiliar");
		session.removeAttribute("currentRelacao");
		session.removeAttribute("idRelacaoPacienteFamiliar");
		session.removeAttribute("listFamiliares");
		session.removeAttribute("idRelacaoFamiliarFamiliar");

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		RelacaoPacienteFamiliar relacao = new RelacaoPacienteFamiliar();
		RelacaoPacienteFamiliarId relacaoId = new RelacaoPacienteFamiliarId();
		
		System.out.println("Tem paciente: " + paciente);
		
		relacaoId.setIdPaciente(paciente.getIdPaciente());
		relacao.setId(relacaoId);
		relacao.setPaciente(paciente);
		return new ModelAndView("inserirFamiliar", "relacaoModel", relacao);
	}

	@RequestMapping(value = "/inserirFamiliar", method = RequestMethod.POST)
	public ModelAndView inserirFamiliar(@Valid @ModelAttribute("relacaoModel") RelacaoPacienteFamiliar relacao,
			BindingResult result, HttpServletRequest request) {

		System.out.println("inserir Familiar post");
		session = request.getSession();

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");

		Paciente pacienteRel = relacao.getPaciente();
		RelacaoPacienteFamiliarId relacaoId = new RelacaoPacienteFamiliarId();

		relacaoId.setIdPaciente(paciente.getIdPaciente());
		relacao.setId(relacaoId);
		relacao.setPaciente(paciente);

		if (pacienteRel == null) {
			relacao.setPaciente(paciente);
		}
		int idRelacaoPacienteFamiliarId = relacao.getId().getIdRelacaoPacienteFamiliar();
		if (!result.hasErrors()) {
			System.out.println("Não tem erros");
			if (idRelacaoPacienteFamiliarId != 0
					&& relacaoPacienteFamiliarService.get(idRelacaoPacienteFamiliarId) != null) {
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

	@RequestMapping(value = "/listarFamiliares", method = RequestMethod.GET)
	public ModelAndView listarFamiliar(HttpServletRequest request) {
		System.out.println("listar Familiar");

		session = request.getSession();
		Enumeration<?> lista = session.getAttributeNames();
		while (lista.hasMoreElements()) {
			String value = (String) lista.nextElement();
			System.out.println("Atttribute name " + value);
			if (value.contains("Familiar") || value.contains("Relacao")) {
				session.removeAttribute(value);
			}
		}

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");

		List<RelacaoPacienteFamiliar> listRelacaoPacienteFamiliar = relacaoPacienteFamiliarService.list(paciente);
		System.out.println("Num familiares nivel 1");
		paciente.setRelacaoPacienteFamiliars(listRelacaoPacienteFamiliar);

		HashMap<String, Familiar> listFamiliar = new HashMap<String, Familiar>();

		for (RelacaoPacienteFamiliar relacao : listRelacaoPacienteFamiliar) {
			List<RelacaoFamiliarFamiliar> listRelacaoFamiliarFamiliar = relacaoFamiliarFamiliarService
					.listFromFamily(paciente, relacao.getFamiliar());
			for (RelacaoFamiliarFamiliar r : listRelacaoFamiliarFamiliar) {
				System.out.println(r.getTipoRelacao() + "\n--" + relacao.getFamiliar());
			}
			relacao.getFamiliar().setRelacaoFamiliarFamiliarsForIdFamiliar1(listRelacaoFamiliarFamiliar);
			listFamiliar.put(relacao.getTipoRelacao(), relacao.getFamiliar());
		}

		return new ModelAndView("listarFamiliares", "listFamiliares", listRelacaoPacienteFamiliar);
	}

	/* Familiares 2º Grau: */

	@RequestMapping(value = "/verSegundoGrauFamiliar", method = RequestMethod.GET)
	public ModelAndView verSegundoGrauFamiliar(HttpServletRequest request) {
		System.out.println("ver Familiar 2º grau get");

		session = request.getSession();

		Familiar familiar1 = null;

//		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");

		RelacaoFamiliarFamiliar relacao = null;
		System.out.println("Relacao: " + session.getAttribute("currentRelacao"));
		Integer idFamiliar = 0;
		if(null != session.getAttribute("currentRelacao")){
			relacao = (RelacaoFamiliarFamiliar)session.getAttribute("currentRelacao");
			if(null != relacao.getFamiliarByIdFamiliar1()){
				idFamiliar = relacao.getFamiliarByIdFamiliar1().getIdFamiliar();
			}
			
		}

		/*if (request.getParameter("idFamiliarSegundoGrau") != null) {
			idFamiliar = Integer.parseInt(request.getParameter("idFamiliarSegundoGrau"));
			System.out.println("idFamiliar "  + idFamiliar);
		} else {
			if (session.getAttribute("idFamiliarSegundoGrau") != null)
				System.out.println("idFamiliar "  + idFamiliar);
				idFamiliar = (Integer) session.getAttribute("idFamiliarSegundoGrau");
		}*/
		familiar1 = familiarService.get(idFamiliar);
		System.out.println("Familiar1: " + familiar1);
		//relacao = relacaoFamiliarFamiliarService.getWithPatientAndFamily2grau(paciente, familiar1);

		session.setAttribute("currentRelacao", relacao);

		session.setAttribute("currentFamiliar", familiar1);
		Familiar fam = (Familiar) session.getAttribute("currentFamiliar");
		System.out.println("familiar: " + fam);

		return new ModelAndView("verSegundoGrauFamiliar", "currentRelacao", relacao);
	}

	@RequestMapping(value = "/verSegundoGrauFamiliar", method = RequestMethod.POST, params = {"idFamiliarSegundoGrau"})
	public ModelAndView verSegundoGrauFamiliar(@RequestParam(value = "error", required = false) boolean error,
			ModelMap model, HttpServletRequest request) {
		System.out.println("ver Familiar 2º grau post");
		Familiar familiar1 = null;
		// Familiar familiar = (Familiar)
		// session.getAttribute("currentFamiliar");
		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		RelacaoFamiliarFamiliar relacao = (RelacaoFamiliarFamiliar) session.getAttribute("currentRelacao");
		System.out.println("Tem id familiar2? " + request.getAttribute("idFamiliarSegundoGrau"));
		if (error) {
			System.out.println("Houve Erros");
			model.put("viewFamiliar-error", "Não conseguiu mostrar os dados do familiar");
			return new ModelAndView("ver-paciente", "currentPaciente", paciente);
		} else {
			model.put("viewProfile-error", "");
			System.out.println("não houve erros");

			int idRelacaoFamiliarFamiliar = 0;
			if (request.getParameter("idRelacaoFamiliarFamiliar") != null) {
				idRelacaoFamiliarFamiliar = Integer.parseInt(request.getParameter("idRelacaoFamiliarFamiliar"));
			} else {
				if (session.getAttribute("idRelacaoFamiliarFamiliar") != null) {
					idRelacaoFamiliarFamiliar = (Integer) session.getAttribute("idRelacaoFamiliarFamiliar");
				}
			}

			if (idRelacaoFamiliarFamiliar > 0) {
				relacao = relacaoFamiliarFamiliarService.get(idRelacaoFamiliarFamiliar);
				familiar1 = relacao.getFamiliarByIdFamiliar1();
			} else {
				int idFamiliar = 0;
				if (request.getParameter("idFamiliar") != null) {
					idFamiliar = Integer.parseInt(request.getParameter("idFamiliar"));
				} else {
					if (session.getAttribute("idFamiliar") != null)
						idFamiliar = (Integer) session.getAttribute("idFamiliar");
				}
				familiar1 = familiarService.get(idFamiliar);
			}

			if (familiar1 != null) {
				session.setAttribute("currentFamiliar", familiar1);
				Familiar fam = (Familiar) session.getAttribute("currentFamiliar");
				System.out.println("familiar: " + fam);
				return new ModelAndView("verSegundoGrauFamiliar", "currentFamiliar", familiar1);
			} else {
				model.put("viewFamiliar-error", "Não conseguiu mostrar os dados do familiar");
				return new ModelAndView("ver-paciente", "currentPaciente", paciente);
			}
		}
	}

	@RequestMapping(value = "/inserirSegundoGrauFamiliar", method = RequestMethod.GET)
	public ModelAndView inserirSegundoGrauFamiliar(HttpServletRequest request) {
		System.out.println("inserirFamiliar em 2º grau");

		session = request.getSession();

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		Familiar familiar = (Familiar) session.getAttribute("currentFamiliar");
		System.out.println("familiar: " + familiar);
		RelacaoFamiliarFamiliar relacao = new RelacaoFamiliarFamiliar();
		RelacaoFamiliarFamiliarId relacaoId = new RelacaoFamiliarFamiliarId();

		relacaoId.setIdFamiliar(familiar.getIdFamiliar());
		relacao.setId(relacaoId);
		relacao.setPaciente(paciente);
		relacao.setFamiliarByIdFamiliar(familiar);
		return new ModelAndView("inserirSegundoGrauFamiliar", "relacaoModel", relacao);
	}

	@RequestMapping(value = "/inserirSegundoGrauFamiliar", method = RequestMethod.POST)
	public ModelAndView inserirSegundoGrauFamiliar(
			@Valid @ModelAttribute("relacaoModel") RelacaoFamiliarFamiliar relacao, BindingResult result,

			HttpServletRequest request) {

		System.out.println("inserir Familiar em 2º grau post");
		session = request.getSession();

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		System.out.println("Tem paciente: " + paciente);
		Familiar familiar = (Familiar) session.getAttribute("currentFamiliar");
		System.out.println("Tem familiar: " + familiar);

		Paciente pacienteRel = relacao.getPaciente();
		Familiar familiarRel = relacao.getFamiliarByIdFamiliar();
		RelacaoFamiliarFamiliarId relacaoId = new RelacaoFamiliarFamiliarId();

		relacaoId.setIdFamiliar(familiar.getIdFamiliar());
		relacao.setId(relacaoId);
		relacao.setPaciente(paciente);
		relacao.setFamiliarByIdFamiliar(familiar);

		if (pacienteRel == null) {
			relacao.setPaciente(paciente);
		}
		if (familiarRel == null) {
			relacao.setFamiliarByIdFamiliar(familiar);
		}
		int idRelacaoFamiliarFamiliarId = relacao.getId().getIdRelacaoFamiliarFamiliar();
		if (!result.hasErrors()) {
			System.out.println("Não tem erros");
			if (idRelacaoFamiliarFamiliarId != 0
					&& relacaoFamiliarFamiliarService.get(idRelacaoFamiliarFamiliarId) != null) {
				System.out.println("Já existe essa relação");
				result.rejectValue("idRelacaoFamiliarFamiliar", "CustomMessage", "Já existe uma relacao igual");
			} else {
				System.out.println("vai inserir a relacao");
				int newId = relacaoFamiliarFamiliarService.saveOrUpdate(relacao);
				session.setAttribute("idRelacaoFamiliarFamiliar", newId);
				session.setAttribute("currentRelacao", relacao);
				verSegundoGrauFamiliar(request);
			}
			return new ModelAndView("verSegundoGrauFamiliar", "currentRelacao", relacao);
		} else {
			System.out.println(result.getAllErrors().toString());
			System.out.println("ocorreu um erro");
			logger.debug("Existem Erros:" + result.hasErrors());
			RelacaoFamiliarFamiliar relacaoNew = new RelacaoFamiliarFamiliar();
			relacaoNew.setPaciente(paciente);
			relacaoNew.setFamiliarByIdFamiliar(familiar);
			return new ModelAndView("inserirSegundoGrauFamiliar", "currentRelacao", relacaoNew);
		}
	}

	@RequestMapping(value = "/editarSegundoGrauFamiliar", method = RequestMethod.GET)
	public ModelAndView editarSegundoGrauFamiliar(HttpServletRequest request) {
		System.out.println("editarFamiliar");

		session = request.getSession();

		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");

		int idFamiliar = 0;
		if (request.getParameter("idFamiliar") != null) {
			idFamiliar = Integer.parseInt(request.getParameter("idFamiliar"));
		} else {
			if (session.getAttribute("idFamiliar") != null)
				idFamiliar = (Integer) session.getAttribute("idFamiliar");
		}
		Familiar familiar = familiarService.get(idFamiliar);

		RelacaoFamiliarFamiliar relacao = relacaoFamiliarFamiliarService.getWithPatientAndFamily2grau(paciente,
				familiar);
		return new ModelAndView("editarSegundoGrauFamiliar", "relacaoModel", relacao);
	}

	@RequestMapping(value = "/editarSegundoGrauFamiliar", method = RequestMethod.POST)
	public ModelAndView editarSegundoGrauFamiliar(
			@ModelAttribute("currentRelacao") @Valid RelacaoFamiliarFamiliar relacao, BindingResult result,
			HttpServletRequest request) {
		System.out.println("Vou editar a relação " + relacao);

		session = request.getSession();
		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		Familiar familiar = (Familiar) session.getAttribute("currentFamiliar");

		Paciente pacienteRel = relacao.getPaciente();
		Familiar familiarRel = relacao.getFamiliarByIdFamiliar();
		RelacaoFamiliarFamiliarId relacaoId = new RelacaoFamiliarFamiliarId();

		relacaoId.setIdFamiliar(familiar.getIdFamiliar());
		relacao.setId(relacaoId);
		relacao.setPaciente(paciente);
		relacao.setFamiliarByIdFamiliar(familiar);

		if (pacienteRel == null) {
			relacao.setPaciente(paciente);
		}
		if (familiarRel == null) {
			relacao.setFamiliarByIdFamiliar(familiar);
		}

		int idRelacaoFamiliarFamiliarId = relacaoId.getIdRelacaoFamiliarFamiliar();

		if (!result.hasErrors()) {
			System.out.println("Não tem erros");
			if (idRelacaoFamiliarFamiliarId == 0
					&& relacaoFamiliarFamiliarService.get(idRelacaoFamiliarFamiliarId) == null) {
				System.out.println("Essa relação não existe");
				result.rejectValue("id", "CustomMessage", "Essa relação não existe");
			} else {
				System.out.println("vai editar a relacao");
				familiarService.saveOrUpdate(relacao.getFamiliarByIdFamiliar1());
				int updateId = relacaoFamiliarFamiliarService.saveOrUpdate(relacao);
				System.out.println("ja editou a relacao " + updateId);
				verSegundoGrauFamiliar(request);
			}
			return new ModelAndView("verSegundoGrauFamiliar", "currentRelacao", relacao);
		} else {
			System.out.println(result.getAllErrors().toString());
			System.out.println("ocorreu um erro");
			logger.debug("Existem Erros:" + result.hasErrors());
			RelacaoFamiliarFamiliar relacaoNew = new RelacaoFamiliarFamiliar();
			relacaoNew.setPaciente(paciente);
			relacaoNew.setFamiliarByIdFamiliar(familiarRel);
			return new ModelAndView("editarSegundoGrauFamiliar", "currentRelacao", relacaoNew);
		}
	}

	@RequestMapping(value = "/listarSegundoGrauFamiliares", method = RequestMethod.GET)
	public ModelAndView listarSegundoGrauFamiliares(HttpServletRequest request) {
		System.out.println("listar Familiar");

		session.removeAttribute("currentRelacao");

		session = request.getSession();
		Paciente paciente = (Paciente) session.getAttribute("currentPaciente");
		Familiar familiar = (Familiar) session.getAttribute("currentFamiliar");

		List<RelacaoFamiliarFamiliar> listRelacaoFamiliarFamiliar = relacaoFamiliarFamiliarService
				.listFromFamily(paciente, familiar);
		paciente.setRelacaoFamiliarFamiliars(listRelacaoFamiliarFamiliar);

		HashMap<String, Familiar> listFamiliar = new HashMap<String, Familiar>();

		for (RelacaoFamiliarFamiliar relacao : listRelacaoFamiliarFamiliar) {
			listFamiliar.put(relacao.getTipoRelacao(), relacao.getFamiliarByIdFamiliar1());
		}

		return new ModelAndView("listarSegundoGrauFamiliares", "listFamiliares", listFamiliar);
	}
}
