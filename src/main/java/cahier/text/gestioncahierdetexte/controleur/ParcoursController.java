package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Formation;
import cahier.text.gestioncahierdetexte.entities.Parcours;
import cahier.text.gestioncahierdetexte.entities.Semestre;
import cahier.text.gestioncahierdetexte.repositories.FormationRepository;
import cahier.text.gestioncahierdetexte.repositories.ParcoursRepository;
import cahier.text.gestioncahierdetexte.repositories.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/parcours")
public class  ParcoursController {

    @Autowired
    private ParcoursRepository parcoursRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private SemestreRepository semestreRepository;

    @GetMapping("/get")
    public Parcours getById(@RequestParam Long id) {
        Parcours g = parcoursRepository.findById(id).get();
        return g;
    }

    @GetMapping("/getByFormation")
    public ArrayList<Parcours> getByFormation(@RequestParam("formation") Long idForm) {
        Formation f = formationRepository.findById(idForm).orElse(null);
        if (f != null) {
            return parcoursRepository.findByFormation(idForm);
        }
        return null;
    }

    @GetMapping("/getByFormationSemestre")
    public ArrayList<Parcours> getByFormationSemestre(@RequestParam("formation") Long idForm, @RequestParam("semestre") Long idSem) {
        Formation f = formationRepository.findById(idForm).orElse(null);
        Semestre s = semestreRepository.findById(idSem).orElse(null);
        if (f != null) {
            return parcoursRepository.findByFormationSemestre(idForm, idSem);
        }
        return null;
    }

    @PostMapping("/add")
    public Parcours ajouter(@RequestBody Parcours parcours) {

        return parcoursRepository.save(parcours);
    }

    @PutMapping("/update")
    public Parcours update(@RequestBody Parcours parcours) {

        Parcours g = parcoursRepository.findById(parcours.getId()).get();

        if (g.getId() != null) {
            g = parcoursRepository.save(parcours);
        }

        return g;
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Parcours u = parcoursRepository.findById(id).get();
        parcoursRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(parcoursRepository.findAll(), HttpStatus.OK);
    }

}
