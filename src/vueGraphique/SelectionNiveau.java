package vueGraphique;

import javax.swing.*;

import modele.Carte;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SelectionNiveau extends JFrame {
    private JButton niveau1Button;
    private JButton niveau2Button;
    private JButton niveau3Button;
    private Carte carte ;

    public SelectionNiveau() {
        super("Sélection de Niveau");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        
        // Création des boutons pour chaque niveau
        niveau1Button = new JButton("Niveau 1");
        niveau2Button = new JButton("Niveau 2");
        niveau3Button = new JButton("Niveau 3");

        // Ajout des boutons au panel
        panel.add(niveau1Button);
        panel.add(niveau2Button);
        panel.add(niveau3Button);

        // Ajout du panel à la fenêtre
        add(panel);

        // Ajout des écouteurs d'événements aux boutons
        niveau1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    carte = new Carte(1);
                    fermer();
                    ModeGraphique modeGraphique = new ModeGraphique(carte);
                    modeGraphique.afficher();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        niveau2Button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    carte = new Carte(2);
                    fermer();
                    ModeGraphique modeGraphique = new ModeGraphique(carte);
                    modeGraphique.afficher();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        niveau3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    carte = new Carte(3);
                    fermer();
                    ModeGraphique modeGraphique = new ModeGraphique(carte);
                    modeGraphique.afficher();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public Carte getCarte() {
        return carte ;
    }

    public void afficher() {
        setVisible(true);
    }

    public void fermer() {
        dispose();
    }
}