package ecolededev.pe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ecolededev.pe.models.Action;
import ecolededev.pe.models.Contact;
import ecolededev.pe.models.Convention;
import ecolededev.pe.models.RDTDocument;
import ecolededev.pe.models.Partenaire;
import ecolededev.pe.models.Rencontre;


@Service
public interface IConventionServices {
	public List<Convention> listeConvention();
	public Convention objetConvention (Long idConvention);
	public Convention findByOldIdConvention (long oldIdConvention);
	public List<Contact> listeContacts (Long idConvention);
	public List<RDTDocument> listeDocuments (Long idConvention);
	public List<Rencontre> listeRencontres (Long idConvention);
	public List<Action> listeActions (Long idConvention);

} // interface IConventionServices
