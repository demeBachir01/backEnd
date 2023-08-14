package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.Etudiant;
import cahier.text.gestioncahierdetexte.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface InscriptionRepository extends JpaRepository<Inscription,Long> {

    Collection<Inscription> findByEtudiant(Etudiant etudiant);
    @Query("from Inscription i where i.formation.anneeScolaire.actif = true and i.etudiant = ?1")
    Inscription findActifByEtudiant(Etudiant etudiant);
}
