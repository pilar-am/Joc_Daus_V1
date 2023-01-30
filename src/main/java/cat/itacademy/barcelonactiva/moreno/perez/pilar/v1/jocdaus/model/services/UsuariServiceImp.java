package cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.domain.Usuari;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.dto.UsuariDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.repository.UsuariRepository;

@Service
public class UsuariServiceImp  implements UsuariService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UsuariRepository usuariRepository;
	
	//Retorna todos los usuariosDTO
	@Override
	public List<UsuariDTO> llistaUsuaris() {
		return usuariRepository.findAll()
				.stream()
        		.map(this::convertEntityToDto)
        		.collect(Collectors.toList());
	}

	//Retorna un usuarioDTO según su id
	@Override
	public UsuariDTO getUsuariById(int id) {
		Usuari usuari = usuariRepository.findById(id).orElseThrow();
		UsuariDTO usuariDTO = modelMapper.map(usuari, UsuariDTO.class);
		return usuariDTO;
	}

	//Guarda un usuario(nombre)
	@Override
	public void saveUsuariDTO(UsuariDTO usuariDTO) {
		Usuari usuari = new Usuari();
		usuari.setNom(usuariDTO.getNom());
		usuariRepository.save(usuari);
	}

	//Actualiza el usuario(nombre) y retorna usuariDTO
	@Override
	public UsuariDTO updateUsuariDTO(UsuariDTO usuariDTO, int id) {
		Usuari usuari = usuariRepository.findById(id).orElseThrow();
		usuari.setNom(usuariDTO.getNom());
		Usuari usuariActualitzat = usuariRepository.save(usuari);
		return convertEntityToDto(usuariActualitzat);
	}

	//Elimina usuario según su id   No endpoint?
	@Override
	public void deleteUsuariDTO(int id) {		
		Usuari usuari = usuariRepository.findById(id).orElseThrow();
		usuariRepository.delete(usuari);
	}

	//Retorna una lista de todos los usuarios con el ranking actualizado. Ranking sólo en DTO
	@Override
	public List<UsuariDTO> usuarisAmbRanking() {
		List<UsuariDTO> usuaris = usuariRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
		
		for(UsuariDTO u:usuaris) {
			u.rankingJugador();
		}
		return usuaris;
	}
	
	//Recoge todos los usuarios, los ordena según su ranking y retorna el último usuario de la lista
	@Override
	public UsuariDTO getLoser() {
		List<UsuariDTO> usuaris = usuariRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
				
		for(UsuariDTO u:usuaris) {
			u.rankingJugador();
		}
		Collections.sort(usuaris);
		UsuariDTO usuariDTO = usuaris.get(usuaris.size()-1);
		return usuariDTO;
	}
	
	//Recoge todos los usuarios, los ordena según su ranking y retorna el primer usuario de la lista
	@Override
	public UsuariDTO getWinner() {
		List<UsuariDTO> usuaris = usuariRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
				
		for(UsuariDTO u:usuaris) {
			u.rankingJugador();
		}
		Collections.sort(usuaris);
		UsuariDTO usuariDTO = usuaris.get(0);
		return usuariDTO;
	}
	
	//De Entity a DTO
	private UsuariDTO convertEntityToDto(Usuari usuari) {
		UsuariDTO usuariDTO = modelMapper.map(usuari, UsuariDTO.class);
		return usuariDTO;
	}

}
