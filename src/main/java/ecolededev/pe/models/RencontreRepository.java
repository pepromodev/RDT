package ecolededev.pe.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RencontreRepository extends JpaRepository<Rencontre, Long> {

} // interface RencontreRepository
