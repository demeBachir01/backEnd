package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.Mention;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MentionRepository extends JpaRepository<Mention, Long> {

	
}
