package fousfous;

/** import java.util.Scanner; **/

public class JoueurSuperFort implements IJoueur {

	private PlateauFousFous Plateau;
	private int Ennemi;
	private String Name = "Lanco_Trinkl";
	private int mycolour;
	private String colorA;
	private String colorE;
	private AlphaBeta Algo;
	
	/** Scanner in = new Scanner (System.in); **/
	@Override
	public void initJoueur(int mycolour) {
		this.mycolour = mycolour;
		this.Ennemi = mycolour * -1;
		this.Plateau = new PlateauFousFous();
		this.colorA = mycolour == -1 ? "blanc" : "noir";
		this.colorE = mycolour == -1 ? "noir" : "blanc";
		this.Algo = new AlphaBeta(new Heuristiques(), this.colorA, this.colorE,6);

	}

	@Override
	public int getNumJoueur() {
		return this.mycolour;
	}

	@Override
	public String choixMouvement() {
		System.out.println("Voici mon plateau de jeu avant de choisir mon coup :");
		this.Plateau.AffichePlateau();
		System.out.println();
		System.out.print(this.Plateau.mouvementPossibles(this.colorA).length+ " coups: ");
		if (this.Plateau.mouvementPossibles(colorA).length > 0) {
			String best = this.Algo.meilleurCoup(this.Plateau);
			for (int i = 0; i < this.Plateau.mouvementPossibles(this.colorA).length; i++) {
				System.out.print(this.Plateau.mouvementPossibles(this.colorA)[i]+ " | ");
			}
			System.out.println();
			System.out.println("mon coup est : " + best);
			this.Plateau.play(best, this.colorA);
			System.out.println("Voici mon plateau de jeu apres mon coup :");
			this.Plateau.AffichePlateau();
			return best;
		}
		return "xxxxx";
		/**
		 * String str = in.nextLine(); return str;
		 */
	}

	@Override
	public void declareLeVainqueur(int colour) {
		if (colour == this.mycolour) {
			System.out.println("Oui c'est Gagne !!!");
		}
	}

	@Override
	public void mouvementEnnemi(String coup) {
		System.out.println("le joueur adverse joue le coup : " + coup);
		this.Plateau.play(coup, this.colorE);
	}

	@Override
	public String binoName() {
		return this.Name;
	}

}
