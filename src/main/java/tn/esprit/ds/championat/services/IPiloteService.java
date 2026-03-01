package tn.esprit.ds.championat.services;

import tn.esprit.ds.championat.entities.Pilote;

import java.util.List;

public interface IPiloteService {
    String addPilote(Pilote p);

    List<Pilote> listPilotes();

    Pilote recupererPilote(Long idPilote);

    Pilote modifierPilote(Pilote pilote);

    void supprimerPilote(Long idPilote);

    Pilote affecterPiloteAEquipe(Long idPilote, Long idEquipe);
}