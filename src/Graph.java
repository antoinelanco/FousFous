import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Graph {
	private Panneau pan;
	private PlateauFousFous Plateau;

	public Graph(PlateauFousFous Plateau) {
		this.Plateau = Plateau;
		JFrame fenetre = new JFrame();
		fenetre.setTitle("FousFous");
		fenetre.setSize(1000, 692);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setResizable(false);
		this.pan = new Panneau(Plateau);
		fenetre.setContentPane(pan);
		fenetre.setVisible(true);
	}

	public void Victoire(String player) {
		System.out.println("Le joueur " + player + " a gg");
		JFrame fenetre = new JFrame();
		fenetre.setTitle("Victoire!");
		fenetre.pack();
		fenetre.setSize(400, 100);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Le joueur " + player + " a gg");
		panel.add(label);
		fenetre.setContentPane(panel);
		fenetre.setVisible(true);
	}

	public void refresh(String joueur) {
		this.pan.player = joueur;
		this.pan.repaint();
	}

	public String getmove() {
		return this.pan.getmove();
	}

	public boolean getmoveable() {
		return this.pan.getmoveable();
	}

	public void setmoveablefalse() {
		this.pan.setmoveablefalse();
	}

}

class Panneau extends JPanel implements MouseListener {

	private Cellule[][] tab;
	private PlateauFousFous Plateau;
	private String pion;
	private String dest;
	private String move;
	private boolean moveable;
	protected String player;
	private static Font f = new Font("Courier", Font.BOLD, 16);

	public Panneau(PlateauFousFous Plateau) {
		this.tab = Plateau.Plateau;
		this.Plateau = Plateau;
		super.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {

		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {

				if ((i + j) % 2 == 0) {
					g.setColor(Color.white);
				} else {
					g.setColor(Color.black);
				}
				g.fillRect(j * 80-50, i * 80-50, 80, 80);

				if (tab[i-1][j-1].getColor().equals("b")) {
					g.setColor(Color.RED);
				}

				if (tab[i-1][j-1].getColor().equals("n")) {
					g.setColor(Color.BLUE);
				}
				g.fillOval(j * 80-50, i * 80-50, 80, 80);

			}
		}
		for (int i = 0; i < 9; i++) {
			g.setColor(Color.BLACK);
			g.drawRect(0, i * 80-50, 30, 80);
			g.drawRect(i * 80-50,0, 80, 30);
			}
		g.setFont(f);
		g.drawString("A", 65, 20);
		g.drawString("B", 145, 20);
		g.drawString("C", 225, 20);
		g.drawString("D", 305, 20);
		g.drawString("E", 385, 20);
		g.drawString("F", 465, 20);
		g.drawString("G", 545, 20);
		g.drawString("H", 625, 20);
		g.drawString("1", 10, 75);
		g.drawString("2", 10, 155);
		g.drawString("3", 10, 235);
		g.drawString("4", 10, 315);
		g.drawString("5", 10, 395);
		g.drawString("6", 10, 475);
		g.drawString("7", 10, 555);
		g.drawString("8", 10, 635);
		setBackground(Color.GRAY);
		g.setColor(Color.BLACK);
		g.setFont(f);
		g.drawString("C'est au tour du joueur: " + this.player, 690, 110);
		g.drawString("Nombre de pions noirs: "+ Integer.toString(this.Plateau.getPionNoir()), 690,140);
		g.drawString("Nombre de pions blancs: "+ Integer.toString(this.Plateau.getPionBlanc()), 690,170);
		g.drawString("Les mouvements possibles sont: ", 690, 200);
		int x = 690, y = 220;
		g.drawString(this.Plateau.mouvementPossibles(this.player)[0] + ",", x,y);
		for (int i = 1; i < this.Plateau.mouvementPossibles(this.player).length; i++) {
			if (i % 5 == 0 && this.Plateau.mouvementPossibles(this.player)[i] != null) {
				x = 690;
				y = y + 20;
				g.drawString(this.Plateau.mouvementPossibles(this.player)[i]+ ", ", x, y);
			} else {
				if (this.Plateau.mouvementPossibles(this.player)[i] != null) {
					x = x + 60;
					g.drawString(this.Plateau.mouvementPossibles(this.player)[i]+ ", ", x, y);
				}
			}
		}
		this.repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.pion = "" + (char) (((e.getX()-30) / 80) + 65) + ""
				+ (((e.getY()-30) / 80) + 1);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.dest = "" + (char) (((e.getX()-30) / 80) + 65) + ""
				+ (((e.getY()-30) / 80) + 1);
		this.move = this.pion + "-" + this.dest;
		System.out.println(this.move);
		this.moveable = true;

	}

	public void setmoveablefalse() {
		this.moveable = false;
	}

	public String getmove() {
		return this.move;
	}

	public boolean getmoveable() {
		return this.moveable;
	}
}
