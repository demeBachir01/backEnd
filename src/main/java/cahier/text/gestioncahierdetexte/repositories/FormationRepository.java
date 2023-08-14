package cahier.text.gestioncahierdetexte.repositories;

import java.util.ArrayList;

import cahier.text.gestioncahierdetexte.entities.Formation;
import cahier.text.gestioncahierdetexte.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FormationRepository extends JpaRepository<Formation, Long> {

	@Query("FROM Formation e WHERE e.libelle LIKE %?1% ")
	ArrayList<Formation> findByLibelle(String libelle);
	
	@Query("FROM Formation e WHERE e.mention.id =?2 ")
	ArrayList<Formation> findByMention(Long idMention);

//	@Query("FROM Formation e WHERE e.anneeScolaire.id =?1 ")
//	ArrayList<Formation> findByAnnee(Long idAn);

//	@Query("FROM Formation e WHERE e.anneeScolaire.id =?1 and e.niveau.id =?2")
//	ArrayList<Formation> findByAnneeNiveau(Long idAn,Long idNiv);
	
	@Query("FROM Formation e WHERE e.domaine.id = ?2")
	ArrayList<Formation> findByDomaine(Long idDomaine);

	@Query("FROM Formation e WHERE e.specialite.id = ?2")
	ArrayList<Formation> findBySpecialite(Long idSpec);

	@Query("FROM Formation e WHERE e.mention.id =?2 and e.domaine.id = ?3 and e.specialite.id = ?4 ")
	ArrayList<Formation> findByMentionDomaineSpecialite(Long idMention,Long idDomaine,Long idSpec);

	@Query("FROM Formation e WHERE e.mention.id =?2 and e.domaine.id = ?3")
	ArrayList<Formation> findByMentionDomaine(Long idMention,Long idDomaine);

	@Query("FROM Formation e WHERE e.niveau.id =?2")
	ArrayList<Formation> findByAnneeNiveauDept(Long idDom, Long idNiv, Long idDept);

	ArrayList<Formation> findFormationByDepartementsId(Long departementId);
	Formation findByInscription(Inscription inscription);
}
