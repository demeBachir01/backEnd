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
public class Parcours {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;

	@ManyToOne
    @JoinColumn(name="semestre_id", nullable=false)
    private Semestre semestre;
	
	@ManyToOne
    @JoinColumn(name="formation_id", nullable=false)
	private Formation formation;


	@JsonIgnore
	@OneToMany(mappedBy="parcours")
    private Collection<UE> ues;

	public Parcours() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Parcours(Long id, String libelle, Semestre semestre,
			Formation formation) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.semestre = semestre;
		this.formation = formation;
	}

	public Collection<UE> getUes() {
		return ues;
	}
	public void setUes(Collection<UE> ues) {
		this.ues = ues;
	}

	public Parcours(Long id, String libelle, Semestre semestre,
			Formation formation, Collection<UE> ues) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.semestre = semestre;
		this.formation = formation;
		this.ues = ues;
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

	public Formation getFormation() {
		return formation;
	}


	public void setFormation(Formation formation) {
		this.formation = formation;
	}



	public Semestre getSemestre() {
		return semestre;
	}


	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}


	

}
