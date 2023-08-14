package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.*;
import cahier.text.gestioncahierdetexte.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/chefDeDepartement")
public class ChefDepartementControleur {

    @Autowired
    private ChefDepartementRepository chefDepartementRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private IndividuCompteRepository individuCompteRepository;

    @GetMapping("/get")
    public ChefDepartement getById(@RequestBody(required = false) Long id) {
        ChefDepartement g = chefDepartementRepository.findById(id).get();
        return g;
    }

    @GetMapping("/findByEns")
    public ArrayList<ChefDepartement> getByEnseignant(@RequestBody(required = false) Enseignant ens) {
        Enseignant e = enseignantRepository.findById(ens.getId()).orElse(null);
        ArrayList<ChefDepartement> g = chefDepartementRepository.findByEnseignant(e);
        return g;
    }

    @GetMapping("/findByDepartement")
    public ArrayList<ChefDepartement> getByDepartement(@RequestBody Departement departement) {
        ArrayList<ChefDepartement> chefDepartements = chefDepartementRepository.findByDepartement(departement);
        return chefDepartements;
    }

//    @GetMapping("/findActifByEns")
//    public DirecteurUfr getActifByEnseignant(@RequestBody(required = false) Enseignant ens) {
//        Enseignant e = enseignantRepository.findById(ens.getId()).orElse(null);
//        DirecteurUfr g = directeurUfrRepository.findActifByEnseignant(e);
//        return g;
//    }
//
//    @GetMapping("/findActifByUf")
//    public DirecteurUfr getActifByUfr(@RequestBody(required = false) Ufr g) {
//        Ufr ufr = ufrRepository.findById(g.getId()).get();
//        DirecteurUfr dir = directeurUfrRepository.findActifByUfr(ufr);
//        return dir;
//    }

    @PostMapping("/add")
    public ChefDepartement ajouter(@RequestBody ChefDepartement chefDepartement) {
        //desactivier les autres liaisons
        ArrayList<ChefDepartement> list = chefDepartementRepository.findByDepartement(chefDepartement.getDepartement());
        Iterator<ChefDepartement> it = list.iterator();
        while (it.hasNext()) {
            ChefDepartement g = it.next();
            g.setEnCours(0);
            if (g.getDateFin() != null) {

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                try {
                    date = df.parse(date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                g.setDateFin(date);
            }
            chefDepartementRepository.save(g);
        }
        //ajouter la nouvelle liaison
        ChefDepartement chefDep = new ChefDepartement();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        //Date newDate = LocalDateTime.parse(gradeEns.getDateDebut(), formatter);

        chefDep.setDepartement(chefDepartement.getDepartement());
        chefDep.setDateDebut(chefDepartement.getDateDebut());
        chefDep.setDateFin(chefDepartement.getDateFin());
        chefDep.setEnCours(1);
        Enseignant ens = chefDepartement.getEnseignant();
        chefDep.setEnseignant(ens);
        Role dirRole = roleRepository.findByName(ERole.ROLE_CHEF_DEPARTEMENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        ArrayList<IndividuCompte> comptes = individuCompteRepository.findByIndividu(chefDep.getEnseignant().getId());

        System.out.println("compte individu " + comptes.size());
        if (comptes != null) {
            Iterator<IndividuCompte> compteIterator = comptes.iterator();
            while (compteIterator.hasNext()) {
                IndividuCompte compte = compteIterator.next();

                if (!compte.getRoles().contains(dirRole)) {
                    List<Role> roles = compte.getRoles();
                    roles.add(dirRole);
                    compte.setRoles(roles);
                    individuCompteRepository.save(compte);
                }

            }
        }

        return chefDepartementRepository.save(chefDep);
    }


    @PutMapping("/update")
    public ChefDepartement update(@RequestBody ChefDepartement chefDepartement) {

        ChefDepartement g = chefDepartementRepository.findById(chefDepartement.getId()).get();

        g.setDepartement(chefDepartement.getDepartement());
        g.setDateDebut(chefDepartement.getDateDebut());
        g.setDateFin(chefDepartement.getDateFin());
        g.setEnCours(chefDepartement.getEnCours());
        g.setEnseignant(chefDepartement.getEnseignant());

        Role dirRole = roleRepository.findByName(ERole.ROLE_CHEF_DEPARTEMENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        ArrayList<IndividuCompte> comptes = individuCompteRepository.findByIndividu(chefDepartement.getEnseignant().getId());//(ArrayList<IndividuCompte>) dir.getEnseignant().getComptesIndividu();

        if (comptes != null) {
            Iterator<IndividuCompte> compteIterator = comptes.iterator();
            while (compteIterator.hasNext()) {
                IndividuCompte compte = compteIterator.next();

                if (!compte.getRoles().contains(dirRole)) {
                    List<Role> roles = compte.getRoles();
                    roles.add(dirRole);
                    compte.setRoles(roles);
                    individuCompteRepository.save(compte);
                }

            }
        }

        return chefDepartementRepository.save(g);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        ChefDepartement u = chefDepartementRepository.findById(id).get();
        chefDepartementRepository.delete(u);
        ;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(chefDepartementRepository.findAll(), HttpStatus.OK);
    }

}
