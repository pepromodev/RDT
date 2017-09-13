package ecolededev.pe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ecolededev.pe.models.Partenaire;


@Service
public interface IPartenaireServices {
	
	public List<Partenaire> listePartenaire();
	public List<Partenaire> unPartenaire (String nom);

}
