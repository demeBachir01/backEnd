package cahier.text.gestioncahierdetexte.repositories;

import cahier.text.gestioncahierdetexte.entities.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {

}
