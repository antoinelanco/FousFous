package fousfous;
import fousfous.PlateauFousFous;

public class AlphaBeta {

	private final static int PROFMAXDEFAUT = 4;
	private int profMax = PROFMAXDEFAUT;
	private Heuristiques h;
	private String joueurMin;
	private String joueurMax;
	private int nbnoeuds;
	private int nbfeuilles;

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

		this.nbfeuilles = 0;
		this.nbnoeuds = 0;
		
		String meilleurCoup = plateau.mouvementPossibles(joueurMax)[0];
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		int MH = Integer.MIN_VALUE;

		for (String coup : plateau.mouvementPossibles(this.joueurMax)) {
			if (coup != null) {
				this.nbnoeuds++;
				PlateauFousFous tmp = plateau.copy();
				tmp.play(coup, this.joueurMax);
				int Max = minMax(tmp, this.profMax - 1, alpha, beta);
				System.out.println("mh : "+Max);
				if (alpha < Max) {
					alpha = Max;
					MH = alpha;
					meilleurCoup = coup;

				}
			}
		}
		
		// System.out.println("Prof :"+this.profMax);
		//System.out.println("Nb Noeuds :" + this.nbnoeuds);
		//System.out.println("Nb Feuilles :" + this.nbfeuilles);
		System.out.println("meilleur coup potentiel :" + MH);
		System.out.println("le meilleur coup est: " + meilleurCoup);

		return meilleurCoup;
	}

	public String toString() {
		return "AlphaBeta (ProfMax=" + profMax + ")";
	}

	private int maxMin(PlateauFousFous plateau, int prof, int alpha, int beta) {
		if (plateau.finDePartie() || prof == 0) {
			this.nbfeuilles++;
			//System.out.println("eval h "+h.eval(Plateau, this.joueurMax));
			return h.eval(plateau, this.joueurMax);
		} else {
			for (String c : plateau.mouvementPossibles(this.joueurMax)) {
				if (c != null) {
					this.nbnoeuds++;
					PlateauFousFous tmp = plateau.copy();
					tmp.play(c, this.joueurMax);
					alpha = Math.max(alpha, minMax(tmp, prof - 1, alpha, beta));
					System.out.print("coup: " + c + " | ");
					System.out.print("alpha: " + alpha);
					System.out.println(" | beta: " + beta);
					if (alpha >= beta) {
						return beta;
					}
				}
			}
			// System.out.println(alpha);
			return alpha;
		}
	}
	
	private int minMax(PlateauFousFous plateau, int prof, int alpha, int beta) {
		if (plateau.finDePartie() || prof == 0) {
			this.nbfeuilles++;
			//System.out.println("eval h "+h.eval(Plateau, this.joueurMin));
			return this.h.eval(plateau, this.joueurMin);
		} else {
			for (String c : plateau.mouvementPossibles(this.joueurMin)) {
				if (c != null) {
					this.nbnoeuds++;
					PlateauFousFous tmp = plateau.copy();
					tmp.play(c, this.joueurMin);
					beta = Math.min(beta, maxMin(tmp, prof - 1, alpha, beta));
					if (alpha >= beta) {
						return alpha;
					}
				}
			}
			// System.out.println(min);
			return beta;
		}
	}
	
}
