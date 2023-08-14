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
@RequestMapping("/api/admin/enseignant")
public class EnseignantController {

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private UfrRepository ufrRepository;
    @Autowired
    private IndividuCompteRepository individuCompteRepository;
    @Autowired
    private IndividuRepository individuRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private  FormationRepository formationRepository;
    @Autowired
    private AffecterRepository affecterRepository;

    @Autowired
    PasswordEncoder encoder;
    @GetMapping("/search")
    public ArrayList<Enseignant> rechercher(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("numCNI") String numCNI) {
        ArrayList<Enseignant> ens = new ArrayList<Enseignant>();
        if (enseignantRepository.findByPrenomNom(prenom, nom) != null) {
            ens = (ArrayList<Enseignant>) enseignantRepository.findByPrenomNom(prenom, nom);
        }
        return ens;
    }

    @PostMapping("/add")
    public Enseignant ajouter(@RequestBody Enseignant enseignant,@RequestParam(value = "email") String email ) {
        Enseignant ens = new Enseignant();
        String username = email.split("@")[0];
        //String username=new String(enseignant.getNom()+"."+enseignant.getPrenom());
        ens.setNom(enseignant.getNom());
        ens.setNumCni(enseignant.getNumCni());
        ens.setPrenom(enseignant.getPrenom());
        ens.setTel(enseignant.getTel());
        ens.setCivilite(enseignant.getCivilite());
        Departement org = departementRepository.findById(enseignant.getDepartement().getId()).get();
        ens.setDepartement(org);
        Enseignant save = enseignantRepository.save(ens);
        Role ensRole = roleRepository.findByName(ERole.ROLE_ENSEIGNANT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        List<Role> rolesList=new ArrayList<>();
            rolesList.add(ensRole);
       /* Individu individu=new Individu();
        individu.setNom(ens.getNom());
        individu.setPrenom(ens.getPrenom());
        individu.setCivilite(ens.getCivilite());
        individu.setTel(ens.getTel());
        individu.setNumCni(ens.getNumCni());

        individuRepository.save(individu);*/
        IndividuCompte comptes =new IndividuCompte();
        comptes.setUsername(username);
        comptes.setIndividu(save);
        comptes.setRoles(rolesList);
        comptes.setDateDebutValite(new Date());
        comptes.setActif(true);
        comptes.setPasswordTemp("passerabc");
        comptes.setPassword(encoder.encode("passerabc"));
        comptes.setEmail(email);
        /*Collection<IndividuCompte> compteCollection=new ArrayList<>();
            compteCollection.add(comptes);
        save.setComptesIndividu( compteCollection);*/
        individuCompteRepository.save(comptes);
        return enseignantRepository.save(save);
    }

    @GetMapping("findByUfr")
    public ArrayList<Enseignant> findByUfr(@RequestParam("ufr") Long u) {
        Ufr ufr = ufrRepository.findById(u).get();
        return enseignantRepository.findByUfr(ufr);
    }

    @GetMapping("findByDepartement")
    public ArrayList<Enseignant> findByDepartement(@RequestParam("departement") Long u) {
        Departement dep = departementRepository.findById(u).get();
        return enseignantRepository.findByDepartement(dep);
    }

    @GetMapping("/get")
    public Enseignant getById(@RequestParam Long id) {
        Enseignant g = enseignantRepository.findById(id).get();
        return g;
    }

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
    @PutMapping("/update")
    public Enseignant update(@RequestBody Enseignant enseignant) {

        Enseignant ens = enseignantRepository.findById(enseignant.getId()).get();

        ens.setNom(enseignant.getNom());
        ens.setNumCni(enseignant.getNumCni());
        ens.setPrenom(enseignant.getPrenom());
        ens.setTel(enseignant.getTel());
        ens.setCivilite(enseignant.getCivilite());
        Departement org = departementRepository.findById(enseignant.getDepartement().getId()).get();
        ens.setDepartement(org);

        return enseignantRepository.save(ens);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Enseignant u = enseignantRepository.findById(id).get();
        enseignantRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")

    public ArrayList<Enseignant> findAll() {

        return (ArrayList<Enseignant>) enseignantRepository.findAll();

    }
    @GetMapping("/sizeAllEnseignant")
    public Long sizeAllEnseignant(){

        return enseignantRepository.count();
    }
    @GetMapping("/nombreEnseignantVolumeHoraireAtteint")
    public Integer nbrEnsValideVH(){
        Integer nombreEnseignant=0;
        Integer n=0;
        Integer nbraffect=0;


        List<Enseignant> allEnseignant = enseignantRepository.findAll();

        for (Enseignant e : allEnseignant){
            Collection<Affecter> affectations = affecterRepository.findByEnseignants(e);
            if (affectations.size()!=0){
                Boolean vh = true;
                //for affectation debut
                System.out.println("ens ="+e.getId());
                for (Affecter a : affectations){
                    //nbraffect++;
                    AtomePedagogique at = a.getAtomePedagogiques();
                    System.out.println("effectue = "+at.getNbreHeureEffectue() +" volume=" +at.getVolumeHoraire());
                    if (at.getNbreHeureEffectue() != at.getVolumeHoraire()) {
                        vh = false;
                    }
                    System.out.println(vh);
            }
            //for affectation fin
            if (vh ){
                nombreEnseignant=nombreEnseignant+1;
            }
            //nbraffect=0;
            //n=0;
            }
            System.out.println(nombreEnseignant);
        }
        return nombreEnseignant;
    }
    @GetMapping("/nombreEnseignantVolumeHorairePasAtteint")
    public Integer nbrEnsInValideVH(){
        Integer nombreEnseignant=0;
        Integer n=0;
        Integer nbraffect=0;

        List<Enseignant> allEnseignant = enseignantRepository.findAll();
        for (Enseignant e : allEnseignant){
            Collection<Affecter> affectations = affecterRepository.findByEnseignants(e);
            //for affectation debut
            for (Affecter a : affectations) {
                if (a != null) {
                    nbraffect++;
                    AtomePedagogique at = a.getAtomePedagogiques();
                    if (at.getNbreHeureEffectue() < at.getVolumeHoraire()) {
                        n++;
                    }
                }
            }
            //for affectation fin
            if (nbraffect == n && n!=0 ){
                nombreEnseignant=nombreEnseignant+1;;
            }
            nbraffect=0;
            n=0;
        }
        return nombreEnseignant;
    }
    @GetMapping("/nombreEnseignantVolumeHoraireSupplementaire")
    public Integer nbrEnsSupVH(){
        Integer nombreEnseignant=0;
        Integer n=0;
        List<Enseignant> allEnseignant = enseignantRepository.findAll();
        for (Enseignant e : allEnseignant){
            Collection<Affecter> affectations = affecterRepository.findByEnseignants(e);
            //for affectation debut
            for (Affecter a : affectations) {
                if (a != null) {
                    AtomePedagogique at = a.getAtomePedagogiques();
                    if (at.getNbreHeureEffectue() > at.getVolumeHoraire()) {
                        n++;
                    }
                }
            }
            //for affectation fin
            if (n !=0 ){
                nombreEnseignant=nombreEnseignant+1;
            }
            n=0;
        }
        return nombreEnseignant;
    }

}
