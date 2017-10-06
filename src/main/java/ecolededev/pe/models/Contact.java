package ecolededev.pe.models;

import javax.persistence.*;

@Entity
@Table(name="Contacts")
public class Contact {
	@Id
	@GeneratedValue
	@Column(name="idContact")
	private Long idContact;
	@Column(name="nom")
	private String nom;
	@Column(name="prenom")
	private String prenom;
	@Column(name="fonction")
	private String fonction;
	@Column(name="direction")
	private String direction;
	@Column(name="mail")
	private String mail;
	@Column(name="mobile")
	private String mobile;
	@Column(name="fixe")
	private String fixe;
	@Column(name="commentaire")
	private String commentaire;
	@Column(name="pe")
	private boolean pe;

//	Constructeur	
	public Contact() {}
	public Contact(String nom, String prenom, String fonction, String direction, String mail, String mobile, String fixe, String commentaire, boolean pe) {
		this.nom = nom;
		this.prenom = prenom;
		this.fonction = fonction;
		this.direction = direction;
		this.mail = mail;
		this.mobile = mobile;
		this.fixe = fixe;
		this.commentaire = commentaire;
		this.pe = pe;
	} // Contact

//	Setteurs
	public void setIdContact(Long idContact)		{ this.idContact = idContact; 	}
	public void setNom(String nom)					{ this.nom = nom; }
	public void setPrenom(String prenom)			{ this.prenom = prenom; }
	public void setFonction(String fonction)		{ this.fonction = fonction; }
	public void setDirection(String direction)		{ this.direction = direction; }
	public void setMail(String mail)				{ this.mail = mail; }
	public void setMobile(String mobile)			{ this.mobile = mobile; }
	public void setFixe(String fixe)				{ this.fixe = fixe; }
	public void setCommentaire(String commentaire)	{ this.commentaire = commentaire; }
	public void setPe(boolean pe)					{ this.pe = pe; }

//	Getteurs
	public Long getIdContact()		{ return idContact; }
	public String getNom()			{ return nom; }
	public String getPrenom()		{ return prenom; }
	public String getFonction()		{ return fonction; }
	public String getDirection()	{ return direction; }
	public String getMail()			{ return mail; }
	public String getMobile()			{ return mobile; }
	public String getFixe()			{ return fixe; }
	public String getCommentaire()	{ return commentaire; }
	public boolean isPe()			{ return pe; }

//	Autres
	@Override
	public String toString() {
		return "Contact [idContact=" + idContact + 
				", nom=" + nom + 
				", prenom=" + prenom + 
				", fonction=" + fonction + 
				", direction=" + direction + 
				", mail=" + mail + 
				", mobile=" + mobile + 
				", fixe=" + fixe + 
				", commentaire=" + commentaire + 
				", pe=" + pe + "]";
	} // toString

} // class Contact
