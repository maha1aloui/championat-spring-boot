package tn.esprit.ds.championat.entities;

import jakarta.persistence.*;

@Entity
public class DetailChampionnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String description;

    @OneToOne
    @JoinColumn(name = "championnat_id")
    private Championnal championnat;

    // getters & setters
}