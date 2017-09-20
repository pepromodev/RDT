package ecolededev.pe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ecolededev.pe.models.Action;
import ecolededev.pe.models.Contact;
import ecolededev.pe.models.Convention;
import ecolededev.pe.models.Document;
import ecolededev.pe.models.Rencontre;


@Service
public interface IConventionServices {
	public Convention objetConvention (Long idConvention);
	public List<Contact> listeContacts (Long idConvention);
	public List<Document> listeDocuments (Long idConvention);
	public List<Rencontre> listeRencontres (Long idConvention);
	public List<Action> listeActions (Long idConvention);

} // interface IConventionServices
