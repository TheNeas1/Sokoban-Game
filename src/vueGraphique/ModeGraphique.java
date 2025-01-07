package vueGraphique;

import modele.Carte;
import modele.Direction;
import modele.Element;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ModeGraphique extends JFrame {

    private JPanel panelCarte;
    private HashMap<Character, ImageIcon> imageMap;
    private Carte carte;
    private int largeurCellule = 100; // Largeur de la cellule de la carte
    private int hauteurCellule = 100; // Hauteur de la cellule de la carte

    public ModeGraphique(Carte carte) {
        super("Sokoban");
        this.carte = carte;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        loadImages();
        afficherCarte();
        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                try {
                    deplacerRobot(e);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        setFocusable(true);
        requestFocusInWindow();
        setResizable(false);
        centerWindow();
        cheat();
    }

    private void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    private void initComponents() {
        panelCarte = new JPanel(new GridLayout(carte.getNombreLignes(), carte.getNombreColonnes(), 0, 0));

        add(panelCarte);
        // Définir la taille de la fenêtre en fonction de la taille de la carte
        setSize(carte.getNombreColonnes() * largeurCellule, carte.getNombreLignes() * hauteurCellule);

        addMenuBar();
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem restartMenuItem = new JMenuItem("Restart");
        JMenuItem quitMenuItem = new JMenuItem("Quitter");

        restartMenuItem.addActionListener(new ActionListener() {
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

        quitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        optionsMenu.add(restartMenuItem);
        optionsMenu.add(quitMenuItem);
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);
    }

    private void loadImages() {
        imageMap = new HashMap<>();
        try {
            imageMap.put('#', resizeImage(ImageIO.read(new File("bin/img/mur.gif"))));
            imageMap.put(' ', resizeImage(ImageIO.read(new File("bin/img/sol.gif"))));
            imageMap.put('$', resizeImage(ImageIO.read(new File("bin/img/caisse1.gif"))));
            imageMap.put('*', resizeImage(ImageIO.read(new File("bin/img/caisse2.gif"))));
            imageMap.put('.', resizeImage(ImageIO.read(new File("bin/img/but.gif"))));
            imageMap.put('@', resizeImage(ImageIO.read(new File("bin/img/Haut.gif"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImageIcon resizeImage(Image image) {
        // Ajuster la taille de l'image pour correspondre à la taille de la cellule
        return new ImageIcon(image.getScaledInstance(largeurCellule, hauteurCellule, Image.SCALE_SMOOTH));
    }

    private void afficherCarte() {
        panelCarte.removeAll();
        for (int i = 0; i < carte.getNombreLignes(); i++) {
            for (int j = 0; j < carte.getNombreColonnes(); j++) {
                Element element = carte.getElements(i, j);
                JLabel label = new JLabel(imageMap.get(element.getSymbole()));
                label.setBorder(BorderFactory.createEmptyBorder()); // Définition de la bordure vide
                panelCarte.add(label);
            }
        }
        panelCarte.revalidate();
        panelCarte.repaint();
    }


    private void deplacerRobot(KeyEvent event) throws IOException {
        int keyCode = event.getKeyCode();
        Direction direction = null;

        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_Z:
                direction = Direction.HAUT;
                imageMap.replace('@', resizeImage(ImageIO.read(new File("bin/img/Haut.gif"))));
                imageMap.put('+', imageMap.get('@'));
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                direction = Direction.BAS;
                imageMap.replace('@', resizeImage(ImageIO.read(new File("bin/img/Bas.gif"))));
                imageMap.put('+', imageMap.get('@'));
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_Q:
                direction = Direction.GAUCHE;
                imageMap.replace('@', resizeImage(ImageIO.read(new File("bin/img/Gauche.gif"))));
                imageMap.put('+', imageMap.get('@'));
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                direction = Direction.DROITE;
                imageMap.replace('@', resizeImage(ImageIO.read(new File("bin/img/Droite.gif"))));
                imageMap.put('+', imageMap.get('@'));
                break;
            default:
                return;
        }

        carte.deplacerRobot(direction);
        afficherCarte();
        verifierFinDePartie();
    }

    public void afficher() {
        setVisible(true);
    }

    public void fermer() {
        dispose();
    }

    private void verifierFinDePartie() {
        if (carte.finDePartie()) {
            FinDePartie finDePartie = new FinDePartie(carte);
            finDePartie.afficher();
            fermer();
        }
    }


    // CTRL + M pour finir le niveau directement 
    private void cheat() {

        // Création de l'action pour finir le jeu
        Action endGame = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                carte.setDestination();
                verifierFinDePartie();
            }
        };
    
        // Obtention de l'InputMap du conteneur principal (par exemple, JFrame)
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        // Définition de la combinaison de touches pour "M"
        KeyStroke keyStrokeM = KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK);

        // Associer la combinaison de touches "M" à l'action
        inputMap.put(keyStrokeM, "M");
        this.getRootPane().getActionMap().put("M", endGame);

    }


}
