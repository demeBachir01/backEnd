package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.EC;
import cahier.text.gestioncahierdetexte.entities.UE;
import cahier.text.gestioncahierdetexte.repositories.EcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/ec")
public class EcController {

    @Autowired
    EcRepository ecRepository;

    @GetMapping("/get")
    public EC getById(@RequestBody(required = false) Long id) {
        EC g = ecRepository.findById(id).get();
        return g;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createEC(@RequestBody EC ec, @RequestBody UE ue) {
        ec.setUe(ue);
        return new ResponseEntity<>(ecRepository.save(ec), HttpStatus.CREATED);
    }

    @GetMapping("/getByUe")
    public ArrayList<EC> getByUe(@RequestParam Long id) {
            return ecRepository.findByUe(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEc(@RequestBody EC ec) {

        return new ResponseEntity<>(ecRepository.save(ec), HttpStatus.CREATED);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody EC ec) {
        EC e = ecRepository.findById(ec.getId()).get();
        ecRepository.delete(e);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(ecRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/number")
    public ResponseEntity<?> numberOf() {
        Long number = ecRepository.count();

        return new ResponseEntity<>(number.toString(), HttpStatus.OK);
    }

}
