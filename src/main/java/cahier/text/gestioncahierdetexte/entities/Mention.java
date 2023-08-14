package cahier.text.gestioncahierdetexte.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Mention {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String libelle;

	@JsonIgnore
	@OneToMany(mappedBy="mention")
    private Collection<Formation> formations;


	public Mention() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Mention(long id, String libelle, Collection<Formation> formations) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.formations = formations;
	}


	public String getLibelle() {
		return libelle;
	}



	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Collection<Formation> getFormations() {
		return formations;
	}


	public void setFormations(Collection<Formation> formations) {
		this.formations = formations;
	}

	
}
