package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.Enseignant;
import cahier.text.gestioncahierdetexte.entities.Formation;
import cahier.text.gestioncahierdetexte.entities.ResponsableDeFormation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ResponsableDeFormationRepository extends JpaRepository<ResponsableDeFormation,Long> {
    ArrayList<ResponsableDeFormation> findByFormation(Formation f);

    ResponsableDeFormation findActifByEnseignant(Enseignant enseignant);
}
