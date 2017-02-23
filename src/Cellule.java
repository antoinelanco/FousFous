public class Cellule {
	private int i;
	private int j;
	private String Color;
	private String id;
	
	public Cellule(int i, int j, String Color){
		this.i = i;
		this.j = j;
		this.Color = Color;
		this.id=""+(char)(j+65)+""+(i+1);
	}
	
	public String getColor(){
		return this.Color;
	}
	
	public void setColor(String Color){
		this.Color = Color;
	}
	public int getI(){
		return this.i;
	}
	
	public int getJ(){
		return this.j;
	}
	
	public String getId(){
		return this.id;
	}
}
