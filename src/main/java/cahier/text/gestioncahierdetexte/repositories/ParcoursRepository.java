package cahier.text.gestioncahierdetexte.repositories;

import java.util.ArrayList;

import cahier.text.gestioncahierdetexte.entities.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ParcoursRepository extends JpaRepository<Parcours, Long> {
	@Query("FROM Parcours e WHERE e.formation.id=?1")
	ArrayList<Parcours> findByFormation(Long idForm);
	
	@Query("FROM Parcours e WHERE e.formation.id=?1 and e.semestre.id=?2")
	ArrayList<Parcours> findByFormationSemestre(Long idForm,Long idSem);
	
 
}
