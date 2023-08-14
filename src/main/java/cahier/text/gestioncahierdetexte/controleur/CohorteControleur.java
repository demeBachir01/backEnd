package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Cohorte;
import cahier.text.gestioncahierdetexte.repositories.CohorteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/cohorte")
public class CohorteControleur {
    @Autowired
    private CohorteRepository cohorteRepository;

    @GetMapping("/get")
    public Cohorte getById(@RequestBody(required = false) Long id) {
        Cohorte g = cohorteRepository.findById(id).get();
        return g;
    }

    @PostMapping("/add")
    public Cohorte ajouter(@RequestBody Cohorte cohorte) {
        Cohorte g = new Cohorte();

        g.setLibelle(cohorte.getLibelle());

        return cohorteRepository.save(g);
    }

    @PutMapping("/update")
    public Cohorte update(@RequestBody Cohorte cohorte) {

        Cohorte g = cohorteRepository.findById(cohorte.getId()).get();

        g.setLibelle(cohorte.getLibelle());

        return cohorteRepository.save(g);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Cohorte u = cohorteRepository.findById(id).get();
        cohorteRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(cohorteRepository.findAll(), HttpStatus.OK);
    }

}
