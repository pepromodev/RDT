package ecolededev.pe.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Documents")
public class RDTDocument {
	@Id
	@GeneratedValue
	@Column(name="idDocument")
	private Long idDocument;
	@Column(name="description")
	private String description;
	@Column(name="type")
	private String type;
	@Column(name="dateConc")
	private Date dateConc;
	@Column(name="dateEche")
	private Date dateEche;
	@Column(name="colibri")
	private String colibri;
	
//	Constructeur	
	public RDTDocument() {}
	public RDTDocument(String description, String type, Date conclu, Date echeance, String colibri) {
		this.description = description;
		this.type = type;
		this.dateConc = conclu;
		this.dateEche = echeance;
		this.colibri = colibri;
	} // Document

	//	Setteurs
	public void setIdDocument(Long idDocument)		{ this.idDocument = idDocument; }
	public void setDescription(String description)	{ this.description = description; }
	public void setType(String type)				{ this.type = type; }
	public void setDateConc(Date dateConc)			{ this.dateConc = dateConc; }
	public void setDateEche(Date DateEche)			{ this.dateEche = DateEche; }
	public void setColibri(String colibri)			{ this.colibri = colibri; }
	
//	Getteurs
	public Long getIdDocument()		{ return idDocument; }
	public String getDescription()	{ return description;	 }
	public String getType()			{ return type; }
	public Date getDateConc()		{ return dateConc; }
	public Date getDateEche()		{ return dateEche; }
	public String getColibri()		{ return colibri; }

//	Autres
	@Override
	public String toString() {
		return "Document [idDocument=" + idDocument + 
				", description=" + description + 
				", type=" + type + 
				", conclu=" + dateConc + 
				", echeance=" + dateEche + 
				", colibri=" + colibri + "]";
	} // toString
	
} // class Document
