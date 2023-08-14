package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SeanceCoursRepository extends JpaRepository<SeanceCours,Long> {
    Collection<SeanceCours> findByAffecter(Affecter affecter);
    Collection<SeanceCours> findByValiderIsFalse();
    Collection<SeanceCours> findByValiderIsTrue();
    Boolean findByValider(Boolean v);
}
