package cahier.text.gestioncahierdetexte.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AnneeScolaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String libelle;
    private boolean actif;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateDebut;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateFin;

    @JsonIgnore
    @OneToMany(mappedBy = "anneeScolaire")
    private Collection<Semestre> semestres;
    @JsonIgnore
    @OneToMany(mappedBy = "anneeScolaire")
    private Collection<Formation> formations;
    public AnneeScolaire() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AnneeScolaire(long id, String libelle, boolean actif, Date dateDebut, Date dateFin, Collection<Semestre> semestres, Collection<Formation> formations) {
        this.id = id;
        this.libelle = libelle;
        this.actif = actif;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.semestres = semestres;
        this.formations = formations;
    }

    public long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void setId(long id) {
        this.id = id;
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

    public Collection<Semestre> getSemestres() {
        return semestres;
    }

    public void setSemestres(Collection<Semestre> semestres) {
        this.semestres = semestres;
    }

    public Collection<Formation> getFormations() {
        return formations;
    }

    public void setFormations(Collection<Formation> formations) {
        this.formations = formations;
    }
}
