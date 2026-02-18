

## Objectif du projet
Ce TP permet de créer un projet Maven utilisant **Hibernate** avec une base de données **H2**, de définir des entités avec validations et d’implémenter les opérations **CRUD** via des services.

## Points clés et explications

1. **Configuration Hibernate (`persistence.xml`)**
   - `hibernate.hbm2ddl.auto = create-drop` : génère automatiquement le schéma de la base au démarrage et le supprime à l’arrêt.
   - Autres options possibles : `create`, `update`, `validate`, `none`.

2. **Entités et validations**
   - Chaque classe marquée `@Entity` représente une table de la base.
   - Validations avec annotations (`@NotBlank`, `@Size`, `@Email`, `@Min`, `@Max`) assurent la cohérence des données.
   - Les relations et contraintes uniques sont définies via `@Column` et `@GeneratedValue`.

3. **Architecture des services**
   - **CrudService<T, ID>** : interface générique pour toutes les opérations CRUD.
   - **AbstractCrudService<T, ID>** : implémentation générique des CRUD pour réutilisation.
   - **Services spécifiques** (`UtilisateurService`, `SalleService`) ajoutent des méthodes métiers, par exemple `findByEmail` ou `findByCapaciteMinimum`.

4. **Tests et validation**
   - Les tests unitaires assurent que toutes les opérations CRUD fonctionnent correctement.
   - Les méthodes spécifiques sont également testées pour vérifier la logique métier.

## Conclusion et apprentissages

Grâce à ce TP, on apprend à :

- **Créer un projet Maven structuré** avec dépendances pour JPA, Hibernate et H2.
- **Générer automatiquement le schéma** de la base et comprendre les différentes options de `hibernate.hbm2ddl.auto`.
- **Modéliser des entités** avec validations pour garantir l’intégrité des données.
- **Implémenter des services CRUD réutilisables** et spécifiques aux entités.
- **Tester les opérations** avec des tests unitaires pour assurer le bon fonctionnement.

Ce projet constitue une base solide pour :

- Ajouter des **relations entre entités**, comme les réservations de salles par utilisateurs.
- Développer une **interface utilisateur** (web ou desktop).
- Ajouter des **fonctionnalités métier** et validations avancées.

