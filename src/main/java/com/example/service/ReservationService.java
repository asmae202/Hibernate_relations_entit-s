package com.example.service;

import com.example.model.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ReservationService extends AbstractCrudService<Reservation, Long> {

    private final EntityManager em;

    // Constructeur : on reçoit l'EntityManagerFactory depuis App.java
    public ReservationService(EntityManagerFactory emf) {
        super(emf);       // Indique à AbstractCrudService quelle entité gérer
        this.em = emf.createEntityManager(); // Crée l'EntityManager à partir de la factory
    }

    // Trouver toutes les réservations d'un utilisateur
    public List<Reservation> findAllByUtilisateurId(Long utilisateurId) {
        return em.createQuery(
                        "SELECT r FROM Reservation r WHERE r.utilisateur.id = :uid", Reservation.class)
                .setParameter("uid", utilisateurId)
                .getResultList();
    }

    // Trouver toutes les réservations d'une salle
    public List<Reservation> findAllBySalleId(Long salleId) {
        return em.createQuery(
                        "SELECT r FROM Reservation r WHERE r.salle.id = :sid", Reservation.class)
                .setParameter("sid", salleId)
                .getResultList();
    }
}
