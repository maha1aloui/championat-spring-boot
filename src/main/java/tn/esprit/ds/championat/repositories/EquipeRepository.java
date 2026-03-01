package tn.esprit.ds.championat.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.ds.championat.entities.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}