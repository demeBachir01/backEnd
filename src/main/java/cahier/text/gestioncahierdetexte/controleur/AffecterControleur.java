package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Affecter;
import cahier.text.gestioncahierdetexte.entities.AtomePedagogique;
import cahier.text.gestioncahierdetexte.entities.Enseignant;
import cahier.text.gestioncahierdetexte.repositories.AffecterRepository;
import cahier.text.gestioncahierdetexte.repositories.AtomePedagogiqueRepository;
import cahier.text.gestioncahierdetexte.repositories.EnseignantRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/affecter")
@AllArgsConstructor @NoArgsConstructor
public class AffecterControleur {
    @Autowired
    private AffecterRepository affecterRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private AtomePedagogiqueRepository atomePedagogiqueRepository;

    @GetMapping("get")
    public Affecter get(@RequestParam Long id){
        Affecter affecter=affecterRepository.findById(id).get();
        return affecter;
    }
    @GetMapping("getByEnseignant")
    public Collection<Affecter> getByEnseignant(@RequestParam Long id){
        Enseignant ens=enseignantRepository.findById(id).get();
        Collection<Affecter> affecter=affecterRepository.findByEnseignants(ens);
        return affecter;
    }
    @PostMapping("add")
    public  Affecter ajouter(@RequestBody Affecter affecter){
//        Collection<Enseignant> ens=affecter.getEnseignants();
//        for (Enseignant e : ens){
//            Enseignant enseignant=enseignantRepository.findById(e.getId()).get();
//        }
        Affecter saved = affecterRepository.save(affecter);
        return saved;
    }

    @PutMapping("update")
    public Affecter update(@RequestBody Affecter affecter){
        Affecter save = affecterRepository.save(affecter);
        return  save;
    }

    @DeleteMapping("delete")
    public void deleteAffectation(@RequestBody Affecter affecter){
        affecterRepository.deleteById(affecter.getId());
    }

    @GetMapping("getall")
    public ResponseEntity<?> listAffectation(){
        return new ResponseEntity<>(affecterRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("getByAtomPeda")
    public Affecter getByAtomPeda(@RequestParam Long id){
        AtomePedagogique atomePedagogique=atomePedagogiqueRepository.findById(id).get();
        Affecter affecter=affecterRepository.findByAtomePedagogiques(atomePedagogique);
        return  affecter;
    }
}
