package cahier.text.gestioncahierdetexte.repositories;

import java.util.ArrayList;
import java.util.Optional;

import cahier.text.gestioncahierdetexte.entities.IndividuCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IndividuCompteRepository extends JpaRepository<IndividuCompte, Long> {

    Optional<IndividuCompte> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("FROM IndividuCompte e WHERE e.individu.id=?1 ")
    ArrayList<IndividuCompte> findByIndividu(Long id);
}
