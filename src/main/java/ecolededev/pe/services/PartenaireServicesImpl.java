package ecolededev.pe.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecolededev.pe.models.Convention;
import ecolededev.pe.models.Partenaire;
import ecolededev.pe.models.PartenaireRepository;

@Service
public class PartenaireServicesImpl implements IPartenaireServices {

	@Autowired PartenaireRepository partenaireRepository;
	
	@Override
	public List<Partenaire> listePartenaire(){
		List<Partenaire> liste = partenaireRepository.findAll();
		return liste;
	} // listePartenaire
	
	@Override
	public List<Partenaire>  unPartenaire (String nom) {
		List<Partenaire> tempo = partenaireRepository.findByPartenaire (nom);
		return tempo;	
	} // unPartenaire

	@Override
	public List<Convention> listeConventions (Long idPartenaire) {
		return partenaireRepository.getListeConventions(idPartenaire);
	} // listeConventions
	
	@Override
	public String descriptionPartenaire (Long  idPartenaire) {
		return partenaireRepository.findOne(idPartenaire).getDescription();
	} // descriptionPartenaire
}
