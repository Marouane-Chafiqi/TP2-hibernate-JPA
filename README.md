# Gestion des salles : TP Hibernate / JPA avec Maven et H2

## Objectif du TP

Ce TP a pour but de mettre en place une petite application Java qui gère des utilisateurs et des salles. L'application utilise JPA avec Hibernate pour communiquer avec une base de données H2 en mémoire, et permet de réaliser les quatre opérations de base : créer, lire, modifier et supprimer des données.

## Outils utilisés

Le projet a été réalisé avec IntelliJ IDEA et Maven pour la gestion des dépendances. Le langage utilisé est Java 8. Hibernate sert d'implémentation de JPA, Hibernate Validator permet de contrôler les données saisies, H2 fournit la base de données en mémoire, SLF4J s'occupe des logs et JUnit permet d'écrire les tests unitaires.

## Création du projet Maven

J'ai commencé par créer un projet Maven avec le groupId com.example, l'artifactId gestion-salles et la version 1.0-SNAPSHOT. Ensuite, j'ai ouvert le fichier pom.xml pour y ajouter toutes les dépendances nécessaires, puis j'ai configuré la version de Java et l'encodage du projet en UTF-8.

## Configuration de Hibernate et de H2

Dans le dossier resources, j'ai créé un dossier META-INF contenant le fichier persistence.xml. Ce fichier déclare l'unité de persistence, la connexion à la base H2 en mémoire, le dialecte Hibernate et la liste des entités. J'ai choisi la valeur create-drop pour la génération du schéma : les tables sont créées automatiquement au démarrage de l'application et supprimées à son arrêt. L'affichage des requêtes SQL est activé pour pouvoir suivre ce que fait Hibernate.

## Les entités

J'ai créé deux entités dans le package model. La première est Utilisateur, avec un nom, un prénom, un email, une date de naissance et un numéro de téléphone. La deuxième est Salle, avec un nom, une capacité, une description, un statut de disponibilité et un étage. Chaque entité possède une clé primaire générée automatiquement. Des validations ont été ajoutées sur les champs : par exemple le nom est obligatoire, l'email doit avoir un format correct, la date de naissance doit être dans le passé, le téléphone doit respecter un format précis et la capacité d'une salle doit rester entre 1 et 1000 personnes.

## Les services CRUD

Dans le package service, j'ai d'abord défini une interface générique qui liste les opérations de base : enregistrer, chercher par identifiant, lister tout, modifier et supprimer. J'ai ensuite écrit une classe abstraite qui implémente ces opérations une seule fois pour toutes les entités, en gérant les transactions et la fermeture de l'EntityManager. Enfin, j'ai créé deux services spécifiques. UtilisateurService ajoute la recherche d'un utilisateur par email. SalleService ajoute la recherche des salles selon leur disponibilité et selon une capacité minimale.

## La classe principale

La classe App sert à tester l'ensemble du travail. Elle crée l'EntityManagerFactory, puis exécute un scénario complet sur les utilisateurs et sur les salles. Pour les utilisateurs, elle ajoute deux personnes avec des noms marocains, affiche la liste, recherche par identifiant et par email, modifie un numéro de téléphone, supprime un utilisateur et réaffiche la liste restante. Pour les salles, elle ajoute trois salles, les affiche, recherche par identifiant, filtre les salles disponibles et celles qui ont au moins 50 places, modifie une capacité, supprime une salle et réaffiche le résultat.

## Les tests unitaires

Deux classes de tests ont été écrites avec JUnit, une pour chaque service. Elles vérifient que la création donne bien un identifiant, que la lecture retrouve la bonne donnée, que la modification est bien enregistrée et que la suppression fait disparaître l'élément. Elles testent aussi les méthodes spécifiques : recherche par email, filtre par disponibilité et filtre par capacité minimale.

## Exécution

L'application se lance en exécutant la classe App depuis IntelliJ. Les tests se lancent avec la commande de test de Maven ou directement depuis l'IDE. Dans la console, on voit les requêtes SQL générées par Hibernate ainsi que les affichages du scénario de test : les créations, les lectures, les modifications et les suppressions se déroulent sans erreur.

## Démonstration vidéo

Une vidéo d'enregistrement de l'exécution du projet est jointe à ce travail. Elle montre le lancement de la classe App, les résultats affichés dans la console, puis l'exécution des tests unitaires avec leur réussite.



https://github.com/user-attachments/assets/914c6e5e-a431-4255-88d1-b807ffd54bc6



## Conclusion

Ce TP m'a permis de comprendre comment créer un projet Maven avec Hibernate et H2, comment générer automatiquement le schéma de la base de données, comment définir des entités avec des validations et comment organiser le code en services réutilisables. Il m'a aussi montré l'utilité des tests unitaires pour vérifier le bon fonctionnement de l'application. Le projet pourrait être amélioré plus tard en ajoutant des relations entre les entités, comme la réservation d'une salle par un utilisateur, ainsi qu'une interface graphique et des validations plus complexes.
