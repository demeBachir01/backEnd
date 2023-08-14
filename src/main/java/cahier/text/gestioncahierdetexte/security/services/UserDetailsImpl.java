package cahier.text.gestioncahierdetexte.security.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import cahier.text.gestioncahierdetexte.entities.Individu;
import cahier.text.gestioncahierdetexte.entities.IndividuCompte;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	private Individu individu;
	
	private boolean actif;

	private Date dateFinValite;
	
	public UserDetailsImpl(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities, Individu individu,
			boolean actif,Date dateFinValite) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.individu = individu;
		this.actif = actif;
		this.dateFinValite = dateFinValite;
	}

	public static UserDetailsImpl build(IndividuCompte individuCompte) {
		List<GrantedAuthority> authorities = individuCompte.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				individuCompte.getId(), 
				individuCompte.getUsername(), 
				individuCompte.getEmail(),
				individuCompte.getPassword(), 
				authorities,
				individuCompte.getIndividu(),
				individuCompte.isActif(),
				individuCompte.getDateFinValite());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Individu getIndividu() {
		return individu;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		Date aujourdhui = new Date();
		if(this.dateFinValite != null)
			return (aujourdhui.compareTo(this.dateFinValite) > 0);
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.actif;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}