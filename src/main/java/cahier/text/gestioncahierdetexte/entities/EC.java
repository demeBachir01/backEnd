package cahier.text.gestioncahierdetexte.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EC {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String libelle;
	private String code;
	
	@ManyToOne
    @JoinColumn(name="ue_id", nullable=false)
	private UE ue;
	
	@JsonIgnore
	@OneToMany(mappedBy="ec")
    private Collection<AtomePedagogique> atomesPedagogiques;

	public EC() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EC(Long id, String libelle, UE ue, Collection<AtomePedagogique> atomesPedagogiques) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.ue = ue;
		this.atomesPedagogiques = atomesPedagogiques;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public UE getUe() {
		return ue;
	}

	public void setUe(UE ue) {
		this.ue = ue;
	}

	public Collection<AtomePedagogique> getAtomesPedagogiques() {
		return atomesPedagogiques;
	}

	public void setAtomesPedagogiques(Collection<AtomePedagogique> atomesPedagogiques) {
		this.atomesPedagogiques = atomesPedagogiques;
	}
	

}