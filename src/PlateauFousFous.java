public class PlateauFousFous implements Partie1 {
	private String[][] Plateau;
	
	public PlateauFousFous(){
		
		this.Plateau = new String[8][8];
		
		
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if((i%2)==0 && (j%2)==1){
					this.Plateau[i][j] = "b";
				}
				else if((i%2)==1 && (j%2)==0){
					this.Plateau[i][j] = "n";
				}
				else{
					this.Plateau[i][j] = "-";
				}
			}
		}
	}

	@Override
	public void setFromFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveToFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean estValide(String move, String player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] mouvementPossibles(String player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void play(String move, String player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean finDePartie() {
		// TODO Auto-generated method stub
		return false;
	}
}
