package ecolededev.pe.models;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Partenaires")
public class Partenaire {
	@Id
	@GeneratedValue
	@Column(name="idPartenaire")
	private Long idPartenaire;
	@Column(name="partenaire")
	private String partenaire;		// doit etre en majuscule !
	@Column(name="description")
	private String description;
	@OneToMany (fetch=FetchType.EAGER)
	private List<Convention> listeConventions; 

//	Constructeur	
	public Partenaire(String partenaire, String description, List<Convention> listeConventions) {
		this.partenaire = partenaire;
		this.description = description;
		this.listeConventions = listeConventions;
	} // Partenaire

//	Setteurs
	public void setIdPartenaire(Long idPartenaire)						{ this.idPartenaire = idPartenaire; }
	public void setPartenaire(String partenaire)						{ this.partenaire = partenaire; }
	public void setDescription(String description)						{ this.description = description; }
	public void setListeConventions(List<Convention> listeConventions)	{ this.listeConventions = listeConventions; }

//	Getteurs
	public Long getIdPartenaire()					{ return idPartenaire; }
	public String getPartenaire()					{ return partenaire; }
	public String getDescription()					{ return description; }
	public List<Convention> getListeConventions()	{ return listeConventions; }
	
//	Autres
	@Override
	public String toString() {
		return "Partenaire [id=" + idPartenaire + 
				", partenaire=" + partenaire + 
				", description=" + description + "]";
	} // toString

} // class Partenaire
