# Atelier REST n°1 - Gestion des options et des étudiants

Service web RESTful pour gérer des options et des étudiants, réalisé avec JAX-RS (Jersey 2.27), Tomcat 9 et Java 17.

**URL de base :** `http://localhost:8080/Gestion_Options_Etudiants_war_exploded/rest`

## Structure du projet

- `utilities/RestActivator` : active JAX-RS avec `@ApplicationPath("rest")`
- `ressources/OptionResource` : les endpoints des options
- `ressources/EtudiantResource` : les endpoints des étudiants
- `metiers` et `entities` : classes fournies (traitement métier en mémoire)

## Lancer le projet

1. Ouvrir le projet dans IntelliJ.
2. Configurer Tomcat 9 avec l'artifact `Gestion_Options_Etudiants:war exploded`.
3. Démarrer Tomcat et tester les URLs avec Postman.

## Ressource Option

### A1 - Création d'une option
`POST /options` (statut 200)

![A1](screenshots/A1.png)

### A2 - Liste de toutes les options
`GET /options` (statut 200)

![A2](screenshots/A2.png)

### A3 - Options d'un domaine
`GET /options?domaine=Mathématiques` (statut 200)

![A3](screenshots/A3.png)

### A4 - Suppression d'une option
`DELETE /options/2` (statut 204)

![A4](screenshots/A4.png)

### A5 - Modification d'une option
`PUT /options/1` (statut 200)

![A5](screenshots/A5.png)

### A6 - Option par code
`GET /options/1` (statut 200)

![A6](screenshots/A6.png)

## Ressource Etudiant

### B1 - Création d'un étudiant
`POST /etudiants` (statut 200)

![B1](screenshots/B1.png)

### B2 - Liste de tous les étudiants
`GET /etudiants` (statut 200)

![B2](screenshots/B2.png)

### B3 - Étudiant par identifiant
`GET /etudiants/I003` (statut 200)

![B3](screenshots/B3.png)

### B4 - Suppression d'un étudiant
`DELETE /etudiants/I003` (statut 204)

![B4](screenshots/B4.png)

### B5 - Modification d'un étudiant
`PUT /etudiants/I001` (statut 200)

![B5](screenshots/B5.png)

### B6 - Étudiants d'une option (XML)
`GET /etudiants/option?codeOption=1` (statut 200, réponse en XML)

![B6](screenshots/B6.png)