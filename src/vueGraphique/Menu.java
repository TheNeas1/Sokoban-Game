package vueGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JButton startButton;
    private JButton quitterButton;

    public Menu() {
        super("Sokoban");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel titleLabel = new JLabel("Sokoban");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);
        
        startButton = new JButton("Start");
        quitterButton = new JButton("Quitter");

        panel.add(startButton);
        panel.add(quitterButton);

        add(panel);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectionNiveau selectionNiveau = new SelectionNiveau();
                fermer();
                selectionNiveau.afficher();
            }
        });

        quitterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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