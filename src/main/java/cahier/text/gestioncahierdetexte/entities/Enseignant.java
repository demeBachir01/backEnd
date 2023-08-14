package cahier.text.gestioncahierdetexte.entities;

import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Enseignant extends Individu {

    private boolean estPermanent;
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Departement departement;


    @JsonIgnore
    @OneToMany(mappedBy = "enseignant")
    private Collection<DirecteurUfr> directeurUfrs;

    @JsonIgnore
    @OneToMany(mappedBy = "enseignant")
    private Collection<ChefDepartement> chefDepartements;

    @JsonIgnore
    @OneToMany(mappedBy = "enseignant")
    private Collection<EstViceRecteur> viceRecteurs;

	@JsonIgnore
	@OneToMany(mappedBy = "enseignant")
	private Collection<EstResponsableFormation> responsableFormations;
	@JsonIgnore
	@OneToMany(mappedBy = "enseignants")
	private Collection<Affecter> affecter;
	@ManyToMany
	private Collection<Formation> formations;

	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enseignant(long id, String nom, String prenom, int tel) {
		super(id, nom, prenom, tel);
		// TODO Auto-generated constructor stub
	}



	public Enseignant(boolean estPermanent, Departement departement,
			Collection<ChefDepartement> chefDepartements, Collection<EstViceRecteur> viceRecteurs) {
		super();
		this.estPermanent = estPermanent;
		this.departement = departement;
		this.directeurUfrs = directeurUfrs;
		this.chefDepartements = chefDepartements;
		this.viceRecteurs = viceRecteurs;
	}

	public boolean isEstPermanent() {
		return estPermanent;
	}

	public void setEstPermanent(boolean estPermanent) {
		this.estPermanent = estPermanent;
	}


	public Collection<DirecteurUfr> getDirecteurUfrs() {
		return directeurUfrs;
	}

	public void setDirecteurUfrs(Collection<DirecteurUfr> directeurUfrs) {
		this.directeurUfrs = directeurUfrs;
	}

	public Collection<EstViceRecteur> getViceRecteurs() {
		return viceRecteurs;
	}

	public void setViceRecteurs(Collection<EstViceRecteur> viceRecteurs) {
		this.viceRecteurs = viceRecteurs;
	}

	public Collection<ChefDepartement> getChefDepartements() {
		return chefDepartements;
	}

	public void setChefDepartements(Collection<ChefDepartement> chefDepartements) {
		this.chefDepartements = chefDepartements;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	
}

