package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Etudiant;
import cahier.text.gestioncahierdetexte.entities.Inscription;
import cahier.text.gestioncahierdetexte.repositories.EtudiantRepository;
import cahier.text.gestioncahierdetexte.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin/inscription")
@RestController
public class InscriptionControleur {
    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;

    @GetMapping("/get")
    public Inscription getById(@RequestParam Long id) {
        Inscription g = inscriptionRepository.findById(id).get();
        return g;
    }
    @GetMapping("/getByEtudiant")
    public Inscription getByEtudiant(@RequestParam Long id) {
        Etudiant etudiant=etudiantRepository.findById(id).get();
        /*Collection<Inscription> g = inscriptionRepository.findByEtudiant(etudiant);
        for(Inscription i : g){
            Date date=new Date();
            if(i.getDateInscription().getYear() == date.getYear() ){
                return i;
            }
        }*/
    return  inscriptionRepository.findActifByEtudiant(etudiant);
    }
    @PostMapping("/add")
    public Inscription ajouter(@RequestBody Inscription inscription) {
            Inscription insc=new Inscription();
            insc.setDateInscription(inscription.getDateInscription());
            insc.setEtudiant(inscription.getEtudiant());
            insc.setGroupe(inscription.getGroupe());
        Inscription save = inscriptionRepository.save(insc);
        return save;
    }

    @PutMapping("/update")
    public Inscription update(@RequestBody Inscription inscription) {

        Inscription g = inscriptionRepository.findById(inscription.getId()).get();
        return inscriptionRepository.save(g);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Inscription u = inscriptionRepository.findById(id).get();
        inscriptionRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(inscriptionRepository.findAll(), HttpStatus.OK);
    }

}
