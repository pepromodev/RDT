package ecolededev.pe.InitialisationDB;

import java.io.*;
import java.net.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import ecolededev.pe.models.Contact;
import ecolededev.pe.models.Convention;
import ecolededev.pe.models.Partenaire;
import ecolededev.pe.services.ConventionServicesImpl;
import ecolededev.pe.services.PartenaireServicesImpl;

public class InitialisationDB {

	@Autowired PartenaireServicesImpl partenaireServicesImpl;
	@Autowired ConventionServicesImpl conventionServicesImpl;

	public void main(String[] args) throws IOException {
		if (!loadPartenairesConventions ("C:\\Users\\Utilisateur-SJD\\Downloads\\RDT\\RDT_Indicateurs.txt"))  {
			System.err.println("Chargement abandonné : chargement impossible des Partenaire / Conventions");
		} else if (!loadContacts ("C:\\Users\\Utilisateur-SJD\\Downloads\\RDT\\RDT_contact_DGA.txt", true)) {
			System.err.println("Chargement abandonné : chargement impossible des Contacts PE");
		} else if (!loadContacts ("C:\\Users\\Utilisateur-SJD\\Downloads\\RDT\\RDT_contact.txt", false)) {
			System.err.println("Chargement abandonné : chargement impossible des Contacts non PE");
		} else if (!loadContrats ("C:\\Users\\Utilisateur-SJD\\Downloads\\RDT\\RDT_Contrats_signés_avec_le_client.txt")) {
			System.err.println("Chargement abandonné : chargement impossible des Contrats non PE");
		}
		
	} // main

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

//	Charger les contrats
//	Initialiser l'accès à la liste des contrats
//	SI l'accès NE présente PAS de problème
//		Ignorer la 1ère ligne de description des colonnes
//		TANT QUE la lecture de la ligne suivante est possible
	private boolean loadContrats (String completFileName) throws IOException {
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
					String line = donnee[1];
					Contrat contact = new Contact (donnee[2],	// nom
												   donnee[3],	// prenom
												   donnee[4],	// fonction
												   donnee[5],	// direction
												   donnee[6],	// mail
												   donnee[7],	// mobile
												   donnee[8],	// fixe
												   donnee[9],	// commentaire
												   pe);			// pe

//				Associer le nouveau contrat avec la convention identifiée
//				FIN TANT QUE
//			FIN SI
						Convention convention = conventionServicesImpl.findByOldIdConvention(oldIdConvention);
						convention.addContrat(contrat);
						res = true;
					} // for
				} // try
				return res;
			} // loadContrats

} // class InitialisationDB
