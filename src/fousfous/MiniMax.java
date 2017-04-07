package fousfous;

public class MiniMax {

	private final static int PROFMAXDEFAUT = 4;
	private int profMax = PROFMAXDEFAUT;
	private Heuristiques h;
	private String joueurMin;
	private String joueurMax;

	public MiniMax(Heuristiques h, String joueurMax, String joueurMin) {
		this(h, joueurMax, joueurMin, PROFMAXDEFAUT);
	}

	public MiniMax(Heuristiques h, String joueurMax, String joueurMin, int profMaxi) {
		this.h = h;
		this.joueurMin = joueurMin;
		this.joueurMax = joueurMax;
		this.profMax = profMaxi;
		
	}
	
	public String meilleurCoup(PlateauFousFous plateau) {
		int Max = Integer.MIN_VALUE;
		int newVal;
		String best=plateau.mouvementPossibles(this.joueurMax)[0];
		for(String move : plateau.mouvementPossibles(this.joueurMax)){
			if (move != null) {
				PlateauFousFous tmp = plateau.copy();
				tmp.play(move, this.joueurMax);
				newVal = minMax(tmp,this.profMax-1);
				System.out.print("*");
				//System.out.println("Coup :"+move+", Heuristique :"+newVal);
				if (newVal > 10000){
					System.out.println();
					return move;
				}
				if (newVal>Max){
					Max=newVal;
					best=move;
				}
			}
		}
		System.out.println();
		System.out.println("le meilleur coup est: " + best);
		return best;
		
	}
	
	public int maxMin(PlateauFousFous plateau, int prof){
		if(plateau.finDePartie() || prof == 0){
			return this.h.eval(plateau, this.joueurMax);
		}else{
			int Max = Integer.MIN_VALUE;
			for(String move : plateau.mouvementPossibles(this.joueurMax)){
				if (move != null) {
					PlateauFousFous tmp = plateau.copy();
					tmp.play(move, this.joueurMax);
					Max = Math.max(Max, minMax(tmp,prof-1));
				}
			}
			return Max;
		}
	}
	
	public int minMax(PlateauFousFous plateau, int prof){
		if(plateau.finDePartie() || prof == 0){
			return this.h.eval(plateau, this.joueurMax);
		}else{
			int Min = Integer.MAX_VALUE;
			for(String move : plateau.mouvementPossibles(this.joueurMin)){
				if (move != null) {
					PlateauFousFous tmp = plateau.copy();
					tmp.play(move, this.joueurMin);
					Min = Math.min(Min, maxMin(tmp,prof-1));
				}
			}
			return Min;
		}
	}
	
	public String toString() {
		return "NegEchecAlphaBetaMem (ProfMax=" + profMax + ")";
	}
}
