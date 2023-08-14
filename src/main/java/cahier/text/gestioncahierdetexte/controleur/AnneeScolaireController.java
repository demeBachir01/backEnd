package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.AnneeScolaire;
import cahier.text.gestioncahierdetexte.repositories.AnneeScolaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/anneeScolaire")
public class AnneeScolaireController {

    @Autowired
    private AnneeScolaireRepository anneeScolaireRepository;

    @GetMapping("/get")
    public AnneeScolaire getById(@RequestBody(required = false) Long id) {
        AnneeScolaire g = anneeScolaireRepository.findById(id).get();
        return g;
    }

    @PostMapping("/add")
    public AnneeScolaire ajouter(@RequestBody AnneeScolaire anneeSco) {
        //desactivier les autres liaisons
        List<AnneeScolaire> list = anneeScolaireRepository.findAll();
        Iterator<AnneeScolaire> it = list.iterator();
        while (it.hasNext()) {
            AnneeScolaire a = it.next();
            a.setActif(false);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            try {
                date = df.parse(date.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (a.getDateFin().equals(null)) {

                a.setDateFin(date);
            }
            anneeScolaireRepository.save(a);
        }
        //ajouter la nouvelle liaison
        AnneeScolaire a = new AnneeScolaire();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        //Date newDate = LocalDateTime.parse(gradeEns.getDateDebut(), formatter);
        a.setActif(true);
        a.setDateDebut(anneeSco.getDateDebut());
        a.setDateFin(anneeSco.getDateFin());
        a.setLibelle(anneeSco.getLibelle());

        return anneeScolaireRepository.save(a);
    }

    @PutMapping("/update")
    public AnneeScolaire update(@RequestBody AnneeScolaire anneeSco) {

        AnneeScolaire a = anneeScolaireRepository.findById(anneeSco.getId()).get();

        a.setActif(anneeSco.isActif());
        a.setDateDebut(anneeSco.getDateDebut());
        a.setDateFin(anneeSco.getDateFin());
        a.setLibelle(anneeSco.getLibelle());

        return anneeScolaireRepository.save(a);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        AnneeScolaire u = anneeScolaireRepository.findById(id).get();
        anneeScolaireRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(anneeScolaireRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findByActif")
    public ResponseEntity<?> findByActif() {

        return new ResponseEntity<>(anneeScolaireRepository.findByActif(), HttpStatus.OK);
    }

}
