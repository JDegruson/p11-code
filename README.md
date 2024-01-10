
Projet de Réservation de Rendez-vous Médical
Ce projet vise à créer une application React permettant aux utilisateurs de prendre des rendez-vous médicaux en ligne de manière simple et efficace.

Fonctionnalités Principales
Page Principale : Affiche une interface conviviale permettant aux utilisateurs de naviguer entre différentes sections de l'application.
Prendre un Rendez-vous : Permet aux utilisateurs de sélectionner leur emplacement, spécialité médicale et horaire pour réserver un rendez-vous.
Affichage des Rendez-vous : Une page dédiée qui affiche tous les rendez-vous pris par l'utilisateur.
Technologies Utilisées
React : La bibliothèque JavaScript pour la construction de l'interface utilisateur.
React Router : Pour la gestion des routes et de la navigation dans l'application.
Google Maps API : Intégration pour la sélection d'emplacements.
@react-google-maps/api : Bibliothèque pour l'intégration facile de Google Maps avec React.
Autres dépendances : Jest pour les tests unitaires, etc.
Installation
Cloner le repository : git clone https://github.com/votre-utilisateur/votre-projet.git
Installer les dépendances : npm install
Lancer l'application : npm start

Git workflow :
  Branches principales:
    master: Représente la branche principale du développement, contenant toujours le dernier déploiement de production.
  Flux de travail:
    Développement de nouvelles fonctionnalités dans des branches distinctes.
    Création d'une pull request pour discussion et validation.
    Fusion de la pull request dans master après la validation.
    Le déploiement continu est encouragé, avec des mises en production fréquentes à partir de master.
    Les bugs et les corrections sont effectués directement dans master et déployés immédiatement.

Configuration de Google Maps API
Obtenez une clé API Google Maps à partir de Google Cloud Console.
Copiez la clé API dans le fichier AddressForm.js où apiKey est défini.
Tests Unitaires
Le projet utilise Jest pour les tests unitaires. Pour lancer les tests, utilisez la commande : npm test

Auteur
[Ajoutez votre nom ici]
