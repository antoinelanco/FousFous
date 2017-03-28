package fousfous;

public class JoueurSuperFort implements IJoueur{

	private PlateauFousFous Plateau;
	private int Ennemi;
	private String Name = "Lanco_Trinkl";
	private int mycolour; 
	private String colorA;
	private String colorE;
	private AlphaBeta Algo;
	
	@Override
	public void initJoueur(int mycolour) {
		this.mycolour = mycolour;
		this.Plateau = new PlateauFousFous();
		
		this.colorA = (mycolour == -1 ? "blanc" : "noir");
		this.colorE = (mycolour == -1 ? "noir" : "blanc");
		this.Algo = new AlphaBeta(new Heuristiques(), this.colorA, this.colorE, 4);
	}

	@Override
	public int getNumJoueur() {
		return this.mycolour;
	}

	@Override
	public String choixMouvement() {
		String best = this.Algo.meilleurCoup(this.Plateau);
		this.Plateau.play(best, this.colorA);
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
