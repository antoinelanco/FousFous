package fousfous;

import Algo.AlphaBeta;
import Algo.MiniMax;

public class JoueurSuperFort implements IJoueur {

	private PlateauFousFous Plateau;
	private String Name = "Lanco_Trinkl";
	private int mycolour;
	private String colorA;
	private String colorE;
	private MiniMax algo;
	private AlphaBeta algo2;
	private int profondeur;
	
	@Override
	public void initJoueur(int mycolour) {
		this.mycolour = mycolour;
		this.Plateau = new PlateauFousFous();
		this.colorA = mycolour == -1 ? "blanc" : "noir";
		this.colorE = mycolour == -1 ? "noir" : "blanc";
		this.algo = new MiniMax(new Heuristiques(), this.colorA, this.colorE, this.profondeur);
		this.algo2 = new AlphaBeta(new Heuristiques(), this.colorA, this.colorE, this.profondeur);
		
	}

	@Override
	public int getNumJoueur() {
		return this.mycolour;
	}

	@Override
	public String choixMouvement() {
		
		String best;
		System.out.println("Voici mon plateau de jeu avant de choisir mon coup :");
		this.Plateau.AffichePlateau();
		System.out.println();
		int movePossible = Plateau.mouvementPossibles(this.colorA).length;
		System.out.print(movePossible+ " coups possibles: ");	
		for (int i = 0; i < movePossible; i++) {
			System.out.print(this.Plateau.mouvementPossibles(this.colorA )[i]+ " | ");
		}
		System.out.println();
		if (movePossible > 20){
			this.profondeur = 7;
			System.out.println("mon algo est alphaBeta et la profondeur est de " + this.profondeur);
			this.algo2 = new AlphaBeta(new Heuristiques(), this.colorA, this.colorE,this.profondeur);
			best = this.algo2.meilleurCoup(this.Plateau);
		} else {
			if ( movePossible > 10){
				this.profondeur = 5;
			} else {
				this.profondeur = 6;
			}
			System.out.println("mon algo est minimax et la profondeur est de " + this.profondeur);
			this.algo = new MiniMax(new Heuristiques(), this.colorA, this.colorE,this.profondeur);
			best = this.algo.meilleurCoup(this.Plateau);
		}
		
		System.out.println("mon coup est : " + best);
		this.Plateau.play(best, this.colorA);
		System.out.println("Voici mon plateau de jeu apres mon coup :");
		this.Plateau.AffichePlateau();
		return best;
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
