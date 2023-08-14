package cahier.text.gestioncahierdetexte.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Affecter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateAffectation;

    @ManyToOne
    private Enseignant enseignants;
    @ManyToOne
    private AtomePedagogique atomePedagogiques;
    @ManyToOne
    private Groupe groupe;
    @ManyToOne
    private Cohorte cohorte;
    @JsonIgnore
    @OneToMany(mappedBy = "affecter")
    private Collection<SeanceCours> seanceCours;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public Enseignant getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(Enseignant enseignants) {
        this.enseignants = enseignants;
    }

    public AtomePedagogique getAtomePedagogiques() {
        return atomePedagogiques;
    }

    public void setAtomePedagogiques(AtomePedagogique atomePedagogiques) {
        this.atomePedagogiques = atomePedagogiques;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Cohorte getCohorte() {
        return cohorte;
    }

    public void setCohorte(Cohorte cohorte) {
        this.cohorte = cohorte;
    }

    public Collection<SeanceCours> getSeanceCours() {
        return seanceCours;
    }

    public void setSeanceCours(Collection<SeanceCours> seanceCours) {
        this.seanceCours = seanceCours;
    }
}
