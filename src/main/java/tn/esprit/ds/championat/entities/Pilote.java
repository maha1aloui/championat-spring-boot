package tn.esprit.ds.championat.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalDate;

@Schema(name = "Pilote", description = "Entité représentant un pilote du championnat")
@Entity
@Table(name = "pilote")
public class Pilote {

    @Schema(description = "Identifiant unique du pilote (auto-généré)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPilote;

    @Schema(description = "Nom du pilote", example = "Verstappen", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nom;

    @Schema(description = "Prénom du pilote", example = "Max", requiredMode = Schema.RequiredMode.REQUIRED)
    private String prenom;

    @Schema(description = "Date de naissance du pilote", example = "1997-09-30")
    private LocalDate dateNaissance;

    @Schema(description = "Nationalité du pilote", example = "Néerlandais")
    private String nationalite;

    @Schema(description = "Numéro de voiture du pilote", example = "1")
    private Integer numeroVoiture;

    @Schema(description = "Équipe à laquelle appartient le pilote")
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    // 🔹 Constructeurs
    public Pilote() {
    }

    public Pilote(String nom, String prenom, LocalDate dateNaissance, String nationalite, Integer numeroVoiture) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.nationalite = nationalite;
        this.numeroVoiture = numeroVoiture;
    }

    // 🔹 Getters & Setters
    public Long getIdPilote() {
        return idPilote;
    }

    public void setIdPilote(Long idPilote) {
        this.idPilote = idPilote;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public Integer getNumeroVoiture() {
        return numeroVoiture;
    }

    public void setNumeroVoiture(Integer numeroVoiture) {
        this.numeroVoiture = numeroVoiture;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}