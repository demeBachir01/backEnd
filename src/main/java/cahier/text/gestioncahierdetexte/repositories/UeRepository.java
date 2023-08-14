package cahier.text.gestioncahierdetexte.repositories;

import java.util.ArrayList;

import cahier.text.gestioncahierdetexte.entities.UE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UeRepository extends JpaRepository<UE, Long> {

	@Query("FROM UE e WHERE e.parcours.id = ?1 ")
	ArrayList<UE> findByParcours(Long idParcours);

	@Query("FROM UE e WHERE e.parcours.formation.id = ?1 ")
	ArrayList<UE> findByFormation(Long idF);

	@Query("FROM UE e WHERE e.parcours.semestre.id = ?1 ")
	ArrayList<UE> findBySemestre(Long idSem);
}
