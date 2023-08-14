package cahier.text.gestioncahierdetexte.entities;

import  java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String libelle;
    private boolean estValide;
    @JsonIgnore
    @OneToMany(mappedBy = "formation")
    private Collection<Parcours> parcours;
    @ManyToOne
    @JoinColumn(name = "domaine_id", nullable = false)
    private Domaine domaine;
    @ManyToOne
    @JoinColumn(name = "anneeScolaire_id", nullable = false)
    private AnneeScolaire anneeScolaire;
    @ManyToOne
    @JoinColumn(name = "mention_id", nullable = false)
    private Mention mention;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "specialite_id", nullable = true)
    private Specialite specialite;
    @ManyToOne
    @JoinColumn(name = "niveau_id", nullable = false)
    private Niveau niveau;
    @ManyToMany(mappedBy = "formations")
    Collection<Departement> departements;
    @OneToMany (mappedBy = "formation")
    private Collection<ResponsableDeFormation>  responsableDeFormation;
    @JsonIgnore
    @OneToMany(mappedBy="formation")
    private Collection<Inscription> inscription;
    @ManyToMany
    private Collection<Enseignant> enseignants;
    public Formation() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Formation(Long id, String code, String libelle, boolean estValide, Collection<Parcours> parcours,
             Domaine domaine, Mention mention, Specialite specialite, Niveau niveau,
             Collection<Departement> departements) {
        super();
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.estValide = estValide;
        this.parcours = parcours;
        this.domaine = domaine;
        this.mention = mention;
        this.specialite = specialite;
        this.niveau = niveau;
        this.departements = departements;
    }

    public Formation(Long id, String code, String libelle, boolean estValide, Collection<Parcours> parcours,
            Domaine domaine, Mention mention, Niveau niveau,
            Collection<Departement> departements) {
        super();
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.estValide = estValide;
        this.parcours = parcours;
        this.domaine = domaine;
        this.mention = mention;
        this.niveau = niveau;
        this.departements = departements;
    }

    public Collection<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(Collection<Departement> departements) {
        this.departements = departements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Collection<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(Collection<Parcours> parcours) {
        this.parcours = parcours;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public boolean getEstValide() {
        return estValide;
    }

    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public Mention getMention() {
        return mention;
    }

    public void setMention(Mention mention) {
        this.mention = mention;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Formation(Long id, String code, String libelle, boolean estValide, Collection<Parcours> parcours, Domaine domaine, AnneeScolaire anneeScolaire, Mention mention, Specialite specialite, Niveau niveau, Collection<Departement> departements, Collection<ResponsableDeFormation> responsableDeFormation, Collection<Inscription> inscription, Collection<Enseignant> enseignants) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.estValide = estValide;
        this.parcours = parcours;
        this.domaine = domaine;
        this.anneeScolaire = anneeScolaire;
        this.mention = mention;
        this.specialite = specialite;
        this.niveau = niveau;
        this.departements = departements;
        this.responsableDeFormation = responsableDeFormation;
        this.inscription = inscription;
        this.enseignants = enseignants;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public AnneeScolaire getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public Collection<ResponsableDeFormation> getResponsableDeFormation() {
        return responsableDeFormation;
    }

    public void setResponsableDeFormation(Collection<ResponsableDeFormation> responsableDeFormation) {
        this.responsableDeFormation = responsableDeFormation;
    }

    public Collection<Inscription> getInscription() {
        return inscription;
    }

    public void setInscription(Collection<Inscription> inscription) {
        this.inscription = inscription;
    }

    public Collection<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(Collection<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }
}
