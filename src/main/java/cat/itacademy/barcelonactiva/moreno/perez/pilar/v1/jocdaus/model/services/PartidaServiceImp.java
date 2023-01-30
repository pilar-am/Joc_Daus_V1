package cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.domain.Partida;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.domain.Usuari;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.dto.PartidaDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.dto.UsuariDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.repository.PartidaRepository;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.repository.UsuariRepository;

@Service
public class PartidaServiceImp implements PartidaService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	PartidaRepository partidaRepository;
	
	@Autowired
	UsuariRepository usuariRepository;
	
	
	//Retorna todas las partidas   no endpoint??
	@Override
	public List<PartidaDTO> llistaPartides() {
		return partidaRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	//Retorna una partida según su id   no endpoint??
	@Override
	public PartidaDTO getPartidaById(int id) {
		Partida partida = partidaRepository.findById(id).orElseThrow();
		PartidaDTO partidaDTO = modelMapper.map(partida, PartidaDTO.class);

		return partidaDTO;
	}

	//Guarda una partida
	@Override
	public void savePartidaDTO(PartidaDTO partidaDTO) {
		
		int tirada1 = partidaDTO.numAleatori();
		int tirada2 = partidaDTO.numAleatori();
		
		partidaDTO.setTirada1(tirada1);
		partidaDTO.setTirada2(tirada2);
		partidaDTO.setPuntuacio(tirada1+tirada2);
		
		Partida partida = new Partida();
		
		partida.setTirada1(partidaDTO.getTirada1());
		partida.setTirada2(partidaDTO.getTirada2());
		partida.setPuntuacio(partidaDTO.getPuntuacio());
		
		Usuari usuari = modelMapper.map(partidaDTO.getUsuariDTO(), Usuari.class);
		
		partida.setUsuari(usuari);
				
		modelMapper.map(partidaDTO.getUsuariDTO(), Usuari.class);
		partidaRepository.save(partida);
	}

	//Actualiza una partida   no endpoint??
	@Override
	public PartidaDTO updatePartidaDTO(PartidaDTO partidaDTO, int id) {
		
		Partida partida = partidaRepository.findById(id).orElseThrow();
		
		partida.setTirada1(partidaDTO.getTirada1());
		partida.setTirada2(partidaDTO.getTirada2());
		partida.setPuntuacio(partidaDTO.getPuntuacio());
		//partida.setUsuari(partidaDTO.getUsuariDTO());
		
		Partida partidaActualitzada = partidaRepository.save(partida);
		
		return convertEntityToDto(partidaActualitzada);
		
	}

	//Elimina una partida   no endpoint??
	@Override
	public void deletePartidaDTO(int id) {
		Partida partida = partidaRepository.findById(id).orElseThrow();
		partidaRepository.delete(partida);
		
	}

	//Retorna las partidas de un usuario según su id
	@Override
	public List<PartidaDTO> llistaPartidesByUsuari(int id) {
		Usuari usuari = usuariRepository.findById(id).orElseThrow();
		UsuariDTO usuariDTO = modelMapper.map(usuari, UsuariDTO.class);
		List<PartidaDTO> partides = usuariDTO.getPartidas();
		
		return partides;
	}
	
	//Elimina las partidas de un usuario según su id
	@Override
	public boolean deletePartidesByUsuari(int id) {
		List<Partida> partides = partidaRepository.findAll();
		boolean hayId = false;
		for(Partida p: partides) {
			if(p.getUsuari().getId() == id) {
				hayId=true;
				partidaRepository.delete(p);
			}
		}
		return hayId;
	}
	
	//De Entity a DTO
	private PartidaDTO convertEntityToDto(Partida partida) {
		PartidaDTO partidaDTO = modelMapper.map(partida, PartidaDTO.class);
		return partidaDTO;
	}

}
