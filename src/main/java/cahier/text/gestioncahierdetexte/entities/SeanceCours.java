package cahier.text.gestioncahierdetexte.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.Date;
import java.util.Timer;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class SeanceCours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeance;
    private String contenue;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateSeance;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private String heureDeb;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private String heureFin;
    private boolean valider=false;
    private String commentaire="";
    private Integer numSeance;
    @ManyToOne
    private Affecter affecter;
}

