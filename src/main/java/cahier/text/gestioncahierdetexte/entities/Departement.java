package cahier.text.gestioncahierdetexte.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String libelle;
    private String description;

    @JsonIgnore
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "departement")
    private Collection<ChefDepartement> chefDepartements;

    @JsonIgnore
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "departement")
    private Collection<Enseignant> enseignants;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "formationDepartement",
            joinColumns = @JoinColumn(name = "departement_id"),
            inverseJoinColumns = @JoinColumn(name = "formation_id"))
    Collection<Formation> formations;

    @ManyToOne
    @JoinColumn(name = "ufr_id", nullable = false)
    private Ufr ufr;

    public Collection<ChefDepartement> getChefDepartements() {
        return chefDepartements;
    }

    public void setChefDepartements(Collection<ChefDepartement> chefDepartements) {
        this.chefDepartements = chefDepartements;
    }

    public Collection<Formation> getFormations() {
        return formations;
    }

    public void setFormations(Collection<Formation> formations) {
        this.formations = formations;
    }

    public void addFormation(Formation f) {
        this.formations.add(f);
        f.getDepartements().add(this);
    }

    public void removeFormation(long fId) {
        Formation form = this.formations.stream().filter(f -> f.getId() == fId).findFirst().orElse(null);
        if (form != null) {
            this.formations.remove(form);
            form.getDepartements().remove(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ufr getUfr() {
        return ufr;
    }

    public void setUfr(Ufr ufr) {
        this.ufr = ufr;
    }

    public Collection<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(Collection<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public Departement(long id, String libelle, String description, Collection<ChefDepartement> chefDepartements,
            Collection<Enseignant> enseignants, Collection<Formation> formations, Ufr ufr) {
        super();
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.chefDepartements = chefDepartements;
        this.enseignants = enseignants;
        this.formations = formations;
        this.ufr = ufr;
    }

    public Departement() {
        super();
        // TODO Auto-generated constructor stub
    }

}
