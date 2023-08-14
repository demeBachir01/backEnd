package cahier.text.gestioncahierdetexte.security.services;

import cahier.text.gestioncahierdetexte.entities.IndividuCompte;
import cahier.text.gestioncahierdetexte.repositories.IndividuCompteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  IndividuCompteRepository individuCompteRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    IndividuCompte individuCompte = individuCompteRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Impossible trouver l'identifiant : " + username));

    return UserDetailsImpl.build(individuCompte);
  }

}