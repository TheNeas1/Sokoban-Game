package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lecture {
    private List<String> lignes;
    private int nombreLignes;
    private int tailleLignes;

    public Lecture(String nomFichier) throws IOException {
        lignes = new ArrayList<>();
        BufferedReader lecteur = new BufferedReader(new FileReader(nomFichier));
        String ligne;

        // Lecture de chaque ligne du fichier
        while ((ligne = lecteur.readLine()) != null) {
            lignes.add(ligne);
        }

        lecteur.close();

        // Initialisation du nombre de lignes et de la taille des lignes
        nombreLignes = lignes.size();
        if (nombreLignes > 0) {
            tailleLignes = lignes.get(0).length();
        }
    }

    public List<String> getLignes() {
        return lignes;
    }

    public int getNombreLignes() {
        return nombreLignes;
    }

    public int getTailleLignes() {
        return tailleLignes;
    }
}

