import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PlateauFousFous implements Partie1 {
	private String[][] Plateau;
	
	public PlateauFousFous(){
		
		this.Plateau = new String[8][8];
		
		
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
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
		try {
			String line;
			int i = 0,j = 0;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null){
				String[] SplitLine = line.split(" ");
				if(SplitLine[0].equals("%")) continue;
				String[] data = SplitLine[1].split("");
				for(String stats : data){
					this.Plateau[i][j] = stats;
					j++;
				}
				j=0;
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
			for(int i=0; i<8; i++){
				bw.write((i+1)+" ");
				for(int j=0; j<8; j++){
					bw.write(this.Plateau[i][j]);
				}
				bw.write(" "+(i+1));
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
		String[] data = move.split("-");
		String pion = data[0];
		String dest = data[1];
		
		int ipion = Integer.parseInt(pion.split("")[1])-1;
		int jpion = pion.toCharArray()[0] - 'A';
		
		int idest = Integer.parseInt(dest.split("")[1])-1;
		int jdest = dest.toCharArray()[0] - 'A';
		
		//System.out.println("pion :"+ipion+";"+jpion+"/ dest :"+idest+";"+jdest);
		
		if(player.split("")[0].equals(this.Plateau[ipion][jpion])){
			this.Plateau[idest][jdest] = this.Plateau[ipion][jpion];
			this.Plateau[ipion][jpion] = "-";
		}else{
			System.out.println("Il n'y a pas de pion "+player+" ici");
		}
		
		
		
	}

	@Override
	public boolean finDePartie() {
		int b=0,n=0;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(this.Plateau[i][j].equals("b")){
					b++;
				}
				else if(this.Plateau[i][j].equals("n")){
					n++;
				}
			}
		}
		if(b==0 || n==0){
			return true;
		}else{
			return false;
		}
	}
	
	public void AffichePlateau(){
		System.out.println("% ABCDEFGH");
		for(int i = 0; i<8; i++){
			System.out.print((i+1)+" ");
			for(int j = 0; j<8; j++){
				System.out.print(this.Plateau[i][j]);
			}
			System.out.println(" "+(i+1));
		}
		System.out.println("% ABCDEFGH");
	}
	
	public static void main(String[] args){
		String fileName = "test.txt";
		PlateauFousFous PF = new PlateauFousFous();
		PF.saveToFile(fileName);
		PF.setFromFile(fileName);
		PF.AffichePlateau();
		PF.play("B1-C2", "blanc");
		PF.AffichePlateau();
		PF.saveToFile(fileName);
		
	}
}
