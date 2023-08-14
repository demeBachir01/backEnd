package cahier.text.gestioncahierdetexte.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ufr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String libelleLong;
    private String libelleCourt;

    @JsonIgnore
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "ufr")
    private Collection<DirecteurUfr> directeursUfr;

    @JsonIgnore
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "ufr")
    private Collection<Departement> departements;

//    @JsonIgnore
//    @OneToMany(mappedBy = "ufr")
//    private Collection<EstResponsableFormation> estResponsablePs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelleLong() {
        return libelleLong;
    }

    public void setLibelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
    }

    public String getLibelleCourt() {
        return libelleCourt;
    }

    public void setLibelleCourt(String libelleCourt) {
        this.libelleCourt = libelleCourt;
    }

    public Collection<DirecteurUfr> getDirecteursUfr() {
        return directeursUfr;
    }

    public void setDirecteursUfr(Collection<DirecteurUfr> directeursUfr) {
        this.directeursUfr = directeursUfr;
    }


    public Collection<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(Collection<Departement> departements) {
        this.departements = departements;
    }

    public Ufr() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Ufr(long id, String libelleLong, String libelleCourt, Collection<DirecteurUfr> directeursUfr,
            Collection<Departement> departements) {
        super();
        this.id = id;
        this.libelleLong = libelleLong;
        this.libelleCourt = libelleCourt;
        this.directeursUfr = directeursUfr;
        this.departements = departements;
    }

    public Ufr(long id, String libelleLong, String libelleCourt, Collection<DirecteurUfr> directeursUfr,
            Collection<Departement> departements, Collection<EstResponsableFormation> estResponsablePs) {
        super();
        this.id = id;
        this.libelleLong = libelleLong;
        this.libelleCourt = libelleCourt;
        this.directeursUfr = directeursUfr;
        this.departements = departements;
    }

}
