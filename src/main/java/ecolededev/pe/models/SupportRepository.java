package ecolededev.pe.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepository extends JpaRepository<Service, Long> {

	@Query ("SELECT listeContacts FROM Support WHERE idSupport = :idSupport")
	List<Contact> getListeContacts (@Param("idSupport") Long idSupport);

} // interface SupportRepository
