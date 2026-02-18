package com.example;

import com.example.model.Salle;
import com.example.model.Utilisateur;
import com.example.model.Reservation;
import com.example.service.SalleService;
import com.example.service.UtilisateurService;
import com.example.service.ReservationService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        // Création de l'EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion-salles");

        // Création des services
        UtilisateurService utilisateurService = new UtilisateurService(emf);
        SalleService salleService = new SalleService(emf);
        ReservationService reservationService = new ReservationService(emf);

        try {
            // Test CRUD Utilisateur
            System.out.println("\n=== Test CRUD Utilisateur ===");
            testCrudUtilisateur(utilisateurService);

            // Test CRUD Salle
            System.out.println("\n=== Test CRUD Salle ===");
            testCrudSalle(salleService);

            // Test des réservations
            System.out.println("\n=== Test Réservations ===");
            testReservations(utilisateurService, salleService, reservationService);

        } finally {
            // Fermeture de l'EntityManagerFactory
            emf.close();
        }
    }

    private static void testCrudUtilisateur(UtilisateurService service) {
        // Création d'utilisateurs
        Utilisateur u1 = new Utilisateur("Dupont", "Jean", "jean.dupont@example.com");
        u1.setDateNaissance(LocalDate.of(1985, 5, 15));
        u1.setTelephone("+33612345678");

        Utilisateur u2 = new Utilisateur("Martin", "Sophie", "sophie.martin@example.com");
        u2.setDateNaissance(LocalDate.of(1990, 10, 20));
        u2.setTelephone("+33687654321");

        service.save(u1);
        service.save(u2);
    }

    private static void testCrudSalle(SalleService service) {
        // Création de salles
        Salle s1 = new Salle("Salle A101", 30);
        s1.setDescription("Salle de réunion équipée d'un projecteur");
        s1.setEtage(1);

        Salle s2 = new Salle("Amphithéâtre B201", 150);
        s2.setDescription("Grand amphithéâtre pour conférences");
        s2.setEtage(2);

        service.save(s1);
        service.save(s2);
    }

    private static void testReservations(UtilisateurService utilisateurService,
                                         SalleService salleService,
                                         ReservationService reservationService) {

        // Récupération des utilisateurs et salles
        Utilisateur u1 = utilisateurService.findById(1L)
                .orElseThrow(() -> new RuntimeException("Utilisateur 1 non trouvé"));

        Utilisateur u2 = utilisateurService.findById(2L)
                .orElseThrow(() -> new RuntimeException("Utilisateur 2 non trouvé"));

        Salle s1 = salleService.findById(1L)
                .orElseThrow(() -> new RuntimeException("Salle 1 non trouvée"));

        Salle s2 = salleService.findById(2L)
                .orElseThrow(() -> new RuntimeException("Salle 2 non trouvée"));


        // Création des réservations
        Reservation r1 = new Reservation(u1, s1, LocalDateTime.now().plusDays(1));
        Reservation r2 = new Reservation(u2, s2, LocalDateTime.now().plusDays(2));

        // Sauvegarde
        reservationService.save(r1);
        reservationService.save(r2);

        // Ajout aux listes de chaque côté (facultatif si cascade = ALL)
        u1.addReservation(r1);
        s1.addReservation(r1);
        u2.addReservation(r2);
        s2.addReservation(r2);

        // Affichage des réservations
        List<Reservation> reservations = reservationService.findAll();
        System.out.println("\nToutes les réservations :");
        reservations.forEach(System.out::println);

        // Afficher les réservations par utilisateur
        System.out.println("\nRéservations de Jean Dupont :");
        reservationService.findAllByUtilisateurId(u1.getId()).forEach(System.out::println);

        System.out.println("\nRéservations de Salle A101 :");
        reservationService.findAllBySalleId(s1.getId()).forEach(System.out::println);
    }
}

