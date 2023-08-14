package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.EC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;


public interface EcRepository extends JpaRepository<EC, Long> {

	@Query("FROM EC e WHERE e.ue.id = ?1 ")
	ArrayList<EC> findByUe(Long id);
}
