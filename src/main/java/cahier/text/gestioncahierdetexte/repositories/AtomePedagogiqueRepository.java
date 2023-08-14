package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface AtomePedagogiqueRepository extends JpaRepository<AtomePedagogique, Long> {

	@Query("FROM AtomePedagogique e WHERE e.ec.id = ?1 ")
	ArrayList<AtomePedagogique> findByEc(Long id);

}
