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
	private String partenaire;
	@Column(name="description")
	private String description;
	@OneToMany
	private List<Convention> listeConventions; 

//	Constructeur	
	public Partenaire() {}
	public Partenaire(String partenaire, String description) {
		this.partenaire = partenaire;
		this.description = description;
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
