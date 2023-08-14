package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.*;
import cahier.text.gestioncahierdetexte.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/seanceCours")
@AllArgsConstructor
public class SeanceCoursControlleur {
    private SeanceCoursRepository seanceCoursRepository;
    private AffecterRepository affecterRepository;
    private DepartementRepository departementRepository;
    private EnseignantRepository enseignantRepository;
    private CohorteRepository cohorteRepository;
    private AtomePedagogiqueRepository atomePedagogiqueRepository;
    private EtudiantRepository etudiantRepository;
    private final InscriptionRepository inscriptionRepository;
    private final SemestreRepository semestreRepository;

    @GetMapping("/all")
    public Collection<SeanceCours> seanceCours(){
    return  seanceCoursRepository.findAll();
    }
    @GetMapping("/get")
    public SeanceCours seanceCours(@RequestParam Long idSeance){
        SeanceCours cours=seanceCoursRepository.findById(idSeance).get();
//        SimpleDateFormat hourParser=new SimpleDateFormat("HH:mm");

//       Date heureFinale=new Date(seanceCours.getHeureFin().getTime()-seanceCours.getHeureDeb().getTime());
//        System.out.println("Heure finale " + hourParser.format(heureFinale));
        return cours;
    }
    /*@PostMapping("getNombreHeureByEnseignant")
    public Integer getNombreHeureByEnseignant(@RequestBody Enseignant enseignant) throws ParseException {
        Integer cumuleHeure=0;
        Enseignant ens=enseignantRepository.findById(enseignant.getId()).get();
        Collection<Affecter> affecter=affecterRepository.findByEnseignants(ens);
        ArrayList<SeanceCours> seanceCoursList=new ArrayList<>();
        for (Affecter a : affecter){
            Collection<SeanceCours> seancess=semestreRepository.fi
        }
        Collection<SeanceCours> listSeances = seanceCoursRepository.findByAffecter(affecter);
        for (SeanceCours seanceCours : listSeances) {
            SimpleDateFormat hourParser=new SimpleDateFormat("HH:mm");
//        Date heureDeb=hourParser.parse(seanceCours.getHeureDeb());
//            Date heureFin=hourParser.parse(seanceCours.getHeureFin());
            Date nbrHeure=new Date(hourParser.parse(seanceCours.getHeureFin()).getTime()-hourParser.parse(seanceCours.getHeureDeb()).getTime());
            cumuleHeure +=nbrHeure.getHours();
        }
        return  cumuleHeure;
    }*/
   /* @PostMapping("getAtomePedagogiqueByEnseignant")
    public AtomePedagogique getAtomePedagogiqueByEnseignant(@RequestBody Enseignant enseignant){
        Enseignant ens=enseignantRepository.findById(enseignant.getId()).get();
        Collection<Affecter> affecter=affecterRepository.findByEnseignants(ens);
        AtomePedagogique atomePedagogique=affecter.getAtomePedagogiques();
        AtomePedagogique atomePedagogiques=atomePedagogiqueRepository.findById(atomePedagogique.getId()).get();
        return atomePedagogiques;
    }*/
    @PostMapping("getEnseignantByFormation")
    public Collection<Enseignant>  getEnseignantByFormation(@RequestBody Formation formation){
        Collection<Enseignant> listEnseignant = enseignantRepository.findByFormations(formation);
        return  listEnseignant;
    }
    @GetMapping("validerSeance")
    public SeanceCours validerSeance(@RequestParam Long id){
        SeanceCours s=seanceCoursRepository.findById(id).get();
        s.setValider(true);
        return seanceCoursRepository.save(s);
    }

    @PostMapping("addCommentaire")
    public SeanceCours addCommentaire(@RequestBody SeanceCours ss, @RequestParam String comment ){
        SeanceCours s = seanceCoursRepository.findById(ss.getIdSeance()).get();
        s.setCommentaire(s.getCommentaire()+" \n"+comment);
        return seanceCoursRepository.save(s);
    }

    @PostMapping("/add")
    public SeanceCours saveSeanceCours(@RequestBody SeanceCours seanceCours) throws ParseException {
        SimpleDateFormat hourParser=new SimpleDateFormat("HH:mm");
        Affecter aff=seanceCours.getAffecter();
        Affecter affecter=affecterRepository.findById(aff.getId()).get();
        Collection<SeanceCours> sss=seanceCoursRepository.findByAffecter(affecter);
        int nbrSeance= sss.size();
        seanceCours.setNumSeance(nbrSeance + 1);
        seanceCours.setIdSeance(seanceCours.getIdSeance());
        SeanceCours saveSeance = seanceCoursRepository.save(seanceCours);
        Date dateFF=new Date(hourParser.parse(saveSeance.getHeureFin()).getTime()-hourParser.parse(saveSeance.getHeureDeb()).getTime());
        AtomePedagogique atomePedagogique=affecter.getAtomePedagogiques();
        AtomePedagogique atomePedagogiques=atomePedagogiqueRepository.findById(atomePedagogique.getId()).get();
        Integer nbHeureEffectue = atomePedagogiques.getNbreHeureEffectue() + dateFF.getHours();
        atomePedagogiques.setNbreHeureEffectue(nbHeureEffectue);
        atomePedagogiques.setNbreHeureRestant(atomePedagogiques.getVolumeHoraire() - atomePedagogique.getNbreHeureEffectue());
        atomePedagogiqueRepository.save(atomePedagogiques);
        saveSeance = seanceCoursRepository.findById(saveSeance.getIdSeance()).get();
        return  saveSeance;
    }
  /*  @GetMapping("/getByCohorte")
    public Collection<SeanceCours>  getByCohorte(@RequestParam Long id){
        Cohorte cohorte=cohorteRepository.findById(id).get();
        Affecter affecter=affecterRepository.findByCohorte(cohorte.getId());
        Collection<SeanceCours> seanceCours=seanceCoursRepository.findByAffecter(affecter);
        return  seanceCours;
    }*/
    @GetMapping("/getByGroupe")
    public Collection<SeanceCours>  getByGroupe(@RequestParam("id_groupe") Long id){
        Collection<Affecter> affectations=affecterRepository.findByGroupe(id);
        ArrayList<SeanceCours> seanceCoursReturn=new ArrayList<SeanceCours>();
        for (Affecter a : affectations){
            Collection<SeanceCours> seances=seanceCoursRepository.findByAffecter(a);
            seanceCoursReturn.addAll(seances);
        }
        return  seanceCoursReturn;
    }
    @GetMapping("getByEnseignant")
    public Collection<SeanceCours> getByEnseignant(@RequestParam Long id){
        Enseignant enseignant=enseignantRepository.findById(id).get();
        Collection<Affecter> affecter=affecterRepository.findByEnseignants(enseignant);
        ArrayList<SeanceCours> seanceCoursArrayList=new ArrayList<>();
        for (Affecter a : affecter ){
            Collection<SeanceCours> seanceCours= seanceCoursRepository.findByAffecter(a);
            seanceCoursArrayList.addAll(seanceCours);
        }
        return seanceCoursArrayList;
    }

//    public  Collection<SeanceCours> getByDepartement(Departement departement){
//    ArrayList<Enseignant> enseignant=enseignantRepository.findByDepartement(departement);
//    }
    @PutMapping("/update")
    public SeanceCours upDateSeanceCours(@RequestBody SeanceCours seanceCours){
        SeanceCours s=seanceCoursRepository.findById(seanceCours.getIdSeance()).get();
        if(s != null){
                s = seanceCoursRepository.save(seanceCours);
        }
       return s;
    }
    @DeleteMapping("/delete")
    public  void deleteSeance(@RequestBody Long idSeance){
        SeanceCours seanceCours = seanceCoursRepository.findById(idSeance).get();
        if( seanceCours != null) {
            seanceCoursRepository.delete(seanceCours);
        }
    }
}
