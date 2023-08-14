package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Departement;
import cahier.text.gestioncahierdetexte.entities.Ufr;
import cahier.text.gestioncahierdetexte.repositories.DepartementRepository;
import cahier.text.gestioncahierdetexte.repositories.UfrRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/departement")
@AllArgsConstructor
public class DepartementController {
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private UfrRepository ufrRepository;

    @GetMapping("/search")
    public ArrayList<Departement> rechercher(@RequestParam("libelle") String libelle) {
        ArrayList<Departement> ens = new ArrayList<Departement>();
        if (departementRepository.findByLibelle(libelle) != null) {
            ens = (ArrayList<Departement>) departementRepository.findByLibelle(libelle);
        }
        return ens;
    }

    @GetMapping("findByUfr")
    public ArrayList<Departement> findByUfr(@RequestParam("ufr") Long u) {
        Ufr ufr = ufrRepository.findById(u).get();
        return departementRepository.findByUfr(ufr);
    }

    @GetMapping("/get")
    public Departement getById(@RequestBody(required = false) Long id) {
        Departement g = departementRepository.findById(id).get();
        return g;
    }

    @PostMapping("/add")
    public Departement ajouter(@RequestBody Departement departement) {
        return departementRepository.save(departement);
    }

    @PutMapping("/update")
    public Departement update(@RequestBody Departement org) {

        return departementRepository.save(org);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Departement u = departementRepository.findById(id).get();
        departementRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(departementRepository.findAll(), HttpStatus.OK);
    }

}
