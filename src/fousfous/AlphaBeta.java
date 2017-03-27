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

	public String meilleurCoup(PlateauFousFous Plateau) {

		this.nbfeuilles = 0;
		this.nbnoeuds = 0;

		String meilleurCoup = Plateau.mouvementPossibles(joueurMax)[0];

		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		int MH = Integer.MIN_VALUE;

		for (String c : Plateau.mouvementPossibles(this.joueurMax)) {
			if (c != null) {
				this.nbnoeuds++;
				PlateauFousFous tmp = Plateau.copy();
				tmp.play(c, this.joueurMax);
				int Max = minMax(tmp, profMax - 1, alpha, beta);
				// System.out.println("mh : "+Max);
				if (alpha < Max) {
					alpha = Max;
					MH = alpha;
					meilleurCoup = c;

				}
			}
		}

		// System.out.println("Prof :"+this.profMax);
		System.out.println("N°Noeuds :" + this.nbnoeuds);
		System.out.println("N°Feuilles :" + this.nbfeuilles);
		System.out.println("mc :" + MH);
		return meilleurCoup;
	}

	public String toString() {
		return "AlphaBeta(ProfMax=" + profMax + ")";
	}

	private int maxMin(PlateauFousFous Plateau, int prof, int alpha, int beta) {
		if (Plateau.finDePartie() || prof == 0) {
			this.nbfeuilles++;
			// System.out.println("eval h "+h.eval(p, this.joueurMax));
			return h.eval(Plateau, this.joueurMax);
		} else {
			for (String c : Plateau.mouvementPossibles(this.joueurMax)) {
				if (c != null) {
					this.nbnoeuds++;
					PlateauFousFous tmp = Plateau.copy();
					tmp.play(c, this.joueurMax);
					alpha = Math.max(alpha, minMax(tmp, prof - 1, alpha, beta));
					if (alpha >= beta) {
						return beta;
					}
				}
			}
			// System.out.println(alpha);
			return alpha;
		}
	}

	private int minMax(PlateauFousFous Plateau, int prof, int alpha, int beta) {
		if (Plateau.finDePartie() || prof == 0) {
			this.nbfeuilles++;
			// System.out.println("eval h "+h.eval(p, this.joueurMin));
			return h.eval(Plateau, this.joueurMin);
		} else {
			for (String c : Plateau.mouvementPossibles(this.joueurMin)) {
				if (c != null) {
					this.nbnoeuds++;
					PlateauFousFous tmp = Plateau.copy();
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
