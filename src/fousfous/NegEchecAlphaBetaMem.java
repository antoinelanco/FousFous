package fousfous;
import java.util.HashMap;

public class NegEchecAlphaBetaMem {

	private final static int PROFMAXDEFAUT = 4;
	private int profMax = PROFMAXDEFAUT;
	private Heuristiques h;
	private String joueurMin;
	private String joueurMax;
	private HashMap hash;

	public NegEchecAlphaBetaMem(Heuristiques h, String joueurMax, String joueurMin) {
		this(h, joueurMax, joueurMin, PROFMAXDEFAUT);
	}

	public NegEchecAlphaBetaMem(Heuristiques h, String joueurMax, String joueurMin, int profMaxi) {
		this.h = h;
		this.joueurMin = joueurMin;
		this.joueurMax = joueurMax;
		this.profMax = profMaxi;
		
	}

	public String meilleurCoup(PlateauFousFous plateau) {
		
		this.hash = new HashMap(100000,(float) 0.25);
		String meilleurCoup = plateau.mouvementPossibles(joueurMax)[0];
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		int Max=Integer.MIN_VALUE;

		for (String coup : plateau.mouvementPossibles(this.joueurMax)) {
			if (coup != null) {
				PlateauFousFous tmp = plateau.copy();
				tmp.play(coup, this.joueurMax);
				Max = Math.max(Max, -NEABM(tmp, this.profMax - 1, -beta, -alpha,-1));
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
		return "NegEchecAlphaBetaMem (ProfMax=" + profMax + ")";
	}
	
	public int NEABM(PlateauFousFous plateau, int prof, int alpha, int beta, int p){
		int Max = Integer.MIN_VALUE;
		int alphaInit = alpha;
		
		EntreeT Entree = (EntreeT) this.hash.get(plateau.hashCode());
		if(Entree != null && Entree.PROF >= prof){
			//System.out.println("Trouver!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			if(Entree.FLAG.equals("EXACTVAL")){
				return Entree.VAL;
			}else if(Entree.FLAG.equals("BINF")){
				alpha=Math.max(alpha, Entree.VAL);				
			}else if(Entree.FLAG.equals("BSUP")){
				beta=Math.min(beta, Entree.VAL);
			}
			if(alpha >= beta){
				return Entree.VAL;
			}
		}
		if(plateau.finDePartie() || prof == 0){
			return p*this.h.eval(plateau, this.joueurMax);
		}else{
			Max=Integer.MIN_VALUE;
			if(p>0){
				
				for(String coup : plateau.mouvementPossibles(this.joueurMax)){
					if(coup!=null){
						PlateauFousFous tmp = plateau.copy();
						tmp.play(coup, this.joueurMax);
						Max = Math.max(Max, -NEABM(tmp,prof-1,-beta,-alpha,-p));
						if(alpha >= beta){
							break;
						}
					}
				}
			}else{
				
				for(String coup : plateau.mouvementPossibles(this.joueurMin)){
					if(coup!=null){
						PlateauFousFous tmp = plateau.copy();
						tmp.play(coup, this.joueurMin);
						Max = Math.max(Max, -NEABM(tmp,prof-1,-beta,-alpha,-p));
						alpha = Math.max(alpha, Max);
						if(alpha >= beta){
							break;
						}
					}
				}
			}
		}
		EntreeT NewEntree = new EntreeT();
		NewEntree.VAL = Max;		
		if(Max <= alphaInit){
			NewEntree.FLAG = "BSUP";
		}else if(Max >= beta){
			NewEntree.FLAG = "BINF";
		}else{
			NewEntree.FLAG = "EXACTVAL";
		}
		NewEntree.PROF = prof;
		this.hash.put(plateau.hashCode(),NewEntree );
		return Max;		
		
	}
}