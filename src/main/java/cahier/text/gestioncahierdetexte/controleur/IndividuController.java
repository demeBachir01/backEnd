package cahier.text.gestioncahierdetexte.controleur;


import cahier.text.gestioncahierdetexte.entities.Individu;
import cahier.text.gestioncahierdetexte.repositories.IndividuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/individu")
public class IndividuController {

    @Autowired
    private IndividuRepository individuRepository;

    @GetMapping("/get")
    public Individu getById(@RequestBody(required = false) Long id) {
        //System.out.println(id);
        Individu g = individuRepository.findById(id).get();
        return g;
    }

    @GetMapping("/getByNumCni")
    public Individu getByNumCni(@RequestBody(required = false) int id) {
        //System.out.println(id);
        Individu g = individuRepository.findByNumCni(id);
        return g;
    }


    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(individuRepository.findAll(), HttpStatus.OK);
    }

}
