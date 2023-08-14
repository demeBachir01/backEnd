package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;


public interface DirecteurUfrRepository extends JpaRepository<DirecteurUfr, Long>{


	@Query("FROM DirecteurUfr e WHERE e.enseignant=?1 ")
	DirecteurUfr findActifByEnseignant(Enseignant ens);

	@Query("FROM DirecteurUfr e WHERE e.ufr=?1 ")
	DirecteurUfr findActifByUfr(Ufr u);

	@Query("FROM DirecteurUfr e WHERE e.enseignant=?1 ")
	ArrayList<DirecteurUfr> findByEnseignant(Enseignant ens);

	@Query("FROM DirecteurUfr e WHERE e.ufr.id=?1")
	ArrayList<DirecteurUfr> findByUfr(Long g);

}
