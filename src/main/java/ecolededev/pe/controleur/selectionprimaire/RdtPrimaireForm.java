package ecolededev.pe.controleur.selectionprimaire;

import java.util.ArrayList;
import java.util.List;

import ecolededev.pe.models.Contact;
import ecolededev.pe.models.Convention;
import ecolededev.pe.models.Partenaire;
import ecolededev.pe.models.Rencontre;

public class RdtPrimaireForm {
	private Long idPartenaire;
	private Partenaire objetPartenaire = new Partenaire();
	private List<Partenaire> listePartenaires = new ArrayList<>();
	private List<Convention> listeConventions = new ArrayList<>();
	private Long idConvention;
	private Convention objetConvention = new Convention();
	private List<Contact> listeContacts = new ArrayList<>();
	private List<Rencontre> listeRencontres = new ArrayList<>();
	
//	Setteurs
	public void setIdPartenaire(Long idPartenaire)						{ this.idPartenaire = idPartenaire; }
	public void setObjetPartenaire(Partenaire objetPartenaire)			{ this.objetPartenaire = objetPartenaire; }
	public void setListePartenaires(List<Partenaire> listePartenaires)	{ this.listePartenaires = listePartenaires; }
	public void setListeConventions(List<Convention> listeConventions)	{ this.listeConventions = listeConventions; }
	public void setIdConvention(Long idConvention)						{ this.idConvention = idConvention; }
	public void setObjetConvention(Convention objetConvention)			{ this.objetConvention = objetConvention; }
	public void setListeContacts(List<Contact> listeContacts)			{ this.listeContacts = listeContacts; }
	public void setListeRencontres(List<Rencontre> listeRencontres)		{ this.listeRencontres = listeRencontres; }

//	Getteurs
	public Long getIdPartenaire()					{ return idPartenaire; }
	public Partenaire getObjetPartenaire()			{ return objetPartenaire; }
	public List<Partenaire> getListePartenaires()	{ return listePartenaires; }
	public List<Convention> getListeConventions()	{ return listeConventions; }
	public Long getIdConvention()					{ return idConvention; }
	public Convention getObjetConvention()			{ return objetConvention; }
	public List<Contact> getListeContacts()			{ return listeContacts; }
	public List<Rencontre> getListeRencontre()		{ return listeRencontres; }
	
	
	
	
	


} // class rdtFormPrimaire
