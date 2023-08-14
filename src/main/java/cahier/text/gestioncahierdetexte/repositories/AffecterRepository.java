package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface AffecterRepository extends JpaRepository<Affecter,Long> {

    Collection<Affecter> findByEnseignants(Enseignant enseignant);
    Collection<Affecter> findByCohorte(Cohorte cohorte);
    @Query("from Affecter a where a.groupe.id=?1")
    Collection<Affecter> findByGroupe(Long id);
    Affecter findByAtomePedagogiques(AtomePedagogique atomePedagogique);

}
