package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.Etudiant;
import cahier.text.gestioncahierdetexte.entities.Formation;
import cahier.text.gestioncahierdetexte.entities.IndividuCompte;
import cahier.text.gestioncahierdetexte.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    ArrayList<Etudiant> findByInscription(Inscription inscription);
    Etudiant findByComptesIndividu(IndividuCompte individuCompte);

}
