package modele;

class Vide extends Element {

    public Vide() {
        super('/'); // Caractère représentant le sol
    }

    public void setElementDessous(Element element) {
        return ;
    }

    public Element getElementDessous() {
        return null ;
    }

}