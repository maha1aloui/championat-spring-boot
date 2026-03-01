package tn.esprit.ds.championat.services;

import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championat.entities.Equipe;
import tn.esprit.ds.championat.repositories.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeServiceImpl implements IEquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public List<Equipe> listEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe recupererEquipe(Long idEquipe) {
        return equipeRepository.findById(idEquipe).orElse(null);
    }

    @Override
    @Transactional
    public Equipe modifierEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    @Transactional
    public void supprimerEquipe(Long idEquipe) {
        equipeRepository.deleteById(idEquipe);
    }
}