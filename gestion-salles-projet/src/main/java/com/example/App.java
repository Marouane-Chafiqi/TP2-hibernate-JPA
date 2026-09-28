package com.example;

import com.example.model.Salle;
import com.example.model.Utilisateur;
import com.example.service.SalleService;
import com.example.service.UtilisateurService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion-salles");

        UtilisateurService utilisateurService = new UtilisateurService(emf);
        SalleService salleService = new SalleService(emf);

        try {
            System.out.println("\n=== Test CRUD Utilisateur ===");
            testerUtilisateurs(utilisateurService);

            System.out.println("\n=== Test CRUD Salle ===");
            testerSalles(salleService);
        } finally {
            emf.close();
        }
    }

    private static void testerUtilisateurs(UtilisateurService service) {
        // Ajout de deux utilisateurs
        System.out.println("Ajout des utilisateurs...");

        Utilisateur youssef = new Utilisateur("Benali", "Youssef", "youssef.benali@gmail.com");
        youssef.setDateNaissance(LocalDate.of(2001, 5, 15));
        youssef.setTelephone("+212661234567");

        Utilisateur khadija = new Utilisateur("El Idrissi", "Khadija", "khadija.elidrissi@gmail.com");
        khadija.setDateNaissance(LocalDate.of(2002, 10, 20));
        khadija.setTelephone("+212677654321");

        service.save(youssef);
        service.save(khadija);

        // Affichage
        System.out.println("\nTous les utilisateurs :");
        List<Utilisateur> liste = service.findAll();
        liste.forEach(System.out::println);

        System.out.println("\nRecherche par ID (1) :");
        Optional<Utilisateur> trouve = service.findById(1L);
        trouve.ifPresent(System.out::println);

        System.out.println("\nRecherche par email :");
        Optional<Utilisateur> parEmail = service.findByEmail("khadija.elidrissi@gmail.com");
        parEmail.ifPresent(System.out::println);

        // Modification du numéro de téléphone
        System.out.println("\nModification du téléphone :");
        trouve.ifPresent(u -> {
            u.setTelephone("+212600112233");
            service.update(u);
            System.out.println("Après modification : " + u);
        });

        // Suppression
        System.out.println("\nSuppression de l'utilisateur ID=2...");
        service.deleteById(2L);

        System.out.println("\nUtilisateurs restants :");
        service.findAll().forEach(System.out::println);
    }

    private static void testerSalles(SalleService service) {
        // Ajout de trois salles
        System.out.println("Ajout des salles...");

        Salle salleA = new Salle("Salle A101", 30);
        salleA.setDescription("Salle de réunion avec projecteur");
        salleA.setEtage(1);

        Salle amphi = new Salle("Amphithéâtre B201", 150);
        amphi.setDescription("Grand amphi pour les conférences");
        amphi.setEtage(2);

        Salle salleC = new Salle("Salle C305", 10);
        salleC.setDescription("Petite salle pour les entretiens");
        salleC.setEtage(3);
        salleC.setDisponible(false);

        service.save(salleA);
        service.save(amphi);
        service.save(salleC);

        // Affichage
        System.out.println("\nToutes les salles :");
        List<Salle> salles = service.findAll();
        salles.forEach(System.out::println);

        System.out.println("\nRecherche par ID (2) :");
        Optional<Salle> salleTrouvee = service.findById(2L);
        salleTrouvee.ifPresent(System.out::println);

        System.out.println("\nSalles disponibles :");
        service.findByDisponible(true).forEach(System.out::println);

        System.out.println("\nSalles de 50 places minimum :");
        service.findByCapaciteMinimum(50).forEach(System.out::println);

        // Modification de la capacité
        System.out.println("\nModification de la capacité :");
        salleTrouvee.ifPresent(s -> {
            s.setCapacite(200);
            service.update(s);
            System.out.println("Après modification : " + s);
        });

        // Suppression
        System.out.println("\nSuppression de la salle ID=3...");
        service.deleteById(3L);

        System.out.println("\nSalles restantes :");
        service.findAll().forEach(System.out::println);
    }
}