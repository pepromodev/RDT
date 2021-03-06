package ecolededev.pe.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Conventions")
public class Convention {
	@Id
	@GeneratedValue
	@Column(name="idConvention")
	private Long idConvention;
	@Column(name="convention")
	private String convention;
	@Column(name="objet")
	private String objet;
	@Column(name="status")
	private String status;
	@ManyToMany
	private List<Contact> listeContacts = new ArrayList<Contact>();
	@OneToMany 
	private List<RDTDocument> listeDocuments = new ArrayList<RDTDocument>();
	@OneToMany
	private List<Rencontre> listeRencontres = new ArrayList<Rencontre>();
	@OneToMany 
	private List<Action> listeActions = new ArrayList<Action>();
	@OneToMany 
	private List<Service> listeServices = new ArrayList<Service>();
	@Column(name="oldIdConvention")
	private long oldIdConvention;

//	Constructeur	
	public Convention()	{}
	public Convention(String convention, String objet, String status)	{
		this.convention = convention;
		this.objet = objet;
		this.status = status;
	} // Convention
	public Convention(String convention, String objet, String status, long oldIdConvention)	{
		this.convention = convention;
		this.objet = objet;
		this.status = status;
		this.oldIdConvention = oldIdConvention;
	} // Convention

//	Setteurs
	public void setIdConvention(Long idConvention)					{ this.idConvention = idConvention; }
	public void setConvention(String convention)					{ this.convention = convention; }
	public void setObjet(String objet)								{ this.objet = objet; }
	public void setStatus(String status)							{ this.status = status; }
	public void setListeContacts(List<Contact>	listeContacts)		{ this.listeContacts = listeContacts; }
	public void setListeDocuments(List<RDTDocument> listeDocuments)	{ this.listeDocuments = listeDocuments; }
	public void setListeRencontres(List<Rencontre> listeRencontres)	{ this.listeRencontres = listeRencontres; }
	public void setListeActions(List<Action> listeActions)			{ this.listeActions = listeActions; }
	public void setListeServices(List<Service> listeServices)		{ this.listeServices = listeServices; }
	public void setOldIdConvention(long oldIdConvention)			{ this.oldIdConvention = oldIdConvention; }

//	Getteurs
	public Long getIdConvention()				{ return idConvention; }
	public String getConvention()				{ return convention; }
	public String getObjet()					{ return objet; }
	public String getStatus()					{ return status; }
	public List<Contact> getListeContacts()		{ return listeContacts; }
	public List<RDTDocument> getListeDocuments()	{ return listeDocuments; }
	public List<Rencontre> getListeRencontres()	{ return listeRencontres; }
	public List<Action> getListeActions()		{ return listeActions; }
	public List<Service> getListeServices()		{ return listeServices; }
	public long getOldIdConvention()			{ return oldIdConvention; }

//	Autres
	@Override
	public String toString() {
		return "Convention [idConvention=" + idConvention + 
				", convention=" + convention + 
				", objet=" + objet + 
				", status=" + status + "]";
	} // toString

	public void addContact (Contact contact)	{ this.listeContacts.add(contact); }
	public void addDocument (RDTDocument rDTDocument)	{ this.listeDocuments.add(rDTDocument); }
	public void addRencontre (Rencontre rencontre)	{ this.listeRencontres.add(rencontre); }
	public void addAction (Action action)	{ this.listeActions.add(action); }
	
	
} // class Convention
