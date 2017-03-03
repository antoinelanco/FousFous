import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PartieFousFousWithMe {
	public static void main(String[] args) throws IOException, InterruptedException {
		String jBlanc = "blanc";
		String jNoir = "noir";
		
		String[] lesJoueurs = { jBlanc, jNoir };
		Heuristiques h = new Heuristiques();
		AlphaBeta algoNoir = new AlphaBeta(h, jNoir, jBlanc, 4);

		PlateauFousFous Plateau = new PlateauFousFous();
		Graph GUI = new Graph(Plateau);
		int jnum = 0;
		String move = null;
		while (!Plateau.finDePartie()) {

			System.out.println("Joueur " + lesJoueurs[jnum]);
			GUI.refresh(lesJoueurs[1 - jnum]);
			Plateau.AffichePlateau();
			System.out.println("Les mouvement possibles :");
			Plateau.AfficheMovePossible(lesJoueurs[jnum]);

			if (jnum == 0) {
				while (!GUI.getmoveable()) {
					Thread.sleep(100);
				}
				move = GUI.getmove();
				GUI.setmoveablefalse();
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
		GUI.refresh(lesJoueurs[1 - jnum]);
		GUI.Victoire(lesJoueurs[1 - jnum]);
	}
}
