package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.AnneeScolaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaire, Long> {
	@Query("FROM AnneeScolaire e WHERE e.actif=true ")
	ArrayList<AnneeScolaire> findByActif();
}
