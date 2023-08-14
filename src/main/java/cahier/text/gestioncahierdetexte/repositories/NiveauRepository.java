package cahier.text.gestioncahierdetexte.repositories;

import java.util.ArrayList;

import cahier.text.gestioncahierdetexte.entities.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface NiveauRepository extends JpaRepository<Niveau, Long> {
	@Query("FROM Niveau e WHERE e.numCycle=?1")
	ArrayList<Niveau> findByCycle(int numCycle);
 
}
