package cahier.text.gestioncahierdetexte.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@PrimaryKeyJoinColumn( name = "id" )
public class Etudiant extends Individu {

	private int numEtudiant;
	private Date dateNaissance;
	private String lieuNaissaince;
	
	@JsonIgnore
	@OneToMany(mappedBy="etudiant")
    private Collection<Inscription> inscription;

	
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Etudiant(int numEtudiant, Date dateNaissance, String lieuNaissaince) {
		super();
		this.numEtudiant = numEtudiant;
		this.dateNaissance = dateNaissance;
		this.lieuNaissaince = lieuNaissaince;
	}
	public Etudiant(long id, String nom, String prenom, int tel,int numEtudiant, Date dateNaissance, String lieuNaissaince) {
		super(id, nom, prenom, tel);
		this.numEtudiant = numEtudiant;
		this.dateNaissance = dateNaissance;
		this.lieuNaissaince = lieuNaissaince;
	}
	public Etudiant(long id, String nom, String prenom, int tel) {
		super(id, nom, prenom, tel);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Collection<Inscription> getInscription() {
		return inscription;
	}
	public void setInscription(Collection<Inscription> inscription) {
		this.inscription = inscription;
	}
	public int getNumEtudiant() {
		return numEtudiant;
	}
	public void setNumEtudiant(int numEtudiant) {
		this.numEtudiant = numEtudiant;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getLieuNaissaince() {
		return lieuNaissaince;
	}
	public void setLieuNaissaince(String lieuNaissaince) {
		this.lieuNaissaince = lieuNaissaince;
	}
	
	

}