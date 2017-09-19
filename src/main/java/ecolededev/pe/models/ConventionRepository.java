package ecolededev.pe.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConventionRepository extends JpaRepository<Convention, Long> {
	
	@Query ("SELECT listeContacts FROM Convention c WHERE c.idConvention = :idConvention")
	List<Contact> getlisteContacts (@Param("idConvention") Long idConvention);
	
	@Query ("SELECT listeDocuments FROM Convention c WHERE c.idConvention = :idConvention")
	List<Document> getlisteDocuments (@Param("idConvention") Long idConvention);
	
	@Query ("SELECT listeRencontres FROM Convention c WHERE c.idConvention = :idConvention")
	List<Rencontre> getlisteRencontres (@Param("idConvention") Long idConvention);
	
	@Query ("SELECT listeActions FROM Convention c WHERE c.idConvention = :idConvention")
	List<Action> getlisteActions (@Param("idConvention") Long idConvention);
	
	@Query ("SELECT listeServices FROM Convention c WHERE c.idConvention = :idConvention")
	List<Service> getlisteServices (@Param("idConvention") Long idConvention);

} // interface ConvenionRepository
