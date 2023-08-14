package cahier.text.gestioncahierdetexte.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Semestre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int numSemestre;
    private boolean actif;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dateDebut;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dateFin;
	
    @JsonIgnore
	@OneToMany(mappedBy="semestre")
	private Collection<Parcours> parcours;
    @ManyToOne 
    @JoinColumn(name="anneeScolaire_id", nullable=false)
	private AnneeScolaire anneeScolaire;


	
    @JsonIgnore
	@OneToMany(mappedBy="semestre")
	private Collection<Groupe> groupes;
    
    
	
	public Semestre(long id, int numSemestre, boolean actif, Collection<Parcours> parcours, AnneeScolaire anneeScolaire) {
		super();
		this.id = id;
		this.numSemestre = numSemestre;
		this.actif = actif;
		this.parcours = parcours;
		this.anneeScolaire = anneeScolaire;
	}

	public Semestre() { 
		super();
		// TODO Auto-generated constructor stub
	}

	public Semestre(long id, int numSemestre, boolean actif, Date dateDebut, Date dateFin,
			Collection<Parcours> parcours, AnneeScolaire anneeScolaire,
			Collection<Groupe> groupes) {
		super();
		this.id = id;
		this.numSemestre = numSemestre;
		this.actif = actif;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.parcours = parcours;
		this.anneeScolaire = anneeScolaire;
		this.groupes = groupes;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public int getNumSemestre() {
		return numSemestre;
	}

	public void setNumSemestre(int numSemestre) {
		this.numSemestre = numSemestre;
	}

	public Collection<Parcours> getParcours() {
		return parcours;
	}

	public void setParcours(Collection<Parcours> parcours) {
		this.parcours = parcours;
	}

	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}


	public Collection<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Collection<Groupe> groupes) {
		this.groupes = groupes;
	}

	public Semestre(long id, int numSemestre, boolean actif, Collection<Parcours> parcours, AnneeScolaire anneeScolaire
			,  Collection<Groupe> groupes) {
		super();
		this.id = id;
		this.numSemestre = numSemestre;
		this.actif = actif;
		this.parcours = parcours;
		this.anneeScolaire = anneeScolaire;
		this.groupes = groupes;
	}

	

	
}