package ecolededev.pe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ecolededev.pe.models.Action;
import ecolededev.pe.models.Contact;
import ecolededev.pe.models.Convention;
import ecolededev.pe.models.ConventionRepository;
import ecolededev.pe.models.Document;
import ecolededev.pe.models.Rencontre;

public class ConventionServicesImpl  implements IConventionServices {

	@Autowired ConventionRepository conventionRepository;

	@Override
	public Convention objetConvention(Long idConvention)		{ return conventionRepository.findOne (idConvention); } 

	@Override
	public List<Contact> listeContacts(Long idConvention)		{ return conventionRepository.getlisteContacts(idConvention); } 

	@Override
	public List<Document> listeDocuments(Long idConvention)		{ return conventionRepository.getlisteDocuments(idConvention); }

	@Override
	public List<Rencontre> listeRencontres(Long idConvention)	{ return conventionRepository.getlisteRencontres(idConvention); }

	@Override
	public List<Action> listeActions(Long idConvention)			{ return conventionRepository.getlisteActions(idConvention); }

} // class ConventionServicesImpl