package cahier.text.gestioncahierdetexte.controleur;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import cahier.text.gestioncahierdetexte.entities.*;
import cahier.text.gestioncahierdetexte.payload.request.LoginRequest;
import cahier.text.gestioncahierdetexte.payload.request.SignupRequest;
import cahier.text.gestioncahierdetexte.payload.response.JwtResponse;
import cahier.text.gestioncahierdetexte.payload.response.MessageResponse;
import cahier.text.gestioncahierdetexte.repositories.*;
import cahier.text.gestioncahierdetexte.security.jwt.JwtUtils;
import cahier.text.gestioncahierdetexte.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    IndividuCompteRepository individuCompteRepository;
    @Autowired
    IndividuRepository individuRepository;
    @Autowired
    AdministrateurRepository administrateurRepository;
    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    EtudiantRepository etudiantRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                userDetails.getIndividu()));
    }

    @PostMapping("/newAdmin")
    public ResponseEntity<?> registerAdmin( @RequestBody SignupRequest signUpRequest) {
//        if (individuCompteRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Cet identifiant est déja utilisé!"));
//        }

        if (individuCompteRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Cet adresse email est déja utilisé"));
        }

        // Create new individu's account 
        IndividuCompte individuCompte = new IndividuCompte(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        List<Role> roles = new ArrayList<>();
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);
        
        //Créer un administrateur 
        Administrateur admin = new Administrateur();
        admin.setNom(signUpRequest.getNom());
        admin.setPrenom(signUpRequest.getPrenom());
        admin.setTel(signUpRequest.getTel());
        
        // Vérifie si l'administrateur est bien persisté puis enregistrer le compte de l'admin
        if (administrateurRepository.save(admin) != null) {
            individuCompte.setActif(true);
            individuCompte.setDateDebutValite(new Date());
            individuCompte.setRoles((List<Role>) roles);
            individuCompte.setIndividu(admin);
            individuCompte.setPasswordTemp(signUpRequest.getPassword());
            individuCompteRepository.save(individuCompte);

            return ResponseEntity.ok(new MessageResponse("Un nouvel administrateur a été enregistré avec succès!"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Une erreur est survenue, veuillez contacter votre administrateur"));
        }

    }

    @PostMapping("/newEnseignant")
    public ResponseEntity<?> registerEnseignant(@Valid @RequestBody SignupRequest signUpRequest) {
        if (individuCompteRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Cet identifiant est déja utilisé!"));
        }

        if (individuCompteRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Cet adresse email est déja utilisé"));
        }

        // Create new individu's account
        IndividuCompte individuCompte = new IndividuCompte(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        List<Role> roles = new ArrayList<>();
        Role adminRole = roleRepository.findByName(ERole.ROLE_ENSEIGNANT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);
        
        //Créer un enseignant
        Enseignant enseignant = new Enseignant();
        enseignant.setNom(signUpRequest.getNom());
        enseignant.setPrenom(signUpRequest.getPrenom());
        enseignant.setTel(signUpRequest.getTel());
        
        //Vérifier si l'enseignant est bien persisté puis enregistrer le compte de l'enseignant
        if (enseignantRepository.save(enseignant) != null) {
            individuCompte.setActif(true);
            individuCompte.setDateDebutValite(new Date());
            individuCompte.setRoles((List<Role>) roles);
            individuCompte.setIndividu(enseignant);
            individuCompte.setPasswordTemp(signUpRequest.getPassword());
            individuCompteRepository.save(individuCompte);

            return ResponseEntity.ok(new MessageResponse("Un nouvel enseignant a été enregistré avec succès!"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("une erreur est survenue, veuillez contacter votre administrateur"));
        }

    }
    @PostMapping("/newEtudiant")
    public ResponseEntity<?> registerEtudiant(@Valid @RequestBody SignupRequest signUpRequest, Etudiant etudiant) {
        if (individuCompteRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Cet identifiant est déja utilisé!"));
        }

        if (individuCompteRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Cet adresse email est déja utilisé"));
        }

        // Create new individu's account
        IndividuCompte individuCompte = new IndividuCompte(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        List<Role> roles = new ArrayList<>();
        Role adminRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);

        //Créer un enseignant
        Etudiant etudi = new Etudiant();
        etudi.setNom(signUpRequest.getNom());
        etudi.setPrenom(signUpRequest.getPrenom());
//        etudi.setDateNaissance();
//        etudi.setNumEtudiant();
//        etudi.setLieuNaissaince();
        etudi.setTel(signUpRequest.getTel());

        //Vérifier si l'enseignant est bien persisté puis enregistrer le compte de l'enseignant
        if (etudiantRepository.save(etudi) != null) {
            individuCompte.setActif(true);
            individuCompte.setDateDebutValite(new Date());
            individuCompte.setRoles((List<Role>) roles);
            individuCompte.setIndividu(etudi);
            individuCompte.setPasswordTemp(signUpRequest.getPassword());
            individuCompteRepository.save(individuCompte);

            return ResponseEntity.ok(new MessageResponse("Un nouvel etudiant a été enregistré avec succès!"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("une erreur est survenue, veuillez contacter votre administrateur"));
        }

    }

    /*
	 * @PostMapping("/signup") public ResponseEntity<?>
	 * registerUser(@Valid @RequestBody SignupRequest signUpRequest) { if
	 * (individuCompteRepository.existsByUsername(signUpRequest.getUsername())) {
	 * return ResponseEntity .badRequest() .body(new
	 * MessageResponse("Error: Cet identifiant est déja utilisé!")); }
	 * 
	 * if (individuCompteRepository.existsByEmail(signUpRequest.getEmail())) {
	 * return ResponseEntity .badRequest() .body(new
	 * MessageResponse("Error: Cet adresse email est déja utilisé")); }
	 * 
	 * 
	 * 
	 * // Create new individu's account IndividuCompte individuCompte = new
	 * IndividuCompte(signUpRequest.getUsername(), signUpRequest.getEmail(),
	 * encoder.encode(signUpRequest.getPassword())); Individu user = new Individu();
	 * user.setNom(signUpRequest.getNom());
	 * user.setPrenom(signUpRequest.getPrenom());
	 * user.setTel(signUpRequest.getTel()); Set<String> strRoles =
	 * signUpRequest.getRole(); Set<Role> roles = new HashSet<>();
	 * 
	 * if (strRoles == null) { Role userRole =
	 * roleRepository.findByName(ERole.ROLE_USER) .orElseThrow(() -> new
	 * RuntimeException("Error: le role fourni est introuvable."));
	 * roles.add(userRole); } else { strRoles.forEach(role -> { switch (role) { case
	 * "admin": Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	 * .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	 * roles.add(adminRole); // create new individu
	 * 
	 * break; case "enseignant": Role modRole =
	 * roleRepository.findByName(ERole.ROLE_ENSEIGNANT) .orElseThrow(() -> new
	 * RuntimeException("Error: Role is not found.")); roles.add(modRole);
	 * 
	 * break; default: Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	 * .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	 * roles.add(userRole); } }); }
	 * 
	 * if(individuRepository.save(user) != null) { individuCompte.setActif(true);
	 * individuCompte.setDateDebutValite(new Date());
	 * individuCompte.setRoles(roles); individuCompte.setIndividu(user);
	 * individuCompteRepository.save(individuCompte);
	 * 
	 * return ResponseEntity.ok(new MessageResponse("Enregistrement réussi!")); }
	 * else { return ResponseEntity .badRequest() .body(new
	 * MessageResponse("une erreur est survenue, veuillez contacter votre administrateur"
	 * )); }
	 * 
	 * 
	 * }
     */
}
