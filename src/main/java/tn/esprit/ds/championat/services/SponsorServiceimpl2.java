package tn.esprit.ds.championat.services;

import tn.esprit.ds.championat.entities.Sponsor;
import tn.esprit.ds.championat.repositories.sponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SponsorServiceimpl2 implements ISponsorService {

    @Autowired
    private sponsorRepository sponsorRepository;

    @Override
    public Sponsor ajouterSponsor(Sponsor sponsor) {
        // Initialiser les valeurs selon les exigences
        sponsor.setDateCreation(LocalDate.now()); // Date système
        sponsor.setArchived(false); // Initialisé à false
        sponsor.setBloquerContrat(false); // Initialisé à false

        return sponsorRepository.save(sponsor);
    }

    @Override
    public List<Sponsor> ajouterSponsors(List<Sponsor> sponsors) {
        // Initialiser les valeurs pour chaque sponsor
        for (Sponsor sponsor : sponsors) {
            sponsor.setDateCreation(LocalDate.now()); // Date système
            sponsor.setArchived(false); // Initialisé à false
            sponsor.setBloquerContrat(false); // Initialisé à false
        }

        return sponsorRepository.saveAll(sponsors);
    }

    @Override
    @Transactional
    public Sponsor modifierSponsor(Sponsor sponsor) {
        // Mettre à jour la date de dernière modification
        sponsor.setDateDerniereModification(LocalDate.now()); // Date système

        return sponsorRepository.save(sponsor);
    }

    @Override
    @Transactional
    public void supprimerSponsor(Long idSponsor) {
        sponsorRepository.deleteById(idSponsor);
    }

    @Override
    public List<Sponsor> listSponsors() {
        return sponsorRepository.findAll();
    }

    @Override
    public Sponsor recupererSponsor(Long idSponsor) {
        Optional<Sponsor> sponsorOptional = sponsorRepository.findById(idSponsor);
        return sponsorOptional.orElse(null);
    }

    @Override
    @Transactional
    public Boolean archiverSponsor(Long idSponsor) {
        Optional<Sponsor> sponsorOptional = sponsorRepository.findById(idSponsor);

        if (sponsorOptional.isPresent()) {
            Sponsor sponsor = sponsorOptional.get();
            sponsor.setArchived(true); // Mettre archived à true
            sponsorRepository.save(sponsor);
            return true;
        }

        return false;
    }
}