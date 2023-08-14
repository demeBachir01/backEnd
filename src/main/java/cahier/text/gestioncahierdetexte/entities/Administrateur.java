package cahier.text.gestioncahierdetexte.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn( name = "id" )
public class Administrateur extends Individu{
	

}
