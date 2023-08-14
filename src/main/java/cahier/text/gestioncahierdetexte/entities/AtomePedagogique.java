package cahier.text.gestioncahierdetexte.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class AtomePedagogique {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String libelle;
	private Integer volumeHoraire;
	private Integer nbreHeureEffectue;
	private Integer nbreHeureRestant;
	@ManyToOne
    @JoinColumn(name="ec_id", nullable=false)
	private EC ec;
	@JsonIgnore
	@OneToMany(mappedBy = "atomePedagogiques")
	private Collection<Affecter> affecterCollection;
}
