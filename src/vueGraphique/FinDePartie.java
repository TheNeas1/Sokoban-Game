package vueGraphique;

import modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FinDePartie extends JFrame {
    private JButton menuButton;
    private JButton quitterButton;
    private JButton recommencerButton;
    private JButton niveauSuivantButton;
    private Carte carte;
    private int nbNiveau = 3 ;

    public FinDePartie(Carte carte) {
        super("Fin de Partie");
        this.carte = carte;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        menuButton = new JButton("Retour au Menu");
        quitterButton = new JButton("Quitter");
        recommencerButton = new JButton("Recommencer");
        niveauSuivantButton = new JButton("Niveau Suivant");

        panel.add(menuButton);
        panel.add(recommencerButton);
        panel.add(niveauSuivantButton);
        panel.add(quitterButton);

        add(panel);

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.afficher();
                fermer();
            }
        });

        quitterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        recommencerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Carte nouvelleCarte = new Carte(carte.getNiveau());
                    ModeGraphique modeGraphique = new ModeGraphique(nouvelleCarte);
                    modeGraphique.afficher();
                    fermer();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        

        niveauSuivantButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(carte.getNiveau()+1 <= nbNiveau){
                        Carte nouvelleCarte = new Carte(carte.getNiveau()+1);
                        ModeGraphique modeGraphique = new ModeGraphique(nouvelleCarte);
                        modeGraphique.afficher();
                        fermer();
                    }else{
                        niveauSuivantButton.setEnabled(false);
                    }
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void afficher() {
        setVisible(true);
    }

    public void fermer() {
        dispose();
    }
}
