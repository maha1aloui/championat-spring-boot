package tn.esprit.ds.championat.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "championnal")
public class Championnal {

    public enum Categorie {
        JUNIOR,
        SENIOR,
        VETERAN
    }

    @OneToOne(mappedBy = "championnat", cascade = CascadeType.ALL)
    private DetailChampionnat detailChampionnat;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> courses;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChampionnal;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private String libelleC;

    private Integer annee;

    // 🔹 Constructeur vide (OBLIGATOIRE pour JPA)
    public Championnal() {
    }

    // 🔹 Constructeur avec paramètres
    public Championnal(Categorie categorie, String libelleC, Integer annee) {
        this.categorie = categorie;
        this.libelleC = libelleC;
        this.annee = annee;
    }

    // 🔹 Getters & Setters
    public Long getIdChampionnal() {
        return idChampionnal;
    }

    public void setIdChampionnal(Long idChampionnal) {
        this.idChampionnal = idChampionnal;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getLibelleC() {
        return libelleC;
    }

    public void setLibelleC(String libelleC) {
        this.libelleC = libelleC;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    @Entity
    @Table(name = "contrat")
    public static class Contrat {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idContrat;
        @ManyToOne
        @JoinColumn(name = "sponsor_id")
        private Sponsor sponsor;
        @ManyToOne
        @JoinColumn(name = "equipe_id")
        private Equipe equipe;

        private Float montant;

        private String annee;

        private Boolean archived;

        // 🔹 Constructeurs
        public Contrat() {
        }

        public Contrat(Float montant, String annee, Boolean archived) {
            this.montant = montant;
            this.annee = annee;
            this.archived = archived;
        }

        // 🔹 Getters & Setters
        public Long getIdContrat() {
            return idContrat;
        }

        public void setIdContrat(Long idContrat) {
            this.idContrat = idContrat;
        }

        public Float getMontant() {
            return montant;
        }

        public void setMontant(Float montant) {
            this.montant = montant;
        }

        public String getAnnee() {
            return annee;
        }

        public void setAnnee(String annee) {
            this.annee = annee;
        }

        public Boolean getArchived() {
            return archived;
        }

        public void setArchived(Boolean archived) {
            this.archived = archived;
        }
    }
}