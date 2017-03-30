package fousfous;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PlateauFousFous implements Partie1 {
	public Cellule[][] Plateau;

	public PlateauFousFous() {

		this.Plateau = new Cellule[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i % 2) == 0 && (j % 2) == 1) {
					this.Plateau[i][j] = new Cellule(i, j, "b");
				} else if ((i % 2) == 1 && (j % 2) == 0) {
					this.Plateau[i][j] = new Cellule(i, j, "n");
				} else {
					this.Plateau[i][j] = new Cellule(i, j, "-");
				}
			}
		}
	}

	public PlateauFousFous(Cellule[][] copy) {
		this.Plateau = new Cellule[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.Plateau[i][j] = new Cellule(copy[i][j].getI(),
						copy[i][j].getJ(), copy[i][j].getColor());
			}
		}
	}

	public PlateauFousFous copy() {
		return new PlateauFousFous(this.Plateau);
	}

	@Override
	public void setFromFile(String fileName) {
		try {
			String line;
			int i = 0, j = 0;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] SplitLine = line.split(" ");
				if (SplitLine[0].equals("%"))
					continue;
				String[] data = SplitLine[1].split("");
				for (String stats : data) {
					this.Plateau[i][j] = new Cellule(i, j, stats);
					j++;
				}
				j = 0;
				i++;

			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void saveToFile(String fileName) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write("% ABCDEFGH");
			bw.newLine();
			for (int i = 0; i < 8; i++) {
				bw.write((i + 1) + " ");
				for (int j = 0; j < 8; j++) {
					bw.write(this.Plateau[i][j].getColor());
				}
				bw.write(" " + (i + 1));
				bw.newLine();
			}
			bw.write("% ABCDEFGH");
			bw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean estValide(String move, String player) {

		Cellule[] cel = moveToCellule(move);

		if(!this.isNear(cel[0], cel[1]) || !cel[0].getColor().equals(player.substring(0, 1))
			|| cel[0].getColor() == cel[1].getColor()){
			return false;
		}
		if (this.prendLePion(cel, player)|| (!this.peutPrendreUnPion(cel, player) && this.positionMenace(cel, player))) {
			return true;
		}
		return false;
	}

	public boolean positionMenace(Cellule[] cell, String player) {
		for (Cellule c : this.getDiagNear(cell[1])) {
			if (c != null){
				if (cell[1].getColor() == "b" && c.getColor() == "n" && player == "blanc") {
					return true;
				}
				if (cell[1].getColor() == "n" && c.getColor() == "b" && player == "noir") {
					return true;
				}
			}
		}
		return false;
	}

	public boolean peutPrendreUnPion(Cellule[] cell, String player) {
		for (Cellule c : this.getDiagNear(cell[0])) {
			if (c != null){
				if (cell[1].getColor() == "b" && c.getColor() == "n" && player == "blanc") {
					return true;
				}
				if (cell[1].getColor() == "n" && c.getColor() == "b" && player == "noir") {
					return true;
				}
			}
		}
		return false;
	}

	public boolean prendLePion(Cellule[] cell, String player) {
		if (cell[0].getColor() == "b" && cell[1].getColor() == "n"
				&& this.isNear(cell[0], cell[1]) && player == "blanc") {
			return true;
		}
		if (cell[0].getColor() == "n" && cell[1].getColor() == "b"
				&& this.isNear(cell[0], cell[1]) && player == "noir") {
			return true;
		}
		return false;
	}

	@Override
	public String[] mouvementPossibles(String player) {
		String[] res = new String[100];
		int iter = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.Plateau[i][j].getColor().equals(player.substring(0, 1))) {
					for (Cellule c : getDiagAll(this.Plateau[i][j])) {
						if (c != null && estValide(CelluleToMove(this.Plateau[i][j], c),player)) {
							res[iter] = CelluleToMove(this.Plateau[i][j], c);
							iter++;
						}
					}
				}
			}
		}
		String[] resu = new String[iter];
		for (int i=0; i<resu.length;i++){
			resu[i] = res[i];
		}
		//this.afficheTabMove(res);
		return resu;
	}
	
	public String afficheMouvementsPossibles(String[] tabMove){
		String res = "";
		for (int i=0; i<tabMove.length-1; i++){
			if(tabMove[i] != null){
				res = res + tabMove[i] + " | ";
			} else {
				if(tabMove[i+1] == null){
					res = res + tabMove[i];
					break;
				}
			}
		}
		return res;
	}

	@Override
	public void play(String move, String player) {

		Cellule[] cel = moveToCellule(move);
		if (player.substring(0, 1).equals(cel[0].getColor())) {
			cel[1].setColor(cel[0].getColor());
			cel[0].setColor("-");
			// System.out.println("Play")
			for (Cellule[] tab : this.Plateau){
				for (Cellule c : tab){
					if (c.getI() == cel[0].getI() && c.getJ() == cel[0].getJ()){
						c = cel[0];
					}
					if (c.getI() == cel[1].getI() && c.getJ() == cel[1].getJ()){
						c = cel[1];
					}
				}
			}
		} else {
			System.out.print("le joueur "+ player + " veut jouer le coup: " + move);
			System.out.println(" mais il n'y a pas de pion de sa couleur ici");
		}
	}
	
	@Override
	public boolean finDePartie() {
		return getPionBlanc() == 0 || getPionNoir() == 0;
	}

	public int getPionBlanc() {
		int res = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.Plateau[i][j].getColor().equals("b")) {
					res++;
				}
			}
		}
		return res;
	}

	public int getPionNoir() {
		int res = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.Plateau[i][j].getColor().equals("n")) {
					res++;
				}
			}
		}
		return res;
	}

	private boolean isNear(Cellule pion, Cellule dest) {
		for (Cellule c : getDiagNear(pion)) {
			if (c != null && c.getI() == dest.getI() && c.getJ() == dest.getJ()) {
				return true;
			}
		}
		return false;
	}

	private Cellule[][] getDiag(Cellule cel) {
		Cellule[][] res = new Cellule[4][7];

		int i = cel.getI() + 1;
		int j = cel.getJ() + 1;
		int iter = 0;
		while (i < 8 && j < 8) {
			res[0][iter] = this.Plateau[i][j];
			if (this.Plateau[i][j].getColor().equals("b")
					|| this.Plateau[i][j].getColor().equals("n")) {
				break;
			}

			iter++;
			i++;
			j++;
		}

		i = cel.getI() - 1;
		j = cel.getJ() - 1;
		iter = 0;
		while (i >= 0 && j >= 0) {

			res[1][iter] = this.Plateau[i][j];
			if (this.Plateau[i][j].getColor().equals("b")
					|| this.Plateau[i][j].getColor().equals("n")) {
				break;
			}
			iter++;
			i--;
			j--;
		}

		i = cel.getI() - 1;
		j = cel.getJ() + 1;
		iter = 0;
		while (i >= 0 && j < 8) {

			res[2][iter] = this.Plateau[i][j];
			if (this.Plateau[i][j].getColor().equals("b")
					|| this.Plateau[i][j].getColor().equals("n")) {
				break;
			}

			iter++;
			i--;
			j++;
		}

		i = cel.getI() + 1;
		j = cel.getJ() - 1;
		iter = 0;
		while (i < 8 && j >= 0) {

			res[3][iter] = this.Plateau[i][j];
			if (this.Plateau[i][j].getColor().equals("b")
					|| this.Plateau[i][j].getColor().equals("n")) {
				break;
			}
			iter++;
			i++;
			j--;
		}

		return res;
	}

	private Cellule[] getDiagAll(Cellule cel) {
		Cellule[] res = new Cellule[256];
		int iter = 0;
		for (Cellule[] cc : getDiag(cel)) {
			for (Cellule c : cc) {
				if (c != null) {
					res[iter] = c;
					iter++;
				}
			}
		}
		return res;
	}

	private Cellule[] getDiagNear(Cellule cel) {
		Cellule[][] diag = getDiag(cel);
		Cellule[] res = new Cellule[4];
		for (int i = 0; i < 4; i++) {
			loop: for (int j = 0; j < 7; j++) {
				// if(diag[i][j]!=null)System.out.println(diag[i][j].getId());
				if (diag[i][j] != null
						&& (diag[i][j].getColor().equals("b") || diag[i][j]
								.getColor().equals("n"))) {
					res[i] = diag[i][j];
					break loop;
				}

			}
			// System.out.println("space");
		}
		return res;
	}

	private boolean NearbyFoe(Cellule cell, String player) {
		if (player.substring(0, 1).equals("b")) {
			for (Cellule c : getDiagNear(cell)) {
				if (c != null && c.getColor().equals("n")) {
					return true;
				}
			}
		}

		else if (player.substring(0, 1).equals("n")) {
			for (Cellule c : getDiagNear(cell)) {
				if (c != null && c.getColor().equals("b")) {
					return true;
				}
			}
		}

		return false;

	}

	private Cellule[] moveToCellule(String move) {
		Cellule[] res = new Cellule[2];
		try {
			String[] data = move.split("-");
			String pion = data[0];
			String dest = data[1];

			res[0] = this.Plateau[Integer.parseInt(pion.substring(1, 2)) - 1][pion
					.toCharArray()[0] - 'A'];
			res[1] = this.Plateau[Integer.parseInt(dest.substring(1, 2)) - 1][dest
					.toCharArray()[0] - 'A'];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return res;

	}

	private String CelluleToMove(Cellule pion, Cellule dest) {
		return pion.getId() + "-" + dest.getId();
	}

	public void AffichePlateau() {
		System.out.println("% ABCDEFGH");
		for (int i = 0; i < 8; i++) {
			System.out.print((i + 1) + " ");
			for (int j = 0; j < 8; j++) {
				System.out.print(this.Plateau[i][j].getColor());
			}
			System.out.println(" " + (i + 1));
		}
		System.out.println("% ABCDEFGH");
	}

	public void AfficheMovePossible(String player) {
		int nl = 0;
		for (String coup : mouvementPossibles(player)) {
			if (coup != null) {
				System.out.print("{" + coup + "}");
				if (nl > 8) {
					System.out.println("");
					nl = -1;
				}
				nl++;
			}
		}
		System.out.println("");
		System.out.println("");
	}
	

	public static void main(String[] args) {
		String fileName = "test.txt";
		PlateauFousFous PF = new PlateauFousFous();
		PF.saveToFile(fileName);
		PF.setFromFile(fileName);
		PF.AffichePlateau();
		// PF.play("B1-C2", "blanc");
		PF.AffichePlateau();
		PF.saveToFile(fileName);
		System.out.println(PF.estValide("B2-C2", "blanc"));
		PF.AfficheMovePossible("blanc");
		System.out.println(PF.isNear(new Cellule(1, 1, "-"), new Cellule(1, 2,
				"n")));

		PlateauFousFous copy = PF.copy();
		copy.play("B1-C2", "blanc");

		PF.AffichePlateau();

	}
}
