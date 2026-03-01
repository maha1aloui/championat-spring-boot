package tn.esprit.ds.championat.services;

import tn.esprit.ds.championat.entities.Equipe;

import java.util.List;

public interface IEquipeService {
    Equipe ajouterEquipe(Equipe equipe);

    List<Equipe> listEquipes();

    Equipe recupererEquipe(Long idEquipe);

    Equipe modifierEquipe(Equipe equipe);

    void supprimerEquipe(Long idEquipe);
}