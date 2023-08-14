package cahier.text.gestioncahierdetexte.controleur;

import cahier.text.gestioncahierdetexte.entities.Role;
import cahier.text.gestioncahierdetexte.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/role")
@AllArgsConstructor
public class RoleController {
@Autowired
    private RoleRepository roleRepository;
    @GetMapping("/get")
    public Role getById(@RequestBody(required = false) Long id) {
        Role g = roleRepository.findById(id).get();
        return g;
    }

    @PostMapping("/add")
    public Role ajouter(@PathVariable Long idEnseignant, @RequestBody Role role) {
        Role r = new Role();

        r.setName(role.getName());
        return roleRepository.save(r);
    }

    @PutMapping("/update")
    public Role update(@RequestBody Role role) {

        Role g = roleRepository.findById(role.getId());

        g.setName(role.getName());
        return roleRepository.save(g);
    }

    //This can be also @DeleteMapping.
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        Role u = roleRepository.findById(id).get();
        roleRepository.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(roleRepository.findAll(), HttpStatus.OK);
    }
}
