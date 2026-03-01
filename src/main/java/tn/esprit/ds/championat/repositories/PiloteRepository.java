package tn.esprit.ds.championat.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.ds.championat.entities.Pilote;

public interface PiloteRepository extends JpaRepository<Pilote, Long> {
}