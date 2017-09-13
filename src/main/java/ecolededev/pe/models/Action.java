package ecolededev.pe.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Actions")
public class Action {
	@Id
	@GeneratedValue
	@Column(name="idAction")
	private Long idAction;
	@Column(name="description")
	private String description;
	@Column(name="nature")
	private String nature;
	@Column(name="dateAction")
	private Date dateAction;
	@Column(name="proposee")
	private String proposee;
	@Column(name="responsable")
	private String responsable;
	@Column(name="echeanceInit")
	private Date echeanceInit;
	@Column(name="echeanceActu")
	private Date echeanceActu;
	@Column(name="avancement")
	private String avancement;
	@Column(name="suivi")
	private String suivi;
	@Column(name="verification")
	private String verification;
	@Column(name="etat")
	private String etat;
	@Column(name="dateClot")
	private Date dateClot;
	@Column(name="dateChoi")
	private String dateChoi;

//	Constructeur	
	public Action() {}
	public Action(String description, String nature, Date dateAction, String proposee, String responsable,
			Date echeanceInit, Date echeanceActu, String avancement, String suivi, String verification, String etat,
			Date dateClot, String dateChoi) {
		this.description = description;
		this.nature = nature;
		this.dateAction = dateAction;
		this.proposee = proposee;
		this.responsable = responsable;
		this.echeanceInit = echeanceInit;
		this.echeanceActu = echeanceActu;
		this.avancement = avancement;
		this.suivi = suivi;
		this.verification = verification;
		this.etat = etat;
		this.dateClot = dateClot;
		this.dateChoi = dateChoi;
	} // Action

	//	Setteurs
	public void setIdAction(Long idAction)				{ this.idAction = idAction; }
	public void setDescription(String description)		{ this.description = description; }
	public void setNature(String nature)				{ this.nature = nature; }
	public void setDateAction(Date dateAction)			{ this.dateAction = dateAction; }
	public void setProposee(String proposee)			{ this.proposee = proposee; }
	public void setResponsable(String responsable)		{ this.responsable = responsable; }
	public void setEcheanceActu(Date echeanceActu)		{ this.echeanceActu = echeanceActu; }
	public void setEcheanceInit(Date echeanceInit)		{ this.echeanceInit = echeanceInit; }
	public void setAvancement(String avancement)		{ this.avancement = avancement; }
	public void setSuivi(String suivi)					{ this.suivi = suivi; }
	public void setVerification(String verification)	{ this.verification = verification; }
	public void setEtat(String etat)					{ this.etat = etat; }
	public void setDateClot(Date dateClot)				{ this.dateClot = dateClot; }
	public void setDateChoi(String dateChoi)			{ this.dateChoi = dateChoi; }

//	Getteurs
	public Long getIdAction()		{ return idAction; }
	public String getDescription()	{ return description; }
	public String getNature()		{ return nature; }
	public Date getDateAction()		{ return dateAction; }
	public String getProposee()		{ return proposee; }
	public String getResponsable()	{ return responsable; }
	public Date getEcheanceInit()	{ return echeanceInit; }
	public Date getEcheanceActu()	{ return echeanceActu; }
	public String getAvancement()	{ return avancement; }
	public String getSuivi()		{ return suivi; }
	public String getVerification()	{ return verification; }
	public String getEtat()			{ return etat; }
	public Date getDateClot()		{ return dateClot; }
	public String getDateChoi()		{ return dateChoi; }

//	Autres
	@Override
	public String toString() {
		return "Action [idAction=" + idAction + 
				", description=" + description + 
				", nature=" + nature + 
				", dateAction=" + dateAction + 
				", proposee=" + proposee + 
				", responsable=" + responsable + 
				", echeanceInit=" + echeanceInit + 
				", echeanceActu=" + echeanceActu + 
				", avancement=" + avancement + 
				", suivi=" + suivi + 
				", verification=" + verification + 
				", etat=" + etat + 
				", dateClot=" + dateClot + 
				", dateChoi=" + dateChoi + "]";
	} // toString

} // class Action
