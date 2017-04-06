package fousfous;
import fousfous.PlateauFousFous;

public class Heuristiques {

	public Heuristiques() {
	}

	public int eval(PlateauFousFous plateau, String player) {

		if (player.substring(0, 1).equals("b")) {
			if(plateau.getPionNoir()==0){
				return (10000 + plateau.getPionBlanc());
			}
			return (int) (plateau.getPionBlanc() * plateau.ScoreDiag(player)) - (plateau.getPionNoir()*20) ;
		} else {
			if(plateau.getPionBlanc()==0){
				return (10000 + plateau.getPionNoir());
			}
			return (int) (plateau.getPionNoir() * plateau.ScoreDiag(player)) - (plateau.getPionBlanc()*20);
		}
	}

}
