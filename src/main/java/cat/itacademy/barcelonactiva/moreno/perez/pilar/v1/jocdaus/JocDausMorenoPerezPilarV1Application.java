package cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/*
El joc de daus s’hi juga amb dos daus. En cas que el resultat de la suma dels dos daus sigui 7, 
la partida és guanyada, si no és perduda. Un jugador/a pot  veure un llistat de totes les tirades 
que ha fet i el percentatge d’èxit.   

Per poder jugar al joc i realitzar una tirada, un usuari/ària  s’ha de registrar amb un nom no repetit. 
En crear-se, se li assigna un identificador numèric únic i una data de registre. Si l’usuari/ària així ho desitja, 
pot no afegir cap nom i es  dirà “ANÒNIM”. Pot haver-hi més d’un jugador “ANÒNIM”.  

Cada jugador/a pot veure un llistat de totes les  tirades que ha fet, amb el valor de cada dau i si s’ha  
guanyat o no la partida. A més, pot saber el seu percentatge d’èxit per totes les tirades  que ha fet.    

No es pot eliminar una partida en concret, però sí que es pot eliminar tot el llistat de tirades per un jugador/a.  

El software ha de permetre llistar tots els jugadors/es que hi ha al sistema, el percentatge d’èxit 
de cada jugador/a i el  percentatge d’èxit mitjà de tots els jugadors/es en el sistema.   

 */

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Joc de Daus", version = "1.0", description = "API on es gestiona un joc de daus, amb usuaris i partides del usuaris"))
public class JocDausMorenoPerezPilarV1Application {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JocDausMorenoPerezPilarV1Application.class, args);
				
		
	}	
	
	//http://localhost:9000/v3/api-docs  para el json de swagger
	//http://localhost:9000/swagger-ui/index.html  para la interfaz
}
