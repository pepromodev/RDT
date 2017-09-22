package ecolededev.pe.controleur.selectionprimaire;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ecolededev.pe.models.Convention;
import ecolededev.pe.services.IConventionServices;
import ecolededev.pe.services.IPartenaireServices;

@Controller
public class SelectionPrimaireControleur { // Controlleur de selection partenaire / convention

	@Autowired
	private IPartenaireServices partenaireServices; // lien avec les services 
	@Autowired
	private IConventionServices conventionServices; // lien avec les services 
	
	@GetMapping("/objetPartenaire")
	String objetPartenaire(Principal principal, Model model, @RequestParam (name = "idPartenaire") String idPartenaire) {
		RdtPrimaireForm rdtPrimaireForm = new RdtPrimaireForm();
		rdtPrimaireForm.setObjetPartenaire(partenaireServices.objetPartenaire(new Long(idPartenaire)));
		model.addAttribute("rdtPrimaireForm", rdtPrimaireForm);
		return("rtd/descriptionPartenaire");
	} // objetPartenaire

	@GetMapping("/selectionConventions")
	String selectionConvention(Principal principal, Model model, @RequestParam (name = "idPartenaire") String idPartenaire) {
		List<Convention> conventions = partenaireServices.listeConventions(new Long(idPartenaire));
		RdtPrimaireForm rdtPrimaireForm = new RdtPrimaireForm();
		rdtPrimaireForm.setListeConventions(conventions);
		model.addAttribute("rdtPrimaireForm", rdtPrimaireForm);
		return("rtd/selectionConventions");
	} // selectionConvention


	@GetMapping("/objetConvention")
	String objetConvention(Principal principal, Model model, @RequestParam (name = "idConvention") String idConvention) {
		RdtPrimaireForm rdtPrimaireForm = new RdtPrimaireForm();
		rdtPrimaireForm.setObjetConvention(conventionServices.objetConvention(new Long(idConvention)));
		model.addAttribute("rdtPrimaireForm", rdtPrimaireForm);
		return("rtd/objetConvention");
	} // objetConvention
	
	@GetMapping("/statusConvention")	
	String statusConvention(Principal principal, Model model, @RequestParam (name = "idConvention") String idConvention) {
		RdtPrimaireForm rdtPrimaireForm = new RdtPrimaireForm();
		rdtPrimaireForm.setObjetConvention(conventionServices.objetConvention(new Long(idConvention)));
		model.addAttribute("rdtPrimaireForm", rdtPrimaireForm);
		return("rtd/statusConvention");
	} // objetConvention
	
	@GetMapping("/ongListeContacts")	
	String listeContacts(Principal principal, Model model, @RequestParam (name = "idConvention") String idConvention) {
		RdtPrimaireForm rdtPrimaireForm = new RdtPrimaireForm();
		rdtPrimaireForm.setListeContacts(conventionServices.listeContacts(new Long(idConvention)));
		model.addAttribute("rdtPrimaireForm", rdtPrimaireForm);
		return("rtd/ongListeContacts");
	} // objetConvention
	
} // class SelectionPrimaireControleur
