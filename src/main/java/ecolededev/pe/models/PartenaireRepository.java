package ecolededev.pe.models;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Long> {

	@Query ("SELECT listeConventions FROM Partenaire WHERE idPartenaire = :idPart")
	List<Convention> getListeConventions (@Param("idPart") Long idPartenaire);
	
	List<Partenaire> findByPartenaire (String partenaire);

} // interface PartenaireRepository
