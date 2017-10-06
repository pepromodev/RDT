package ecolededev.pe.services;

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
	public List<Partenaire> listePartenaire()						{ return partenaireRepository.findAll(); }
	
	@Override
	public List<Convention> listeConventions (Long idPartenaire)	{ return partenaireRepository.getListeConventions(idPartenaire); }
	
	@Override
	public Partenaire objetPartenaire (Long  idPartenaire)			{ return partenaireRepository.findOne(idPartenaire); }

	@Override
	public List<Partenaire> findByPartenaire(String nomPartenaire)	{ return partenaireRepository.findByPartenaire(nomPartenaire); }

} // class PartenaireServicesImpl
