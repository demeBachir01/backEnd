package cahier.text.gestioncahierdetexte.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cahier.text.gestioncahierdetexte.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
	
	@Query("FROM Enseignant e WHERE e.prenom LIKE %?1% or e.nom LIKE %?2%")
	ArrayList<Enseignant> findByPrenomNom(String prenom, String nom);
	
	@Query("FROM Enseignant e WHERE e.departement.ufr=?1")
	ArrayList<Enseignant> findByUfr(Ufr ufr);

	@Query("FROM Enseignant e WHERE e.departement=?1")
	ArrayList<Enseignant> findByDepartement(Departement dep);
	Enseignant findEnseignantByAffecter(Affecter affecter);
	Collection<Enseignant> findByFormations(Formation formation);
	
}
