package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.ChefDepartement;
import cahier.text.gestioncahierdetexte.entities.Departement;
import cahier.text.gestioncahierdetexte.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Collection;

public interface ChefDepartementRepository extends JpaRepository<ChefDepartement,Long> {
    ArrayList<ChefDepartement> findByDepartement(Departement departement);
    ArrayList<ChefDepartement> findByEnseignant(Enseignant enseignant);
}
