package fousfous;
import fousfous.PlateauFousFous;

public class Heuristiques {

	public Heuristiques() {
	}

	public int eval(PlateauFousFous Plateau, String player) {

		if (player.substring(0, 1).equals("b")) {
			return Plateau.getPionBlanc() - (Plateau.getPionNoir()*2);
		} else {
			return Plateau.getPionNoir() - (Plateau.getPionBlanc()*2);
		}
	}

}
