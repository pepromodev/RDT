package ecolededev.pe.controleur.selectionprimaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.spi.TransactionalWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.naming.CannotProceedException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ecolededev.pe.models.Action;
import ecolededev.pe.models.Contact;
import ecolededev.pe.models.ContactRepository;
import ecolededev.pe.models.Convention;
import ecolededev.pe.models.ConventionRepository;
import ecolededev.pe.models.RDTDocument;
import ecolededev.pe.models.Partenaire;
import ecolededev.pe.models.PartenaireRepository;
import ecolededev.pe.models.Rencontre;
import ecolededev.pe.services.IConventionServices;
import ecolededev.pe.services.IPartenaireServices;

@Controller
public class SelectionPrimaireControleur { // Controlleur de selection partenaire / convention
	private String path="C:\\Users\\Utilisateur-SJD\\Downloads\\RDT\\";
	@Autowired
	private IPartenaireServices partenaireServices; // lien avec les services 
	@Autowired
	private IConventionServices conventionServices; // lien avec les services 
	@Autowired
	private PartenaireRepository partenaireRepository; // lien avec la base de données
	@Autowired
	private ConventionRepository conventionRepository; // lien avec la base de données
	@Autowired
	private ContactRepository contactRepository; // lien avec la base de données

//	Initialisation de la base de données
//	------------------------------------
	@GetMapping("/rdtPartenairesConventions")
	String rdtPartsConvs(Principal principal, Model model) throws Exception {
		String file = "RDT_Indicateurs.xml";
		RdtInitDBForm rdtInitDBForm = new RdtInitDBForm();
		try{
		loadPartenairesConventions (null, false, path + file);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		rdtInitDBForm.setFilePartsConvs(path + file);
		rdtInitDBForm.setPartenaires(partenaireServices.listePartenaire().size());
		rdtInitDBForm.setConventions(conventionServices.listeConvention().size());
		model.addAttribute("rdtInitDBForm", rdtInitDBForm);
		return ("rtd/initialisation/rdtPartenairesConventions");
	} // rdtPartenairesConventions

	@GetMapping("/rdtContacts")
	String rdtContacts(Principal principal, Model model, @RequestParam (name = "idPartenaire") String idPartenaire) throws IOException, ParserConfigurationException, SAXException  {
		RdtInitDBForm rdtInitDBForm = new RdtInitDBForm();
		loadContacts (null, false, path + "RDT_Contact_DGA.xml");
		rdtInitDBForm.setFileContsNPE(path + "RDT_Contact_DGA.xml");
		loadContacts (null, false, path + "RDT_Contact.xml");
		rdtInitDBForm.setFileContsNPE(path + "RDT_Contact.xml");
		rdtInitDBForm.setContacts(conventionServices.listeConvention().size());
		model.addAttribute("rdtInitDBForm", rdtInitDBForm);
		return ("rtd/initialisation/rdtContacts");
	} // rdtContacts

	@GetMapping("/rdtContrats")
	String rdtContrats(Principal principal, Model model, @RequestParam (name = "idPartenaire") String idPartenaire) {
		return ("rtd/initialisation/rdtContrats");
	} // rdtContrats

	@GetMapping("/rdtSuivis")
	String rdtSuivis(Principal principal, Model model, @RequestParam (name = "idPartenaire") String idPartenaire) {
		return ("rtd/initialisation/rdtSuivis");
	} // rdtSuivis

	@GetMapping("/rdtActions")
	String rdtActions(Principal principal, Model model, @RequestParam (name = "idPartenaire") String idPartenaire) {
		return ("rtd/initialisation/rdtActions");
	} // rdtActions


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
	} // statusConvention
	
	@GetMapping("/ongListeContacts")	
	String listeContacts(Principal principal, Model model, @RequestParam (name = "idConvention") String idConvention) {
		RdtPrimaireForm rdtPrimaireForm = new RdtPrimaireForm();
		rdtPrimaireForm.setListeContacts(conventionServices.listeContacts(new Long(idConvention)));
		model.addAttribute("rdtPrimaireForm", rdtPrimaireForm);
		return("rtd/ongListeContacts");
	} // listeContacts
	
	@GetMapping("/ongSuiviContacts")	
	String listeRencontres(Principal principal, Model model, @RequestParam (name = "idConvention") String idConvention) {
		RdtPrimaireForm rdtPrimaireForm = new RdtPrimaireForm();
		rdtPrimaireForm.setListeRencontres(conventionServices.listeRencontres(new Long(idConvention)));
		model.addAttribute("rdtPrimaireForm", rdtPrimaireForm);
		return("rtd/ongSuiviContacts");
	} // listeRencontres

//======================================================================================================================
//	Routines d'initialisation de la base de données ====================================================================
//	Charger les partenaires et leurs conventions
//		SI un nom de fichier est donné
//			SI l'accès au fichier de données est possible
//				SI le fichier contient des données
//					Extraire les partenaires et leurs conventions pour la liste des données trouvée ET le mode NON données
//				SINON
//				FIN SI
//			SINON
//			FIN SI
	private void loadPartenairesConventions (NodeList nodeList, boolean datas, String filename) throws Exception { // ParserConfigurationException, SAXException, IOException {
		long oldIdConvention = 0L;
		String nomPartenaire = "";
		String nomConvention = "";
		String presentation = "";
		String interet = "";
		String contraintes = "";
		String contacts = "";
		String action = "";
		String alertes = "";
		String pj = "";
		if ("" != filename) {
			File file = new File(filename);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			if (doc.hasChildNodes()) {
				loadPartenairesConventions(doc.getChildNodes(), false, "");
			} else {
			} // if


//		SINON
//			POUR tous les données de la liste des données trouvée
//				SI la donnée courante est élément XML
//					SI le mode données
//						SELON le nom de la donnée
//							extraire la donnée
//						FIN SELON
		} else {
			for (int current = 0; current < nodeList.getLength(); current++) {
				Node currentNode = nodeList.item(current);
				// make sure it's element node.
				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					if (datas) {
						switch (currentNode.getNodeName()) {
						case "Idind" :	oldIdConvention = Long.parseLong(currentNode.getTextContent()); break;
						case "Société" :	 nomPartenaire = currentNode.getTextContent(); 
						nomConvention = nomPartenaire;
						if (nomPartenaire.contains(" ")) {
							nomConvention = nomPartenaire.substring(nomPartenaire.indexOf(' ') + 1);
							nomPartenaire = nomPartenaire.split(" ")[0];
						} // if
						break;
						case "Présentation_du_client" :		presentation = currentNode.getTextContent(); break;
						case "Intérêt_à_suivre_le_client" :	interet = currentNode.getTextContent(); break;
						case "Contraintes_du_client" :		contraintes = currentNode.getTextContent(); break;
						case "Contacts_privilégiés" :		contacts = currentNode.getTextContent(); break;
						case "Principale_action" :			action = currentNode.getTextContent(); break;
						case "Alertes" :					alertes = currentNode.getTextContent(); break;
						case "Pj" :							pj = currentNode.getTextContent(); break;
						default :							break;
						} // switch

//					SINON
//						SI la donnée courante est parent d'une liste de données
//							Extraire les partenaires et leurs conventions pour la liste des données trouvée ET 
//									le mode données SI le nom de la donnée courante est "Indicateurs"
//						FIN SI
//					FIN SI
					} else {
						// get node name and value
						if (currentNode.hasChildNodes()) {
							// loop again if has child nodes
							loadPartenairesConventions(currentNode.getChildNodes(), "Indicateurs" == currentNode.getNodeName(), "");
						} // if
					} // if
				} // if
			} // for

//			SI le mode données
//				SI le partenaire N'existe PAS
//					Créer un nouveau partenaire et l'identifier
//				SINON
//					Identifier le partenaire
//				FIN SI
//				Créer une nouvelle convention
//				Associer la nouvelle convention avec le partenaire identifié
//			FIN SI
			if (datas) {
				List<Partenaire> liste = partenaireServices.findByPartenaire(nomPartenaire);
				Partenaire partenaire;
				if (liste.isEmpty()) {
					partenaire = new Partenaire(nomPartenaire, presentation);
				} else {
					partenaire = liste.get(0);
				} // if
				Convention convention = new Convention (nomConvention, interet, alertes, oldIdConvention);
				convention = conventionRepository.saveAndFlush(convention);
//			if (partenaire.getListeConventions() == null) { partenaire.setListeConventions(new ArrayList<>()); } 
//				partenaire.getListeConventions().add(convention);
				partenaire.addConvention(convention);
				partenaire = partenaireRepository.saveAndFlush(partenaire);
					System.out.println("Ajout de : " + nomPartenaire + " ==> " + nomConvention);
			} // if					
		} // if
		if (true){};
	} // extractPartenairesConventions

//-------------------------------------------------------------------------------------------------------------------
//Charger les contacts PE Ou non PE
//	Charger les partenaires et leurs conventions
//	SI un nom de fichier est donné
//		SI l'accès au fichier de données est possible
//			SI le fichier contient des données
//				Extraire les partenaires et leurs conventions pour la liste des données trouvée ET le mode NON données
//			SINON
//			FIN SI
//		SINON
//		FIN SI
private void loadContacts (NodeList nodeList, boolean datas, String filename) throws ParserConfigurationException, SAXException, IOException {
	long oldIdConvention = 0L;
	long ligne = 0L;
	String nom = "";
	String prenom = "";
	String fonction = "";
	String direction = "";
	String mail = "";
	String mobile = "";
	String fixe = "";
	String commentaire = "";
	boolean pe = true;
	if ("" != filename) {
		File file = new File(filename);
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		if (doc.hasChildNodes()) {
			loadContacts(doc.getChildNodes(), false, "");
		} else {
		} // if


//	SINON
//		POUR tous les données de la liste des données trouvée
//			SI la donnée courante est élément XML
//				SI le mode données
//					SELON le nom de la donnée
//						extraire la donnée
//					FIN SELON
	} else {
		for (int current = 0; current < nodeList.getLength(); current++) {
			Node currentNode = nodeList.item(current);
			// make sure it's element node.
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				if (datas) {
					switch (currentNode.getNodeName()) {
					case "Clef_contact_DGA" :	oldIdConvention = Long.parseLong(currentNode.getTextContent()); 
												pe = true;
												break;
					case "Clef_contact" 	:	oldIdConvention = Long.parseLong(currentNode.getTextContent()); 
												pe = false;
												break;
					case "Ligne" :				ligne = Long.parseLong(currentNode.getTextContent()); break;
					case "Nom" :				nom = currentNode.getTextContent(); break;
					case "Prénom" :				prenom = currentNode.getTextContent(); break;
					case "Fonction" :			fonction = currentNode.getTextContent(); break;
					case "Direction" :			direction = currentNode.getTextContent(); break;
					case "Mail" :				mail = currentNode.getTextContent(); break;
					case "Tel_x0020_Mobile" :	mobile = currentNode.getTextContent(); break;
					case "Tel_x0020_fixe" :		fixe = currentNode.getTextContent(); break;
					case "Problème" :			commentaire = currentNode.getTextContent(); break;
					default :					break;
					} // switch

//				SINON
//					SI la donnée courante est parent d'une liste de données
//						Extraire les partenaires et leurs conventions pour la liste des données trouvée ET 
//								le mode données SI le nom de la donnée courante est "Indicateurs"
//					FIN SI
//				FIN SI
				} else {
					// get node name and value
					if (currentNode.hasChildNodes()) {
						// loop again if has child nodes
						loadContacts(currentNode.getChildNodes(), "Indicateurs" == currentNode.getNodeName(), "");
					} // if
				} // if
			} // if
		} // for

//		SI le mode données
//			Créer un nouveau contact et l'identifier
//			Identifier la convention à associer au nouveau contact
//			Associer le nouveau contact avec la convention identifiée
//		FIN SI
		if (datas) {
			Contact contact = new Contact(nom, prenom, fonction, direction, mail, mobile, fixe, commentaire, pe);
			contact = contactRepository.saveAndFlush(contact);
			Convention convention = conventionServices.findByOldIdConvention(oldIdConvention);
			convention = conventionRepository.saveAndFlush(convention);
			convention.addContact(contact);
		} // if					
	} // if
} // extractPartenairesConventions
//-------------------------------------------------------------------------------------------------------------------
//Charger les contrats

//-------------------------------------------------------------------------------------------------------------------
//Charger les rencontres

//-------------------------------------------------------------------------------------------------------------------
//Charger le suivi des actions
	
} // class SelectionPrimaireControleur
