package cahier.text.gestioncahierdetexte.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class ViceRectorat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String libelle;
	private String description;
	

	@JsonIgnore
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(mappedBy="viceRectorat")
    private Collection<EstViceRecteur> viceRecteurs;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<EstViceRecteur> getViceRecteurs() {
		return viceRecteurs;
	}

	public void setViceRecteurs(Collection<EstViceRecteur> viceRecteurs) {
		this.viceRecteurs = viceRecteurs;
	}

	public ViceRectorat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ViceRectorat(long id, String libelle, String description, Collection<EstViceRecteur> viceRecteurs) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.description = description;
		this.viceRecteurs = viceRecteurs;
	}
	

	
}