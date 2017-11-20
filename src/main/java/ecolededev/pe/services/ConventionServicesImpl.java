package ecolededev.pe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecolededev.pe.models.Action;
import ecolededev.pe.models.Contact;
import ecolededev.pe.models.Convention;
import ecolededev.pe.models.ConventionRepository;
import ecolededev.pe.models.RDTDocument;
import ecolededev.pe.models.Rencontre;

@Service
public class ConventionServicesImpl  implements IConventionServices {

	@Autowired ConventionRepository conventionRepository;

	@Override
	public List<Convention> listeConvention()					{ return conventionRepository.findAll(); }
	
	@Override
	public Convention objetConvention(Long idConvention)		{ return conventionRepository.findOne (idConvention); } 

	@Override
	public List<Contact> listeContacts(Long idConvention)		{ return conventionRepository.getlisteContacts(idConvention); } 

	@Override
	public List<RDTDocument> listeDocuments(Long idConvention)		{ return conventionRepository.getlisteDocuments(idConvention); }

	@Override
	public List<Rencontre> listeRencontres(Long idConvention)	{ return conventionRepository.getlisteRencontres(idConvention); }

	@Override
	public List<Action> listeActions(Long idConvention)			{ return conventionRepository.getlisteActions(idConvention); }

	@Override
	public Convention findByOldIdConvention (long oldIdConvention) { 
		List <Convention> liste = conventionRepository.findByOldIdConvention (oldIdConvention);
		return liste.get(0);
	}


} // class ConventionServicesImpl
