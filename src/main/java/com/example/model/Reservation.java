package com.example.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation vers Utilisateur
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    // Relation vers Salle
    @ManyToOne
    @JoinColumn(name = "salle_id", nullable = false)
    private Salle salle;

    private LocalDateTime dateReservation;

    // Constructeur vide
    public Reservation() {}

    public Reservation(Utilisateur utilisateur, Salle salle, LocalDateTime dateReservation) {
        this.utilisateur = utilisateur;
        this.salle = salle;
        this.dateReservation = dateReservation;
    }

    // Getters et setters
    public Long getId() { return id; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public Salle getSalle() { return salle; }
    public void setSalle(Salle salle) { this.salle = salle; }
    public LocalDateTime getDateReservation() { return dateReservation; }
    public void setDateReservation(LocalDateTime dateReservation) { this.dateReservation = dateReservation; }
}

