import java.io.IOException;
import java.util.Scanner;

public class PartieFousFousWithMe {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		String jBlanc = "blanc";
		String jNoir = "noir";

		String[] lesJoueurs = { jBlanc, jNoir };
		Heuristiques h = new Heuristiques();
		AlphaBeta algoNoir = new AlphaBeta(h, jNoir, jBlanc, 6);

		PlateauFousFous Plateau = new PlateauFousFous();
		int jnum = 0;
		Scanner input = new Scanner(System.in);
		while (!Plateau.finDePartie()) {

			System.out.println("Joueur " + lesJoueurs[jnum]);
			Plateau.AffichePlateau();
			System.out.println("Les mouvement possibles :");
			Plateau.AfficheMovePossible(lesJoueurs[jnum]);

			if (jnum == 0) {
				System.out.print("Move :");
				String move = input.nextLine();
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
	}
}
