import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graph {
	private Panneau pan;
	private PlateauFousFous Plateau;

	public Graph(PlateauFousFous Plateau) {
		this.Plateau = Plateau;
		JFrame fenetre = new JFrame();
		fenetre.setTitle("FousFous");
		fenetre.setSize(810, 845);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setResizable(false);
		this.pan = new Panneau(Plateau.Plateau);
		fenetre.setContentPane(pan);
		fenetre.setVisible(true);
	}

	public void refresh() {
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
	private String pion;
	private String dest;
	private String move;
	private boolean moveable;

	public Panneau(Cellule[][] tab) {
		this.tab = tab;
		super.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if ((i + j) % 2 == 0) {
					g.setColor(Color.white);
				} else {
					g.setColor(Color.black);
				}
				g.fillRect(j * 100, i * 100, 100, 100);

				if (tab[i][j].getColor().equals("b")) {
					g.setColor(Color.RED);
				}

				if (tab[i][j].getColor().equals("n")) {
					g.setColor(Color.BLUE);
				}

				g.fillOval(j * 100, i * 100, 100, 100);

			}
		}
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
		this.pion = "" + (char) ((e.getX() / 100) + 65) + "" + ((e.getY() / 100) + 1);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.dest = "" + (char) ((e.getX() / 100) + 65) + "" + ((e.getY() / 100) + 1);
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
