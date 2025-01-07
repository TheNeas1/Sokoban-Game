package modele;

class Caisse extends Element {
    private Element elementDessous;

    public Caisse() {
        super('$'); // Caractère représentant une caisse
        this.elementDessous = new Sol(); // Element en dessous de la caisse 
    }

    // Changer l'element sous la caisse  
    public void setElementDessous(Element element) {
        this.elementDessous = element;
    }

    // Prendre Element en dessous de la caisse 
    public Element getElementDessous() {
        return elementDessous;
    }

}
