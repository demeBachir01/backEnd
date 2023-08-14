package cahier.text.gestioncahierdetexte.entities;


import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class EstResponsableFormation {
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
    @JoinColumn(name="affecter_id", nullable=false)
	private Formation formation;
	@ManyToOne
	private ResponsableDeFormation responsableDeFormation;


	public EstResponsableFormation(Long id, int enCours, Date dateDebut, Date dateFin, Ufr ufr) {
		super();
		this.id = id;
		this.enCours = enCours;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}


	public EstResponsableFormation() {
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

}