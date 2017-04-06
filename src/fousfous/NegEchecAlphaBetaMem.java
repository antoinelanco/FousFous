package fousfous;
import fousfous.PlateauFousFous;

public class NegEchecAlphaBetaMem {

	private final static int PROFMAXDEFAUT = 4;
	private int profMax = PROFMAXDEFAUT;
	private Heuristiques h;
	private String joueurMin;
	private String joueurMax;
	private int nbnoeuds;
	private int nbfeuilles;

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
		return null;
	}
	
	public String toString() {
		return "NegEchecAlphaBetaMem (ProfMax=" + profMax + ")";
	}
}