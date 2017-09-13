package ecolededev.pe.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConventionRepository extends JpaRepository<Convention, Long> {
	
	@Query ("SELECT listeContacts FROM Convention WHERE idContact = :idContact")
	List<Contact> getlisteContacts (@Param("idContact") Long idContact);
	
	@Query ("SELECT listeDocuments FROM Convention WHERE idContact = :idContact")
	List<Document> getlisteDocuments (@Param("idContact") Long idContact);
	
	@Query ("SELECT listeRencontres FROM Convention WHERE idContact = :idContact")
	List<Rencontre> getlisteRencontres (@Param("idContact") Long idContact);
	
	@Query ("SELECT listeActions FROM Convention WHERE idContact = :idContact")
	List<Action> getlisteActions (@Param("idContact") Long idContact);
	
	@Query ("SELECT listeServices FROM Convention WHERE idContact = :idContact")
	List<Service> getlisteServices (@Param("idContact") Long idContact);

} // interface ConvenionRepository
