package vueTexte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import modele.Carte;
import modele.Direction;


public class ModeTexte {

    public void startModeTexte() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            
            int niveau = demanderNiveau(reader);
            jouerPartie(reader, niveau);

            if (!demanderRelancer(reader)) {
                System.out.println("Merci d'avoir joue !");
                break;
            }
        }
    }

    private int demanderNiveau(BufferedReader reader) {
        while (true) {
            System.out.println("Veuillez selectionner un niveau (1, 2 ou 3) : ");
            try {
                String input = reader.readLine();
                int niveau = Integer.parseInt(input);
                if (niveau < 1 || niveau > 3) {
                    throw new NumberFormatException();
                }
                return niveau;
            } catch (NumberFormatException | IOException e) {
                System.out.println("Erreur : Veuillez saisir un chiffre valide (1, 2 ou 3).");
            }
        }
    }
    

    private void jouerPartie(BufferedReader reader, int niveau) {
        try {
            Carte carte = new Carte(niveau);
            System.out.println("Niveau selectionne : " + niveau);
            carte.afficherCarte();

            while (!carte.finDePartie()) {

                System.out.println("Entrez une direction (z, s, q, d) ou 'x' pour quitter : ");
                char input;
                input = Outil.lireCaractere();

                if (input == 'x') {
                    System.out.println("Vous avez quitte la partie !");
                    break;
                }

                Direction direction = null;
                switch (input) {
                    case 'z':
                        direction = Direction.HAUT;
                        break;
                    case 's':
                        direction = Direction.BAS;
                        break;
                    case 'q':
                        direction = Direction.GAUCHE;
                        break;
                    case 'd':
                        direction = Direction.DROITE;
                        break;
                    default:
                        System.out.println("Direction invalide !");
                        continue;
                }

                // Déplacer le robot dans la direction spécifiée
                carte.deplacerRobot(direction);

                // Afficher la carte mise à jour
                carte.afficherCarte();
            }

            System.out.println("Partie finie !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture de la carte : " + e.getMessage());
        }
    }

    private boolean demanderRelancer(BufferedReader reader) {
        while (true) {
            System.out.println("Voulez-vous relancer une partie ? (oui/non) : ");
            try {
                String reponse = reader.readLine().trim().toLowerCase();
                if (reponse.equals("oui")) {
                    return true;
                } else if (reponse.equals("non")) {
                    return false;
                } else {
                    System.out.println("Erreur : Veuillez saisir oui ou non");
                }
            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture de l'entree utilisateur : " + e.getMessage());
                return false;
            }
        }
    }
}
