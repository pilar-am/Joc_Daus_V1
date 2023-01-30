package cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partides")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private int tirada1;
	private int tirada2;
	private int puntuacio;
		
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="usuari_id")
	@JsonProperty(access = Access.WRITE_ONLY) //para evitar lazyinicialitionException
	private Usuari usuari;
	
	
}
