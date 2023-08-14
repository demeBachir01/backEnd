package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Semestre;
import cahier.text.gestioncahierdetexte.repositories.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/semestre")
public class SemestreController {

    @Autowired
    private SemestreRepository semestreRepository;

    @GetMapping("/get")
    public Semestre getById(@RequestBody(required = false) Long id) {
        Semestre g = semestreRepository.findById(id).get();
        return g;
    }

    @GetMapping("/getByAnneeScolaire")
    public ArrayList<Semestre> getByAnneeScolaire(@RequestParam("anneeScolaire") Long idP) {
        if (idP != 0) {
            //if(!(semestreRepository.findById(idP).get()).equals(null)) {
            return semestreRepository.findByAnneeScolaire(idP);
        }
        return null;
    }

    @GetMapping("/activateSemestre")
    public Semestre activateSemestre(@RequestParam("semestreEnCours") Long id, @RequestParam("anneeScolaire") Long idAn) {
        Semestre sem = semestreRepository.findById(id).get();
        ArrayList<Semestre> semestresAnnee = semestreRepository.findByAnneeScolaire(idAn);
        Iterator<Semestre> it = semestresAnnee.iterator();
        while (it.hasNext()) {
            Semestre s = it.next();
            if (s.getId() != sem.getId()) {
                s.setActif(false);
                if (s.getDateFin() == null) {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    try {
                        date = df.parse(date.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    s.setDateFin(date);
                }
                semestreRepository.save(s);
            }
        }
        sem.setActif(true);
        return semestreRepository.save(sem);
    }

    @PostMapping("/add")
    public Semestre ajouter(@RequestBody Semestre sem) {

        //on verifie si la phase n'est pas déjà enregistré
        Semestre p = semestreRepository.findByLibelle(sem.getNumSemestre(), sem.getAnneeScolaire());
        if (p == null) {
            return semestreRepository.save(sem);
        } else {
            return new Semestre();
        }

    }

    @PutMapping("/update")
    public Semestre update(@RequestBody Semestre sem) {

        Semestre g = semestreRepository.findById(sem.getId()).get();//getOne(sem.getId());

        if (g != null) {
            g = semestreRepository.save(sem);
        }

        return g;
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Semestre u = semestreRepository.findById(id).get();
        semestreRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(semestreRepository.findAll(), HttpStatus.OK);
    }

}
