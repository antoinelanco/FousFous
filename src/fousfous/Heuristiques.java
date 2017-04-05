package fousfous;
import fousfous.PlateauFousFous;

public class Heuristiques {

	public Heuristiques() {
	}

	public int eval(PlateauFousFous plateau, String player) {

		if (player.substring(0, 1).equals("b")) {
			if(plateau.getPionNoir()==0){
				return 10000;
			}
			return plateau.getPionBlanc();
		} else {
			if(plateau.getPionBlanc()==0){
				return 10000;
			}
			return plateau.getPionNoir();
		}
	}

}
