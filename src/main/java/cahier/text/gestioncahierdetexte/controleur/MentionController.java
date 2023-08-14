package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Mention;
import cahier.text.gestioncahierdetexte.repositories.MentionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/mention")
public class MentionController {

    @Autowired
    private MentionRepository mentionRepository;

    @GetMapping("/get")
    public Mention getById(@RequestBody(required = false) Long id) {
        Mention g = mentionRepository.findById(id).get();
        return g;
    }

    @PostMapping("/add")
    public Mention ajouter(@RequestBody Mention mention) {
        Mention g = new Mention();

        g.setLibelle(mention.getLibelle());

        return mentionRepository.save(g);
    }

    @PutMapping("/update")
    public Mention update(@RequestBody Mention mention) {

        Mention g = mentionRepository.findById(mention.getId()).get();

        g.setLibelle(mention.getLibelle());

        return mentionRepository.save(g);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Mention u = mentionRepository.findById(id).get();
        mentionRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(mentionRepository.findAll(), HttpStatus.OK);
    }

}
