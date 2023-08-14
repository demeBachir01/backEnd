package cahier.text.gestioncahierdetexte.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Individu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private int tel;
    private long numCni;
    private String civilite;

    @JsonIgnore
    @OneToMany(mappedBy = "individu")
    private Collection<IndividuCompte> comptesIndividu;

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public long getId() {
        return id;
    }

    public Individu() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Individu(long id, String nom, String prenom, int tel) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
    }

    public long getNumCni() {
        return numCni;
    }

    public void setNumCni(long numCni) {
        this.numCni = numCni;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public Collection<IndividuCompte> getComptesIndividu() {
        return comptesIndividu;
    }

    public void setComptesIndividu(Collection<IndividuCompte> comptesIndividu) {
        this.comptesIndividu = comptesIndividu;
    }

}
