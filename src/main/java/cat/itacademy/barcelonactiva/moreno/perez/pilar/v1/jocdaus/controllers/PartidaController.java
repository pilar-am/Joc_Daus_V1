package cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.dto.PartidaDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.dto.UsuariDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.services.PartidaService;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.services.UsuariService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class PartidaController {
	
	@Autowired
	PartidaService partidaService;
	
	@Autowired
	UsuariService usuariService;
	
	//POST: Un jugador/a específic realitza una tirada dels daus.  
	@Operation(summary = "Un jugador/a específic realitza una tirada dels daus")
	@PostMapping("/players/{id}/games")
	public ResponseEntity<String> partidaJugador(@PathVariable Integer id, @RequestBody PartidaDTO partidaDTO){
				
		UsuariDTO usuari = usuariService.getUsuariById(id);
		
		if(usuari == null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}else {
			partidaDTO.setUsuariDTO(usuari);
			partidaService.savePartidaDTO(partidaDTO);
			return new ResponseEntity<>("Partida guardada",HttpStatus.CREATED);
		}
	}
	
	//GET:Retorna el llistat de jugades per un jugador/a.
	@Operation(summary = "Retorna el llistat de jugades per un jugador/a")
	@GetMapping("/players/{id}/games")
	public ResponseEntity<List<PartidaDTO>> partidesJugador(@PathVariable Integer id){
		
		List<PartidaDTO> partides = partidaService.llistaPartidesByUsuari(id);

		return new ResponseEntity<>(partides, HttpStatus.OK);
		
	}
	
	//DELETE: Elimina les tirades del jugador/a.
	@Operation(summary = "Elimina les tirades del jugador/a")
	@DeleteMapping("/players/{id}/games")
	public ResponseEntity<String> eliminarPartides(@PathVariable Integer id){
		try {
			boolean hayUsuari = partidaService.deletePartidesByUsuari(id);
			if(hayUsuari) {
				return new ResponseEntity<>("Partides eliminades",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("L'usuari no existeix", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
