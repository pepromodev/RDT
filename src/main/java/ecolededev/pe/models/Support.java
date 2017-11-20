package ecolededev.pe.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Supports")
public class Support {
	@Id
	@GeneratedValue
	@Column(name="idSupport")
	private Long idSupport;
	@Column(name="nature")
	private String nature;
	@OneToMany (fetch=FetchType.EAGER)
	private List<Contact> listeContacts = new ArrayList<Contact>();

//	Constructeur	
	public Support() {}
	public Support(String nature) { this.nature = nature; }
		
//	Setteurs (pas de setIdContact : Id géré par JPA)
	public void setIdSupport(Long idSupport)					{ this.idSupport = idSupport; }
	public void setNature(String nature)						{ this.nature = nature; }
	public void setListeContacts(List<Contact> listeContacts)	{ this.listeContacts = listeContacts; }

//	Getteurs
	public Long getIdSupport()				{ return idSupport; }
	public String getNature()				{ return nature; }
	public List<Contact> getListeContacts()	{ return listeContacts; }


//	Autres
	@Override
	public String toString() {
		return "Support [idSupport=" + idSupport + 
				", nature=" + nature + "]";
	} // toString
	
} // class Support
