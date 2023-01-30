package cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v1.jocdaus.model.domain.Usuari;

@Repository
public interface UsuariRepository extends JpaRepository<Usuari, Integer>{

}
