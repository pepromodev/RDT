package ecolededev.pe.models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Services")
public class Service {
	@Id
	@GeneratedValue
	@Column(name="idService")
	private Long idService;
	@Column(name="nom")
	private String nom;
	@Column(name="sensEchan")
	private String sensEchan;
	@Column(name="capacite")
	private String capacite;
	@Column(name="support")
	private String support;
	@Column(name="typeDispo")
	private String typeDispo;
	@Column(name="heureDeb")
	private Time heureDeb;
	@Column(name="heureFin")
	private Time heureFin;
	@OneToMany
	private List<Support> listeSupports = new ArrayList<Support>();

//	Constructeur	
	public Service() {}
	public Service(String nom, String sensEchan, String capacite, String support, String typeDispo, Time heureDeb,
			Time heureFin) {
		this.nom = nom;
		this.sensEchan = sensEchan;
		this.capacite = capacite;
		this.support = support;
		this.typeDispo = typeDispo;
		this.heureDeb = heureDeb;
		this.heureFin = heureFin;
	} // Service

//	Setteurs (pas de setIdContact : Id géré par JPA)
	public void setIdService(Long idService)	{ this.idService = idService; }
	public void setNom(String nom)								{ this.nom = nom; }
	public void setSensEchan(String sensEchan)					{ this.sensEchan = sensEchan; }
	public void setCapacite(String capacite)					{ this.capacite = capacite; }
	public void setSupport(String support)						{ this.support = support; }
	public void setTypeDispo(String typeDispo)					{ this.typeDispo = typeDispo; }
	public void setHeureDeb(Time heureDeb)						{ this.heureDeb = heureDeb; }
	public void setHeureFin(Time heureFin)						{ this.heureFin = heureFin; }
	public void setListeSupports(List<Support> listeSupports)	{ this.listeSupports = listeSupports; }

//	Getteurs
	public Long getIdService()				{ return idService; }
	public String getNom()					{ return nom; }
	public String getSensEchan()			{ return sensEchan; }
	public String getCapacite()				{ return capacite; }
	public String getSupport()				{ return support; }
	public String getTypeDispo()			{ return typeDispo; }
	public Time getHeureDeb()				{ return heureDeb; }
	public Time getHeureFin()				{ return heureFin; }
	public List<Support> getListeSupports()	{ return listeSupports; }

//	Autres
	@Override
	public String toString() {
		return "Service [idService=" + idService + 
				", nom=" + nom + 
				", sensEchan=" + sensEchan + 
				", capacite=" + capacite + 
				", support=" + support + 
				", typeDispo=" + typeDispo + 
				", heureDeb=" + heureDeb + 
				", heureFin=" + heureFin + "]";
	} // toString

} // class Service
