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
public class Cohorte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String libelle;
	private boolean actif;
	@JsonIgnore
	@OneToMany(mappedBy="cohorte")
	private Collection<Groupe> groupes;
	@ManyToOne
    @JoinColumn(name="formation_id", nullable=false)
	private Formation formation;
	@OneToMany(mappedBy = "cohorte")
	private Collection<Affecter> affecters;
	public Cohorte() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cohorte(long id, String libelle, boolean actif, Collection<Inscription> inscriptions) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.actif = actif;
	}
	public boolean getActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
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
	public Collection<Groupe> getGroupes() {
		return groupes;
	}
	public void setGroupes(Collection<Groupe> groupes) {
		this.groupes = groupes;
	}
	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}


}
