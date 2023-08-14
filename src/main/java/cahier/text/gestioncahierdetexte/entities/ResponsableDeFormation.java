package cahier.text.gestioncahierdetexte.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class ResponsableDeFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateDebut;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateFin;

    private int encours;
    @ManyToOne
    @JoinColumn(name="enseignant_id", nullable=false)
    private Enseignant enseignant;


    @ManyToOne
    @JoinColumn(name="formation_id", nullable=false)
    private Formation formation;

    @JsonIgnore
    @OneToMany(mappedBy = "responsableDeFormation")
    private Collection<EstResponsableFormation> estResponsable;


}
