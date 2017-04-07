package fousfous;

/** import java.util.Scanner; **/

public class JoueurSuperFort implements IJoueur {

	private PlateauFousFous Plateau;
	private String Name = "Lanco_Trinkl";
	private int mycolour;
	private String colorA;
	private String colorE;
	private NegEchecAlphaBetaMem Algo;
	private int profondeur = 6;
	
	/** Scanner in = new Scanner (System.in); **/
	@Override
	public void initJoueur(int mycolour) {
		this.mycolour = mycolour;
		this.Plateau = new PlateauFousFous();
		this.colorA = mycolour == -1 ? "blanc" : "noir";
		this.colorE = mycolour == -1 ? "noir" : "blanc";
		this.Algo = new NegEchecAlphaBetaMem(new Heuristiques(), this.colorA, this.colorE,this.profondeur);

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
		int movepossible = Plateau.mouvementPossibles(this.colorA).length;
		if (movepossible > 0) {
			if (movepossible < 7){
				this.profondeur = 15;
			}else{
				if (movepossible < 10){
					this.profondeur = 11;
				} else {
					if (movepossible < 20){
						this.profondeur = 8;
					} else {
						if (movepossible < 30){
							this.profondeur = 6;
						}
					}
				}
			}
			System.out.println("profondeur: " + this.profondeur);
			System.out.println(movepossible+ " coups: ");	
			this.Algo = new NegEchecAlphaBetaMem(new Heuristiques(), this.colorA, this.colorE,this.profondeur);
			String best = this.Algo.meilleurCoup(this.Plateau);
			for (int i = 0; i < movepossible; i++) {
				System.out.print(this.Plateau.mouvementPossibles(this.colorA )[i]+ " | ");
			}
			System.out.println();
			System.out.println("mon coup est : " + best);
			this.Plateau.play(best, this.colorA);
			System.out.println("Voici mon plateau de jeu apres mon coup :");
			this.Plateau.AffichePlateau();
			System.out.print("Coup adversair : ");
			for(String couppp : this.Plateau.mouvementPossibles(this.colorE)){
				System.out.print(couppp+" | ");
			}
			System.out.println("");
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
