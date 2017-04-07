package fousfous;
import fousfous.PlateauFousFous;

public class NegEchecAlphaBeta {

	private final static int PROFMAXDEFAUT = 4;
	private int profMax = PROFMAXDEFAUT;
	private Heuristiques h;
	private String joueurMin;
	private String joueurMax;

	public NegEchecAlphaBeta(Heuristiques h, String joueurMax, String joueurMin) {
		this(h, joueurMax, joueurMin, PROFMAXDEFAUT);
	}

	public NegEchecAlphaBeta(Heuristiques h, String joueurMax, String joueurMin, int profMaxi) {
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
				int Max = -NEAB(tmp, this.profMax - 1, -beta, -alpha,-1);
				System.out.println("Coup :"+coup+", Heuristique :"+Max);
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
		return "NegEchecAlphaBeta (ProfMax=" + profMax + ")";
	}
	
	public int NEAB(PlateauFousFous plateau, int prof, int alpha, int beta, int p){
		int Max = Integer.MIN_VALUE;
		String BestCoup;
		if(plateau.finDePartie() || prof == 0){
			if(plateau.finDePartie()){
				System.out.println("Fin de partie");
			}
			
			if(p<0){
				return p*this.h.eval(plateau, this.joueurMax);
			}else{
				return -p*this.h.eval(plateau, this.joueurMin);
			}

		}else{
			if(p>0){
				BestCoup = plateau.mouvementPossibles(this.joueurMax)[0];
				for(String coup : plateau.mouvementPossibles(this.joueurMax)){
					if(coup!=null){
						PlateauFousFous tmp = plateau.copy();
						tmp.play(coup, this.joueurMax);
						Max = Math.max(Max, -NEAB(tmp,prof-1,-beta,-alpha,-p));
						alpha = Math.max(alpha, Max);
						if(alpha >= beta){
							if(Max > 10000 || Max < -10000){
								System.out.print(coup+"<--");
							}
							return Max;
						}
					}
				}
			}else{
				BestCoup = plateau.mouvementPossibles(this.joueurMin)[0];
				for(String coup : plateau.mouvementPossibles(this.joueurMin)){
					if(coup!=null){
						PlateauFousFous tmp = plateau.copy();
						tmp.play(coup, this.joueurMin);
						Max = Math.max(Max, -NEAB(tmp,prof-1,-beta,-alpha,-p));
						alpha = Math.max(alpha, Max);
						if(alpha >= beta){
							if(Max > 10000 || Max < -10000){
								System.out.print(coup+"<--");
							}
							return Max;
						}
					}
				}
			}
		}
		if(Max > 10000 || Max < -10000){
			System.out.print(BestCoup+"<--");
		}
		return Max;
	}
}