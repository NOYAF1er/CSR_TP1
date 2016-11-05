// PENSEZ A INDIQUER PAR DES COMMENTAIRES LES MODIFICATIONS APPORTEES A CE SQUELETTE AU FUR
// ET A MESURE DE L'EVOLUTION DU CODE DEMANDEE DANS LE TP.

/**
 * Les objets instances de la classe Usine represente une usine avec deux ateliers.
 * Une instance d'Usine possede un stock de pieces a transformer ainsi qu'un stock
 * de pieces finies initialement vide. Chacun des deux ateliers transforme la moitie
 * des unites du stock a transformer. 
 * La methode fonctionner() fait travailler successivement les deux ateliers et affiche
 * l'etat des stocks a la fin des travaux.
 */
class Usine {
	/**
	 * Stock de pieces a transformer
	 */
    Stock stockDepart = new Stock("de depart", 10, 10);
    /**
     * Stock de pieces transformees
     */
    Stock stockFin = new Stock("d'arrivee", 0, 10);
    /**
     * Stock de pieces semi-fini
     */
    Stock stockInter = new Stock("intermédiaire", 0, 1);
    /**
     * Ateliers de transformation
     */
    Atelier atelier11 = new Atelier(stockDepart, stockInter, 5);
    Atelier atelier12 = new Atelier(stockDepart, stockInter, 5);
    Atelier atelier2 = new Atelier(stockInter, stockFin, 5);
    Atelier atelier3 = new Atelier(stockInter, stockFin, 5);
    
    /**
     * Effectuer le travail de l'usine
     * Utilise successivement chaque atelier pour transformer une piece et affiche
     * l'evolution de l'etat des stocks.
     */
    public void fonctionner() {

    		atelier2.start();
    		atelier3.start();
    		atelier11.start();
    		atelier12.start();
    		
    		try {
    			atelier3.join();
				atelier2.join();
				atelier11.join();
				atelier12.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    		System.out.println("******************************");
    		stockDepart.afficher();
    		stockFin.afficher();
    		System.out.println("******************************");
    }
    
    /**
     * Point d'entree pour l'ensemble du TP.
     * @param args Non utilise
     */
    public static void main(String[] args) {

    	Usine monUsine = new Usine();
    	
    	monUsine.fonctionner();

    }
}
