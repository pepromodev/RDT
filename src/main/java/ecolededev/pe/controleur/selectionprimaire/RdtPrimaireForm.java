package ecolededev.pe.controleur.selectionprimaire;

import java.util.ArrayList;
import java.util.List;

import ecolededev.pe.models.Partenaire;

public class RdtPrimaireForm {
	private String codePartenaire;
	private Partenaire objetPartenaire = new Partenaire();
	private List<Partenaire> listePartenaires = new ArrayList<>();
	
//	Setteurs
	public void setCodePartenaire(String codePartenaire)				{ this.codePartenaire = codePartenaire; }
	public void setObjetPartenaire(Partenaire objetPartenaire)			{ this.objetPartenaire = objetPartenaire; }
	public void setListePartenaires(List<Partenaire> listePartenaires)	{ this.listePartenaires = listePartenaires; }

//	Getteurs
	public String getCodePartenaire()				{ return codePartenaire; }
	public Partenaire getObjetPartenaire()			{ return objetPartenaire;                                                        }
	public List<Partenaire> getListePartenaires()	{ return listePartenaires; }


} // class rdtFormPrimaire
