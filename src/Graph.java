import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graph {
	private Panneau pan;
	public Graph(Cellule[][] tab){
		JFrame fenetre = new JFrame();
		fenetre.setTitle("FousFous");
		fenetre.setSize(810, 845);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setResizable(false);
		this.pan = new Panneau(tab);
		fenetre.setContentPane(pan);
		fenetre.setVisible(true);
	}
	public void refresh(){
		this.pan.repaint();
	}
}

class Panneau extends JPanel {
	
	private Cellule[][] tab;
	
	public Panneau(Cellule[][] tab){
		this.tab = tab;
	}
	
	public void paintComponent(Graphics g) {
		// Vous verrez cette phrase chaque fois que la méthode sera invoquée
		System.out.println("Je suis exécutée !");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if ((i + j) % 2 == 0) {
					g.setColor(Color.white);
				} else {
					g.setColor(Color.black);
				}
				g.fillRect(i * 100, j * 100, 100, 100);
				
				if(tab[i][j].getColor().equals("b")){
					g.setColor(Color.RED);
				}
				
				if(tab[i][j].getColor().equals("n")){
					g.setColor(Color.BLUE);
				}
				
				g.fillOval(i * 100, j * 100, 100, 100);

			}
		}
	}
}
