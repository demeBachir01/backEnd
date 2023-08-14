package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.*;
import cahier.text.gestioncahierdetexte.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/etudiant")

public class EtudiantControleur {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private IndividuCompteRepository individuCompteRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private IndividuRepository individuRepository;

//    @GetMapping("/search")
//    public ArrayList<Enseignant> rechercher(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("numCNI") String numCNI) {
//        ArrayList<Enseignant> ens = new ArrayList<Enseignant>();
//        if (enseignantRepository.findByPrenomNom(prenom, nom) != null) {
//            ens = (ArrayList<Enseignant>) enseignantRepository.findByPrenomNom(prenom, nom);
//        }
//        return ens;
//    }
    @PostMapping("/getAllEnseignantByFormation")
    public Collection<Enseignant>  getAllEnseignantByFormation(@RequestBody Etudiant etudiant) {
        Collection<Inscription> inscription = inscriptionRepository.findByEtudiant(etudiant);
        Inscription ins=new Inscription();
        for(Inscription i : inscription){
            Date date=new Date();
            if(i.getDateInscription().getYear() == date.getYear() ){
                ins=i;
            }
        }
        Formation formation = formationRepository.findByInscription(ins);
    Collection<Enseignant> enseignantByFor = enseignantRepository.findByFormations(formation);
    return enseignantByFor;
}

    @PostMapping("/add")
    public Etudiant ajouter(@RequestBody Etudiant etudiant, @RequestParam(value = "email") String email) {
        Etudiant ens = new Etudiant();
        String username = email.split("@")[0];
        //String username=new String(etudiant.getNom()+"."+etudiant.getPrenom());
        ens.setNom(etudiant.getNom());
        ens.setNumCni(etudiant.getNumCni());
        ens.setPrenom(etudiant.getPrenom());
        ens.setTel(etudiant.getTel());
        ens.setLieuNaissaince(etudiant.getLieuNaissaince());
        ens.setDateNaissance(etudiant.getDateNaissance());
        ens.setNumEtudiant(etudiant.getNumEtudiant());
        ens.setCivilite(etudiant.getCivilite());
//        Inscription inscription = inscriptionRepository.findByEtudiant(etudiant);
//        ens.setInscription((Collection<Inscription>) inscription);
        Etudiant save = etudiantRepository.save(ens);
        Role ensRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        List<Role> roleList=new ArrayList<>();
        roleList.add(ensRole);
        /*Individu individu=new Individu();
        individu.setNom(ens.getNom());
        individu.setPrenom(ens.getPrenom());
        individu.setCivilite(ens.getCivilite());
        individu.setTel(ens.getTel());
        individu.setNumCni(ens.getNumCni());
        individuRepository.save(individu);*/
        IndividuCompte comptes =new IndividuCompte();
        comptes.setUsername(username);
        comptes.setIndividu(save);
        comptes.setRoles(roleList);
        comptes.setDateDebutValite(new Date());
        comptes.setActif(true);
        comptes.setPasswordTemp("passerabc");
        comptes.setPassword(encoder.encode("passerabc"));
        comptes.setEmail(email);
        individuCompteRepository.save(comptes);
        /*Collection<IndividuCompte> individuComptes=new ArrayList<>();
        individuComptes.add(comptes);
        save.setComptesIndividu(individuComptes);*/
        return etudiantRepository.save(save);
    }

//    @GetMapping("findByUfr")
//    public ArrayList<Enseignant> findByUfr(@RequestParam("ufr") Long u) {
//        Ufr ufr = ufrRepository.findById(u).get();
//        return enseignantRepository.findByUfr(ufr);
//    }

//    @GetMapping("findByDepartement")
//    public ArrayList<Enseignant> findByDepartement(@RequestParam("departement") Long u) {
//        Departement dep = departementRepository.findById(u).get();
//        return enseignantRepository.findByDepartement(dep);
//    }

    @GetMapping("/get")
    public Etudiant getById(@RequestBody(required = false) Long id) {
        Etudiant g = etudiantRepository.findById(id).get();
        return g;
    }


    @PutMapping("/update")
    public Etudiant update(@RequestBody Etudiant etudiant) {

        Etudiant ens = etudiantRepository.findById(etudiant.getId()).get();

        ens.setNom(etudiant.getNom());
        ens.setNumCni(etudiant.getNumCni());
        ens.setPrenom(etudiant.getPrenom());
        ens.setTel(etudiant.getTel());
        ens.setCivilite(etudiant.getCivilite());
        Collection<Inscription> org = inscriptionRepository.findByEtudiant(etudiant);
        ens.setInscription((Collection<Inscription>) org);

        return etudiantRepository.save(ens);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Etudiant u = etudiantRepository.findById(id).get();
        etudiantRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")

    public ArrayList<Etudiant> findAll() {

        return (ArrayList<Etudiant>) etudiantRepository.findAll();

    }

}
