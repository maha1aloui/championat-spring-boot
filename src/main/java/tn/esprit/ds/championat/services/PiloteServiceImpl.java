package tn.esprit.ds.championat.services;

import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championat.entities.Equipe;
import tn.esprit.ds.championat.entities.Pilote;
import tn.esprit.ds.championat.repositories.EquipeRepository;
import tn.esprit.ds.championat.repositories.PiloteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PiloteServiceImpl implements IPiloteService {

    @Autowired
    private PiloteRepository piloteRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public String addPilote(Pilote p) {
        Pilote savedPilote = piloteRepository.save(p);
        return "Pilote " + savedPilote.getNom() + " " + savedPilote.getPrenom()
                + " (Numéro: " + savedPilote.getNumeroVoiture() + ") ajouté avec succès!";
    }

    @Override
    public List<Pilote> listPilotes() {
        return piloteRepository.findAll();
    }

    @Override
    public Pilote recupererPilote(Long idPilote) {
        return piloteRepository.findById(idPilote).orElse(null);
    }

    @Override
    @Transactional
    public Pilote modifierPilote(Pilote pilote) {
        return piloteRepository.save(pilote);
    }

    @Override
    @Transactional
    public void supprimerPilote(Long idPilote) {
        piloteRepository.deleteById(idPilote);
    }

    @Override
    @Transactional
    public Pilote affecterPiloteAEquipe(Long idPilote, Long idEquipe) {
        Optional<Pilote> piloteOpt = piloteRepository.findById(idPilote);
        Optional<Equipe> equipeOpt = equipeRepository.findById(idEquipe);
        if (piloteOpt.isPresent() && equipeOpt.isPresent()) {
            Pilote pilote = piloteOpt.get();
            pilote.setEquipe(equipeOpt.get());
            return piloteRepository.save(pilote);
        }
        return null;
    }
}