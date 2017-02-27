import java.io.IOException;

public class PartieFousFous {
	public static void main(String[] args) throws IOException {
		String jBlanc = "blanc";
		String jNoir = "noir";

		String[] lesJoueurs = { jBlanc, jNoir };
		Heuristiques h = new Heuristiques();
		AlphaBeta algoNoir = new AlphaBeta(h, jNoir, jBlanc, 6);
		AlphaBeta algoBlanc = new AlphaBeta(h, jBlanc, jNoir, 1);
		AlphaBeta[] algo = { algoBlanc, algoNoir };

		PlateauFousFous Plateau = new PlateauFousFous();
		int jnum = 0;
		Plateau.AfficheGUI();
		while (!Plateau.finDePartie()) {

			System.out.println("Joueur " + lesJoueurs[jnum]);
			Plateau.AffichePlateau();
			Plateau.RefreshGUI();
			System.out.println("Les mouvement possibles :");
			Plateau.AfficheMovePossible(lesJoueurs[jnum]);

			String bestMove = algo[jnum].meilleurCoup(Plateau);
			System.out.println("Mouvement play " + bestMove);
			Plateau.play(bestMove, lesJoueurs[jnum]);
			jnum = 1 - jnum;
		}
		System.out.println("Le joueur " + lesJoueurs[1 - jnum] + " a gg");
	}
}
