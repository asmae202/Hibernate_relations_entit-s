

## Objectif du projet

-Créer une application de gestion des réservations de salles :

-Gérer les utilisateurs

-Gérer les salles

-Permettre de réserver une salle

-Afficher les réservations

## Technologies utilisées :

-Java

-JPA (Hibernate)

-Base de données

-JavaFX (interface desktop)
## ÉTAPE 1 : Création des entités (Modèle)

On a créé 3 entités :

1️/ Utilisateur

Représente une personne qui peut réserver.
Contient :

id

nom

prénom

email

téléphone

  C’est une table dans la base de données.

2️/Salle

Représente une salle disponible.
Contient :

id

nom

capacité


3️/ Reservation

Représente une réservation.
Contient :

id

utilisateur

salle

dateReservation

 Cette entité est spéciale car elle relie les deux autres.

## ÉTAPE 2 : Ajout des relations entre entités 

 -On a ajouté des relations parce qu’une réservation appartient à un utilisateur concerne une salle.

Donc il fallait créer un lien entre les tables.

## 🔹 Relation 1 : Reservation → Utilisateur

Type : ManyToOne

Un utilisateur peut avoir plusieurs réservations.

Mais une réservation appartient à un seul utilisateur.

Donc :
Plusieurs réservations → 1 utilisateur

## 🔹 Relation 2 : Reservation → Salle

Type : ManyToOne

Une salle peut être réservée plusieurs fois.

Mais une réservation concerne une seule salle.

Donc :
Plusieurs réservations → 1 salle

## Résultat dans la base de données

La table Reservation contient :

utilisateur_id (clé étrangère)

salle_id (clé étrangère)

 Cela crée le lien entre les tables.

## ÉTAPE 3 : Configuration JPA

On a :

-Créé persistence.xml

-Configuré la connexion à la base de données

-Défini les entités

 -Cela permet à JPA de créer automatiquement les tables.

 ## ÉTAPE 4 : Création des Services 

On a créé :

-AbstractCrudService (classe générique)

-UtilisateurService

-SalleService

-ReservationService

## Rôle des services

Ils permettent de :

Ajouter

Supprimer

Modifier

Trouver des données

Exemple :

Trouver toutes les réservations d’un utilisateur

Trouver toutes les réservations d’une salle

Ça c’est une fonctionnalité métier supplémentaire.

## ÉTAPE 5 : Création de l’interface Desktop (JavaFX)

On a créé :

-Une fenêtre principale

-Table des utilisateurs

-Table des salles

-Table des réservations

-Bouton "Réserver une salle"

-ComboBox pour choisir utilisateur et salle

-DatePicker pour choisir la date

## ÉTAPE 6 : Ajout des fonctionnalités métier dans l’interface

Avant :
L’interface affichait seulement des données.

Après :
On a ajouté :

Création d’une réservation

Affichage dynamique des réservations

Mise à jour automatique de la table

On a relié :
Interface + Services + Base de données

## Résumé Global du Projet
📌 Couche 1 : Modèle

Entités + relations entre elles

📌 Couche 2 : Accès aux données

JPA + EntityManager

📌 Couche 3 : Logique métier

Services CRUD + méthodes personnalisées

📌 Couche 4 : Interface utilisateur

JavaFX

## Resultat de run App.main:

![run app 1](https://github.com/user-attachments/assets/cfd5bc6e-a239-4baf-84ab-9868aa54e9ce)

![run app 2](https://github.com/user-attachments/assets/6a855f8b-381b-4193-bc53-9bff9fb0afea)

![run app 3](https://github.com/user-attachments/assets/e249d0f2-aa74-4b9f-9998-1e457a3c3cab)


![run app 4](https://github.com/user-attachments/assets/e1bcb0bd-5a19-4032-a599-e88efcb73c38)

![run app 5](https://github.com/user-attachments/assets/40a24355-f40c-4a62-9128-d1e12395f5c2)

![run app 6](https://github.com/user-attachments/assets/c0bef52c-34e6-4b9a-a787-4c4578353709)

![run app 7](https://github.com/user-attachments/assets/90d7518e-ff05-4cc6-9220-81a7eea94f6b)

![run app 8](https://github.com/user-attachments/assets/e05705f2-61e7-49fc-9f53-f9cf1632a902)

![run app 9](https://github.com/user-attachments/assets/c77d3c72-0547-40a5-ba41-6994dd767e77)

![run app 10](https://github.com/user-attachments/assets/9c633fc2-c1c5-4081-a96b-293bd8a1efc1)

## Resultat de run SalleServiceTest:
![run salleservicetest 1](https://github.com/user-attachments/assets/98ff052c-416d-4ea7-a88a-86daa440fa32)

![run salleservicetest 2](https://github.com/user-attachments/assets/720ffdec-61fe-4c12-bf9c-90ee4c8efdcb)

![run salleservicetest 3](https://github.com/user-attachments/assets/21c8036e-1dfc-408d-8235-16a59e8cbd38)

![run salleservicetest 4](https://github.com/user-attachments/assets/d37fccc7-ebad-46d1-adf1-d77a17fb6ff4)

![run salleservicetest 5](https://github.com/user-attachments/assets/d10817b9-a8ff-4de2-a97d-920f6f60abc3)

![run salleservicetest 6](https://github.com/user-attachments/assets/bae927a6-059f-49b3-8339-b246b3660b77)

![run salleservicetest 7](https://github.com/user-attachments/assets/b385a11b-e49e-461a-b177-c2fc9160e5ae)

![run salleservicetest 8](https://github.com/user-attachments/assets/09c61a09-6e65-4be2-9b53-ed09ca84463d)

<img width="621" height="302" alt="run salleservice9" src="https://github.com/user-attachments/assets/6aa38264-21d2-4565-b2f1-bed164c9a945" />

<img width="553" height="299" alt="run salleservice10" src="https://github.com/user-attachments/assets/ca5dc7bc-37c2-4be4-ab30-3fc480d7cf3c" />

<img width="611" height="305" alt="run salleservice11" src="https://github.com/user-attachments/assets/30a233ff-87f5-49d1-8dee-a07fd29e8f3b" />

<img width="530" height="309" alt="run salleservice12" src="https://github.com/user-attachments/assets/8d6ea258-9fb2-4395-b718-af329c10da05" />

<img width="614" height="335" alt="run salleservice13" src="https://github.com/user-attachments/assets/3f29fdb9-5afa-4125-9255-64cc25dc963e" />

<img width="650" height="316" alt="run salleservice14" src="https://github.com/user-attachments/assets/c1d260d6-4feb-4ad2-ae59-96acbdf2b6ed" />

<img width="631" height="312" alt="run salleservice15" src="https://github.com/user-attachments/assets/c2ea8d1b-fcd8-43e6-9860-4fdb8dbe117c" />

<img width="679" height="308" alt="run salleservice16" src="https://github.com/user-attachments/assets/6a1ce5da-2f66-48b9-89a5-55c4fc9a4ac8" />

<img width="679" height="322" alt="run salleservice17" src="https://github.com/user-attachments/assets/e8a92ad9-a0a5-445b-9dea-d3dbf81db60f" />

<img width="689" height="317" alt="run salleservice18" src="https://github.com/user-attachments/assets/1bb9c2d4-852d-4a04-bff4-e7db0e8a76e2" />







