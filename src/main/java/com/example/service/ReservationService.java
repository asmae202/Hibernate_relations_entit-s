package com.example.service;

import com.example.model.Reservation;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ReservationService extends AbstractCrudService<Reservation, Long> {

    private final EntityManager em;

    // Constructeur correct
    public ReservationService(EntityManagerFactory emf) {
        super(emf);  // ✅ On passe la classe de l'entité
        this.em = emf.createEntityManager();
    }


    //  Trouver réservations par utilisateur

    public List<Reservation> findAllByUtilisateurId(Long utilisateurId) {
        return em.createQuery(
                        "SELECT r FROM Reservation r WHERE r.utilisateur.id = :uid",
                        Reservation.class)
                .setParameter("uid", utilisateurId)
                .getResultList();
    }


    //  Trouver réservations par salle

    public List<Reservation> findAllBySalleId(Long salleId) {
        return em.createQuery(
                        "SELECT r FROM Reservation r WHERE r.salle.id = :sid",
                        Reservation.class)
                .setParameter("sid", salleId)
                .getResultList();
    }


    //  Vérifier si salle disponible (règle métier)

    public boolean salleDisponible(Long salleId, LocalDateTime date) {
        return em.createQuery(
                        "SELECT r FROM Reservation r WHERE r.salle.id = :sid AND r.dateReservation = :date",
                        Reservation.class)
                .setParameter("sid", salleId)
                .setParameter("date", date)
                .getResultList()
                .isEmpty();
    }


    // 4️ Compter réservations d’un utilisateur

    public long countReservationsByUtilisateur(Long utilisateurId) {
        return em.createQuery(
                        "SELECT COUNT(r) FROM Reservation r WHERE r.utilisateur.id = :uid",
                        Long.class)
                .setParameter("uid", utilisateurId)
                .getSingleResult();
    }


    //  Supprimer anciennes réservations

    public void supprimerAnciennesReservations() {
        em.getTransaction().begin();
        em.createQuery(
                        "DELETE FROM Reservation r WHERE r.dateReservation < CURRENT_TIMESTAMP")
                .executeUpdate();
        em.getTransaction().commit();
    }
}


