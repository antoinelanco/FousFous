package fousfous;

public class JoueurSuperFort implements IJoueur{

	private PlateauFousFous Plateau;
	private int Ennemi;
	private String Name = "Lanco";
	private int mycolour; 
	
	@Override
	public void initJoueur(int mycolour) {
		this.mycolour = mycolour;
		
	}

	@Override
	public int getNumJoueur() {
		return this.mycolour;
	}

	@Override
	public String choixMouvement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void declareLeVainqueur(int colour) {
		if(colour == this.mycolour){
			System.out.println("Oui c'est Gagné !!!");
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
