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
@RequestMapping("/api/admin/responsableformation")
    public class ResponsableFormationControlleur {

        @Autowired
        private FormationRepository formationRepository;
        @Autowired
        private EnseignantRepository enseignantRepository;
        @Autowired
         private ResponsableDeFormationRepository responsableDeFormationRepository;
        @Autowired
         private   DirecteurUfrRepository directeurUfrRepository;
        @Autowired
        private RoleRepository roleRepository;
        @Autowired
        private IndividuCompteRepository individuCompteRepository;

        @GetMapping("/get")
        public ResponsableDeFormation getById(@RequestBody(required = false) Long id) {
            ResponsableDeFormation responsableDeFormation = responsableDeFormationRepository.findById(id).get();
            return responsableDeFormation;
        }

        @GetMapping("/findByFormation")
        public ArrayList<ResponsableDeFormation> getByFormation(@RequestBody(required = false) Enseignant ens) {
            Formation e = formationRepository.findById(ens.getId()).orElse(null);
            ArrayList<ResponsableDeFormation> responsableDeFormations = responsableDeFormationRepository.findByFormation(e);
            return responsableDeFormations;
        }


        @GetMapping("/findActifByEnseignant")
        public ResponsableDeFormation getActifByResponsable(@RequestBody(required = false) Enseignant ens) {
            ResponsableDeFormation responsableDeFormation = responsableDeFormationRepository.findById(ens.getId()).orElse(null);
            ResponsableDeFormation g = responsableDeFormationRepository.findActifByEnseignant(ens);
            return g;
        }

        @PostMapping("/add")
        public ResponsableDeFormation ajouter(@RequestBody ResponsableDeFormation responsableDeFormation) {
            //desactivier les autres liaisons
            ArrayList<ResponsableDeFormation> list = responsableDeFormationRepository.findByFormation(responsableDeFormation.getFormation());
            Iterator<ResponsableDeFormation> it = list.iterator();
            while (it.hasNext()) {
                ResponsableDeFormation g = it.next();
                g.setEncours(0);
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
                responsableDeFormationRepository.save(g);
            }
            //ajouter la nouvelle liaison
            ResponsableDeFormation g = new ResponsableDeFormation();
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            //Date newDate = LocalDateTime.parse(gradeEns.getDateDebut(), formatter);

            g.setFormation(responsableDeFormation.getFormation());
            g.setDateDebut(responsableDeFormation.getDateDebut());
            g.setDateFin(responsableDeFormation.getDateFin());
            g.setEncours(1);
            Enseignant ens = responsableDeFormation.getEnseignant();
            g.setEnseignant(ens);
            Role responsableRole = roleRepository.findByName(ERole.ROLE_RESPONSABLE_FORMATION)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            ArrayList<IndividuCompte> comptes = individuCompteRepository.findByIndividu(responsableDeFormation.getEnseignant().getId());

            System.out.println("compte individu " + comptes.size());
            if (comptes != null) {
                Iterator<IndividuCompte> compteIterator = comptes.iterator();
                while (compteIterator.hasNext()) {
                    IndividuCompte compte = compteIterator.next();

                    if (!compte.getRoles().contains(responsableRole)) {
                        List<Role> roles = compte.getRoles();
                        roles.add(responsableRole);
                        compte.setRoles(roles);
                        individuCompteRepository.save(compte);
                    }

                }
            }

            return responsableDeFormationRepository.save(g);
        }

        @PutMapping("/update")
        public ResponsableDeFormation update(@RequestBody ResponsableDeFormation responsableDeFormation) {

            ResponsableDeFormation g = responsableDeFormationRepository.findById(responsableDeFormation.getId()).get();

            g.setFormation(responsableDeFormation.getFormation());
            g.setDateDebut(responsableDeFormation.getDateDebut());
            g.setDateFin(responsableDeFormation.getDateFin());
            g.setEncours(responsableDeFormation.getEncours());
            g.setEnseignant(responsableDeFormation.getEnseignant());

            Role dirRole = roleRepository.findByName(ERole.ROLE_DIRECTEUR_UFR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            ArrayList<IndividuCompte> comptes = individuCompteRepository.findByIndividu(responsableDeFormation.getEnseignant().getId());//(ArrayList<IndividuCompte>) dir.getEnseignant().getComptesIndividu();

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

            return responsableDeFormationRepository.save(g);
        }

        //This can be also @DeleteMapping.
        @DeleteMapping("/delete")
        public ResponseEntity<?> delete(@RequestBody Long id) {
            ResponsableDeFormation u = responsableDeFormationRepository.getOne(id);
            responsableDeFormationRepository.delete(u);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @GetMapping("/all")
        public ResponseEntity<?> findAll() {

            return new ResponseEntity<>(responsableDeFormationRepository.findAll(), HttpStatus.OK);
        }

    }

