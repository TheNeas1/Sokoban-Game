package modele;


public enum Direction {
    HAUT(0),
    BAS(1),
    GAUCHE(2),
    DROITE(3);

    private int valeur;

    Direction(int valeur) {
        this.valeur = valeur;
    }

    // Méthode pour obtenir l'incrément d'abscisse associé à une direction
    public int incrementAbscisse() {
        return Direction.INC_ABRISSEE[this.valeur];
    }

    // Méthode pour obtenir l'incrément d'ordonnée associé à une direction
    public int incrementOrdonnee() {
        return Direction.INC_ORDONNEE[this.valeur];
    }

    // Tableaux d'incréments pour chaque direction
    private static final int[] INC_ABRISSEE = { -1, 1, 0, 0 };
    private static final int[] INC_ORDONNEE = { 0, 0, -1, 1 };
}

