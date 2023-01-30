package cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuaris")//(name = "usuaris", uniqueConstraints = {@UniqueConstraint(columnNames = {"nom"})})   Nom únic
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuari {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private String nom;
	
	
	private LocalDate dataRegistre = LocalDate.now();
	
	
	@OneToMany(mappedBy = "usuari", cascade = CascadeType.ALL)
	private List<Partida> partidas;

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
		for(Partida p: partidas) {
			p.setUsuari(this);
		}
	}
	
	
}
