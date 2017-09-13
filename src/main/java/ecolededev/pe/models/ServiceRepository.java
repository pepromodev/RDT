package ecolededev.pe.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

	@Query ("SELECT listeSupports FROM Service WHERE idService = :idService")
	List<Support> getListeSupports (@Param("idService") Long idService);

} // interface ServiceRepository
