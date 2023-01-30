package cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.services;

import java.util.List;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.dto.PartidaDTO;

public interface PartidaService {

	List<PartidaDTO> llistaPartides();	//no endpoint??
	PartidaDTO getPartidaById(int id);	//no endpoint??
	void savePartidaDTO(PartidaDTO partidaDTO);
	PartidaDTO updatePartidaDTO(PartidaDTO partidaDTO, int id);  //no endpoint??
	void deletePartidaDTO(int id);	//no endpoint??
	List<PartidaDTO> llistaPartidesByUsuari(int id);
	public boolean deletePartidesByUsuari(int id);
}
