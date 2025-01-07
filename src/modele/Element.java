package modele;

public abstract class Element {
    char symbole;

    public Element(char symbole) {
        this.symbole = symbole;
    }

    public char getSymbole() {
        return symbole;
    }

    public void setSymbole(char symbole) {
        this.symbole = symbole;
    }

    public abstract void setElementDessous(Element element);
    public abstract Element getElementDessous();
}







