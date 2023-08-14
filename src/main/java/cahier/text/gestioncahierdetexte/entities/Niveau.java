package cahier.text.gestioncahierdetexte.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Niveau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int numero;
    private int niveauCycle;
    private String libelle;
    private String cycle;
    private int numCycle;

    @JsonIgnore
    @OneToMany(mappedBy = "niveau")
    private Collection<Formation> formations;

    public Niveau() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Niveau(long id, int numero, int niveauCycle, String libelle, String cycle, int numCycle,
            Collection<Formation> formations) {
        super();
        this.id = id;
        this.numero = numero;
        this.niveauCycle = niveauCycle;
        this.libelle = libelle;
        this.cycle = cycle;
        this.numCycle = numCycle;
        this.formations = formations;
    }

    public int getNumCycle() {
        return numCycle;
    }

    public void setNumCycle(int numCycle) {
        this.numCycle = numCycle;
    }

    public Collection<Formation> getFormations() {
        return formations;
    }

    public void setFormations(Collection<Formation> formations) {
        this.formations = formations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNiveauCycle() {
        return niveauCycle;
    }

    public void setNiveauCycle(int niveauCycle) {
        this.niveauCycle = niveauCycle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

}
