package ecolededev.pe.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Rencontres")
public class Rencontre {
	@Id
	@GeneratedValue
	@Column(name="idRencontre")
	private Long idRencontre;
	@Column(name="dateRenc")
	private Date dateRenc;
	@Column(name="nature")
	private String nature;
	@Column(name="participants")
	private String participants;
	@Column(name="participantsPE")
	private String participantsPE;
	@Column(name="ordreDuJour")
	private String ordreDuJour;
	@Column(name="natureCR")
	private String natureCR;
	@Column(name="prochaine")
	private String prochaine;
	@Column(name="commentaire")
	private String commentaire;
	@Column(name="dateDeb")
	private Date dateDeb;
	@Column(name="dateFin")
	private Date dateFin;

//	Constantes
	private String[] natures = {"Enquêtes de satisfaction",		"Instances Contractuelles",
	                            "Rencontre client",				"réception courrier",
	                            "diffusion courrier",			"Sollicitation Direction DSI",
	                            "Atelier Contrat de service",	"Demande d'informations"};
	
//	Constructeur	
	public Rencontre() {}
	public Rencontre(Date date, String nature, String participants, String participantsPE,
			String ordreDuJour, String natureCR, String prochaine, String commentaire, Date dateDeb, Date dateFin) {
		this.dateRenc = date;
		this.nature = nature;
		this.participants = participants;
		this.participantsPE = participantsPE;
		this.ordreDuJour = ordreDuJour;
		this.natureCR = natureCR;
		this.prochaine = prochaine;
		this.commentaire = commentaire;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
	} // Rencontre

	//	Setteurs
	public void setIdRencontre(Long idRencontre)			{ this.idRencontre = idRencontre; }
	public void setDateRenc(Date date)						{ this.dateRenc = date; }
	public void setNature(String nature)					{ this.nature = nature; }
	public void setParticipants(String participants)		{ this.participants = participants; }
	public void setParticipantsPE(String participantsPE)	{ this.participantsPE = participantsPE; }
	public void setOrdreDuJour(String ordreDuJour)			{ this.ordreDuJour = ordreDuJour; }
	public void setNatureCR(String natureCR)				{ this.natureCR = natureCR; }
	public void setProchaine(String prochaine)				{ this.prochaine = prochaine; }
	public void setCommentaire(String commentaire)			{ this.commentaire = commentaire; }
	public void setDateDeb(Date dateDeb)					{ this.dateDeb = dateDeb; }
	public void setDateFin(Date dateFin)					{ this.dateFin = dateFin; }
	
//	Getteurs
	public Long getIdRencontre()		{ return idRencontre; }
	public Date getDateRenc()			{ return dateRenc; }
	public String getNature()			{ return nature; }
	public String getParticipants()		{ return participants; }
	public String getParticipantsPE()	{ return participantsPE; }
	public String getOrdreDuJour()		{ return ordreDuJour; }
	public String getNatureCR()			{ return natureCR; }
	public String getProchaine()		{ return prochaine; }
	public String getCommentaire()		{ return commentaire; }
	public Date getDateDeb()			{ return dateDeb; }
	public Date getDateFin()			{ return dateFin; }
	
	public String[] getNatures()		{ return natures; }

//	Autres
	@Override
	public String toString() {
		return "Rencontre [idRencontre=" + idRencontre + 
				", date=" + dateRenc + 
				", nature=" + nature + 
				", participants=" + participants + 
				", participantsPE=" + participantsPE + 
				", ordreDuJour=" + ordreDuJour + 
				", natureCR=" + natureCR + 
				", prochaine=" + prochaine + 
				", commentaire=" + commentaire + 
				", dateDeb=" + dateDeb + 
				", dateFin=" + dateFin + "]";
	} // toString

} // class Rencontre
