package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Niveau;
import cahier.text.gestioncahierdetexte.repositories.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/niveau")
public class NiveauController {

    @Autowired
    private NiveauRepository niveauRepository;

    @GetMapping("/get")
    public Niveau getById(@RequestBody(required = false) Long id) {
        Niveau g = niveauRepository.findById(id).get();
        return g;
    }

    @PostMapping("/add")
    public Niveau ajouter(@RequestBody Niveau niv) {

        return niveauRepository.save(niv);
    }

    @PutMapping("/update")
    public Niveau update(@RequestBody Niveau niv) {

        return niveauRepository.save(niv);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Niveau u = niveauRepository.findById(id).get();
        niveauRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(niveauRepository.findAll(), HttpStatus.OK);
    }

}
