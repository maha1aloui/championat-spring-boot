package tn.esprit.ds.championat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.ds.championat.entities.Sponsor;

import java.util.List;

public interface sponsorRepository extends JpaRepository<Sponsor,Long> {



}
