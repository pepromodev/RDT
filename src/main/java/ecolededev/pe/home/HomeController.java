package ecolededev.pe.home;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import ecolededev.pe.controleur.selectionprimaire.RdtInitDBForm;
import ecolededev.pe.controleur.selectionprimaire.RdtPrimaireForm;
import ecolededev.pe.models.Partenaire;
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
//			Détermination de la liste des partenaires
//			SI la liste des partenaires est vide (il est nécessaire d'initialiser la base de données)
//				Création des données à echanger
//				Définition du nom utilisé dans thymeleaf
//				Lancer l'initialisation de la base de données RDT
			List<Partenaire> partenaires = partenaireServices.listePartenaire();
			if (partenaires.isEmpty()) {
				RdtInitDBForm rdtInitDBForm = new RdtInitDBForm();
				model.addAttribute("rdtInitDBForm", rdtInitDBForm); 
				return "rtd/initialisation/rdtDB";
			
//			SINON
//				Lancer l'application RDT
//			FIN SI
			} else {
				RdtPrimaireForm rdtPrimaireForm = new RdtPrimaireForm();
				model.addAttribute("rdtPrimaireForm", rdtPrimaireForm); 
				rdtPrimaireForm.setListePartenaires(partenaires);
				return "rtd/rtd";
			}
		} else {
			return "home/homeNotSignedIn";
		} // if
	}
}
