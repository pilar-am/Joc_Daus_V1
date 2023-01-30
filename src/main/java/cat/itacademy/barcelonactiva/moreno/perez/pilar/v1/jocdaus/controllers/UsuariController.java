package cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.dto.UsuariDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.services.UsuariService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class UsuariController {

	@Autowired
	UsuariService usuariService;
	
	//GET: Retorna el llistat de tots  els jugadors/es del sistema amb el seu  percentatge mitjà d’èxits. 
	@Operation(summary = "Retorna la llista dels usuaris")
	@GetMapping("/players")	
	public ResponseEntity<List<UsuariDTO>> getAllUsers(){
		try {
			List<UsuariDTO> usuaris = usuariService.usuarisAmbRanking();
			if(usuaris.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<List<UsuariDTO>>(usuaris, HttpStatus.OK);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	//Retorna un jugador segons el seu id  No es necesita
	@Operation(summary = "Retorna un jugador segons el seu id")
	@GetMapping("/players/{id}")
	public ResponseEntity<UsuariDTO> getUsuariById(@PathVariable("id") Integer id){
		try {
			UsuariDTO usuariDTO = usuariService.getUsuariById(id);
			return new ResponseEntity<>(usuariDTO, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} 
	}
	
	//POST: Crea un jugador/a. 
	@Operation(summary = "Un jugador es pot inscriure amb el seu nom o de forma anònima")
	@PostMapping("/players")
	public ResponseEntity<?> crearUsuari(@RequestBody UsuariDTO usuariDTO){
		try {
			
			if(usuariDTO.getNom() == null) {
				usuariDTO.setNom("ANÒNIM");
			}
			boolean repetit = false;
			List<UsuariDTO> usuaris = usuariService.llistaUsuaris();
			for(UsuariDTO u:usuaris) {
				if(u.getNom().equals(usuariDTO.getNom()) && !u.getNom().equals("ANÒNIM")) {
					repetit=true;
				}
			}
			if(repetit) {
				return new ResponseEntity<>("Nom duplicat", HttpStatus.INTERNAL_SERVER_ERROR);
			}else {
				usuariService.saveUsuariDTO(usuariDTO);
				return new ResponseEntity<>(usuariDTO, HttpStatus.CREATED);
			}
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//PUT: Modifica el nom del jugador/a.
	@Operation(summary = "Modifica el nom del jugador")
	@PutMapping("/players/{id}")
	public ResponseEntity<UsuariDTO> updateUsuari(@PathVariable("id") Integer id, @RequestBody UsuariDTO usuariDTO){
		try {
			UsuariDTO usuari =  usuariService.updateUsuariDTO(usuariDTO, id);
			return new ResponseEntity<>(usuari, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	//Elimina el jugador  No es necessita
	@Operation(summary = "Elimina el jugador")
	@DeleteMapping("/players/{id}")
    public ResponseEntity<String> deleteUsuari(@PathVariable("id") Integer id){
    	try {
    		usuariService.deleteUsuariDTO(id);
    		return new ResponseEntity<>("Usuari eliminat", HttpStatus.OK);
    	}catch (Exception e) {
    		return new ResponseEntity<>("No existeix l'id",HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	List<String> rankingUsuaris = new ArrayList<>();
	
	//GET: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits. 
	@Operation(summary = "Retorna el ranking mig dels jugadors")
	@GetMapping("/players/ranking")
	public ResponseEntity<List<String>> retornaRanking(){
		try {
			List<UsuariDTO> usuaris = usuariService.usuarisAmbRanking();
			if(usuaris.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				Collections.sort(usuaris);
				rankingUsuaris.clear();
				for(UsuariDTO u:usuaris) {
					rankingUsuaris.add(u.getNom() + ": " + u.getRanking() + "%");
				}
				return new ResponseEntity<>(rankingUsuaris, HttpStatus.OK);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
				
	}
	//GET: Retorna el jugador/a  amb pitjor percentatge d’èxit.  
	@Operation(summary = "Retorna el jugador/a amb pitjor percentatge d'èxit")
	@GetMapping("/players/ranking/loser")
	public ResponseEntity<UsuariDTO> getLoser(){
		try {
			UsuariDTO usuariDTO = usuariService.getLoser();
			return new ResponseEntity<>(usuariDTO, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} 
	}
	
	//GET: Retorna el jugador/a  amb millor percentatge d’èxit.  
	@Operation(summary = "Retorna el jugador/a  amb millor percentatge d’èxit")
	@GetMapping("/players/ranking/winner")
	public ResponseEntity<UsuariDTO> getWinner(){
		try {
			UsuariDTO usuariDTO = usuariService.getWinner();
			return new ResponseEntity<>(usuariDTO, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} 
	}
	
}
