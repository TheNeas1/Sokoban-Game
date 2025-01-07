# Sokoban en Java

## 📖 Description du jeu

Sokoban est un jeu de puzzle classique où le joueur incarne un personnage chargé de pousser des caisses pour les placer sur des emplacements cibles. Les règles sont simples : 
- Le joueur ne peut pousser qu'une seule caisse à la fois.
- Les caisses ne peuvent pas être tirées.
- Le but est de positionner toutes les caisses sur les cibles en un minimum de mouvements.

Ce projet est une implémentation de Sokoban en Java, offrant deux interfaces utilisateur différentes : 
- Une interface graphique pour une expérience visuelle interactive.
- Une interface en console pour une expérience minimaliste.

---

## 🎮 Fonctionnalités

- **Interface graphique** :
  - Déplacement du personnage à l'aide des touches directionnelles.
  - Affichage des caisses, des cibles et du plateau de jeu.
  - Gestion des niveaux et progression.
- **Interface console** :
  - Contrôle par saisie des commandes directionnelles (`haut`, `bas`, `gauche`, `droite`).
  - Affichage textuel du plateau de jeu.
  - Retour instantané sur les déplacements.

---

## 🚀 Installation et utilisation

### Prérequis

- Aucune installation complexe requise ! Assurez-vous d'avoir Java Runtime Environment (JRE) installé sur votre machine.
- Téléchargez les fichiers suivants :
  - `SokobanVueGraphique.jar` pour l'interface graphique.
  - `SokobanVueTexte.jar` pour l'interface console.

### Étapes pour exécuter le jeu

1. Téléchargez le fichier correspondant à l'interface que vous souhaitez utiliser.
2. Double-cliquez sur le fichier téléchargé ou utilisez une commande dans le terminal pour l'exécuter :
   - Pour l'interface graphique :
     ```bash
     java -jar SokobanVueGraphique.jar
     ```
   - Pour l'interface console :
     ```bash
     java -jar SokobanVueTexte.jar
     ```
