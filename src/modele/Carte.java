package modele;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Carte {

    private Element[][] elements;
    private Robot robot ;
    private int destination = 0 ;
    private int niveau ;

    public Carte(int niveau) throws IOException {
        String filePath = "bin/map/map" + niveau + ".txt";
        Path path = Paths.get(filePath);
        
        if (!Files.exists(path)) {
            throw new IOException("La carte n'existe pas : " + filePath);
        }
        
        Lecture lecture = new Lecture(filePath);
        this.elements = creerCarte(lecture);
        this.niveau = niveau ;
    }

    public void setDestination(){
        destination = 0 ;
    }

    public int getNiveau(){
        return niveau ;
    }

    public Element getElements(int i , int j) {
        return elements[i][j];
    }

    public int getNombreLignes() {
        return elements.length;
    }

    public int getNombreColonnes() {
        // On suppose que toutes les lignes ont la même longueur, donc on peut utiliser la longueur de la première ligne
        if (elements.length > 0) {
            return elements[0].length;
        } else {
            return 0; // Retourne 0 si la carte est vide
        }
    }

    private Element[][] creerCarte(Lecture lecture) {
        Element[][] carte = new Element[lecture.getNombreLignes()][lecture.getTailleLignes()];

        for (int i = 0; i < lecture.getNombreLignes(); i++) {
            String ligne = lecture.getLignes().get(i);
            for (int j = 0; j < ligne.length(); j++) {
                char symbole = ligne.charAt(j);
                switch (symbole) {
                    case '#':
                        carte[i][j] = new Mur();
                        break;
                    case '/':
                        carte[i][j] = new Vide();
                        break;
                    case ' ':
                        carte[i][j] = new Sol();
                        break;
                    case '$':
                        carte[i][j] = new Caisse();
                        break;
                    case '.':
                        carte[i][j] = new Destination();
                        destination++;
                        break;
                    case '@':
                        robot = new Robot(i,j) ;
                        carte[i][j] = robot;
                        break;
                }
            }
        }

        return carte;
    }

    public void deplacerRobot(Direction direction){
        int nextX = robot.getPositionX() + direction.incrementAbscisse();
        int nextY = robot.getPositionY() + direction.incrementOrdonnee();
        

        if (estPositionValide(nextX, nextY)) {
            Element nextElement = elements[nextX][nextY];
            
            // Vérifier si le prochain élément est un sol, une destination ou une CaisseDestination
            if (nextElement instanceof Sol || nextElement instanceof Destination || nextElement instanceof CaisseDestination) {
                elements[robot.getPositionX()][robot.getPositionY()] = robot.getElementDessous();
                robot.setElementDessous(elements[nextX][nextY]);
                elements[nextX][nextY] = robot;
                robot.deplacer(direction);
            }
            // Vérifier si le prochain élément est une Caisse
            else if (nextElement instanceof Caisse){
                if(deplacerCaisse(direction, nextX, nextY)){
                    elements[robot.getPositionX()][robot.getPositionY()] = robot.getElementDessous();
                    robot.setElementDessous(elements[nextX][nextY]);
                    elements[nextX][nextY] = robot;
                    robot.deplacer(direction);
                }else {
                    return ;
                }
                
            }
        }
    }

    private boolean deplacerCaisse(Direction direction,int i ,int j){
        int nextCaisseX = i + direction.incrementAbscisse();
        int nextCaisseY = j + direction.incrementOrdonnee();

        if(estPositionValide(nextCaisseX, nextCaisseY)){
            Element nextElement = elements[nextCaisseX][nextCaisseY];
            Element caisse = elements[i][j];

            // Vérifier si le prochain élément est un sol ou une CaisseDestination
            if (nextElement instanceof Sol || nextElement instanceof CaisseDestination) {
                elements[i][j] = caisse.getElementDessous() ;
                caisse.setElementDessous(nextElement);
                elements[nextCaisseX][nextCaisseY] = caisse;
                return true ;
            }
            // Vérifier si le prochain élément est une Destination
            else if (nextElement instanceof Destination){
                elements[i][j] = caisse.getElementDessous() ;
                elements[nextCaisseX][nextCaisseY] = new CaisseDestination();
                destination--;
                return true ;
            }else{
                return false ;
            }
        }
        else{
            return false ;
        }

    }

    private boolean estPositionValide(int nextX, int nextY){
        return nextX >= 0 && nextX < elements.length && nextY >= 0 && nextY < elements[0].length ;
    }
    
    public boolean finDePartie(){
        return destination == 0 ;
    }

    public void afficherCarte() {
        for (Element[] ligne : elements) {
            for (Element element : ligne) {
                System.out.print(element.getSymbole());
            }
            System.out.println();
        }
    }
}
