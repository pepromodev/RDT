package ecolededev.pe.home;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import ecolededev.pe.controleur.selectionprimaire.RdtPrimaireForm;
import ecolededev.pe.services.IPartenaireServices;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {

	@Autowired
	private IPartenaireServices partenaireServices; // lien avec les services 

	@ModelAttribute("module")
	String module() {
		return "home";
	}

	@GetMapping("/")
	String index(Principal principal, Model model) {

		if (principal != null) {
			RdtPrimaireForm rdtPrimaireForm = new RdtPrimaireForm(); // création des données à echanger
			model.addAttribute("rdtPrimaireForm", rdtPrimaireForm); // nom utilisé dans thymeleaf
			rdtPrimaireForm.setListePartenaires(partenaireServices.listePartenaire()); // chargement des données depuis la base
			
			return "rtd/rtd";
		} else {
			return "home/homeNotSignedIn";
		} // if
	}
}
