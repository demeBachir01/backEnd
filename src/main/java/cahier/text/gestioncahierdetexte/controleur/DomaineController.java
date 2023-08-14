package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Domaine;
import cahier.text.gestioncahierdetexte.repositories.DomaineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/domaine")
public class DomaineController {

    @Autowired
    private DomaineRepository domaineRepository;

    @GetMapping("/get")
    public Domaine getById(@RequestBody(required = false) Long id) {
        Domaine g = domaineRepository.findById(id).get();
        return g;
    }

    @PostMapping("/add")
    public Domaine ajouter(@RequestBody Domaine dom) {
        Domaine g = new Domaine();

        g.setLibelle(dom.getLibelle());

        return domaineRepository.save(g);
    }

    @PutMapping("/update")
    public Domaine update(@RequestBody Domaine dom) {

        Domaine g = domaineRepository.findById(dom.getId()).get();

        g.setLibelle(dom.getLibelle());

        return domaineRepository.save(g);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Domaine u = domaineRepository.findById(id).get();
        domaineRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(domaineRepository.findAll(), HttpStatus.OK);
    }

}
