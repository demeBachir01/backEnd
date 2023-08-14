package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Specialite;
import cahier.text.gestioncahierdetexte.repositories.SpecialiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/specialite")
public class SpecialiteController {

    @Autowired
    private SpecialiteRepository specialiteRepository;

    @GetMapping("/get")
    public Specialite getById(@RequestBody(required = false) Long id) {
        Specialite g = specialiteRepository.findById(id).get();
        return g;
    }

    @PostMapping("/add")
    public Specialite ajouter(@RequestBody Specialite specialite) {

        return specialiteRepository.save(specialite);
    }

    @PutMapping("/update")
    public Specialite update(@RequestBody Specialite specialite) {

        return specialiteRepository.save(specialite);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Specialite u = specialiteRepository.findById(id).get();
        specialiteRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(specialiteRepository.findAll(), HttpStatus.OK);
    }

}
