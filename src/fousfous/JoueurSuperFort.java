package fousfous;

public class JoueurSuperFort implements IJoueur{

	private PlateauFousFous Plateau;
	private int Ennemi;
	private String Name = "Lanco_Trinkl";
	private int mycolour; 
	private AlphaBeta Algo;
	
	@Override
	public void initJoueur(int mycolour) {
		this.mycolour = mycolour;
		this.Plateau = new PlateauFousFous();
		this.Algo = new AlphaBeta(new Heuristiques(), (this.mycolour == -1 ? "BLANC" : "NOIR"), (this.mycolour == 1 ? "BLANC" : "NOIR"), 4);
		
	}

	@Override
	public int getNumJoueur() {
		return this.mycolour;
	}

	@Override
	public String choixMouvement() {
		String best = this.Algo.meilleurCoup(this.Plateau);
		System.out.println(best);
		this.Plateau.play(best, (this.mycolour == -1 ? "BLANC" : "NOIR"));
		return best;
	}

	@Override
	public void declareLeVainqueur(int colour) {
		if(colour == this.mycolour){
			System.out.println("Oui c'est Gagne !!!");
		}
	}

	@Override
	public void mouvementEnnemi(String coup) {
		this.Plateau.play(coup, Integer.toString(Ennemi));
	}

	@Override
	public String binoName() {
		return this.Name;
	}

}
