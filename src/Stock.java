// PENSEZ A INDIQUER PAR DES COMMENTAIRES LES MODIFICATIONS APPORTEES A CE SQUELETTE AU FUR
// ET A MESURE DE L'EVOLUTION DU CODE DEMANDEE DANS LE TP.

/**
 * Les objets instances de la classe Stock representent un ensemble de pieces, 
 * empilees les unes sur les autres. Du fait de la disposition en piles, il n'est
 * pas possible que deux operateurs saisissent deux pieces au meme moment. 
 *
 */
class Stock {
	/**
	 * Nombre de pieces dans la pile
	 */
    private int nbPieces;
    /**
	 * Nombre maximum de pièce
	 */
    private int nbMaxPieces;
    /**
     * Le nom du stock
     */
    private String nom;

    /**
     * Creer un nouvel objet instance de stock
     * @param nom Le nom du nouveau stock
     * @param nbPieces Le nombre de pieces initial
     */
    public Stock(String nom, int nbPieces, int nbMaxPieces) {
        this.nbPieces = nbPieces;
        this.nbMaxPieces = nbMaxPieces;
        this.nom = nom;		
    }

    /**
     * Poser une piece sur le haut de la pile de pieces
     */
    public synchronized void stocker() {
    	while(nbPieces >= nbMaxPieces){
        	try{
        		this.wait();
	    	}
	    	catch(Exception ex){}    		
    	}
        nbPieces++;
        this.notifyAll();
        this.afficher();
    }

    /**
     * Saisir une piece sur le haut de la pile de pieces
     * @throws InterruptedException 
     */
    public synchronized void destocker() {
    	while(nbPieces == 0){
        	try{
        		this.wait();
	    	}
	    	catch(Exception ex){}
    	}
        nbPieces--;
        this.notifyAll();
        this.afficher();    	
    }

    /**
     * Affiche l'etat de l'objet stock
     */
    public void afficher() {
        System.out.println(Thread.currentThread().getName() + ": Le stock " + nom + " contient " + nbPieces + " piece(s).");
    }

    /** 
     * Methode d'auto-test pour la classe Stock
     * @param args Non utilise
     */
    static public void main(String[] args) {
    	
    	Stock monStock = new Stock("Mon stock", 0, 10);
    	
    	monStock.afficher();
    	monStock.stocker();
    	monStock.afficher();
    	monStock.destocker();
    	monStock.afficher();
    	
    }
}
