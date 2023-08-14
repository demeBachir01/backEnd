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

public class Groupe {

	private String libelle;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private boolean actif;
	@JsonIgnore
	@OneToMany(mappedBy="groupe")
    private Collection<Inscription> inscriptions;
	
	@ManyToOne
    @JoinColumn(name="cohorte_id", nullable=false)
	private Cohorte cohorte;
	
	@ManyToOne
    @JoinColumn(name="semestre_id", nullable=false)
	private Semestre semestre;

	@OneToMany(mappedBy = "groupe")
	private Collection<Affecter> affecter;

	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	

	public boolean getActif() {
		return actif;
	}


	public void setActif(boolean actif) {
		this.actif = actif;
	}


	public Collection<Inscription> getInscriptions() {
		return inscriptions;
	}


	public void setInscriptions(Collection<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}


	public Groupe(String libelle, long id, boolean actif) {
		super();
		this.libelle = libelle;
		this.id = id;
		this.actif = actif;
	}


	public Groupe(String libelle, long id, boolean actif, Collection<Inscription> inscriptions) {
		super();
		this.libelle = libelle;
		this.id = id;
		this.actif = actif;
		this.inscriptions = inscriptions;
	}


	public Groupe() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Cohorte getCohorte() {
		return cohorte;
	}


	public void setCohorte(Cohorte cohorte) {
		this.cohorte = cohorte;
	}


	public Semestre getSemestre() {
		return semestre;
	}


	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}


}