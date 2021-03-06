package ecolededev.pe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ecolededev.pe.models.Convention;
import ecolededev.pe.models.Partenaire;


@Service
public interface IPartenaireServices {
	
	public List<Partenaire> listePartenaire();
	public List<Convention> listeConventions(Long  idPartenaire);
	public Partenaire objetPartenaire(Long idPartenaire);
	public List<Partenaire> findByPartenaire (String nomPartenaire);

} // interface IPartenaireServices
