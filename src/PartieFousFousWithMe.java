import java.io.IOException;

public class PartieFousFousWithMe {
	public static void main(String[] args) throws IOException, InterruptedException {
		String jBlanc = "blanc";
		String jNoir = "noir";

		String[] lesJoueurs = { jBlanc, jNoir };
		Heuristiques h = new Heuristiques();
		AlphaBeta algoNoir = new AlphaBeta(h, jNoir, jBlanc, 6);

		PlateauFousFous Plateau = new PlateauFousFous();
		int jnum = 0;
		Plateau.AfficheGUI();
		String move = null;
		while (!Plateau.finDePartie()) {

			System.out.println("Joueur " + lesJoueurs[jnum]);
			Plateau.RefreshGUI();
			Plateau.AffichePlateau();
			System.out.println("Les mouvement possibles :");
			Plateau.AfficheMovePossible(lesJoueurs[jnum]);

			if (jnum == 0) {
				while (!Plateau.moveable()) {
					Thread.sleep(100);
				}
				move = Plateau.move();
				Plateau.setmoveablefalse();
				if (Plateau.estValide(move, lesJoueurs[jnum])) {
					Plateau.play(move, lesJoueurs[jnum]);
					jnum = 1 - jnum;
				} else {
					System.out.println("Mouvement non valide");
				}
				System.out.println("\n");
			} else {
				String bestMove = algoNoir.meilleurCoup(Plateau);
				System.out.println("Mouvement play " + bestMove);
				Plateau.play(bestMove, jNoir);
				jnum = 1 - jnum;
			}
		}
		Plateau.RefreshGUI();
		System.out.println("Le joueur " + lesJoueurs[1 - jnum] + " a gg");
	}
}
