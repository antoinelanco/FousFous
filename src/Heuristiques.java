
public class Heuristiques {

	public Heuristiques() {
	}

	public int eval(PlateauFousFous Plateau, String player) {

		if (player.split("")[0].equals("b")) {
			return Plateau.getPionBlanc() - Plateau.getPionNoir();
		} else {
			return Plateau.getPionNoir() - Plateau.getPionBlanc();
		}
	}

}
