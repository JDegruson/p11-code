# Gestion des Rendez-vous Médicaux
Ce projet est une application de gestion des rendez-vous médicaux. Il permet aux patients de prendre rendez-vous dans des hôpitaux en fonction de leur spécialité médicale et de leur proximité.

## Fonctionnalités
Création de Rendez-vous: Les utilisateurs peuvent créer des rendez-vous en spécifiant leur emplacement et la spécialité médicale.

Recherche d'Hôpitaux: Les utilisateurs peuvent rechercher des hôpitaux disponibles en fonction de leur emplacement et de la spécialité médicale souhaitée.

Consultation des Rendez-vous: Les utilisateurs peuvent consulter la liste de leurs rendez-vous existants.

## Technologies Utilisées
Java
Spring Boot
MySQL 

## Configuration du Projet
### Clonage du Projet:
git clone https://github.com/JDegruson/p11-code.git

### Configuration de la Base de Données:


CREATE DATABASE IF NOT EXISTS `p11` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `p11`;

-- Listage de la structure de la table p11. appointment
CREATE TABLE IF NOT EXISTS `appointment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `longitude` decimal(10,8) NOT NULL,
  `latitude` decimal(10,8) NOT NULL,
  `patient_name` varchar(255) NOT NULL,
  `hospital_name` varchar(255) NOT NULL,
  `speciality` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


Mettez à jour les informations de la base de données dans le fichier application.properties.

## Exécution du Projet:
./mvnw spring-boot:run
L'application sera accessible à l'adresse http://localhost:8080.

Auteur
Degruson Julien
