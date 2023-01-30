package cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.dto.UsuariDTO;

@Service
public interface UsuariService {

	List<UsuariDTO> llistaUsuaris();
	UsuariDTO getUsuariById(int id);
	void saveUsuariDTO(UsuariDTO usuariDTO);
	UsuariDTO updateUsuariDTO(UsuariDTO usuariDTO, int id);
	void deleteUsuariDTO(int id); //no endpoint?
	List<UsuariDTO> usuarisAmbRanking();
	UsuariDTO getLoser();
	UsuariDTO getWinner();
	
}
