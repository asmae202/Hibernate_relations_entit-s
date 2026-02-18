package com.example.service;

import com.example.model.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UtilisateurService extends AbstractCrudService<Utilisateur, Long> {

    private final EntityManagerFactory emf;

    // ✅ Constructeur correct
    public UtilisateurService(EntityManagerFactory emf) {
        super(emf);   // On passe la classe de l'entité
        this.emf = emf;
    }

    // ================================
    // 1️⃣ Rechercher par email
    // ================================
    public Optional<Utilisateur> findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Utilisateur> query = em.createQuery(
                    "SELECT u FROM Utilisateur u WHERE u.email = :email",
                    Utilisateur.class);
            query.setParameter("email", email);
            List<Utilisateur> results = query.getResultList();
            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } finally {
            em.close();
        }
    }

    // ================================
    // 2️⃣ Compter réservations d’un utilisateur
    // ================================
    public long countReservations(Long utilisateurId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT COUNT(r) FROM Reservation r WHERE r.utilisateur.id = :uid",
                            Long.class)
                    .setParameter("uid", utilisateurId)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    // ================================
    // 3️⃣ Vérifier si email déjà utilisé
    // ================================
    public boolean emailExiste(String email) {
        return findByEmail(email).isPresent();
    }
}

