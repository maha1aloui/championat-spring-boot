package tn.esprit.ds.championat.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.List;

@Schema(name = "Equipe", description = "Entité représentant une équipe du championnat")
@Entity
@Table(name = "equipe")
public class Equipe {

    @Schema(description = "Identifiant unique de l'équipe (auto-généré)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    @Schema(description = "Nom de l'équipe", example = "Red Bull Racing", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nom;

    @Schema(description = "Pays d'origine de l'équipe", example = "Autriche")
    private String pays;

    @Schema(description = "Nom du directeur technique", example = "Adrian Newey")
    private String directeurTechnique;

    @Schema(description = "Année de fondation de l'équipe", example = "2005")
    private Integer anneeFondation;

    @JsonIgnore
    @Schema(description = "Liste des pilotes de l'équipe", accessMode = Schema.AccessMode.READ_ONLY)
    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Pilote> pilotes;

    // 🔹 Constructeurs
    public Equipe() {
    }

    public Equipe(String nom, String pays, String directeurTechnique, Integer anneeFondation) {
        this.nom = nom;
        this.pays = pays;
        this.directeurTechnique = directeurTechnique;
        this.anneeFondation = anneeFondation;
    }

    // 🔹 Getters & Setters
    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
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

    public String getDirecteurTechnique() {
        return directeurTechnique;
    }

    public void setDirecteurTechnique(String directeurTechnique) {
        this.directeurTechnique = directeurTechnique;
    }

    public Integer getAnneeFondation() {
        return anneeFondation;
    }

    public void setAnneeFondation(Integer anneeFondation) {
        this.anneeFondation = anneeFondation;
    }

    public List<Pilote> getPilotes() {
        return pilotes;
    }

    public void setPilotes(List<Pilote> pilotes) {
        this.pilotes = pilotes;
    }
}