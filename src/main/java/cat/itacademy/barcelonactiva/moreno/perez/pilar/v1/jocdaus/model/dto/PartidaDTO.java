package cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidaDTO {

	private Integer id;
	
	private int tirada1;
	private int tirada2;
	private int puntuacio;
	private UsuariDTO usuariDTO;
	
	//Retorna un número aleatorio del 1 al 6
	public int numAleatori() {
		int numero = (int)(Math.random()*6+1);
		return numero;
	}
}
