package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Ufr;
import cahier.text.gestioncahierdetexte.repositories.UfrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/ufr")
public class UfrController {

    @Autowired
    private UfrRepository ufrRepository;

    @GetMapping("/get")
    public Ufr getById(@RequestBody(required = false) Long id) {
        Ufr g = ufrRepository.findById(id).get();
        return g;
    }

    @PostMapping("/add")
    public Ufr ajouter(@RequestBody Ufr org) {

        return ufrRepository.save(org);
    }

    @PutMapping("/update")
    public Ufr update(@RequestBody Ufr org) {

        return ufrRepository.save(org);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        ufrRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(ufrRepository.findAll(), HttpStatus.OK);
    }

}
