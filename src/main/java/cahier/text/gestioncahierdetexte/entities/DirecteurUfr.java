package cahier.text.gestioncahierdetexte.entities;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class DirecteurUfr {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int enCours;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dateDebut;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dateFin;
	
	@ManyToOne
    @JoinColumn(name="enseignant_id", nullable=false)
	private Enseignant enseignant;
	
	
	@ManyToOne
    @JoinColumn(name="ufr_id", nullable=false)
	private Ufr ufr;


	public DirecteurUfr(Long id, int enCours, Date dateDebut, Date dateFin, Enseignant enseignant, Ufr ufr) {
		super();
		this.id = id;
		this.enCours = enCours;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.enseignant = enseignant;
		this.ufr = ufr;
	}


	public DirecteurUfr() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getEnCours() {
		return enCours;
	}


	public void setEnCours(int enCours) {
		this.enCours = enCours;
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


	public Enseignant getEnseignant() {
		return enseignant;
	}


	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}


	public Ufr getUfr() {
		return ufr;
	}


	public void setUfr(Ufr ufr) {
		this.ufr = ufr;
	}


}