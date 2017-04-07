package fousfous;
import fousfous.PlateauFousFous;

public class AlphaBeta {

	private final static int PROFMAXDEFAUT = 6;
	private int profMax = PROFMAXDEFAUT;
	private Heuristiques h;
	private String joueurMin;
	private String joueurMax;

	public AlphaBeta(Heuristiques h, String joueurMax, String joueurMin) {
		this(h, joueurMax, joueurMin, PROFMAXDEFAUT);
	}

	public AlphaBeta(Heuristiques h, String joueurMax, String joueurMin, int profMaxi) {
		this.h = h;
		this.joueurMin = joueurMin;
		this.joueurMax = joueurMax;
		this.profMax = profMaxi;
	}

	public String meilleurCoup(PlateauFousFous plateau) {
		
		String meilleurCoup = plateau.mouvementPossibles(joueurMax)[0];
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;

		for (String coup : plateau.mouvementPossibles(this.joueurMax)) {
			if (coup != null) {

				PlateauFousFous tmp = plateau.copy();
				tmp.play(coup, this.joueurMax);
				int Max = maxMin(tmp, this.profMax - 1, alpha, beta);
				System.out.println("coup: "+coup+", heuristique : "+Max);
				if (alpha < Max) {
					alpha = Max;
					meilleurCoup = coup;

				}
			}
		}
		
		System.out.println("le meilleur coup est: " + meilleurCoup);
		return meilleurCoup;
	}

	public String toString() {
		return "AlphaBeta (ProfMax=" + profMax + ")";
	}

	private int maxMin(PlateauFousFous plateau, int prof, int alpha, int beta) {
		if (plateau.finDePartie() || prof == 0) {
			return h.eval(plateau, this.joueurMax);
		} else {
			for (String c : plateau.mouvementPossibles(this.joueurMax)) {
				if (c != null) {
					PlateauFousFous tmp = plateau.copy();
					tmp.play(c, this.joueurMax);
					alpha = Math.max(alpha, minMax(tmp, prof - 1, alpha, beta));
					if (alpha >= beta) {
						return beta;
					}
				}
			}
			return alpha;
		}
	}
	
	private int minMax(PlateauFousFous plateau, int prof, int alpha, int beta) {
		if (plateau.finDePartie() || prof == 0) {
			System.out.println("heuristique max: " +this.h.eval(plateau, this.joueurMax));
			System.out.println("heuristique min: " +this.h.eval(plateau, this.joueurMin));
			return this.h.eval(plateau, this.joueurMin);
		} else {
			for (String c : plateau.mouvementPossibles(this.joueurMin)) {
				if (c != null) {
					PlateauFousFous tmp = plateau.copy();
					tmp.play(c, this.joueurMin);
					beta = Math.min(beta, maxMin(tmp, prof - 1, alpha, beta));
					if (alpha >= beta) {
						return alpha;
					}
				}
			}
			return beta;
		}
	}
	
}
