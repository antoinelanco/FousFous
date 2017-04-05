package fousfous;
import fousfous.PlateauFousFous;

public class Heuristiques {

	public Heuristiques() {
	}

	public int eval(PlateauFousFous plateau, String player) {

		if (player.substring(0, 1).equals("b")) {
			System.out.println("pion noir est: " + plateau.getPionNoir());
			if(plateau.getPionNoir()==0){
				return 10000;
			}
			return plateau.getPionBlanc();
		} else {
			System.out.println("pion blanc est: " + plateau.getPionBlanc());
			if(plateau.getPionBlanc()==0){
				return 10000;
			}
			return plateau.getPionNoir();
		}
	}

}
