package cahier.text.gestioncahierdetexte.repositories;

import java.util.ArrayList;
import java.util.Collection;

import cahier.text.gestioncahierdetexte.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface DepartementRepository extends JpaRepository<Departement, Long> {
	@Query("FROM Departement e WHERE e.libelle LIKE %?1% ")
	ArrayList<Departement> findByLibelle(String libelle);
	
	@Query("FROM Departement e WHERE e.ufr=?1")
	ArrayList<Departement> findByUfr(Ufr ufr);
	ArrayList<Departement> findDepartementByFormationsId(Long formationId);
}
 