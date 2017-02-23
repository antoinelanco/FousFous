import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PlateauFousFous implements Partie1 {
	private Cellule[][] Plateau;

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

		if (player.split("")[0].equals("b")) {
			if (cel[1].getColor().equals("b")) {
				return false;
			}
			if (cel[1].getColor().equals("n")) {
				return true;
			} else if (!NearbyFoe(cel[0]) && NearbyFoe(cel[1])) {
				return true;
			}
		} else if (player.split("")[0].equals("n")) {
			if (cel[1].getColor().equals("n")) {
				return false;
			}
			if (cel[1].getColor().equals("b")) {
				return true;
			} else if (!NearbyFoe(cel[0]) && NearbyFoe(cel[1])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String[] mouvementPossibles(String player) {
		
		return null;
	}

	@Override
	public void play(String move, String player) {

		Cellule[] cel = moveToCellule(move);

		if (player.split("")[0].equals(cel[0].getColor())) {
			cel[1].setColor(cel[0].getColor());
			cel[0].setColor("-");
		} else {
			System.out.println("Il n'y a pas de pion " + player + " ici");
		}

	}

	private Cellule[] getDiag(Cellule cel) {
		Cellule[] res = new Cellule[4];

		int i = cel.getI() + 1;
		int j = cel.getJ() + 1;
		while (i < 8 && j < 8) {
			if (this.Plateau[i][j].getColor().equals("b") || this.Plateau[i][j].getColor().equals("n")) {
				res[0] = this.Plateau[i][j];
				break;
			}
			i++;
			j++;
		}

		i = cel.getI() - 1;
		j = cel.getJ() - 1;
		while (i > 0 && j > 0) {
			if (this.Plateau[i][j].getColor().equals("b") || this.Plateau[i][j].getColor().equals("n")) {
				res[1] = this.Plateau[i][j];
				break;
			}
			i--;
			j--;
		}

		i = cel.getI() - 1;
		j = cel.getJ() + 1;
		while (i > 0 && j < 8) {
			if (this.Plateau[i][j].getColor().equals("b") || this.Plateau[i][j].getColor().equals("n")) {
				res[2] = this.Plateau[i][j];
				break;
			}
			i--;
			j++;
		}

		i = cel.getI() + 1;
		j = cel.getJ() - 1;
		while (i < 8 && j > 0) {
			if (this.Plateau[i][j].getColor().equals("b") || this.Plateau[i][j].getColor().equals("n")) {
				res[3] = this.Plateau[i][j];
				break;
			}
			i++;
			j--;
		}

		return res;
	}

	private boolean NearbyFoe(Cellule cell) {
		if (cell.getColor().equals("b")) {
			for (Cellule c : getDiag(cell)) {
				if (c != null && c.getColor().equals("n")) {
					return true;
				}
			}
		}

		else if (cell.getColor().equals("n")) {
			for (Cellule c : getDiag(cell)) {
				if (c != null && c.getColor().equals("b")) {
					return true;
				}
			}
		}

		return false;

	}

	private Cellule[] moveToCellule(String move) {
		Cellule[] res = new Cellule[2];
		String[] data = move.split("-");
		String pion = data[0];
		String dest = data[1];

		res[0] = this.Plateau[Integer.parseInt(pion.split("")[1]) - 1][pion.toCharArray()[0] - 'A'];
		res[1] = this.Plateau[Integer.parseInt(dest.split("")[1]) - 1][dest.toCharArray()[0] - 'A'];
		return res;

	}

	private String CelluleToMove(Cellule pion, Cellule dest) {
		return pion.getId() + "-" + dest.getId();
	}

	@Override
	public boolean finDePartie() {
		int b = 0, n = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.Plateau[i][j].equals("b")) {
					b++;
				} else if (this.Plateau[i][j].equals("n")) {
					n++;
				}
			}
		}
		if (b == 0 || n == 0) {
			return true;
		} else {
			return false;
		}
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

	public static void main(String[] args) {
		String fileName = "test.txt";
		PlateauFousFous PF = new PlateauFousFous();
		PF.saveToFile(fileName);
		PF.setFromFile(fileName);
		PF.AffichePlateau();
		PF.play("B1-C2", "blanc");
		PF.AffichePlateau();
		PF.saveToFile(fileName);
		System.out.println(PF.estValide("C2-D3", "blanc"));


	}
}
