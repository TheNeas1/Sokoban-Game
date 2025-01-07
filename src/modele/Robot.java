package modele;

class Robot extends Element {

    private Element elementDessous;
    private int positionX;
    private int positionY;
    
        public Robot(int positionX, int positionY) {
            super('@'); // Caractère représentant un Robot
            this.elementDessous = new Sol();
            this.positionX = positionX;
            this.positionY = positionY;
        }
    
        public void setElementDessous(Element element) {
            this.elementDessous = element;
        }
    
        public Element getElementDessous() {
            return elementDessous;
        }

        /// --- position robot --- ///

        public int getPositionX() {
            return positionX;
        }
    
        public int getPositionY() {
            return positionY;
        }
    
        // Méthode pour déplacer le robot dans une direction donnée
        public void deplacer(Direction direction) {
            positionX += direction.incrementAbscisse();
            positionY += direction.incrementOrdonnee();
            if(getElementDessous() instanceof Destination){
                setSymbole('+');
            }else{
                setSymbole('@');
            }
        }

}