package cahier.text.gestioncahierdetexte.repositories;

import java.util.ArrayList;

import cahier.text.gestioncahierdetexte.entities.Ufr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UfrRepository extends JpaRepository<Ufr, Long> {
	

}
