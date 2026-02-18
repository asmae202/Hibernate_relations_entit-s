package com.example;

import com.example.model.Salle;
import com.example.model.Utilisateur;
import com.example.model.Reservation;
import com.example.service.SalleService;
import com.example.service.UtilisateurService;
import com.example.service.ReservationService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class MainUI extends Application {

    private UtilisateurService utilisateurService;
    private SalleService salleService;
    private ReservationService reservationService;

    private TableView<Utilisateur> tableUtilisateurs = new TableView<>();
    private TableView<Salle> tableSalles = new TableView<>();
    private TableView<Reservation> tableReservations = new TableView<>();

    private ComboBox<Utilisateur> cbUtilisateur = new ComboBox<>();
    private ComboBox<Salle> cbSalle = new ComboBox<>();
    private DatePicker dpDate = new DatePicker();
    private Button btnReserver = new Button("Réserver une salle");

    @Override
    public void start(Stage primaryStage) {

        // Création EntityManagerFactory et services
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion-salles");
        utilisateurService = new UtilisateurService(emf);
        salleService = new SalleService(emf);
        reservationService = new ReservationService(emf);

        setupTables();
        loadData();

        // Rendre les ComboBox éditables pour saisie libre
        cbUtilisateur.setEditable(true);
        cbSalle.setEditable(true);

        btnReserver.setOnAction(e -> reserverSalle());

        VBox vbox = new VBox(10,
                new Label("Utilisateurs"), tableUtilisateurs,
                new Label("Salles"), tableSalles,
                new HBox(10,
                        new Label("Utilisateur:"), cbUtilisateur,
                        new Label("Salle:"), cbSalle,
                        new Label("Date:"), dpDate,
                        btnReserver
                ),
                new Label("Réservations"), tableReservations
        );
        vbox.setPadding(new javafx.geometry.Insets(10));

        Scene scene = new Scene(vbox, 900, 600);
        primaryStage.setTitle("Gestion des réservations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupTables() {
        // Table Utilisateurs
        TableColumn<Utilisateur, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        TableColumn<Utilisateur, String> colPrenom = new TableColumn<>("Prénom");
        colPrenom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        TableColumn<Utilisateur, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        TableColumn<Utilisateur, String> colTel = new TableColumn<>("Téléphone");
        colTel.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelephone()));
        tableUtilisateurs.getColumns().addAll(colNom, colPrenom, colEmail, colTel);

        // Table Salles
        TableColumn<Salle, String> colNomSalle = new TableColumn<>("Nom");
        colNomSalle.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        TableColumn<Salle, Integer> colCapacite = new TableColumn<>("Capacité");
        colCapacite.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCapacite()).asObject());
        tableSalles.getColumns().addAll(colNomSalle, colCapacite);

        // Table Réservations
        TableColumn<Reservation, String> colUserRes = new TableColumn<>("Utilisateur");
        colUserRes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUtilisateur().getNom()));
        TableColumn<Reservation, String> colSalleRes = new TableColumn<>("Salle");
        colSalleRes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSalle().getNom()));
        TableColumn<Reservation, String> colDateRes = new TableColumn<>("Date");
        colDateRes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateReservation().toLocalDate().toString()));
        tableReservations.getColumns().addAll(colUserRes, colSalleRes, colDateRes);
    }

    private void loadData() {
        List<Utilisateur> users = utilisateurService.findAll();
        if(users.isEmpty()){
            // Exemple si base vide
            Utilisateur u = new Utilisateur("Dupont","Jean","j.dupont@mail.com");
            utilisateurService.save(u);
            users.add(u);
        }
        tableUtilisateurs.getItems().setAll(users);
        cbUtilisateur.getItems().setAll(users);

        List<Salle> salles = salleService.findAll();
        if(salles.isEmpty()){
            Salle s = new Salle("Salle A", 10);
            salleService.save(s);
            salles.add(s);
        }
        tableSalles.getItems().setAll(salles);
        cbSalle.getItems().setAll(salles);

        tableReservations.getItems().setAll(reservationService.findAll());
    }

    private void reserverSalle() {
        Utilisateur u = cbUtilisateur.getValue();
        Salle s = cbSalle.getValue();
        LocalDate date = dpDate.getValue();

        // Saisie libre utilisateur
        String textUser = cbUtilisateur.getEditor().getText();
        if(textUser != null && !textUser.isEmpty() && (u == null || !u.getNom().equals(textUser))) {
            u = new Utilisateur(textUser, "", "");
            utilisateurService.save(u);
            tableUtilisateurs.getItems().add(u);
            cbUtilisateur.getItems().add(u);
            cbUtilisateur.setValue(u);
        }

        // Saisie libre salle
        String textSalle = cbSalle.getEditor().getText();
        if(textSalle != null && !textSalle.isEmpty() && (s == null || !s.getNom().equals(textSalle))) {
            s = new Salle(textSalle, 0);
            salleService.save(s);
            tableSalles.getItems().add(s);
            cbSalle.getItems().add(s);
            cbSalle.setValue(s);
        }

        // Crée la réservation
        if(u != null && s != null && date != null){
            Reservation r = new Reservation(u, s, date.atStartOfDay());
            reservationService.save(r);
            tableReservations.getItems().add(r);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs !");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
