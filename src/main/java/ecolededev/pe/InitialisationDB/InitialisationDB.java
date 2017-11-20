package ecolededev.pe.InitialisationDB;

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import ecolededev.pe.models.Action;
import ecolededev.pe.models.Contact;
import ecolededev.pe.models.Convention;
import ecolededev.pe.models.RDTDocument;
import ecolededev.pe.models.Partenaire;
import ecolededev.pe.models.Rencontre;
import ecolededev.pe.services.ConventionServicesImpl;
import ecolededev.pe.services.PartenaireServicesImpl;

public class InitialisationDB {

	@Autowired PartenaireServicesImpl partenaireServicesImpl;
	@Autowired ConventionServicesImpl conventionServicesImpl;

	public void main(String[] args) throws IOException, ParseException {
		String path = "C:\\Users\\Utilisateur-SJD\\Downloads\\RDT\\";
		
//		Chargement des partenaires et conventions
		if (!loadPartenairesConventions (path + "RDT_Indicateurs.txt"))  {
			System.err.println("Chargement abandonné : chargement impossible des Partenaire / Conventions");
			
//		Chargement des contacts
		} else if (!loadContacts (path + "RDT_contact_DGA.txt", true)) {
			System.err.println("Chargement abandonné : chargement impossible des Contacts PE");
		} else if (!loadContacts (path + "RDT_contact.txt", false)) {
			System.err.println("Chargement abandonné : chargement impossible des Contacts non PE");
			
//		Chargement des contrats
		} else if (!loadContrats (path + "RDT_Contrats_signés_avec_le_client.txt")) {
			System.err.println("Chargement abandonné : chargement impossible des Contrats");

//		Chargement des rencontres
		} else if (!loadRencontres (path + "RDT_Suivi.txt")) {
			System.err.println("Chargement abandonné : chargement impossible des Rencontres");

//		Chargement du suivi des actions
		} else if (!loadSuiviActions (path + "RDT_Suivi_actions.txt")) {
			System.err.println("Chargement abandonné : chargement impossible du suivi des actions");
		} // if
		
	} // main

//	-------------------------------------------------------------------------------------------------------------------
//	Charger les partenaires et conventions
//		Initialiser l'accès à la liste des partenaires et conventions
//		SI l'accès NE présente PAS de problème
//			Ignorer la 1ère ligne de description des colonnes
//			TANT QUE la lecture de la ligne suivante est possible
	private boolean loadPartenairesConventions (String completFileName) throws IOException {
		boolean res = false;
		URL url= new URL(completFileName);
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
			bufferedReader.readLine(); // skip header
			int nbrLignes = 0;
			for (String ligne = bufferedReader.readLine(); ligne != null; ligne= bufferedReader.readLine()) {

//				Extraire de la ligne courante les informations du partenaire et de la convention
				nbrLignes++;
				String[] donnee = ligne.split(";");
				long oldIdConvention = Long.parseLong(donnee[0]);
				String societe = donnee[8];
				String nomPartenaire = societe;
				String nomConvention = societe;
				if (societe.contains(" ")) {
					nomPartenaire = societe.split(" ")[0];
					nomConvention = societe.substring(nomPartenaire.length() + 1);
				} // if
				String presentation = donnee[9];
				String x = donnee[10];
				String interet = donnee[11];
				String contraintes = donnee[12];
				String contacts = donnee[13];
				String action = donnee[14];
				String alertes = donnee[15];
				String pj = donnee[16];

//				SI le partenaire N'existe PAS
//					Créer un nouveau partenaire et l'identifier
//				SINON
//					Identifier le partenaire
//				FIN SI
				List<Partenaire> liste = partenaireServicesImpl.findByPartenaire(nomPartenaire);
				Partenaire partenaire;
				if (liste.isEmpty()) {
					partenaire = new Partenaire(nomPartenaire, presentation);
				} else {
					partenaire = liste.get(0);
				} // if

//				Créer une nouvelle convention
//				Associer la nouvelle convention avec le partenaire identifié
//			FIN TANT QUE
//		FIN SI
				Convention convention = new Convention (nomConvention, interet, alertes);
				partenaire.addConvention(convention);
				res = true;
			} // for
		} // try
		return res;
	} // loadPartenairesConventions


//	-------------------------------------------------------------------------------------------------------------------
//	Charger les contacts PE Ou non PE
//	Initialiser l'accès à la liste des contacts
//	SI l'accès NE présente PAS de problème
//		Ignorer la 1ère ligne de description des colonnes
//		TANT QUE la lecture de la ligne suivante est possible
	private boolean loadContacts (String completFileName, boolean pe) throws IOException {
		boolean res = false;
		URL url= new URL(completFileName);
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
			bufferedReader.readLine(); // skip header
			int nbrLignes = 0;
			for (String ligne = bufferedReader.readLine(); ligne != null; ligne= bufferedReader.readLine()) {

//			Créer un nouveau contact
//			Extraire de la ligne courante les informations du contact
				nbrLignes++;
				String[] donnee = ligne.split(";");
				long oldIdConvention = Long.parseLong(donnee[0]); // identification convention
				String line = donnee[1];
				Contact contact = new Contact (donnee[2],	// nom
											   donnee[3],	// prenom
											   donnee[4],	// fonction
											   donnee[5],	// direction
											   donnee[6],	// mail
											   donnee[7],	// mobile
											   donnee[8],	// fixe
											   donnee[9],	// commentaire
											   pe);			// pe

//			Associer le nouveau contact avec la convention identifiée
//		FIN TANT QUE
//	FIN SI
				Convention convention = conventionServicesImpl.findByOldIdConvention(oldIdConvention);
				convention.addContact(contact);
				res = true;
			} // for
		} // try
		return res;
	} // loadContacts

//	-------------------------------------------------------------------------------------------------------------------
//	Charger les contrats
//	Initialiser l'accès à la liste des contrats
//	SI l'accès NE présente PAS de problème
//		Ignorer la 1ère ligne de description des colonnes
//		TANT QUE la lecture de la ligne suivante est possible
	private boolean loadContrats (String completFileName) throws IOException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		boolean res = false;
		URL url= new URL(completFileName);
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
			bufferedReader.readLine(); // skip header
			int nbrLignes = 0;
			for (String ligne = bufferedReader.readLine(); ligne != null; ligne= bufferedReader.readLine()) {

//				Créer un nouveau contrat
//				Extraire de la ligne courante les informations du contact
					nbrLignes++;
					String[] donnee = ligne.split(";");
					long oldIdConvention = Long.parseLong(donnee[0]); // identification convention
					long oldLigne = Long.parseLong(donnee[1]); // identification ligne
					RDTDocument rDTDocument = new RDTDocument ("?", 							// description
													  donnee[2],					// type
													  formatter.parse(donnee[3]),	// date conclusion
													  formatter.parse(donnee[4]),	// date echeance
													  donnee[5]);					// colibri

//				Associer le nouveau contrat avec la convention identifiée
//				FIN TANT QUE
//			FIN SI
						Convention convention = conventionServicesImpl.findByOldIdConvention(oldIdConvention);
						convention.addDocument(rDTDocument);
						res = true;
					} // for
				} // try
				return res;
			} // loadContrats

//	-------------------------------------------------------------------------------------------------------------------
//	Charger les rencontres
//	Initialiser l'accès à la liste des contrats
//	SI l'accès NE présente PAS de problème
//		Ignorer la 1ère ligne de description des colonnes
//		TANT QUE la lecture de la ligne suivante est possible
	private boolean loadRencontres (String completFileName) throws IOException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		boolean res = false;
		URL url= new URL(completFileName);
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
			bufferedReader.readLine(); // skip header
			int nbrLignes = 0;
			for (String ligne = bufferedReader.readLine(); ligne != null; ligne= bufferedReader.readLine()) {

//				Créer une nouvelle rencontre
//				Extraire de la ligne courante les informations du contact
					nbrLignes++;
					String[] donnee = ligne.split(";");
					long oldIdConvention = Long.parseLong(donnee[0]); // identification convention
					long oldLigne = Long.parseLong(donnee[1]); // identification ligne
					Rencontre rencontre = new Rencontre (formatter.parse(donnee[2]), 	// date de la rencontre
													  	donnee[3],						// nature de la rencontre
													  	donnee[4],						// participants non PE
													  	donnee[5],						// participants PE
													  	donnee[6],						// ordre du jour
													  	donnee[7],						// support du compte rendu
													  	donnee[8],						// prochaine rencontre
													  	donnee[9],						// commentaires
													  	formatter.parse(donnee[10]), 	// date de début
													  	formatter.parse(donnee[11]));	// date de fin

//				Associer le nouveau contrat avec la convention identifiée
//				FIN TANT QUE
//			FIN SI
						Convention convention = conventionServicesImpl.findByOldIdConvention(oldIdConvention);
						convention.addRencontre(rencontre);
						res = true;
					} // for
				} // try
				return res;
			} // loadRencontres

//	-------------------------------------------------------------------------------------------------------------------
//	Charger le suivi des actions
//	Initialiser l'accès au suivi des actions
//	SI l'accès NE présente PAS de problème
//		Ignorer la 1ère ligne de description des colonnes
//		TANT QUE la lecture de la ligne suivante est possible
	private boolean loadSuiviActions (String completFileName) throws IOException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		boolean res = false;
		URL url= new URL(completFileName);
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
			bufferedReader.readLine(); // skip header
			int nbrLignes = 0;
			for (String ligne = bufferedReader.readLine(); ligne != null; ligne= bufferedReader.readLine()) {

//				Créer une nouvelle rencontre
//				Extraire de la ligne courante les informations du contact
					nbrLignes++;
					String[] donnee = ligne.split(";");
					long oldIdConvention = Long.parseLong(donnee[0]); // identification convention
					long oldLigne = Long.parseLong(donnee[1]); // identification ligne
//					public Action(String description, String nature, Date dateAction, String proposee, String responsable,
//							Date echeanceInit, Date echeanceActu, String avancement, String suivi, String verification, String etat,
//							Date dateClot, String dateChoi) {

					Action action = new Action (donnee[2],						// origine
												donnee[3],						// description du phénomène
												formatter.parse(donnee[10]), 	// date de prise en compte
												donnee[4], 						// action proposée
												donnee[5],						// Responsable
												formatter.parse(donnee[11]), 	// date d'échéance initiale
												formatter.parse(donnee[12]), 	// date d'échéance actualisée
												donnee[7],						// Avancement
												donnee[8],						// Suivi action
												donnee[14], 					// vérification de l'efficacité
												donnee[6],						// Etat
												formatter.parse(donnee[9]), 	// date de cloture
												donnee[15] + "/" + donnee[16]);	// année et mois choisi

//				Associer le nouveau contrat avec la convention identifiée
//				FIN TANT QUE
//			FIN SI
						Convention convention = conventionServicesImpl.findByOldIdConvention(oldIdConvention);
						convention.addAction(action);
						res = true;
					} // for
				} // try
				return res;
			} // loadSuiviActions

} // class InitialisationDB
