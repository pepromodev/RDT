package ecolededev.pe.controleur.selectionprimaire;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ecolededev.pe.models.Convention;
import ecolededev.pe.services.IPartenaireServices;

@Controller
public class SelectionPrimaireControleur { // Controlleur de selection partenaire / convention

	@Autowired
	private IPartenaireServices partenaireServices; // lien avec les services 
	
	@GetMapping("/selectionConventions")
	String selectionConvention(Principal principal, Model model, @RequestParam (name = "idPartenaire") String idPartenaire) {
		
		List<Convention> conventions = partenaireServices.listeConventions(new Long(idPartenaire));
		RdtPrimaireForm rdtPrimaireForm = new RdtPrimaireForm();
		rdtPrimaireForm.setListeConventions(conventions);
		model.addAttribute("rdtPrimaireForm", rdtPrimaireForm);
		
		return("rtd/selectionConventions");
	}

	@GetMapping("/objetPartenaire")
	String objetPartenaire(Principal principal, Model model, @RequestParam (name = "idPartenaire") String idPartenaire) {
		
		RdtPrimaireForm rdtPrimaireForm = new RdtPrimaireForm();
		rdtPrimaireForm.setObjetPartenaire(partenaireServices.objetPartenaire(new Long(idPartenaire)));
		model.addAttribute("rdtPrimaireForm", rdtPrimaireForm);
		
		return("rtd/objetPartenaire");
	}

	
} // class SelectionPrimaireControleur
