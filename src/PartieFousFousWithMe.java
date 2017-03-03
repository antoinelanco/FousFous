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
			GUI.refresh();
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
		GUI.refresh();
		System.out.println("Le joueur " + lesJoueurs[1 - jnum] + " a gg");
		JFrame fenetre = new JFrame();
		fenetre.setTitle("Victoire!");
		fenetre.pack();
		fenetre.setSize(400, 100);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Le joueur " + lesJoueurs[1 - jnum] + " a gg");
		panel.add(label);
		fenetre.setContentPane(panel);
		fenetre.setVisible(true);
	}
}
