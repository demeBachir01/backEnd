package cahier.text.gestioncahierdetexte.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UE {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String code;
	private String libelle;
	

	@JsonIgnore
	@OneToMany(mappedBy="ue",cascade=CascadeType.REMOVE)
    private Collection<EC> ec;


	@ManyToOne
    @JoinColumn(name="parcours_id", nullable=false)
	private Parcours parcours;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public Collection<EC> getEc() {
		return ec;
	}


	public void setEc(Collection<EC> ec) {
		this.ec = ec;
	}


	public Parcours getParcours() {
		return parcours;
	}


	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}


	

	public UE(long id, String code, String libelle, Collection<EC> ec, Parcours parcours) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.ec = ec;
		this.parcours = parcours;
	}


	public UE() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}