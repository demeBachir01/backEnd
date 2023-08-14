package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.Individu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



public interface IndividuRepository extends JpaRepository<Individu, Long> {
	@Query("FROM Individu e WHERE e.numCni =?1")
	Individu findByNumCni(int numCni);

}
