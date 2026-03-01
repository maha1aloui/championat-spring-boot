package tn.esprit.ds.championat.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Schema(name = "Sponsor", description = "Entité représentant un sponsor du championnat")
@Entity
@Table(name = "sponsor")
public class Sponsor {

    @Schema(description = "Identifiant unique du sponsor (auto-généré)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSponsor;

    @Schema(description = "Liste des contrats associés à ce sponsor", accessMode = Schema.AccessMode.READ_ONLY)
    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL)
    private List<Championnal.Contrat> contrats;

    @Schema(description = "Nom commercial du sponsor", example = "Red Bull Racing", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nom;

    @Schema(description = "Pays d'origine du sponsor", example = "Autriche")
    private String pays;

    @Schema(description = "Budget annuel alloué en euros", example = "5000000.0")
    private Float budgetAnnuel;

    @Schema(description = "Indique si les contrats sont bloqués (initialisé à false)", example = "false", accessMode = Schema.AccessMode.READ_ONLY)
    private Boolean bloquerContrat;

    @Schema(description = "Indique si le sponsor est archivé (initialisé à false)", example = "false", accessMode = Schema.AccessMode.READ_ONLY)
    private Boolean archived;

    @Schema(description = "Date de création du sponsor (initialisée automatiquement)", example = "2026-03-01", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDate dateCreation;

    @Schema(description = "Date de la dernière modification (mise à jour automatiquement)", example = "2026-03-01", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDate dateDerniereModification;

    // 🔹 Constructeurs
    public Sponsor() {
    }

    public Sponsor(String nom, String pays, Float budgetAnnuel, Boolean bloquerContrat) {
        this.nom = nom;
        this.pays = pays;
        this.budgetAnnuel = budgetAnnuel;
        this.bloquerContrat = bloquerContrat;
    }

    // 🔹 Getters & Setters
    public Long getIdSponsor() {
        return idSponsor;
    }

    public void setIdSponsor(Long idSponsor) {
        this.idSponsor = idSponsor;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Float getBudgetAnnuel() {
        return budgetAnnuel;
    }

    public void setBudgetAnnuel(Float budgetAnnuel) {
        this.budgetAnnuel = budgetAnnuel;
    }

    public Boolean getBloquerContrat() {
        return bloquerContrat;
    }

    public void setBloquerContrat(Boolean bloquerContrat) {
        this.bloquerContrat = bloquerContrat;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateDerniereModification() {
        return dateDerniereModification;
    }

    public void setDateDerniereModification(LocalDate dateDerniereModification) {
        this.dateDerniereModification = dateDerniereModification;
    }

    public List<Championnal.Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Championnal.Contrat> contrats) {
        this.contrats = contrats;
    }
}