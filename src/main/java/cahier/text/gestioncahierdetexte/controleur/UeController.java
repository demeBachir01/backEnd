package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.UE;
import cahier.text.gestioncahierdetexte.repositories.UeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/ue")
public class UeController {

    @Autowired
    UeRepository ueRepository;

    @GetMapping("/get")
    public UE getById(@RequestBody(required = false) Long id) {
        UE g = ueRepository.findById(id).get();
        return g;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createUE(@RequestBody UE ue) {
        return new ResponseEntity<>(ueRepository.save(ue), HttpStatus.CREATED);
    }

    @GetMapping("/getBySemestre")
    public ArrayList<UE> getBySemestre(@RequestParam("semestre") Long idP) {
        if (idP != 0) {
            //if(!(semestreRepository.findById(idP).get()).equals(null)) {
            return ueRepository.findBySemestre(idP);
        }
        return null;
    }

    @GetMapping("/getByParcours")
    public ArrayList<UE> getByParcours(@RequestParam("parcours") Long idP) {
        if (idP != 0) {
            //if(!(semestreRepository.findById(idP).get()).equals(null)) {
            return ueRepository.findByParcours(idP);
        }
        return null;
    }
    @GetMapping("/getByFormation")
    public ArrayList<UE> getByFormation(@RequestParam("formation") Long idF) {
        if (idF != 0) {
            //if(!(semestreRepository.findById(idP).get()).equals(null)) {
            return ueRepository.findByFormation(idF);
        }
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUE(@RequestBody UE ue) {

        return new ResponseEntity<>(ueRepository.save(ue), HttpStatus.CREATED);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUE(@RequestBody Long idUe) {
        UE u = ueRepository.findById(idUe).get();
        System.out.println("Ue id : " + u.getId());
        ueRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllUE() {

        return new ResponseEntity<>(ueRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/number")
    public ResponseEntity<?> numberOfUE() {
        Long number = ueRepository.count();

        return new ResponseEntity<>(number.toString(), HttpStatus.OK);
    }

}
