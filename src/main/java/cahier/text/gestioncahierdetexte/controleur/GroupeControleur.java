package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Groupe;
import cahier.text.gestioncahierdetexte.repositories.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/groupe")
public class GroupeControleur {
    @Autowired
    private GroupeRepository groupeRepository;

    @GetMapping("/get")
    public Groupe getById(@RequestBody(required = false) Long id) {
        Groupe g = groupeRepository.findById(id).get();
        return g;
    }

    @PostMapping("/add")
    public Groupe ajouter(@RequestBody Groupe groupe) {
        Groupe g = new Groupe();

        g.setLibelle(groupe.getLibelle());

        return groupeRepository.save(g);
    }

    @PutMapping("/update")
    public Groupe update(@RequestBody Groupe groupe) {

        Groupe g = groupeRepository.findById(groupe.getId()).get();

        g.setLibelle(groupe.getLibelle());

        return groupeRepository.save(g);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Groupe u = groupeRepository.findById(id).get();
        groupeRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(groupeRepository.findAll(), HttpStatus.OK);
    }

}
