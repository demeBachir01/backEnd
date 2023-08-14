package cahier.text.gestioncahierdetexte.repositories;

import java.util.ArrayList;

import cahier.text.gestioncahierdetexte.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface SemestreRepository extends JpaRepository<Semestre, Long> {
	@Query("FROM Semestre e WHERE e.anneeScolaire.id=?1")
	ArrayList<Semestre> findByAnneeScolaire(Long id);
	

	@Query("FROM Semestre e WHERE e.numSemestre=?1 and e.anneeScolaire=?2")
	Semestre findByLibelle(int num,AnneeScolaire a);
	
}
